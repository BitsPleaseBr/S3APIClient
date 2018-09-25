package s3.api.method.resources.verificar;

import com.amazonaws.http.HttpMethodName;
import s3.api.method.resources.Resource;
import s3.api.method.resources.users.Users;

public class Verificar extends Resource {
  
  
  public static final Verificar EMAIL = new Verificar(HEAD), CPF = new Verificar(HEAD), CONFIRMAR_EMAIL = new Verificar(POST);
  
  
  public Verificar(HttpMethodName httpMethod) {
    
    super(httpMethod);
  }

  @Override
  public String getAbsolutePath() {
    
    if (this.parentResource == null)
      this.parentResource = new Users();
    
    return this.parentResource.getAbsolutePath() + "/verificacoes";
  }

}
