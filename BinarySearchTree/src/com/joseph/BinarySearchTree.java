package com.joseph;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private TreeNode<T> root;

    @Override
    public TreeNode<T> getRoot() {
        return this.root;
    }

    @Override
    public TreeNode<T> findElement(T value) {
        if(this.getRoot() == null)
            return null;

        return this.findElement(this.getRoot(), value);
    }

    private TreeNode<T> findElement(TreeNode<T> currentNode, T value){
        if(currentNode.getData().compareTo(value) ==0)
            return currentNode;
        else if(currentNode.getData().compareTo(value) <0)
            return this.findElement(currentNode.getLeftNode(), value);
        else
            return this.findElement(currentNode.getRightNode(), value);
    }

    @Override
    public void insertElement(TreeNode<T> newNode) {

        if(this.getRoot() == null)
            this.root = newNode;
        else
            this.insertElement(this.getRoot(), newNode);
    }


    private void insertElement(TreeNode<T> currentNode, TreeNode<T> newNode){

        if(newNode.getData().compareTo((currentNode.getData()))>=0){
            if(currentNode.getRightNode()!=null)
                this.insertElement(currentNode.getRightNode(), newNode);
            else
                currentNode.setRightNode(newNode);
        }else if(newNode.getData().compareTo(currentNode.getData())<0){

            if(currentNode.getLeftNode() !=null)
                this.insertElement(currentNode.getLeftNode(), newNode);
            else
                currentNode.setLeftNode(newNode);
        }
    }

    @Override
    public void traverse() {
        this.traverse(this.getRoot());
    }

    private void traverse(TreeNode currentNode){
        if(currentNode == null)
            return;
        // Traversing in in-order
        this.traverse(currentNode.getLeftNode());
        System.out.println(currentNode);
        this.traverse(currentNode.getRightNode());

    }

    @Override
    public void deleteElement(T value) {
        if(this.getRoot() ==null)
            return;

        this.deleteElement(this.getRoot(), value);
    }

    private TreeNode deleteElement(TreeNode<T> currentNode, T value){

        if(currentNode == null)
            return null;

        if(currentNode.getData().compareTo(value) <0){
            currentNode.setRightNode(this.deleteElement(currentNode.getRightNode(), value));
        }else if(currentNode.getData().compareTo(value) >0){
            currentNode.setLeftNode(this.deleteElement(currentNode.getLeftNode(), value));
        }else {
            // Found

            // leaf node
            if(currentNode.getRightNode() == null && currentNode.getLeftNode() == null)
                return null;
            else if(currentNode.getRightNode() ==null) {    // one side contains children
                TreeNode<T> tempNode = currentNode.getLeftNode();
                return tempNode ;
            }else if(currentNode.getLeftNode() ==null) {
                TreeNode<T> tempNode = currentNode.getRightNode();
                return tempNode;
            }else {
                // Children on both side
                TreeNode<T> tempNode = getMax(currentNode.getLeftNode());
                currentNode.setData(tempNode.getData());
                currentNode.setLeftNode(deleteElement(currentNode.getLeftNode(), currentNode.getData()));
            }

        }
        return currentNode;
    }

    private TreeNode<T> getMax(TreeNode<T> currentNode){
        if(currentNode ==null)
            return null;
        if(currentNode.getRightNode() !=null)
            return getMax(currentNode.getRightNode());
        return currentNode;
    }

    @Override
    public boolean compare(Tree<T> tree) {
       if(this.getRoot() !=null && tree.getRoot() !=null) {
           return this.compare(this.getRoot(), tree.getRoot());
       }
       return false;
    }

    private boolean compare(TreeNode<T> node1, TreeNode<T> node2){
        // Node data相同且左右 children皆相同
        if(node1 !=null && node2 !=null){
                return node1.getData().compareTo(node2.getData()) ==0
                        && compare(node1.getLeftNode(), node2.getLeftNode())
                        && compare(node1.getRightNode(), node2.getRightNode());
        }
        return node1 == node2;
    }

    public TreeNode<T> getNodeByRank(int rank){
        if(this.getRoot() == null)
            return null;
        return this.getNodeByRank(this.getRoot(), rank);
    }

    private TreeNode<T> getNodeByRank(TreeNode<T> node, int rank){

        // left children數量 再加上 current node
        int leftNodeSize = this.getLeftNodeSize(node.getLeftNode()) +1;

        // Found
        if(leftNodeSize == rank)
            return node;

        // 目標大於當前left node size，則找right children
        if(rank > leftNodeSize)
            return getNodeByRank(node.getRightNode(), rank - leftNodeSize);
        else
            return getNodeByRank(node.getLeftNode(), rank);
    }

    private int getLeftNodeSize(TreeNode<T> currentNode){
        if(currentNode==null)
            return 0;
        return getLeftNodeSize(currentNode.getLeftNode()) + getLeftNodeSize(currentNode.getRightNode()) +1;

    }

}
