package com.joseph;

public interface Tree<T extends Comparable<T>> {

    public TreeNode<T> getRoot();

    public TreeNode<T> findElement(T value);

    public void insertElement(TreeNode<T> newNode);

    public void traverse();

    public void deleteElement(T value);

    public boolean compare(Tree<T> tree);

    public TreeNode<T> getNodeByRank(int rank);

}
