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
import javafx.scene.image.*;


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
		ArrayList<Line> trackList = new ArrayList<Line>();
		ArrayList<String> trackStrings = new ArrayList<String>();
		Text incline = new Text();
		Text decline = new Text();
		Text flat = new Text();
		
		Circle trash = new Circle(1200, 450, 15);
		trash.setFill(Color.GRAY);
		trash.setStroke(Color.BLACK);
		
		Circle startPoint = new Circle(40, 40, 8);
		Circle endPoint = new Circle(1100, 400, 8);
		startPoint.setFill(Color.GREEN);
		endPoint.setFill(Color.RED);
		
		
		incline.setText("INCLINE");
		incline.setX(360);
		incline.setY(440);
		decline.setText("DECLINE");
		decline.setY(440);
		decline.setX(440);
		flat.setText("FLAT");
		flat.setY(440);
		flat.setX(300);
		
	
		//creates an incline piece when text clicked
		incline.setOnMouseClicked(e -> {
			Line inclinePart = new Line(incline.getX() - 25, incline.getY() + 25, incline.getX() + 25, incline.getY() -25);
			inclinePart.setStroke(Color.BROWN);
			inclinePart.setStrokeWidth(5);
			//move incline part
			inclinePart.setOnMouseDragged(j -> {
				if (!trackList.contains(inclinePart)) {
					inclinePart.setStartX(j.getSceneX() - 25);
					inclinePart.setStartY(j.getSceneY() + 25);
					inclinePart.setEndX(j.getSceneX() + 25);
					inclinePart.setEndY(j.getSceneY() - 25);
				}
			});
			//snap part to other parts
			inclinePart.setOnMouseReleased(j -> {
				double x1 = inclinePart.getStartX();
				double y1 = inclinePart.getStartY();
				if (getDistance(x1, y1, startPoint.getCenterX(), startPoint.getCenterY()) < 10) {
					inclinePart.setStartX(startPoint.getCenterX());
					inclinePart.setStartY(startPoint.getCenterY());
					inclinePart.setEndX(startPoint.getCenterX() + 50);
					inclinePart.setEndY(startPoint.getCenterY() - 50);
					trackList.add(inclinePart);
					trackStrings.add("Incline");
				}
				else {
					for (Line l : trackList) {
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							inclinePart.setStartX(l.getEndX());
							inclinePart.setStartY(l.getEndY());
							inclinePart.setEndX(l.getEndX() + 50);
							inclinePart.setEndY(l.getEndY() - 50);
							trackList.add(inclinePart);
							trackStrings.add("Incline");
							break;
						}
					}
				}
				
			});
			//put part on pane
			myPane.getChildren().add(inclinePart);
		});
		
		
		//create decline part
		decline.setOnMouseClicked(e -> {
			Line declinePart = new Line(decline.getX() - 25, decline.getY() - 25, decline.getX() + 25, decline.getY() +25);
			declinePart.setStroke(Color.BROWN);
			declinePart.setStrokeWidth(5);
			//move decline part
			declinePart.setOnMouseDragged(j -> {
				if (!trackList.contains(declinePart)) {
					declinePart.setStartX(j.getSceneX() - 25);
					declinePart.setStartY(j.getSceneY() - 25);
					declinePart.setEndX(j.getSceneX() + 25);
					declinePart.setEndY(j.getSceneY() + 25);
				}
			});
			//snap part to other parts
			declinePart.setOnMouseReleased(j -> {
				double x1 = declinePart.getStartX();
				double y1 = declinePart.getStartY();
				if (getDistance(x1, y1, startPoint.getCenterX(), startPoint.getCenterY()) < 10) {
					declinePart.setStartX(startPoint.getCenterX());
					declinePart.setStartY(startPoint.getCenterY());
					declinePart.setEndX(startPoint.getCenterX() + 50);
					declinePart.setEndY(startPoint.getCenterY() + 50);
					trackList.add(declinePart);
					trackStrings.add("Decline");
				}
				else {
					for (Line l : trackList) {
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							declinePart.setStartX(l.getEndX());
							declinePart.setStartY(l.getEndY());
							declinePart.setEndX(l.getEndX() + 50);
							declinePart.setEndY(l.getEndY() + 50);
							trackList.add(declinePart);
							trackStrings.add("Decline");
							break;
						}
					}
				}
				
			});
			//put part on pane
			myPane.getChildren().add(declinePart);
		});
		
		
		//create flat part
		flat.setOnMouseClicked(e -> {
			Line flatPart = new Line(flat.getX() - 25, flat.getY(), flat.getX() + 25, flat.getY());
			flatPart.setStroke(Color.BROWN);
			flatPart.setStrokeWidth(5);
			//move flat part
			flatPart.setOnMouseDragged(j -> {
				if (!trackList.contains(flatPart)) {
					flatPart.setStartX(j.getSceneX() - 25);
					flatPart.setStartY(j.getSceneY());
					flatPart.setEndX(j.getSceneX() + 25);
					flatPart.setEndY(j.getSceneY());
				}
			});
			//snap part to other parts
			flatPart.setOnMouseReleased(j -> {
				double x1 = flatPart.getStartX();
				double y1 = flatPart.getStartY();
				if (getDistance(x1, y1, startPoint.getCenterX(), startPoint.getCenterY()) < 10) {
					flatPart.setStartX(startPoint.getCenterX());
					flatPart.setStartY(startPoint.getCenterY());
					flatPart.setEndX(startPoint.getCenterX() + 50);
					flatPart.setEndY(startPoint.getCenterY());
					trackList.add(flatPart);
					trackStrings.add("Flat");
				}
				else {
					for (Line l : trackList) {
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							flatPart.setStartX(l.getEndX());
							flatPart.setStartY(l.getEndY());
							flatPart.setEndX(l.getEndX() + 50);
							flatPart.setEndY(l.getEndY());
							trackList.add(flatPart);
							trackStrings.add("Flat");
							break;
						}
					}
				}
				
			});
			//put part on pane
			myPane.getChildren().add(flatPart);
		});
		
		
		//startPoint can be moved up and down
		startPoint.setOnMouseDragged(e -> {
			if (!(trackList.size() > 0)) {
				startPoint.setCenterY(e.getSceneY());}
		});
		
		//move endPoint
		endPoint.setOnMouseDragged(e -> {
			endPoint.setCenterX(e.getSceneX());
			endPoint.setCenterY(e.getSceneY());
		});
		
		
		myPane.getChildren().addAll(incline, decline, flat, startPoint, endPoint);
		
		primaryStage.setScene(new Scene(myPane));
		primaryStage.show();
	}
	
	public double getDistance(double x1, double y1, double x2, double y2) {
		return (Math.sqrt((Math.pow(x1 - x2, 2)) + Math.pow(y1 - y2, 2)));
	}
}
