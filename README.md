Fidesmo gradle plugin example
=============================

This is an example project / template for building applications for the [Fidesmo
card](http://fidesmo.com/). It's using the [Fidesmo gradle plugin](http://github.com/fidesmo/gradle-fidesmo).

Example Usage
-------------

- `./gradlew build` - Build the application and create the CAP-file.
- `./gradlew installToLocalCard` - Build the application and install it to a Fidesmo card connected to a local reader.
- `./gradlew deleteFromLocalCard` - Remove application from a Fidesmo card connected to a local reader.
- `./gradlew uploadExecutableLoadFile` - Upload the application to the Fidesmo Service.

Fidesmo Service
---------------
Before you can interact with the fidesmo servers, you need to add your appId and appKey to you
gradle.properties. If you don't have created an application yet, you can do so on the [developer
portal](https://developer.fidesmo.com/).

    echo 'fidesmoAppId: yourAppID' >> $HOME/.gradle/gradle.properties
    echo 'fidesmoAppKey: yourAppKey' >> $HOME/.gradle/gradle.properties
