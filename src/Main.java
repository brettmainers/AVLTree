public class Main {
    public static void main(String[] args) throws Exception {

        AVLTree avl = new AVLTree();
        avl.addNode(10);
        avl.addNode(5);
        avl.addNode(15);
        avl.addNode(3);
        avl.addNode(7);
        avl.addNode(12);
        avl.addNode(18);


        System.out.println("Inorder traversal:");
        avl.inorderTraversal();
        System.out.println();

        System.out.println("Preorder traversal:");
        avl.preorderTraversal();
        System.out.println();

        System.out.println("Postorder traversal:");
        avl.postorderTraversal();
        System.out.println();

        System.out.println("Searching for 7: " + avl.search(7));
        System.out.println("Searching for 20: " + avl.search(20));
        System.out.println();

        System.out.println("Removing 10");
        System.out.println();
        avl.removeNode(10);
        System.out.println("Inorder traversal after removing 10:");
        avl.inorderTraversal();
    }
}
