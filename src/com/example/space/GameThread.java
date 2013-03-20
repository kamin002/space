package com.example.space;

import android.os.Handler;
import android.widget.RelativeLayout;

public class GameThread extends Thread 
{
	private GameActivity activite;
	private Handler handler;
	private int vitesseDeplacementX;
	private int vitesseDeplacementY;
	final int REPOS=500;
	private boolean deplacementDroite=true;
	private RelativeLayout l;
	public String Etat;
	
	public GameThread( GameActivity activity)
	{
		this.activite=activity;
		this.l=this.activite.getLayout();
		this.vitesseDeplacementX=30;
		this.vitesseDeplacementY=60;
		this.Etat="GAME";
		this.handler= new Handler() 
		{
			public void handleMessage(android.os.Message msg) 
			{
				if(msg.what == 0 && GameThread.this.Etat=="GAME")
					GameThread.this.deplacementHorizontal();
			}
		};

	}

	@Override
	public void run() 
	{
		while(true)
		{
			handler.sendEmptyMessage(0);
			
			try 
			{
				Thread.sleep(REPOS); // Repos du thread pendant un certain temps 
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void deplacementHorizontal()
	{
		
		Alien premier = this.activite.getAliens().get(0);
		Alien dernier = this.activite.getAliens().get(this.activite.getAliens().size()-1);
		
		
		if(l.getX()+dernier.getX()+dernier.getImage().getWidth()+vitesseDeplacementX>l.getX()+l.getWidth())
		{
			this.deplacementDroite=false;
			this.deplacementVertical();
		}
			
		else if(premier.getImage().getX()-vitesseDeplacementX<0 && !(this.deplacementDroite))
		{
			this.deplacementDroite=true;
			this.deplacementVertical();
		}
		
		for(int i=0;i<this.activite.getAliens().size();i++)// Pour chaque aliens du tableau
		{		
			Alien a = this.activite.getAliens().get(i);

			
			if(this.deplacementDroite)
				a.setX(a.getX()+this.vitesseDeplacementX);
			
			else
				a.setX(a.getX()-this.vitesseDeplacementX);
		}
	}
	
	public void deplacementVertical()
	{
		for(int i=0;i<this.activite.getAliens().size();i++)// Pour chaque aliens du tableau
		{
			if(l.getY()+this.activite.getAliens().get(i).getY()+this.activite.getAliens().get(i).getImage().getHeight()+vitesseDeplacementY>l.getY()+l.getHeight())
			{
				this.activite.stop();
				this.Etat="FIN";
			}

			
			Alien a = this.activite.getAliens().get(i);
			a.setY(a.getY()+this.vitesseDeplacementY);
		}

	}
	

}