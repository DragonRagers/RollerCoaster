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
	private Canvas myCanvas = new Canvas(1200, 500);
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
		myPane.setMinWidth(1200);
		myPane.setMinHeight(500);
		ArrayList<Rectangle> part1 = new ArrayList<Rectangle>();
		Text incline = new Text();
		Circle startPoint = new Circle(40, 40, 8);
		Circle endPoint = new Circle(1100, 400, 8);
		incline.setText("INCLINE");
		incline.setX(440);
		incline.setY(440);
		startPoint.setFill(Color.GREEN);
		endPoint.setFill(Color.RED);
		
		incline.setOnMouseClicked(e -> {
			Rectangle rect = new Rectangle(e.getSceneX(), e.getSceneY(), 20, 20);
			Line inclinePart = new Line(incline.getX() - 25, incline.getY() + 25, incline.getX() + 25, incline.getY() -25);
			inclinePart.setStroke(Color.BROWN);
			inclinePart.setStrokeWidth(5);
			rect.setFill(Color.BLUE);
			rect.setStroke(Color.YELLOW);
			part1.add(rect);
			rect.setOnMouseDragged(j -> {
				rect.setX(j.getSceneX());
				rect.setY(j.getSceneY());
			});
			inclinePart.setOnMouseDragged(j -> {
				inclinePart.setStartX(j.getSceneX() - 25);
				inclinePart.setStartY(j.getSceneY() + 25);
				inclinePart.setEndX(j.getSceneX() + 25);
				inclinePart.setEndY(j.getSceneY() - 25);
			});
			//myPane.getChildren().add(rect);
			myPane.getChildren().add(inclinePart);
		});
		
		startPoint.setOnMouseDragged(e -> {
			startPoint.setCenterY(e.getSceneY());
		});
		
		endPoint.setOnMouseDragged(e -> {
			endPoint.setCenterX(e.getSceneX());
			endPoint.setCenterY(e.getSceneY());
		});
		
		for (int i = 0; i < part1.size(); i++) {
			Rectangle thisPart = part1.get(i);
			thisPart.setOnMouseDragged(e -> {
				thisPart.setX(e.getSceneX());
				thisPart.setY(e.getSceneY());
			});
		}
		myPane.getChildren().addAll(incline, startPoint, endPoint);
		
		primaryStage.setScene(new Scene(myPane));
		primaryStage.show();
	}
}
