package entidades.clientes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aed3.RegistroHashExtensivel;

public class ParCpfID implements RegistroHashExtensivel<ParCpfID> {

  private String cpf; 
  private int id;
  private short TAMANHO = 11; 

  public ParCpfID() throws Exception {
    this("", -1);
  }

  public ParCpfID(String cpf, int id) throws Exception {
    if (cpf.length() > 38)
      throw new Exception("Cpf invalido");
    this.cpf = cpf;
    this.id = id;
  }

  @Override
  public int hashCode() {
    return Math.abs(this.cpf.hashCode());
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public int getID() {
    return this.id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public short size() {
    return this.TAMANHO;
  }

  public String toString() {
    return this.cpf + ";" + this.id;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(cpf);
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
    this.cpf = dis.readUTF();
    this.id = dis.readInt();
  }

}