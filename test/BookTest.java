import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {
    @Test
    public void oneBookShouldCost8EUR(){
        assertEquals(8, new Book(BookType.One, 1).eurosCost());
    }

    @Test
    public void zeroBooksShouldCode0EUR(){
        assertEquals(0, new Book(BookType.One, 0).eurosCost());
    }
}
