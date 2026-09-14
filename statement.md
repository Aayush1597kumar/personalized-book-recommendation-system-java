# Project Statement

## Personalized Book Recommendation System Using Java

---

## 1. Problem Statement

With the increasing number of books available across different genres and authors, readers often find it difficult to identify books that match their individual interests.

Traditional book lists and basic search systems generally provide the same information to every user and do not consider personal preferences, previous reading choices, or ratings.

The **Personalized Book Recommendation System** addresses this problem by providing book recommendations based on a user's preferred genre, preferred author, book ratings, and reading history.

The system uses a **weighted recommendation scoring algorithm** to evaluate available books, rank them according to their relevance to the user, and display the most suitable recommendations.

The project is implemented as a **Java-based console application** and demonstrates practical use of Object-Oriented Programming, Collections, File I/O, exception handling, validation, and sorting techniques.

---

## 2. Project Objectives

The main objectives of the project are:

- To develop a Java-based personalized book recommendation system.
- To collect and manage user preferences such as genre and author.
- To allow users to rate books on a scale of 1 to 5.
- To maintain basic reading history for personalization.
- To calculate recommendation scores using multiple preference factors.
- To rank books according to their calculated scores.
- To display relevant and personalized book recommendations.
- To demonstrate the practical application of Java programming concepts.

---

## 3. Scope of the Project

The project focuses on providing personalized book recommendations through a console-based Java application.

The scope includes:

- User profile creation and management.
- Selection of preferred book genre.
- Selection of preferred author.
- Book search by title.
- Book rating from 1 to 5.
- Reading history tracking.
- Weighted recommendation score calculation.
- Ranking and displaying the top five recommendations.
- Maintaining recommendation history during the application session.
- File-based storage of books, users, and ratings.

### Recommendation Criteria

The recommendation score is calculated using the following weighted factors:

| Factor | Weight |
|---|---:|
| Genre Match | 40% |
| Author Match | 20% |
| Book Rating | 20% |
| Reading History | 20% |
| **Total** | **100%** |

The current version is designed as an academic Java application and does not use an SQL database or web interface.

---

## 4. Target Users

The system is primarily intended for:

### Students
Students who want to discover books related to their academic, technical, or personal interests.

### General Readers
Readers who want personalized book suggestions based on their preferred genres and authors.

### Book Enthusiasts
Users who regularly read books and want recommendations influenced by their previous reading choices and ratings.

### Academic Users
The project can also serve as a practical demonstration of Java concepts such as OOP, Collections, File I/O, exception handling, validation, and sorting.

---

## 5. High-Level Features

### 5.1 User Profile Management

Users can create a profile by providing basic information such as:

- Name
- Email

The system assigns a unique user ID to each profile.

---

### 5.2 Preference Management

Users can specify their preferred:

- Genre
- Author

These preferences are used by the recommendation engine when calculating book scores.

---

### 5.3 Book Management

The system maintains a collection of books containing information such as:

- Book ID
- Title
- Author
- Genre
- Average Rating
- Rating Count

Books are stored using a file-based storage mechanism.

---

### 5.4 Book Rating

Users can search for a book by title and provide a rating between **1 and 5**.

The rating is stored and associated with the user's profile and reading history.

---

### 5.5 Personalized Recommendation Engine

The recommendation engine evaluates available books using the user's preferences and reading history.

Each candidate book receives a score based on:

- Genre similarity
- Author similarity
- Existing book rating
- Reading-history relationship

Books that have already been rated by the user are excluded from new recommendations.

---

### 5.6 Recommendation Ranking

After calculating the score of each eligible book, the system sorts the books in descending order of their recommendation score.

The **top five highest-scoring books** are displayed to the user.

---

### 5.7 Recommendation History

The system maintains a record of books recommended during the current application session, allowing the user to review previously generated recommendations.

---

### 5.8 File-Based Data Storage

The project uses Java File I/O instead of an SQL database.

The following files are used:

```text
data/
├── books.txt
├── users.txt
└── ratings.txt
