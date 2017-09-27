package lesson3ex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by vidok on 20.09.17.
 */
public class BigInteger {

    ArrayList<Integer> data;
    Integer sign;

    public BigInteger(String number, int s){
        data = new ArrayList<>();
        //data = Collections.unmodifiableList (data);
        sign = s;
        if(number.length() > 0){
            for(int i=0; i<number.length(); i++) {
                data.add(Character.getNumericValue(number.charAt(i)));
            }
        }
    }

    ArrayList<Integer> getData() { return data; }

    public int sign(Integer num){
        return num < 0 ? -1 : 1;
    }

    public ArrayList ArrSumm(BigInteger rhs){
        ArrayList<Integer> up = data.size() > rhs.data.size() ? data : rhs.data;
        ArrayList<Integer> dw = up == data ? rhs.data : data;
        Integer ups = data.size() > rhs.data.size() ? sign : rhs.sign;
        Integer dws = up == data ? rhs.sign : sign;

        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<=up.size(); i++) {
            result.add(0);
        }
        int j = dw.size() - 1;
        int i;
        Integer carry = 0;
        for(i=up.size() - 1; i>=0 && j>=0; i--, j--){
            Integer u = ups*up.get(i);
            Integer d = dws*dw.get(j);
            result.set(i + 1, (u+d+carry) % 10);
            carry = (u+d) / 10;
        }
        for(;i>=0;i--) {
            Integer u = ups*up.get(i) + carry;
            carry = 0;
            result.set(i + 1, u);
        }
        result.set(0, carry);
        /**
         * коррекция
         * итоговый знак @ ups
         */
        carry = 0;
        for(i=result.size() - 1; i>0; i--){
            Integer u = result.get(i) + carry;
            if((sign(u) != sign(ups)) && (u != 0)) {
                u = u + sign(ups)*10;
                carry = -sign(ups);
            }
            result.set(i, u);
        }

        System.out.println("sign : " + ups);

        return result;
    }

}
