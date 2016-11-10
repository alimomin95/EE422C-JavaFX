/* CRITTERS Critter3.java
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

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;

import assignment5.Critter.CritterShape;

/**
 * Created by aliziyaan on 10/20/16.
 */
public class Critter3 extends Critter {
    @Override
    public String toString() {
        return "3";
    }

    public boolean fight(String enemy){
        //This critter runs away from anything that isn't an Algae.
        if(enemy.equals("Algae")){
            return true;
        }
        run(getRandomInt(8));
        return false;
    }

	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.GREEN; }

    @Override
    public void doTimeStep() {
    	if(look(4, true).equals("")){
    		run(4);
    	}
        //This critter runs to it's death
        if(getEnergy() > 2){
            Critter3 w = new Critter3();
            reproduce(w, 6);
        }
    }

    public static void runStats (java.util.List<Critter> weak){
        int no = 0;
        for(Object g: weak){
            no ++;
        }
        if(no > 0) {
            System.out.println("There are " + no + " Weaklings on the map.");
            System.out.println("All hail our lord! May he bless us with growth and allows no harm upon us!");
        }
        else{
            System.out.println("There is no God!");
        }
    }

}
