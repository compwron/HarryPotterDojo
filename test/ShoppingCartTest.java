import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    @Test
    public void oneBookPriceShouldBe8EUR() {
        List<Book> books = Lists.newArrayList(new Book(BookType.One, 1));
        assertEquals("8.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoBooksOfSameTypeShouldBe16EUR() {
        List<Book> books = Lists.newArrayList(new Book(BookType.One, 2));
        assertEquals("16.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoDifferentBooksShouldReceive5PercentDiscount() {
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1)).withBook(new Book(BookType.Two, 1)).build();
        assertEquals("15.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void threeDifferentBooksShouldReceive10PercentDiscount() {
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1))
                .withBook(new Book(BookType.Two, 1))
                .withBook(new Book(BookType.Three, 1))
                .build();
        assertEquals("21.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fourDifferentBooksShouldReceive20PercentDiscount() {
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1))
                .withBook(new Book(BookType.Two, 1))
                .withBook(new Book(BookType.Three, 1))
                .withBook(new Book(BookType.Four, 1))
                .build();
        assertEquals("25.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fiveDifferentBooksShouldReceive25PercentDiscount() {
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1))
                .withBook(new Book(BookType.Two, 1))
                .withBook(new Book(BookType.Three, 1))
                .withBook(new Book(BookType.Four, 1))
                .withBook(new Book(BookType.Five, 1))
                .build();
        assertEquals("30.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldOnlyReceiveDiscountOnDifferentBooksButNotOnSameBooks(){
        List<Book> books = new BooksBuilder().withBook(new Book(BookType.One, 1))
                .withBook(new Book(BookType.Two, 2))
                .build();
//       (8 * 2 * .95) + 8
        assertEquals("23.2 EUR", new ShoppingCart(books).formattedPrice());
    }

}
