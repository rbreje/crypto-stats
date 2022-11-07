package ro.breje.cryptostats.service.impl;

import ro.breje.cryptostats.service.IMedianProvider;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueuesMedianProvider implements IMedianProvider {

    private Queue<Float> minHeap = new PriorityQueue<>();
    private Queue<Float> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    @Override
    public long size() {
        return minHeap.size() + maxHeap.size();
    }

    @Override
    public void add(float num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    @Override
    public float getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            throw new IllegalStateException("No value provided yet!");
        }
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2;
    }

}
