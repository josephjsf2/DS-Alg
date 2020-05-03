package com.joseph;

public class SplayTree implements Tree{

    private TreeNode root;

    public TreeNode findElement(Comparable data) {
        TreeNode target = findElement(root, data);
        splay(target);
        return target;
    }

    private TreeNode findElement(TreeNode root, Comparable data){
        if(root == null)
            return null;
        if(root.getData().compareTo(data)>0)
            return findElement(root.getLeftChild(), data);
        else if(root.getData().compareTo(data) < 0)
            return findElement(root.getRightChild(), data);
        else
            return root;
    }

    public void deleteElement(Comparable data) {
        TreeNode target = findElement(data);

        if(target.getRightChild() == null){
            // 右邊沒 subtree，讓左邊 subtree變成新的 tree
            this.root = target.getLeftChild();
        }else if(target.getLeftChild() == null){
            // 左邊沒 subtree，讓右邊 subtree變成新的 tree
            this.root = target.getRightChild();
        }else {
            // 兩邊皆有 subtree，拆為左右兩個不同的subtree
            TreeNode leftSubtree  = target.getLeftChild();
            TreeNode rightSubtree = target.getRightChild();

            // 找出右邊subtree最小節點
            TreeNode rightSubtreeMinNode = getMin(rightSubtree);
            splay(rightSubtreeMinNode); // splay完變為root且左邊會沒有節點
            rightSubtree.setLeftChild(leftSubtree);
            leftSubtree.setParent(rightSubtreeMinNode);
            this.root = rightSubtreeMinNode;
        }
        this.root.setParent(null);

    }

    public TreeNode getMin(TreeNode root){
        if(root.getLeftChild() == null)
            return root;
        return getMin(root.getLeftChild());
    }

    public void insertElement(Comparable data) {
        TreeNode newNode = new TreeNode(data);
        if(root == null)
            root = newNode;
        else
            root = insert(root, newNode);

        splay(newNode);
    }

    private TreeNode insert(TreeNode root, TreeNode newNode){
        if(root == null)
            return newNode;
        else if(root.getData().compareTo(newNode.getData()) >0) {
            root.setLeftChild(insert(root.getLeftChild(), newNode));
            root.getLeftChild().setParent(root);
        }else {
            root.setRightChild(insert(root.getRightChild(), newNode));
            root.getRightChild().setParent(root);
        }
        return root;
    }

    public void traverse() {
        if(root == null)
            return;
        System.out.println("Now root is " + root.getData());
        traverse(root);
    }

    private void traverse(TreeNode node){
        if(node.getLeftChild()!=null)
            traverse(node.getLeftChild());
        System.out.println(node);
        if(node.getRightChild()!=null)
            traverse(node.getRightChild());
    }

    private void splay(TreeNode x){
        while(x.getParent()!=null) {
            // Zig
            if (x.getParent().getParent() == null) {
                if (x.getParent().getLeftChild() == x)
                    rightRotation(x.getParent());
                else
                    leftRotation(x.getParent());
            } else if (x.getParent().getParent().getLeftChild() == x.getParent() && x.getParent().getLeftChild() == x) {
                //  X 位於左子節點的左子節點 ZIG - ZIG
                rightRotation(x.getParent().getParent());
                rightRotation(x.getParent());
            } else if (x.getParent().getParent().getRightChild() == x.getParent() && x.getParent().getRightChild() == x) {
                //  X 位於右子節點的右子節點 ZIG- ZIG
                leftRotation(x.getParent());
                leftRotation(x.getParent());
            } else if (x.getParent().getParent().getLeftChild() == x.getParent() && x.getParent().getRightChild() == x) {
                //  X 位於左子節點的右子節點 ZIG - ZAG
                leftRotation(x.getParent());
                rightRotation(x.getParent());
            }else{
                //  X 位於右子節點的左子節點 ZIG - ZAG
                rightRotation(x.getParent());
                leftRotation(x.getParent());
            }
        }
    }

    private void leftRotation(TreeNode node){
        TreeNode rightChild = node.getRightChild();
        TreeNode tempLeftChild = rightChild.getLeftChild();

        TreeNode parent = node.getParent();

        if(parent !=null) {
            if (node == parent.getLeftChild())
                parent.setLeftChild(rightChild);
            else
                parent.setRightChild(rightChild);
        }else
            root = rightChild;   //設定新 ROOT
        rightChild.setParent(parent);
        node.setParent(rightChild);

        rightChild.setLeftChild(node);
        node.setRightChild(tempLeftChild);
        if(tempLeftChild!=null)
            tempLeftChild.setParent(node);

    }

    private void rightRotation(TreeNode node){
        TreeNode leftChild = node.getLeftChild();
        TreeNode tempRightChild = leftChild.getRightChild();

        TreeNode parent = node.getParent();

        if(parent !=null) {
            if (node == parent.getLeftChild())
                parent.setLeftChild(leftChild);
            else
                parent.setRightChild(leftChild);
        }else
            root = leftChild;   //設定新 ROOT
        leftChild.setParent(parent);
        node.setParent(leftChild);

        leftChild.setRightChild(node);
        node.setLeftChild(tempRightChild);
        if(tempRightChild!=null)
            tempRightChild.setParent(node);

    }
}
