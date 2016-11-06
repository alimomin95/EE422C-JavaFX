/* CRITTERS Critter.java
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

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;
//import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.scene.shape.Shape;



/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean fightMode = false;

	//Keeps track of how many critters exist on each given space
	private static int[][] positionMap = new int[Params.world_width][Params.world_height];
	private static List<Critter> fightClub = new java.util.ArrayList<Critter>();

	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public CritterShape viewShape() { return CritterShape.SQUARE;} 
	
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}


	
	private boolean hasMoved = false;
	
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	// this is new!!!!!!!
	protected String look(int direction, boolean steps) {

		this.energy -= Params.look_energy_cost;

		int dx = 0, dy = 0;

		if(direction == 2){
			dx = 0; dy = -1;
		}
		else if(direction == 1){
			dx = 1; dy = -1;
		}
		else if(direction == 0){
			dx = 1; dy = 0;
		}
		else if(direction == 7){
			dx = 1; dy = 1;
		}
		else if(direction == 6){
			dx = 0; dy = 1;
		}
		else if(direction == 5){
			dx = -1; dy = 1;
		}
		else if(direction == 4){
			dx = -1; dy = 0;
		}
		else if(direction == 3){
			dx = -1; dy = -1;
		}

		int x = this.x_coord + dx;
		int y = this.y_coord + dy;

		if(steps){
			x += dx;
			y += dy;

			if(positionMap[x][y] >= 1) {
				for(Critter c: population){
					if(onSquare(c, x, y)){
						return c.toString();
					}
				}
			}
		}
		else{
			if(positionMap[x][y] >= 1) {
				for(Critter c: population){
					if(onSquare(c, x, y)){
						return c.toString();
					}
				}
			}
		}
		return "";
	}

	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	
	private static void placeBaby(Critter baby, int direction){
		int dx = 0, dy = 0;
		
		if(direction == 2){
			dx = 0; dy = -1;
		}
		else if(direction == 1){
			dx = 1; dy = -1;
		}
		else if(direction == 0){
			dx = 1; dy = 0;
		}
		else if(direction == 7){
			dx = 1; dy = 1;
		}
		else if(direction == 6){
			dx = 0; dy = 1;
		}
		else if(direction == 5){
			dx = -1; dy = 1;
		}
		else if(direction == 4){
			dx = -1; dy = 0;
		}
		else if(direction == 3){
			dx = -1; dy = -1;
		}


		
		if((baby.x_coord + dx) < 0){
			baby.x_coord = dx + Params.world_width;
		}
		else{
			baby.x_coord = (baby.x_coord + dx) % Params.world_width;
		}

		if((baby.y_coord + dy) < 0){
			baby.y_coord = dy + Params.world_height;
		}
		else{
			baby.y_coord = (baby.y_coord + dy) % Params.world_height;
		}

		
	}
	
	protected final void walk(int direction) {

		if(direction == 2){
			moveCritter(0,-1);
		}
		else if(direction == 1){
			moveCritter(1,-1);
		}
		else if(direction == 0){
			moveCritter(1,0);
		}
		else if(direction == 7){
			moveCritter(1,1);
		}
		else if(direction == 6){
			moveCritter(0, 1);
		}
		else if(direction == 5){
			moveCritter(-1,1);
		}
		else if(direction == 4){
			moveCritter(-1,0);
		}
		else if(direction == 3){
			moveCritter(-1,-1);
		}

		energy -= Params.walk_energy_cost;

	}
	
	protected final void run(int direction) {
		if(direction == 2){
			moveCritter(0,-2);
		}
		else if(direction == 1){
			moveCritter(2,-2);
		}
		else if(direction == 0){
			moveCritter(2,0);
		}
		else if(direction == 7){
			moveCritter(2,2);
		}
		else if(direction == 6){
			moveCritter(0, 2);
		}
		else if(direction == 5){
			moveCritter(-2,2);
		}
		else if(direction == 4){
			moveCritter(-2,0);
		}
		else if(direction == 3){
			moveCritter(-2,-2);
		}

		energy -= Params.run_energy_cost;
	}

	
	//should be static I think
	private final void moveCritter(int dx, int dy){
		if(this.hasMoved) {
			return;
		}

		this.hasMoved = true;

		if(fightMode && !checkSquareIsEmpty(dx, dy)){
			return;
		}

	    positionMap [this.x_coord][this.y_coord] -= 1;

		if((this.x_coord + dx) < 0){
			this.x_coord = dx + Params.world_width;
		}
		else{
			this.x_coord = (this.x_coord + dx) % Params.world_width;
		}

		if((this.y_coord + dy) < 0){
			this.y_coord = dy + Params.world_height;
		}
		else{
			this.y_coord = (this.y_coord + dy) % Params.world_height;
		}

		positionMap [this.x_coord][this.y_coord] += 1;
	}

	/*
	 * x_coord and y_coord are shifts in current x and y
	 * this method returns if the (x + x_coord, y + y_coord) are taken
	 */
	private final boolean checkSquareIsEmpty(int x_coord, int y_coord){
		int x_temp;
		int y_temp;

		if(x_coord < 0){
			x_temp = x_coord + Params.world_width;
		}
		else{
			x_temp = (this.x_coord + x_coord) % Params.world_width;
		}

		if(y_coord < 0){
			y_temp = y_coord + Params.world_height;
		}
		else{
			y_temp = (this.y_coord + y_coord) % Params.world_height;
		}

		if(positionMap[x_temp][y_temp] > 0){
			return false;
		}

		return true;
	}
	
	private final static int[] getRandomCoord(){
		int rx = getRandomInt(Params.world_width);
		int ry = getRandomInt(Params.world_height);
		int[] arr = {rx, ry};
		return arr;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy >= Params.min_reproduce_energy){
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.y_coord;
		
			placeBaby(offspring, direction);
		
			offspring.energy = this.energy/2;
			this.energy -= (this.energy+1)/2;
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	/*	1. Do move
	 *  2. Do make child
	 *  3. encounters
	 *  4. remove dead critters
	 * 
	 */
	
	
	
	public abstract boolean fight(String oponent);



	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try{
			Critter temp = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
			//set variables
			int[] xyPos = getRandomCoord();
			temp.x_coord = xyPos[0];
			temp.y_coord = xyPos[1];
			temp.energy = Params.start_energy;
			population.add(temp);
			
			positionMap[temp.x_coord][temp.y_coord] += 1;
		}
		catch(ClassNotFoundException e1){
			throw new InvalidCritterException(critter_class_name);
		} catch (InstantiationException e2) {
			throw new InvalidCritterException(critter_class_name);
		} catch (IllegalAccessException e3) {
			throw new InvalidCritterException(critter_class_name);
		}
		
	}
	
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		Critter temp;
		try{
			temp = (Critter) Class.forName(critter_class_name).newInstance();	
		}
		catch(ClassNotFoundException e1){
			throw new InvalidCritterException(critter_class_name);
		} catch (InstantiationException e2) {
			throw new InvalidCritterException(critter_class_name);
		} catch (IllegalAccessException e3) {
			throw new InvalidCritterException(critter_class_name);
		}

		for(Critter c : population){
			if(temp.getClass().isInstance(c)){
				result.add(c);
			}
		}
		return result;
	}
	

	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			positionMap[super.x_coord][super.y_coord] -= 1;
			super.x_coord = new_x_coord;
			positionMap[super.x_coord][super.y_coord] += 1;

		}
		
		protected void setY_coord(int new_y_coord) {
			positionMap[super.x_coord][super.y_coord] -= 1;

			super.y_coord = new_y_coord;
			positionMap[super.x_coord][super.y_coord] += 1;

		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		positionMap = new int[Params.world_width][Params.world_height];
		babies.clear();
	}
	
	private static boolean onSquare(Critter c, int x, int y){
		return (c.x_coord == x) && (c.y_coord == y);
	}

	
	public static void worldTimeStep() {
		//calls doTimeStep for every critter in collection
		Iterator<Critter> it = population.iterator();
		while(it.hasNext()){
			Critter c = it.next();
			c.hasMoved = false;
			c.energy -= Params.rest_energy_cost; //critter loses rest energy
			c.doTimeStep();

			if(c.energy <= 0){
				positionMap[c.x_coord][c.y_coord] -= 1;
				it.remove();
			}
		}

		/*
		for (Critter c : population) {
			c.hasMoved = false;
			c.energy -= Params.rest_energy_cost; //critter loses rest energy
			c.doTimeStep(); // 
			
			//remove dead creatures
			if(c.energy <= 0){
				killCritter(c);		
			}
		}	*/

			
		//check for encounters
		for(int j = 0; j < Params.world_height; j++){
			for(int i = 0; i < Params.world_width; i++){
				if(positionMap[i][j] > 1){
					for(Critter c : population){
						if(onSquare(c, i, j)){
							fightClub.add(c);
						}
					}
					//encounters with fightClub
					encounter();
				}
			}
		}
		
		//died during an encounter
		it = population.iterator();
		while(it.hasNext()){
			Critter c = it.next();
			if(c.energy <= 0){
				positionMap[c.x_coord][c.y_coord] -= 1;
				it.remove();
			}
		}

		
		//add babies to population
		for(Critter b : babies){
			population.add(b);
			positionMap[b.x_coord][b.y_coord] += 1;
			//babies.remove(b);
		}
		babies.clear();

		for(int i = 0; i < Params.refresh_algae_count; i++){
			try{
				makeCritter("Algae");
			}
			catch(InvalidCritterException e){
			}
		}

		
	}

	private static void encounter() {
		//iterate over fightClub
		//
		Critter A;
		Critter B;
		boolean boolA;
		boolean boolB;
		boolean stillFighting;
		
		while(fightClub.size() > 1){

			stillFighting = true;
			A = fightClub.get(0);
			fightClub.remove(0);

			B = fightClub.get(0);
			fightClub.remove(0);

			//System.out.println("Critter A: " + A.toString() + "Critter B: " + B.toString());
			boolA = A.fight(B.toString());
			boolB = B.fight(A.toString());

			A.fightMode = boolA;
			B.fightMode = boolB;

			//System.out.println("fight at: " + A.x_coord + ", " + A.y_coord);

			if(A.energy <= 0){
				//System.out.println("Critter A died to exhaustion");
				stillFighting = false;
			}
			if(B.energy <= 0){
				//System.out.println("Critter B died exhaustion");

				stillFighting = false;
			}

			if(stillFighting && A.x_coord == B.x_coord && A.y_coord == B.y_coord){
				int rollA = (boolA?getRandomInt(A.getEnergy()):0);
				//int rollA = getRandomInt((boolA?A.getEnergy():0));

				int rollB = (boolB?getRandomInt(B.getEnergy()):0);

				//System.out.println("A rolled: " + rollA + " B rolled: " + rollB);

				if(rollA >= rollB){
					//System.out.println("Critter B died");
					A.energy += B.energy/2;
					B.energy = 0;
					fightClub.add(A);

				}
				else{
					//System.out.println("Critter A died");
					B.energy += A.energy/2;
					A.energy = 0;
					fightClub.add(B);
				}
			}
		}
		fightClub.clear();
	}
	
	public static Shape getIcon(Critter c){
		Shape s;
		
		int size = 20;
		switch (c.viewShape()) {
		case SQUARE: s = new Rectangle(size, size); break;
		case CIRCLE: s = new Circle(size/2); break;
		case TRIANGLE: s = new Rectangle(size, size); break;
		case DIAMOND: s = new Rectangle(size, size); break;
		case STAR: s = new Rectangle(size, size); break;
		default: s = new Rectangle(size, size);
		}
		s.setFill(c.viewColor());
		s.setStroke(javafx.scene.paint.Color.BLUE);
		return s;
	}
		
	public static void displayWorld() {
		//GridPane newWorldGrid = new GridPane();
		//newWorldGrid.setHgap(5);
		//newWorldGrid.setHgap(5);

		Main.worldGrid.getChildren().clear();
		for(Critter c: population){
			int x = c.x_coord;
			int y = c.y_coord;
			//Shape shape = c.(c);
			Shape shape = getIcon(c);
			//Main.worldGrid.add(shape,x,y);
			Main.worldGrid.add(shape,  x,  y);
		}
		//Scene secondScene = new Scene(Main.worldGrid, Params.world_width*30, Params.world_height*30);
		//Scene newScreen = new Scene(Main.worldGrid, Params.world_width*30, Params.world_height*30);
		//Main.board.setScene(newScreen);
		
		Main.board.show();




		/*
		System.out.print("+");
		
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.println("+");
		System.out.flush();
		for(int j = 0; j < Params.world_height; j++){
			System.out.print("|");
			System.out.flush();

			for(int i = 0; i < Params.world_width; i++){
				if(positionMap[i][j] >= 1){
					for(Critter c : population){
						if(onSquare(c, i, j)){
							System.out.print(c);
							break;
						}
					}
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
		
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-");
		}
		System.out.println("+");
	*/
	}

}
