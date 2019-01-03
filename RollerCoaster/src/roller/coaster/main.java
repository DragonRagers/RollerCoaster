package roller.coaster;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.*;


public class main extends Application{
	private Canvas myCanvas = new Canvas(500, 500);
	private Pane myPane = new Pane();

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Weight of the Cart: ");
		//int weight = keyboard.nextInt();
		
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("RollerCoasterGoGoGo");
		myPane.setMinWidth(450);
		myPane.setMinHeight(450);
		ArrayList<Rectangle> part1 = new ArrayList<Rectangle>();
		int part1count = 0;
		Text incline = new Text();
		incline.setText("INCLINE");
		incline.setX(60);
		incline.setY(60);
		
		incline.setOnMouseClicked(e -> {
			Rectangle rect = new Rectangle(e.getSceneX(), e.getSceneY(), 20, 20);
			rect.setFill(Color.RED);
			rect.setStroke(Color.BLUE);
			part1.add(rect);
			rect.setOnMouseDragged(j -> {
				rect.setX(j.getSceneX());
				rect.setY(j.getSceneY());
			});
			myPane.getChildren().add(rect);
		});
		
		for (int i = 0; i < part1.size(); i++) {
			Rectangle thisPart = part1.get(i);
			thisPart.setOnMouseDragged(e -> {
				thisPart.setX(e.getSceneX());
				thisPart.setY(e.getSceneY());
			});
		}
		myPane.getChildren().add(incline);
		
		primaryStage.setScene(new Scene(myPane));
		primaryStage.show();
	}
}
