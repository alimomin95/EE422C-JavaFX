/* CRITTERS Critter4.java
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

import javafx.scene.shape.*;

import java.awt.*;

import assignment5.Critter.CritterShape;

/**
 * Created by aliziyaan on 10/18/16.
 */
public class Critter4 extends Critter{
    @Override
    public String toString() {
        return "4";
    }

    public boolean fight(String enemy){
        if(enemy.equals("Algae")){
            return false;
        }
        return true;
    }

	@Override
	public CritterShape viewShape() { return CritterShape.CIRCLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.RED; }

    @Override
    public void doTimeStep(){
        walk(0);
        if(getEnergy() > 50){
            Critter4 jesus = new Critter4();
            reproduce(jesus, 6);
        }
    }

    public static void runStats (java.util.List<Critter> GODS){
        int total_GODS = 0;
        for(Object g: GODS){
            total_GODS ++;
        }
        System.out.println("There are " + total_GODS + " GODS on the map");
    }


}
