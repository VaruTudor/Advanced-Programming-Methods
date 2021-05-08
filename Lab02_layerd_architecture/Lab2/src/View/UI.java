package View;

import Controller.Controller;
import Model.Vegetable;
import Repository.Repository;
import Repository.InMemoryRepository;

public class UI {

    private final Controller controller;

    public UI(){
        Repository new_repo = new InMemoryRepository();
        this.controller = new Controller(new_repo);
    }

    public void printMenu(){
        System.out.println("1.add eggplant");
        System.out.println("2.add tomato");
        System.out.println("3.add pepper");
        System.out.println("4.remove vegetable");
        System.out.println("5.filter bigger");
        System.out.println("6.reload");
        System.out.println("0.exit");
    }

    public void addEggplant(double value){
        this.controller.add_eggplant(value);
    }
    public void addPepper(double value){
        this.controller.add_pepper(value);
    }
    public void addTomato(double value){
        this.controller.add_tomato(value);
    }

    public void remove(int index){
        this.controller.remove(index);
    }

    public void filterBigger(double min){
        for(Vegetable v : this.controller.getBiggerWeightThan(min)) {
            try{
                String str = v.veggieToString();
                System.out.println(str);
            }catch (NullPointerException e){
                continue;
            }
        }
    }

}
