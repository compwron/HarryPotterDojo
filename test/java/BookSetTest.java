import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BookSetTest {
    @Test
    public void thereIsNoSetInNoNBooks() {
        BookSet bookSet = new BookSet(new ArrayList<Book>());
        assertEquals(BookSetType.None, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInOneBookSetIsOne() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 1)));
        assertEquals(BookSetType.One, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksTheSameIsOne() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 2)));
        assertEquals(BookSetType.One, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksDifferentIsTwo() {
        BookSet bookSet = new BookSet(new BooksBuilder()
                .withBook(new Book(BookType.One, 1))
                .withBook(new Book(BookType.Two, 1))
                .build());
        assertEquals(BookSetType.Two, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksDifferentAndSomeBooksTheSameIsStillTwo() {
        BookSet bookSet = new BookSet(new BooksBuilder()
                .withBook(new Book(BookType.One, 2))
                .withBook(new Book(BookType.Two, 3))
                .build());
        assertEquals(BookSetType.Two, bookSet.getBookSetType());
    }

    @Test
    public void shouldReturnEmptyBookSetFromEmptyBookSetWhenAskedToRemoveCurrentLargestBookSet() {
        BookSet bookSet = new BookSet(new ArrayList<Book>());
        assertEquals(new BookSet(new ArrayList<Book>()), bookSet.removeLargestSet());
    }

    @Test
    public void shouldReturnEmptyBookSetWhenRemovingCurrentLargestBookSetFromSetWithOneBook() {
        // Given
        BookSet startingBookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 2)));
        // When
        BookSet bookSet = startingBookSet.removeLargestSet();

        // Then
        BookSet expectedBookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 1)));
        assertEquals(expectedBookSet, bookSet);
    }

    @Test
    public void shouldReturnBookSetWithOneBookWhenRemovingCurrentLargestBookSetFromSetWithTwoOfOneTypeOfBook() {
        BookSet expectedBookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 1)));
        BookSet startingBookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 2)));
        assertEquals(expectedBookSet, startingBookSet.removeLargestSet());
    }
}
