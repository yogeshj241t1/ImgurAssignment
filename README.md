Imgur Gallery App - MVVM Architecture Android Assignment
This repository contains an Android application showcasing the implementation of the MVVM architecture with modern Android libraries.

Project Highlights:

MVVM Architecture: Leverages the well-established Model-View-ViewModel pattern for separation of concerns.
Kotlin: Utilizes Kotlin for concise and expressive code.
Dependency Injection (Dagger): Manages dependencies effectively for better testability and maintainability.
Networking (Retrofit): Handles asynchronous network communication with the Imgur API.
Asynchronous Programming (Coroutines, Flow, StateFlow): Employs coroutines for efficient handling of asynchronous tasks and leverages Flow and StateFlow for data stream management.
Project Structure:

data: Contains classes representing data models and logic related to data access and manipulation.
di: Houses dependency injection components and interfaces ensuring clean dependency management.
ui: Includes view classes (Activities, Fragments, etc.) and their corresponding ViewModels, facilitating separation of UI logic and data binding.
utils: Provides utility classes for common functionalities and helper methods.
APIs Used:

Imgur API: Leverages the Imgur API (https://api.imgur.com/) for fetching and searching images.
