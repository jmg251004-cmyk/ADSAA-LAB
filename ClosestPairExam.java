import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPairExam {

    // Distance formula
    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) +
                         Math.pow(p1.y - p2.y, 2));
    }

    // Brute force method
    static double bruteForce(Point[] points, int start, int end) {
        double min = Double.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                double d = distance(points[i], points[j]);
                if (d < min)
                    min = d;
            }
        }
        return min;
    }

    static double closest(Point[] points, int start, int end) {

        if (end - start <= 2)
            return bruteForce(points, start, end);

        int mid = (start + end) / 2;

        double dl = closest(points, start, mid);
        double dr = closest(points, mid + 1, end);

        double d = Math.min(dl, dr);

        // Check points near middle line
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (Math.abs(points[i].x - points[mid].x) < d) {
                    double dist = distance(points[i], points[j]);
                    if (dist < d)
                        d = dist;
                }
            }
        }

        return d;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of points: ");
        int n = sc.nextInt();

        Point[] points = new Point[n];

        System.out.println("Enter x and y coordinates:");
        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(x, y);
        }

        // Sort by x-coordinate
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));

        long startTime = System.nanoTime();

        double result = closest(points, 0, n - 1);

        long endTime = System.nanoTime();

        double executionTimeMicro =
                (endTime - startTime) / 1000.0; // convert ns to microseconds

        System.out.printf("Minimum Distance: %.4f\n", result);
        System.out.printf("Execution Time: %.3f microseconds (10^-6 sec)\n",
                          executionTimeMicro);
    }
}