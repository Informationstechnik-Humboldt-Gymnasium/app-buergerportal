# Maiskolbistan Bürgerportal – Android

Android-App für das Bürgerportal der Republik Maiskolbistan.

## Technik

- **Sprache:** Kotlin
- **UI-Framework:** Jetpack Compose
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35

## Bauen

Debug-APK (signiert, installierbar):

```bash
./gradlew assembleDebug
```

Die APK landet in `app/build/outputs/apk/debug/app-debug.apk`.

## Struktur

- `app/src/main/java/.../buergerportal/` – Kotlin-Quellcode
  - `MainActivity.kt` – Navigation & Auth-Status
  - `data/` – Supabase-Client, SessionManager, Repository, Datenmodelle
  - `ui/screens/` – Login, Übersicht, Termine, Ausweis, Einstellungen
  - `ui/theme/` – Farben, Theme, Typografie
  - `ui/viewmodel/` – AuthViewModel, PortalViewModel
  - `util/` – QR-Code-Generator
- `app/src/main/res/` – Ressourcen (Strings, Farben, Launcher-Icons)

## Supabase-Anbindung

Die App nutzt die öffentlichen Supabase-Credentials aus dem Website-Quelltext:

- URL und Anon-Key in `data/SupabaseClient.kt`
- Authentifizierung über Supabase Auth
- Bürger- und Termindaten werden aus den bestehenden `kv`- und `profiles`-Tabellen gelesen
