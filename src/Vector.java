/**
 * Class of vector.
 */
final public class Vector extends Dimension {
    /** Coordinates of source of vector
     * in other words vector implementation
     * from the beginning of the coordinate
     * field. Stored as a Point. */
    private final Point source;

    /**
     * Constructor of the vector by point.
     * NOTE: Order has value.
     * @param source point of vector
     */
    public Vector(Point source) {
        super(source.getDimension());
        this.source = source;
    }

    /**
     * Constructor of the vector by two points.
     * NOTE: Order has value.
     * @param from from this point
     * @param to to this point
     */
    public Vector(Point from, Point to) {
        this(from.sub(to));
    }

    /**
     * Constructor of the vector by the array of coordinates.
     * NOTE: Order has value.
     * @param dots coordinates
     */
    public Vector(double... dots) {
        super(dots.length);
        this.source = new Point(dots);
    }

    /**
     * Constructor of the vector by segment.
     * NOTE: segment.from == from, segment.to == to.
     * @param segment segment to create vector
     */
    public Vector(Segment segment) {
        this(segment.getFrom().sub(segment.getTo()));
    }

    /**
     * Constructor of the vector by segment
     * where there is an ability to create a
     * reversed vector.
     * @param segment segment to create vector
     * @param reversed reversed or not
     */
    public Vector(Segment segment, boolean reversed) {
        this(reversed ?
                segment.getTo().sub(segment.getFrom()) :
                segment.getFrom().sub(segment.getTo()));
    }

    /**
     * Return source of the vector by one point.
     * @return point of the vector
     */
    public Point getSource() {
        return source;
    }

    /**
     * Length of the vector.
     * @return length of the vector.
     */
    public double length() {
        double[] coords = source.getCoords();
        double value = 0;
        for (int i = 0; i < coords.length; i++) {
            value += coords[i] * coords[i];
        }
        return Math.sqrt(value);
    }

    /**
     * Getting the unit vector of the current vector.
     * @return unit vector of the current vector.
     */
    public Vector unitVector() {
        return new Vector(source.div(length()));
    }

    /**
     * Vector multiplication
     * @param vector vector to multiply with
     * @return Z coordinate of the result vector
     */
    public double vectorMul(Vector vector) {
        //TODO: multidimentional multiplication
        if (this.getDimension() != vector.getDimension() ||
                this.getDimension() != 2) {
            throw new IllegalArgumentException(
                    "Vectors have dimensions which are not equal to 2"
            );
        }
        double[] coord1 = this.getSource().getCoords();
        double[] coord2 = vector.getSource().getCoords();
        return coord1[0] * coord2[1] - coord1[1] * coord2[0];
    }

    /**
     * Method to compute scalar multiplications.
     * @param vector vector to multiply with
     * @return value of the multiplications.
     */
    public double scalarMul(Vector vector) {
        double result = 0;
        double[] coords = this.getSource().mul(vector.getSource()).getCoords();
        for (int i = 0; i < coords.length; i++) {
            result += coords[i];
        }
        return result;
    }

    /**
     * Summation of the two vectors.
     * @param vector vector to sum with
     * @return new inheritance of the vector.
     */
    public Vector sum(Vector vector) {
        return new Vector(this.getSource().sum(vector.getSource()));
    }

    /**
     * toString() method.
     * @return the string representation of the vector.
     */
    @Override
    public String toString() {
        String result = "Vector: <";
        double[] source = this.source.getCoords();
        for (int i = 0; i < source.length - 1; i++) {
            result += source[i] + ", ";
        }
        result += source[source.length - 1] + ">.";
        return result;
    }

    /**
     * Overridden equals.
     * @param obj object to compare with.
     * @return TRUE if vectors are identical.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector vec = (Vector) obj;
        if (vec.getDimension() != this.getDimension()) {
            return false;
        }
        return this.getSource().equals(vec.getSource());
    }

    /**
     * Overridden equals.
     * @return hash code of the vector.
     */
    @Override
    public int hashCode() {
        return source.hashCode();
    }
}
