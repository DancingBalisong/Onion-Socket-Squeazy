
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
	AVLTree sequoia;//arvore
	
    public Controller(){
    	sequoia = new AVLTree();
    }
    
    public static BufferedReader leituraDeArquivo() throws IOException{//esse metodo retorna o leitor do arquivo
    	String diretorio = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt";//OU pode vir de um outro metodo, depende do teste.
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
    
	public Mercadoria[] transferirArquivoNoVetor() throws IOException{//usa o leitor
		BufferedReader leitor = leituraDeArquivo();
		int contador = contarMercadorias();
		int j = 0;
		Mercadoria[] temp = new Mercadoria[contador];
		String linha = null;
		if(leitor != null && contador != 0){
			linha = leitor.readLine(); // le primeira linha, recebe o valor "null" quando o processo de repeticao atingir o final do arquivo de texto
			while (linha != null){//enquanto nao acabarem as linhas
               String words[] = linha.split(";");//divide a string/frase onde tem -;- e coloca em words, as strings distam -;- de uma a outra.
               for(int i = 0; i < 6; i++){//s�o 6 atributos
            	   if(i == 0){
            		  int k = Integer.parseInt(words[i]);//mudar String para int
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
               linha = leitor.readLine(); // le da segunda em diante
               j++;//muda a posil�ai do vetor de mercadorias
            }
			leitor.close();
			return temp;
		}
		return null;
	}
	
	public void inserirNaArvore() throws IOException{
		Mercadoria[] lote = transferirArquivoNoVetor();
		if(lote != null){
			for(int i = 0; i < lote.length; i++){
				sequoia.inserir(lote[i]);//cria nos com os numeros das mercadorias
			}
			lote = null;//esvazia o vetor
		}
	}
	
	public void listarDadosDaArvore(){
		
	}
	
	public void escreverNoArquivo() throws IOException{
		Mercadoria[] org = organizarMercadorias();
		
		FileWriter escritor = new FileWriter("teste.csv");
		BufferedWriter escrever = new BufferedWriter(escritor);
		String suporte = "";
		String[] produto = null;
		for(int i = 0; i < org.length; i++){//s�o 6 atributos
			produto[i] = org[i].getLote() + ";" + org[i].getEndereco() + ";" + org[i].getBloco() + ";" + org[i].getNumero() + ";" + org[i].getFornecedor()  + ";" + org[i].getData() + ";" + org[i].getHora();//em cada posi��o tem uma frase
			suporte = produto[i];
			escrever.write(suporte);
			escrever.newLine();
			escrever.flush();
			suporte = "";
        }
		escrever.close();
	
	}
	
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
	
	public void removerDaArvore(Mercadoria chave){
		sequoia.remover(chave);
	}
	
	public Mercadoria buscarMercadoria(Mercadoria m, int l, String e, String b, String n, String f){
		Mercadoria teste;
		Iterator pass = sequoia.iterador();
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

	public Mercadoria buscarMercadoria(String lote, String bloco, String fornecedor){//ira retornar os dados da linha desejada do arquivo
    
        /*Scanner teclado = new Scanner(System.in);
        
        System.out.println("Digite o endereço da mercadoria: ");
        String end = teclado.nextLine();
        
        System.out.println("Digite o nome do fornecedor: ");
        String forn = teclado.nextLine();
        
        String  linha;
        
        try {
            FileReader arq = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);
           

            linha = lerArq.readLine(); // lê a primeira linha
                                       // a variável "linha" recebe o valor "null" quando o processo
                                       // de repetição atingir o final do arquivo de texto

            
                                       
            String palav[] = linha.split(",");//divide onde tem espaço e coloca em palav
              
             
            if(end.equals(palav[0]) && forn.equals(palav[4]){//compara o endereço e o fornecedor aos do arquivo
                //retorna os dados que estão nessa linha do arquivo
            }
              
                                       
             
        
            while (linha != null){//enquanto nao acabarem as linhas
              
               String words[] = linha.split(";");//divide onde tem espaço e coloca em words, as strings distam -;- de uma a outra.
               linha = lerArq.readLine(); // le da segunda em diante
              
               if(end.equals(palav[0]) && forn.equals(palav[4]){//compara o endereco e o fornecedor aos do arquivo
                  //retorna os dados que estão nessa linha do arquivo
               }
            }
              
            System.out.printf("\nA quantidade de linhas desse arquivo é de: %d\n", quantDeLinhas);
            System.out.println("A quantidade de vezes que a palavra '" + nome + "' aparece é: " + quantPalavras);
            arq.close();
        
        }
        
        catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
        }
        */
        return null;
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    
    public int contarMercadorias(){//conta o n� de linhas do arquivo
        String  linha;
        int quantDeLinhas = 0;
        try {
            FileReader arq = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            linha = lerArq.readLine(); // le a primeira linha
                                       // a variavel "linha" recebe o valor "null" quando o processo
                                       // de repeticao atingir o final do arquivo de texto

            
                                       
            /////////// Tem a mesma função que a estrutura abaixo (só existe pra 1a linha do arquivo)
            String palav[] = linha.split(" ");//divide onde tem espaço e coloca em palav
              
            quantDeLinhas++;
            System.out.printf("%s\n", linha);//imprime a linha
             
            while (linha != null){//enquanto não acabarem as linhas
              
              quantDeLinhas++;//conta o numero de linhas
              String words[] = linha.split(" ");//divide onde tem espaço e coloca em words
              linha = lerArq.readLine(); // lê da segunda em diante
              
              System.out.printf("%s\n", linha);//imprime a linha
              
              
        }
              
            System.out.printf("\nA quantidade de linhas desse arquivo é de: %d\n", quantDeLinhas);
            arq.close();
        
        }
        
        catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
        }
        return quantDeLinhas;
    }
    
    
    
}
