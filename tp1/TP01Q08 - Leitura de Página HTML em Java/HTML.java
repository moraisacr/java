import java.io.*;
import java.net.*;

public class HTML {
    public static void main(String[] args) {
        MyIO.setCharset("UTF-8");
        String endereco;
        String html;
        String entrada;
        String parada = "FIM";
        entrada = MyIO.readLine();
        while (comparar(entrada, parada) == false) {
            endereco = MyIO.readLine();
            html = leitura(endereco);
            int c = 0;
            int c2 = 0;
            c = table(html);
            c2 = br(html);
            MyIO.print("a("+(lerA(html)-(1*c))+")");
            MyIO.print(" e("+(lerE(html)-(1*c))+")");
            MyIO.print(" i("+lerI(html)+")");
            MyIO.print(" o("+lerO(html)+")");
            MyIO.print(" u("+lerU(html)+")");
            MyIO.print(" á("+lerA2(html)+")");
            MyIO.print(" é("+lerE2(html)+")");
            MyIO.print(" í("+lerI2(html)+")");
            MyIO.print(" ó("+lerO2(html)+")");
            MyIO.print(" ú("+lerU2(html)+")");
            MyIO.print(" à("+lerA3(html)+")");
            MyIO.print(" è("+lerE3(html)+")");
            MyIO.print(" ì("+lerI3(html)+")");
            MyIO.print(" ò("+lerO3(html)+")");
            MyIO.print(" ù("+lerU3(html)+")");
            MyIO.print(" ã("+lerA4(html)+")");
            MyIO.print(" õ("+lerO4(html)+")");
            MyIO.print(" â("+lerA5(html)+")");
            MyIO.print(" ê("+lerE5(html)+")");
            MyIO.print(" î("+lerI5(html)+")");
            MyIO.print(" ô("+lerO5(html)+")");
            MyIO.print(" û("+lerU5(html)+")");
            MyIO.print(" consoante("+(consoante(html)-(c * 3)-(c2 * 2))+")");
            MyIO.print(" <br>("+br(html)+")");
            MyIO.print(" <table>("+table(html)+")");
            MyIO.print(" " + entrada + "\n");
            entrada = MyIO.readLine();
        }
    }

    //METODO COMPARAR STRINGS
    public static boolean comparar(String s, String t) {
        boolean r = true;
        int tamanho;
        tamanho = s.length();
        if (s.length() == t.length()) {
            for (int i = tamanho - 1; i >= 0; i--) {
                if (s.charAt(i) != t.charAt(i)) {
                    r = false;
                }
            }
        } else {
            r = false;
        }
        return r;
    }

    //METODO DE LEITURA DO PUB.IN
    public static String leitura(String endereco) {
        URL f;
        InputStream t = null;
        BufferedReader b;
        String r = "";
        String linha;
        try {
            f = new URL(endereco);
            t = f.openStream();
            b = new BufferedReader(new InputStreamReader(t));
            while ((linha = b.readLine()) != null) {
                r += linha + "\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        try {
            t.close();
        } catch (IOException i) {
        }
        return r;
    }

    //METODO LEITURA DA VOGAL A
    public static int lerA(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'a') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL E
    public static int lerE(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'e') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL I
    public static int lerI(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'i') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL O
    public static int lerO(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'o') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL U
    public static int lerU(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'u') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Á
    public static int lerA2(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'á') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL É
    public static int lerE2(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'é') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Í
    public static int lerI2(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'í') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ó
    public static int lerO2(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ó') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ú
    public static int lerU2(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ú') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL À
    public static int lerA3(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'à') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL È
    public static int lerE3(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'è') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ì
    public static int lerI3(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ì') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ò
    public static int lerO3(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ò') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ù
    public static int lerU3(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ù') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ã
    public static int lerA4(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ã') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Õ
    public static int lerO4(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'õ') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Â
    public static int lerA5(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'â') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ê
    public static int lerE5(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ê') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Î
    public static int lerI5(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'î') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Ô
    public static int lerO5(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'ô') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA VOGAL Û
    public static int lerU5(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'û') {
                cont++;
            }
        }
        return cont;
    }

    //METODO LEITURA DA QUANTIDADE DE CONSOANTES
    public static int consoante(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) > 'a' && html.charAt(i) <= 'z') {
                if (html.charAt(i) != 'e' && html.charAt(i) != 'i' && html.charAt(i) != 'o' && html.charAt(i) != 'u') {
                    cont++;
                }
            }
        }
        return cont;
    }

    //METODO LEITURA DA QUANTIDADE DE <BR>
    public static int br(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<') {
                if (html.charAt(i + 1) == 'b') {
                    if (html.charAt(i + 2) == 'r') {
                        if (html.charAt(i + 3) == '>') {
                            cont++;
                        }
                    }
                }
            }
        }
        return cont;
    }

    //METODO LEITURA DA QUANTIDADE DE <TABLE>
    public static int table(String html) {
        int cont = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<') {
                if (html.charAt(i + 1) == 't') {
                    if (html.charAt(i + 2) == 'a') {
                        if (html.charAt(i + 3) == 'b') {
                            if (html.charAt(i + 4) == 'l') {
                                if (html.charAt(i + 5) == 'e') {
                                    if (html.charAt(i + 6) == '>') {
                                        cont++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return cont;
    }

}
