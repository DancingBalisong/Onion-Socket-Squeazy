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
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Util.AVLTree.No;
import Util.Iterator;
import controller.Controller;
import model.Mercadoria;

public class avlTreeTest {
	static Controller c;
    static Controller emptyTree;
    
    @BeforeClass
    public static void setUpClass() {
        c = new Controller();
        emptyTree = new Controller();
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
    
    @Test
    public void testAlturaSemSucesso(){
    	emptyTree.salgueiroLutador.inserir(null);
    	int esperada = 0;
    	Iterator pass = emptyTree.salgueiroLutador.iterador();
    	No n = (No) pass.nextNo();//raiz
    	int resultado = emptyTree.salgueiroLutador.altura(n);
    	assertEquals("Raiz nula", esperada, resultado);
    }

    @Test
    public void testBalaceamentoComSucesso(){
    	Mercadoria[] teste = {new Mercadoria("a","a","a","a","a","a","a"), 
							  new Mercadoria("b","b","b","b","b","b","b"),
							  new Mercadoria("c","c","c","c","c","c","c")};
		for(int i = 0; i< teste.length; i++){
			c.salgueiroLutador.inserir(teste[i]);
		}
		No n;
		int[] esperada = {-1,0,1};
		int resultado = 0;
		Iterator pass = c.salgueiroLutador.iterador();
		while(pass.hasNext()){
			n = (No) pass.nextNo();
			if(n.getBalanceamento() < -1 && n.getBalanceamento() > 1){
				resultado = n.getBalanceamento();
			}
		}
		assertEquals("Balanceamento Correto", esperada[1], resultado);
    }
    @Test
    public void testAlturaComSucesso(){
    	int esperada = 1;//usando a árvore do controller c.
    	Iterator pass = c.salgueiroLutador.iterador();
    	No n = (No) pass.nextNo();//raiz
    	int resultado = c.salgueiroLutador.altura(n);
    	assertEquals("Raiz nula", esperada, resultado);
    }
  }  
    
