package Tests;



import Controller.Controller;
import Model.Eggplant;
import Model.Pepper;
import Model.Tomato;
import Model.Vegetable;
import Repository.InMemoryRepository;

import Repository.Repository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestVegetable {

    @Test
    public void shouldReturnString(){
        Vegetable my_veggie = new Pepper(0.3);
        assertEquals("Pepper of 0.3",my_veggie.veggieToString());
    }

    @Test
    public void shouldAddToRepository(){
        InMemoryRepository new_repo = new InMemoryRepository();
        new_repo.add(new Pepper(0.5));
        new_repo.add(new Pepper(0.5));
        new_repo.add(new Tomato(0.2));
        new_repo.add(new Eggplant(0.3));

        assertEquals(4,new_repo.size());
    }

    @Test
    public void shouldRemoveToRepository(){
        InMemoryRepository new_repo = new InMemoryRepository();
        Vegetable my_pepper = new Pepper(0.7);

        new_repo.add(new Pepper(0.5));
        new_repo.add(my_pepper);
        new_repo.add(new Tomato(0.2));
        new_repo.add(new Eggplant(0.3));
        assertEquals(0.7,new_repo.getVeggie(1).get_weight(),0.01);
        assertEquals(4,new_repo.size());

        new_repo.remove(1);
        assertEquals(3,new_repo.size());
        assertEquals(0.2,new_repo.getVeggie(1).get_weight(),0.01);
        assertNull(new_repo.getVeggie(3));
    }

    @Test
    public void  shouldReturnBiggerThan(){
        InMemoryRepository new_repo = new InMemoryRepository();
        Controller controller = new Controller(new_repo);

        controller.add_eggplant(0.4);
        controller.add_eggplant(0.5);
        controller.add_eggplant(0.6);
        controller.add_eggplant(0.7);

        Vegetable[] vegetables = controller.getBiggerWeightThan(0.5);


        assertEquals(vegetables[0].get_weight(),0.6,0.01);
        assertEquals(vegetables[1].get_weight(),0.7,0.01);

    }

    @Test
    public void testRun(){
        Repository repository = new InMemoryRepository();
        Controller controller = new Controller(repository);

        // check add
        for (int i=0; i<1000; i++){
            switch (i % 3) {
                case (0) -> controller.add_tomato(i);
                case (1) -> controller.add_pepper(i);
                case (2) -> controller.add_eggplant(i);
            }
        }


        assertEquals(1000,repository.size());


        // check filter
        assertEquals(500,controller.getBiggerWeightThan(499).length);

        // check remove
        for (int i=0; i<600; i=i+2){
            controller.remove(i);
        }


        assertEquals(700,repository.size());
    }

}
