package Model;

public class Tomato implements Vegetable{

    private double weight;

    //default constructor
    @SuppressWarnings("unused")
    public Tomato(){
        this.weight = 0.0;
    }

    public Tomato(double new_weight){
        this.set_weight(new_weight);
    }

    public double get_weight(){
        return this.weight;
    }

    public void set_weight(double new_weight){
        this.weight = new_weight;
    }

    public String veggieToString(){
        return ("Tomato of " + this.weight);
    }
}
