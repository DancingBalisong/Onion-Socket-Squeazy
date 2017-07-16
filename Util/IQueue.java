/***************************
Autor: Rafael das quebrada e Igor Garcia
Componente Curricular: 
Concluído em: 16/07/2017
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estamos cientes que estes trechos não serão considerados para fins de avaliação.
******************************/
package Util;

public interface IQueue {

    public boolean estaVazia();

    public int obterTamanho();

    public void inserirFinal(Object data);

    public Object removerInicio();

    public Object recuperarInicio();   
}
