/**
 *  Flower.java
 *  @author  Brandon Knieriem
 *  @description Part of the Garden.java program.
 *  @version May 2016
 */

package garden;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Flower extends Circle {
	Double FlowerRadius = Math.floor(Math.random() * 6) + 14;

	public Flower(double x, double y) {
		setCenterX(x);
		setCenterY(y);
		double red = truncateDouble(Math.random() * .6 + .4, 2);
		double blue = truncateDouble(Math.random() * .6 + .4, 2);
		double green = truncateDouble(Math.random() * .6 + .4, 2);
		setFill(new Color(red, green, blue, 1));
		setRadius(FlowerRadius);
		System.out.println("A new flower grows!");
	}

	double truncateDouble(double number, int numDigits) {
		double result = number;
		String arg = "" + number;
		int idx = arg.indexOf('.');
		if (idx != -1) {
			if (arg.length() > idx + numDigits) {
				arg = arg.substring(0, idx + numDigits + 1);
				result = Double.parseDouble(arg);
			}
		}
		return result;
	}
}