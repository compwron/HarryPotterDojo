import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShoppingCartTest {
    @Test
    public void shouldCalculateCostOfZeroBooks() {
        List<HarryPotterBookType> books = new ArrayList<HarryPotterBookType>();
        assertEquals("0.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void oneBookPriceShouldBe8EUR() {
        List<HarryPotterBookType> books = Lists.newArrayList(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1));
        assertEquals("8.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoBooksOfSameTypeShouldBe16EUR() {
        List<HarryPotterBookType> books = Lists.newArrayList(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 2));
        assertEquals("16.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void twoDifferentBooksShouldReceive5PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1)).withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 1)).build();
        assertEquals("15.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void threeDifferentBooksShouldReceive10PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.PrisonerOfAzkaban, 1))
                .build();
        assertEquals("21.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fourDifferentBooksShouldReceive20PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.PrisonerOfAzkaban, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.GobletOfFire, 1))
                .build();
        assertEquals("25.60 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void fiveDifferentBooksShouldReceive25PercentDiscount() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.PrisonerOfAzkaban, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.GobletOfFire, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.OrderOfThePhoenix, 1))
                .build();
        assertEquals("30.00 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldOnlyReceiveDiscountOnDifferentBooksButNotOnSameBooks() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 2))
                .build();
//       (8 * 2 * .95) + 8
        assertEquals("23.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldApplyAsManyMultiBookDiscountsAsAreAvailable() {
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 2))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 2))
                .build();
        assertEquals("23.20 EUR", new ShoppingCart(books).formattedPrice());
    }

    @Test
    public void shouldChooseBestDiscountCombination() {
//        It isn’t 5*8*0.75+3*8*0.90. It is in fact 4*8*0.8+4*8*0.8.
        List<HarryPotterBookType> books = new BooksBuilder().withBook(new HarryPotterBookType(HarryPotterNovel.PhilosophersStone, 2))
                .withBook(new HarryPotterBookType(HarryPotterNovel.ChamberOfSecrets, 2))
                .withBook(new HarryPotterBookType(HarryPotterNovel.PrisonerOfAzkaban, 2))
                .withBook(new HarryPotterBookType(HarryPotterNovel.GobletOfFire, 1))
                .withBook(new HarryPotterBookType(HarryPotterNovel.OrderOfThePhoenix, 1))
                .build();
        assertEquals("51.20 EUR", new ShoppingCart(books).formattedPrice());
    }
}
