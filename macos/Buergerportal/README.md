# Maiskolbistan Bürgerportal – macOS

macOS-App für das Bürgerportal der Republik Maiskolbistan.

## Technik

- **Sprache:** Swift
- **UI-Framework:** SwiftUI + WKWebView
- **Min SDK:** macOS 14.0
- **Target SDK:** macOS 27.0

## Funktion

Die App lädt die Website https://maiskolbistan-buergerportal.netlify.app in einer nativen WKWebView.
Das Bürgerportal läuft also 1:1 in der App – inklusive Login, Ausweis, Terminen, Anträgen usw.

## Öffnen in Xcode

```bash
open Buergerportal.xcodeproj
```

## Bauen

Release-Build über die Kommandozeile (unsigniert, lauffähig auf dem eigenen Mac):

```bash
xcodebuild -project Buergerportal.xcodeproj -scheme Buergerportal -configuration Release -derivedDataPath build/DerivedData CODE_SIGN_IDENTITY="" CODE_SIGNING_REQUIRED=NO
```

Die `.app` liegt dann unter:

```
build/DerivedData/Build/Products/Release/Buergerportal.app
```

## DMG erstellen

Ein fertiges DMG liegt im übergeordneten `macos/`-Ordner:

```
macos/Buergerportal.dmg
```

Manuell neu erstellen:

```bash
mkdir -p build-dmg
cp -R build/DerivedData/Build/Products/Release/Buergerportal.app build-dmg/
ln -s /Applications build-dmg/Applications
hdiutil create -volname "Bürgerportal Maiskolbistan" -srcfolder build-dmg -ov -format UDZO Buergerportal.dmg
```

## Hinweis zur Signierung

Die mitgelieferte `.app` und `.dmg` sind **nicht signiert**. Beim ersten Start erscheint daher eine Sicherheitswarnung. Mit **Rechtsklick → Öffnen** oder in **Systemeinstellungen → Datenschutz & Sicherheit** lässt sich die App trotzdem starten.

Für eine vollständige Signierung brauchst du ein **Apple Developer-Konto** und ein entsprechendes **Developer ID-Zertifikat**.

## Struktur

- `Buergerportal/BuergerportalApp.swift` – App-Einstieg
- `Buergerportal/ContentView.swift` – WKWebView-Wrapper
- `Buergerportal/Assets.xcassets` – App-Icons
- `Buergerportal/Info.plist` – App-Metadaten
