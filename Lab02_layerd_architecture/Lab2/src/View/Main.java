package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

//    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        UI ui = new UI();

        int choiceIndex;
        double value;


        while(true){
            try {
                ui.printMenu();

                // !if the scanner is not created at each iteration, it would get blocked if someone would give it a different type
                // and won't attempt to read user input again
                Scanner scanner = new Scanner(System.in);

                System.out.print("> ");
                choiceIndex = scanner.nextInt();
                switch (choiceIndex) {
                    default -> System.out.println("The number you introduced is not part of the options");
                    case 0 -> System.exit(0);
                    case 1 -> {
                        System.out.println("weighting: ");
                        value = scanner.nextDouble();
                        ui.addEggplant(value);
                    }
                    case 2 -> {
                        System.out.println("weighting: ");
                        value = scanner.nextDouble();
                        ui.addTomato(value);
                    }
                    case 3 -> {
                        System.out.println("weighting: ");
                        value = scanner.nextDouble();
                        ui.addPepper(value);
                    }
                    case 4 -> {
                        System.out.println("which index: ");
                        int indexValue = scanner.nextInt();
                        ui.remove(indexValue);
                    }
                    case 5 -> {
                        System.out.println("minimum weight: ");
                        value = scanner.nextDouble();
                        ui.filterBigger(value);
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("U did not give an integer");
            }
        }


    }
}
