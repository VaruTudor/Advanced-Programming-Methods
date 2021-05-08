package Repository;

import Exceptions.IndexTooLarge;
import Model.Vegetable;

public class InMemoryRepository implements Repository{

    private Vegetable[] vegetables;
    private int counter;

    public InMemoryRepository(){
        vegetables = new Vegetable[3];
        counter = 0;
    }

    private void resize(){
        Vegetable[] new_array = new Vegetable[this.size()*2];
        System.arraycopy(this.vegetables,0,new_array,0,this.size());
        this.vegetables = new_array;
    }

    public void add(Vegetable new_veggie){

        try{
            this.vegetables[counter] = new_veggie;
            counter++;
        }
        catch (Exception e){
            this.resize();
            this.vegetables[counter++] = new_veggie;
        }
    }

    public void remove(int index){
        if (index < this.size()){
            if (this.size() - index >= 0)
                System.arraycopy(this.vegetables, index + 1, this.vegetables, index, this.size() - index);
            this.counter--;
        }
        else{
            throw new IndexTooLarge();
        }

    }

    public int size(){
        return  counter;
    }

    public Vegetable getVeggie(int i){
//        try{
//            return this.vegetables[i];
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return  this.vegetables[i];
    }

    public Vegetable[] getAllVeggies(){
        return this.vegetables;
    }
}
