import java.util.*;

class Aircraft {
    String id;
    double x, y;

    Aircraft(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

public class AirTrafficExam {

    static double distance(Aircraft a, Aircraft b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) +
                         Math.pow(a.y - b.y, 2));
    }

    static double bruteForce(Aircraft[] arr, int start, int end) {
        double min = Double.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                double d = distance(arr[i], arr[j]);
                if (d < min)
                    min = d;
            }
        }
        return min;
    }

    static double closest(Aircraft[] arr, int start, int end) {

        if (end - start <= 2)
            return bruteForce(arr, start, end);

        int mid = (start + end) / 2;

        double dl = closest(arr, start, mid);
        double dr = closest(arr, mid + 1, end);

        double d = Math.min(dl, dr);

        // Check near middle
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (Math.abs(arr[i].x - arr[mid].x) < d) {
                    double dist = distance(arr[i], arr[j]);
                    if (dist < d)
                        d = dist;
                }
            }
        }

        return d;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of aircraft: ");
        int n = sc.nextInt();

        Aircraft[] arr = new Aircraft[n];

        System.out.println("Enter AircraftID x y:");
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            arr[i] = new Aircraft(id, x, y);
        }

        System.out.print("Enter safety threshold distance: ");
        double threshold = sc.nextDouble();

        // Sort by x-coordinate
        Arrays.sort(arr, Comparator.comparingDouble(a -> a.x));

        long startTime = System.nanoTime();

        double minDistance = closest(arr, 0, n - 1);

        long endTime = System.nanoTime();

        double executionMicro =
                (endTime - startTime) / 1000.0;

        System.out.printf("Minimum Separation Distance: %.3f km\n",
                          minDistance);

        if (minDistance < threshold)
            System.out.println("Status: ALERT - Safety distance violated");
        else
            System.out.println("Status: SAFE");

        System.out.printf("Execution Time: %.3f microseconds (10^-6 sec)\n",
                          executionMicro);
    }
}