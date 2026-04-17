import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ConvexHullExam {

    // To find orientation
    static double crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) -
               (b.y - a.y) * (c.x - a.x);
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

        long startTime = System.nanoTime();

        // Sort by x-coordinate
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));

        List<Point> hull = new ArrayList<>();

        // Build lower hull
        for (int i = 0; i < n; i++) {
            while (hull.size() >= 2 &&
                   crossProduct(
                     hull.get(hull.size() - 2),
                     hull.get(hull.size() - 1),
                     points[i]) <= 0) {

                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        // Build upper hull
        int lowerSize = hull.size();
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > lowerSize &&
                   crossProduct(
                     hull.get(hull.size() - 2),
                     hull.get(hull.size() - 1),
                     points[i]) <= 0) {

                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        long endTime = System.nanoTime();

        double executionMicro =
                (endTime - startTime) / 1000.0;

        System.out.println("Convex Hull Points:");
        for (int i = 0; i < hull.size() - 1; i++) {
            System.out.println("(" +
                    hull.get(i).x + ", " +
                    hull.get(i).y + ")");
        }

        System.out.printf("Execution Time: %.3f microseconds (10^-6 sec)\n",
                          executionMicro);
    }
}