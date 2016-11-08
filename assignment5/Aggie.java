/* CRITTERS Aggie.java
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
public class Aggie extends Critter {
	int cultPower = 0;
	
	boolean move = false; //it won't move until it's cultPower hits 50
	
    @Override
    public String toString() {
        return "A";
    }

    public boolean fight(String enemy){
    	if(cultPower > 100 || enemy.equals("Longhorn")){ //always fights Longhorn
    		if(cultPower > 100){
    			cultPower -= 50;
    		}
    		return true;
    	}
    	return false;
    }

	@Override
	public CritterShape viewShape() { return CritterShape.STAR; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }

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
            Aggie a = new Aggie();
            reproduce(a, 7);
        }
    }

    public static void runStats (java.util.List<Critter> ag){
        int count = ag.size();
        if(count > 0) {
            System.out.println("There are " + count + " Aggies on the map.");
            System.out.println("But anyone can be an Aggie, so...");
        }
        else{
            System.out.println("There must not be enough UT applicants!");
        }
    }

}
