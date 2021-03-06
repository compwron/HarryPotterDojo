import com.google.common.collect.Lists;

import java.util.List;

public class Main {
    public static void main(String... args){
        List<Book> books = Lists.newArrayList();
        books.add(new Book(HarryPotterNovel.PhilosophersStone, 2));
        books.add(new Book(HarryPotterNovel.ChamberOfSecrets, 2));
        books.add(new Book(HarryPotterNovel.PrisonerOfAzkaban, 2));
        books.add(new Book(HarryPotterNovel.GobletOfFire, 1));
        books.add(new Book(HarryPotterNovel.OrderOfThePhoenix, 1));

        ShoppingCart cart = new ShoppingCart(books);

        System.out.println("Price of  2 copies of the first book, 2 copies of the second book, 2 copies of the third book, 1 copy of the fourth book, 1 copy of the fifth book: " + cart.formattedPrice());
    }
}
