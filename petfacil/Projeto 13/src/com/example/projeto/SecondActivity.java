package com.example.projeto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {
	
	private Intent pegaLoja;
	private String nomeLoja;
	private Intent tela;
	private Intent telaMapa;
	private Intent pegaInfo;
	private Intent busca;
	private String rua;
	private String num;
	private TextView tvNomePet;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second_activity);
	    tela = new Intent(this, MainActivity.class);
	    tvNomePet = (TextView)findViewById(R.id.tvNomePetshop);
	    
	    pegaLoja = getIntent();
	    nomeLoja = pegaLoja.getStringExtra("Nome");
	    rua = pegaLoja.getStringExtra("Rua");
	    num = pegaLoja.getStringExtra("Numero");
	    tvNomePet.setText(nomeLoja);
	    double latitude = pegaLoja.getDoubleExtra("latitude", 0);
	    double longitude = pegaLoja.getDoubleExtra("longitude", 0);
	    telaMapa = new Intent(this, MapaActivity.class);
	   
	    
	    
	    
	    busca = new Intent(this, BuscaProd.class);
	    
	}

	 	
	public void verMapa(View v)
	{
		telaMapa.putExtra("Nome", nomeLoja);
		telaMapa.putExtra("Rua", rua);
		telaMapa.putExtra("Numero", num);
		telaMapa.putExtra("tipo", "0");
		startActivity(telaMapa);
	}
	
	public void verProdutos(View v)
	{
		Intent prod = new Intent(this, VerProdutos.class);
		prod.putExtra("nome", nomeLoja);
		startActivity(prod);
	}
	
	public void verServicos(View v)
	{
		Intent serv = new Intent(this, VerServ.class);
		serv.putExtra("nome", nomeLoja);
		startActivity(serv);
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