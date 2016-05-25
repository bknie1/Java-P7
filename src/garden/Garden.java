/**
 *  Garden.java
 *  @author  Brandon Knieriem
 *  @description A program that allows the user to interact with image
 *  			 objects without GUI buttons.
 *  @version May 2016
 */
package garden;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
// For centering
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// For KeyEvent and KeyCode
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Garden Class.
 */
public class Garden extends Application {

	/* Model */
	Integer width = 1024;
	Integer height = 768;
	Integer iGardenCount = 0;
	ArrayList<Flower> alFlowers = new ArrayList<Flower>();
	Integer SceneCount = 1;
	/* View */
	BorderPane pCanvas = new BorderPane();
	Pane pGrass = new Pane();
	Pane pText = new Pane();
	public static final Color palegreen = new Color(.83, .93, .57, 1);
	public static final Color goldenrod = new Color(.92, .92, .68, 1);
	public static final Color liberty = new Color(.64, .86, .82, 1);
	public static final Background bkColor = new Background
			(new BackgroundFill(palegreen, null, new Insets(4)));

	Text tDir = new Text("Click or drag to place vegetation.\n" + "Y: \t\tSet a yellow background.\n"
			+ "B: \t\tSet a blue background.\n" + "G: \t\tSet a green background.\n" + "Z: \t\tToggle the scene.\n"
			+ "Arrows: \tMoves the flowers.\n" + "Q: \t\tExits the program.");
	Scene scGarden = new Scene(pCanvas, width, height);
	Pane pMusic = new Pane();
	Scene scMusic = new Scene(pMusic, width, height);
	ImageView im = new ImageView(new Image("SMB.jpg"));
	MediaPlayer audio = new MediaPlayer(new Media(new File("bin\\SMB.mp3").toURI().toString()));
	private Stage st;

	/**
	 * The main method is unused because there are no recognized arguments and
	 * this is a JavaFX program.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage st) {
		/* Mouse Listener */
		pCanvas.setOnMouseClicked(e -> {
			doClick(e);
		});
		pCanvas.setOnMouseDragged(e -> {
			doDrag(e);
		});
		/* GUI Construction */
		this.st = st;

