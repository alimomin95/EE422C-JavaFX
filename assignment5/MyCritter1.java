package assignment5;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.List;

public class MyCritter1 extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		walk(0);
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}

	@Override
	public Shape getShape() {
		return new Circle();
	}

	@Override
	public String getColor() {
		return "black";
	}

	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
