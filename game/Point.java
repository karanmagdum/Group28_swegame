package game;

import java.util.Stack;

public class Point{

    public enum Checker{
        O,
        X
    }
    private Stack<Checker> list;

    Point(){
        list = new Stack<>();
    }
    public Stack<Checker> getList() {
        return list;
    }

    public int getSize(){
        return getList().size();
    }

    public boolean isEmpty(){
        return getList().isEmpty();
    }

    public boolean equals(Checker checker) {
        return (!isEmpty() && checker==getList().get(0));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        if(point.isEmpty())
            return false;
        return equals(point.getList().get(0));
    }

    public void add(Checker checker){
        if(!isEmpty() && checker!=getList().get(0)){
            throw new RuntimeException("Wrong checker value added");
        }
        getList().push(checker);
    }
}