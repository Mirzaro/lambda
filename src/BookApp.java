import java.time.LocalDate;
import lambda.Book;
import lambda.Person;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class BookApp {
    public static void main(String[] args) {
        // Maak de auteurs (Person-objecten) aan
        Person author1 = new Person("Carissa", "Broadsent", LocalDate.of(2022, 8, 30));
        Person author2 = new Person("Brandon", "Taylor", LocalDate.of(2023, 5, 23));
        Person author3 = new Person("George", "Orwell", LocalDate.of(1949, 7, 8));
        Person author4 = new Person("J.K.", "Rowling", LocalDate.of(1997, 6, 26));
        Person author5 = new Person("Hannah", "Grace", LocalDate.of(2022, 8, 23));
        // Maak een Book-array aan en vul deze met 5 boeken
        Book[] books = new Book[5];
        books[0] = new Book("Book 1", author1, LocalDate.of(2020, 3, 10), "Thriller");
        books[1] = new Book("Book 2", author2, LocalDate.of(2015, 7, 20), "Fantasy");
        books[2] = new Book("Book 3", author3, LocalDate.of(2018, 11, 5), "Mystery");
        books[3] = new Book("Book 4", author4, LocalDate.of(2005, 9, 12), "Romance");
        books[4] = new Book("Book 5", author5, LocalDate.of(2012, 6, 25), "Science Fiction");

        // Roep de verschillende methoden aan en print de resultaten af
        Book newestBook = getNewestBook(books);
        System.out.println("Newest Book: " + newestBook.getTitle());

        System.out.println("Youngest Writer:");
        printYoungestWriter(books);

        System.out.println("Books sorted by title:");
        printSortedByTitle(books);

        System.out.println("Books per author:");
        countBooksPerAuthor(books);


    }

    public static Book getNewestBook(Book[] books) {
        return Arrays.stream(books)
                .max(Comparator.comparing(Book::getReleaseDate))
                .orElse(null);
    }

    public static void printYoungestWriter(Book[] books) {
        Person youngestWriter = Arrays.stream(books)
                .map(Book::getAuthor)
                .min(Comparator.comparing(Person::getDateOfBirth))
                .orElse(null);

        System.out.println("Youngest Writer: " + youngestWriter.getFirstName() + " " + youngestWriter.getLastName());
    }

    public static void printSortedByTitle(Book[] books) {
        Arrays.stream(books)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    public static void countBooksPerAuthor(Book[] books) {
        Map<Person, Long> booksPerAuthor = Arrays.stream(books)
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));

        booksPerAuthor.forEach((author, count) -> System.out.println(author.getFirstName() + " " + author.getLastName() + ": " + count));
    }}


