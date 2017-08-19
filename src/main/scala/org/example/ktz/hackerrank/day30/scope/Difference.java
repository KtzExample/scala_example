package org.example.ktz.hackerrank.day30.scope;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Difference {
    private int[] elements;
    public int maximumDifference;

    public Difference(int[] elements) {
        this.elements = elements;
    }

    public void computeDifference() {
        List<Integer> elementList = new ArrayList<>();

        for(Integer ele : elements) {
            elementList.add(ele);
        }

        maximumDifference = elementList.stream().max(Integer::compare).get() - elementList.stream().min(Integer::compare).get();
    }
}
