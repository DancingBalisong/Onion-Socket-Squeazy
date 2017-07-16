package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Mercadoria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Util.Iterator;

import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ControllerTest {
    
    static Controller c;
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        c = new Controller();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of leituraDeArquivo method, of class Controller.
     */

    @Test
    public void testTransferirArquivoNoVetor() throws Exception {
        Mercadoria [] test = {new Mercadoria("a","a","a","a","a","a","a"), new Mercadoria("b","b","b","b","b","b","b")};
        Mercadoria m = new Mercadoria("a","a","a","a","a","a","a");
        assertEquals("O lote é 1.",m.getLote(), test[0].getLote());
    }

    @Test
    public void testInserirNaArvore() throws Exception {
        Mercadoria [] test = {new Mercadoria("a","a","a","a","a","a","a"), new Mercadoria("b","b","b","b","b","b","b")};
        c.salgueiroLutador.inserir(test[0]);
        Iterator passador = c.salgueiroLutador.iterador();
        Mercadoria x = (Mercadoria) passador.next();
        assertEquals(x.getLote(), test[0].getLote());
        //comparação dos atributos.
    }

    @Test
    public void testListarDadosDaArvoreComSucesso() {
    	int quantidade = 0;
    	int qtdLista = 0;
    	Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
							  new Mercadoria("b","b","b","b","b","b","b"),
							  new Mercadoria("c","c","c","c","c","c","c")};
		for(int i = 0; i< teste.length; i++){
			c.salgueiroLutador.inserir(teste[i]);
			quantidade++;
		}
		Iterator p = c.salgueiroLutador.iterador();
		while(p.hasNext()){
        	Mercadoria n = (Mercadoria) p.next();
        	qtdLista++;
        	System.out.println(qtdLista + ":" + n.pegarLinha());
	    }
		assertEquals("Lista Completa.",quantidade, qtdLista);
    }

    public void testListarDadosDaArvoreSemSucesso() {
    	int quantidade = 0;
    	int qtdLista = 0;
    	Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
							  new Mercadoria("b","b","b","b","b","b","b"),
							  new Mercadoria("b","c","c","b","c","a","a"),
							  new Mercadoria("c","c","c","c","c","c","c")};
		for(int i = 0; i< teste.length; i++){
			c.salgueiroLutador.inserir(teste[i]);
			quantidade++;
		}
		Iterator p = c.salgueiroLutador.iterador();
		while(p.hasNext()){
        	Mercadoria n = (Mercadoria) p.next();
        	qtdLista++;
        	System.out.println(qtdLista + ":" + n.pegarLinha());
	    }
		assertEquals("Lista Completa.",quantidade, qtdLista);
    }
    @Test
    public void testRemoverDaArvoreComSucesso() {
    	Mercadoria [] test = {new Mercadoria("a","a","a","a","a","a","a"),
    						  new Mercadoria("b","b","b","b","b","b","b")};
    	
        c.salgueiroLutador.inserir(test[0]);
        c.salgueiroLutador.inserir(test[1]);
        Mercadoria procurada = new Mercadoria("b","b","b","b","b","b","b");
        c.salgueiroLutador.remover(procurada);
        Mercadoria resultado = c.salgueiroLutador.buscar(procurada);
        assertTrue(resultado == null);
    }
    @Test
    public void testRemoverDaArvoreSemSucesso() {
    	int cont = 0;
    	@SuppressWarnings("unused")
		Mercadoria n;
    	Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
							  new Mercadoria("b","b","b","b","b","b","b"),
				              new Mercadoria("c","c","c","c","c","c","c")};
    	for(int i = 0; i< teste.length; i++)
			c.salgueiroLutador.inserir(teste[i]);
    	
    	Iterator p = c.salgueiroLutador.iterador();
        Mercadoria procurada = new Mercadoria("c","b","l","o","i","r","o");
        c.salgueiroLutador.remover(procurada);
        while(p.hasNext()){
        	n = (Mercadoria) p.next();
        	cont++;
        }
        int esperado = cont - teste.length;
        //a quantidade esperada é zero, pois todos os itens estariam lá.
        assertTrue(esperado == 0);
        //se não removeu, todos itens estão lá.
    }
    @Test
    public void testBuscarMercadoriaSemSucesso() {
    	Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
    						  new Mercadoria("b","b","b","b","b","b","b"),
				              new Mercadoria("c","c","c","c","c","c","c")};
		for(int i = 0; i< teste.length; i++)
			c.salgueiroLutador.inserir(teste[i]);
		Mercadoria procurada = new Mercadoria("v","b","b","b","b","b","b");
		Mercadoria result = c.buscarMercadoria(procurada);
		assertTrue(result == null);
		//null é o valor se não encontrar nada.
    }
    @Test
    public void testBuscarMercadoriaComSucesso() {
        Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
        					  new Mercadoria("b","b","b","b","b","b","b"),
        					  new Mercadoria("c","c","c","c","c","c","c")};
        for(int i = 0; i< teste.length; i++){
        	c.salgueiroLutador.inserir(teste[i]);
        }
        
        Mercadoria expResult = new Mercadoria("b","b","b","b","b","b","b");
        Mercadoria result = c.buscarMercadoria(expResult);
        assertEquals(expResult.pegarLinha(), result.pegarLinha());
    }
   @Test
    public void testContarMercadoriasComSucessoDeUmaLinha() throws IOException {
	   	//pode haver erro por causa do diretorio.
        String  linha = "";
        int quantDeLinhas = 0;
        FileReader arq = new FileReader("arquivo.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        linha = lerArq.readLine();
        if(linha != "")
        	quantDeLinhas++;
        lerArq.close();
        assertEquals("So tem uma linha", 1, quantDeLinhas);
   }
   
   @Test
   public void testContarMercadoriasComSucessoComMaisDeUmaLinha() throws IOException {
	   	//pode haver erro por causa do diretorio.
       String  linha = "";
       int quantDeLinhas = 0;
       FileReader arq = new FileReader("arquivoTesteComMaisDeUmaLinha.txt");
       BufferedReader lerArq = new BufferedReader(arq);
       while(linha != null){
    	   linha = lerArq.readLine();
    	   if(linha != null)
    		  quantDeLinhas++;
    	   	
       }
       int esperado = 7;//há 7 linhas no arquivo
       lerArq.close();
       assertEquals("So tem uma linha", esperado, quantDeLinhas);
  }
   @Test
    public void testContarMercadoriaSemSucesso() throws IOException{
	   //pode haver erro por causa do diretorio.
	   String  linha = "";
	   int c = 0;
       FileReader arq = new FileReader("arquivo.txt");
       BufferedReader lerArq = new BufferedReader(arq);
       linha = lerArq.readLine();
       while (linha != null){
           c++;
           linha = lerArq.readLine(); 
       }
       int esperada = 3;
       lerArq.close();
       assertFalse(esperada == c);
   }
    
    /**
     * Test of escreverNoArquivo method, of class Controller.
     * @throws IOException 
     */
    @Test
    public void testEscreverNoArquivoComSucesso() throws IOException  {
    	//pode haver erro por causa do diretorio.
        FileWriter escritor = new FileWriter("arquivo.txt");
        BufferedWriter escrever = new BufferedWriter(escritor);
        String produto = "testeDeEscrita";
        
        escrever.write(produto);
        escrever.newLine();
        escrever.close();
        
    	String diretorio = "arquivo.txt";//OU pode vir de um outro metodo, depende do teste.
    	FileReader arq = null;
        arq = new FileReader(diretorio);
        BufferedReader lerArq = new BufferedReader(arq);
        
        String linha = lerArq.readLine();
        lerArq.close();
        
        assertEquals(produto, linha);
    }
    
    @Test
    public void testEscreverNoArquivoSemSucesso() throws IOException  {
    	//pode haver erro por causa do diretorio.
        FileWriter escritor = new FileWriter("arquivo.txt");
        BufferedWriter escrever = new BufferedWriter(escritor);
        String esperada = "testeDeEscrita";//queria escrever isso.
        
        String produto = "";//mas escreveu isso.
        escrever.write(produto);
        escrever.newLine();
        escrever.close();
        
    	String diretorio = "arquivo.txt";//o arquivo pode vir de um outro metodo, depende do teste.
    	FileReader arq = null;
        arq = new FileReader(diretorio);
        BufferedReader lerArq = new BufferedReader(arq);
        
        String linha = lerArq.readLine();
        lerArq.close();
        
        assertFalse(esperada.compareTo(linha) == 0);
    }
}
