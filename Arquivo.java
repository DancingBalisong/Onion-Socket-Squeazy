package MestreKami;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Scanner;

public class Arquivo {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//Scanner teclado = new Scanner(System.in);
		//C:\\Users\\Usuário\\Anti-plágio.txt
		System.out.println("teste.");
		String nome = "C:\\Users\\Usuário\\Armazem.csv";
		String linha = null;
		@SuppressWarnings("unused")//é usada sim, cale sua boca
		int qtdLinha = 0;//atributo contator de linhas
			
		try{
			FileReader arq = new FileReader(nome);
			BufferedReader lerArquivo = new BufferedReader(arq);
			qtdLinha = 0;
			linha = lerArquivo.readLine();//lê a primeira linha  e linha recebe o valor null quando o processo de repetição atingir o fim do arquivo
			while(linha != null){
				System.out.println(linha);
				qtdLinha++;
				linha = lerArquivo.readLine();//lê da segunda até a última linha
			}
			
			System.out.println("teste.");
			lerArquivo.close();
		}
		
		catch(IOException e){
			System.err.printf("teste.\n", e.getMessage());
		}
		
		try{
			FileWriter out = new FileWriter("teste.txt");
			BufferedWriter outPut = new BufferedWriter(out);
			//vou usar a ultima linha do arquivo.
			
			outPut.write("Rafael acha Kayla Kayden burra.");
			outPut.close();
			
		}catch(IOException e){
			System.err.printf("teste.\n", e.getMessage());
		}
		
		try{//copia do mesmo arquivo, agora quero por fornecedor como Kayla Kayden, hu3
			FileReader leitor = new FileReader(nome);
			BufferedReader lerKayla = new BufferedReader(leitor);
			FileWriter escritor = new FileWriter("teste.txt");
			BufferedWriter escreverKayden = new BufferedWriter(escritor);
			qtdLinha = 0;//atributo contator de linhas
			linha = lerKayla.readLine();
			String[] troll = null;
			String suporte = "";
			
			while(linha != null){
				System.out.println("Kayla Kayden é demais!" + linha);
				qtdLinha++;
				troll = linha.split(";");
				troll[4] = "Kayla Kayden";
				for(int i = 0; i < troll.length; i++){
					if(i < troll.length - 1){
						suporte += troll[i] + ";";
					}else
						suporte += troll[i];
				}
				escreverKayden.write(suporte);
				escreverKayden.newLine();
				escreverKayden.flush();
				linha = lerKayla.readLine();//lê da segunda até a última linha
				suporte = "";
			}
			
			System.out.println("teste.");
			lerKayla.close();
			escreverKayden.close();
		}
		catch(IOException e){
			System.err.printf("teste.\n", e.getMessage());
		}
	}	
}
