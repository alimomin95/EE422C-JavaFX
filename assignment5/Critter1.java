/* CRITTERS Critter1.java
 * EE422C Project 5 submission by
 * Quinten Zambeck
 * qaz62
 * 16470
 * Ali Ziyaan Momin
 * AZM259
 * 16470
 * Slip days used: 0
 * Fall 2016
 * GitHub URL: https://github.com/alimomin95/EE422C-JavaFX.git
 */

package assignment5;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;

import assignment5.Critter.CritterShape;

//Created by Quinn Z.
public class Critter1 extends Critter {
	int cultPower = 0;
	
	boolean move = false; //it won't move until it's cultPower hits 50
	
    @Override
    public String toString() {
        return "1";
    }

    public boolean fight(String enemy){
    	if(this.look(0, true).equals("Critter2") == false){
    		run(0);
    	}
    	if(cultPower > 100 || enemy.equals("Critter2")){ //always fights Critter2
    		if(cultPower > 100){
    			cultPower -= 50;
    		}
    		return true;
    	}
    	return false;
    }

	@Override
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLUE; }

    @Override
    public void doTimeStep() {
    	if(cultPower < 200){
        	cultPower += 25;
    	}
    	if(cultPower >= 50){
    		move = false;
    	}
    	else{
    		move = true;
    	}

    	if(move){
    		walk(getRandomInt(8));
    	}
        //This critter runs to it's death
        if(getEnergy() > 150){
            Critter1 a = new Critter1();
            reproduce(a, 7);
        }
    }

    public static void runStats (java.util.List<Critter> ag){
        int count = ag.size();
        if(count > 0) {
            System.out.println("There are " + count + " Aggies on the map.");
            System.out.println("But anyone can be an Critter1, so...");
        }
        else{
            System.out.println("There must not be enough UT applicants!");
        }
    }

}
