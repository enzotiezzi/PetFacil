package com.example.projeto;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class VerDetalheServico extends Activity {

	private Intent pegaInfo;
	private TextView nomeServ;
	private TextView categoriaServ;
	private TextView precoServ;
	private TextView tvLojaView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ver_detalhe_serv_activity);
	    nomeServ = (TextView) findViewById(R.id.tvNomeServ);
	    categoriaServ = (TextView) findViewById(R.id.tvCategoriaServ);
	    precoServ = (TextView) findViewById(R.id.tvPrecoServ);
	    tvLojaView = (TextView)findViewById(R.id.tvNomeLojaViewServ);
	    
	    pegaInfo = getIntent();
	    String nome = pegaInfo.getStringExtra("nome");
	    String categoria = pegaInfo.getStringExtra("categoria");
	    String valor = pegaInfo.getStringExtra("preco");
	    String nomeLoja = pegaInfo.getStringExtra("nomeLoja");
	    tvLojaView.setText(nomeLoja);
	    nomeServ.setText("Nome: "+nome);
	    categoriaServ.setText("Categoria: "+categoria);
	    precoServ.setText("Preço: R$"+valor);
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
