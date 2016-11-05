package assignment5;
/*
 * Do not change this file.
 */
import assignment5.Critter.TestCritter;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }

	@Override
	public Shape getShape() {
		return new Circle();
	}

	@Override
	public String getColor() {
		return "green";
	}

	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
}
