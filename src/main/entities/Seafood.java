package entities;

public class Seafood extends Material{
    public Seafood(String name, boolean usedup, double price, double quantity, String freshness,
                   int ImportDate){
        super(name, usedup, price, quantity, freshness, ImportDate);
        this.freshness = freshness;
        this.ImportDate = ImportDate;
    }


}

