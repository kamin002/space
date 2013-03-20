package com.example.space;

import java.util.ArrayList;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameActivity extends Activity implements SensorEventListener
{
	private RelativeLayout layoutprincipal; // Layout Principal
	private GameThread thread; 		// Thread du jeu
	private ArrayList<Alien> aliens = new ArrayList<Alien>(); // Tableau contenant les Aliens
	final int NOMBRE_ALIENS=5;
	final int INTERVALLE_ENTRE_ALIENS=70;
	private ImageView vaisseau;
	private	float xGyroscope;
	private SensorManager sensorManager;
	private Sensor gyroscope;
	private ImageView missile;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		layoutprincipal = (RelativeLayout)findViewById(R.id.layout);
		layoutprincipal.setBackgroundColor(Color.BLACK);

	}

	@Override
	public void onStart(){
		super.onStart();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Garde l'activité en mode portrait

		//Initialisation du thread et des aliens
		this.InitAliens(NOMBRE_ALIENS,false,3);
		this.thread= new GameThread(this);
		this.thread.start();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		// Création du Vaisseau
		vaisseau = new ImageView(this);
		vaisseau.setImageResource(R.drawable.vaisseau);
		vaisseau.setX(this.layoutprincipal.getWidth()/2);//placement en X du vaisseau
		vaisseau.setY(this.layoutprincipal.getHeight()-vaisseau.getHeight()-100);//placement en Y du vaisseau
		layoutprincipal.addView(vaisseau);
		
		//On active les evenements sur le capteur
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI);		
	}

	@Override
	protected void onPause() {
		//On active les evenements sur le capteur
		sensorManager.unregisterListener(this, gyroscope);
		// and don't forget to pause the thread that redraw the xyAccelerationView
		super.onPause();
	}

	public ImageView getVaisseau() {
		return vaisseau;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public ArrayList<Alien> getAliens() {
		return aliens;
	}

	public RelativeLayout getLayout() {
		return layoutprincipal;
	}

	public void setLayout(RelativeLayout layout) {
		this.layoutprincipal = layout;
	}

	public void stop()
	{
		(Toast.makeText(this, "Looser", Toast.LENGTH_SHORT)).show();
	}

	//Initialisation du tableau contenant les aliens
	public void InitAliens(int nbaliens, boolean tir, int vie )
	{
		for(int i=0; i<nbaliens;i++)
		{
			String nomAlien="Alien"+i;
			//Creation à la volée des Images
			ImageView iv = new ImageView(this);
			iv.setImageResource(R.drawable.alien1);
			iv.setX(iv.getX()+INTERVALLE_ENTRE_ALIENS*i);//placement des aliens
			layoutprincipal.addView(iv);//Ajout des aliens au layout principal
			this.aliens.add(new Alien(nomAlien,tir,vie,iv.getX(),iv.getY(),iv));
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{}

	@Override
	// action quand le capteur bouge
	public void onSensorChanged(SensorEvent se) 
	{ 
		GameActivity.this.xGyroscope = se.values[0];

		if(GameActivity.this.xGyroscope < -1)
			if((vaisseau.getX()+60) < (layoutprincipal.getWidth()) )
				vaisseau.setX(vaisseau.getX()+20);

		if(GameActivity.this.xGyroscope > 1)
			if((vaisseau.getX()-10) > 0 )
				vaisseau.setX(vaisseau.getX()-20);	
	}
	
	public boolean onTouchEvent(MotionEvent ev) 
	{			
		if(ev.getAction() == MotionEvent.ACTION_DOWN)
		{
			missile = new ImageView(this);
			missile.setX(this.getVaisseau().getX()+this.getVaisseau().getWidth()/2);
			missile.setY(this.getVaisseau().getY()-this.getVaisseau().getHeight());
			missile.setImageResource(R.drawable.missile2);
			this.layoutprincipal.addView(missile);
			/*Tir t =new Tir(missile.getX(),missile.getY(),missile);
			this.runOnUiThread(t.action);*/
		}
		
		return true;

	}
}