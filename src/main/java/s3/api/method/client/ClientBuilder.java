package s3.api.method.client;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import ca.ryangreen.apigateway.generic.GenericApiGatewayClientBuilder;
import s3.api.method.caller.MethodCaller;

public class ClientBuilder extends GenericApiGatewayClientBuilder {


  private String endpoint = "https://bh7xqk7gcj.execute-api.sa-east-1.amazonaws.com";
  private Region region = Region.getRegion(Regions.fromName("sa-east-1"));
  private MethodCaller methodCaller;
  private boolean useApiKey = false;


  @Override
  public GenericApiGatewayClientBuilder withEndpoint(String endpoint) {
    return this;
  }

  @Override
  public GenericApiGatewayClientBuilder withRegion(Region region) {
    return this;
  }

  public void useApiKey(boolean useApiKey) {
    
    this.useApiKey = useApiKey;
  }
  

  public ClientBuilder setMethodCaller(MethodCaller caller) {

    this.methodCaller = caller;
    return this;
  }

  public MethodCaller getMethodCaller() {

    return this.methodCaller;
  }

  public S3ApiClient s3Build() {

    super.withEndpoint(endpoint);
    super.withRegion(region);
    
    System.out.println(getS3ApiKey());
    
    if (useApiKey)
      super.withApiKey(getS3ApiKey());

    return new S3ApiClient(super.build());
  }
  
  private String getS3ApiKey() {
    
    GetParameterRequest getRequest = new GetParameterRequest();
    getRequest.setName("S3WebClientAPIKey");
    GetParameterResult result = AWSSimpleSystemsManagementClientBuilder.standard().withRegion(Regions.SA_EAST_1).build().getParameter(getRequest);
    return result.getParameter().getValue();
  }
}
