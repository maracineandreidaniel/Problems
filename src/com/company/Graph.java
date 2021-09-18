package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int v;
    private LinkedList<Integer>[] adjacents;

    public Graph(int v) {
        this.v=v;
        adjacents=new LinkedList[v];
        for(int i=0;i<v;i++){
            adjacents[i]=new LinkedList<>();
        }
    }


    public void addVertex(int v, int e){
        adjacents[v].add(e);
    }


    public void BFS(int start){
        boolean visited[]=new boolean[v];
        LinkedList<Integer> queue=new LinkedList<>();
        visited[start]=true;
        queue.add(start);
        while(!queue.isEmpty()) {
            start = queue.poll();
            System.out.println(start+" ");
            Iterator<Integer> i=adjacents[start].listIterator();
            while(i.hasNext()){
                int n=i.next();
                if(!visited[n]){
                    visited[n]=true;
                    queue.add(n);
                 }
            }
        }
    }
}
