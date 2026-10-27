# Maiskolbistan Bürgerportal App

🇩🇪 Bürgerportal-App für die fiktive **Republik Maiskolbistan**.

- 🌐 Web-Vorlage: https://maiskolbistan-buergerportal.netlify.app
- 🏛️ Organisation: [Informationstechnik-Humboldt-Gymnasium](https://github.com/Informationstechnik-Humboldt-Gymnasium)

## Plattformen

| Ordner | Plattform | Status |
|--------|-----------|--------|
| `android/` | Android (Kotlin + Jetpack Compose) | ✅ App gebaut, APK vorhanden |
| `ios/` | iOS (Swift + SwiftUI) | ⏳ Struktur angelegt |
| `macos/` | macOS (Swift + SwiftUI) | ⏳ Struktur angelegt |

## APK

- `android/app-debug.apk` – Debug-Build, signiert, direkt installierbar

## Android-Features

- ✅ Echte Anmeldung gegen Supabase Auth
- ✅ Profil aus `profiles`-Tabelle
- ✅ Bürgerdaten aus `kv` (`mks:registry:v4`)
- ✅ Termine aus `kv` (`mks:appointments:v1`)
- ✅ Ankündigungen aus `kv` (`mks:announcements:v1`)
- ✅ Echter QR-Code aus der Bürger-ID
- ✅ Abmelden

## Dokumentation

- [KONZEPT.md](./KONZEPT.md) – Funktionsumfang, Roadmap, Design-Grundlagen
- [android/buergerportal/README.md](./android/buergerportal/README.md) – Android-Projekt-Übersicht

## Hinweis

Dies ist ein Schul-/Freizeit-Projekt für ein erfundenes Land. Alle Daten und Institutionen sind fiktiv.
