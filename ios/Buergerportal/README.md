# Maiskolbistan Bürgerportal – iOS

iOS-App für das Bürgerportal der Republik Maiskolbistan.

## Technik

- **Sprache:** Swift
- **UI-Framework:** SwiftUI + WKWebView
- **Min SDK:** iOS 16.0
- **Target SDK:** iOS 27.0

## Funktion

Die App lädt die Website https://maiskolbistan-buergerportal.netlify.app in einer nativen WKWebView.
Das Bürgerportal läuft also 1:1 in der App – inklusive Login, Ausweis, Terminen, Anträgen usw.

## Öffnen in Xcode

```bash
open Buergerportal.xcodeproj
```

## Bauen & Signieren

Damit die App auf einem echten iPhone läuft, brauchst du ein **Apple Developer-Konto** (kostenlos reicht fürs eigene Gerät) oder ein bezahltes für Verteilung:

1. In Xcode das Projekt öffnen
2. Target „Buergerportal“ auswählen
3. Tab **Signing & Capabilities**
4. Dein **Team** auswählen
5. Bundle Identifier ggf. leicht ändern (z. B. `de.humboldtgym.maiskolbistan.buergerportal`)
6. Auf ein angeschlossenes iPhone bauen (**Cmd + R**)

Für den App Store / TestFlight benötigst du ein bezahltes Apple Developer Program.

## Struktur

- `Buergerportal/BuergerportalApp.swift` – App-Einstieg
- `Buergerportal/ContentView.swift` – WKWebView-Wrapper
- `Buergerportal/Assets.xcassets` – App-Icons
- `Buergerportal/Info.plist` – App-Metadaten
