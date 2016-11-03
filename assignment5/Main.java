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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
	Button button;
	static private File[] listOfFiles;
	static private ArrayList<String> fileName = new ArrayList<>();


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
		listOfFiles = folder.listFiles();
		for(File f: listOfFiles){
			fileName.add(f.getName());
			System.out.println(f.getName());
		}

		launch(args);

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
        System.out.flush();

    }

    public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("This is a test!");
		button = new Button("Touch Me!");

		ChoiceBox<String> choiceBox = new ChoiceBox<>();

		for(String s: fileName){
			choiceBox.getItems().add(s);
		}

		choiceBox.setValue("Craig.java");

		button.setOnAction(e -> getChoice(choiceBox));

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(choiceBox, button);

		scene = new Scene(layout, 300 , 250);
		window.setScene(scene);
		window.show();


	}

	private void getChoice (ChoiceBox<String> choiceBox) {
		System.out.println(choiceBox.getValue());
	}
}
