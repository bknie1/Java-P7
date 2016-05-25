/**
 *  Tree.java
 *  @author  Brandon Knieriem
 *  @description Part of the Garden.java program.
 *  @version May 2016
 */

package garden;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Tree extends Ellipse {
	Integer TreeRadiusX = 200;
	Integer TreeRadiusY = 150;

	public Tree(double x, double y) {
		setCenterX(x);
		setCenterY(y);

		setFill(Color.FORESTGREEN);
		setRadiusX(TreeRadiusX);
		setRadiusY(TreeRadiusY);
	}
}