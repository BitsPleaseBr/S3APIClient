package s3.api.method.client;

import ca.ryangreen.apigateway.generic.GenericApiGatewayClient;
import ca.ryangreen.apigateway.generic.GenericApiGatewayRequest;
import ca.ryangreen.apigateway.generic.GenericApiGatewayResponse;
import s3.api.method.response.S3ApiResponse;

public class S3ApiClient {

  
  private GenericApiGatewayClient client;
  
  
  public S3ApiClient(GenericApiGatewayClient client) {
    
    this.client = client;
  }
  
  
  public void setClient(GenericApiGatewayClient client) {
    
    this.client = client;
  }
  
  public S3ApiResponse execute(GenericApiGatewayRequest request) {
    
    GenericApiGatewayResponse response = this.client.execute(request);
    
    return new S3ApiResponse(response);
  }
}