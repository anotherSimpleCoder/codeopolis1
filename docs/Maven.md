# Build-Werkzeug Apache Maven

Sie können [Apache Maven](https://maven.apache.org) auf der `Kommandozeile` verwenden, um das Softwareprojekt zu bauen.

Dieses Projekt stellt Ihnen eine vorkonfigurierte [POM-Datei](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) (`pom.xml`) zur Verfügung, so dass Sie sofort loslegen können. Bitte passen Sie diese Datei Ihren Bedürfnissen an. Insbesondere bieten sich Konfigurationen wie `<groupId>`, `<artifactId>`, `<name>`, `<description>`, `<developers>` und `<mainClass>` an.

## Ausführen von Maven

Sie können Maven entweder manuell [herunterladen](https://maven.apache.org/download.cgi) und [installieren](https://maven.apache.org/install.html) oder den [Maven Wrapper](https://maven.apache.org/wrapper/) verwenden, der Maven bei Bedarf automatisch beim ersten Aufruf herunterlädt. Dieses Projekt enthält die Skripte, um den Wrapper direkt aufzurufen.

### Maven

Wenn Sie Maven installiert haben, lautet der Befehl für den direkten Aufruf `mvn`.

Beispielhafte Ausgabe auf der Kommandozeile nach erfolgreicher manueller Installation von Maven:

```shell
❯ mvn --version
Apache Maven 3.9.4 (dfbb324ad4a7c8fb0bf182e6d91b0ae20e3d2dd9)
Maven home: /opt/homebrew/Cellar/maven/3.9.4/libexec
Java version: 20.0.2, vendor: Eclipse Adoptium, runtime: /Library/Java/JavaVirtualMachines/temurin-20.jdk/Contents/Home
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "13.6", arch: "aarch64", family: "mac"
```

### Maven Wrapper

Wenn Sie den Wrapper verwenden, lautet der Befehl zum Aufruf von Maven unter Linux und MacOS `./mvnw`. Unter Windows ist es `mvnw.cmd`.

Beispielhafte Ausgabe auf der Kommandozeile nach erfolgreichem Start des Maven Wrappers:

```shell
❯ ./mvnw --version
Apache Maven 3.9.4 (dfbb324ad4a7c8fb0bf182e6d91b0ae20e3d2dd9)
Maven home: /Users/mburger/.m2/wrapper/dists/apache-maven-3.9.4-bin/32a55694/apache-maven-3.9.4
Java version: 20.0.2, vendor: Eclipse Adoptium, runtime: /Library/Java/JavaVirtualMachines/temurin-20.jdk/Contents/Home
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "13.6", arch: "aarch64", family: "mac"
```

Unter Windows lautet dieser Befehl entsprechend `mvnw.cmd  --version`.

## Bauen der Software

Das Bauen der Software umfasst **Kompilieren**, **Ausführen**, **Testen**, **Paketieren** und **Validieren** der Software. Sie können auch eine **Webseite** für Ihr Projekt erstellen, um verschiedene Berichte im Browser anzuzeigen. Dazu gehört eine Übersicht über alle Testergebnisse und eventuelle Verstöße gegen gute Programmierpraktiken.

*Hinweis:* Im Folgenden wird der direkte Aufruf von Maven mit dem Kommando `mvn` verwendet. Wenn Sie den Wrapper verwenden, ersetzen Sie dies durch `./mvnw` oder `mvnw.cmd`. Aus `mvn compile` wird dann `./mvnw compile` oder `mvnw.cmd compile`.

### Kompilieren

Mit dem Befehl `mvn compile` können Sie den Quellcode Ihrer Software im Verzeichnis `src/main` übersetzen beziehungsweise kompilieren.

Die kompilierten Klassen (`.class`-Dateien) finden Sie anschließend im Verzeichnis `target/classes`.

*Hinweis*: Dieses Projekt ist so konfiguriert, dass der Java-Compiler bei der Übersetzung des Java-Quellcodes das statische Analysewerkzeug [Error Prone](https://errorprone.info) verwendet, das häufige Programmierfehler bereits zur Kompilierzeit erkennt. Stößt das Werkzeug auf einen solchen Fehler, wird in der Ausgabe des Compilers ein Link zu weiteren Informationen über den Fehler angezeigt. Auf der Webseite von Error Prone finden Sie eine [Übersicht über die sogenannten Bug Patterns](https://errorprone.info/bugpatterns) ("Fehler-Muster"), nach denen das Werkzeug sucht.

### Ausführen

Mit dem Befehl `mvn exec:java` können Sie Ihr Programm ausführen.

*Wichtig:* Zuvor muss der Quellcode kompiliert worden sein. Dies können Sie mit dem Befehl `mvn compile exec:java` kombinieren.

*Hinweis:* Der Name der auszuführenden Klasse ist in der `pom.xml`-Datei unter der Konfiguration `<mainClass>` hinterlegt.

### Testen

Mit dem Befehl `mvn test` können Sie Ihr Programm testen. Hierzu werden die [JUnit-Tests](https://junit.org) im Verzeichnis `src/test` kompiliert und aus dem Verzeichnis `target/test-classes` heraus ausgeführt.

### Paketieren

Mit dem Befehl `mvn package` können Sie Ihr Programm paketieren. In diesem Fall bedeutet dies die Erstellung eines [Java-Archivs](https://openbook.rheinwerk-verlag.de/javainsel/23_005.html#u23.5) (`.jar`-Datei).

Die `.jar`-Datei befindet sich anschließend im Verzeichnis `target`.

#### JAR-Archiv ausführen

Mit dem Befehl `java -jar target/java-template-0.1-SNAPSHOT.jar` können Sie Ihr Programm direkt aus dem JAR-Archiv starten.

*Hinweis:* Der Name der `.jar`-Datei leitet sich aus dem in der `pom.xml`-Datei unter der Konfiguration `<artifactId>` hinterlegten Wert ab.

### Validieren

Mit dem Befehl `mvn verify` können Sie Ihr Programm auf die Einhaltung der zuvor definierten Qualitätskriterien überprüfen. In diesem Projekt bedeutet dies die Ausführung von [Checkstyle](https://checkstyle.org), einem Werkzeug zur Überprüfung des Programmierstils von Java-Quellcode.

*Wichtig:* Checkstyle wird in diesem Fall nur ausgeführt, wenn Ihr Projekt zuvor erfolgreich kompiliert und getestet werden konnte. Schlägt dies fehl, wird der Build-Prozess abgebrochen und Checkstyle nicht ausgeführt. In diesem Fall können Sie Checkstyle direkt mit dem Befehl `mvn checkstyle:check` ausführen.

*Hinweis:* Checkstyle ist so konfiguriert, dass die [Google Java Style](https://google.github.io/styleguide/javaguide.html)-Konventionen überprüft werden.

### Webseite

Mit dem Befehl `mvn site` können Sie eine [Webseite generieren](https://maven.apache.org/guides/mini/guide-site.html), die verschiedene Berichte enthält. Dazu gehören [navigierbarer Quellcode](https://maven.apache.org/jxr/), Testergebnisse (jUnit), [Testabdeckung](https://de.wikipedia.org/wiki/Testabdeckung) ([JaCoCo](https://www.jacoco.org/jacoco/)), Checkstyle, [Codetags](https://stackoverflow.com/q/1452934/66981) und [Javadoc](https://openbook.rheinwerk-verlag.de/javainsel/23_004.html#u23.4).

Die Webseite befindet sich anschließend im Verzeichnis `target/site`. Um die Berichte dort anzusehen, öffnen Sie die Datei `target/site/index.html` in Ihrem Browser.

*Hinweis*: Eine beispielhafte Webseite finden Sie unter: <https://martinburger.github.io/sesqa-java-programming-template/>
