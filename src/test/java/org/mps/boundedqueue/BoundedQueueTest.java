// Eduardo Ariza Abad y Enrique Ibáñez Rico

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
    @DisplayName("Constructor with size 0 throws IllegalArgumentException")
    /*
     * Asertos:
     * - La cola debería lanzar una IllegalArgumentException al intentar crear una cola con tamaño 0.
    */
    public void constructorWithSize0_ThrowsIllegalArgumentException() {
        // Act + Assert
        assertThatThrownBy(() -> new ArrayBoundedQueue<>(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("ArrayBoundedException: capacity must be positive");
    }

    @Test
    @DisplayName("Test put method with empty queue")
    /*
     * Asertos:
     * - La cola no debería estar vacía al añadir elementos.
     * - La cola debería tener el tamaño correcto después de añadir elementos.
     * - La cola debería contener los elementos añadidos en el orden correcto.
     * - La cola debería empezar con el primer elemento.
     * - La cola debería terminar con el último elemento.
    */
    public void put_EmptyQueue_InsertsElements() {
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
    }

    @Test
    @DisplayName("Test put method with null value")
    /*
     * Asertos:
     * - La cola debería lanzar una IllegalArgumentException al intentar añadir un valor nulo.
    */
    public void put_NullValue_ThrowsIllegalArgumentException() {
        // Act + Assert
        assertThatThrownBy(() -> queue.put(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("put: element cannot be null");
    }

    @Test
    @DisplayName("Test put method with full queue")
    /*
     * Asertos:
     * - La cola debería lanzar una FullBoundedQueueException al intentar añadir un elemento a una cola llena.
    */
    public void put_FullQueue_ThrowsFullBoundedQueueException() {
        // Arrange
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.put(5);

        // Act + Assert
        assertThatThrownBy(() -> queue.put(6))
            .isInstanceOf(FullBoundedQueueException.class)
            .hasMessageContaining("put: full bounded queue");
    }
    
    @Test
    @DisplayName("get() throws EmptyBoundedQueueException if the queue is empty")
    /*
     * Asertos:
     * - La cola debería lanzar una EmptyBoundedQueueException al intentar obtener un elemento de una cola vacía.
    */
    public void get_EmptyQueue_ThrowsEmptyBoundedQueueException() {
        // Act + Assert
        assertThatThrownBy(() -> queue.get())
            .isInstanceOf(EmptyBoundedQueueException.class)
            .hasMessageContaining("get: empty bounded queue");
    }

    @Test
    @DisplayName("get() returns the first element in the queue")
    /*
     * Asertos:
     * - La cola debería devolver el primer elemento al llamar a get() en una cola no vacía.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver true al llamar a isFull() en una cola llena.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver false al llamar a isFull() en una cola no llena.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver true al llamar a isEmpty() en una cola vacía.
    */
    public void isEmpty_EmptyQueue_ReturnsTrue() {
        // Act
        boolean result = queue.isEmpty();

        // Assert
        assertThat(result)
            .isTrue();
    }

    @Test
    @DisplayName("isEmpty() returns false if the queue is not empty")
    /*
     * Asertos:
     * - La cola debería devolver false al llamar a isEmpty() en una cola no vacía.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver el tamaño correcto al llamar a size() en una cola no vacía.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver el índice del primer elemento al llamar a getFirst() en una cola no vacía.
    */
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
    /*
     * Asertos:
     * - La cola debería devolver el índice del último elemento al llamar a getLast() en una cola no vacía.
    */
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