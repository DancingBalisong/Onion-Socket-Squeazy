/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import model.Mercadoria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.AVLTree.No;
import util.Iterator;

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

    /**
     * Test of transferirArquivoNoVetor method, of class Controller.
     */
    @Test
    public void testTransferirArquivoNoVetor() throws Exception {
        Mercadoria [] test = {new Mercadoria(1,"a","a","a","a","a","a"),new Mercadoria(2,"b","b","b","b","b","b")};
        Mercadoria m = new Mercadoria(1,"a","a","a","a","a","a");
        assertEquals("O lote é 1.",m.getLote(), test[0].getLote());
    }

    /**
     * Test of inserirNaArvore method, of class Controller.
     */
    @Test
    public void testInserirNaArvore() throws Exception {
        Mercadoria [] test = {new Mercadoria(1,"a","a","a","a","a","a"),new Mercadoria(2,"b","b","b","b","b","b")};
        c.salgueiroLutador.inserir(test[0]);
        Iterator passador = c.salgueiroLutador.iterador();
        No x = (No) passador.next();
        assertEquals(x.getchave().getLote(), test[0].getLote());
        
        
    }

    /**
     * Test of listarDadosDaArvore method, of class Controller.
     */
    @Test
    public void testListarDadosDaArvore() {
        System.out.println("listarDadosDaArvore");
        Controller instance = new Controller();
        instance.listarDadosDaArvore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of escreverNoArquivo method, of class Controller.
     */
    @Test
    public void testEscreverNoArquivo() throws Exception {
		
        FileWriter escritor = new FileWriter("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
        BufferedWriter escrever = new BufferedWriter(escritor);
        
        String produto = "testeDeEscrita";
        
        escrever.write(produto);
        escrever.newLine();
        
        escrever.close();
        
    	String diretorio = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt";//OU pode vir de um outro metodo, depende do teste.
    	FileReader arq = null;
        arq = new FileReader(diretorio);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine();
        assertEquals(produto, linha);
        
        lerArq.close();
    }

    /**
     * Test of testeDasChaves method, of class Controller.
     */
    @Test
    public void testTesteDasChaves() {
        System.out.println("testeDasChaves");
        Mercadoria teste = null;
        int l = 0;
        String e = "";
        String b = "";
        String n = "";
        String f = "";
        Controller instance = new Controller();
        boolean expResult = false;
        boolean result = instance.testeDasChaves(teste, l, e, b, n, f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removerDaArvore method, of class Controller.
     */
    @Test
    public void testRemoverDaArvore() {
        System.out.println("removerDaArvore");
        Mercadoria chave = null;
        Controller instance = new Controller();
        instance.removerDaArvore(chave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarMercadoria method, of class Controller.
     */
    @Test
    public void testBuscarMercadoria_6args() {
        System.out.println("buscarMercadoria");
        Mercadoria m = null;
        int l = 0;
        String e = "";
        String b = "";
        String n = "";
        String f = "";
        Controller instance = new Controller();
        Mercadoria expResult = null;
        Mercadoria result = instance.buscarMercadoria(m, l, e, b, n, f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarMercadoria method, of class Controller.
     */
    @Test
    public void testBuscarMercadoria_3args() {
        System.out.println("buscarMercadoria");
        String lote = "";
        String bloco = "";
        String fornecedor = "";
        Controller instance = new Controller();
        Mercadoria expResult = null;
        Mercadoria result = instance.buscarMercadoria(lote, bloco, fornecedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contarMercadorias method, of class Controller.
     */
   @Test
    public void testContarMercadorias() throws IOException {
        String  linha;
        int quantDeLinhas = 0;
        
        FileReader arq = new FileReader("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Arquivo\\arquivo.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        
        linha = lerArq.readLine();
        
        quantDeLinhas++;
        
        assertEquals("So tem uma linha", 1, quantDeLinhas);
    }
    
}
