package com.example.space;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{
	private Button changeactivite;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Garde l'aplication en format paysage
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
		changeactivite = (Button)findViewById(R.id.button1);

		//Listener du bouton de changement d'activité
		changeactivite.setOnClickListener(new View.OnClickListener() 
		{
			//Click sur le bouton
			public void onClick(View v)
			{				
				Intent t = new Intent(MainActivity.this, GameActivity.class); //envoie un Intent a l'autre classe
				startActivity(t); // demarre l'autre classe
			}
		});
	}
}