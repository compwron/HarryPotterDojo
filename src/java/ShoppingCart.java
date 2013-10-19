import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ShoppingCart {
    private final double booksPrice;

    public ShoppingCart(List<HarryPotterBookType> books) {
        this.booksPrice = applyDiscounts(new BookSet(books), 0);
    }

    private double applyDiscounts(BookSet bookSet, double currentTotal) {
        if (bookSet.hasNoMoreMultiSets()) {
            return currentTotal + bookSet.priceOfLargestSet();
        } else {
            return applyDiscounts(bookSet.removeLargestSet(), currentTotal + bookSet.priceOfLargestSet());
        }
    }

    public String formattedPrice() {
        return formatPriceToTwoDigits(this.booksPrice) + " EUR";
    }

    private String formatPriceToTwoDigits(double booksPrice) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(booksPrice);
    }
}
