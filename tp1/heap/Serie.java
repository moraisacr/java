/**
 * Classe SÃ©rie
 * TP - Algoritmos e Estruturas de Dados II
 * @author - Augusto Cesar
 * 2021 - 2o. Semestre
 */
import java.text.ParseException;
import java.io.*;
import java.io.FileReader;
import java.net.MalformedURLException;

class Serie{
    //declaraÃ§Ã£o dos atributos
    static int tam = 0;
    private String name;
    private String format;
    private String duration;
    private String country;
    private String language;
    private String broadcaster;
    private String streaming;
    private int seasons;
    private int episodes;
    static int numComparacao = 0;
    static int numMovimentacao = 0;
    //construtor primÃ¡rio
    public Serie(){
        name = "";
        format = "";
        duration = "";
        country = "";
        language = "";
        broadcaster = "";
        streaming = "";
        seasons = 0;
        episodes = 0;
    }
    //construtor secundÃ¡rio
    public Serie(String name, String format, String duration, String country, String language, String broadcaster, String streaming, int seasons, 
    int episodes){
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.broadcaster = broadcaster;
        this.streaming = streaming;
        this.seasons = seasons;
        this.episodes = episodes;
    }
    //mÃ©todo para setar o atributo name
    public void setName(String name){
        this.name = name;
    }
    //mÃ©todo para setar o atributo Format
    public void setFormat(String format){
        this.format = format;
    }
    //mÃ©todo para setar o atributo duration
    public void setDuration(String duration){
        this.duration = duration;
    }
    //mÃ©todo para setar o atributo country
    public void setCountry(String country){
        this.country = country;
    }
    //mÃ©todo para setar o atributo language
    public void setLanguage(String language){
        this.language = language;
    }
    //mÃ©todo para setar o atributo broadcaster
    public void setBroadcaster(String broadcaster){
        this.broadcaster = broadcaster;
    }
    //mÃ©todo para setar o atributo streaming
    public void setStreaming(String streaming){
        this.streaming = streaming;
    }
    //mÃ©todo para setar o atributo seasons
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }
    //mÃ©todo para setar o atributo episodes
    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }
    //mÃ©todo para retornar o atributo name
    public String getName(){ 
        return this.name; 
    }
    //mÃ©todo para retornar o atributo format
    public String getFormat(){ 
        return this.format; 
    }
    //mÃ©todo para retornar o atributo duration
    public String getDuration(){ 
        return this.duration; 
    }
    //mÃ©todo para retornar o atributo country
    public String getCountry(){ 
        return this.country; 
    }
    //mÃ©todo para retornar o atributo language
    public String getLanguage(){ 
        return this.language; 
    }
    //mÃ©todo para retornar o atributo broadcaster
    public String getBroadcaster(){ 
        return this.broadcaster; 
    }
    //mÃ©todo para retornar o atributo streaming
    public String getStreaming(){ 
        return this.streaming; 
    }
    //mÃ©todo para retornar o atributo seasons
    public int getSeasons(){ 
        return this.seasons; 
    }
    //mÃ©todo para retornar o atributo episodes
    public int getEpisodes(){ 
        return this.episodes; 
    }
    //mÃ©todo para clonar a classe
    public Serie clone(){
        Serie resp = new Serie();
        resp.name = this.name;
        resp.format = this.format;
        resp.duration = this.duration;
        resp.country = this.country;
        resp.language = this.language;
        resp.broadcaster = this.broadcaster;
        resp.streaming = this.streaming;
        resp.seasons = this.seasons;
        resp.episodes = this.episodes;
        return resp;
    }
    //mÃ©todo para printar a classe
    public void printClass(){
        System.out.println(name + " " + format + " " + duration + " " + country + " " + language + " " + broadcaster + " " +
        streaming + " " + seasons + " " + episodes);
    }
    //mÃ©todo para tratar a linha, deixar apenas nÃºmeros e converter o retorno de String para Integer
    public static int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um nÃºmero ele Ã© concatenado a variÃ¡vel resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condiÃ§Ã£o de parada e o mÃ©todo de repetiÃ§Ã£o Ã© encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //conversÃ£o da string resp para nÃºmero inteiro a ser retornado
    }
    //mÃ©todo para a remoÃ§Ã£o das tags da linha lida do arquivo para retornar apenas o que Ã© desejado
    public static String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // Ã© testado para verificar se o contador i ainda estÃ¡ dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o laÃ§o de repetiÃ§Ã£o Ã© encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exceÃ§Ãµes presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags Ã© concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }
    public static boolean conferirFim(String linha){ /* CONDIÃ‡ÃƒO DE PARADA */
		boolean resp;
		if(linha.length()==3 && linha.charAt(0)=='F' && linha.charAt(1)=='I' && linha.charAt(2)=='M'){
			resp = true;
		}else{
			resp = false;
		}
		return resp;
	}
    //mÃ©todo para tratar o nome do arquivo e retornar o nome da sÃ©rie
    public static String searchName(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posiÃ§Ã£o i seja igual ao '_' a variÃ¡vel resp recebe um espaÃ§o em branco
                resp += ' ';
            } else { //caso nÃ£o tenha espaÃ§o em branco o caracter Ã© concatenado Ã  string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 Ãºltimos caracteres relacionados Ã  extensÃ£o do arquivo
    }
    //mÃ©todo para leitura do arquivo .html e tratamento das linhas
    public static Serie readClass(String fileName)throws IOException {
        Serie serie = new Serie();
        
        String resp = "";
        String file = "/tmp/series/" + fileName;
        try {
            FileReader fileReader = new FileReader(file); //declaraÃ§Ã£o da variÃ¡vel fileReader que serÃ¡ recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declaraÃ§Ã£o do bufferedReader para leitura do arquivo
            
            //set nome da sÃ©rie
            serie.setName(tirarEspaco(searchName(fileName)));
            
            
            //set Format da sÃ©rie
            while(!br.readLine().contains("Format"));
            serie.setFormat(removeTags(br.readLine()));

            //set duraÃ§Ã£o da sÃ©rie
            while(!br.readLine().contains("DuraÃ§Ã£o"));
            serie.setDuration(tirarEspaco(removeTags(br.readLine())));
            

            //set paÃ­s da sÃ©rie
            while(!br.readLine().contains("PaÃ­s de origem"));
            serie.setCountry(tirarEspaco(removeTags(br.readLine())));
            

            //set idioma da sÃ©rie
            while(!br.readLine().contains("Idioma original"));
            serie.setLanguage(tirarEspaco(removeTags(br.readLine())));
            

            //set emissora da sÃ©rie
            while(!br.readLine().contains("Emissora de televisÃ£o"));
            serie.setBroadcaster(tirarEspaco(removeTags(br.readLine())));
            

            //set transmissÃ£o original da sÃ©rie
            while(!br.readLine().contains("TransmissÃ£o original"));
            serie.setStreaming(tirarEspaco(removeTags(br.readLine())));
            

            //set temporadas da sÃ©rie
            while(!br.readLine().contains("N.Âº de temporadas"));
            serie.setSeasons(justInt(removeTags(br.readLine())));

            //set episÃ³dios da sÃ©rie
            while(!br.readLine().contains("N.Âº de episÃ³dios"));
            serie.setEpisodes(justInt(removeTags(br.readLine())));
            
            //mÃ©todo para mostrar a classe
            //serie.printClass();
            //fechamento do bufferedReader
            br.close();         
        //Tratamento de exceÃ§Ãµes
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return serie;
    }
    public static String[] leituraPubIn(){
        String[] linhas = new String[100];
    	    
            linhas[tam] = MyIO.readLine();
            while(conferirFim(linhas[tam]) == false){ /* LEITURA DO ARQUIVO ENQUANTO ESSE NÃƒO CHEGAR NO FIM */
            tam++;
            linhas[tam] = MyIO.readLine();
            }
            return linhas;
		        		
	}
	
/* MAIN DO CÃ“DIGO QUE CHAMA OS METODOS */
public static void main(String[] args)throws ParseException{
	MyIO.setCharset("UTF-8");
	
	Serie[] S = new Serie[100];
	String[] linhasPubIn = leituraPubIn();
    Serie[] serie = new Serie[tam];
	int i = 0;
	try{
		while(i < tam){
			serie[i] = readClass(linhasPubIn[i]);
			i++;
		}
    }catch(IOException e) {
        e.printStackTrace();
		}
    long tempoInicial = System.currentTimeMillis();
    Serie[] seriesOrdenadas = sort(serie);
    long tempoTotal = System.currentTimeMillis() - tempoInicial;
	  
    for(i = 0; i < tam; i++){
        System.out.println("teste");
        seriesOrdenadas[i].printClass();
    }
    
    Arq.openWrite("613764_heapsort.txt");
	Arq.println("Matricula: 613764	" + "Tempo:" + tempoTotal + " " + "ComparaÃ§Ãµes:" + numComparacao + 
    "Numero de movimentacoes"+ numMovimentacao + "");
	Arq.close(); 
}
/*public static Serie[] swap(int i, int j, Serie[] array) {
    
    Serie temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    return array;
 }
*/
public static String tirarEspaco(String atributo){
    if(atributo.charAt(atributo.length() - 1) == ' '){
    atributo = atributo.replaceFirst(".$","");
    }
    if(atributo.charAt(0) == ' '){
    atributo = atributo.substring(1);
    }
    return atributo;
    }

    public static Serie[] swap(Serie[] array, int i, int j) {
        Serie temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        numMovimentacao += 3;
        return array;
     }

    public static Serie[] sort(Serie[] array) {
        //Alterar o vetor ignorando a posicao zero
        Serie[] tmp = new Serie[tam+1];
        for(int i = 0; i < tam; i++){
           tmp[i+1] = array[i];
           numMovimentacao++;
        }
        array = tmp;
  
        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= tam; tamHeap++){
            array = construir(tamHeap, array);
        }
  
        //Ordenacao propriamente dita
        int tamHeap = tam;
        while(tamHeap > 1){
            array = swap(array, 1, tamHeap--);
            array = reconstruir(tamHeap, array);
        }
  
        //Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new Serie[tam];
        for(int i = 0; i < tam; i++){
           array[i] = tmp[i+1];
           numMovimentacao++;
        }
        return array;
     }
  
  
     public static Serie[] construir(int tamHeap, Serie[] array){
        for(int i = tamHeap; i > 1 && (array[i].getFormat().compareTo(array[i/2].getFormat()) > 0 || (array[i].getFormat().compareTo(array[i/2].getFormat()) == 0 && array[i].getName().compareTo(array[i/2].getName()) > 0)); i /= 2){
           array = swap(array, i, i/2);
           numMovimentacao++;
        }
        return array;
     }
  
  
     public static Serie[] reconstruir(int tamHeap, Serie[] array){
        int i = 1;
        while(i <= (tamHeap/2)){
           int filho = getMaiorFilho(i, tamHeap, array);
           numComparacao++;
           if(array[i].getFormat().compareTo(array[filho].getFormat()) < 0 || (array[i].getFormat().compareTo(array[filho].getFormat()) == 0 && array[i].getName().compareTo(array[filho].getName()) < 0)){
              array = swap(array, i, filho);
              i = filho;
           }else{
              i = tamHeap;
           }
        }
        return array;
     }
  
     public static int getMaiorFilho(int i, int tamHeap, Serie[] array){
        int filho = 0;
        numComparacao++;
        if (2*i == tamHeap || array[2*i].getFormat().compareTo(array[2*i+1].getFormat()) > 0 || (array[2*i].getFormat().compareTo(array[2*i+1].getFormat()) == 0 && array[2*i].getName().compareTo(array[2*i+1].getName()) > 0)){
           filho = 2*i;
        } else {
           filho = 2*i + 1;
        }
        return filho;
     }

  
}

    
 
        
         
