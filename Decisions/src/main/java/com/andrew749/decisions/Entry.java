package com.andrew749.decisions;

/**
 * Created by andrew on 3/20/15.
 */
public class Entry {
    public int number=0;
    public String name;
    public Entry(String name){
        this.name=name;
    }
    public void incrementNumber(){
        number++;
    }

    public int getNumber() {
        return number;

   }

    public void setNumber(int number) {
        this.number = number;
    }
    public void clearValue(){
        number=0;
    }
}
