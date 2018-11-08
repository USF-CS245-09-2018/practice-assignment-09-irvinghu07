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

    public void printInternal() {
        System.out.println("length: " + this.getCurrentSize());
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.getData().length; i++) {
            if (-1 != this.data[i]) {
                if (i + 1 != this.getData().length) {
                    stringBuffer.append(this.data[i] + ", ");
                } else {
                    stringBuffer.append(i);
                }
            }
        }
        stringBuffer.append("]");
//        System.out.println(Arrays.toString(this.getData()));
        System.out.println(stringBuffer.toString());
    }

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
        int currentIndex = this.getCurrentSize() + 1;
        if (!haveEnoughSpace()) {
            doubleArraySize();
        }
        this.getData()[currentIndex] = val;
        int parentIndex = parentIndex(currentIndex);
        while (this.data[parentIndex] > this.data[currentIndex]) {
            swapValueWithIndex(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex(currentIndex);
        }
        currentSize++;
    }

    @Override
    public Integer remove() {
        if (isEmpty()) {
            throw new BinaryHeapIndexOutOfBoundException();
        }
        int lastElementIndex = this.getCurrentSize();
        int currentIndex = 1;
        if (currentIndex != lastElementIndex) {
            swapValueWithIndex(currentIndex, lastElementIndex);
            moveDown(currentIndex);
        }
        int returnVal = this.data[this.currentSize];
        this.data[this.getCurrentSize()] = -1;
        this.currentSize--;
        return returnVal;
    }

    private void moveDown(int currentIndex) {
        int leftChildIndex = leftChildIndex(currentIndex);
        int rightChildIndex = rightChildIndex(currentIndex);
        if (leftChildIndex >= this.getCurrentSize() || rightChildIndex >= this.getCurrentSize()) {
            return;
        }
        if (this.data[currentIndex] > this.data[leftChildIndex] && this.data[currentIndex] < this.data[rightChildIndex]) {
            // bigger than left children
            swapValueWithIndex(currentIndex, leftChildIndex);
            moveDown(leftChildIndex);
        } else if (this.data[currentIndex] < this.data[leftChildIndex] && this.data[currentIndex] > this.data[rightChildIndex]) {
            // bigger than right children
            swapValueWithIndex(currentIndex, rightChildIndex);
            moveDown(rightChildIndex);
        } else if (this.data[currentIndex] > this.data[leftChildIndex] && this.data[currentIndex] > this.data[rightChildIndex]) {
            // bigger than both of left and right children
            int resultIndex = minBetweenChildren(leftChildIndex, rightChildIndex);
            swapValueWithIndex(currentIndex, resultIndex);
            moveDown(resultIndex);
        } else {
            return;
        }
    }

    private int minBetweenChildren(int leftChildIndex, int rightChildIndex) {
        return this.data[leftChildIndex] < this.data[rightChildIndex] ? leftChildIndex : rightChildIndex;
    }

    private void swapValueWithIndex(int a, int b) {
        int temp = this.data[a];
        this.data[a] = this.data[b];
        this.data[b] = temp;
    }

    private void initializeArray(int[] targetArray) {
        Arrays.fill(targetArray, -1);
    }

    private boolean isEmptyOnIndex(int targetIndex) {
        return -1 == this.getData()[targetIndex];
    }

    private int parentIndex(int childIndex) {
        return childIndex / 2;
    }

    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex;
    }

    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private void doubleArraySize() {
        int[] newData = new int[this.getMaxSize() * 2];
        initializeArray(newData);
        System.arraycopy(this.getData(), 0, newData, 0, this.getCurrentSize() + 1);
        this.setData(newData);
        this.setMaxSize(this.getMaxSize() * 2);
    }

    private boolean haveEnoughSpace() {
        return this.getCurrentSize() + 2 != this.getMaxSize();
    }

    private boolean isEmpty() {
        return -1 == this.getData()[1];
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