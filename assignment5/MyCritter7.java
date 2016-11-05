package assignment5;

import assignment5.Critter.TestCritter;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;

public class MyCritter7 extends TestCritter {
	
	@Override
	public void doTimeStep() {
	}

	@Override
	public boolean fight(String opponent) {

		return true;
	}

	@Override
	public Shape getShape() {
		return new Circle();
	}

	@Override
	public String getColor() {
		return "black";
	}

	@Override
	public String toString () {
		return "7";
	}
}
