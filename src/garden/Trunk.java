/**
 *  Trunk.java
 *  @author  Brandon Knieriem
 *  @description Part of the Garden.java program.
 *  @version May 2016
 */

package garden;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Trunk extends Rectangle {
	Integer TrunkWidth = 75;
	Integer TrunkHeight = 400;

	public Trunk(double x, double y) {
		setX(x - 40);
		setY(y);

		setFill(Color.SADDLEBROWN);
		setWidth(TrunkWidth);
		setHeight(TrunkHeight);
	}
}