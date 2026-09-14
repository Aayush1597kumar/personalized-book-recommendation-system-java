import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// it control the user 

public class User {

    private int id;
    private String name;
    private String email;

    private String preferredGenre;
    private String preferredAuthor;

    private final Map<Integer, Integer> ratings;
    private final ArrayList<Integer> readingHistory;

    public User(String name, String email) {

        validateName(name);
        validateEmail(email);

        this.id = 0;
        this.name = name.trim();
        this.email = email.trim();

        this.preferredGenre = "";
        this.preferredAuthor = "";

        ratings = new HashMap<>();
        readingHistory = new ArrayList<>();
    }

    public User(
            int id,
            String name,
            String email,
            String preferredGenre,
            String preferredAuthor) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID."
            );
        }

        validateName(name);
        validateEmail(email);

        this.id = id;
        this.name = name.trim();
        this.email = email.trim();

        this.preferredGenre =
                preferredGenre == null
                        ? ""
                        : preferredGenre.trim();

        this.preferredAuthor =
                preferredAuthor == null
                        ? ""
                        : preferredAuthor.trim();

        ratings = new HashMap<>();
        readingHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPreferredGenre() {
        return preferredGenre;
    }

    public String getPreferredAuthor() {
        return preferredAuthor;
    }

    public Map<Integer, Integer> getRatings() {
        return new HashMap<>(ratings);
    }

    public ArrayList<Integer> getReadingHistory() {
        return new ArrayList<>(readingHistory);
    }

    public void setId(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Invalid user ID."
            );
        }

        this.id = id;
    }

    public void setName(String name) {

        validateName(name);

        this.name = name.trim();
    }

    public void setEmail(String email) {

        validateEmail(email);

        this.email = email.trim();
    }

    public void setPreferredGenre(
            String genre) {

        preferredGenre =
                genre == null
                        ? ""
                        : genre.trim();
    }

    public void setPreferredAuthor(
            String author) {

        preferredAuthor =
                author == null
                        ? ""
                        : author.trim();
    }

    public void addRating(
            int bookId,
            int rating) {

        if (bookId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid book ID."
            );
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5."
            );
        }

        ratings.put(bookId, rating);

        addToReadingHistory(bookId);
    }

    public void addToReadingHistory(
            int bookId) {

        if (bookId <= 0) {
            throw new IllegalArgumentException(
                    "Invalid book ID."
            );
        }

        if (!readingHistory.contains(bookId)) {
            readingHistory.add(bookId);
        }
    }

    public boolean hasRated(int bookId) {
        return ratings.containsKey(bookId);
    }

    public int getRating(int bookId) {
        return ratings.getOrDefault(bookId, 0);
    }

    public double getAverageRating() {

        if (ratings.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (int rating : ratings.values()) {
            total += rating;
        }

        return (double) total / ratings.size();
    }

    public boolean hasRead(int bookId) {
        return readingHistory.contains(bookId);
    }

    public void clearPreferences() {

        preferredGenre = "";
        preferredAuthor = "";
    }

    private void validateName(String name) {

        if (name == null ||
                name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Name cannot be empty."
            );
        }
    }

    private void validateEmail(String email) {

        if (email == null ||
                email.trim().isEmpty() ||
                !email.contains("@")) {

            throw new IllegalArgumentException(
                    "Please enter a valid email."
            );
        }
    }

    @Override
    public String toString() {

        return "\n                                        " +
                "\n              USER PROFILE" +
                "\n                                       " +
                "\nID              : " + id +
                "\nName            : " + name +
                "\nEmail           : " + email +
                "\nPreferred Genre : " +
                (preferredGenre.isEmpty()
                        ? "Not specified"
                        : preferredGenre) +
                "\nPreferred Author: " +
                (preferredAuthor.isEmpty()
                        ? "Not specified"
                        : preferredAuthor) +
                "\nBooks Rated     : " +
                ratings.size() +
                "\nAverage Rating  : " +
                String.format(
                        "%.2f",
                        getAverageRating()
                ) +
                "\n                                     ";
    }
}