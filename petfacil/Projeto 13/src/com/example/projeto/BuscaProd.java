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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscaProd extends Activity {
	private List<Loja> listaDeLoja;
	private EditText busca;
	private TextView nomeLoja;
	private Listas listas;
	private Intent levaNome;
	private String nome;
	private String[] listadeNomes;
	private String[] arraynomes;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.busca_prod_activity);
	    listas = new Listas();
		listas.initListaDeLojas();
		listaDeLoja = listas.getListaLojas();
	    busca = (EditText) findViewById(R.id.etBusca);
	    nomeLoja = (TextView) findViewById(R.id.tvNomeLoja);
	    }
	
	public void buscaProduto(View v)
	{
		listadeNomes = new String[5];
		int i = 0;
		List<Produto> listP;
		List<Servico> listS;
		for (Loja loja : listaDeLoja) {
			listP = loja.getListaProdutos();
			listS = loja.getListaServico();
			for (Produto produto : listP) {
				if (produto.getNome().equalsIgnoreCase(busca.getText().toString())) {
					nome = loja.getNome();
					listadeNomes[i]=nome;
					i++;
				}
			}
			
			for (Servico servico : listS) {
				if (servico.getNome().equalsIgnoreCase(busca.getText().toString())) {
					nome = loja.getNome();
					listadeNomes[i]=nome;
					i++;
				}
			}
		}
		
		arraynomes = new String[i];
		
		for (int j = 0; j < i; j++) {
			if (listadeNomes[j] != null) {
				arraynomes[j] = listadeNomes[j];
			}
		}
		
		levaNome = new Intent(this, ListadeProdServ.class);
		levaNome.putExtra("nomes", arraynomes);
		startActivity(levaNome);
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
}
