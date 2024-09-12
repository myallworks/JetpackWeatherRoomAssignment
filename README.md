# 📱 Jetpack Compose MVVM Room Retrofit Glide DI Project

This project is built entirely with **Jetpack Compose** and **Kotlin**, leveraging modern Android development techniques like **Room**, **MVVM**, **Retrofit**, and **Dependency Injection (DI)**. The app flows seamlessly from login to a dynamic user list, with data persistence and API integration.

## ✨ Features

- **🛠 Built with Jetpack Compose**: Fully declarative UI using Jetpack Compose.
- **🏛️ MVVM Architecture**: Clean separation of concerns with ViewModel, Repository, and LiveData.
- **🗄 Room Database**: Stores user data locally with smooth UI updates.
- **🌐 Retrofit Integration**: Fetches weather details from an external API.
- **🔐 Login with SharedPreferences**: Stores login state securely.

## 📖 Project Flow

1. **🔐 Login Screen**:  
   - Username: `testapp@google.com`
   - Password: `Test@123456`
   - Stores the login state using **SharedPreferences**.

2. **🏠 Home Page**:
   - **👤 User List**: Displays a list of users fetched from the **Room database**.
   - **➕ Add User**: Opens a form to input first name, last name, and email.
   - **📥 Save User**: The entered details are stored with an auto-generated primary key in **Room**, and the list refreshes automatically.

3. **☁️ Weather Details**:
   - Click on any user in the list to view their weather details fetched from an external web API using **Retrofit**.

## 🧑‍💻 Tech Stack

- **🧩 Jetpack Compose**: Declarative UI framework.
- **🏛 MVVM**: Separation of concerns between UI and data handling.
- **🗄 Room**: Local database management.
- **🌐 Retrofit**: HTTP client for web API interaction.
- **🔌 Dependency Injection (DI)**: For better code management and testability.
- **🔑 SharedPreferences**: Persisting login state.
- **✨ Glide**: For displaying image from url

## 🚀 How to Run

1. Clone the repository.
2. Open in Android Studio.
3. Run the project on an emulator or physical device.

---

Feel free to adjust it as needed for your project!
