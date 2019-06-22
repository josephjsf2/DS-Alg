package com.joseph;

public class App {
    public static void main(String[] args) {
        Tree<String> tree = new AvlTree<>();
        tree.insertElement("APR");
        tree.insertElement("AUG");
        tree.insertElement("DEC");
        tree.insertElement("FEB");
        tree.insertElement("JAN");
        tree.insertElement("JULY");
        tree.insertElement("JUNE");
        tree.insertElement("MAR");
        tree.insertElement("MAY");
        tree.insertElement("NOV");
        tree.insertElement("OCT");
        tree.insertElement("SEPT");
        tree.traverse();
        tree.deleteElement("APR");
        tree.deleteElement("JAN");
        tree.deleteElement("FEB");
        tree.traverse();
    }
}
