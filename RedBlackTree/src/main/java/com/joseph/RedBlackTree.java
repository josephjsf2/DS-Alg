package com.joseph;

public class RedBlackTree<T extends Comparable> implements  Tree<T>{
    private TreeNode root;
    @Override
    public TreeNode findElement(T data) {
        if(this.root!=null)
            return this.findElement(this.root, data);
        return null;
    }

    private TreeNode findElement(TreeNode node, T data){
        if(node ==null)
                return null;

        else if(node.getData().compareTo(data) >0){
            return this.findElement(node.getLeftChild(), data);
        }else if(node.getData().compareTo(data) <0){
            return this.findElement(node.getRightChild(), data);
        }
        return node;
    }

    @Override
    public void deleteElement(T data) {

        TreeNode z = this.findElement(data); //要刪除的資料
        if(z ==null)
            return;
        TreeNode y = z;  // 刪除node後，要補上的節點
        TreeNode x=null;    //要取代原本 Y 位置的節點

        //  最後 x 的parent，目的在於 x 可能是 null，但fixup需要 x的 parent 資訊
        TreeNode parentOfX = null;

        NodeColor successorOriginColor = y.getColor();  // 儲存 Y 原始顏色

        if(z.getLeftChild() == null){
            x = z.getRightChild();
            parentOfX = z.getParent();
            transplant(z, z.getRightChild());

            if(parentOfX==null)
                this.root = x;
        }else if(z.getRightChild() ==null){
            x = z.getLeftChild();
            parentOfX = z.getParent();

            if(parentOfX==null)
                this.root = x;
            transplant(z, z.getLeftChild());
        }else{
            // z 左右節點都不是 null
            y = this.getMin(z.getRightChild()); // 取得 Z 的 successor
            successorOriginColor = y.getColor();
            x = y.getRightChild();

            if(y.getParent() == z) {
                // Y是Z的child
                parentOfX = y;
                if(x!=null)
                    x.setParent(y);
            }else {
                parentOfX = y.getParent();
                // 將 X 取代 Y
                transplant(y, y.getRightChild());

                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }
            transplant(z, y); // 將 Y 取代 Z
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());

            if(y.getParent() == null)
                this.root = y;
        }

