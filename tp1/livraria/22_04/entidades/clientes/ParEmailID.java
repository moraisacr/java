package entidades.clientes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aed3.RegistroHashExtensivel;

public class ParEmailID implements RegistroHashExtensivel<ParEmailID> {

  private String email; // máximo 38 bytes
  private int id;
  private short TAMANHO = 44; // bytes

  public ParEmailID() throws Exception {
    this("", -1);
  }

  public ParEmailID(String email, int id) throws Exception {
    if (email.length() > 38)
      throw new Exception("E-mail maior que o permitido");
    this.email = email;
    this.id = id;
  }

  @Override
  public int hashCode() {
    return Math.abs(this.email.hashCode());
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    return this.email + ";" + this.id;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(email);
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
    this.email = dis.readUTF();
    this.id = dis.readInt();
  }

}