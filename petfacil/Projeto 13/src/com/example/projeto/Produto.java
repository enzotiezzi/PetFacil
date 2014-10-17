package com.example.projeto;

public class Produto {

	private String categoria;
	private int codigo;
	private String nome;
	private double valor;
	
	public Produto(String categoria, int codigo, String nome, double valor) {
		this.categoria = categoria;
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}
	
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double  getValor() {
		return valor;
	}
	
}