        // 如果 Y 原始顏色是黑色，才進行 FIXUP
        if(successorOriginColor == NodeColor.BLACK){
            deleteFixup(x, parentOfX);
        }
    }

    private void deleteFixup(TreeNode x, TreeNode parent){
        while(x!= this.root && (x== null || x.getColor() == NodeColor.BLACK )){

            TreeNode w;	// sibling
            // 如果 x 是在 parent的左邊
            if(parent.getLeftChild() == x){
                w = parent.getRightChild();

                // case I： sibling 是紅色節點，會將 tree調整為 case II III IV 其中一種
                if(w !=null && w.getColor() == NodeColor.RED){
                    System.out.println("CASE I");
                    w.setColor(NodeColor.BLACK);
                    parent.setColor(NodeColor.RED);
                    leftRotation(parent);
                    w = parent.getRightChild();
                }

                // case II： sibling 是黑色且 sibling 兩個 child都是黑色
                if( (w.getRightChild()== null || w.getRightChild().getColor()== NodeColor.BLACK) ||
                        (w.getLeftChild()== null || w.getLeftChild().getColor() == NodeColor.BLACK )){
                    System.out.println("CASE II");
                    w.setColor(NodeColor.RED);
                    x = x.getParent();	// 重新指定 x，在下一個迴圈再依據新的 case 調整
                    parent = x.getParent();
                }else{
                    // case III：sibling是黑色，且sibling 左邊child是紅色，右邊 child是黑色
                    if(w.getRightChild() == null ||w.getRightChild().getColor() == NodeColor.BLACK){
                        System.out.println("CASE III");
                        w.getLeftChild().setColor(NodeColor.BLACK);
                        w.setColor(NodeColor.RED);
                        rightRotation(w);
                        w = parent.getRightChild();
                    }

                    // case IV：sibling是黑色，且sibling 右邊child是紅色
                    System.out.println("CASE IV");
                    w.setColor(parent.getColor());
                    parent.setColor(NodeColor.BLACK);
                    w.getRightChild().setColor(NodeColor.BLACK);
                    leftRotation(parent);
                    x = this.root; //調整完成，直接指向 root離開迴圈
                }
            }else{
                w = parent.getLeftChild();
                // case I： sibling 是紅色節點，會將 tree調整為 case II III IV 其中一種
                if(w != null && w.getColor() == NodeColor.RED){
                    System.out.println("CASE I");
                    w.setColor(NodeColor.BLACK);
                    parent.setColor(NodeColor.RED);
                    rightRotation(parent);
                    w = parent.getLeftChild();
                }

                // case II： sibling 是黑色且 sibling 兩個 child都是黑色
                if( (w.getLeftChild() == null || w.getLeftChild().getColor()==NodeColor.BLACK) &&
                        (w.getRightChild() == null || w.getRightChild().getColor() == NodeColor.BLACK)){
                    System.out.println("CASE II");
                    w.setColor(NodeColor.RED);
                    x = parent; // 重新指定 x，在下一個迴圈再依據新的 case 調整
                    parent = x.getParent();
                }else {
                    // case III：sibling是黑色，且sibling 左邊child是紅色，右邊 child是黑色
                    if(w.getLeftChild() == null ||w.getLeftChild().getColor() == NodeColor.BLACK){
                        System.out.println("CASE III");
                        w.getRightChild().setColor(NodeColor.BLACK);
                        w.setColor(NodeColor.RED);
                        leftRotation(w);
                        w = parent.getLeftChild();
                    }
                    // case IV：sibling是黑色，且sibling 右邊child是紅色
                    System.out.println("CASE IV");
                    w.setColor(parent.getColor());
                    parent.setColor(NodeColor.BLACK);
                    w.getLeftChild().setColor(NodeColor.BLACK);
                    rightRotation(parent);
                    x = this.root; //調整完成，直接指向 root離開迴圈
                }
            }
        }

        x.setColor(NodeColor.BLACK);

    }

    private void transplant(TreeNode originNode, TreeNode newNode){
        if(originNode.getParent()==null)
            this.root = newNode;
        else if(originNode.getParent().getLeftChild() == originNode)
            originNode.getParent().setLeftChild(newNode);
        else
            originNode.getParent().setRightChild(newNode);
        if(newNode !=null)
            newNode.setParent(originNode.getParent());
    }

    private TreeNode getMin(TreeNode node){
        if(node.getLeftChild()!=null)
            return getMin(node.getLeftChild());
        return node;
    }

    @Override
    public void insertElement(T data) {
        TreeNode node = new TreeNode(data);
        this.root = this.insert(root, node);
        fixInsert(node);
    }

    private TreeNode insert(TreeNode root, TreeNode newNode){

        if(root == null)
            return newNode;

        if(root.getData().compareTo(newNode.getData()) >=0){
            root.setLeftChild(this.insert(root.getLeftChild(), newNode));
            root.getLeftChild().setParent(root);
        }else{
            root.setRightChild(insert(root.getRightChild(), newNode));
            root.getRightChild().setParent(root);
        }
        return root;
    }

    @Override
    public void traverse(){
        if(this.root !=null)
            this.traverse(root);
    }

    private void traverse(TreeNode node) {
        if(node.getLeftChild()!=null)
            this.traverse(node.getLeftChild());
        System.out.println(node.toString());
        if(node.getRightChild() !=null)
            this.traverse(node.getRightChild());
    }

    private void rightRotation(TreeNode node){
        System.out.println("Rotating to right on the Node: " + node);
        TreeNode tempRight  = node.getLeftChild().getRightChild();
        TreeNode tempLeft = node.getLeftChild();

        node.setLeftChild(tempRight);
        if(tempRight !=null)
            tempRight.setParent(node);

        // 設定 node 的 parent 指向之 node
        if(node.getParent() == null)
            root = tempLeft;
        else if(node.getParent().getLeftChild() == node)
            node.getParent().setLeftChild(tempLeft);
        else
            node.getParent().setRightChild(tempLeft);

        tempLeft.setParent(node.getParent());
        tempLeft.setRightChild(node);
        node.setParent(tempLeft);
    }

    private void leftRotation(TreeNode node){
        System.out.println("Rotating to left on the Node: " + node);
        TreeNode tempLeft = node.getRightChild().getLeftChild();
        TreeNode tempRight = node.getRightChild();
        node.setRightChild(tempLeft);
        if(tempLeft !=null)
            tempLeft.setParent(node);

        // 設定 node 的 parent 指向之 node
        if(node.getParent() == null)
            root = tempRight;
        else if(node.getParent().getRightChild() == node)
            node.getParent().setRightChild(tempRight);
        else
            node.getParent().setLeftChild(tempRight);

        tempRight.setLeftChild(node);
        tempRight.setParent(node.getParent());
        node.setParent(tempRight);
    }

    private void fixInsert(TreeNode node){
        while(node!= this.root && node.getParent().getColor() == NodeColor.RED){
            TreeNode parent = node.getParent();
            TreeNode grandParent = parent.getParent();
            TreeNode uncle = null;

            // left side
            if(parent == grandParent.getLeftChild()){
                uncle = grandParent.getRightChild();
                // case I
                if(uncle!=null && uncle.getColor() == NodeColor.RED){
                    uncle.setColor(NodeColor.BLACK);
                    parent.setColor(NodeColor.BLACK);
                    grandParent.setColor(NodeColor.RED);
                    // now focus on grandparent node
                    node = grandParent;
                }else {
                    // case II
                    if(node == parent.getRightChild()){
                        leftRotation(parent);
                        parent = node;
                        node = parent.getParent();
                    }
                    // case III
                    grandParent.setColor(NodeColor.RED);
                    parent.setColor(NodeColor.BLACK);
                    rightRotation(grandParent);
                }
            }else {
                uncle = grandParent.getLeftChild();
                // case I
                if(uncle !=null && uncle.getColor() == NodeColor.RED){
                    parent.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    grandParent.setColor(NodeColor.RED);
                    // now focus on grandparent node
                    node = grandParent;
                }else {
                    if(node == parent.getLeftChild()){
                        // case II
                        rightRotation(parent);
                        parent = node;
                        node = parent.getParent();
                    }
                    // case III
                    grandParent.setColor(NodeColor.RED);
                    parent.setColor(NodeColor.BLACK);
                    leftRotation(grandParent);
                }
            }
        }
        this.root.setColor(NodeColor.BLACK);
    }
}
