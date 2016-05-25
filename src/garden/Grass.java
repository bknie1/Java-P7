/**
 *  Grass.java
 *  @author  Brandon Knieriem
 *  @description Part of the Garden.java program.
 *  @version May 2016
 */

package garden;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

public class Grass extends Arc {
	Integer GrassRadiusX = 5;
	Integer GrassRadiusY = 40;
	Integer GrassAngle = 160 + (int) (Math.random() * ((200 - 160) + 1));

	public Grass(double x, double y) {
		setCenterX(x);
		setCenterY(y);

		setFill(Color.FORESTGREEN);
		setRadiusX(GrassRadiusX);
		setRadiusY(GrassRadiusY);
		setLength(100);
		setStartAngle(GrassAngle);
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