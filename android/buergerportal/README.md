# Maiskolbistan Bürgerportal – Android

Einfacher WebView-Wrapper für das Bürgerportal der Republik Maiskolbistan.

## Technik

- **Sprache:** Kotlin
- **UI:** Android XML Layout + WebView
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 35

## Funktion

Die App lädt die Website https://maiskolbistan-buergerportal.netlify.app in einer nativen WebView.
Das Bürgerportal läuft also 1:1 in der App – inklusive Login, Ausweis, Terminen, Anträgen usw.

## Bauen

Debug-APK (signiert, installierbar):

```bash
./gradlew assembleDebug
```

Die APK landet in `app/build/outputs/apk/debug/app-debug.apk`.

## Struktur

- `app/src/main/java/.../buergerportal/MainActivity.kt` – WebView-Setup & Navigation
- `app/src/main/res/layout/activity_main.xml` – Layout mit WebView + Lade-Indikator
- `app/src/main/res/values/` – Farben, Strings, Theme
