package com.joseph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode<T extends Comparable<T>> {

    private T data;
    private TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", leftChild=" + (leftChild == null ? "null" : leftChild.getData()) +
                ", rightChild=" + (rightChild == null ? "null" : rightChild.getData()) +
                ", parent=" + (parent==null ? "null" : parent.getData()) +
                '}';
    }
}