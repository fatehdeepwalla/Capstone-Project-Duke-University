# Movie Recommendation System

- A Java-based movie recommendation system developed as the capstone project for Duke University's online **Java Programming and Software Engineering Fundamentals Specialization**.
- It applies object-oriented programming, CSV data processing, filtering, rating analysis, and similarity-based recommendation techniques to a movie recommendation problem.
- The repository contains the Java implementation and supporting datasets.

---
## How It Works

  The recommendation process is organized into several stages:
- **Movie data processing:** Load movie metadata and user ratings from CSV files.
- **Average rating calculation:** Calculate average ratings for movies, with a configurable minimum number of raters.
- **Similar-user identification:** Compare users' rating patterns to identify users with similar preferences.
- **Movie recommendations:** Aggregate ratings from similar users to rank potential recommendations.
- **Flexible filtering:** Filter movies by genre, director, release year, or runtime.
- **Combined filters:** Apply multiple filtering conditions together using a composite filter.
- **Recommendation output:** Generate a table containing the recommended movies and their associated details.

---
## Tech Stack

| Technology | Role |
|---|---|
| Java | Programming language and recommendation logic |
| BlueJ | Development environment and project organization |
| Apache Commons CSV | Parsing CSV data |
| Duke `edu.duke` library | File utilities used by the project |
| CSV Datasets | Movie metadata and user ratings |

---
## Dataset

  The repository includes the following data files under `Capstone Project/data/`:
  
  | File | Purpose |
  |---|---|
  | `ratedmoviesfull.csv` | Movie metadata |
  | `ratings.csv` | User ratings associated with movies |
  | `ratedmovies_short.csv` | Smaller movie dataset for testing |
  | `ratings_short.csv` | Smaller rating dataset for testing |
  
  These datasets allow the project to load movie information and rating histories for analysis and recommendation calculations.

---

