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

> **Wichtige Einschränkung:** Die Website und das Supabase-Backend werden vom Mitstreiter betrieben. Wir können dort **keine Änderungen** vornehmen (keine neuen Tabellen, keine Edge Functions, keine Trigger). Die Apps müssen also mit den bestehenden Datenstrukturen arbeiten.

Die Apps sollen trotzdem **nicht neu gebaut** werden müssen, wenn sich Inhalte auf der Website ändern. Dafür nutzen wir das **bestehende Supabase-Projekt als Quelle** und eingebettete Web-Ansichten für stark formatierte Inhalte.

| Ebene | Lösung |
|-------|--------|
| **Navigation** | Feste Hauptpunkte im Code; Inhalte dahinter kommen aus Supabase |
| **Dashboard-Kacheln** | Native Kacheln, die existierende Daten anzeigen (Termine, Kontostand, neue Ankündigungen) |
| **Ankündigungen / Regierung / Ministerien** | Native Liste + WebView-Detailansicht der Website, falls das Layout komplex ist |
| **Formulare (Anträge, Terminbuchung)** | Bestehende Web-Formulare in kontrollierter WebView – so spiegelt die App sofort jede Website-Änderung |
| **Farben & Branding** | Feste Maiskolbistan-Palette im Code, angepasst an System-Design (Material You / iOS) |

### Umsetzung

- Apps lesen nur aus den **vorhandenen Supabase-Tabellen** (z. B. Termine, Anträge, Bürgerprofil)
- Für Bereiche, die sich häufig ändern oder viel HTML benötigen, verwenden wir eine **eingebettete WebView** mit der Website-URL
- App cached gelesene Supabase-Daten lokal, um Offline-Anzeige zu ermöglichen
- Ein App-Update ist nur nötig bei neuen **nativen Features** oder Änderungen an der Authentifizierung

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

Da wir Supabase **nicht verändern** dürfen, nutzen wir zwei Arten von Mitteilungen:

#### 1. Lokale Mitteilungen (kein Server nötig)
- Terminerinnerungen basierend auf den in der App gespeicherten Terminen
- Wahl-/Umfrage-Erinnerungen, wenn das Datum bekannt ist
- Werden direkt vom Betriebssystem verwaltet

#### 2. Push-Mitteilungen (später, optional)
- Benötigen einen eigenen, kleinen Server/Dienst, den wir betreiben
- Dieser Dienst **pollt** in regelmäßigen Abständen die öffentlichen/freigegebenen Supabase-Daten (z. B. `announcements`)
- Bei neuen Einträgen sendet er über FCM (Android) bzw. APNs (iOS/macOS)
- Geräte-Token werden in unserem eigenen Dienst gespeichert, **nicht** in Supabase

> Im MVP konzentrieren wir uns auf **lokale Mitteilungen**. Push über einen eigenen Server kommt erst, wenn Android & iOS stabil laufen.

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
