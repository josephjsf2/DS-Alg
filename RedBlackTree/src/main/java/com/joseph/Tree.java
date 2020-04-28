package com.joseph;

public interface Tree<T extends Comparable> {

    public TreeNode findElement(T data);

    public void deleteElement(T data);

    public void insertElement(T data);

    public void traverse();
}
