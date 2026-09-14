import java.util.List;
import java.util.Scanner;

// it is the main class of the code 

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static User currentUser;
    private static FileManager fileManager;
    private static RecommendationEngine recommendationEngine;

    public static void main(String[] args) {

        System.out.println("\n ");
        System.out.println("     PERSONALIZED BOOK RECOMMENDATION SYSTEM");
        System.out.println("");

        try {

            fileManager = new FileManager();
            fileManager.initializeData();

            recommendationEngine =
                    new RecommendationEngine(fileManager);

            runApplication();

        } catch (Exception e) {

            System.out.println(
                    "\nError: " + e.getMessage()
            );

        } finally {

            scanner.close();
        }
    }

    private static void runApplication() {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        createUserProfile();
                        break;

                    case 2:
                        addPreferences();
                        break;

                    case 3:
                        rateBook();
                        break;

                    case 4:
                        viewProfile();
                        break;

                    case 5:
                        generateRecommendations();
                        break;

                    case 6:
                        viewRecommendationHistory();
                        break;

                    case 7:
                        running = false;
                        System.out.println(
                                "\nThank you for using the system!"
                        );
                        break;

                    default:
                        System.out.println(
                                "\nInvalid choice. Please select 1-7."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "\nError: " + e.getMessage()
                );
            }
        }
    }

    private static void displayMenu() {

        System.out.println("\n");
        System.out.println("                 MAIN MENU");
        System.out.println(" ");

        System.out.println("1. Create User Profile");
        System.out.println("2. Add / Update Preferences");
        System.out.println("3. Rate a Book");
        System.out.println("4. View My Profile");
        System.out.println("5. Get Book Recommendations");
        System.out.println("6. View Recommendation History");
        System.out.println("7. Exit");

        System.out.println(" ");
    }

    private static void createUserProfile()
            throws Exception {

        System.out.println("\n           CREATE USER PROFILE            ");

        String name = readString("Enter your name: ");
        String email = readString("Enter your email: ");

        User user = new User(name, email);

        fileManager.saveUser(user);

        currentUser = user;

        System.out.println(
                "Profile created successfully."
        );
    }

    private static void addPreferences()
            throws Exception {

        if (!checkUser()) {
            return;
        }

        System.out.println("\n           UPDATE PREFERENCES            ");

        System.out.println("1. Fiction");
        System.out.println("2. Science");
        System.out.println("3. Mystery");
        System.out.println("4. Romance");
        System.out.println("5. Technology");
        System.out.println("6. Self Help");
        System.out.println("7. Fantasy");
        System.out.println("8. Clear Preferences");

        int choice =
                readInt("Select preferred genre: ");

        if (choice == 8) {

            currentUser.clearPreferences();

            fileManager.updateUser(
                    currentUser
            );

            System.out.println(
                    "Preferences cleared successfully."
            );

            return;
        }

        String genre = getGenre(choice);

        if (genre == null) {

            System.out.println(
                    "Invalid genre choice."
            );

            return;
        }

        String author =
                readString(
                        "Enter preferred author " +
                        "(or press Enter to skip): "
                );

        currentUser.setPreferredGenre(genre);
        currentUser.setPreferredAuthor(author);

        fileManager.updateUser(
                currentUser
        );

        System.out.println(
                "Preferences updated successfully."
        );
    }

    private static void rateBook()
            throws Exception {

        if (!checkUser()) {
            return;
        }

        System.out.println("\n           RATE A BOOK            ");

        String title =
                readString("Enter book title: ");

        Book book =
                fileManager.findBookByTitle(title);

        if (book == null) {

            System.out.println(
                    "Book not found."
            );

            System.out.println(
                    "\nAvailable books:"
            );

            List<Book> books =
                    fileManager.getAllBooks();

            for (Book availableBook : books) {
                System.out.println(availableBook);
            }

            return;
        }

        System.out.println("\nSelected Book:");
        System.out.println(book);

        int rating =
                readInt("Enter rating (1-5): ");

        if (rating < 1 || rating > 5) {

            System.out.println(
                    "Rating must be between 1 and 5."
            );

            return;
        }

        fileManager.saveRating(
                currentUser.getId(),
                book.getId(),
                rating
        );

        currentUser.addRating(
                book.getId(),
                rating
        );

        System.out.println(
                "Rating saved successfully."
        );
    }

    private static void viewProfile() {

        if (!checkUser()) {
            return;
        }

        System.out.println(currentUser);
    }

    private static void generateRecommendations()
            throws Exception {

        if (!checkUser()) {
            return;
        }

        recommendationEngine.generateRecommendations(
                currentUser
        );
    }

    private static void viewRecommendationHistory() {

        if (!checkUser()) {
            return;
        }

        recommendationEngine.displayRecommendationHistory(
                currentUser.getId()
        );
    }

    private static boolean checkUser() {

        if (currentUser == null) {

            System.out.println(
                    "\nPlease create a user profile first."
            );

            return false;
        }

        return true;
    }

    private static String getGenre(int choice) {

        switch (choice) {

            case 1:
                return "Fiction";

            case 2:
                return "Science";

            case 3:
                return "Mystery";

            case 4:
                return "Romance";

            case 5:
                return "Technology";

            case 6:
                return "Self Help";

            case 7:
                return "Fantasy";

            default:
                return null;
        }
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }
}