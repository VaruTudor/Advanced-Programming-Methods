package Repository;

import Model.Vegetable;

public interface Repository {
    void add(Vegetable new_veggie);
    void remove(int index);

    int size();

    Vegetable getVeggie(int i);
    Vegetable[] getAllVeggies();
}
