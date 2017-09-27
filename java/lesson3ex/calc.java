package lesson3ex;

import java.util.ArrayList;

/**
 * Created by vidok on 20.09.17.
 */
public class calc {

    public static void main(String[] args) {
        BigInteger first = new BigInteger("1101111111111111111111111111111111111111", 1);
        BigInteger second = new BigInteger("890999999999993333333333333333333333333333333333333333333", -1);
        ArrayList<Integer> result = first.ArrSumm(second);
        for(Integer i:result){
            System.out.print(" " + i + " ");
        }
    }

}
