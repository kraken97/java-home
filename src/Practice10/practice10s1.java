package Practice10;

import jdk.nashorn.internal.ir.BinaryNode;
import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * Created by kraken on 19.11.16.
 */
public class practice10s1 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int size = (int) Math.pow(2, n);

        Element[] arr = new Element[size-1];
        for (int i = 0; i < size-1; i++) {
            arr[i] = new Element(i+1);
        }
        MinHeap<Element> l = new MinHeap(arr, arr.length, arr.length);
        int n1 = Integer.parseInt(reader.readLine())-1;
        int n2 =Integer.parseInt(reader.readLine())-1;
        int common = getCommon(l, n2 ,n1);
        System.out.println(common);

    }

    static int getCommon(MinHeap<Element> heap, int n1, int n2) {
        int p1 = n1;
        int p2 = n2;
        while (true) {
            heap.Heap[p1].visited = true;
            if (heap.Heap[p2].visited) {
                return heap.Heap[p2].value;
            }
            heap.Heap[p2].visited = true;
            p1 = heap.parent(p1);
            p2 = heap.parent(p2);
        }
    }
}


/**
 * Min-heap implementation
 */
class MinHeap<E extends Comparable<? super E>> {
    public E[] Heap;   // Pointer to the heap array
    private int size;   // Maximum size of the heap
    private int n;      // Number of things in heap

    public MinHeap(E[] h, int num, int max) {
        Heap = h;
        n = num;
        size = max;
        buildheap();
    }


    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }

    /**
     * Return position for left child of pos
     */
    public int leftchild(int pos) {
        assert pos < n / 2 : "Position has no left child";
        return 2 * pos + 1;
    }


    /**
     * Return position for parent
     */
    public int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos - 1) / 2;
    }

    /**
     * Heapify contents of Heap
     */
    public void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--) siftdown(i);
    }


    private void siftdown(int pos) {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (Heap[j].compareTo(Heap[j + 1]) > 0))
                j++; // j is now index of child with greater value
            if (Heap[pos].compareTo(Heap[j]) <= 0)
                return;
            swap(Heap, pos, j);
            pos = j;  // Move down
        }
    }



    static <T> void swap(T[] arr, int p1, int p2) {
        T temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;

    }
}