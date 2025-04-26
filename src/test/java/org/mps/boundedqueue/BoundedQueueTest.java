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
}
