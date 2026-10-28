# Bürgerportal Maiskolbistan

🇩🇪 Bürgerportal-App für die fiktive **Republik Maiskolbistan**.

- 🌐 Web-Vorlage: https://maiskolbistan-buergerportal.netlify.app
- 🏛️ Organisation: [Informationstechnik-Humboldt-Gymnasium](https://github.com/Informationstechnik-Humboldt-Gymnasium)

## App-Name

**Bürgerportal Maiskolbistan** – auf allen Plattformen einheitlich.

## Plattformen

| Ordner | Plattform | Status |
|--------|-----------|--------|
| `android/` | Android (Kotlin + WebView) | ✅ APK verfügbar |
| `ios/` | iOS (Swift + SwiftUI + WKWebView) | ✅ Xcode-Projekt verfügbar |
| `macos/` | macOS (Swift + SwiftUI + WKWebView) | ✅ Xcode-Projekt + DMG verfügbar |

## Downloads

- `android/app-debug.apk` – Debug-Build, signiert, direkt installierbar
- `macos/Buergerportal.dmg` – macOS-App als DMG (unsigniert, aber lauffähig)

## Konzept

Alle drei Apps sind **WebView-Wrapper** für das bestehende Bürgerportal. Das bedeutet:

- Keine doppelte Pflege von Inhalten
- Neue Features auf der Website sofort in den Apps verfügbar
- Login, Ausweis, Termine und Formulare funktionieren genau wie im Browser

## Dokumentation

- [KONZEPT.md](./KONZEPT.md) – Funktionsumfang, Roadmap, Design-Grundlagen
- [android/buergerportal/README.md](./android/buergerportal/README.md) – Android-Projekt
- [ios/Buergerportal/README.md](./ios/Buergerportal/README.md) – iOS-Projekt
- [macos/Buergerportal/README.md](./macos/Buergerportal/README.md) – macOS-Projekt

## Hinweis

Dies ist ein Schul-/Freizeit-Projekt für ein erfundenes Land. Alle Daten und Institutionen sind fiktiv.
