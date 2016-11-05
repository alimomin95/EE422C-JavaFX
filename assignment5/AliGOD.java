/* CRITTERS AliGOD.java
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

/**
 * Created by aliziyaan on 10/18/16.
 */
public class AliGOD extends Critter{
    @Override
    public String toString() {
        return "A";
    }

    public boolean fight(String enemy){
        if(enemy.equals("Algae")){
            return false;
        }
        return true;
    }

    @Override
    public javafx.scene.shape.Shape getShape() {
        return new Circle();
    }

    @Override
    public String getColor() {
        return "black";
    }

    @Override
    public void doTimeStep(){
        walk(0);
        if(getEnergy() > 50){
            AliGOD jesus = new AliGOD();
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
