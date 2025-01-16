import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private List<Integer> heap;
    private int childrenCount;

    public PowerOfTwoMaxHeap(int power) {
        this.heap = new ArrayList<>();
        this.childrenCount = (int) Math.pow(2, power);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.size() == 0) throw new IllegalStateException("Heap is empty");

        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown(0);
        return maxValue;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / childrenCount;

        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / childrenCount;
        }
    }

    private void heapifyDown(int index) {
        int largest = index;

        for (int i = 1; i <= childrenCount; i++) {
            int childIndex = childrenCount * index + i;
            if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largest)) {
                largest = childIndex;
            }
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);

        heap.insert(10);
        heap.insert(4);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);

        System.out.println("Max value removed: " + heap.popMax());
    }
}
