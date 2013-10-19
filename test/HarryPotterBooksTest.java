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
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1)).withBook(new Book(BookType.Two, 1)).build();
        assertEquals("15.20 EUR", new HarryPotterBooks(books).formattedPrice());
    }

    @Test
    public void threeDifferentBooksShouldRecieve10PercentDiscount() {
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1)).withBook(new Book(BookType.Two, 1)).withBook(new Book(BookType.Three, 1)).build();
        assertEquals("21.60 EUR", new HarryPotterBooks(books).formattedPrice());
    }
}
