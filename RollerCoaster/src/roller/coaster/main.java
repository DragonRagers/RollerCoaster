package roller.coaster;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import roller.coaster.*;


public class main extends Application{
	private Canvas myCanvas = new Canvas(1200, 500);
	private Pane myPane = new Pane();
	final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

	
	
	int pieceLength = 5;

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
		ArrayList<TrackPiece> trackPieces = new ArrayList<TrackPiece>();
		Text incline = new Text();
		Text decline = new Text();
		Text flat = new Text();
		Text loop = new Text();
		Button go = new Button();
		Text stats = new Text("PositionX: \nPositionY: ");
		
		stats.setX(650);
		stats.setY(420);
		
		go.setText("GOGOGOGOGO");
		go.setLayoutX(500);
		go.setLayoutY(420);
		
		Circle trash = new Circle(1200, 450, 15);
		trash.setFill(Color.GRAY);
		trash.setStroke(Color.BLACK);
		
		Circle startPoint = new Circle(40, 40, 8);
		Circle endPoint = new Circle(1100, 400, 8);
		startPoint.setFill(Color.GREEN);
		endPoint.setFill(Color.RED);
		
		
		incline.setText("INCLINE");
		incline.setX(320);
		incline.setY(440);
		decline.setText("DECLINE");
		decline.setY(440);
		decline.setX(400);
		flat.setText("FLAT");
		flat.setY(440);
		flat.setX(260);
		loop.setText("LOOP");
		loop.setY(440);
		loop.setX(200);
		
		
	
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
					trackPieces.add(new Straight(new Point(inclinePart.getEndX(), inclinePart.getEndY(), 0), pieceLength, 45));
				}
				else {
					Line l = trackList.get(trackList.size() - 1);
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							inclinePart.setStartX(l.getEndX());
							inclinePart.setStartY(l.getEndY());
							inclinePart.setEndX(l.getEndX() + 50);
							inclinePart.setEndY(l.getEndY() - 50);
							trackList.add(inclinePart);
							trackPieces.add(new Straight(new Point(inclinePart.getEndX(), inclinePart.getEndY(), 0), pieceLength, 45));
							
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
					trackPieces.add(new Straight(new Point(declinePart.getEndX(), declinePart.getEndY(), 0), pieceLength, -45));
				}
				else {
					Line l = trackList.get(trackList.size() - 1);
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							declinePart.setStartX(l.getEndX());
							declinePart.setStartY(l.getEndY());
							declinePart.setEndX(l.getEndX() + 50);
							declinePart.setEndY(l.getEndY() + 50);
							trackList.add(declinePart);
							trackPieces.add(new Straight(new Point(declinePart.getEndX(), declinePart.getEndY(), 0), pieceLength, -45));							
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
					trackPieces.add(new Straight(new Point(flatPart.getEndX(), flatPart.getEndY(), 0), pieceLength, 0));
				}
				else {
					Line l = trackList.get(trackList.size() - 1);
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							flatPart.setStartX(l.getEndX());
							flatPart.setStartY(l.getEndY());
							flatPart.setEndX(l.getEndX() + 50);
							flatPart.setEndY(l.getEndY());
							trackList.add(flatPart);
							trackPieces.add(new Straight(new Point(flatPart.getEndX(), flatPart.getEndY(), 0), pieceLength, 0));
						}
					}
				
				
			});
			//put part on pane
			myPane.getChildren().add(flatPart);
		});
		
		//create loop part
		loop.setOnMouseClicked(e -> {
			Line loopBase = new Line(loop.getX() - 85, loop.getY(), loop.getX() + 85, loop.getY());
			loopBase.setStroke(Color.BROWN);
			loopBase.setStrokeWidth(5);
			Circle loopCircle = new Circle(loop.getX(), loop.getY(), 55);
			loopCircle.setStroke(Color.BROWN);
			loopCircle.setFill(Color.WHITE);
			loopCircle.setStrokeWidth(5);
			loopBase.startXProperty().bind(loopCircle.centerXProperty().subtract(85));
			loopBase.startYProperty().bind(loopCircle.centerYProperty().add(loopCircle.getRadius()));
			loopBase.endXProperty().bind(loopCircle.centerXProperty().add(85));
			loopBase.endYProperty().bind(loopCircle.centerYProperty().add(loopCircle.getRadius()));
			
			loopCircle.setOnMouseDragged(j -> {
				if (!trackList.contains(loopBase)) {
					loopCircle.setCenterX(j.getSceneX());
					loopCircle.setCenterY(j.getSceneY());
				}
			});
			
			//snap part to other parts
			loopCircle.setOnMouseReleased(j -> {
				double x1 = loopBase.getStartX();
				double y1 = loopBase.getStartY();
				if (getDistance(x1, y1, startPoint.getCenterX(), startPoint.getCenterY()) < 10) {
					loopCircle.setCenterX(startPoint.getCenterX() + 85);
					loopCircle.setCenterY(startPoint.getCenterY() - loopCircle.getRadius());
					trackList.add(loopBase);
					trackPieces.add(new VerticalLoop(new Point(loopBase.getStartX(), loopBase.getStartY(), 0), (float)loopCircle.getRadius(), 0));
				}
				else {
					Line l = trackList.get(trackList.size() - 1);
						if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
							loopCircle.setCenterX(l.getEndX() + 85);
							loopCircle.setCenterY(l.getEndY() - loopCircle.getRadius());
							trackList.add(loopBase);
							trackPieces.add(new VerticalLoop(new Point(loopBase.getStartX(), loopBase.getStartY(), 0), (float)loopCircle.getRadius(), 0));
						}
					}
				
				
			});
			
			myPane.getChildren().addAll(loopBase, loopCircle);
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
		//lock endPoint
		endPoint.setOnMouseReleased(e -> {
			double x1 = endPoint.getCenterX();
			double y1 = endPoint.getCenterY();
			Line l = trackList.get(trackList.size() - 1);
			if (getDistance(x1, y1, l.getEndX(), l.getEndY()) < 10) {
				endPoint.setCenterX(l.getEndX());
				endPoint.setCenterY(l.getEndY());
				//send trackList
			}
		});
		
		//output position and actual simulation
		go.setOnMouseClicked(e -> {
			for (TrackPiece t : trackPieces) {
				ses.scheduleAtFixedRate(new Runnable() {
					public void run() {
						String toStat = simulate(t);
						stats.setText(toStat);
					}
				}, 0, 1, TimeUnit.SECONDS);
			}
			ses.shutdown();
		});
		
		
		myPane.getChildren().addAll(incline, decline, flat, loop, go,stats, startPoint, endPoint);
		
		primaryStage.setScene(new Scene(myPane));
		primaryStage.show();
	}
	
	public double getDistance(double x1, double y1, double x2, double y2) {
		return (Math.sqrt((Math.pow(x1 - x2, 2)) + Math.pow(y1 - y2, 2)));
	}
	
	public String simulate(TrackPiece t) {
		Point current = t.calcPosition(t.arcLength());
		return "PositionX: " + current.x + "\nPositionY: " + current.y;
	}
}
