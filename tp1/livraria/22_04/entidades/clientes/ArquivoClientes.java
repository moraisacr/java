package entidades.clientes;

import aed3.Arquivo;
import aed3.HashExtensivel;

public class ArquivoClientes extends Arquivo<Cliente> {

  HashExtensivel<ParEmailID> indiceIndiretoEmail;

  public ArquivoClientes(String nomePasta) throws Exception {
    super(nomePasta, Cliente.class.getConstructor());
    indiceIndiretoEmail = new HashExtensivel<>(
        ParEmailID.class.getConstructor(),
        4,
        nomePasta + "/indiceEmail.1.db",
        nomePasta + "/indiceEmail.2.db");
    
  }

  

  // ---------------------
  // CREATE
  // ---------------------
  @Override
  public int create(Cliente cliente) throws Exception {
    int id = super.create(cliente);
    cliente.setID(id);
    indiceIndiretoEmail.create(
        new ParEmailID(cliente.getEmail(), id));
    return id;
  }

  // ---------------------
  // READ (email)
  // ---------------------
  public Cliente read(String email) throws Exception {
    ParEmailID p = indiceIndiretoEmail.read(Math.abs(email.hashCode()));
    if (p == null)
      return null;
    return read(p.getID()); // método da superclasse
  }

  public boolean update(Cliente novoCliente, String email) throws Exception {
    // Lembre-se de que, se a chave (email) for alterada, o update
    // deve excluir o par anterior e inserir o novo par

    /**
     * ideia Caio
     * pesquisa na Hash, verifica se é o mesmo email, 
     * caso sejam iguais, modifica pelo que o usuário quer e 
     * termina à ação 
     */

    // System.out.println(novoCliente.getID());

    ParEmailID pEmailId = new ParEmailID(novoCliente.getEmail(), novoCliente.getID());
    //deleta aquela posição na hash
    indiceIndiretoEmail.delete(Math.abs(email.hashCode()));
    //insere um novo par
    indiceIndiretoEmail.create(pEmailId);
    // ParEmailID p = indiceIndiretoEmail.read(pEmailId.getID());

    return super.update(novoCliente);

  }

  public boolean delete(Cliente cliente) throws Exception {
    //ideia do Caio; 
    /**
     * Coleta compara o email pesquisado com o da posição da Hash
     * depois de verificar, pega o id da posição chama a super e deleta o objeto.
     * pode dar certo 
     */

     //Caio, a chave do índice indireto não é o ID, mas o hashCode do email.
    
    indiceIndiretoEmail.delete(Math.abs(cliente.getEmail().hashCode()));
    
    return super.delete(cliente.getID());
  }

}
