import java.util.*;

class MinHeap {

    int heap[] = new int[100];
    int size = 0;

    int parent(int i) {
        return (i - 1) / 2;
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    void insert(int key) {

        heap[size] = key;
        int i = size;
        size++;

        while (i != 0 && heap[parent(i)] > heap[i]) {

            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;

            i = parent(i);
        }
    }

    int getMin() {
        return heap[0];
    }

    int extractMin() {

        if (size <= 0)
            return -1;

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapify(0);

        return root;
    }

    void heapify(int i) {

        int l = left(i);
        int r = right(i);
        int smallest = i;

        if (l < size && heap[l] < heap[smallest])
            smallest = l;

        if (r < size && heap[r] < heap[smallest])
            smallest = r;

        if (smallest != i) {

            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;

            heapify(smallest);
        }
    }

    void display() {

        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");

        System.out.println();
    }
}

public class PriorityQueueMinHeap {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MinHeap h = new MinHeap();

        h.insert(10);
        h.insert(4);
        h.insert(15);
        h.insert(20);
        h.insert(0);

        System.out.println("Heap:");
        h.display();

        System.out.println("Minimum Element: " + h.getMin());

        System.out.println("Extracted Minimum: " + h.extractMin());

        System.out.println("Heap after Extract-Min:");
        h.display();
    }
}