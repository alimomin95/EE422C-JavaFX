package assignment5;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.List;

import assignment5.Critter.CritterShape;

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
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.BLUE; }

	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
