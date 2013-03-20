package com.example.space;

import android.widget.ImageView;

public class Tir 
{
	private float x;
	private float y;
	private ImageView image;
	public Runnable action= new Runnable() 
	{   
        @Override
        public void run() 
        {
        	Tir.this.setX(10);
        }
   
	}; 
	
	
	public Tir(float x,float y, ImageView image )
	{
		this.x=x;
		this.y=y;
		this.image=image;
		
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
		this.getImage().setX(this.getImage().getX()+100);
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
		this.getImage().setY(this.getImage().getY()+10);
	}


	public ImageView getImage() {
		return image;
	}


	public void setImage(ImageView image) {
		this.image = image;
	}	
}
