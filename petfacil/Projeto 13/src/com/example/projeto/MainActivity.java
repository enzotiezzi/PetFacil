package com.example.projeto;

import java.text.DateFormat.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Servico servico;
	private Produto produto;
	private Loja loja;
	private ArrayAdapter<Loja> adapter;
	private TextView nome;
	private EditText texto;
	private ListView lista;
	private List<Loja> listaLojas;
	private Intent secondActivity;
	private Listas listas;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private long ultimaVez;
	private List<Loja> novaListaLojas;
	private Intent pegaInfo;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        java.lang.reflect.Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception ex) {
	        // Ignore
	    }
		
		lista = (ListView) findViewById(R.id.llvItens);		
		listaLojas = new ArrayList<Loja>();
		try {
				listas = new Listas();
				listas.initListaDeLojas();
				listaLojas = listas.getListaLojas();
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
		
		ultimaVez = SystemClock.uptimeMillis() - 15000;
		
		adapter = new ArrayAdapter<Loja>(this, android.R.layout.simple_list_item_1, listaLojas);
		lista.setAdapter(adapter);
		
		secondActivity = new Intent(this, SecondActivity.class);
		
		lista.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Loja lojaAtual = listaLojas.get(position);
				secondActivity.putExtra("Nome", lojaAtual.getNome());
				secondActivity.putExtra("Rua", lojaAtual.getRua());
				secondActivity.putExtra("Numero", String.valueOf(lojaAtual.getNumero()) );
				secondActivity.putExtra("tipo", "0");
				startActivity(secondActivity);
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
	    java.util.Collections.sort(listaLojas, new Comparator<Loja>() {
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
		lista.setVisibility(View.VISIBLE);
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
