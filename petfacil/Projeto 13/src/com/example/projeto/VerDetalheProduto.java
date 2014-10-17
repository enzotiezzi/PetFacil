package com.example.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class VerDetalheProduto extends Activity {

	private Intent pegaInfo;
	private TextView nomeProd;
	private TextView categoriaProd;
	private TextView precoProd;
	private TextView tvLojaView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ver_detalhe_prod);
	    nomeProd = (TextView) findViewById(R.id.tvNomeProd);
	    categoriaProd = (TextView) findViewById(R.id.tvCategoriaProd);
	    precoProd = (TextView) findViewById(R.id.tvPrecoProd);
	    tvLojaView = (TextView)findViewById(R.id.tvNomeLojaView);
	    pegaInfo = getIntent();
	    String nome = pegaInfo.getStringExtra("nome");
	    String codigo = pegaInfo.getStringExtra("codigo");
	    String categoria = pegaInfo.getStringExtra("categoria");
	    String valor = pegaInfo.getStringExtra("valor");
	    String nomeLoja = pegaInfo.getStringExtra("nomeLoja");
	    tvLojaView.setText(nomeLoja);
	    nomeProd.setText("Nome: "+nome);
	    categoriaProd.setText("Categoria: "+categoria);
	    precoProd.setText("Preço: R$"+valor);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}

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
