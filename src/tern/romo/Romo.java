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
import android.os.Handler;
import android.os.Message;
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
	
	
	int leftVal;
	int rightVal;
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
 

   public int doMove(int [] args) {
	   execute();
	   return 0;
	   
   }
   
   public int doStartMotor(int [] args) {
	   int motor = args[0];
	   int power = args[1];
	   
	   if (motor == 1) {
		   this.rightVal = power;
	   }
	   else {
		   this.leftVal = power;
	   }
	   //execute();
	   return 0;
	   
   }
   
   public int doStopMotor(int [] args) {
	   this.leftVal = this.rightVal = 0x80;
	   execute();
	   return 0;
   }
   
   public boolean timer() {
	   	while (check) {
				long elapsed = (System.currentTimeMillis() - last_tick);
				if (elapsed > TIMEOUT) {
					check = false;}
			}
	   		
	   	return !check;
	}
   
   
	   
   public int doEnd(int [] args) {
	   this.img = "smile";
	   return 0;
   }
   
   
   public void execute() {
	      repaintHandler.sendEmptyMessage(0);
	   }
   
   private Handler repaintHandler = new Handler() {
	      @Override public void handleMessage(Message msg) {
	         action();
	      }
	      
   };
   
   public void action() {
		mCommandInterface.playMotorCommand(this.leftVal, this.rightVal);
	}
   
   
   public int sendBeep(int [] args){
	   this.leftVal = this.rightVal = 0xFF;
	   execute();
	   
	   last_tick = System.currentTimeMillis();
	   if (timer()) {
	   		check = true;
	   		this.leftVal = this.rightVal = 0x00;
	   		execute();
	   		
	   		last_tick = System.currentTimeMillis();
	   		if (timer()) {
		   		check = true;
		   		this.leftVal = this.rightVal = 0x80;
		   		execute();}
	   } 
	   //action();
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