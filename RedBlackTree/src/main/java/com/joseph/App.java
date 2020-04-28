package com.joseph;

public class App {
    public static void main(String[] args) {
        // SOME TEST CASE FROM EXAMPLE
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
    }

    public static void test1(){
        Tree rbTree = generateRBTree();
        rbTree.deleteElement(6);
        rbTree.traverse();
    }

    public static void test2(){
        Tree rbTree = generateRBTree();
        rbTree.deleteElement(1);
        rbTree.traverse();
        // expect 6 to be black
    }

    public static void test3(){
        Tree rbTree = generateRBTree();
        rbTree.deleteElement(17);
        rbTree.traverse();
        // 22 移至 25 PARENT 且顏色為 RED
    }

    public static void test4(){
        Tree rbTree = generateRBTree();
        rbTree.deleteElement(25);
        rbTree.traverse();
        // 27(BLACK) LEFT CHILD -> 22(RED)
    }

    public static void test5(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(7);
        rbTree.insertElement(3);
        rbTree.insertElement(18);
        rbTree.insertElement(10);
        rbTree.insertElement(22);
        rbTree.insertElement(8);
        rbTree.insertElement(11);
        rbTree.insertElement(26);
        rbTree.deleteElement(18);
        rbTree.traverse();
        // 22 R; 10LB; 26RB     10 LEFT BLACK; 26 RIGHT BLACK
        // 10B; 8LR; 11RR
        // 26B' NULL L; NULL R
    }

    public static void test6(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(5);
        rbTree.insertElement(2);
        rbTree.insertElement(8);
        rbTree.insertElement(1);
        rbTree.insertElement(4);
        rbTree.insertElement(7);
        rbTree.insertElement(9);
        rbTree.deleteElement(2);
        rbTree.traverse();
        // 5B; 4LB; 8RB
        // 4B; 1LR; NULL R
        // 8B; 7LR; 9RB
    }

    public static void test7(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(13);
        rbTree.insertElement(8);
        rbTree.insertElement(17);
        rbTree.insertElement(1);
        rbTree.insertElement(11);
        rbTree.insertElement(15);
        rbTree.insertElement(25);
        rbTree.insertElement(6);
        rbTree.insertElement(22);
        rbTree.insertElement(27);

        rbTree.deleteElement(13);
        rbTree.traverse();
        // 15B 8LR 25RR
        // 25R 17LB 27RB
        // 17B null L 22RB
    }

    public static void test8(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(13);
        rbTree.insertElement(8);
        rbTree.insertElement(17);
        rbTree.insertElement(1);
        rbTree.insertElement(11);
        rbTree.insertElement(15);
        rbTree.insertElement(25);
        rbTree.insertElement(6);
        rbTree.insertElement(22);
        rbTree.insertElement(27);

        rbTree.deleteElement(8);
        rbTree.traverse();
        // 6B 1LB; 13LB
    }

    public static void test9(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(7);
        rbTree.insertElement(3);
        rbTree.insertElement(18);
        rbTree.insertElement(10);
        rbTree.insertElement(22);
        rbTree.insertElement(8);
        rbTree.insertElement(11);
        rbTree.insertElement(26);

        rbTree.deleteElement(3);
        rbTree.traverse();
        // 10R 7LB 11RB
        // 7B NULL L 8RR
    }

    public static void test10(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(13);
        rbTree.insertElement(8);
        rbTree.insertElement(17);
        rbTree.insertElement(1);
        rbTree.insertElement(11);
        rbTree.insertElement(15);
        rbTree.insertElement(25);
        rbTree.insertElement(6);
        rbTree.insertElement(22);
        rbTree.insertElement(27);

        rbTree.deleteElement(11);
        rbTree.traverse();

        // 6R 1LB 13RB
    }

    public static Tree generateRBTree(){
        Tree rbTree = new RedBlackTree();

        rbTree.insertElement(13);
        rbTree.insertElement(8);
        rbTree.insertElement(17);
        rbTree.insertElement(1);
        rbTree.insertElement(11);
        rbTree.insertElement(15);
        rbTree.insertElement(25);
        rbTree.insertElement(6);
        rbTree.insertElement(22);
        rbTree.insertElement(27);
        return rbTree;
    }
}
