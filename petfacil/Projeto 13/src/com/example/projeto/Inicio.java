package com.example.projeto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

public class Inicio extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.inicial_activity);

	}
	
	public void verProx(View v)
	{
		
		Intent verprox = new Intent(this, MainActivity.class);
		startActivity(verprox);
	}

	public void irBusca(View v)
	{
		Intent irbusca = new Intent(this, BuscaProd.class);
		startActivity(irbusca);
	}


}
