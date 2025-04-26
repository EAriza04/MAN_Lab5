package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;

public class BoundedQueueTest {

    ArrayBoundedQueue<Integer> queue;
    
    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(5);
    }

}
