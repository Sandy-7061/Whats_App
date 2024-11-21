# 📱 WhatsApp Clone  

🚀 **WhatsApp Clone** is a feature-packed chat application that replicates the functionality of WhatsApp. Built using Firebase, it provides a seamless messaging experience with **one-to-one chat**, **group chat**, **profile management**, **secure registration and login**, and much more!  

---

## ✨ Key Features  

- 💬 **One-to-One Chat**: Communicate instantly with friends and family in private conversations.  
- 👥 **Group Chat**: Create or join groups to chat with multiple people simultaneously.  
- 🖼️ **Profile Management**: Customize your profile with a name, photo, and status.  
- 🔐 **Registration & Login**: Secure user authentication using Firebase Authentication.  
- 🕒 **Real-Time Messaging**: Messages update in real-time with Firebase Realtime Database.  
- 🟢 **User Presence**: Show online/offline status to stay updated with your contacts’ activity.  
- 📷 **Media Sharing** (optional): Share images, videos, or files within chats.  
- 🔄 **Data Synchronization**: All messages, media, and profiles sync across devices in real-time.  

---

## 📂 Project Structure  

Here's a detailed breakdown of the app's structure:  

### 1️⃣ **Frontend (Android)**  
- **Language**: Java/Kotlin  
- **UI Components**:  
  - Login and Registration screens  
  - Chat screens for one-to-one and group chats  
  - Profile screen with editable details  
  - Group creation and management screen
  - Setting screen  

### 2️⃣ **Backend (Firebase)**  
- **Firebase Authentication**: Handles user registration, login, and secure sessions.  
- **Firebase Realtime Database**: Stores messages, user details, and group chat data.  
- **Firebase Storage**: Manages user profile pictures and shared media.  

---

## 🛠️ Setup Instructions  

Follow these steps to set up the project on your local machine:  

### Step 1: Clone the Repository  
```bash  
git clone https://github.com/Sandy-7061/Whats_App.git
cd Whats_App  
```  

### Step 2: Open the Project in Android Studio  
1. Open Android Studio.  
2. Click on `File > Open` and select the project folder.  

### Step 3: Configure Firebase  
1. Go to the [Firebase Console](https://console.firebase.google.com/).  
2. Create a new project.  
3. Add an Android app and download the `google-services.json` file.  
4. Place the `google-services.json` file in the `app/` directory.  

### Step 4: Add Dependencies  
Ensure the following dependencies are included in your `build.gradle` file:  
```gradle  
implementation 'com.google.firebase:firebase-auth:xx.x.x'  
implementation 'com.google.firebase:firebase-database:xx.x.x'  
implementation 'com.google.firebase:firebase-storage:xx.x.x'  
implementation 'com.github.bumptech.glide:glide:4.12.0'  
```  

### Step 5: Run the Application  
- Sync Gradle files.  
- Build and run the app on an emulator or a physical device.  

---


## 📂 File Structure  

```
📁 whatsapp-clone  
├── 📁 app  
│   ├── 📁 src  
│   │   ├── 📁 main  
│   │   │   ├── 📁 java  
│   │   │   │   ├── 📁 com.example.whatsappclone  
│   │   │   │   │   ├── MainActivity.java    # Entry point of the app  
│   │   │   │   │   ├── ChatActivity.java    # Handles chat screen functionality  
│   │   │   │   │   ├── GroupActivity.java   # Manages group chats  
│   │   │   │   │   ├── ProfileActivity.java # Handles profile updates  
│   │   │   │   │   ├── AuthActivity.java    # Manages login and registration  
│   │   │   │   │   └── ...                  # Other files  
│   │   │   ├── 📁 res  
│   │   │   │   ├── 📁 layout                # XML layout files for UI screens  
│   │   │   │   ├── 📁 drawable              # App icons and images  
│   │   │   │   ├── 📁 values                # Strings, colors, and styles  
│   │   │   │   └── ...  
├── 📁 build.gradle                          # Gradle configuration  
├── 📄 google-services.json                  # Firebase configuration file  
└── ...  
```

---

## 👤 Owner Information  

**Sandeep Kushwaha**  
📱 [7024520740](tel:7024520740)  
📧 [sandeepkush880@gmail.com](mailto:sandeepkush880@gmail.com)  

---

## 🚀 Future Enhancements  

- 🌟 Add voice and video calling features.  
- 🎨 Introduce themes for a personalized experience.  
- 🔔 Push notifications for message alerts.  
- 🔒 End-to-end encryption for enhanced privacy.  

---

Feel free to contribute or reach out for any queries! 😊
