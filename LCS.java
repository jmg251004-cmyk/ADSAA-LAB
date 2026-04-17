import java.util.*;

public class LCS {

    // Set to store unique LCS strings (prevents duplicates)
    static Set<String> allLCS = new TreeSet<>();

    public static void findAllLCS(int[][] L, String X, String Y, int i, int j, String currentLCS) {
        // Base case: if we reach the start of either string
        if (i == 0 || j == 0) {
            // Reverse the string because we build it backwards
            allLCS.add(new StringBuilder(currentLCS).reverse().toString());
            return;
        }

        // If characters match, move diagonally
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            findAllLCS(L, X, Y, i - 1, j - 1, currentLCS + X.charAt(i - 1));
        } else {
            // If values from top and left are equal, explore both paths
            if (L[i - 1][j] == L[i][j - 1]) {
                findAllLCS(L, X, Y, i - 1, j, currentLCS);
                findAllLCS(L, X, Y, i, j - 1, currentLCS);
            } 
            // Otherwise, follow the larger value
            else if (L[i - 1][j] > L[i][j - 1]) {
                findAllLCS(L, X, Y, i - 1, j, currentLCS);
            } else {
                findAllLCS(L, X, Y, i, j - 1, currentLCS);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first string (X): ");
        String X = sc.nextLine();
        System.out.println("Enter second string (Y): ");
        String Y = sc.nextLine();

        int m = X.length();
        int n = Y.length();
        int[][] L = new int[m + 1][n + 1];

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        System.out.println("\nLength of LCS: " + L[m][n]);

        // Find all combinations using recursion
        findAllLCS(L, X, Y, m, n, "");

        System.out.println("All possible LCS combinations:");
        for (String s : allLCS) {
            System.out.println(s);
        }

        sc.close();
    }
}