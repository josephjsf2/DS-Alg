package com.joseph;

public class AvlTree<T extends Comparable<T>> implements Tree<T>{

    private TreeNode<T> root;

    @Override
    public TreeNode findElement(T data) {
        if(getRoot() == null)
            return null;
        return findElement(data, getRoot());
    }
    
    private TreeNode findElement(T data, TreeNode node){
        if(node ==null)
            return null;
        
        if(node.getData().compareTo(data) <0)
            return findElement(data, node.getRightNode());
        else if(node.getData().compareTo(data) >0)
            return findElement(data, node.getLeftNode());
        else return node;
    }

    @Override
    public TreeNode deleteElement(T data) {
        
        if(getRoot() == null)
            return null;
        root = deleteElement(data, getRoot());
        return root;
    }
    
    private TreeNode deleteElement(T data, TreeNode node){
        if(node == null)
            return null;

        if(node.getData().compareTo(data) <0){
            node.setRightNode(deleteElement(data, node.getRightNode()));
        }else if(node.getData().compareTo(data) >0){
            node.setLeftNode(deleteElement(data,node.getLeftNode()));
        }else{
            // case 1: target data is a leaf node
            if(node.getLeftNode() == null && node.getRightNode() == null)
                return null;
            // case 2: target data only has right child
            else if(node.getLeftNode() ==null)
                return node.getRightNode();
            // case 3: target data only has left child
            else if(node.getRightNode() == null)
                return node.getLeftNode();
            // case 4: target data has left and right child
            else{
                // find the node which has the max value on the left subtree
                TreeNode leftMaxValueTreeNode = findMaxValueNode(node.getLeftNode());

                // swap the data
                Comparable tempData = node.getData();
                node.setData(leftMaxValueTreeNode.getData());
                leftMaxValueTreeNode.setData(tempData);

                // delete the data
                node.setLeftNode(deleteElement(data, node.getLeftNode()));
            }
        }
        // update tree height
        updateNodeHeight(node);

        // update tree balance
        return updateTreeBalance(node);

    }
    
    @Override
    public void insertElement(T data) {
        if(this.getRoot() ==null)
            this.root = new TreeNode(data);
        else
            this.root = this.insertElement(data, this.getRoot());
    }

    private TreeNode insertElement(T data, TreeNode currentNode){

        if(currentNode == null)
            return new TreeNode(data);

        if(currentNode.getData().compareTo(data) <= -1)
            currentNode.setRightNode(this.insertElement(data, currentNode.getRightNode()));
        else
            currentNode.setLeftNode(this.insertElement(data, currentNode.getLeftNode()));

        // update node height and balance after inserting the node
        updateNodeHeight(currentNode);
        currentNode = updateTreeBalance(currentNode);

        return currentNode;
    }

    @Override
    public void traverse() {
        System.out.println("Traversing the tree:");
        traverse(getRoot());
    }

    /**
     * Traverse the tree in-order
     * @param node
     */
    private void traverse(TreeNode node){
        if(node == null)
            return;
        traverse(node.getLeftNode());
        System.out.println( " -> " + node.getData());
        traverse(node.getRightNode());
    }

    /**
     * Checking and updating the tree balance starting from the node
     * @param node the root of subtree to be checked
     * @return new root node of the subtree
     */
    private TreeNode updateTreeBalance(TreeNode node){

        int bf = getBalanceFactor(node);
        if(bf >1){
            if(node.getLeftNode()!=null && node.getLeftNode().getLeftNode() !=null){
                // LL type
                return rightRotation(node);
            }else {
                // LR type
                node.setLeftNode(leftRotation(node.getLeftNode()));
                return rightRotation(node);
            }
        }else if(bf < -1){
            if(node.getRightNode()!=null && node.getRightNode().getRightNode() !=null){
                // RR type
                return leftRotation(node);
            }else{
                // RL type
                node.setRightNode(rightRotation(node.getRightNode()));
                return leftRotation(node);
            }
        }

        return node;

    }

    private int getNodeHeight(TreeNode node){
        if(node == null)
            return -1;
        return node.getHeight();
    }

    /**
     * Calculate balance factor of the node
     * @param node
     * @return
     */
    private int getBalanceFactor(TreeNode node){
        if(node == null)
            return 0;

        return getNodeHeight(node.getLeftNode()) - getNodeHeight(node.getRightNode());

    }

    /**
     * Update the height data of the node
     * @param node
     */
    private void updateNodeHeight(TreeNode node){
        if(node == null)
            return;
        node.setHeight( Math.max(getNodeHeight(node.getLeftNode()), getNodeHeight(node.getRightNode())) +1);
    }

    private TreeNode getRoot(){
        return this.root;
    }

    /**
     * Doing right rotation on specified subtree
     * @param node
     * @return new root of subtree
     */
    private TreeNode rightRotation(TreeNode node){
        System.out.println("Perform right rotation on node:" + node.getData());
        // nodes relate to right rotation
        TreeNode leftChild = node.getLeftNode();
        TreeNode tempNode = leftChild.getRightNode();

        // update node reference
        node.setLeftNode(tempNode);
        leftChild.setRightNode(node);

        // update node height, child must update height first
        updateNodeHeight(node);
        updateNodeHeight(leftChild);

        return leftChild;
    }

    /**
     * Doing left rotation on specified subtree
     * @param node
     * @return new root of subtree
     */
    private TreeNode leftRotation(TreeNode node){
        System.out.println("Perform left rotation on node:" + node.getData());
        // nodes relate to right rotation
        TreeNode rightChild =node.getRightNode();
        TreeNode tempNode = rightChild.getLeftNode();

        // update node reference
        node.setRightNode(tempNode);
        rightChild.setLeftNode(node);

        // update node height, child must update height first
        updateNodeHeight(node);
        updateNodeHeight(rightChild);

        return rightChild;
    }

    /**
     * Find the max-value node on specified subtree
     * @param node the root of the tree
     * @return the max-value node
     */
    private TreeNode findMaxValueNode(TreeNode node){
        if(node == null)
            return null;
        if(node.getRightNode() !=null)
            return findMaxValueNode(node.getRightNode());
        return node;
        
    }


}
