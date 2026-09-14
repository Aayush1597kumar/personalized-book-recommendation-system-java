# Project Statement

## Project Title

Personalized Book Recommendation System Using Java

---

## 1. Problem Statement

Readers often have difficulty finding books that match their interests because of the large number of books available. A simple list of books does not consider individual preferences such as favorite genres, authors, ratings, and previous reading choices.

The objective of this project is to develop a Java-based Personalized Book Recommendation System that analyzes user preferences and reading history to recommend suitable books.

The system uses a weighted scoring approach to calculate how well each book matches the user's preferences and then displays the highest-ranked recommendations.

---

## 2. Scope of the Project

The project focuses on providing personalized book recommendations through a console-based Java application.

The system includes:

- User profile creation
- Genre and author preference management
- Book rating from 1 to 5
- Reading history tracking
- Personalized recommendation generation
- Recommendation history
- File-based data storage

The project is designed as a compact academic application demonstrating Java programming, object-oriented programming, collections, exception handling, and file handling.

The current version does not use a web interface or SQL database.

---

## 3. Target Users

The primary target users are:

- Students who want to discover books based on their interests
- General readers looking for personalized book suggestions
- Users who want recommendations based on their previous ratings and reading choices

The project can also be used as an academic demonstration of how a recommendation system can be implemented using Java.

---

## 4. High-Level Features

### 1. User Profile Management

Users can create a profile by providing their name and email address.

### 2. Preference Management

Users can select their preferred genre and optionally specify a preferred author.

### 3. Book Rating

Users can search for a book by title and provide a rating between 1 and 5.

### 4. Recommendation Engine

The system calculates a recommendation score using:

- Genre match – 40%
- Author match – 20%
- Book rating – 20%
- Reading history – 20%

Books are ranked according to their calculated scores.

### 5. Personalized Recommendations

The system displays the top five books based on the user's preferences, ratings, and reading history.

### 6. Recommendation History

The system keeps track of books that have been recommended during the current application session.

### 7. File-Based Storage

The project uses Java File I/O to store:

- Book information
- User information
- Rating information

The data is stored in text files inside the `data` folder.

---

## 5. Project Workflow

The basic workflow of the system is:

Create User Profile
        ↓
Add / Update Preferences
        ↓
Rate Books
        ↓
Generate Recommendations
        ↓
Calculate Recommendation Scores
        ↓
Rank Books
        ↓
Display Top 5 Recommendations

---

## 6. Technologies Used

- Java
- Object-Oriented Programming
- Collections Framework
- Exception Handling
- File I/O
- VS Code
- Git and GitHub

---

## 7. Project Limitations

The current version is a console-based application and does not provide a graphical or web interface.

Recommendation history is maintained during the application session. More advanced versions can store recommendation history permanently and support multiple user profiles and login functionality.

## Screenshot

<img width="1920" height="1080" alt="Screenshot 2026-09-14 212511" src="https://github.com/user-attachments/assets/526704a7-1140-4f81-8144-0cb859061f74" />

<img width="1920" height="1080" alt="Screenshot 2026-09-14 212538" src="https://github.com/user-attachments/assets/bbc2f1dc-2179-466e-a209-8539b3535bb5" />

<img width="1920" height="1080" alt="Screenshot 2026-09-14 212804" src="https://github.com/user-attachments/assets/74bc1a96-3330-46cc-8ff7-a32f86f2abe3" />



