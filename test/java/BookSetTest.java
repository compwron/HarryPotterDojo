import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    @Test
    public void priceOfLargestSetOfEmptyBookSetIsZero() {
        BookSet bookSet = new BookSet(new ArrayList<Book>());
        assertThat(bookSet.priceOfLargestSet(), is(0.0));
    }

    @Test
    public void priceOfLargestSetOfBookSetWithOneBookSetIs8() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new Book(BookType.One, 1)));
        assertThat(bookSet.priceOfLargestSet(), is(8.0));
    }

    @Test
    public void shouldCalculatePriceOfLargestSetOfBookSetWithFiveDifferentBooksAndAssortedOtherBooks() {
        BookSet bookSet = new BookSet(new BooksBuilder().withBook(new Book(BookType.One, 99))
                .withBook(new Book(BookType.Two, 400))
                .withBook(new Book(BookType.Three, 542352))
                .withBook(new Book(BookType.Four, 3453234))
                .withBook(new Book(BookType.Five, 23))
                .build());
        assertThat(bookSet.priceOfLargestSet(), is(5 * 8 * 0.75));
    }
}
