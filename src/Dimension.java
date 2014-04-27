/**
 * Immutable basic class of the universe
 */
class Dimension {
    /** number of dimension of the universe **/
    final private int size;

    final private static int TWO_DIMENSIONS = 2;

    /** Constructor
     * with value 2 as
     * a default value
     * expecting that most
     * of our work will be
     * in 2 dimensions.
     * **/
    protected Dimension() {
        size = TWO_DIMENSIONS;
    }

    /** Constructor **/
    protected Dimension(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                    "Number of dimensions cannot be negative or equals 0"
            );
        }
        this.size = size;
    }

    /**
     * @return number of dimensions of the universe
     */
    public int getDimension() {
        return size;
    }

    /**
     * Overridden equals method compares sizes of the dimensions.
     * @param d object to compare
     * @return TRUE for same object or with the same dimension size, FALSE - else.
     */
    @Override
    public boolean equals(Object d) {
        if (d == this) {
            return true;
        }
        if (!(d instanceof Dimension)) {
            return false;
        }
        return size == ((Dimension) d).size;
    }


    @Override
    public int hashCode() {
        return 31 * size;
    }
}