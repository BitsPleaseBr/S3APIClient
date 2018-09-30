package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogsAsync;
import com.amazonaws.services.logs.AWSLogsAsyncClientBuilder;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.LogStream;
import com.amazonaws.services.logs.model.PutLogEventsRequest;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementAsync;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementAsyncClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;

public class Logger {
  private static final AWSSimpleSystemsManagementAsync ssmClient =
      AWSSimpleSystemsManagementAsyncClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
  private static final AWSLogsAsync asyncClient = AWSLogsAsyncClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
  private static final String logGroupName = getSSMParam("s3WebsiteLogGroup");
  private static final String logStreamName = getSSMParam("s3WebsiteLogStream");
  private static Collection<InputLogEvent> inputStack = new ArrayList<InputLogEvent>();

  
  public static void log(String mensagem) {
    Collection<InputLogEvent> inputs = new ArrayList<InputLogEvent>();
    inputs.add(getNewInputLogEvent(mensagem));
    asyncClient.putLogEvents(getNewEventRequest(inputs));
  }

  public static void log(Exception e) {
    for (StackTraceElement el : e.getStackTrace()) {
      addToStack(el.toString());
    }
    logStack();
  }

  public static void addToStack(String mensagem) {
    inputStack.add(getNewInputLogEvent(mensagem));
    
  }

  public static void logStack() {
    if (inputStack.size() > 0) {
      asyncClient.putLogEvents(getNewEventRequest(inputStack));
      inputStack.clear();
    }
  }

  private static String getSSMParam(String param) {
    GetParameterRequest getRequest = new GetParameterRequest();
    getRequest.setName(param);
    GetParameterResult result = ssmClient.getParameter(getRequest);
    return result.getParameter().getValue();
  }
  private static PutLogEventsRequest getNewEventRequest(Collection<InputLogEvent> inputs) {
    PutLogEventsRequest eventsRequest = new PutLogEventsRequest();
    eventsRequest.setLogGroupName(logGroupName);
    eventsRequest.setLogStreamName(logStreamName);
    
    //Aqui também tava errado, ele usava o inputStack, que tava vazio
    eventsRequest.setLogEvents(inputs);
    
    //Nesse request dava um erro de que tava faltando um token
    eventsRequest.setSequenceToken(getSequenceToken(logGroupName));
    return eventsRequest;
  }
  
  private static InputLogEvent getNewInputLogEvent(String mensagem) {
    InputLogEvent logInput = new InputLogEvent();
    logInput.setMessage(mensagem);
    logInput.setTimestamp(System.currentTimeMillis());
    return logInput;
  }
  
  //Método pra pegar o token
  private static String getSequenceToken(String logGroupName) {
    
    String token = null;
    
    DescribeLogStreamsRequest logStreamRequest = new DescribeLogStreamsRequest().withLogGroupName(logGroupName);
    List<LogStream> logStreamList = asyncClient.describeLogStreams(logStreamRequest).getLogStreams();
    
    for (LogStream logStream : logStreamList) {
      
      token = logStream.getUploadSequenceToken();
    }
    
    return token;
  }
}