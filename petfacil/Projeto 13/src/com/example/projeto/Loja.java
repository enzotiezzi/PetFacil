package com.example.projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Loja {
	
	private String bairro;
	private String cidade;
	private String estado;
	private String nome;
	private String descr;
	private int numero;
	private List<Produto> listaProdutos;
	private String rua;
	private List<Servico> listaServico;
	private String telefone;
	private double latitude;
	private double longitude;
	
	public Loja(String bairro, String cidade, String estado, String nome, int numero, String rua, String telefone) {
	
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.nome = nome;
		this.numero = numero;
		this.rua = rua;
		this.telefone = telefone;
		this.listaProdutos = new ArrayList<Produto>();
		this.listaServico = new ArrayList<Servico>();
		this.latitude = 0;
		this.longitude = 0;
		this.descr = getNome() + " (" + getCidade() + ")";
	}

	@Override
	public String toString() {
		return descr;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}
	
	public List<Servico> getListaServico() {
		return listaServico;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void addProduto(Produto produto)
	{
		listaProdutos.add(produto);
	}
	
	public void addServico(Servico servico)
	{
		listaServico.add(servico);
	}
	
	public Produto procurarProduto(String nome)
	{
		for (Produto prod : listaProdutos)
		{
			if(prod.getNome().equalsIgnoreCase(nome))
			{
				return prod;	
			}
		}
		return null;
	}
	
	public Servico procurarServico(String servico)
	{
		for (Servico serv : listaServico)
		{
			if(serv.getNome().equalsIgnoreCase(servico))
			{
				return serv;
			}
		}
		return null;
		
	} 

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
}
