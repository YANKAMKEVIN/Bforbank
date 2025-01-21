# BforBank Technical Test Project

## Introduction
This project was created as part of a technical test requested by BforBank. The objective of this exercise is to provide a foundation for discussion during the technical interview.

---

## Test Instructions
BforBank provided the following guidelines for this project:

### Mandatory Requirements:
- Use **Kotlin** as the programming language.
- Implement **dependency injection**.
- Include data to send to the API (e.g., text from a search field or geolocation).
- Display information received from the API (e.g., text, images, points on a map).
- Ensure API calls support **pagination**.

### Areas of Focus:
- Application architecture.
- Use of **coroutines** for asynchronous operations.
- Utilization of **Jetpack libraries**.
- Writing and implementation of **unit tests**.

---

## My Approach
Instead of creating a single project, I decided to implement **four mini-projects in one application** to showcase a broader range of skills and techniques. Each mini-project uses a different API and demonstrates varied approaches to pagination, architecture, and testing.

---

## Implemented APIs

### 1. **PokéAPI** ([https://pokeapi.co/api/v2/](https://pokeapi.co/api/v2/))
- Displays a **paginated list of Pokémon**.
- Clicking on a Pokémon provides access to its **detailed information**.

### 2. **Spotify API** ([https://api.spotify.com/](https://api.spotify.com/))
- Displays a **paginated list of tracks** based on the search query.
- Clicking on a track shows its **detailed information**.

### 3. **Unsplash API** ([https://api.unsplash.com/](https://api.unsplash.com/))
- Displays a **paginated list of photos** with descriptions based on the search query.
- Clicking on a photo shows it in a **larger view**.

### 4. **Mapbox API** ([https://api.mapbox.com/](https://api.mapbox.com/))
- Displays a **list of addresses** matching the search query.
- Clicking on an address shows **detailed information** about the selected location.

---

## Why Combine Multiple Projects?
I chose to implement multiple projects in one to:
- **Demonstrate versatility** by using different approaches to pagination.
- Showcase a **robust and clean architecture**.
- Highlight **varied testing methodologies** for different components.
- Illustrate my ability to handle **complex requirements** effectively.

---

## Technical Highlights

### Language and Frameworks:
- Full **Kotlin** implementation.
- UI entirely built with **Jetpack Compose**.

### Dependency Injection:
- **Hilt** used for dependency injection.

### Networking:
- **Retrofit** implemented for API communication.

### Key Features:
- **Robust architecture** ensuring scalability and maintainability.
- Comprehensive **unit testing** for critical classes and functionalities.
- **Multiple pagination techniques** tailored to different APIs.

---

This project is a demonstration of my ability to design, develop, and test complex Android applications while adhering to modern best practices.

Thank you for reviewing my work!
