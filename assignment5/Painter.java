package assignment5;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Painter {
    public static Shape getShape(Critter critter){
    	Shape returnVal;
    	if(critter.toString().equals("A")){
    		returnVal = new Rectangle(20, 20);
    	}
    	else{
    		returnVal = new Rectangle(20, 20);
    	}
    	return returnVal;
    }
    
    public static void paint(){
    	Main.worldGrid.getChildren().clear();
		for(int y = 0; y < Params.world_width; y++){
			for(int x = 0; x < Params.world_height; x++){
				/*Button tmp = new Button(Integer.toString(x) + ", " + Integer.toString(y));
				tmp.setMaxWidth(20); tmp.setMaxWidth(20);
				tmp.setOnAction(e -> System.out.println(tmp.getText()));
				*/
				
				Shape tmp = new Rectangle(20, 20);
				tmp.setFill(Color.BLACK);				
				Main.worldGrid.add(tmp, x, y);
			}
		}

    }

}
