import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HarryPotterBooksTest {

    @Test
    public void oneBookPriceShouldBe8EUR() {
        List<Book> books = Lists.newArrayList(new Book(BookType.One, 1));
        assertEquals("8.00 EUR", new HarryPotterBooks(books).formattedPrice());
    }

    @Test
    public void twoBooksOfSameTypeShouldBe16EUR() {
        List<Book> books = Lists.newArrayList(new Book(BookType.One, 2));
        assertEquals("16.00 EUR", new HarryPotterBooks(books).formattedPrice());
    }

    @Test
    public void twoDifferentBooksShouldReceive5PercentDiscount() {
        List<Book> books = Lists.newArrayList(new Book(BookType.One, 1), new Book(BookType.Two, 1));
        assertEquals("15.20 EUR", new HarryPotterBooks(books).formattedPrice());
    }
}
