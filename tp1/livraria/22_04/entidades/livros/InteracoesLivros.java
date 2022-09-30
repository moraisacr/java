package entidades.livros;

import java.io.File;
import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class InteracoesLivros {

  private static Scanner console = new Scanner(System.in);
  private ArquivoLivros arqLivros;

  public InteracoesLivros() {
    try {
      File d;
      d = new File("dados");
      if (!d.exists())
        d.mkdir();
      d = new File("dados/livros");
      if (!d.exists())
        d.mkdir();
      arqLivros = new ArquivoLivros("dados/livros");
    } catch (Exception e) {
      System.out.println("Arquivo não pode ser aberto ou criado.");
      e.printStackTrace();
    }
  }

  public Livro leLivro() throws Exception {
    System.out.print("\nTítulo: ");
    String titulo = console.nextLine();
    System.out.print("\nAutor: ");
    String autor = console.nextLine();
    System.out.print("\nPreço: ");
    float preco = Float.parseFloat(console.nextLine().replaceAll(",", "."));
    System.out.print("\nData de lançamento (dd/mm/aaaa): ");
    String[] partesData = console.nextLine().split("/");
    Calendar lancamento = Calendar.getInstance();
    lancamento.set(
        Integer.parseInt(partesData[2]),
        Integer.parseInt(partesData[1]) - 1,
        Integer.parseInt(partesData[0]));
    Livro l = new Livro(titulo, autor, preco, lancamento.getTime());
    return l;
  }

  public void mostraLivro(Livro l) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    System.out.println(
        "\nTítulo: " + l.getTitulo() +
            "\nAutor: " + l.getAutor() +
            "\nPreço: R$ " + l.getPreco() +
            "\nData de lançamento: " + sdf.format(l.getLancamento()));
  }

  public void menuLivros() throws Exception{
    int opcao;
    do {
      System.out.println("\nMENU DE LIVROS");
      System.out.println("\n1) Incluir livro");
      System.out.println("2) Buscar livro por id");
      System.out.println("3) Buscar livro por nome");
      System.out.println("4) Alterar livro");
      System.out.println("5) Excluir livro");
      System.out.println("\n0) Retornar ao menu anterior");

      System.out.print("\nOpção: ");
      try {
        opcao = Integer.valueOf(console.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
        case 1:
          incluirLivro();
          break;
        case 2:
          buscarLivro();
          break;
        case 3:
          buscarLivroNome();
          break;
        case 4:
          alterarLivro();
          break;
        case 5:
          excluirLivro();
          break;
        case 0:
          break;
        default:
          System.out.println("Opção inválida");
      }
    } while (opcao != 0);
  }

  public void incluirLivro() {
    Livro novoLivro;
    try {
      novoLivro = leLivro();
    } catch (Exception e) {
      System.out.println("Dados inválidos");
      return;
    }

    int id;
    try {
      id = arqLivros.create(novoLivro);
    } catch (Exception e) {
      System.out.println("Livro não pode ser criado");
      e.printStackTrace();
      return;
    }

    System.out.println("\nLivro criado com o ID " + id);

  }

  public void buscarLivro() {
    int id;
    System.out.print("\nID do Livro: ");
    try {
      id = Integer.valueOf(console.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    try {
      Livro livro = arqLivros.read(id);
      if(livro != null){
        mostraLivro(livro);
      }else{
        System.out.println("Livro não encontrado!");
      }
    } catch (Exception e) {
      System.out.println("Erro no acesso ao arquivo");
      e.printStackTrace();
    }

  }

  public void buscarLivroNome()throws Exception{
    String nome; 
    Livro livro;
    System.out.println("Título do livro: ");
    nome = console.nextLine();

    try{
      livro = arqLivros.read(nome);
      if(livro != null){
        mostraLivro(livro);
      }else{
        System.out.println("Cliente não encontrado!");
      }
    }catch(Exception e){
      System.out.println("Erro no acesso do arquivo");
      e.printStackTrace();
    }

  }

  public void alterarLivro() throws Exception{
    String nome, novoNome, respUsuario = "";
    Livro livro;
    boolean respUpdate = false;

    do{
      System.out.println("Digite o nome do Livro desejado: ");
      nome = console.nextLine();

      livro = arqLivros.read(nome);
      System.out.println(livro);
      mostraLivro(livro);

      System.out.println("Podemos proseguir?");
      System.out.println("Sim ou Não");
      respUsuario = console.nextLine();
      respUsuario = respUsuario.toLowerCase();

    }while(respUsuario == "sim" || respUsuario == "s");

    System.out.println("Digite o novo título: ");
    novoNome = console.nextLine();
    livro.setTitulo(novoNome);

    respUpdate = arqLivros.update(livro, nome);
    if(respUpdate == true){
      System.out.println("Atualizado com sucesso");
    }else{
      System.out.println("Não foi possível atualizar o usuário");
    }
  }

  public void excluirLivro()throws Exception{
    String nome = "", respUsuario = "";
    Livro livro;

    do{
      System.out.println("Digite o título do livro que você deseja apagar");
      nome = console.nextLine();

      livro = arqLivros.read(nome);
      mostraLivro(livro);

      System.out.println("Deseja apagar esse livro?");
      System.out.println("Sim ou não:");
      respUsuario = console.nextLine();
      respUsuario = respUsuario.toLowerCase();
    }while(respUsuario == "sim" || respUsuario == "s");
    boolean respDelete = arqLivros.delete(livro);

    if(respDelete == true){
      System.out.println("Exclusão feita com sucesso");
    }else{
      System.out.println("Não foi possível deletar o email!");
    }

  }

}
