import java.util.Objects;

/**
 * Represents a book in the recommendation system.
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private double averageRating;
    private int ratingCount;

    public Book(
            String title,
            String author,
            String genre,
            double averageRating) {

        validateText(title, "Book title");
        validateText(author, "Author");
        validateText(genre, "Genre");

        if (averageRating < 0 ||
                averageRating > 5) {

            throw new IllegalArgumentException(
                    "Rating must be between 0 and 5."
            );
        }

        this.id = 0;
        this.title = title.trim();
        this.author = author.trim();
        this.genre = genre.trim();

        this.averageRating = averageRating;
        this.ratingCount = 0;
    }

    public Book(
            int id,
            String title,
            String author,
            String genre,
            double averageRating,
            int ratingCount) {

        this(
                title,
                author,
                genre,
                averageRating
        );

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid book ID."
            );
        }

        if (ratingCount < 0) {
            throw new IllegalArgumentException(
                    "Rating count cannot be negative."
            );
        }

        this.id = id;
        this.ratingCount = ratingCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setTitle(String title) {

        validateText(title, "Book title");

        this.title = title.trim();
    }

    public void setAuthor(String author) {

        validateText(author, "Author");

        this.author = author.trim();
    }

    public void setGenre(String genre) {

        validateText(genre, "Genre");

        this.genre = genre.trim();
    }

    public void addRating(int rating) {

        if (rating < 1 || rating > 5) {

            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5."
            );
        }

        double total =
                averageRating * ratingCount;

        ratingCount++;

        averageRating =
                (total + rating)
                        / ratingCount;
    }

    public boolean isGenre(String genre) {

        return genre != null &&
                this.genre.equalsIgnoreCase(
                        genre.trim()
                );
    }

    public boolean isAuthor(String author) {

        return author != null &&
                this.author.equalsIgnoreCase(
                        author.trim()
                );
    }

    private void validateText(
            String value,
            String field) {

        if (value == null ||
                value.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    field + " cannot be empty."
            );
        }
    }

    @Override
    public String toString() {

        return String.format(
                "%d. %s | %s | %s | Rating: %.2f",
                id,
                title,
                author,
                genre,
                averageRating
        );
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book)) {
            return false;
        }

        Book other = (Book) obj;

        return id > 0 &&
                id == other.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}