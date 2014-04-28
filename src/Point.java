import java.awt.*;

/**
 * Immutable class of geometric point.
 */
final public class Point extends GeomObject {
    /** Dots of point **/
    final private double[] coords;

    /** Constructor **/
    public Point(double... dots) {
        super(dots.length);
        this.coords = new double[dots.length];
        for (int i = 0; i < dots.length; i++) {
            this.coords[i] = dots[i];
        }
    }

    /**
     *
     * @return array of the coordinated of the point
     */
    public double[] getCoords() {
        // for not allowing to change
        // items of the array
        return coords.clone();
    }

    /**
     * Returning coordinate by the index.
     * @param index index of the coordinate
     * @return coordinate of the point by index.
     */
    public double getCoord(int index) {
        if (index >= getDimension()) {
            throw new IllegalArgumentException(
                    "Index is bigger than dimension"
            );
        }
        return coords[index];
    }

    /**
     *
     * @return X coordinate
     */
    public double getX() {
        return coords[0];
    }

    /**
     *
     * @return Y coordinate
     */
    public double getY() {
        if (getDimension() < 2) {
            throw new IllegalStateException(
                    "This Point has no second dimension"
            );
        }
        return coords[1];
    }

    /**
     *
     * @return Z coordinate
     */
    public double getZ() {
        if (getDimension() < 3) {
            throw new IllegalStateException(
                "This Point has no third dimension"
            );
        }
        return coords[2];
    }

    /** OPERATIONS WITH POINTS **/

    /**
     * Method to return two double arrays of
     * coordinates for points for using it
     * in basic operations
     * @param point point to operate with
     * @return double[][]
     */
    private double[][] prepareForOperation(Point point) {
        if (this.getDimension() != point.getDimension()) {
            throw new IllegalArgumentException(
                    "Dimensions of the points are distinct!");
        }
        double[] thisCoord = this.getCoords();
        double[] pointCoord = point.getCoords();
        double[][] result = new double[2][];
        result[0] = thisCoord;
        result[1] = pointCoord;
        return result;
    }


    /**
     * Method to compute coordinates' sub
     * Example: to get vector coordinates
     * @param point point to sub with
     * @return result point
     */
    public Point sub(Point point) {
        double[][] prepared = prepareForOperation(point);
        double[] dots = new double[prepared[0].length];
        for (int i = 0; i < this.getDimension(); i++) {
            dots[i] = prepared[1][i] - prepared[0][i];
        }
        return new Point(dots);
    }

    /**
     * Method to sum two points for some reason
     * @param point point to sum with
     * @return result point
     */
    public Point sum(Point point) {
        double[][] prepared = prepareForOperation(point);
        double[] dots = new double[prepared[0].length];
        for (int i = 0; i < this.getDimension(); i++) {
            dots[i] = prepared[1][i] + prepared[0][i];
        }
        return new Point(dots);
    }

    /**
     * Method to sum all coordinates of
     * the point with a value
     * @param value value to sum with
     * @return result point
     */
    public Point sum(int value) {
        double[] coords = getCoords();
        for (int i = 0; i < coords.length; i++) {
            coords[i] += value;
        }
        return new Point(coords);
    }

    /**
     * Method to multiply all coordinates of the
     * point with a value
     * @param value value to multiply with
     * @return result point
     */
    public Point mul(int value) {
        double[] coords = getCoords();
        for (int i = 0; i < coords.length; i++) {
            coords[i] *= value;
        }
        return new Point(coords);
    }

    /**
     * Method to multiply all coordinates of the
     * point with a coordinates of the another point.
     * @param point point to multiply with
     * @return result point
     */
    public Point mul(Point point) {
        double[][] prepared = prepareForOperation(point);
        double[] dots = new double[prepared[0].length];
        for (int i = 0; i < this.getDimension(); i++) {
            dots[i] = prepared[1][i] * prepared[0][i];
        }
        return new Point(dots);
    }

    /**
     * Method to multiply all coordinates of the
     * point with a value
     * @param value value to multiply with
     * @return result point
     */
    public Point div(double value) {
        if (value == 0) {
            throw new IllegalArgumentException(
                    "Value should not be 0"
            );
        }
        double[] coords = getCoords();
        for (int i = 0; i < coords.length; i++) {
            coords[i] /= value;
        }
        return new Point(coords);
    }

    /** END OF OPERATIONS OF POINTS **/

    /**
     * toString() method
     * @return string representation of the point
     */
    @Override
    public String toString() {
        String result = "Point: ";
        for (int i = 0; i < this.coords.length - 1; i++) {
            result += coords[i] + ", ";
        }
        result += coords[this.coords.length - 1] + ".";
        return result;
    }

    /**
     * Compare point to the other
     * @param obj object to compare with
     * @return true if coordinates are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        return this.compareTo((Point) obj) == 0;
    }

    /**
     * Overridden hashCode method
     * @return hash code of the point
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (double coord : coords) {
            result += (result + (int)(coord * 100)) * 31;
        }
        return result;
    }

    /**
     * Compares two points
     * by the COORDINATES IN REVERSED ORDER(URGENT)
     * example: ...Z, Y, X
     * @param point point to compare with.
     * @return 1 if this > point, 0 if this == point.
     */
    public int compareTo(Point point) {
        if (this.getDimension() != point.getDimension()) {
            throw new IllegalArgumentException(
                    "Points are in the distinct dimensions"
            );
        }
        for (int i = this.getDimension() - 1; i >= 0; i--) {
            if (this.getCoord(i) > point.getCoord(i)) {
                return 1;
            } else if (this.getCoord(i) < point.getCoord(i)) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Method to draw 2-dimensional point
     * @param g graphics instance
     * @param color color of the point
     * @param size size of the point (number of pixels)
     * @return success of the drawing
     */
    public boolean draw2D(Graphics g, Color color, int size) {
        if (getDimension() != 2) {
            throw new IllegalStateException(
                    "Point is not 2-dimensional!"
            );
        }
        Color colorInit = g.getColor();
        g.setColor(color);
        g.fillOval((int)getX() - size / 2, (int)getY() - size / 2, size, size);
        g.setColor(colorInit);
        return true;
    }
}