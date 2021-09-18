package com.company;

import java.util.HashMap;
import java.util.Map;

public class NodeTrie {
    private Map<Character, NodeTrie> children=new HashMap<>();
    private boolean word;


    Map<Character, NodeTrie> getChildren(){
        return children;
    }

    public boolean isWord(){
        return word;
    }

    public void setWord(boolean stare){
        word=stare;
    }
}
