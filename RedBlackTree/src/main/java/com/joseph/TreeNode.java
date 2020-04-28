package com.joseph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode<T extends Comparable> {

    private T data;
    private TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private NodeColor color;

    public TreeNode(T data) {
        this.data = data;
        this.color = NodeColor.RED;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", leftChild=" + (leftChild==null? null : leftChild.getData()) + "("+ (leftChild==null ? "" : leftChild.getColor())+")" +
                ", rightChild=" + (rightChild == null ? null : rightChild.getData()) +  "("+ (rightChild==null ? "" : rightChild.getColor())+")" +
                ", parent:" + (parent==null ? "null" : parent.getData()) +
                ", color=" + color +
                '}';
    }
}
