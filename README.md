# Second Best Story Ever – Android App

**Second Best Story Ever** is an Android application built with **Jetpack Compose** that showcases a manga titled *Reject Ranger*. The app fetches manga information, characters, pictures, and real-time user locations using **Retrofit**, **WebSockets**, and publicly available APIs. It provides an interactive UI for browsing manga content, exploring characters, viewing lore, and connecting with other users in real-time on a map.

---

## Features

### Manga & Characters
- View detailed information about the manga including **title, synopsis, background**, and **authors**.
- Browse a list or grid of characters with:
  - **Name**
  - **Role**
  - **Nicknames**
  - **Profile images**
- Search functionality for filtering characters by name.
- Detailed character view with images, Kanji names, and descriptions.

### Pictures
- View manga pictures in a **grid layout** with high-quality images fetched from the API.

### Map & Real-time Users
- Connect to a WebSocket server to share your **location** with other users.
- Track your **latitude and longitude** in real-time.
- View other connected users’ locations.

### Navigation
- Intuitive navigation with a **home screen** leading to:
  - Characters
  - Lore
  - Pictures
  - Map

---

## Screens

1. **Home Screen**
   - Entry point with buttons to navigate to all features.
   - Displays the manga poster and title.

2. **Characters Screen**
   - Grid or List layout toggle.
   - Search bar for filtering characters.
   - Click a character to view detailed information.

3. **Character Details**
   - Shows detailed character info:
     - Name (and Kanji if available)
     - Nicknames
     - Role
     - Profile image
     - Description/About

4. **Lore Screen**
   - Displays manga lore including:
     - Synopsis (expandable)
     - Background (expandable)
     - Authors
   - Manga poster image.

5. **Pictures Screen**
   - Displays manga images in a 2-column **grid**.

6. **Map Screen**
   - Connect with a username.
   - Real-time location tracking and sharing.
   - Display all connected users’ locations.

---

## Architecture & Tech Stack

- **Language & Framework**: Kotlin, Jetpack Compose  
- **Networking**: Retrofit, Gson  
- **WebSockets**: org.java_websocket for real-time updates  
- **State Management**: `mutableStateOf` and `StateFlow`  
- **Location Services**: FusedLocationProviderClient  
- **Image Loading**: Coil  

### Project Structure

```
com.example.secondbeststoryever
├── data
│   ├── dto           # API Response DTOs
│   ├── model         # Domain models
│   ├── remote        # Retrofit API, mappers, and network calls
├── ui
│   ├── screens
│   │   ├── characters # Characters list & details
│   │   ├── lore       # Manga lore
│   │   ├── map        # Map and real-time users
│   │   ├── pictures   # Manga pictures
│   │   └── Home       # Home screen
│   └── theme           # App theme, colors, dimensions
├── MainActivity.kt
```

---

## APIs Used

- **Jikan API (v4)**: For manga, characters, and pictures data  
  Base URL: `https://api.jikan.moe/v4/`  
  Endpoints:
  - `/manga/{id}/characters`
  - `/characters/{id}`
  - `/manga/{id}`
  - `/manga/{id}/pictures`

- **WebSocket Server**: `wss://ws-realtime-server.onrender.com`  
  - Handles real-time user location updates.

---

## Permissions

- **Location**: `ACCESS_FINE_LOCATION`, `ACCESS_COARSE_LOCATION`  
  Required for real-time location updates.
