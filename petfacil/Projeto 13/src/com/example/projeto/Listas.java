package com.example.projeto;

import java.util.ArrayList;
import java.util.List;

public class Listas {
	private Loja loja;
	private Loja loja1;
	private Loja loja2;
	private Produto produto;
	private Servico servico;
	private List<Produto> listaProdutos;
	private List<Servico> listaServicos;	
	private List<Loja> listaLojas;
	
	  public Listas() {
		listaProdutos = new ArrayList<Produto>();
		listaLojas = new ArrayList<Loja>();
	}
	  
	  public void setListaLojas(List<Loja> listaLojas) {
		this.listaLojas = listaLojas;
	}
	  
	  public List<Loja> getListaLojas() {
		return listaLojas;
	}
	  
	  public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	  
	  public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	  public void addLoja(Loja loja)
	  {
		  listaLojas.add(loja);
	  }
	  
	  public void addProduto(Produto produto)
	  {
		  listaProdutos.add(produto);
	  }

	  public void initListaDeLojas()
	  {
		  	//-23.5773773
			//-46.6436925
		  	loja = new Loja("Vila Mariana", "São Paulo", "São Paulo", "Petfacil", 666, "Rua Estela", "999999999");
		  	loja.setLatitude(-23.5793271);
		  	loja.setLongitude(-46.6385901);
		    produto = new Produto("Ração", 0001, "Dogshaw", 15.00);
		    loja.addProduto(produto);
			produto = new Produto("Coleira", 0002, "bach", 15.00);
			loja.addProduto(produto);
			servico = new Servico("Banho",0001, "Banho", 30.00);
			loja.addServico(servico);
			servico = new Servico("Tosa", 0002, "Tosa", 15.00);
			loja.addServico(servico);
		    addLoja(loja);
			loja1 = new Loja("Vila Mariana", "São Paulo", "São Paulo", "PetOutro", 666, "Rua Estela", "999999999");
			loja1.setLatitude(-23.5770678);
			loja1.setLongitude(-46.6410075);
			produto = new Produto("Ração", 0001, "pedigri", 15.00);
			loja1.addProduto(produto);
			produto = new Produto("Coleira", 0002, "coleira", 15.00);
			loja1.addProduto(produto);
			servico = new Servico("Banho",0001, "Banho", 31.00);
			loja1.addServico(servico);
			servico = new Servico("Tosa", 0002, "Tosa", 16.00);
			loja1.addServico(servico);
			addLoja(loja1);
			loja2 = new Loja("Vila Mariana", "São Paulo", "São Paulo", "PetAquele", 666, "Rua Estela", "999999999");
			loja2.setLatitude(-23.5777234);
			loja2.setLongitude(-46.6429015);
			produto = new Produto("Ração", 0001, "premier", 15.00);
			loja2.addProduto(produto);
			produto = new Produto("Coleira", 0002, "ostenta", 15.00);
			loja2.addProduto(produto);
			servico = new Servico("Banho",0001, "Banho", 32.00);
			loja2.addServico(servico);
			servico = new Servico("Tosa", 0002, "Tosa", 17.00);
			loja2.addServico(servico);
			addLoja(loja2);
	  }
}
