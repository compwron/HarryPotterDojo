import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    public static int STANDARD_PRICE = 8;
    private final BookType bookType;
    private final int bookCount;
}
