/**
 * Basic class for all geometric objects in the universe.
 * At this point we assume that we will work in the same dimensions all time.
 * (Hard to imagine how we will cooperate objects from different dimensions.
 */
public abstract class GeomObject extends Dimension {
    /** Default constructor **/
    public GeomObject() {
        super();
    }

    /** Constructor **/
    public GeomObject(int numOfDimensions) {
        super(numOfDimensions);
    }

    /**
     * Overridden equals
     * @param obj object to compare with
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
}
