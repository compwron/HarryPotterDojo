import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    public static int STANDARD_PRICE = 8;
    private final HarryPotterNovel harryPotterNovel;
    private final int bookCount;

    public boolean hasBooks() {
        return bookCount > 0;
    }
}
