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

package assignment5; // cannot be in default package
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.internal.ws.message.stream.PayloadStreamReaderMessage;

import java.io.*;




/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main extends Application {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console
	Stage window;
	Scene scene;
	static private File[] listOfFiles;
	static private ArrayList<String> fileName = new ArrayList<>();
	static public GridPane worldGrid;
	static public Stage board;
	static private ArrayList<String> currentCrits = new ArrayList<>();
	ChoiceBox<String> statsBox;
	static public ByteArrayOutputStream baos;
	static public PrintStream ps;

	public Button seed;
	public Button button;
	public Button step;
	public Button show;
	public Button quit;
	public Button animate;
	public TextField number;
	public TextField seedset;
	public ChoiceBox<String> choiceBox;
	TextField stepCount;
	Slider slide;


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
		File folder = new File(System.getProperty("user.dir") + "/src/assignment5");
		//System.out.println(System.getProperty("user.dir"));

		// Create a stream to hold the output
		//baos = new ByteArrayOutputStream();
		//ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		//PrintStream old = System.out;
		// Tell Java to use your special stream
		//System.setOut(ps);
		// Print some output: goes to your special stream
		//System.out.println("Foofoofoo!");
		// Put things back
		//System.out.flush();
		//System.setOut(old);
		// Show what happened
		//System.out.println("Here: " + baos.toString());


		listOfFiles = folder.listFiles();
		for(File f: listOfFiles){
			if(f.getName().equals("Params.java") || f.getName().equals("Main.java") || f.getName().equals("InvalidCritterException.java") || f.getName().equals("Header.java") || f.getName().equals("Critter.java")) {
				continue;
			}
			String name = f.getName();
			ArrayList<Character> cBuilder = new ArrayList<>();
			for(int i = 0; i < name.length(); i ++){
				if(name.charAt(i) == '.'){
					break;
				}
				cBuilder.add(name.charAt(i));
			}
			char[] c = new char[cBuilder.size()];
			for(int i = 0; i < cBuilder.size(); i ++){
				c[i] = cBuilder.get(i);
			}
			String temp = new String(c);
			fileName.add(temp);
			//System.out.println(temp);
		}


		launch(args);

		/*
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        //System.out.println("GLHF");
        /*
        boolean playing = true;
        
        String input;
        String[] inputSplit;
        while(playing){
        	System.out.print("critters>");
        	input = kb.nextLine();
        	inputSplit = input.split("\\s+");
        	//System.out.println(Arrays.toString(inputSplit));
        	if(inputSplit.length == 1 && inputSplit[0].equals("quit")){
        		System.out.print("Thanks for playing!");
        		playing = false;
        	}
        	else if(inputSplit.length == 1 && inputSplit[0].equals("show")){
                Critter.displayWorld();
        	}
        	else if(inputSplit.length >= 1 && inputSplit.length < 3 && inputSplit[0].equals("step")){
        		int n = 1;
        		if(inputSplit.length == 2){
        			try{
        				n = Integer.parseInt((inputSplit[1]));
        			}
        			catch(NumberFormatException e){
                		System.out.println("error processing: " + input);
                		continue;
        			}
        		}
        		for(int i = 0; i < n; i++){
        			Critter.worldTimeStep();
        		}
        	}
        	
        	else if(inputSplit.length >= 2 && inputSplit.length <= 3 && inputSplit[0].equals("make")){
        		int n = 1;
        		if(inputSplit.length == 3){
        			try{
        				n = Integer.parseInt(inputSplit[2]);
        			}
        			catch(NumberFormatException e){
                		System.out.println("error processing: " + input);
                		continue;
        			}
        		}
        		for(int i = 0; i < n; i++){
        			try{
        				Critter.makeCritter(inputSplit[1]);
        			}
        			catch(InvalidCritterException e){
        				System.out.println("error processing: " + input);
        				break;
        			}
        		}
        		
        	}
        	
        	else if(inputSplit.length == 2 && inputSplit[0].equals("seed")){
        		long n = 0;
        		try{
        			n = Long.parseLong(inputSplit[1]);
        		}
        		catch(NumberFormatException e){
            		System.out.println("error processing: " + input);
            		continue;
        		}
        		Critter.setSeed(n);
        	}
        	
            else if(inputSplit.length == 2 && inputSplit[0].equals("stats")){
            	List<Critter> l;
            	try{
            		l = Critter.getInstances(myPackage + "." + inputSplit[1]);

            	}
            	catch(InvalidCritterException e){
            		System.out.println("error processing: " + input);
            		continue;
            	}
            	try{
					Critter temp = (Critter) Class.forName(myPackage + "." + inputSplit[1]).newInstance();
					Class<?> tempClass = temp.getClass();
					Method m = tempClass.getMethod("runStats", List.class);
					m.invoke(tempClass, l);
				} catch (IllegalAccessException e1) {
					Critter.runStats(l);
				} catch (InstantiationException e2) {
					Critter.runStats(l);
				} catch (ClassNotFoundException e3) {
					Critter.runStats(l);
				} catch (NoSuchMethodException e4) {
					Critter.runStats(l);
				} catch (InvocationTargetException e5) {
					Critter.runStats(l);
				}

			}

        	
        	else{
        		System.out.println("error processing: " + input);
        	}
        	
            System.out.flush();

        }
        
        
        /* Write your code above */
        //System.out.flush();

    }

    public void start(Stage primaryStage) throws Exception {

		//com.apple.eawt.Application.getApplication().setDockIconImage(Toolkit.getDefaultToolkit().getImage("file:critterlogo.png"));

		window = primaryStage;
		window.setTitle("Configuration Panel");
		window.setX(20);
		window.setY(200);
		//window.getIcons().add(new Image(getClass().getResourceAsStream("file:critterlogo.png")));

		number = new TextField();
		stepCount = new TextField();

		seed = new Button("Set");
		button = new Button("Make");
		step = new Button("Step");
		show = new Button("Show");
		quit = new Button("Quit");

		
		animate = new Button("Animate");

		Label seeder = new Label("Set seed value:");
		seedset = new TextField();

		seed.setMaxWidth(60);
		button.setMaxWidth(60);
		step.setMaxWidth(60);
		show.setMaxWidth(60);
		quit.setMaxWidth(60);

		choiceBox = new ChoiceBox<>();
		quit.setStyle("-fx-background-color:RED");

		ChoiceBox<String> choiceBox = new ChoiceBox<>();

		for(String s: fileName){
			choiceBox.getItems().add(s);
		}

		choiceBox.setValue("Craig");

		seed.setOnAction(e -> setSeed());
		button.setOnAction(e -> {
			try {
				getChoice(choiceBox, number);
			} catch (InvalidCritterException e1) {
				e1.printStackTrace();
			}
		});
		step.setOnAction(e -> stepChoice(stepCount));
		show.setOnAction(e -> showChoice());
		quit.setOnAction(e -> System.exit(0));
		window.setOnCloseRequest(e -> System.exit(0));
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(15);
		grid.setPadding(new Insets(2, 2, 2, 2));
		grid.setStyle("-fx-background-color:GRAY");

		Text welcome = new Text("Welcome to Critters!");
		welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label make = new Label("Make critter: ");

		number.setMaxWidth(40);

		seedset.setMaxWidth(40);

		stepCount.setMaxWidth(40);


		grid.add(welcome, 0, 0, 3, 1);
		grid.add(seeder, 0, 1, 2, 1);
		grid.add(seedset, 2, 1, 1, 1);
		grid.add(seed, 3, 1, 1, 1);
		grid.add(make, 0, 2, 1, 1);
		grid.add(choiceBox, 1, 2, 1, 1);
		grid.add(number, 2, 2, 1, 1);
		grid.add(button, 3, 2, 1, 1);
		grid.add(stepCount, 2, 4, 1, 1);
		grid.add(step, 3, 4, 1, 1);
		grid.add(show, 3, 6, 1, 1);
		grid.add(quit, 3, 8, 1, 1);
		grid.add(animate, 0, 8, 1, 1);

		
		grid.setGridLinesVisible(false);

		slide = new Slider();
		slide.setMin(1);
		slide.setMax(10);
		slide.setShowTickLabels(true);
		slide.setShowTickMarks(true);
		slide.setMajorTickUnit(1);
		slide.setMinorTickCount(0);
		slide.setBlockIncrement(1);
		slide.setSnapToTicks(true);
		grid.add(slide, 1, 8);

		animate.setOnAction(e -> animateChoice(slide));


		scene = new Scene(grid, 400 , 400);
		window.setScene(scene);
		window.setAlwaysOnTop(true);
		window.setMinWidth(400);
		window.setMinHeight(300);
		window.show();

		// ------------------------------------------------- world screen
		worldGrid = new GridPane();
		worldGrid.setAlignment(Pos.CENTER);
		//worldGrid.getColumnConstraints().add(new ColumnConstraints(Params.world_width));
		//worldGrid.getColumnConstraints().add(new ColumnConstraints(Params.world_height));

		board = new Stage();
		board.setTitle("World");

		//worldGrid.setGridLinesVisible(true);
		Scene secondScene = new Scene(worldGrid, Params.world_width*23 + 23, Params.world_height*23 + 23);
		//Painter.paint();
		board.setScene(secondScene);
		board.setOnCloseRequest(e -> System.exit(0));
		board.setMinHeight(Params.world_width*20 + 75);
		board.setMinWidth(Params.world_height*20 + 55);
		board.setAlwaysOnTop(true);
		board.show();

		// -------------------------------------------------- run stats screen
		Stage statsStage = new Stage();
		statsStage.setTitle("Critter Stats");

	}
    

	private void getChoice (ChoiceBox<String> choiceBox, TextField number) throws InvalidCritterException {
		try {
			Integer n = 1;
			if(!number.getText().equals("")){
				n = Integer.valueOf(number.getText());
			}
			if (n < 0){
				throw new Exception();
			}
			for (int i = 0; i < n; i++) {
				//statsBox.getItems().add(choiceBox.getValue());
				Critter.makeCritter(choiceBox.getValue());
			}
			Critter.displayWorld();
		}catch (Exception e){
			//System.out.println("Please enter a valid integer");
			AlertBox.display("Invalid Input", "Please enter a valid integer!");
		}

	}

	private void stepChoice(TextField stepCount){
		try{
			Integer n = 1;
			if(!stepCount.getText().equals("")){
				n = Integer.valueOf(stepCount.getText());
			}
			if(n<0){
				throw new Exception();
			}
			for(int i = 1; i <= n; i++){
				Critter.worldTimeStep();
			}
			Critter.displayWorld();
		}catch(Exception e){
			//System.out.println("Please enter a valid integer");
			AlertBox.display("Invalid Input", "Please enter a valid integer!");
		}

	}
	
	private void animateChoice(Slider slider){
		System.out.println((int) slider.getValue());

		String buttonText = animate.getText();
		
		if(buttonText.equals("Animate")){
			animate.setText("Cancel");
			button.setDisable(true);
			step.setDisable(true);
			show.setDisable(true);
			quit.setDisable(true);
			number.setDisable(true);
			stepCount.setDisable(true);
			choiceBox.setDisable(true);
			slider.setDisable(true);

		}
		else{
			animate.setText("Animate");
			button.setDisable(false);
			step.setDisable(false);
			show.setDisable(false);
			quit.setDisable(false);
			number.setDisable(false);
			stepCount.setDisable(false);
			choiceBox.setDisable(false);
			slider.setDisable(false);

		}
		
	}

	private void showChoice(){
		Critter.displayWorld();
	}

	private void setSeed(){
		try{
			long n = 0;
			if(!seedset.getText().equals("")){
				n = Long.valueOf(seedset.getText());
			}
			Critter.setSeed(n);
		}catch(Exception e){
			//System.out.println("Please enter a valid integer");
			AlertBox.display("Invalid Input", "Please enter a valid integer!");
		}
	}

	public void displayStats(){

	}

}
