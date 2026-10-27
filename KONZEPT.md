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
- Benachrichtigungen / Announcements
- Einstellungen / Profil

### Langfristige Features

- QR-Code-Scanner für Behörden-Checks
- Offline-Modus für Ausweis & wichtige Dokumente
- Push-Benachrichtigungen für Termine & Wahlen
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

## 4. Roadmap

| Phase | Plattform | Ziel |
|-------|-----------|------|
| 1 | **Android** | App-Grundgerüst, Login, Dashboard, Ausweis |
| 2 | **iOS** | Parallele native iOS-App mit gleichem Feature-Set |
| 3 | **macOS** | Desktop-Variante mit angepasstem Layout |
| 4 | **Cross-Platform** | Optional gemeinsame API-Client/Design-Tokens |

---

## 5. Technische Grundlagen

- **Backend:** Supabase (Auth, Postgres, Realtime) – identisch zum Web-Portal
- **API/Auth:** Supabase Client SDKs
- **Daten:** Bürgerprofil, Termine, Anträge, Ausweisdaten, Ankündigungen
- **Sicherheit:** Keine Hardcoded Secrets; `local.properties` / Xcode Configs für Keys
- **Build-Artefakte:** APK (Android) und später .ipa / .app werden in Releases hinterlegt, nicht im Quellcode

---

## 6. Nächster Schritt

Start mit **Android** sobald Android Studio bereitsteht.
