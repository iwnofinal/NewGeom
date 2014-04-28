import java.awt.*;
import java.util.Arrays;

/**
 * Immutable class of geometric segment
 */
final public class Segment extends Dimension {
    /** Point from (where segment starts) **/
    private final Point from;

    /** Point to (where segment ends) **/
    private final Point to;

    /** Constructor **/
    public Segment(Point from, Point to) {
        super(from.getDimension());
        if (from.getDimension() != to.getDimension()) {
            throw new IllegalArgumentException(
                    "Points are in the distinct dimensions!"
            );
        }
        this.from = from;
        this.to = to;
    }

    /** Constructor **/
    public Segment(double... dots) {
        this(new Point(Arrays.copyOfRange(dots, 0, dots.length / 2)),
                new Point(Arrays.copyOfRange(dots, dots.length / 2, dots.length)));
    }

    /**
     * Get two points.
     * Note: returning mutable object brings
     * no violations of the immutability
     * @return array length of 2
     */
    public Point[] getPoints() {
        Point[] result = { from, to };
        return result;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    /**
     * Method to finding out if two segment intersects.
     * Method uses vector multiplication.
     * @param segment another segment
     * @return TRUE - segments intersect, FALSE - else
     */
    public boolean isIntersect(Segment segment) {
        Vector vectorSeg1 = new Vector(this);
        Vector vectorSeg2 = new Vector(segment);
        Vector vecFor11 = new Vector(this.from, segment.from);
        Vector vecFor12 = new Vector(this.from, segment.to);
        Vector vecFor21 = new Vector(segment.from, this.from);
        Vector vecFor22 = new Vector(segment.from, this.to);
        double value1 = vectorSeg1.vectorMul(vecFor11);
        double value2 = vectorSeg1.vectorMul(vecFor12);
        double value3 = vectorSeg2.vectorMul(vecFor21);
        double value4 = vectorSeg2.vectorMul(vecFor22);
        if (value1 * value2 < 0 && value3 * value4 < 0) {
            return true;
        } else if (value1 == 0 && this.isOnSegment(segment.from)) {
            return true;
        } else if (value2 == 0 && this.isOnSegment(segment.to)) {
            return true;
        } else if (value3 == 0 && segment.isOnSegment(this.from)) {
            return true;
        } else if (value4 == 0 && segment.isOnSegment(this.to)) {
            return true;
        }
        return false;
    }

    /**
     * Finding out is point lies on the
     * segment assuming that all 3 points
     * lie on the one line.
     * @param point
     * @return
     */
    private boolean isOnSegment(Point point) {
        return Math.min(this.getFrom().getX(), this.getTo().getX()) <= point.getX() &&
                point.getX() <= Math.max(this.getFrom().getX(), this.getTo().getX()) &&
                Math.min(this.getFrom().getY(), this.getTo().getY()) <= point.getY() &&
                point.getY() <= Math.max(this.getFrom().getY(), this.getTo().getX());
    }

    /**
     * Method to return double-valued length of the segment
     * @return double value of the length of the segment.
     */
    public double length() {
        double result = 0;
        double[] fromCoord = from.getCoords();
        double[] toCoord = to.getCoords();
        for (int i = 0; i < getDimension(); i++) {
            result += (fromCoord[i] - toCoord[i]) * (fromCoord[i] - toCoord[i]);
        }
        return Math.sqrt(result);
    }

    /**
     * To string method.
     * @return string representation of the vector.
     */
    @Override
    public String toString() {
        String result = "Segment: from (";
        double[] fromCoord = from.getCoords();
        double[] toCoord = to.getCoords();
        for (int i = 0; i < fromCoord.length - 1; i++) {
            result+= fromCoord[i] + ", ";
        }
        result += fromCoord[fromCoord.length - 1] + ") to (";
        for (int i = 0; i < toCoord.length - 1; i++) {
            result += toCoord[i] + ", ";
        }
        result += toCoord[toCoord.length - 1] + ").";
        return result;
    }

    /**
     * Equals method.
     * Note: despite the fact that segment has no direction
     * we define that the direction IS valuable.
     * @param obj object to compare with
     * @return TRUE - if the segments have identical points from and to,
     *         FALSE - else.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Segment)) {
            return false;
        }
        Segment seg = (Segment) obj;
        if (seg.getDimension() != this.getDimension()) {
            return false;
        }
        if (from.equals(from) && to.equals(to)) {
            return true;
        }
        return false;
    }

    /**
     * Overridden hash code.
     * @return hash code of the segment.
     */
    @Override
    public int hashCode() {
        int result = from.hashCode() * 31;
        result += to.hashCode();
        return result;
    }

    /**
     * Method to draw 2D-segment
     * @param g graphics instance
     * @param color color of the segment
     * @param thickness thickness of the segment
     * @return sucess of the drawing
     */
    public boolean draw2D(Graphics g, Color color, int thickness) {
        if (getDimension() != 2) {
            throw new IllegalStateException(
                    "The segment is not 2-dimensional!"
            );
        }
        Color colorInit = g.getColor();
        g.setColor(color);
        Stroke strokeInit = ((Graphics2D) g).getStroke();
        ((Graphics2D) g).setStroke(new BasicStroke(thickness));
        g.drawLine((int)from.getX(), (int)from.getY(), (int)to.getX(), (int)to.getY());
        ((Graphics2D) g).setStroke(strokeInit);
        g.setColor(colorInit);
        return true;
    }
}