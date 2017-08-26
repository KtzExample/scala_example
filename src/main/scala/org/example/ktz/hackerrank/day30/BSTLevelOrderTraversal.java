package org.example.ktz.hackerrank.day30;

import java.util.*;
import java.io.*;

public class BSTLevelOrderTraversal {

    static void printQueue(Queue<Node> queue) {
        if(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + " ");

            if(node.left != null)
                queue.offer(node.left);

            if(node.right != null)
                queue.offer(node.right);

            printQueue(queue);
        }
    }


    static void levelOrder(Node root){
        Queue<Node> rootQueue = new LinkedList<>();
        rootQueue.offer(root);

        printQueue(rootQueue);
    }

    public static Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data<=root.data){
                cur=insert(root.left,data);
                root.left=cur;
            }
            else{
                cur=insert(root.right,data);
                root.right=cur;
            }
            return root;
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        Node root=null;
        while(T-->0){
            int data=sc.nextInt();
            root=insert(root,data);
        }
        levelOrder(root);
    }
}
