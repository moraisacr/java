package entidades.clientes;

import java.io.File;
import java.util.Scanner;

public class InteracoesClientes {

  private static Scanner console = new Scanner(System.in);
  private ArquivoClientes arqClientes;

  public InteracoesClientes() {
    try {
      File d;
      d = new File("dados");
      if (!d.exists())
        d.mkdir();
      d = new File("dados/clientes");
      if (!d.exists())
        d.mkdir();
      arqClientes = new ArquivoClientes("dados/clientes");
    } catch (Exception e) {
      System.out.println("Arquivo não pode ser aberto ou criado.");
      e.printStackTrace();
    }
  }

  public Cliente leCliente() throws Exception {
    System.out.print("\nNome..: ");
    String nome = console.nextLine();
    System.out.print("E-Mail: ");
    String email = console.nextLine();
    System.out.print("CPF: ");
    String cpf = console.nextLine();
    Cliente l = new Cliente(nome, email, cpf);
    return l;
  }

  public void mostraCliente(Cliente l) {
    System.out.println(
        "\nNome: " + l.getNome() + "\nE-mail: " + l.getEmail()+ "\nCpf: " + l.getCpf());
  }

  public void menuClientes() throws Exception {
    int opcao;
    do {
      System.out.println("\nMENU DE CLIENTES");
      System.out.println("\n1) Incluir cliente");
      System.out.println("2) Buscar cliente por ID");
      System.out.println("3) Buscar cliente por Cpf");
      System.out.println("4) Buscar cliente por E-mail");
      System.out.println("5) Alterar cliente");
      System.out.println("6) Excluir cliente");
      System.out.println("\n0) Retornar ao menu anterior");

      System.out.print("\nOpção: ");
      try {
        opcao = Integer.valueOf(console.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
        case 1:
          incluirCliente();
          break;
        case 2:
          buscarClientePorID();
          break;
        case 3:
          //buscarClientePorCpf();
          break;
        case 4:
          buscarClientePorEmail();
          break;        
        case 5:
          alterarCliente();
          break;
        case 6:
          excluirCliente();
          break;
        case 0:
          break;
        default:
          System.out.println("Opção inválida");
      }
    } while (opcao != 0);
  }

  public void incluirCliente() {
    Cliente novoCliente;
    try {
      novoCliente = leCliente();
    } catch (Exception e) {
      System.out.println("Dados inválidos");
      return;
    }

    int id;
    try {
      id = arqClientes.create(novoCliente);
    } catch (Exception e) {
      System.out.println("Cliente não pode ser criado");
      e.printStackTrace();
      return;
    }

    System.out.println("\nCliente criado com o ID " + id);

  }

  public void buscarClientePorID() {
    int id;
    System.out.print("\nID do Cliente: ");
    try {
      id = Integer.valueOf(console.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    try {
      Cliente cliente = arqClientes.read(id);
      if (cliente != null)
        mostraCliente(cliente);
      else
        System.out.println("Cliente não encontrado!");
    } catch (Exception e) {
      System.out.println("Erro no acesso ao arquivo");
      e.printStackTrace();
    }

  }

  /*public void buscarClientePorCpf() {
    String cpf;
    System.out.print("\nCpf do Cliente: ");
    cpf = console.nextLine();

    try {
      Cliente cliente = arqClientes.read(cpf);
      if (cliente != null)
        mostraCliente(cliente);
      else
        System.out.println("Cliente não encontrado!");
    } catch (Exception e) {
      System.out.println("Erro no acesso ao arquivo");
      e.printStackTrace();
    }

  }*/


  public void buscarClientePorEmail() {
    String email;
    System.out.print("\nE-mail do Cliente: ");
    email = console.nextLine();

    try {
      Cliente cliente = arqClientes.read(email);
      if (cliente != null)
        mostraCliente(cliente);
      else
        System.out.println("Cliente não encontrado!");
    } catch (Exception e) {
      System.out.println("Erro no acesso ao arquivo");
      e.printStackTrace();
    }

  }

  public void alterarCliente()throws Exception{

    String email, novoEmail, respUsuario = "";
    Cliente cliente;
    boolean respUpdate = false;

    //recebe o email e verifica se é o que o usuário deseja;
    do{
      System.out.println("Digite o E-mail desejado: ");
      email = console.nextLine();

      cliente = arqClientes.read(email);
      mostraCliente(cliente);

      System.out.println("Podemos proseguir?");
      System.out.println("Sim ou Não: ");
      respUsuario = console.nextLine();
      respUsuario = respUsuario.toLowerCase();    
      //System.out.println(respUsuario); Debug

    }while(respUsuario == "sim" || respUsuario == "s");

    System.out.println("Digite o novo E-mail: ");
    novoEmail = console.nextLine();    
    cliente.setEmail(novoEmail);

    respUpdate = arqClientes.update(cliente, email);
    if(respUpdate == true){
      System.out.println("Atualizado com sucesso");
    }else{
      System.out.println("Não foi possível atualizar o usuário");
    }
  }

  public void excluirCliente()throws Exception{

    String email = "", respUsuario = "";
    Cliente c;

    do{
      System.out.println("Digite o email que você deseja apagar: ");
      email = console.nextLine();

      c = arqClientes.read(email);
      mostraCliente(c);

      System.out.println("Deseja apagar esse email? ");
      System.out.print("Sim ou não: ");
      respUsuario = console.nextLine();
      respUsuario = respUsuario.toLowerCase();
    }while(respUsuario == "sim" || respUsuario == "s");

    boolean respDelete = arqClientes.delete(c);

    if(respDelete == true){
      System.out.println("Exclusão feita com sucesso");
    }else{
      System.out.println("Não foi possível deletar o email!");
    }

  }

}
