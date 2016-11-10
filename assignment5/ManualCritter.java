/* CRITTERS ManualCritter.java
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
import java.util.List;
import java.util.Scanner;

import assignment5.Critter.CritterShape;

public class ManualCritter extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		//System.out.println(this.getEnergy());
		System.out.println("place critter (" + this.getX_coord() + "," + this.getY_coord() + ") at: ");
		Scanner s = new Scanner(System.in);
		int input = s.nextInt();
		int input2 = s.nextInt();
		this.setX_coord(input);
		this.setY_coord(input2);

		System.out.println("Do you want to have a baby: ");
		String babyQ = s.next();
		//int babyDirection;
		if(babyQ.equals("y")){
			System.out.println("enter direction: ");
			int babyDirection = s.nextInt();
			MyCritter1 myBaby = new MyCritter1();
			reproduce(myBaby, babyDirection);
		}
		System.out.println("MyCritter 1 energy: " + this.getEnergy());
		//walk(0);
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}

	@Override
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }

	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
