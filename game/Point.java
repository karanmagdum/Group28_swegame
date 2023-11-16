package game;

import java.util.Stack;

public class Point{
    Stack<String> list= new Stack<>();
    private int num = 0;

    public void add(String checker){
        if(!(checker.equals("O") || checker.equals("X") || checker.equals("="))){
            throw new RuntimeException("Wrong checker value added");
        }
        list.add(checker);
    }


}