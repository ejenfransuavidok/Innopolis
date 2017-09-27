package aiteco;

/**
 * Created by vidok on 22.09.17.
 */
public class Task2 {

    public static void printEvenNumbers(int number){
        if(number == 0 || number < 0)
            return;
        else if(number % 2 == 0){
            System.out.println(number);
        }
        printEvenNumbers(--number);
    }

    public static void main(String[] args) {
        printEvenNumbers(100);
    }

}
