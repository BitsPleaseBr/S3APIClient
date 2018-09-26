package main;

import ca.ryangreen.apigateway.generic.GenericApiGatewayException;

public class Caller {


  public static void main(String[] args) {

    try {
      
      /*EnderecoBean eb = new EnderecoBean();
      eb.setInfo(EnderecoInfo.Cep, "123");
      eb.setInfo(EnderecoInfo.Estado, "SC");
      eb.setInfo(EnderecoInfo.Cidade, "Blumenau");
      eb.setInfo(EnderecoInfo.Bairro, "Itoupava Central");
      eb.setInfo(EnderecoInfo.Rua, "Erwin Henschel");
      eb.setInfo(EnderecoInfo.Numero, 643);
      eb.setInfo(EnderecoInfo.Complemento, "Casa");
      eb.setInfo(EnderecoInfo.IDUser, 1);
      eb.setInfo(EnderecoInfo.Tipo, eb.RESIDENCIAL);
      
      System.out.println(new Gson().newBuilder().setPrettyPrinting().create().toJson(eb));
      
      TelefoneBean tb = new TelefoneBean();
      tb.setInfo(TelefoneInfo.Tipo, tb.TELEFONE);
      tb.setInfo(TelefoneInfo.Numero, "12345-1234");
      tb.setInfo(TelefoneInfo.IDUser, 1);
      
      System.out.println(new Gson().newBuilder().setPrettyPrinting().create().toJson(tb));*/
      
      //System.out.println(MethodCallerFactory.verificarEmail("nathangabriel97@gmail.com").call().getResponse().getHttpResponse().getStatusCode() == 200);
      //System.out.println(MethodCallerFactory.verificarCpf("057.575.029-40").call().getResponse().getHttpResponse().getStatusCode() == 200);
      
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