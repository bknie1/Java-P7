/**
 *  Bush.java
 *  @author  Brandon Knieriem
 *  @description Part of the Garden.java program.
 *  @version May 2016
 */

package garden;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Bush extends Ellipse {
	Double BushRadiusX = Math.floor(Math.random() * 40) + 80;
	Double BushRadiusY = Math.floor(Math.random() * 30) + 40;

	public Bush(double x, double y) {
		setCenterX(x);
		setCenterY(y);

		setFill(Color.DARKGREEN);
		setRadiusX(BushRadiusX);
		setRadiusY(BushRadiusY);
	}
}