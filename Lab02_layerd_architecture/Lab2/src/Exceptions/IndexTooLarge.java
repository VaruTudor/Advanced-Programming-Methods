package Exceptions;

public class IndexTooLarge extends RuntimeException{

    @Override
    public String toString () {

        return "There is no item with the index u tried to use";
    }
}
