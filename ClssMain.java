
import java.io.IOException;
import testeclssexeption.TesteClssExeption;


public class ClssMain {
 
    public static void main(String[] args){
         try{
             
             metodo();
             
         }
         
         catch(TesteClssExeption e){
             System.out.println(e.getMessage());
         }

    }


    
    public static void metodo() throws Exception {
        throw new TesteClssExeption("erro de leitura");
    }
}
