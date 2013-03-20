package com.example.space;

import android.widget.ImageView;

public class Alien
{
	private String nom; //texte qui donne nom de l'alien
	private boolean shooter; // booléen qui définit si l'alien peut tirer ou non
	private int nbShoot;		 // Nombre de tir necessaire pour l'abbatre
	private float X;			//coordonnées en X
	private float Y;			//coordonnées en Y)
	private ImageView image;//référence de l'image alien
	
	public Alien (String n, boolean b, int nb, float x, float y, ImageView i)
	{
		this.nom=n;
		this.shooter=b;
		this.nbShoot=nb;
		this.X=x;
		this.Y=y;
		this.image=i;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isShooter() {
		return shooter;
	}

	public void setShooter(boolean shooter) {
		this.shooter = shooter;
	}

	public int getNbShoot() {
		return nbShoot;
	}

	public void setNbShoot(int nbShoot) {
		this.nbShoot = nbShoot;
	}

	public float getX() {
		return X;
	}

	public void setX(float x) {
		X = x;
		this.image.setX(x);
	}

	public float getY() {
		return Y;
	}

	public void setY(float y) {
		Y = y;
		this.image.setY(y);
	}

}
