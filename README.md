# Java Programming Template { SESQA }

Projektvorlage zur Programmierung in Java von [Professor Martin Burger](https://sesqa.martin-burger.net).

## Benötigte Software

Sie benötigen **Java**, um die Software zu übersetzen (kompilieren) und auszuführen. Als integrierte Entwicklungsumgebung (IDE) empfehlen wir **IntelliJ IDEA**. Wenn Sie auch auf der [`Kommandozeile`](https://de.wikipedia.org/wiki/Kommandozeile) arbeiten möchten, empfehlen wir **Apache Maven**.

### Programmiersprache Java

Zur Entwicklung von Java-Programmen benötigen Sie ein **Java Development Kit** (JDK). Das JDK ist ein Softwarepaket, das Bibliotheken und Werkzeuge für die Entwicklung und den Test von Java-Anwendungen sowie für die Ausführung von Anwendungen auf der Java-Plattform enthält. Eine freie Version des offiziellen JDK der Firma Oracle ist unter dem Namen [OpenJDK](https://de.wikipedia.org/wiki/OpenJDK) verfügbar.

Wir empfehlen und unterstützen **Eclipse Temurin**, die OpenJDK-Distribution der Community-Organisation Adoptium: <https://adoptium.net/de/>

*Tipp:* Informationen zu den Vor- und Nachteilen verschiedener JDKs finden Sie auf der Website [Which Version of JDK Should I Use?](https://whichjdk.com)

Beispielhafte Ausgabe auf der Kommandozeile nach erfolgreicher Installation von Temurin:

```shell
❯ java -version
openjdk version "20.0.2" 2023-07-18
OpenJDK Runtime Environment Temurin-20.0.2+9 (build 20.0.2+9)
OpenJDK 64-Bit Server VM Temurin-20.0.2+9 (build 20.0.2+9, mixed mode)
```

*Wichtig:* Stellen Sie sicher, dass Sie mindestens LTS (Long-Term Support) Version 17 installiert haben. Im obigen Beispiel ist die neuere Version 20 installiert, die wir empfehlen.

### Integrierte Entwicklungsumgebung

Obwohl Sie den Quellcode Ihres Java-Programms mit jedem beliebigen Texteditor bearbeiten können, empfehlen wir Ihnen, eine **integrierte Entwicklungsumgebung** (Integrated Development Environment, IDE) zu verwenden. Dabei handelt es sich um eine Softwareanwendung, die umfassende, auf die Softwareentwicklung spezialisierte Funktionalitäten bietet. Eine IDE besteht in der Regel mindestens aus einem Quellcode-Editor, Werkzeugen zur Build-Automatisierung und einem Debugger.

Wir empfehlen und unterstützen **IntelliJ IDEA**, eine auf Java spezialisierte IDE der Firma JetBrains: <https://www.jetbrains.com/idea/>

*Wichtig:* Stellen Sie sicher, dass Sie ein JDK installiert haben, bevor Sie IntelliJ IDEA installieren. Während Sie Java nicht installieren müssen, um IntelliJ IDEA auszuführen, benötigen Sie ein eigenständiges JDK, um Java-Anwendungen in der IDE zu entwickeln.

JetBrain bietet eine [kostenlose Lizenz für Bildungszwecke](https://www.jetbrains.com/community/education/) an. Diese berechtigt zur Nutzung für nicht-kommerzielle Bildungszwecke und kann kostenlos verlängert werden, solange Sie Student:in oder Dozent:in sind.

*Tipp:* Zu IntelliJ IDEA gibt es eine umfangreiche [Dokumentation](https://www.jetbrains.com/help/idea/getting-started.html) sowie zahlreiche [Tipps](https://www.jetbrains.com/idea/guide/tips/) und [Tutorials](https://www.jetbrains.com/idea/guide/tutorials/). Für Einsteiger:innen bieten sich zum Beispiel die Tutorials aus dem Themenbereich [Getting started with IntelliJ IDEA](https://www.jetbrains.com/idea/guide/tutorials/getting-started-intellij-idea/) an.

### Build-Werkzeug auf der Kommandozeile

Mit einer IDE wie IntelliJ IDEA können Sie Ihre Software über eine grafische Benutzeroberfläche kompilieren, ausführen und testen. Auf der Kommandozeile können Sie diese Arbeitsschritte mit geeigneten Werkzeugen weiter vereinfachen und automatisieren. Mit einem solchen dedizierten **Build-Werkzeug** können Sie Ihre Arbeit also wesentlich schneller und damit effizienter erledigen.

Wir empfehlen ein Build-Werkzeug als Ergänzung zu einer IDE und unterstützen daher **Apache Maven**, ein entsprechendes Kommandozeilen-Werkzeug der Apache Software Foundation: <https://maven.apache.org>

*Tipp:* Mit IntelliJ IDEA können Sie auch [Maven-Projekte verwalten und ausführen](https://www.jetbrains.com/help/idea/maven-support.html). IntelliJ bietet ein [Tutorial zur Verwendung von Maven in der IDE](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/) an.

**Die Installation von Maven ist optional.** Dieses Projekt verwendet den [Apache Maven Wrapper](https://maven.apache.org/wrapper/), der Maven bei Bedarf automatisch beim ersten Aufruf herunterlädt. Es ist daher nicht notwendig, Maven manuell zu installieren.

*Hinweis*: Details zu Maven und dessen Nutzung finden Sie unter [`docs/Maven.md`](docs/Maven.md).

## Projekt- und Verzeichnisstruktur

Dieses Projekt hat folgende Verzeichnisstruktur:

```text
sesqa-java-programming-template
├── .git
│   └── ...
├── .github
│   └── ...
├── .gitignore
├── .idea
│   └── ...
├── .mvn
│   └── ...
├── LICENSE
├── README.md
├── docs
│   └── ...
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── net
    │           └── martinburger
    │               └── sesqa
    │                   └── programming
    │                       └── App.java
    ├── site
    │   └── site.xml
    └── test
        └── java
            └── net
                └── martinburger
                    └── sesqa
                        └── programming
                            └── AppTest.java
```

Die Verzeichnisse `.github`, `.idea` und `.mvn` enthalten eigene `README.md` Dateien und werden im Folgenden nicht beschrieben.

### Verzeichnis `.git`

Das Verzeichnis **`.git`** enthält fast alles, was das [Versionsverwaltungswerkzeug Git](https://git-scm.com/book/de/v2/Erste-Schritte-Was-ist-Versionsverwaltung%3F) für die lokale Repository-Verwaltung speichert.

### Datei `.gitignore`

Die Datei **[`.gitignore`](https://git-scm.com/docs/gitignore)** legt fest, welche Dateien und Verzeichnisse Git [ignorieren soll](https://docs.github.com/de/get-started/getting-started-with-git/ignoring-files). Diese werden von Git nicht automatisch hinzugefügt und auch nicht als "unversioniert" (engl. untracked) angezeigt. In der Regel handelt es sich dabei um automatisch generierte Dateien wie Logdateien oder Dateien, die von einem Build-Werkzeug wie Maven erzeugt werden.

### Datei `LICENSE`

Die Datei **`LICENSE`** enthält die Lizenz, unter der dieses Projekt veröffentlicht wird.

### Datei `README.md`

Die Datei **[`README.md`](https://docs.github.com/de/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes)** ist die Datei, die Sie gerade lesen.

### Verzeichnis `docs`

Im Verzeichnis **`docs`** finden Sie weitere Dokumentation zur Benutzung dieses Projektes.

### Dateien `mvnw` und `mvnw.cmd`

Die Dateien **`mvnw`** und **`mvnw.cmd`** sind die Skripte, die der [Maven Wrapper](https://maven.apache.org/wrapper/) verwendet, um Maven bei Bedarf automatisch herunterzuladen. Maven selbst wird dann von diesen Skripten ausgeführt.

### Datei `pom.xml`

Die **[`pom.xml`](https://maven.apache.org/pom.html)** Datei ist eine XML Datei, die Informationen über das Projekt und Konfigurationsdetails enthält, die von Maven verwendet werden, um das Projekt zu bauen. Diese Informationen werden im sogenannten [Project Object Model](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) (POM) dargestellt. Beim Ausführen eines Tasks oder Goals (zum Beispiel mit dem Befehl `mvn compile`) sucht Maven nach dieser Datei im aktuellen Verzeichnis. Maven liest daraus das POM, holt sich die benötigten Konfigurationsinformationen und führt dann den Befehl aus.

### Verzeichnis `src`

Das Verzeichnis **[`src`](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)** enthält den gesamten Quellcode und die zugehörigen Ressourcen für die Erstellung des Projekts, der Webseite und dergleichen. Es enthält ein Unterverzeichnis für jedes dieser Artefakte: `main` für das Haupt-Build-Artefakt (in diesem Fall ist dies Ihr Java-Programm), `test` für den Unit-Test-Code, `site` für die Webseite und so weiter.

Der Java-Quellcode befindet sich in den beiden Verzeichnissen `src/main/java` und `src/test/java`. Die beiden darin enthaltenen Verzeichnisse `net/martinburger/sesqa/programming` definieren das [Java-Paket](https://dh-cologne.github.io/java-wegweiser/articles/Packages-package-und-import.html) `net.martinburger.sesqa.programming`.

*Hinweis:* Maven erstellt beim Bauen ein Verzeichnis namens `target`. Dieses enthält alle Daten, die beim Bauen des Projekts automatisch erzeugt werden. Dazu gehören unter anderem die kompilierten Java-Klassen, das JAR-Archiv und die Webseite. Dieses Verzeichnis wird durch die Datei `.gitignore` von der Versionskontrolle ausgeschlossen.
