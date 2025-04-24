# 🎬 Simple-Movie-Hub

An Android application built with Java that helps users explore, manage, and get notified about movies they want to watch. Users can browse movies by categories, save favorites to a watchlist, and set reminders to watch later.

## 🚀 Features

- 🔐 User **Login / Registration** via Firebase Authentication
- ☁️ **Firebase Realtime Database** for storing user data (Watchlist, Reminders)
- 🎥 Browse movies by categories:
    - **Top Rated**
    - **Now Playing**
    - **Popular**
    - **Upcoming**
- ⭐ Add movies to a **Watchlist**
- ⏰ Set a **Reminder** using WorkManager to get notified before watching
- ⚙️ **Settings tab** to:
    - Filter movies by:
        - Minimum Rating (e.g., 7+)
        - Release Year (e.g., from 2020)
        - Suitable for children

## 🛠 Tech Stack & Libraries

| Technology               | Purpose                           |
|--------------------------|-----------------------------------|
| **Java**                 | Main programming language         |
| **Firebase Auth**        | User authentication               |
| **Firebase Database**    | Store watchlist and user settings |
| **Retrofit**             | Consume Movie API                 |
| **RxJava**               | Handle asynchronous data          |
| **Paging Library**       | Efficiently load paginated movies |
| **Hilt**                 | Dependency Injection              |
| **Picasso**              | Load and cache movie posters      |
| **Navigation Component** | In-app screen navigation          |
| **WorkManager**          | Schedule notifications/reminders  |


## 🎬 Movie Data Source

This app uses the [TMDB (The Movie Database) API](https://www.themoviedb.org/documentation/api) for fetching movie information.