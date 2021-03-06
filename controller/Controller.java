/***************************
Autor: Rafael Araújo e Igor Garcia
Componente Curricular: MI programação 
Concluído em: 16/07/2017
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estamos cientes que estes trechos não serão considerados para fins de avaliação.
******************************/

package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Util.AVLTree;
import Util.Iterator;
import model.Mercadoria;

public class Controller {
	AVLTree salgueiroLutador;//arvore
	
    public Controller(){
    	salgueiroLutador = new AVLTree();
    }
    
    /**
     * esse metodo retorna o leitor do arquivo
     * pega um diretorio
     * arq recebe o diretorio do arquivo txt
     * o try/catch trata um possível erro de abertura do arquivo
     * lerArq recebe o aquivo (proviniente de arq)
     * retorna o lerArq (para ser usado por outro método)
     * fecha o arquivo
     */
    public static BufferedReader leituraDeArquivo() throws IOException{
    	String diretorio = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt";
    	FileReader arq = null;
            try {
                arq = new FileReader(diretorio);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
            }
        BufferedReader lerArq = new BufferedReader(arq);
        if(arq != null){
        	return lerArq;
        }
        lerArq.close();
        return null;
    }
    
    
    
    /** 
    * le primeira linha, recebe o valor "null" quando o processo de repeticao atingir o final do arquivo de texto
    * enquanto nao acabarem as linhas
    * divide a string/frase onde tem -;- e coloca em words, as strings distam -;- de uma a outra.
    * são 6 atributos
    * muda String para int
    * le da segunda em diante
    * muda a posilção do vetor de mercadorias
    */
    public Mercadoria[] transferirArquivoNoVetor() throws IOException{//usa o leitor
        BufferedReader leitor = leituraDeArquivo();
        int contador = contarMercadorias();
        int j = 0;
        Mercadoria[] temp = new Mercadoria[contador+1];
        String linha = null;
        if(leitor != null && contador != 0){
            linha = leitor.readLine(); 
            while (linha != null){
                String words[] = linha.split(";");
                for(int i = 0; i < 6; i++){
                    if(i == 0){
                           int k = Integer.parseInt(words[i]);
                           temp[j].setLote(k);
                    }else if(i == 1){
                            temp[j].setBloco(words[i]);
                    }else if(i == 2){
                           temp[j].setBloco(words[i]);
                    }else if(i == 3){
                           temp[j].setNumero(words[i]);
                    }else if(i == 4){
                           temp[j].setFornecedor(words[i]);
                    }else if(i == 5){
                           temp[j].setData(words[i]);
                    }else
                           temp[j].setHora(words[i]);
                }
                linha = leitor.readLine(); 
                j++;
            }
            leitor.close();
            return temp;
        }
        return null;
    }
	
    
    
    /**
     * cria nos com os numeros das mercadorias
     * esvazia o vetor
     */
    public void inserirNaArvore() throws IOException{
            Mercadoria[] lote = transferirArquivoNoVetor();
            if(lote != null){
                    for(int i = 0; i < lote.length; i++){
                            salgueiroLutador.inserir(lote[i]);
                    }
                    lote = null;
            }
    }

    public Iterator listarDadosDaArvore(){
        Iterator pass = salgueiroLutador.iterador();
        return pass;
    }

    public void escreverNoArquivo() throws IOException{
            Mercadoria[] org = organizarMercadorias();

            FileWriter escritor = new FileWriter("teste.csv");
            BufferedWriter escrever = new BufferedWriter(escritor);
            String suporte = "";
            String[] produto = null;
            for(int i = 0; i < org.length; i++){//são 6 atributos
                produto[i] = org[i].getLote() + ";" + org[i].getEndereco() + ";" + org[i].getBloco() + ";" + org[i].getNumero() + ";" + org[i].getFornecedor()  + ";" + org[i].getData() + ";" + org[i].getHora();//em cada posição tem uma frase
                suporte = produto[i];
                escrever.write(suporte);
                escrever.newLine();
                escrever.flush();
                suporte = "";
    }
            escrever.close();

    }

    /**
     * recebe um objeto de Mercadoria e as chaves para serem comparadas
     * utiliza o compareTo() para comparar as chaves da mercadoria recebida com os atributos restantes
     * @param teste
     * @param l
     * @param e
     * @param b
     * @param n
     * @param f
     */
    public boolean testeDasChaves(Mercadoria teste, int l, String e, String b, String n, String f){
            if(l == teste.getLote()){
                    if(e.compareTo(teste.getEndereco()) == 0){
                            if(b.compareTo(teste.getBloco()) == 0){
                                    if(n.compareTo(teste.getNumero()) == 0){
                                            if(f.compareTo(teste.getFornecedor()) == 0){
                                                    return true;
                                            }else
                                                    return false;
                                    }else
                                            return false;
                            }else
                                    return false;
                    }else
                            return false;
            }else
                    return false;
    }

	
    /**
     * chama o metodo de remoção existente na classe AVLTree
     * manda a chave para esse método istanciado pelo objeto "salgueiroLutador"
     * @param chave 
     */
    public void removerDaArvore(Mercadoria chave){
            salgueiroLutador.remover(chave);
    }

    /**
     * o método recebe os parâmetros de Mercadoria
     * instancia um objeto de Mercadoria e do Iterator
     * enquanto não chegar ao fim da árvore, continua passando
     * compara em cada loop os dados da mercadoria usando o método testeDasChaves
     * @param m
     * @param l
     * @param e
     * @param b
     * @param n
     * @param f
     * @return 
     */
    public Mercadoria buscarMercadoria(Mercadoria m, int l, String e, String b, String n, String f){
        Mercadoria teste;
        Iterator pass = salgueiroLutador.iterador();
        if(m != null){
            while(pass.hasNext()){
                teste = (Mercadoria) pass.next();
                if(testeDasChaves(teste, l, e, b, n, f)){
                    return teste;
                }
            }
        }
        return null;
    }

    private Mercadoria[] organizarMercadorias() {
            return null;
    }

	
    
    
    /**
     * conta o nº de linhas do arquivo
     * le a primeira linha
     * a variavel "linha" recebe o valor "null" quando o processo de repeticao atingir o final do arquivo de texto
     * enquanto não acabarem as linhas
     * conta o numero de linhas
     * lê da segunda em diante
     */
    
    public int contarMercadorias(){
        String  linha;
        int quantDeLinhas = 0;
        try {
            FileReader arq = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            linha = lerArq.readLine(); 
                                       

            
            quantDeLinhas++;
             
            while (linha != null){
              
              quantDeLinhas++;
              linha = lerArq.readLine(); 
              
            }
              
            arq.close();
        
        }
        
        catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
        }
        return quantDeLinhas;
    }
    
    
    
}
