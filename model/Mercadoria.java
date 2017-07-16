
package model;

public class Mercadoria {
	
    private String lote;
    private String endereco;
    private String bloco;
    private String numero;
    private String fornecedor;
    private String data;
    private String hora;
    
    public Mercadoria(String l, String e, String b, String n, String f, String d, String h){
    	this.lote = l;
    	this.setEndereco(e);
    	this.bloco = b;
    	this.numero = n;
    	this.fornecedor = f;
    	this.data = d;
    	this.hora = h;
    }
    
    public String pegarLinha(){
    	return "" + this.lote + ";" + this.endereco + ";" + this.bloco + ";" + this.numero + ";" + this.fornecedor + ";" + this.data + ";" + this.getHora();
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
