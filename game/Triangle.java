package game;

import java.util.Stack;

public abstract class Triangle {
    public enum Checker{
        O,
        X
    }
    private Stack<Checker> list;

    public int getSize(){
        return getList().size();
    }
    public boolean isEmpty(){
        return getList().isEmpty();
    }
    public boolean equals(Checker checker) {
        return (!isEmpty() && checker==getList().get(0));
    }

    public Stack<Checker> getList() {
        return list;
    }

    Triangle(){
        list = new Stack<>();
    }

    //Functions to implemented if inherited
    public abstract boolean equals(Object o);
    public abstract void add(Checker checker);
}
