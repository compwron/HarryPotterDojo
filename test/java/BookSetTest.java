import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class BookSetTest {
    @Test
    public void thereIsNoSetInNoNBooks() {
        BookSet bookSet = new BookSet(new ArrayList<HarryPotterBookType>());
        assertEquals(BookSetType.None, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInOneBookSetIsOne() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 1)));
        assertEquals(BookSetType.One, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksTheSameIsOne() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 2)));
        assertEquals(BookSetType.One, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksDifferentIsTwo() {
        BookSet bookSet = new BookSet(new BooksBuilder()
                .withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .build());
        assertEquals(BookSetType.Two, bookSet.getBookSetType());
    }

    @Test
    public void theLargestSetOfBooksInBookSetWithTwoBooksDifferentAndSomeBooksTheSameIsStillTwo() {
        BookSet bookSet = new BookSet(new BooksBuilder()
                .withBook(new HarryPotterBookType(BookSeriesNumber.One, 2))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 3))
                .build());
        assertEquals(BookSetType.Two, bookSet.getBookSetType());
    }

    @Test
    public void shouldReturnEmptyBookSetFromEmptyBookSetWhenAskedToRemoveCurrentLargestBookSet() {
        BookSet bookSet = new BookSet(new ArrayList<HarryPotterBookType>());
        assertEquals(new BookSet(new ArrayList<HarryPotterBookType>()), bookSet.removeLargestSet());
    }

    @Test
    public void shouldReturnEmptyBookSetWhenRemovingCurrentLargestBookSetFromSetWithOneBook() {
        // Given
        BookSet startingBookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 2)));
        // When
        BookSet bookSet = startingBookSet.removeLargestSet();

        // Then
        BookSet expectedBookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 1)));
        assertEquals(expectedBookSet, bookSet);
    }

    @Test
    public void shouldReturnBookSetWithOneBookWhenRemovingCurrentLargestBookSetFromSetWithTwoOfOneTypeOfBook() {
        BookSet expectedBookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 1)));
        BookSet startingBookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 2)));
        assertEquals(expectedBookSet, startingBookSet.removeLargestSet());
    }

    @Test
    public void bookSetTypeShouldBeTwoWhenThereAreTwoBooksOfDifferentTypes(){
        BookSet startingBookSet = new BookSet(new BooksBuilder()
                .withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .build());
        assertThat(startingBookSet.getBookSetType(), is(BookSetType.Two));
    }

    @Test
    public void typeOfBookSetMadeByRemovingAllBooksInSetShouldBeNone(){
        BookSet startingBookSet = new BookSet(new BooksBuilder()
                .withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .build());
        assertThat(startingBookSet.removeLargestSet().getBookSetType(), is(BookSetType.None));
    }

    @Test
    public void priceOfLargestSetOfEmptyBookSetIsZero() {
        BookSet bookSet = new BookSet(new ArrayList<HarryPotterBookType>());
        assertThat(bookSet.priceOfLargestSet(), is(0.0));
    }

    @Test
    public void priceOfLargestSetOfBookSetWithOneBookSetIs8() {
        BookSet bookSet = new BookSet(Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 1)));
        assertThat(bookSet.priceOfLargestSet(), is(8.0));
    }

    @Test
    public void shouldCalculatePriceOfLargestSetOfBookSetWithFiveDifferentBooksAndAssortedOtherBooks() {
        BookSet bookSet = new BookSet(new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 99))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 400))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Three, 542352))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Four, 3453234))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Five, 23))
                .build());
        assertThat(bookSet.priceOfLargestSet(), is(5 * 8 * 0.75));
    }
}
