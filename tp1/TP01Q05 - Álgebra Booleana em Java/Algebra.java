public class Algebra{
    final static String[] op1 ={
    " ",
    "not(0)",
    "not(1)",
    "and(0,0)",
    "and(1,1)",
    "and(0,1)",
    "and(1,0)",
    "and(0,0)",
    "and(1,1)",
    "and(0,1)",
    "and(1,0)",
    "or(0,0)",
    "or(1,1)",
    "or(0,1)",
    "or(1,0)",
    "or(0,0)",
    "or(1,1)",
    "or(0,1)",
    "or(1,0)"
    };

    final static String[] op2 ={
    "",
    "1",
    "0",
    "0",
    "1",
    "0",
    "0",
    "and(and(0,0),",
    "and(and(1,1),",
    "and(and(0,1),",
    "and(and(1,0),",
    "0",
    "1",
    "1",
    "1",
    "or(or(0,0),",
    "or(or(1,1),",
    "or(or(0,1),",
    "or(or(1,0),"
    };

  	//METODO MAIN DO CODIGO
  	public static void main(String[]args) {
		leituraPubIn();
  	}

  	//METODO DE LEITURA DO PUB.IN 
  	public static void leituraPubIn() {
    	int i = MyIO.readInt();
    	if (i == 0) {
      		return;
    	} else {
      		int[] vetor = new int[i];
      		int[] valores = criarArranjo(i,vetor);
      		String e1 = MyIO.readLine();
      		String e2 = alterLetra(e1, valores);
      		MyIO.println(conferir(e2));
      		leituraPubIn();
    	}
  	}

  	//METODO QUE CRIA ARRANO E RETORNA UM BOOLEAN
  	public static int[] criarArranjo(int i, int vetor[]){
    	return criarArranjo(i,vetor,0);
  	}
  
  	//METODO QUE CRIA ARRANO E RETORNA UM BOOLEAN RECURSIVO
  	public static int[] criarArranjo(int i,int vetor[], int j) {
    	if (j < i) {
      		vetor[j] = MyIO.readInt();
      		criarArranjo(i,vetor,j + 1);
    	}
    	return vetor;
  	}

  	//METODO PARA ALTERAR LETRA 
  	public static String alterLetra(String e1,int valores[]) {
    	return alterLetra(e1,valores,0);
  	}

  	//METODO PARA ALTERAR LETRA RECURSIVO
  	public static String alterLetra(String e1,int valores[], int i)	{
    	String r = "";
    	if (i >= e1.length()) {
      		return r;
    	} else {
      		r += alterLetra2(e1,valores,i);
      		r += alterLetra(e1,valores, i + 1);
    	}
    	return r;
  	}

  	//METODO AUXILIAR DO ALTERAR LETRA
  	public static char alterLetra2(String e1,int valores[],int i) {
    	return alterLetra2(e1,valores,i,0);
  	}
  
  	//METODO AUXILIAR DO ALTERAR LETRA RECURSIVO
  	public static char alterLetra2(String e1,int valores[],int i,int c) {
    	char letra;
    	if (c >= valores.length) {
      		letra = e1.charAt(i);
    	} else if ((char)('A' + c) == e1.charAt(i)) {
      		letra = (char)(valores[c] + '0');
    	} else {
      		letra = alterLetra2(e1,valores,i,c + 1);
    	}
    	return letra;
  	}

  	//METODO CONFERIR RESULTADO
  	public static char conferir(String e2) {
    	if (e2.charAt(0) == '0' || e2.charAt(0) == '1') {
    		//MyIO.println("Entrou no IF");
      		return e2.charAt(0);
    	} else {
    		//MyIO.println("Entrou no ELSE");
            e2 = conferir2(e2);
        }
    	return conferir(e2);
  	}

  	//METODO CONFERIR RESULTADO AUXILIAR
  	public static String conferir2(String e2) {
    	return conferir2(e2,0);
  	}

  	//METODO CONFERIR RESULTADO AUXILIAR RECURSIVO
 	 public static String conferir2(String e2, int i) {
    	if (i >= op1.length) {
    		MyIO.println("Entrou no IF");
      		return e2;
    	} else {
    		MyIO.println("Entrou no ELSE");
      		e2 = substituirExpressao(e2, op1[i], op2[i]);
    	}
    	return conferir2(e2,i + 1);
	}
  
  	//METODO DE SUBSTITUIR EXPRESSÃO POR RESPOSTA
  	public static String substituirExpressao(String frase, String procurando, String lugar) {
    	return substituirExpressao(frase,procurando,lugar,0);
  	}

  	//METODO DE SUBSTITUIR EXPRESSÃO POR RESPOSTA RECURSIVO
  	public static String substituirExpressao(String frase, String procurando, String lugar, int i) {
        String nova = "";
        if (i >= frase.length()) {
        	MyIO.println("Entrou no IF");
            nova = "";
        } else if (saoIguais(frase, procurando, i)) {
        	MyIO.println("Entrou no IF e ELSE");
            nova += lugar;
            i += procurando.length() - 1;
            nova += substituirExpressao(frase, procurando, lugar, i + 1);
        } else {
        	MyIO.println("Entrou no ELSE");
            nova += frase.charAt(i);
            nova += substituirExpressao(frase, procurando, lugar, i + 1);
        }
        return nova;
    }

  	//METODO QUE CONFERE SE AS STRINGS SÃO IGUAIS
  	public static boolean saoIguais(String frase, String procurando, int i) {
    	boolean t = false;
    	int j = i;
    	if (frase.length()-i >= procurando.length() && frase.charAt(i) == procurando.charAt(0)) {
      		j += saoIguais2(frase,procurando,i,j);
      		t = (j-i) >= procurando.length();
    	}
    	return t;
  	}

  	//METODO QUE CONFERE SE AS STRINGS SÃO IGUAIS AUXILIAR
  	public static int saoIguais2(String frase, String procurando, int i,int j) {
    	int c;
    	if(!(j < frase.length() && j-i < procurando.length() && frase.charAt(j) == procurando.charAt(j-i))) {
    		c = 0;
    	} else {
      		c = 1 + saoIguais2(frase,procurando,i,j + 1);
    	}
    	return c;
  	}  
}
