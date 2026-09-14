import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file-based data storage.
 *
 * No SQL or database is required.
 */
public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String BOOK_FILE =
            DATA_FOLDER + File.separator + "books.txt";

    private static final String USER_FILE =
            DATA_FOLDER + File.separator + "users.txt";

    private static final String RATING_FILE =
            DATA_FOLDER + File.separator + "ratings.txt";

    private final List<Book> books;

    public FileManager() {

        books = new ArrayList<>();
    }

    /**
     * Creates required files and loads book data.
     */
    public void initializeData()
            throws IOException {

        File folder =
                new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        createFileIfNeeded(BOOK_FILE);
        createFileIfNeeded(USER_FILE);
        createFileIfNeeded(RATING_FILE);

        loadBooks();

        if (books.isEmpty()) {
            addSampleBooks();
        }

        System.out.println(
                "File storage initialized successfully."
        );
    }

    private void createFileIfNeeded(
            String fileName)
            throws IOException {

        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads books from books.txt.
     */
    private void loadBooks()
            throws IOException {

        books.clear();

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        BOOK_FILE
                                )
                        )
        ) {

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data =
                        line.split(",", -1);

                if (data.length != 6) {
                    continue;
                }

                try {

                    int id =
                            Integer.parseInt(
                                    data[0]
                            );

                    String title = data[1];
                    String author = data[2];
                    String genre = data[3];

                    double rating =
                            Double.parseDouble(
                                    data[4]
                            );

                    int count =
                            Integer.parseInt(
                                    data[5]
                            );

                    books.add(
                            new Book(
                                    id,
                                    title,
                                    author,
                                    genre,
                                    rating,
                                    count
                            )
                    );

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Skipping invalid book data."
                    );
                }
            }
        }
    }

//     add the books if initial file is empty 

    private void addSampleBooks()
            throws IOException {

        books.add(
                new Book(
                        1,
                        "Clean Code",
                        "Robert C. Martin",
                        "Technology",
                        4.7,
                        100
                )
        );

        books.add(
                new Book(
                        2,
                        "The Pragmatic Programmer",
                        "Andrew Hunt",
                        "Technology",
                        4.8,
                        120
                )
        );

        books.add(
                new Book(
                        3,
                        "Atomic Habits",
                        "James Clear",
                        "Self Help",
                        4.8,
                        150
                )
        );

        books.add(
                new Book(
                        4,
                        "The Alchemist",
                        "Paulo Coelho",
                        "Fiction",
                        4.6,
                        200
                )
        );

        books.add(
                new Book(
                        5,
                        "Harry Potter",
                        "J.K. Rowling",
                        "Fantasy",
                        4.9,
                        300
                )
        );

        books.add(
                new Book(
                        6,
                        "The Da Vinci Code",
                        "Dan Brown",
                        "Mystery",
                        4.5,
                        180
                )
        );

        books.add(
                new Book(
                        7,
                        "A Brief History of Time",
                        "Stephen Hawking",
                        "Science",
                        4.7,
                        140
                )
        );

        books.add(
                new Book(
                        8,
                        "Pride and Prejudice",
                        "Jane Austen",
                        "Romance",
                        4.6,
                        160
                )
        );

        books.add(
                new Book(
                        9,
                        "Introduction to Algorithms",
                        "Thomas H. Cormen",
                        "Technology",
                        4.7,
                        130
                )
        );

        books.add(
                new Book(
                        10,
                        "Rich Dad Poor Dad",
                        "Robert Kiyosaki",
                        "Self Help",
                        4.5,
                        170
                )
        );

        saveBooks();
    }

    
//  Returns all books.
    
    public List<Book> getAllBooks() {

        return new ArrayList<>(books);
    }

//     Finds a book by ID.

    public Book getBookById(int bookId) {

        for (Book book : books) {

            if (book.getId() == bookId) {
                return book;
            }
        }

        return null;
    }

//  Finds a book by ID.

    public Book findBookByTitle(
            String title) {

        if (title == null ||
                title.trim().isEmpty()) {

            return null;
        }

        for (Book book : books) {

            if (book.getTitle()
                    .equalsIgnoreCase(
                            title.trim()
                    )) {

                return book;
            }
        }

        return null;
    }

//     saves a new user 


    public void saveUser(User user)
            throws IOException {

        int newId = getNextUserId();

        user.setId(newId);

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        USER_FILE,
                                        true
                                )
                        )
        ) {

            writer.write(
                    user.getId() + "," +
                    user.getName() + "," +
                    user.getEmail() + "," +
                    user.getPreferredGenre() + "," +
                    user.getPreferredAuthor()
            );

            writer.newLine();
        }
    }

   
//  Updates an existing user's preferences.
     
    public void updateUser(User user)
            throws IOException {

        List<String> lines =
                new ArrayList<>();

        File file =
                new File(USER_FILE);

        if (!file.exists()) {
            return;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

            String line;

            while ((line = reader.readLine())
                    != null) {

                String[] data =
                        line.split(",", -1);

                if (data.length >= 5
                        && data[0].equals(
                                String.valueOf(
                                        user.getId()
                                )
                        )) {

                    lines.add(
                            user.getId() + "," +
                            user.getName() + "," +
                            user.getEmail() + "," +
                            user.getPreferredGenre() + "," +
                            user.getPreferredAuthor()
                    );

                } else {

                    lines.add(line);
                }
            }
        }

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(file)
                        )
        ) {

            for (String line : lines) {

                writer.write(line);
                writer.newLine();
            }
        }
    }

//     Saves a user rating.

    public void saveRating(
            int userId,
            int bookId,
            int rating)
            throws IOException {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        RATING_FILE,
                                        true
                                )
                        )
        ) {

            writer.write(
                    userId + "," +
                    bookId + "," +
                    rating
            );

            writer.newLine();
        }
    }
// Saves book data.

    private void saveBooks()
            throws IOException {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(
                                        BOOK_FILE
                                )
                        )
        ) {

            for (Book book : books) {

                writer.write(
                        book.getId() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getGenre() + "," +
                        book.getAverageRating() + "," +
                        book.getRatingCount()
                );

                writer.newLine();
            }
        }
    }

    
//  Generates the next user ID.

    private int getNextUserId()
            throws IOException {

        int maxId = 0;

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(
                                        USER_FILE
                                )
                        )
        ) {

            String line;

            while ((line = reader.readLine())
                    != null) {

                String[] data =
                        line.split(",", -1);

                if (data.length > 0) {

                    try {

                        int id =
                                Integer.parseInt(
                                        data[0]
                                );

                        if (id > maxId) {
                            maxId = id;
                        }

                    } catch (
                            NumberFormatException e) {

                        // Ignore invalid ID
                    }
                }
            }
        }

        return maxId + 1;
    }
}