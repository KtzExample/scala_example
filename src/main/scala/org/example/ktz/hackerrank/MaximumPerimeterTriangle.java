package org.example.ktz.hackerrank;

import java.io.*;
import java.util.*;


public class MaximumPerimeterTriangle {
    private static Scanner scan = new Scanner(System.in);

    public static List<Integer> getInput(Integer nInput, List<Integer> intArray) {
        if(nInput == 0)
            return intArray;
        else{
            intArray.add(scan.nextInt());
            return getInput(nInput - 1 , intArray);
        }
    }

    private static boolean isValidTriangle(List<Integer> perimeters) {
        return perimeters.get(0) < perimeters.get(1) + perimeters.get(2);
    }

    private static List<Integer> getValidPerimeters(List<Integer> perimeters) {
        if(perimeters.size() < 3)
            return new ArrayList<>();
        else if(isValidTriangle(perimeters))
            return perimeters.subList(0, 3);
        else
            return getValidPerimeters(perimeters.subList(1, perimeters.size()));
    }


    public static void main(String[] args) {
        List<Integer> perimeters = getInput(scan.nextInt(), new ArrayList<>());

        perimeters.sort((a, b) -> a.compareTo(b) * (-1));

        List<Integer> result = getValidPerimeters(perimeters);
        result.sort(Integer::compareTo);

        if(result.isEmpty())
            System.out.println("-1");
        else {
            result.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }
}
