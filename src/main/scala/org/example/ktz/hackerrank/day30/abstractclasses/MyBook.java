package org.example.ktz.hackerrank.day30.abstractclasses;

public class MyBook extends Book{

    private final Integer price;

    MyBook(String title, String author, Integer price) {
        super(title, author);
        this.price = price;
    }

    @Override
    void display() {
        System.out.println(
                "Title: " + title + "\n" +
                        "Author: " + author + "\n" +
                        "Price: " + price
        );
    }
}
