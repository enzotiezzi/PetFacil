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

public class VerServ extends Activity {
	private Listas lista;
	private String nome;
	private Intent pegaInfo;
	private List<Loja> listadeLojas;
	private List<Servico> listaServicos;
	private List<String> nomeServicos;
	private ArrayAdapter<String> adapter;
	private ListView listaDeServicos;
	private Intent verServ;
	private String nomeLoja;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ver_serv_activity);
	    listaDeServicos = (ListView) findViewById(R.id.lvServicos);
	    nomeServicos = new ArrayList<String>();
	    lista = new Listas();
	    lista.initListaDeLojas();
	    pegaInfo = getIntent();
	    nome = pegaInfo.getStringExtra("nome");
	    
	    Loja loja = getLoja(nome, 0);
	    listaServicos = loja.getListaServico();
		verServ = new Intent(this, VerDetalheServico.class);
	    nomeLoja = loja.getNome();
		
	    for (Servico s : listaServicos) {
	    	nomeServicos.add(s.getNome());
		}
	    
	    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomeServicos);
	    listaDeServicos.setAdapter(adapter);
		
	    listaDeServicos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Servico servico =  listaServicos.get(position);
				String nome = servico.getNome();
				String categoria = servico.getCategoria();
				String preco = String.valueOf(servico.getValor());
				verServ.putExtra("nome", nome);
				verServ.putExtra("categoria", categoria);
				verServ.putExtra("preco", preco);
				verServ.putExtra("nomeLoja", nomeLoja);
				startActivity(verServ);
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
		listadeLojas = lista.getListaLojas();
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
