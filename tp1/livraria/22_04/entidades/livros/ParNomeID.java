package entidades.livros;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aed3.RegistroHashExtensivel;

public class ParNomeID implements RegistroHashExtensivel<ParNomeID> {

    private String nome;
    private int id;
    private short TAMANHO = 44;

    public ParNomeID() throws Exception{
        this("", -1);
    }

    public ParNomeID(String nome, int id) throws Exception{
        if(nome.length() > 38){
            throw new Exception("Nome maior do que o permitido");
        }
        this.nome = nome;
        this.id = id;
    }

    @Override
    public int hashCode(){
        return Math.abs(this.nome.hashCode());
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public short size() {
        return this.TAMANHO;
    }
    public String toString(){
        return this.nome + ";" + this.id;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(nome);
        dos.writeInt(id);
        byte[] b1 = baos.toByteArray();
        byte[] b2 = new byte[this.TAMANHO];
        for (int i = 0; i < b1.length; i++)
            b2[i] = b1[i];
        return b2;
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.nome = dis.readUTF();
        this.id = dis.readInt();
    }
}
