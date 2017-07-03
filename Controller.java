
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.Mercadoria;

public class Controller {
	
    public Controller(){
    }
    
    
    
	public Mercadoria[] transferirArquivoNoVetor(){
		return null;
	}
	
    public String buscarMercadoria(){//ira retornar os dados da linha desejada do arquivo
    
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
        
    }
    
    
    //////////////////////////////////////////////////////////////////////////
    
    public void contarMercadorias(){//conta o n� de linhas do arquivo
        String  linha;
        
        try {
            FileReader arq = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            int quantDeLinhas = 0;//atributo para contar as linhas
           

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
        
    }
    
    
    
}
