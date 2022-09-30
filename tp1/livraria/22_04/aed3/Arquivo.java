package aed3;

import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro> {

  private RandomAccessFile arquivo;
  private HashExtensivel<ParIDEndereco> indiceDireto;
  private Constructor<T> construtor;
  private int TAMANHO_CABECALHO = 4;

  public Arquivo(String nomePasta, Constructor<T> c) throws Exception {
    arquivo = new RandomAccessFile(nomePasta + "/dados.db", "rw");
    indiceDireto = new HashExtensivel<>(
        ParIDEndereco.class.getConstructor(),
        4,
        nomePasta + "/indiceID.1.db",
        nomePasta + "/indiceID.2.db");
    construtor = c;
    if (arquivo.length() < TAMANHO_CABECALHO) {
      arquivo.seek(0);
      arquivo.writeInt(0);
    }
  }

  public int create(T entidade) throws Exception {
    arquivo.seek(0);
    int ultimoID = arquivo.readInt();
    int novoID = ultimoID + 1;
    entidade.setID(novoID);
    arquivo.seek(0);
    arquivo.writeInt(novoID);

    // Movimenta o ponteiro do arquivo para o ponto de inserção do novo registro
    arquivo.seek(arquivo.length());
    long endereco = arquivo.getFilePointer();

    // Cria o registro no arquivo
    byte[] ba = entidade.toByteArray();
    arquivo.writeByte('#'); // # -> registro válido; * -> registro excluído
    arquivo.writeShort(ba.length);
    arquivo.write(ba);
    indiceDireto.create(new ParIDEndereco(novoID, endereco));
    return novoID;
  };

  public T read(int id) throws Exception {

    ParIDEndereco p = indiceDireto.read(id);
    if (p == null)
      return null;

    arquivo.seek(p.getEndereco());

    byte lapide = arquivo.readByte();
    int tamanho = arquivo.readShort();
    byte[] ba = new byte[tamanho];
    if (lapide == '#') {
      arquivo.read(ba);
      T entidade = construtor.newInstance();
      entidade.fromByteArray(ba);
      if (entidade.getID() == id)
        return entidade;
    }

    System.out.println("Arquivo corrompido.");
    return null;
  };

  public boolean update(T novaEntidade) throws Exception{
        
    ParIDEndereco p = indiceDireto.read(novaEntidade.getID());
    if(p == null){
        return false;
    }
    //movo para a posição do registro
    arquivo.seek(p.getEndereco());
    byte lapide = arquivo.readByte();
    int tamanho = arquivo.readShort();
    //System.out.println(tamanho);
    byte[] ba = new byte[tamanho];
    //verifico se é válido ou não
    if(lapide == '#'){
        arquivo.read(ba);
        T entidade = construtor.newInstance();
        entidade.fromByteArray(ba);
        
        if(entidade.getID() == novaEntidade.getID()){
            byte[] novoRegistro = novaEntidade.toByteArray();
            //System.out.println(novoRegistro.length);
            if(novoRegistro.length <= tamanho){
                arquivo.seek(p.getEndereco());
                arquivo.writeByte('#');
                arquivo.writeShort(tamanho);
                arquivo.write(novoRegistro);
            }else{
                arquivo.seek(p.getEndereco());
                arquivo.writeByte(' ');
                long pos = arquivo.length();
                arquivo.seek(pos);
                arquivo.writeByte('#');
                arquivo.writeShort(novoRegistro.length);
                arquivo.write(novoRegistro);

                ParIDEndereco novo = new ParIDEndereco(novaEntidade.getID(), pos);
                indiceDireto.update(novo);
            }
        }else{
            // System.out.println("passei aqui2");
            return false;
        }
        return true;
    }
    
    return false;
    
};

public boolean delete(int id) throws Exception {

    ParIDEndereco p = indiceDireto.read(id);
    if (p == null) {
        return false;
    }
    arquivo.seek(p.getEndereco());

    byte lapide = arquivo.readByte();
    int tamanho = arquivo.readShort();
    byte[] ba = new byte[tamanho];
    if (lapide == '#') {
        T entidade = construtor.newInstance();
        entidade.fromByteArray(ba);
        arquivo.seek(p.getEndereco());
        arquivo.writeByte(' ');
        indiceDireto.delete(id);
        return true;
    }
    return false;

};

}
