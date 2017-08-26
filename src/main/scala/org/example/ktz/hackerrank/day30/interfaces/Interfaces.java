package org.example.ktz.hackerrank.day30.interfaces;

import java.io.*;
import java.util.*;

interface AdvancedArithmetic{
    int divisorSum(int n);
}

class Calculator implements AdvancedArithmetic {

    private List<Integer> loop(Integer target, Integer divider, List<Integer> acc) {
        if(target < divider)
            return acc;
        else {
            List<Integer> newAcc = acc;
            if(target % divider == 0)
                newAcc.add(divider);

            return loop(target, divider + 1, newAcc);
        }
    }

    @Override
    public int divisorSum(int n) {
        return loop(n, 1, new ArrayList<>()).stream().mapToInt(Integer::intValue).sum();
    }
}

public class Interfaces {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.close();

        AdvancedArithmetic myCalculator = new Calculator();
        int sum = myCalculator.divisorSum(n);
        System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName() );
        System.out.println(sum);
    }
}