		st.setScene(scGarden);
		buildMusicGUI();
		buildGUI();
		st.setTitle("My Garden - Brandon Knieriem");
		st.show();
		scGarden.setOnKeyReleased(new KeyPressed());
	}

	/**
	 * Builds the music GUI.
	 */
	// -----------------------------------------------------------------------------
	public void buildMusicGUI() {
		pMusic.getChildren().addAll(im);
		st.setTitle("My Music - Brandon Knieriem");
		st.setResizable(false);
	}

	/**
	 * Builds the normal GUI.
	 */
	public void buildGUI() {
		pCanvas.setBackground(bkColor);
		pCanvas.getChildren().addAll();
		pText.getChildren().addAll(tDir);
		pText.setLayoutX(width - 190);
		pText.setLayoutY(20);
		pCanvas.getChildren().addAll(pText);
		pCanvas.setCenter(pGrass);
		st.setResizable(false);
	}

	/**
	 * Sets a Yellow Background.
	 */
	public void YellowBG() {
		Background bkYellow = new Background(new BackgroundFill(goldenrod, null, new Insets(4)));
		pCanvas.setBackground(bkYellow);
	}

	/**
	 * Sets a Green Background.
	 */
	public void GreenBG() {
		Background bkGreen = new Background(new BackgroundFill(palegreen, null, new Insets(4)));
		pCanvas.setBackground(bkGreen);
	}

	/**
	 * Sets a Blue Background.
	 */
	public void BlueBG() {
		Background bkBlue = new Background(new BackgroundFill(liberty, null, new Insets(4)));
		pCanvas.setBackground(bkBlue);
	}

	/**
	 * Toggles between the Garden and Music Scenes.
	 */
	public void ToggleBG() {
		System.out.println("Toggling background.");
		switch (SceneCount) {
		case 0:
			st.setScene(scGarden);
			SceneCount++;
			System.out.println("Garden Scene");
			scGarden.setOnKeyReleased(new KeyPressed());
			audio.pause();
			break;
		case 1:
			st.setScene(scMusic);
			SceneCount--;
			System.out.println("Music Scene");
			scMusic.setOnKeyReleased(new KeyPressed());
			audio.play();
			break;
		}
	}

	/**
	 * Shifts the flower left.
	 */
	public void FlowerShiftLeft() {
		Double x;
		for (Flower e : alFlowers) {
			x = e.getCenterX();
			e.setCenterX(x - 10);
			FlowerCatcher(e);
		}
	}

	/**
	 * Shifts the flower right.
	 */
	public void FlowerShiftRight() {
		Double x;
		for (Flower e : alFlowers) {
			x = e.getCenterX();
			e.setCenterX(x + 10);
			FlowerCatcher(e);
		}
	}

	/**
	 * Shifts the flower up.
	 */
	public void FlowerShiftUp() {
		Double y;
		for (Flower e : alFlowers) {
			y = e.getCenterY();
			e.setCenterY(y - 10);
			FlowerCatcher(e);
		}
	}

	/**
	 * Shifts the flower down.
	 */
	public void FlowerShiftDown() {
		Double y;
		for (Flower e : alFlowers) {
			y = e.getCenterY();
			e.setCenterY(y + 10);
			FlowerCatcher(e);
		}
	}

	/**
	 * Shifts flowers that would otherwise fall off the edge of the program.
	 *
	 * @param e
	 *            the e
	 */
	public void FlowerCatcher(Flower e) {
		if (e.getCenterX() > scGarden.getWidth())
			e.setCenterX(0);
		if (e.getCenterX() < 0)
			e.setCenterX(scGarden.getWidth());
		if (e.getCenterY() > scGarden.getHeight())
			e.setCenterY(0);
		if (e.getCenterY() < 0)
			e.setCenterY(scGarden.getHeight());
	}

	/**
	 * Grow tree.
	 *
	 * @param e
	 *            the e
	 */
	public void GrowTree(MouseEvent e) {
		double xT = e.getX();
		double yT = e.getY();
		iGardenCount++; // Tree
		Tree tree1 = new Tree(xT, yT - 100);
		Tree tree2 = new Tree(xT - 100, yT + 50);
		Tree tree3 = new Tree(xT + 100, yT + 30);
		Trunk trunk1 = new Trunk(xT, yT);
		pGrass.getChildren().addAll(trunk1, tree1, tree2, tree3);
		System.out.println("A new tree grows!");
	}

	/**
	 * Grow bush.
	 *
	 * @param e
	 *            the e
	 */
	public void GrowBush(MouseEvent e) {
		double xB = e.getX();
		double yB = e.getY();
		iGardenCount++; // Bush
		Bush bush1 = new Bush(xB, yB);
		Bush bush2 = new Bush(xB + 40, yB + 40);
		Bush bush3 = new Bush(xB - 40, yB + 40);
		pGrass.getChildren().addAll(bush1, bush2, bush3);
		System.out.println("A new bush grows!");
	}

	/**
	 * Grow flower.
	 *
	 * @param e
	 *            the e
	 */
	public void GrowFlower(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		Flower flower1 = new Flower(x, y);
		pGrass.getChildren().add(flower1);
		System.out.println("A new flower grows!");
		alFlowers.add(flower1);
	}

	/**
	 * Grow grass.
	 *
	 * @param e
	 *            the e
	 */
	public void GrowGrass(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		Grass grass1 = new Grass(x, y);
		pGrass.getChildren().add(grass1);
		System.out.println("Grass grows!");
	}

	/**
	 * On Mouse Click.
	 *
	 * @param e
	 *            the e
	 */
	// -----------------------------------------------------------------------------
	public void doClick(MouseEvent e) {
		switch (iGardenCount) {
		/* Increments to grows one Tree, one Bush, and finally Flowers. */
		case 0:
			GrowTree(e);
			break;

		case 1:
			GrowBush(e);
			break;

		case 2:
			GrowFlower(e);
			break;
		}
	}

	/**
	 * On Mouse Drag.
	 *
	 * @param e
	 *            the e
	 */
	public void doDrag(MouseEvent e) {
		GrowGrass(e);
	}

	/**
	 * The Class KeyPressed.
	 */
	class KeyPressed implements EventHandler<KeyEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(KeyEvent e) {
			KeyCode kcKey = e.getCode();

			switch (kcKey) {
			case LEFT:
				FlowerShiftLeft();
				break;
			case RIGHT:
				FlowerShiftRight();
				break;
			case UP:
				FlowerShiftUp();
				break;
			case DOWN:
				FlowerShiftDown();
				break;
			case Y:
				YellowBG();
				break; // Yellow BG
			case B:
				BlueBG();
				break; // Blue BG
			case G:
				GreenBG();
				break; // Green BG
			case Z:
				ToggleBG();
				break; // Garden Toggle
			case Q:
				System.exit(0);
			default:
				break;
			}
		}
	}
}