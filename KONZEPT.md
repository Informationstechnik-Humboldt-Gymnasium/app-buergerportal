# Maiskolbistan Bürgerportal – App-Konzept

> Projekt: **app-bürgerportal**  
> Organisation: Informationstechnik-Humboldt-Gymnasium  
> Land: Republik Maiskolbistan (erfunden, gegr. 2022)  
> Web-Vorlage: https://maiskolbistan-buergerportal.netlify.app

---

## 1. Ausgangslage

Das Web-Bürgerportal ist eine React-Single-Page-App mit offiziellem, staatlichem Look (Gold/Bordeaux/Pergament). Es wird gegen eine Supabase-Backend betrieben und bietet Bürgerinnen und Bürgern einen zentralen Zugang zu staatlichen Diensten.

### Kern-Features des Web-Portals (aus Quellcode abgelesen)

| Bereich | Beschreibung |
|---------|--------------|
| **Anmeldung** | E-Mail + Passwort, Passwort-Reset, Master-Account |
| **Übersicht** | Dashboard mit Status, Terminen, Benachrichtigungen |
| **Bank/Konto** | Bürgerkonto, Transaktionen, Bankverwaltung |
| **Ausweis** | Digitaler Personalausweis mit QR-Code |
| **Reisepass** | Digitaler Reisepass |
| **Termine** | Behörden-Terminbuchung mit Kalender |
| **Anträge** | Antragsarten, Bewerbungen, Statusverfolgung |
| **Wahlen & Umfragen** | Abstimmungen, Wahlen, Audit |
| **Regierung** | Ministerien, Organe, Einheiten, Befugnisse |
| **Akten & Haftbefehle** | Behördeninterne/gerichtliche Register |
| **Chat & Support** | Bürgerservice, Feature-Requests, Patchnotes |
| **Einstellungen** | Profil, Berechtigungen, Design |

### Design-Sprache

- **Farben:** Gold (`#DAA520`), Bordeaux/Maroon (`#8B2A2C`), Pergament (`#FBF6EA`), Tinte (`#2A2116`)
- **Typografie:** Zilla Slab (Display), Bitter (Body), Hanken Grotesk (UI), JetBrains Mono (Codes)
- **Stil:** Offiziell, würdevoll, leicht vintage/staatlich, nicht zu dunkel

---

## 2. Ziel der Apps

Drei native Apps, die das Web-Portal ergänzen und für mobile Endgeräte optimieren:

1. **Android** – primäre Zielplattform, wird zuerst entwickelt
2. **iOS** – anschließend für iPhone/iPad
3. **macOS** – Desktop-Ergänzung mit SwiftUI/AppKit

### Minimal-Viable-Product (MVP) je Plattform

- Login mit E-Mail/Passwort gegen Supabase
- Übersichts-Dashboard
- Digitaler Ausweis mit QR-Code-Anzeige
- Terminübersicht
- **Push-Mitteilungen** für Termine, Ankündigungen, Wahlen (FCM / APNs)
- Einstellungen / Profil

### Langfristige Features

- QR-Code-Scanner für Behörden-Checks
- Offline-Modus für Ausweis & wichtige Dokumente
- Biometrische Anmeldung (Face ID / Touch ID / Fingerprint)
- Deep-Linking zu Anträgen & Terminen

---

## 3. Repository-Struktur

```
app-buergerportal/
├── android/          # Android-Projekt (Kotlin + Jetpack Compose)
├── ios/              # iOS-Projekt (Swift + SwiftUI)
├── macos/            # macOS-Projekt (Swift + SwiftUI/AppKit)
├── KONZEPT.md        # Dieses Dokument
└── README.md         # Projekt-Übersicht
```

> In diesem ersten Schritt werden **nur die Ordner und Dokumentation** angelegt. Code folgt plattformweise.

---

## 4. Architektur: Möglichst wenig Updates

Die Apps sollen **nicht neu gebaut** werden müssen, wenn sich kleine Inhalte ändern (Texte, Farben, Reihenfolge von Kacheln, neue Ministerien, neue Antragsarten). Dafür nutzen wir ein **servergesteuertes UI**:

