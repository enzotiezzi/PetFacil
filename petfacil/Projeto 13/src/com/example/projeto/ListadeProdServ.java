package com.example.projeto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ListadeProdServ extends Activity {
	
	private ArrayAdapter<Loja> adapter;
	private ListView listaDeLojas;
	private Intent pegaInfo;
	private Intent levaInfo;
	private Listas novaLista;
	private String[] nomesLojas;
	private List<Loja> listadeLojas;
	private List<Loja> listaVazia;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private long ultimaVez;
	private ProgressBar progressBar;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lista_lojas_prodeserv);
	    
	    novaLista = new Listas();
	    novaLista.initListaDeLojas();
	    progressBar = (ProgressBar)findViewById(R.id.progressBar1);
	    
	    listaDeLojas = (ListView) findViewById(R.id.lvlojas);
	    listadeLojas = new ArrayList<Loja>();
	    listaVazia = new ArrayList<Loja>();
	    pegaInfo = getIntent();

	    levaInfo = new Intent(this, MapaActivity.class);
	    nomesLojas = pegaInfo.getStringArrayExtra("nomes");
	    String[] auxnomeslojas = nomesLojas;
	    
	    for (int i = 0; i < auxnomeslojas.length; i++) {
			Loja loja = getLoja(auxnomeslojas[i], auxnomeslojas, 0);
			listaVazia.add(loja);
		}
	    
	    ultimaVez = SystemClock.uptimeMillis() - 15000;
	    
	    adapter = new ArrayAdapter<Loja>(this, android.R.layout.simple_list_item_1, listaVazia);
		listaDeLojas.setAdapter(adapter);
		//não mexer
		nomesLojas = pegaInfo.getStringArrayExtra("nomes");
		//
		listaDeLojas.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Loja loja = listaVazia.get(position);//getLoja(nomesLojas[position], nomesLojas, 1);
				String nome = loja.getNome();
				String rua = loja.getRua();
				int numero = loja.getNumero();
				double latitude = loja.getLatitude();
				double longitude = loja.getLongitude();
				levaInfo.putExtra("Nome", nome);
				levaInfo.putExtra("Rua", rua);
				levaInfo.putExtra("Numero", String.valueOf(numero));
				levaInfo.putExtra("latitude", latitude);
				levaInfo.putExtra("longitude", longitude);
				levaInfo.putExtra("tipo", "0");
				startActivity(levaInfo);
			}
		});
		
		setConfig();
	}

	public void makeUseOfNewLocation(Location location) {
		final double lat = location.getLatitude();
		final double lng = location.getLongitude();
		
	    // deixa atualizar a lista apenas a cada 15 segundos
	    long agora = SystemClock.uptimeMillis();
	    if ((agora - ultimaVez) < 15000)
	    	return;
	    
	    ultimaVez = agora;
	    
	    //reordenar listaLojas com base em lat e lng
	    java.util.Collections.sort(listaVazia, new Comparator<Loja>() {
			@Override
			public int compare(Loja lhs, Loja rhs) {
				double a = lhs.getLatitude() - lat;
				double b = lhs.getLongitude() - lng;
				final double dl = Math.sqrt((a * a) + (b * b));
				a = rhs.getLatitude() - lat;
				b = rhs.getLongitude() - lng;
				final double dr = Math.sqrt((a * a) + (b * b));
				if (dl > dr) return 1;
				if (dl < dr) return -1;
				return 0;
			}
	    });
	    
	    adapter.notifyDataSetChanged();
		
	    progressBar.setVisibility(View.GONE);
		listaDeLojas.setVisibility(View.VISIBLE);
	  }
	
	
	
	private void setConfig()
	{
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	locationManager.removeUpdates(locationListener);
			    if (location == null) {
			    	location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			    	if (location == null)
			    		return;
			    }
		    	makeUseOfNewLocation(location);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		
		  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	   
	}
	
	private Loja getLoja(String nome, String[] lojas, int tipo)
	{
		listadeLojas = novaLista.getListaLojas();
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
