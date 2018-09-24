package main;

import ca.ryangreen.apigateway.generic.GenericApiGatewayException;
import s3.api.access.MethodCallerFactory;

public class Caller {


  public static void main(String[] args) {

    try {
      
      System.out.println(MethodCallerFactory.verificarCpf("09313177978").call().getResponse().getHttpResponse().getStatusCode());
      
      /*PacienteBean pb = new PacienteBean();
      
      pb.setInfo(UserInfo.Nome, "Gilson");
      pb.setInfo(UserInfo.Sobrenome, "Gerson");
      pb.setInfo(UserInfo.CPF, "12345678912");
      pb.setInfo(UserInfo.DataNascimento, "23/12/1998");
      pb.setInfo(UserInfo.Email, "gilsongerson@gmail.com");
      pb.setInfo(UserInfo.Senha, "meunomeégilsongerson");
      
      System.out.println(MethodCallerFactory.cadastrarUser(pb).call().getResponse().getBody());*/
    } catch (GenericApiGatewayException e) {

      e.printStackTrace();
    }
  }
}