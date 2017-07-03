
package escrevenoarquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EscreveNoArquivo {

    
    
    public static void main(String[] args) {
        try{
            
            /*ESCREVER*/
            FileWriter arq = new FileWriter ("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");//arquivo para escrita
            BufferedWriter escreveArq = new BufferedWriter (arq);
            Scanner teclado = new Scanner(System.in);
            
            
            System.out.println("Digite o texto a ser inserido no arquivo: ");
            String texto = teclado.nextLine();
            
            escreveArq.append (texto + "\n");
            
            arq.close();
        }
        
        
        catch (IOException io){
        }
    }
    
}
