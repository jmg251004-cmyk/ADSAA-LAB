import java.util.*;

class Item {
    int weight, profit;
    double ratio;

    Item(int w, int p) {
        this.weight = w;
        this.profit = p;
        this.ratio = (double) p / w;
    }
}

class Node {
    int level, profit, weight;
    double bound;
}

public class Knapsack {

    // Function to calculate the upper bound of profit in a subtree
    static double calculateBound(Node u, int n, int W, Item[] items) {
        if (u.weight >= W) return 0;

        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        // Greedily include full items
        while (j < n && totalWeight + items[j].weight <= W) {
            totalWeight += items[j].weight;
            profitBound += items[j].profit;
            j++;
        }

        // If capacity remains, add fractional part of the next item
        if (j < n) {
            profitBound += (double)(W - totalWeight) * items[j].ratio;
        }

        return profitBound;
    }

    static int solveKnapsack(int W, Item[] items, int n) {
        // Sort items by profit/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        Queue<Node> q = new LinkedList<>();
        Node u = new Node();
        Node v = new Node();

        // Initialize dummy root node
        u.level = -1;
        u.profit = 0;
        u.weight = 0;
        u.bound = calculateBound(u, n, W, items);

        q.add(u);

        int maxProfit = 0;

        while (!q.isEmpty()) {
            u = q.poll();

            // If it's a leaf node or the bound is worse than current max, skip
            if (u.level == n - 1) continue;

            // Generate "Include" child
            v = new Node();
            v.level = u.level + 1;
            v.weight = u.weight + items[v.level].weight;
            v.profit = u.profit + items[v.level].profit;

            if (v.weight <= W && v.profit > maxProfit) {
                maxProfit = v.profit;
            }

            v.bound = calculateBound(v, n, W, items);
            if (v.bound > maxProfit) {
                q.add(v);
            }

            // Generate "Exclude" child
            v = new Node();
            v.level = u.level + 1;
            v.weight = u.weight;
            v.profit = u.profit;
            v.bound = calculateBound(v, n, W, items);

            if (v.bound > maxProfit) {
                q.add(v);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of items: ");
        int n = sc.nextInt();
        System.out.println("Enter maximum capacity of knapsack: ");
        int W = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter weight and profit for each item:");
        for (int i = 0; i < n; i++) {
            int w = sc.nextInt();
            int p = sc.nextInt();
            items[i] = new Item(w, p);
        }

        int result = solveKnapsack(W, items, n);
        System.out.println("\nMaximum Profit: " + result);
    }
}