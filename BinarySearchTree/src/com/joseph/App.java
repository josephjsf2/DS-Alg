package com.joseph;

public class App {
    public static void main(String[] args) {

        Tree<String> tree =new BinarySearchTree<>();

        tree.insertElement(new TreeNode<>("Micheal"));
        tree.insertElement(new TreeNode<>("Jimmy"));
        tree.insertElement(new TreeNode<>("Lucy"));
        tree.insertElement(new TreeNode<>("Louis"));
        tree.insertElement(new TreeNode<>("Tina"));
        tree.insertElement(new TreeNode<>("Andy"));
        tree.insertElement(new TreeNode<>("Tom"));
        tree.insertElement(new TreeNode<>("Cathy"));
        tree.insertElement(new TreeNode<>("Dennis"));
        tree.insertElement(new TreeNode<>("Stanley"));
        tree.insertElement(new TreeNode<>("Micky"));
        tree.traverse();
        tree.deleteElement("Jimmy");
        tree.deleteElement("Tina");

        tree.traverse();

        System.out.println(tree.getNodeByRank(5));


    }
}
