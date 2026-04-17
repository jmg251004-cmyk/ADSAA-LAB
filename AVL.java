import java.util.*;

class Node {
    int key;
    Node left, right;
    int height;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVL {

    static int height(Node n) {
        if (n == null) return 0;
        return n.height;
    }

    static int balance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t = x.right;

        x.right = y;
        y.left = t;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t = y.left;

        y.left = x;
        x.right = t;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    static Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int b = balance(node);

        // LL
        if (b > 1 && key < node.left.key)
            return rightRotate(node);

        // RR
        if (b < -1 && key > node.right.key)
            return leftRotate(node);

        // LR
        if (b > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (b < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static Node minValue(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    static Node delete(Node root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {

            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node temp = minValue(root.right);
            root.key = temp.key;
            root.right = delete(root.right, temp.key);
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int b = balance(root);

        if (b > 1 && balance(root.left) >= 0)
            return rightRotate(root);

        if (b > 1 && balance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (b < -1 && balance(root.right) <= 0)
            return leftRotate(root);

        if (b < -1 && balance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static boolean search(Node root, int key) {

        if (root == null)
            return false;

        if (key == root.key)
            return true;

        if (key < root.key)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Node root = null;

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            root = insert(root, val);
        }

        System.out.print("Enter element to search: ");
        int s = sc.nextInt();
        boolean found = search(root, s);

        System.out.print("Enter element to delete: ");
        int d = sc.nextInt();
        root = delete(root, d);

        long end = System.nanoTime();

        double timeMicro = (end - start) / 1000.0;

        System.out.println("Inorder Traversal:");
        inorder(root);

        System.out.println("\nSearch Result: " +
                (found ? "Found" : "Not Found"));

        System.out.printf("Execution Time: %.3f microseconds (10^-6 sec)",
                timeMicro);
    }
}