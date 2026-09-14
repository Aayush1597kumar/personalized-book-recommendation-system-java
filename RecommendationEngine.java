import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// it geneartes Recommendation of the books 

public class RecommendationEngine {

    private final FileManager fileManager;

    private final Map<Integer, List<Integer>>
            recommendationHistory;

    public RecommendationEngine(
            FileManager fileManager) {

        if (fileManager == null) {

            throw new IllegalArgumentException(
                    "File manager cannot be null."
            );
        }

        this.fileManager = fileManager;

        recommendationHistory =
                new HashMap<>();
    }

    public void generateRecommendations(
            User user) {

        if (user == null) {

            System.out.println(
                    "User profile not found."
            );

            return;
        }

        List<Book> books =
                fileManager.getAllBooks();

        if (books.isEmpty()) {

            System.out.println(
                    "No books available."
            );

            return;
        }

        List<BookScore> recommendations =
                new ArrayList<>();

        for (Book book : books) {

            if (user.hasRated(book.getId())) {
                continue;
            }

            double score =
                    calculateScore(user, book);

            recommendations.add(
                    new BookScore(
                            book,
                            score
                    )
            );
        }

        recommendations.sort(
                Comparator.comparingDouble(
                        BookScore::getScore
                ).reversed()
        );

        if (recommendations.isEmpty()) {

            System.out.println(
                    "\nYou have rated all available books."
            );

            return;
        }

        List<Integer> history =
                recommendationHistory.computeIfAbsent(
                        user.getId(),
                        key -> new ArrayList<>()
                );

        int limit =
                Math.min(
                        5,
                        recommendations.size()
                );

        System.out.println(
                "\n                                     "
        );

        System.out.println(
                "     PERSONALIZED RECOMMENDATIONS"
        );

        System.out.println(
                "                                        "
        );

        for (int i = 0; i < limit; i++) {

            BookScore result =
                    recommendations.get(i);

            System.out.printf(
                    "%d. %s%n",
                    i + 1,
                    result
            );

            int bookId =
                    result.getBook().getId();

            if (!history.contains(bookId)) {
                history.add(bookId);
            }
        }

        System.out.println(
                "                                       "
        );

        System.out.println(
                "Recommendations generated using "
                + "your preferences and history."
        );
    }

    private double calculateScore(
            User user,
            Book book) {

        double score = 0.0;

        // Genre match = 40%
        if (!user.getPreferredGenre().isEmpty()
                && book.isGenre(
                        user.getPreferredGenre())) {

            score += 40.0;
        }

        // Author match = 20%
        if (!user.getPreferredAuthor().isEmpty()
                && book.isAuthor(
                        user.getPreferredAuthor())) {

            score += 20.0;
        }

        // Book rating = 20%
        double ratingScore =
                (book.getAverageRating() / 5.0)
                        * 20.0;

        score += ratingScore;

        // Reading history = 20%
        if (hasRelatedReadingHistory(
                user,
                book)) {

            score += 20.0;
        }

        return score;
    }

    private boolean hasRelatedReadingHistory(
            User user,
            Book book) {

        for (Integer bookId :
                user.getReadingHistory()) {

            Book previousBook =
                    fileManager.getBookById(
                            bookId
                    );

            if (previousBook != null
                    && previousBook.isGenre(
                            book.getGenre())) {

                return true;
            }
        }

        return false;
    }

    public void displayRecommendationHistory(
            int userId) {

        List<Integer> history =
                recommendationHistory.get(userId);

        System.out.println(
                "\n"
        );

        System.out.println(
                "       RECOMMENDATION HISTORY"
        );

        System.out.println(
                " "
        );

        if (history == null ||
                history.isEmpty()) {

            System.out.println(
                    "No recommendation history available."
            );

            return;
        }

        int count = 1;

        for (Integer bookId : history) {

            Book book =
                    fileManager.getBookById(
                            bookId
                    );

            if (book != null) {

                System.out.println(
                        count + ". " + book
                );

                count++;
            }
        }
    }

    /**
     * Stores a book and its recommendation score.
     */
    private static class BookScore {

        private final Book book;
        private final double score;

        public BookScore(
                Book book,
                double score) {

            this.book = book;
            this.score = score;
        }

        public Book getBook() {
            return book;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {

            return String.format(
                    "%s | Match Score: %.2f%%",
                    book,
                    score
            );
        }
    }
}