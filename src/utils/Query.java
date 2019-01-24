package utils;

import graph.State;

import java.util.Random;

public class Query {
    private int i, j;
    private Random rand;
    public Query(int i, int j){
        this.i = i;
        this.j = j;
        rand = new Random();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public int hashCode(){
        return this.i * this.j * rand.nextInt(Config.N);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Query query = (Query) obj;
        if(query.getJ() == this.j && query.getI() == this.i){
            return true;
        }
        return false;
    }
}
