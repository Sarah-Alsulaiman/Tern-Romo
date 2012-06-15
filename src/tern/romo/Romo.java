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
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.util.Log;
import android.content.*;
import com.romotive.library.*;


/**
 * Romo implementation of Robot
 */
public class Romo implements Robot {
   
   public static final String TAG = "Romo";
   
   /** Reference to the View object */
   protected ProgramView view;
      
   protected RomoCommandInterface mCommandInterface;
   
   private int leftVal = 0x80;
   private int rightVal = 0x80;
   
   
   
   public Romo(ProgramView view, Context context) {
      this.view = view;
      mCommandInterface = new RomoCommandInterface();
      // Initialize your RomoCommandInterface
      AudioManager manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
      manager.setStreamVolume(AudioManager.STREAM_MUSIC, manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
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
	   //mCommandInterface.playMotorCommand(this.leftVal, this.rightVal);//f
      
   }

   
   private void changeMove(int l, int r) {
	   this.leftVal = l;
	   this.rightVal = r;
	   
	   //view.repaint();
   }
   
   
   public int doForward(int [] args) {
	   int left = 0xFF;
	   int right = 0xFF;
	   
	  // mCommandInterface.playMotorCommand(left, right);//f
	   //mCommandInterface.playMotorCommand(left, right);//f
	   //mCommandInterface.playMotorCommand(left, right);//f
	   
	   Log.i(TAG, "forward executed");
	   view.repaint();
	   
	  // changeMove(left, right);
	// mCommandInterface.playMotorCommand(0xC0, 0xFF);//leftforward -- rightforward
	// mCommandInterface.playMotorCommand(0xFF, 0xFF);//forward -- right
	// mCommandInterface.playMotorCommand(0xFF, 0xC0);//rightforward -- right
	   
	// mCommandInterface.playMotorCommand(0x00, 0xFF);//left -- leftbackward
	// mCommandInterface.playMotorCommand(0x80, 0x80);//stop -- stop
	// mCommandInterface.playMotorCommand(0xFF, 0x00);//right -- leftforward --right
	   
	   
	// mCommandInterface.playMotorCommand(0x40, 0x00);//leftbackward -- leftforward
	// mCommandInterface.playMotorCommand(0x00, 0x00); //backward -- rightbackward
	// mCommandInterface.playMotorCommand(0x00, 0x40); //rightbackward - rightbackward

	   
      return 0;
   }
   
   
   /**public int doBackward(int [] args) {
	   mCommandInterface.playMotorCommand(0x00, 0x00);
	   mCommandInterface.playMotorCommand(0x80, 0x80);
      return 0;
   }//*/
   
   
   public int doSing(int [] args) {
	   int left= 0x80;
	   int right = 0x80;
	   //changeMove(left, right);
	  //mCommandInterface.playMotorCommand(left, right);//f
	   
	   Log.i(TAG, "sing executed");
	   view.repaint();
	   return 0;
	   }

}