import java.util.Arrays;

/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-09
 * Date: 2018-11-02
 */
public class BinaryHeap implements PriorityQueue {

    private static final int DEFAULT_SIZE = 10;

    private int[] data;

    private int currentSize;

    private int maxSize;

    public BinaryHeap(int size) {
        this.data = new int[size];
        initializeArray(this.data);
        this.currentSize = 0;
        this.maxSize = size;
    }

    public BinaryHeap() {
        this.data = new int[DEFAULT_SIZE];
        initializeArray(this.data);
        this.currentSize = 0;
        this.maxSize = DEFAULT_SIZE;
    }

    @Override
    public void add(Integer val) {
        if (!haveEnoughSpace()) {
            doubleArraySize();
        }
        this.getData()[this.getCurrentSize()] = val;
        this.setCurrentSize(this.getCurrentSize() + 1);
    }

    @Override
    public Integer remove() {
        return null;
    }

    private void initializeArray(int[] targetArray) {
        Arrays.fill(targetArray, -1);
    }

    private boolean isEmptyOnIndex(int targetIndex) {
        return -1 == this.getData()[targetIndex];
    }

    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private void doubleArraySize() {
        int[] newData = new int[this.getMaxSize() * 2];
        initializeArray(newData);
        System.arraycopy(this.getData(), 0, newData, 0, this.getCurrentSize());
        this.setData(newData);
        this.setMaxSize(this.getMaxSize() * 2);
    }

    private boolean haveEnoughSpace() {
        return this.getCurrentSize() + 1 != this.getMaxSize();
    }

    private boolean isEmpty() {
        return -1 == this.getData()[0];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}