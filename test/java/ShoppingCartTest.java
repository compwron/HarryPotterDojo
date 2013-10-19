import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {
    @Test
    public void shouldCalculateCostOfZeroBooks() {
        List<HarryPotterBookType> books = new ArrayList<HarryPotterBookType>();
        assertEquals("0.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void oneBookPriceShouldBe8EUR() {
        List<HarryPotterBookType> books = Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 1));
        assertEquals("8.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoBooksOfSameTypeShouldBe16EUR() {
        List<HarryPotterBookType> books = Lists.newArrayList(new HarryPotterBookType(BookSeriesNumber.One, 2));
        assertEquals("16.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoDifferentBooksShouldReceive5PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 1)).withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1)).build();
        assertEquals("15.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void threeDifferentBooksShouldReceive10PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Three, 1))
                .build();
        assertEquals("21.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fourDifferentBooksShouldReceive20PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Three, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Four, 1))
                .build();
        assertEquals("25.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fiveDifferentBooksShouldReceive25PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Three, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Four, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Five, 1))
                .build();
        assertEquals("30.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldOnlyReceiveDiscountOnDifferentBooksButNotOnSameBooks() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 1))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 2))
                .build();
//       (8 * 2 * .95) + 8
        assertEquals("23.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldApplyAsManyMultiBookDiscountsAsAreAvailable() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 2))
                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 2))
                .build();
//       (8 * 2 * .80) + (8 * 2 * .80)
        assertEquals("23.20 EUR", new ShoppingCart(books).formattedPrice());
    }

//    @Test
//    public void shouldChooseBestDiscountCombination() {
////        It isn’t 5*8*0.75+3*8*0.90. It is in fact 4*8*0.8+4*8*0.8.
//
////        It isn’t
////        numberOfDifferentBooks * HarryPotterBookType.STANDARD_PRICE * DiscountPercentages.get(5 differnt books) + 3*8*0.90.
//        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(BookSeriesNumber.One, 2))
//                .withBook(new HarryPotterBookType(BookSeriesNumber.Two, 2))
//                .withBook(new HarryPotterBookType(BookSeriesNumber.Three, 2))
//                .withBook(new HarryPotterBookType(BookSeriesNumber.Four, 1))
//                .withBook(new HarryPotterBookType(BookSeriesNumber.Five, 1))
//                .build();
//        assertEquals("51.20 EUR", new ShoppingCart(books).formattedPrice());
//
//    }

}
