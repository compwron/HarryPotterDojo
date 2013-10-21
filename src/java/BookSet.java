import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode
public class BookSet {
    private final List<HarryPotterBookType> books;

    @Getter
    private BookSetType bookSetType;

    public BookSet(List<HarryPotterBookType> books) {
        this.books = books;
        this.bookSetType = bookSetTypeOf(findBooksPresent(books));
    }

    private BookSetType bookSetTypeOf(HashMap<HarryPotterNovel, Boolean> booksPresent) {
        int numberOfTypesOfBooks = 0;
        for (HarryPotterNovel bookType : booksPresent.keySet()) {
            if (booksPresent.get(bookType)) {
                numberOfTypesOfBooks++;
            }
        }
        return BookSetType.valueOf(numberOfTypesOfBooks);
    }

    private HashMap<HarryPotterNovel, Boolean> findBooksPresent(List<HarryPotterBookType> books) {
        HashMap<HarryPotterNovel, Boolean> booksPresent = new HashMap<HarryPotterNovel, Boolean>();
        for (HarryPotterBookType book : books) {
            booksPresent.put(book.getHarryPotterNovel(), true);
        }
        return booksPresent;
    }

    public BookSet removeLargestSet() {
        return new BookSet(removeOneOfEachBook(books));
    }

    private List<HarryPotterBookType> removeOneOfEachBook(List<HarryPotterBookType> books) {
        List<HarryPotterBookType> reducedBookList = new ArrayList<HarryPotterBookType>();
        for (HarryPotterBookType book : books) {
            HarryPotterBookType bookWithDecrementedCount = decrementBookCount(book);
            if (bookWithDecrementedCount.hasBooks()) {
                reducedBookList.add(bookWithDecrementedCount);
            }
        }
        return reducedBookList;
    }

    private HarryPotterBookType decrementBookCount(HarryPotterBookType book) {
        int reducedCount = book.getBookCount() - 1;
        int validReducedCount = reducedCount >= 0 ? reducedCount : 0;
        return new HarryPotterBookType(book.getHarryPotterNovel(), validReducedCount);
    }

    public double priceOfLargestSet() {
        return DiscountPercentages.get(bookSetType) * HarryPotterBookType.STANDARD_PRICE * bookSetType.numberOfTypesOfBooks;
    }

    public String toString() {
        return "[BookSetType: " + bookSetType + "]";
    }

    public boolean hasNoMoreMultiSets() {
        return bookSetType.numberOfTypesOfBooks < 1;
    }
}