| Ebene | Was im App-Code bleibt | Was vom Backend kommt |
|-------|------------------------|----------------------|
| **Navigation** | Feste Hauptpunkte (Übersicht, Ausweis, Termine, Einstellungen) | Reihenfolge, Sichtbarkeit, Badges |
| **Dashboard-Kacheln** | Typen und Layout-Regeln | Inhalt, Icons, Links, Sichtbarkeit |
| **Texte & Labels** | Fallback-Werte | Überschreibungen aus `app_config` |
| **Farben** | Basis-Palette + System-Integration | Aktive Akzentfarbe, saisonale Anpassungen |
| **Ankündigungen** | Darstellungskomponente | Titel, Body, Aktion, Zielgruppe |
| **Formulare** | Validierungsregeln, Feld-Typen | Felder, Reihenfolge, Pflichtangaben |

### Umsetzung

- **Remote-Config-Tabelle** in Supabase (`app_config`, `app_tiles`, `app_announcements`)
- App lädt Konfiguration beim Start und cached sie lokal
- Bei Änderungen reicht ein Backend-Update – keine Store-App-Updates nötig
- Nur bei neuen **nativen Features** (z. B. QR-Scanner, neue Berechtigungen) wird ein App-Update fällig

---

## 5. Plattformspezifisches Design

Jede App soll sich auf ihrer Plattform **nativ anfühlen**, aber das Maiskolbistan-Branding (Gold/Bordeaux/Pergament) behalten.

### Android

- **Material You / Dynamic Color:** Akzentfarben können vom Hintergrundbild des Geräts abgeleitet werden, wobei Gold/Bordeaux als feste Primär-/Sekundärfarben bleiben
- **Adaptive Icons:** Launcher-Icon mit Vorder- und Hintergrundebene
- **Edge-to-Edge:** Inhalte bis in die System-Gesten- und Status-Bar
- **Predictive Back Gesture** ab Android 14
- **Widgets (später):** Ausweis-Shortcut, nächster Termin

### iOS

- **SwiftUI mit nativen Komponenten**
- **Liquid Glass / visuelle Updates:** Sobald Apple neue System-Styles einführt, werden systemkonforme Glass-/Blur-Effekte verwendet, ohne das Branding zu verlassen
- **Lock Screen Widgets & Live Activities** für anstehende Termine
- **Dynamic Island** (optional) für laufende Behörden-Aktionen

### macOS

- **SwiftUI mit AppKit-Elementen** wo nötig
- **Sidebar-Navigation** wie bei Systemeinstellungen
- **Menübar-Extra** für schnellen Ausweis-Zugriff
- **Native Tastaturkürzel** und Touch-Bar-/Menu-bar-Integration

---

## 6. Mitteilungen (Push)

Push-Mitteilungen sind Teil des MVP.

| Trigger | Kanal | Beispiel |
|---------|-------|----------|
| Terminerinnerung | FCM / APNs | „Morgen 10:00 Uhr: Termin Bürgeramt“ |
| Neue Ankündigung | FCM / APNs | Wichtige Regierungsmeldung |
| Wahl-/Umfrage-Start | FCM / APNs | „Neue Abstimmung verfügbar“ |
| Antrags-Status | FCM / APNs | „Ihr Antrag wurde genehmigt“ |
| Chat/Support | FCM / APNs | Neue Antwort im Bürgerservice |

### Architektur

- Supabase-Datenbank-Trigger / Edge Functions erzeugen Mitteilungen
- **Android:** Firebase Cloud Messaging (FCM)
- **iOS / macOS:** Apple Push Notification service (APNs)
- Token werden in der `user_profiles`-Tabelle gespeichert
- Benutzer kann Mitteilungen pro Kategorie in den Einstellungen deaktivieren

---

## 7. Roadmap

| Phase | Plattform | Ziel |
|-------|-----------|------|
| 1 | **Android** | App-Grundgerüst, Login, Dashboard, Ausweis |
| 2 | **iOS** | Parallele native iOS-App mit gleichem Feature-Set |
| 3 | **macOS** | Desktop-Variante mit angepasstem Layout |
| 4 | **Cross-Platform** | Optional gemeinsame API-Client/Design-Tokens |

---

## 8. Technische Grundlagen

- **Backend:** Supabase (Auth, Postgres, Realtime) – identisch zum Web-Portal
- **API/Auth:** Supabase Client SDKs
- **Daten:** Bürgerprofil, Termine, Anträge, Ausweisdaten, Ankündigungen
- **Sicherheit:** Keine Hardcoded Secrets; `local.properties` / Xcode Configs für Keys
- **Build-Artefakte:** APK (Android) und später .ipa / .app werden in Releases hinterlegt, nicht im Quellcode

---

## 9. Nächster Schritt

Start mit **Android** sobald Android Studio bereitsteht.
