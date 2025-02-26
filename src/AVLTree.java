public class AVLTree {

    Node root = null;

    public void addNode(int val) {
        root = addNodeHelper(root, val);
    }

    private Node addNodeHelper(Node node, int val) {
        if (node == null) {
            return new Node(val);
        } else if (val < node.val) {
            node.leftChild = addNodeHelper(node.leftChild, val);
        } else {
            node.rightChild = addNodeHelper(node.rightChild, val);
        }

        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));

        int balance = getBalance(node);

        if (balance > 1 && val < node.leftChild.val) {
            return rotateRight(node);
        }
        if (balance < -1 && val > node.rightChild.val) {
            return rotateLeft(node);
        }
        if (balance > 1 && val > node.leftChild.val) {
            node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }
        if (balance < -1 && val < node.rightChild.val) {
            node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        return node;
    }

    public void removeNode(int val) {
        root = removeNodeHelper(root, val);
    }

    private Node removeNodeHelper(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (val < node.val) {
            node.leftChild = removeNodeHelper(node.leftChild, val);
        } else if (val > node.val) {
            node.rightChild = removeNodeHelper(node.rightChild, val);
        } else {
            if (node.leftChild == null && node.rightChild == null) {
                node = null;
            } else if (node.leftChild == null) {
                node = node.rightChild;
            } else if (node.rightChild == null) {
                node = node.leftChild;
            } else {
                Node temp = findMinValueNode(node.rightChild);
                node.val = temp.val;
                node.rightChild = removeNodeHelper(node.rightChild, temp.val);
            }
        }
        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.leftChild) >= 0) {
            return rotateRight(node);
        }
        if (balance > 1 && getBalance(node.leftChild) < 0) {
            node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.rightChild) <= 0) {
            return rotateLeft(node);
        }
        if (balance < -1 && getBalance(node.rightChild) > 0) {
            node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        return node;
    }

    private Node findMinValueNode(Node node) {
        Node current = node;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.leftChild) - height(node.rightChild);
    }

    private Node rotateRight(Node unbalanced) {
        Node newRoot = unbalanced.leftChild;
        Node rightSubtree = newRoot.rightChild;
        newRoot.rightChild = unbalanced;
        unbalanced.leftChild = rightSubtree;
        unbalanced.height = Math.max(height(unbalanced.leftChild), height(unbalanced.rightChild)) + 1;
        newRoot.height = Math.max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
        return newRoot;
    }

    private Node rotateLeft(Node unbalanced) {
        Node newRoot = unbalanced.rightChild;
        Node leftSubtree = newRoot.leftChild;
        newRoot.leftChild = unbalanced;
        unbalanced.rightChild = leftSubtree;
        unbalanced.height = Math.max(height(unbalanced.leftChild), height(unbalanced.rightChild)) + 1;
        newRoot.height = Math.max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
        return newRoot;
    }

    public boolean search(int val) {
        return searchHelper(root, val);
    }

    private boolean searchHelper(Node node, int val) {
        if (node == null) {
            return false;
        } else if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return searchHelper(node.leftChild, val);
        } else {
            return searchHelper(node.rightChild, val);
        }
    }

    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.leftChild);
            System.out.print(node.val + " ");
            inorderHelper(node.rightChild);
        }
    }

    public void preorderTraversal() {
        preorderHelper(root);
        System.out.println();
    }

    private void preorderHelper(Node node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preorderHelper(node.leftChild);
            preorderHelper(node.rightChild);
        }
    }

    public void postorderTraversal() {
        postorderHelper(root);
        System.out.println();
    }

    private void postorderHelper(Node node) {
        if (node != null) {
            postorderHelper(node.leftChild);
            postorderHelper(node.rightChild);
            System.out.print(node.val + " ");
        }
    }
}
