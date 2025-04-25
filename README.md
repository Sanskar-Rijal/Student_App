# Student App

The **Student App** is an Android application developed to provide students of IOE Purwanchal Campus with a seamless platform to view academic information such as attendance, internal marks, study materials, announcements, and teacher details. It is designed to improve communication and transparency between students and faculty.

---

## Features

### 1. View Attendance
- Allows students to track their attendance records for each subject.
- Data is structured by semester and subject for clarity and convenience.

### 2. Internal Marks
- Students can view the internal assessment marks assigned by their teachers.
- Promotes academic awareness and self-monitoring.

### 3. Notices and Announcements
- Displays important notices and announcements from the college and respective faculties.
- Ensures students remain up-to-date with academic and administrative updates.

### 4. Study Materials (Notes)
- Enables students to access and download notes uploaded by teachers.
- Notes are provided in PDF format for offline viewing.

### 5. Teacher Information
- Shows a list of teachers with their respective details.
- Helps students easily identify and connect with their instructors.

---

## Tech Stack & Architecture

- **Frontend:** Kotlin with Jetpack Compose
- **Architecture Pattern:** MVVM (Model-View-ViewModel)
- **State Management:** LiveData and StateFlow
- **Data Storage:**
  - Cache memory for storing the username
  - CookieJar for handling backend session cookies

### Backend
- **Language & Framework:** Node.js with Express.js
- **Database:** PostgreSQL
- **Architecture:** Monolithic  
- Backend Repository: [Campus Connect – Backend](https://github.com/Sangyog10/Campus-connect)

---

## Installation & Setup

### Prerequisites
- Android Studio installed
- Android device or emulator running Android 7.0 or higher

### Steps to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/Sanskar-Rijal/Student_App.git
   cd Student_App
   ```

2. Open the project in Android Studio:
   - Select **File > Open** and navigate to the cloned project directory.

3. Build and Run:
   - Connect your device or start an emulator.
   - Click the **Run** button to build and launch the application.

---

## License

This project is intended for educational purposes and is open to further contributions and improvements.

---

## Contribution

Contributions are welcome. You can fork the repository, create a feature branch, and submit a pull request. Suggestions and issues can also be raised via GitHub.

---

## About the Project

This application is part of the **Comprehensive Application for the Institute of Engineering, Purwanchal Campus (IOEPC)**. It was developed as a **Minor Project** to enhance academic interaction and digital accessibility for students.

---
