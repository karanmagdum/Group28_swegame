package game;

import java.util.Stack;

public class Point{
    Stack<String> list= new Stack<>();
    private String type = "";

    public String getType() {
        return type;
    }

    public int getSize(){
        return list.size();
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void add(String checker){
        if(!(checker.equals("O") || checker.equals("X") || checker.equals("="))){
            throw new RuntimeException("Wrong checker value added");
        }
        list.add(checker);
        setType(checker);
    }


}