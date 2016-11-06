package assignment5;

import assignment5.Critter.CritterShape;
import assignment5.Critter.TestCritter;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;

public class MyCritter6 extends TestCritter {
	
	@Override
	public void doTimeStep() {
		walk(0);
	}

	@Override
	public boolean fight(String opponent) {
		run(4);
		return false;
	}

	@Override
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }

	@Override
	public String toString () {
		return "6";
	}
}
