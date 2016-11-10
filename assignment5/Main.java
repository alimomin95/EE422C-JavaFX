/* CRITTERS Main.java
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
	static private File[] listOfFiles;
	static private ArrayList<String> fileName = new ArrayList<>();
	static public GridPane worldGrid;
	static public Stage board;

	ChoiceBox<String> statsBox;
	static public ByteArrayOutputStream baos;
	static public PrintStream ps;

	public Button seed;
	public Button button;
	public Button step;
	public Button show;
	public Button quit;
	public Button animate;
	public Button reset;
	public TextField number;
	public TextField seedset;
	public ChoiceBox<String> choiceBox;
	TextField stepCount;
	Slider slide;
	public Button statsBtn;
	public ChoiceBox<String> statsDrop;
	public Stage statsStage;
	public GridPane statsPane;
	public Text statsText;
	
	//testing!!!!
	public double mouseStartX;
	public double mouseStartY;



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


		listOfFiles = folder.listFiles();
		for(File f : listOfFiles){
			try{
				String name = f.getName();

				name = name.split("\\.")[0];
				Critter temp = (Critter) Class.forName(myPackage + "." + name).newInstance();
				fileName.add(name);
			}
			catch(Exception e){
				continue;
			}
			

		}


		launch(args);
    }

    public void start(Stage primaryStage) throws Exception {


		window = primaryStage;
		window.setTitle("Configuration Panel");
		window.setX(40);
		window.setY(200);

		number = new TextField();
		stepCount = new TextField();

		seed = new Button("Set");
		button = new Button("Make");
		step = new Button("Step");
		show = new Button("Show");
		quit = new Button("Quit");
		statsBtn = new Button("Stats");
		reset = new Button("Reset");
		
		animate = new Button("Animate");

		Label seeder = new Label("Set seed value:");
		seeder.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		seedset = new TextField();

		seed.setMaxWidth(60);
		button.setMaxWidth(60);
		step.setMaxWidth(60);
		show.setMaxWidth(60);
		quit.setMaxWidth(60);
		statsBtn.setMaxWidth(60);
		reset.setMaxWidth(60);
		choiceBox = new ChoiceBox<>();
		quit.setStyle("-fx-background-color:RED");

		ChoiceBox<String> choiceBox = new ChoiceBox<>();

		statsDrop = new ChoiceBox<>();

		for(String s: fileName){
			statsDrop.getItems().add(s);
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
		reset.setOnAction(e -> resetButton());
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(15);
		grid.setPadding(new Insets(2, 2, 2, 2));
		grid.setStyle("-fx-background-color:#CECECE");

		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));


		Text welcome = new Text("Welcome to Critters!");
		welcome.setEffect(ds);
		welcome.setFill(Color.BLUE);
		welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		Label make = new Label("Make:");
		make.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		number.setMaxWidth(40);

		seedset.setMaxWidth(40);

		stepCount.setMaxWidth(40);


		grid.add(welcome, 0, 0, 3, 1);
		grid.add(reset, 3, 0, 1, 1);
		grid.add(seeder, 0, 1, 2, 1);
		grid.add(seedset, 2, 1, 1, 1);
		grid.add(seed, 3, 1, 1, 1);
		grid.add(make, 0, 2, 1, 1);
		grid.add(choiceBox, 1, 2, 1, 1);
		grid.add(number, 2, 2, 1, 1);
		grid.add(button, 3, 2, 1, 1);
		grid.add(stepCount, 2, 4, 1, 1);
		grid.add(step, 3, 4, 1, 1);
		grid.add(statsDrop, 2, 6, 1, 1);
		grid.add(statsBtn, 3, 6, 1, 1);
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
		window.setMinWidth(450);
		window.setMinHeight(300);
		window.show();

		// ------------------------------------------------- world screen
		int width = 25*Params.world_width + 18;
		int height = 25*Params.world_height + 41;
		
		ScrollPane scroll = new ScrollPane();
		scroll.setPrefSize(500, 500);
		scroll.setPannable(true);
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollBarPolicy.NEVER);

		scroll.setStyle("-fx-background-color:#6B4949");
		
		worldGrid = new GridPane();
		worldGrid.setAlignment(Pos.CENTER);
		worldGrid.setStyle("-fx-background-color:#6B4949");
		
		board = new Stage();
		board.setTitle("World");

		worldGrid.setGridLinesVisible(true);
		scroll.setContent(worldGrid);

		Scene secondScene = new Scene(scroll, width, height);
		board.setScene(secondScene);

		board.setOnCloseRequest(e -> System.exit(0));
		board.setWidth(500);
		board.setHeight(500);

		board.setMinWidth(200);
		board.setMinHeight(200);
		
		board.setMaxWidth(width);
		board.setMaxHeight(height);


		board.setAlwaysOnTop(true);
		
		Critter.displayWorld();
		board.show();

		// -------------------------------------------------- run stats screen
		statsStage = new Stage();
		statsStage.setTitle("Critter Stats");
		statsPane = new GridPane();
		statsDrop.setValue("Craig");
		statsText = new Text();
		statsBtn.setOnAction(e -> displayStats());
		Scene statsScene = new Scene(statsPane, 800, 200);
		statsStage.setScene(statsScene);
		statsStage.setOnCloseRequest(e -> System.exit(0));
		statsStage.setX(200);
		statsStage.setY(700);
		statsStage.setAlwaysOnTop(true);
		statsStage.show();
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
				Critter.makeCritter(choiceBox.getValue());
			}
			Critter.displayWorld();
		}catch (Exception e){
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
				displayStats();
			}
			Critter.displayWorld();
		}catch(Exception e){
			e.printStackTrace();
			AlertBox.display("Invalid Input", "Please enter a valid integer!");
		}

	}
	
	private void animateChoice(Slider slider){

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
			seed.setDisable(true);
			seedset.setDisable(true);
			reset.setDisable(true);
			statsBtn.setDisable(true);
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
			seed.setDisable(false);
			seedset.setDisable(false);
			reset.setDisable(false);
			statsBtn.setDisable(false);
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
				AlertBox.display("Seed set", "The seed has been set to: " + n);
			}
			Critter.setSeed(n);
		}catch(Exception e){
			//System.out.println("Please enter a valid integer");
			AlertBox.display("Invalid Input", "Please enter a valid integer!");
		}
	}

	public void displayStats(){
		statsPane.getChildren().clear();
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		List<Critter> l = null;
		try{
			l = Critter.getInstances(myPackage + "." + statsDrop.getValue());

		}
		catch(InvalidCritterException e){
		}
		try{
			Critter temp = (Critter) Class.forName(myPackage + "." + statsDrop.getValue()).newInstance();
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
		// Put things back
		System.out.flush();
		System.setOut(old);
		//System.out.println(baos);
		statsText.setText(baos.toString());
		statsPane.add(statsText, 0, 0);
		statsStage.show();

	}
	
	public void resetButton(){
		Critter.clearWorld();
		Critter.displayWorld();
		AlertBox.display("World Cleared", "The world has been cleared");
		
	}
}
