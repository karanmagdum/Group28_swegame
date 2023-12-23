package game;

import java.util.Stack;

/**
 * This class represents a point on a Backgammon board, extending the functionality of the Triangle class.
 * It provides methods to add a checker to a point and to check for equality based on the checker type.
 */
public class Point extends Triangle {
    /**
     * Checks if this Point object is equal to another object.
     * Equality is based on the type of checker on the top of the point's stack.
     *
     * @param o The object to compare with this Point.
     * @return true if the given object is a Point with the same top checker type; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        if(point.isEmpty())
            return false;
        return equals(point.getList().get(0));
    }

     /**
     * Adds a checker to this point. If the point is not empty, the added checker must match the existing checker type.
     *
     * @param checker The checker to add to this point.
     * @throws RuntimeException if trying to add a checker that doesn't match the point's existing checker type.
     */
    @Override
    public void add(Checker checker){
        if(!isEmpty() && checker!=getList().get(0)){
            throw new RuntimeException("Wrong checker value added");
        }
        getList().push(checker);
    }
}