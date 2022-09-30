package aed3;

public interface Registro {
  public void setID(int id);

  public int getID();

  public byte[] toByteArray() throws Exception;

  public void fromByteArray(byte[] ba) throws Exception;

}
