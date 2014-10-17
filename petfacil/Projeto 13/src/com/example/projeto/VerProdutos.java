package com.example.projeto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class VerProdutos extends Activity {
	
	private Listas lista;
	private Intent pegaInfo;
	private ListView listaDeProdutos;
	private List<Produto> listaProdutos;
	private List<String> nomeProduto;
	private ArrayAdapter<String> adapter;
	private Intent verProd;
	private String nomeLoja;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ver_prod_activity);
	    
	    pegaInfo = getIntent();
	    String nome = pegaInfo.getStringExtra("nome");
	    listaDeProdutos = (ListView) findViewById(R.id.lvProduto);
	    lista = new Listas();
	    lista.initListaDeLojas();
	    nomeProduto = new ArrayList<String>();
	    Loja loja = getLoja(nome, 0);
	    listaProdutos = loja.getListaProdutos();
	    nomeLoja = loja.getNome();
	    for (Produto p : listaProdutos) {
			nomeProduto.add(p.getNome());
		}
	    verProd = new Intent(this, VerDetalheProduto.class);
	    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomeProduto);
	    listaDeProdutos.setAdapter(adapter);
	    
	    listaDeProdutos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Produto produto =  listaProdutos.get(position);
				String nome = produto.getNome();
				String codigo = String.valueOf(produto.getCodigo());
				String valor = String.valueOf(produto.getValor());
				String categoria = produto.getCategoria();
				verProd.putExtra("nome", nome);
				verProd.putExtra("codigo", codigo);
				verProd.putExtra("valor", valor);
				verProd.putExtra("categoria", categoria);
				verProd.putExtra("nomeLoja", nomeLoja);
				startActivity(verProd);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		
		case R.id.back:
			this.finish();
			break;
		}
		return true;
	}
	
	private void aboutMenuItem()
	{
		new AlertDialog.Builder(this)
		.setTitle("Sobre")
		.setMessage("Estudantes do colégio bandeirantes")
		.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).show();
	}
	
	private void settingMenuItem()
	{
		new AlertDialog.Builder(this)
		.setTitle("Settings")
		.setMessage("Configurações")
		.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).show();
	}
	
	private Loja getLoja(String nome, int tipo)
	{
		List<Loja> listadeLojas = lista.getListaLojas();
		if(tipo == 0)
		{
			for (Loja l : listadeLojas) {
				if (l.getNome().equals(nome)) {
					return l;
				}
			}
		}
		else {
			for (Loja l : listadeLojas) {
				String nomeL = l.getNome()+"("+l.getCidade()+")";
				if (nomeL.equals(nome)) {
					return l;
				}
			}
		}
		return null;
	}

}
