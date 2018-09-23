package s3.api.method.response;

import java.util.HashMap;
import com.google.gson.Gson;
import ca.ryangreen.apigateway.generic.GenericApiGatewayResponse;

public class S3ApiResponse {


  private GenericApiGatewayResponse response;


  public S3ApiResponse(GenericApiGatewayResponse response) {

    this.response = response;
  }
  
  public GenericApiGatewayResponse getResponse() {
    
    return this.response;
  }


  public HashMap<String, Object> getHashBody() {

    @SuppressWarnings("unchecked")
    HashMap<String, Object> body =
        (HashMap<String, Object>) new Gson().fromJson(response.getBody(), HashMap.class);

    return body;
  }
}
