package tqs.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TqsStackTest {

    TqsStack<String> stack;

    @BeforeEach
    void start() {
        stack = new TqsStack<String>();
    }
    
    @Test
    @DisplayName("A stack is empty on construction.")
    void emptyOnConstruction() {
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("A stack has size 0 on construction.")
    void size0OnConstruction() {
        assertEquals(stack.size(), 0);
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    void afterPush() {
        stack.push("Amogus");
        stack.push("Ricardo");
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), 2);
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x.")
    void pop() {
        stack.push("Amogus");
        assertEquals(stack.pop(), "Amogus");
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    void peek() {
        stack.push("Amogus");
        int initialSize = stack.size();
        assertEquals(stack.peek(), "Amogus");
        assertEquals(stack.size(), initialSize);

    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    void multiplePops() {
        stack.push("Amogus");
        stack.push("Ricardo");
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
        
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException [You should test for the Exception occurrence")
    void noElementPop() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });

        assertTrue(exception instanceof NoSuchElementException);

    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementExceptioni)For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
    void noElementPeek() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });

        assertTrue(exception instanceof NoSuchElementException);
    }



    // h)
    
}
