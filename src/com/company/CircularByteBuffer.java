package com.company;

public class CircularByteBuffer {
    private int capacity;
    private byte[] buffer;
    private int readPointer;
    private int writePointer;
    private int available;

    CircularByteBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new byte[capacity];

    }

    public synchronized int available() {
        return available;
    }

    public synchronized int capacity() {
        return capacity;
    }

    public synchronized int slots() {
        return capacity - available;
    }

    public synchronized void clear() {
        readPointer = 0;
        writePointer = 0;
        available = 0;
    }

    public synchronized boolean put(int value) {
        if (available == capacity)
            return false;
        buffer[writePointer] = (byte) value;
        writePointer = (writePointer + 1) % capacity;
        available++;
        return true;

    }

    public synchronized int get() {
        if (available == 0)
            return -1;
        byte value = buffer[readPointer];
        readPointer = (readPointer + 1) % capacity;
        available--;
        return value;
    }

}
