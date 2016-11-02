package assignment5;

import java.util.List;
import java.util.Scanner;

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
	
	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
