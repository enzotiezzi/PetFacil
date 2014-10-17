package com.example.projeto;

import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.R.bool;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class MapaActivity extends Activity {
	private GoogleMap map;
	private LocationManager locationManager;
	private String provider;
	private LatLng local;
	private Location location;
	private Marker lugar;
	private LocationListener locationListener;
	private TextView tvruanum;
	private TextView tvnome;
	private Intent pegainfo;
	private Listas lista;
	private List<Loja> listaDeLoja;
	private double lat;
	private double lng;
	private String nome;
	/** Called when the activity is first created. */
	@SuppressLint("NewApi") @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mapa_activity);
	    setConfig();
	    lista = new Listas();
	    lista.initListaDeLojas();
	    listaDeLoja = lista.getListaLojas();
	    pegainfo = getIntent();
	    nome = pegainfo.getStringExtra("Nome");
	    String rua = pegainfo.getStringExtra("Rua");
	    String num = pegainfo.getStringExtra("Numero");
	    String tipoo = pegainfo.getStringExtra("tipo");
	    int tipo = Integer.parseInt(tipoo);
	    tvruanum = (TextView) findViewById(R.id.tvRuanum);
	    tvnome = (TextView) findViewById(R.id.tvNome);
	    tvnome.setText(nome);
	    tvruanum.	setText(rua+"-"+num);
	    Loja loja = getLoja(nome, listaDeLoja, tipo);
	    lat = loja.getLatitude();
	    lng = loja.getLongitude();
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		local = new LatLng(lat, lng);
	   	              
		lugar = map.addMarker(new MarkerOptions().position(local)
	         .title(nome).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
	map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 15));
	map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
	    
	}
	
	private Loja getLoja(String nome,List<Loja> listadeLojas, int tipo)
	{
		
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
	
	
	
	public void makeUseOfNewLocation(Location location) {
		//-23.5773773
		//-46.6436925
	    double lat = (double) (location.getLatitude());
	    double lng = (double) (location.getLongitude());
	    local = new LatLng(lat, lng);
	    	    	 lugar = map.addMarker(new MarkerOptions().position(local)
	    	          .title("Aqui"));
	    	 
	    	 
		
	  }
	
	
	
	private void setConfig()
	{
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	locationManager.removeUpdates(locationListener);
			    provider = LocationManager.NETWORK_PROVIDER;
			    
			    location = locationManager.getLastKnownLocation(provider);
		    	makeUseOfNewLocation(location);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	   
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_mapa, menu);
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
			
		case R.id.servicos:
			goNew(2);
			break;
		case R.id.produtos:
			goNew(1);
			break;
		}
		return true;
	}
	
	public void goNew(int op)
	{
		Intent i = null;
		switch (op) {
		case 1:
			i = new Intent(this, VerProdutos.class);
			break;
		case 2:
			i = new Intent(this, VerServ.class);
			break;
		}
		i.putExtra("nome", nome);
		startActivity(i);
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

