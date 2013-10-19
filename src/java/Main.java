import com.google.common.collect.Lists;

import java.util.List;

public class Main {
    public static void main(String... args){
        List<HarryPotterBookType> books = Lists.newArrayList();
        books.add(new HarryPotterBookType(BookSeriesNumber.One, 2));
        books.add(new HarryPotterBookType(BookSeriesNumber.Two, 2));
        books.add(new HarryPotterBookType(BookSeriesNumber.Three, 2));
        books.add(new HarryPotterBookType(BookSeriesNumber.Four, 1));
        books.add(new HarryPotterBookType(BookSeriesNumber.Five, 1));

        ShoppingCart cart = new ShoppingCart(books);

        System.out.println("Price of  2 copies of the first book, 2 copies of the second book, 2 copies of the third book, 1 copy of the fourth book, 1 copy of the fifth book: " + cart.formattedPrice());
    }
}
