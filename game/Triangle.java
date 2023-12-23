package game;

import java.util.Stack;

/**
 * Represents a triangle on a Backgammon board. This abstract class provides the basic functionality
 * for managing checkers on a triangle, including adding and checking the state of checkers.
 * It is designed to be extended by concrete implementations.
 */
public abstract class Triangle {

    /**
     * Enumeration for the types of checkers.
    */
    public enum Checker{
        O,
        X
    }

    private Stack<Checker> list;

    /**
     * Returns the number of checkers on this triangle.
     *
     * @return The number of checkers.
     */
    public int getSize(){
        return getList().size();
    }

     /**
     * Checks if this triangle has no checkers.
     *
     * @return true if there are no checkers on this triangle, false otherwise.
     */
    public boolean isEmpty(){
        return getList().isEmpty();
    }

    /**
     * Checks if the top checker on this triangle is of the specified type.
     *
     * @param checker The type of checker to compare against.
     * @return true if the top checker is of the specified type, false if the triangle is empty or the types do not match.
     */
    public boolean equals(Checker checker) {
        return (!isEmpty() && checker==getList().get(0));
    }

    /**
     * Gets the stack of checkers on this triangle.
     *
     * @return The stack of checkers.
     */
    public Stack<Checker> getList() {
        return list;
    }

    /**
     * Constructs a Triangle with an empty stack of checkers.
     */
    Triangle(){
        list = new Stack<>();
    }

    //Functions to implemented if inherited
     /**
     * Determines if this triangle is equal to another object.
     * This method must be implemented by subclasses.
     *
     * @param o The object to compare with this Triangle.
     * @return true if the specified object is equal to this triangle, false otherwise.
     */
    public abstract boolean equals(Object o);

    /**
     * Adds a checker to this triangle.
     * This method must be implemented by subclasses.
     *
     * @param checker The checker to add.
     */
    public abstract void add(Checker checker);
}
