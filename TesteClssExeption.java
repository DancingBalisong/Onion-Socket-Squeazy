
package testeclssexeption;

import java.io.IOException;




public class TesteClssExeption extends Exception {



    public TesteClssExeption(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public TesteClssExeption(String msg){
        super(msg); 
    }
    
    
    
}
