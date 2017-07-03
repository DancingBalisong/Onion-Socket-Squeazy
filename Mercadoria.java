
package model;

public class Mercadoria {

    private String bloco;
    private String lote;
    private String fornecedor;
    private String data;
    private String hora;
    
    public Mercadoria(String b, String l, String f, String d, String h){
    	this.bloco = b;
    	this.lote = l;
    	this.fornecedor = f;
    	this.data = d;
    	this.hora = h;
    }
    
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
}
