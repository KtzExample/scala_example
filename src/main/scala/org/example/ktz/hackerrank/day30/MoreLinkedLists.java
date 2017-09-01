package org.example.ktz.hackerrank.day30;

import java.io.*;
import java.util.*;

public class MoreLinkedLists {
    static class Node{
        int data;
        Node next;
        Node(int d){
            data=d;
            next=null;
        }

    }


    public static Node removeDuplicates(Node head) {
        return traverseNode(head, head);
    }

    private static Node traverseNode(Node head, Node current) {
        if(current == null)
            return head;
        else {
            current = removeNode(current, current, current.data);
            return traverseNode(head, current.next);
        }
    }

    private static Node removeNode(Node head, Node lastCalculated, int data) {
        if(lastCalculated.next == null)
            return head;
        else if(lastCalculated.next.data == data) {
            lastCalculated.next = lastCalculated.next.next;
            return removeNode(head, lastCalculated, data);
        } else
            return removeNode(head, lastCalculated.next, data);
    }

    public static  Node insert(Node head,int data)
    {
        Node p=new Node(data);
        if(head==null)
            head=p;
        else if(head.next==null)
            head.next=p;
        else
        {
            Node start=head;
            while(start.next!=null)
                start=start.next;
            start.next=p;

        }
        return head;
    }
    public static void display(Node head)
    {
        Node start=head;
        while(start!=null)
        {
            System.out.print(start.data+" ");
            start=start.next;
        }
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Node head=null;
        int T=sc.nextInt();
        while(T-->0){
            int ele=sc.nextInt();
            head=insert(head,ele);
        }
        head=removeDuplicates(head);
        display(head);

    }
}
