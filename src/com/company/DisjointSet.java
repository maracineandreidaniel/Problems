package com.company;

public class DisjointSet {



    private int n;
    private int[] rank;
    private int[] parent;

    public DisjointSet(int n){
        this.n=n;
        rank=new int[n];
        parent=new int[n];
        initializeDisjointSet();
    }

    private void initializeDisjointSet() {
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int find(int x){
        if(parent[x]==x)
            return x;
        else{
            return find(parent[x]);
        }
    }

    public void union(int x, int y){
        int xRoot=find(x);
        int yRoot=find(y);
        if(xRoot==yRoot)
            return;

        if(rank[xRoot]<rank[yRoot])
            parent[xRoot]=yRoot;
        else if(rank[yRoot]<rank[xRoot])
            parent[yRoot]=xRoot;
        else{
            parent[yRoot]=xRoot;
            rank[xRoot]++;
        }
    }









}
