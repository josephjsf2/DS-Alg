package com.joseph;

public class App {

    public static void main(String[] args) {
        Tree splayTree = new SplayTree();
        splayTree.insertElement(10);
        splayTree.insertElement(20);
        splayTree.insertElement(30);
        splayTree.insertElement(40);
        splayTree.insertElement(50);
        splayTree.traverse();

        splayTree.findElement(20);
        splayTree.traverse();

        splayTree.findElement(10);
        splayTree.traverse();

        splayTree.findElement(30);
        splayTree.traverse();

        splayTree.deleteElement(10);
        splayTree.traverse();
        splayTree.deleteElement(40);
        splayTree.traverse();
    }
}
