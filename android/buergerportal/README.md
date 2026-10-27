# Maiskolbistan Bürgerportal – Android

Android-App für das Bürgerportal der Republik Maiskolbistan.

## Technik

- **Sprache:** Kotlin
- **UI-Framework:** Jetpack Compose
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35

## Bauen

```bash
./gradlew assembleRelease
```

Die APK landet in `app/build/outputs/apk/release/app-release-unsigned.apk`.

## Struktur

- `app/src/main/java/.../buergerportal/` – Kotlin-Quellcode
  - `MainActivity.kt` – Navigation
  - `ui/screens/` – Login, Übersicht, Termine, Ausweis, Einstellungen
  - `ui/theme/` – Farben, Theme, Typografie
- `app/src/main/res/` – Ressourcen (Strings, Farben, Launcher-Icons)
