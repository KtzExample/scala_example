package org.example.ktz.hackerrank.linkedlist;
import java.io.*;
import java.util.*;

public class LinkedList {

    public static  Node insert(Node head,int data) {
        if(head == null)
            return new Node(data);
        else {
            insertToLast(head, data);

            return head;
        }
    }

    private static void insertToLast(Node pointer, int data) {
        if(pointer.next == null)
            pointer.next = new Node(data);
        else
            insertToLast(pointer.next, data);
    }

    public static void display(Node head) {
        Node start = head;
        while(start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        int N = sc.nextInt();

        while(N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head,ele);
        }
        display(head);
        sc.close();
    }
}
