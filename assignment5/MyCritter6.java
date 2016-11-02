package assignment5;

import assignment5.Critter.TestCritter;

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
	public String toString () {
		return "6";
	}
}
