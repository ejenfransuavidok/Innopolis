package com.crossover.trial;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Tester {
	
    /**
     * Complete the function below.
     */
    static int[] rearrange(int[] elements) 
    {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<elements.length; i++) {
            map.put(Integer.bitCount(elements[i]), elements[i]);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        int[] ret = new int[result.size()];
        for (int i=0; i < ret.length; i++) {
            ret[i] = result.get(i).intValue();
        }
        return ret;
    }

    /**
     * DO NOT MODIFY THIS METHOD!
     */
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(System.in);
        int[] res;
        
        int _elements_size = 0;
        _elements_size = Integer.parseInt(in.nextLine().trim());
        int[] _elements = new int[_elements_size];
        int _elements_item;
        for(int _elements_i = 0; _elements_i < _elements_size; _elements_i++) {
            _elements_item = Integer.parseInt(in.nextLine().trim());
            _elements[_elements_i] = _elements_item;
        }

        res = rearrange(_elements);
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.println(String.valueOf(res[res_i]));
        }        
    }
}