import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent space
 * in the geometric universe
*/
public class Space extends GeomObject {
    /** List of points **/
    protected List<GeomObject> objects;

    /** Constructor **/
    public Space() {
        super();
        objects = new ArrayList<GeomObject>();
    }

    /** Constructor **/
    public Space(int numOfDimensions) {
        super(numOfDimensions);
        objects = new ArrayList<GeomObject>();
    }

    /** Constructor **/
    public Space(GeomObject[] objects) {
        this(objects[0].getDimension());
        for (GeomObject object : objects) {
            this.objects.add(object);
        }
    }

    /** Constructor **/
    public Space(List<GeomObject> objects) {
        this(objects.get(0).getDimension());
        for (GeomObject object: objects) {
            this.objects.add(object);
        }
    }

    /**
     * Add point if there is no such point
     * @param object point to add
     * @return success of adding
     */
    public boolean addObject(GeomObject object) {
        if (object.getDimension() != getDimension()) {
            throw new IllegalArgumentException("Space.addObject()" +
                    " illegal dimension of the object.");
        }
        objects.add(object);
        return true;
    }
}