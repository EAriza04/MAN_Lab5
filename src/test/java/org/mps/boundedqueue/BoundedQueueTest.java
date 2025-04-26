package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BoundedQueueTest {

    BoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        // Arrange
        queue = new ArrayBoundedQueue<>(5);
    }

    @Test
    @DisplayName("Test put method")
    /*
     * Asertos:
     * - La cola no debería estar vacía al añadir elementos.
     * - La cola debería tener el tamaño correcto después de añadir elementos.
     * - La cola debería contener los elementos añadidos en el orden correcto.
     * - La cola debería empezar con el primer elemento.
     * - La cola debería terminar con el último elemento.
     * - La cola debería lanzar una excepción al intentar añadir un elemento a una cola llena.
     * - La cola debería lanzar una excepción al intentar añadir un elemento nulo.
    */
    public void testPutAssertJ() {
        // Act
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(5);

        // Assert
        assertThat(queue)
            .isNotEmpty()
            .hasSize(5)
            .containsExactly(1, 2, 3, 4, 5)
            .startsWith(1)
            .endsWith(5);

        assertThatThrownBy(() -> queue.put(6))
            .isInstanceOf(FullBoundedQueueException.class)
            .hasMessageContaining("put: full bounded queue");

        assertThatThrownBy(() -> {
                BoundedQueue<Integer> nullQueue = new ArrayBoundedQueue<>(5); 
                nullQueue.put(null); 
            })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("put: element cannot be null");
    }
    
    @Test
    @DisplayName("get() throws EmptyBoundedQueueException if the queue is empty")
    public void get_EmptyQueue_ThrowsEmptyBoundedQueueException() {
        // Act + Assert
        assertThatThrownBy(() -> queue.get())
            .isInstanceOf(EmptyBoundedQueueException.class)
            .hasMessageContaining("get: empty bounded queue");
    }

    @Test
    @DisplayName("get() returns the first element in the queue")
    public void get_NonEmptyQueue_ReturnsFirstElement() {
        // Arrange
        queue.put(1);
        queue.put(2);
        queue.put(3);

        // Act
        int result = queue.get();
        int expected = 1;

        // Assert
        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("isFull() returns true if the queue is full")
    public void isFull_FullQueue_ReturnsTrue() {
        // Arrange
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(5);

        // Act
        boolean result = queue.isFull();

        // Assert
        assertThat(result)
            .isTrue();
    }

    @Test
    @DisplayName("isFull() returns false if the queue is not full")
    public void isFull_NonFullQueue_ReturnsFalse() {
        // Arrange
        queue.put(1);
        queue.put(2);

        // Act
        boolean result = queue.isFull();

        // Assert
        assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("isEmpty() returns true if the queue is empty")
    public void isEmpty_EmptyQueue_ReturnsTrue() {
        // Act
        boolean result = queue.isEmpty();

        // Assert
        assertThat(result)
            .isTrue();
    }

    @Test
    @DisplayName("isEmpty() returns false if the queue is not empty")
    public void isEmpty_NonEmptyQueue_ReturnsFalse() {
        // Arrange
        queue.put(1);

        // Act
        boolean result = queue.isEmpty();

        // Assert
        assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("size() returns the number of elements in the queue")
    public void size_NonEmptyQueue_ReturnsCorrectSize() {
        // Arrange
        queue.put(1);
        queue.put(2);
        queue.put(3);

        // Act
        int result = queue.size();
        int expected = 3;

        // Assert
        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("getFirst() returns the index of the position of the first element")
    public void getFirst_Queue_ReturnsFirstIndex() {
        // Act
        int result = queue.getFirst();
        int expected = 0;

        // Assert
        assertThat(result)
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("getLast() returns the index of the position of the last element")
    public void getLast_Queue_ReturnsLastIndex() {
        // Arrange
        queue.put(1);
        queue.put(2);
        queue.put(3);

        // Act
        int result = queue.getLast();
        int expected = 3;

        // Assert
        assertThat(result)
            .isEqualTo(expected);
    }

}
