# Weather-App
Simple android app that displays current weather and list of past weathers depends on your current location.


## Project Set up
- Android Studio Jellyfish | 2023.3.1
- Programming Language: Kotlin
- Third Party API tool: Postman v9.1.1
- Gradle Plug in Version: 8.1.4 
- Gradle Version: 8.4
- JDK version: 17

## Features
- Offline login and registration
- Retrieve real-time weather data based on the user's current location.
- Offline access for previously fetched weather data

## Building the Project
Upon opening the project make sure you add this to local.properties file -> apiKey=d7b062ae5d0b865a8ab3a2bf9d8ba9df

## Software Design Pattern
MVVM (Model-View-ViewModel)+Clean Architecture(Use Case)

MVVM (Model-View-ViewModel) is an architectural pattern used in software development to separate user interface logic from the business logic of the application. In MVVM, the Model represents the data and business logic, the View represents the user interface, and the ViewModel serves as an intermediary that connects the View and the Model, handling user interactions, data binding, and presentation logic. This separation enhances code maintainability, testability, and scalability, making it easier to manage and modify different components of the application independently. MVVM focuses on the separation of concerns within the application, Use Cases play a crucial role in defining the application's requirements and behaviors, which in turn influence the design and implementation of the ViewModel layer in MVVM


## Android jetpack components and others

    Viewbinding
    ViewModel
    Coroutines/Flow
    Retrofit
    DaggerHilt
    Navigation Architecture Components
    JUnit4(Unit Testing)
    Truth
    Github(Version Control)
    Room
    Google Services



