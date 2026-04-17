import java.util.*;

// Step 1: Create a class to represent a Node in the Huffman Tree
class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left = null, right = null;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    // Constructor for internal nodes
    Node(int freq, Node left, Node right) {
        this.ch = '\0'; // Internal nodes don't store characters
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    // Used by PriorityQueue to keep the smallest frequency at the top
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

public class HuffmanCoding {

    // Step 2: Recursive function to traverse the tree and print codes
    public static void printCodes(Node root, String code) {
        if (root == null) return;

        // If it's a leaf node, print the character and its code
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + ": " + code);
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of unique characters:");
        int n = sc.nextInt();

        PriorityQueue<Node> pq = new PriorityQueue<>();

        System.out.println("Enter character and frequency pairs (e.g., A 5):");
        for (int i = 0; i < n; i++) {
            char c = sc.next().charAt(0);
            int f = sc.nextInt();
            pq.add(new Node(c, f));
        }

        // Step 3: Build the tree
        while (pq.size() > 1) {
            Node left = pq.poll();  // Smallest
            Node right = pq.poll(); // Second smallest
            
            int sum = left.freq + right.freq;
            pq.add(new Node(sum, left, right));
        }

        Node root = pq.poll();

        System.out.println("\nGenerated Huffman Codes:");
        printCodes(root, "");
        
        sc.close();
    }
}