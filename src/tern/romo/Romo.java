/*
 * @(#) Roberto.java
 * 
 * Tern Tangible Programming Language
 * Copyright (c) 2011 Michael S. Horn
 * 
 *           Michael S. Horn (michael.horn@tufts.edu)
 *           Northwestern University
 *           2120 Campus Drive
 *           Evanston, IL 60613
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2) as
 * published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package tern.romo;

import tern.romo.rt.Robot;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.util.Log;

import com.romotive.library.RomoCommandInterface;


/**
 * Romo implementation of Robot
 */
public class Romo implements Robot {
	RomoCommandInterface mCommandInterface = new RomoCommandInterface();
	boolean check = true;
	int TIMEOUT = 1000;
	private long last_tick;
	
	
   public static final String TAG = "Romo";
   
   /** Reference to the View object */
   protected ProgramView view;
   
   protected String img = "smile";
      
   //protected Drawable img;
   
   
   public Romo(ProgramView view, Context context) {
      this.view = view;
   }
   
   /**
    * These functions are inherited from the Robot interface but not
    * needed for Roberto.
    */
   public boolean isConnected() { return true; }
   public void setAddress(String address) {  }
   public void openConnection() { }
   public void closeConnection() { }
   public void allStop() {
      
   }
   
    
   public void draw(Canvas canvas) {
	   
	 /**  Resources res = view.getResources();
       int id = res.getIdentifier(this.img, "drawable", "tern.romo");
       Drawable current = res.getDrawable(id);
	   current.draw(canvas);*/
      
   }
   
   
   private void changeMove(int l, int r) {   
	   Log.i(TAG, "DIRECTIONS: " + l + ", " +r );
	   mCommandInterface.playMotorCommand(l, r);
	   view.repaint();
   }
   
   
   public int doStartMotor(int [] args) {
	   int direction = args[0];
	   int left = 0x80;
	   int right = 0x80;
	   
	   switch (direction) {
	   
	   case 1:   //FORWARD
		   left = 0xFF;
		   right = 0xFF;   
		   Log.i(TAG,"DIRECTION FORWARD");
		   break;
		   
	   case 2:   //BACKWARD
		   left =  0x00;
		   right = 0x00;
		   Log.i(TAG,"DIRECTION BACKWARD");
		   break;
		   
	   case 3:   //RIGHT
		   left =  0xFF;
		   right = 0x00;
		   Log.i(TAG,"DIRECTION RIGHT");
		   break;
		   
	   case 4:   //LEFT
		   left =  0x00;
		   right = 0xFF;
		   Log.i(TAG,"DIRECTION LEFT");
		   break;
	   }
	   
	   changeMove(left, right);
	   return 0;
	   
   }
   
   public int doStopMotor(int [] args) {
	   changeMove(0x80, 0x80);
	   return 0;
   }
   
   
   public int sendBeep(int [] args){
	   	last_tick = System.currentTimeMillis();
	   	mCommandInterface.playMotorCommand(0xFF, 0xFF); //forward
	   	if (timer()) {
	   		last_tick = System.currentTimeMillis();
	   		check = true;
	   		mCommandInterface.playMotorCommand(0x00, 0x00); //backward
	   		
	   		if (timer()) {
	   			last_tick = System.currentTimeMillis();
	   			check = true;
	       		mCommandInterface.playMotorCommand(0x80, 0x80); //stop
	       		}
	   	}
	   	
	   	return 0;
   }
   
   
   public boolean timer() {
	   	while (check) {
				long elapsed = (System.currentTimeMillis() - last_tick);
				if (elapsed > TIMEOUT) {
					Log.i("ROMO","INSIDE IF");
					check = false;}
			}
			return !check;
	}
   
   
	   
   public int doEnd(int [] args) {
	   this.img = "smile";
	   changeMove(0,0);
	   return 0;
   }
   
 /**
	// mCommandInterface.playMotorCommand(0xC0, 0xFF);//leftforward
	// mCommandInterface.playMotorCommand(0xFF, 0xFF);//forward
	// mCommandInterface.playMotorCommand(0xFF, 0xC0);//rightforward
	   
	// mCommandInterface.playMotorCommand(0x00, 0xFF);//left
	// mCommandInterface.playMotorCommand(0x80, 0x80);//stop
	// mCommandInterface.playMotorCommand(0xFF, 0x00);//right
	   
	   
	// mCommandInterface.playMotorCommand(0x40, 0x00);//leftbackward
	// mCommandInterface.playMotorCommand(0x00, 0x00); //backward
	// mCommandInterface.playMotorCommand(0x00, 0x40); //rightbackward
//*/

}