package Controller;

import Exceptions.IndexTooLarge;
import Model.Eggplant;
import Model.Pepper;
import Model.Tomato;
import Model.Vegetable;
import Repository.Repository;

public class Controller {

    private final Repository repository;

    public Controller(Repository new_repo){
        this.repository = new_repo;
    }

    public void add_tomato(double tomato_weight){
        this.repository.add(new Tomato(tomato_weight));
    }
    public void add_pepper(double pepper_weight){
        this.repository.add(new Pepper(pepper_weight));
    }
    public void add_eggplant(double eggplant_weight){
        this.repository.add(new Eggplant(eggplant_weight));
    }

    public void remove(int index){
        try {
            this.repository.remove(index);
        }catch (IndexTooLarge e){
            System.out.println(e.toString());
        }
    }

    public Vegetable[] getBiggerWeightThan(double weight){
        int vegetablesCurrent = 0;
        int sizeOfNewArray = 0;

        // determining the size of the new array
        for(int i=0; i<this.repository.size(); i++){
            if(this.repository.getVeggie(i).get_weight() > weight){
                sizeOfNewArray++;
            }
        }

        Vegetable[] vegetables = new Vegetable[sizeOfNewArray];

        for(int i=0; i<this.repository.size(); i++){
            if(this.repository.getVeggie(i).get_weight() > weight){
                vegetables[vegetablesCurrent++] = this.repository.getVeggie(i);
            }
        }

        return vegetables;
    }

}
