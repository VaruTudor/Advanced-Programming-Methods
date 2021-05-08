package com.company.tudor;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        int[] numbers = new int[20];
        double sum = 0;

        Scanner scanner = new Scanner(System.in);

        int number;
        int index = 0;

        while(true){
            number = scanner.nextInt();
            if (number != 0){
                numbers[index++] = number;
            }
            else{
                break;
            }
        }

        for(int i : numbers) {
            sum+=i;
        }

        System.out.println("Average is " + sum/index);
    }
}
