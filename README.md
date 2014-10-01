Fidesmo gradle plugin example
=============================

This is an example project / template for building applications for the [Fidesmo
card](http://fidesmo.com/). It's using the [Fidesmo gradle plugin](http://github.com/fidesmo/gradle-fidesmo).

Before usage
------------

- Download the JavaCard Development Kit in Version 2.2.2 from [Oracle][oracle-jc-sdk]
- Unzip the Javacard Development Kit and also the zipfiles it contains
- Set the environment variable `JC_HOME` to the folder containg the JavaCard Development Kit (usually named `java_card_kit-2_2_2`)

[oracle-jc-sdk]: http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javame-419430.html#java_card_kit-2.2.2-oth-JPR "Oracel JavCard Development Kit 2.2.2 download"

Fidesmo Service
---------------
Before you can interact with the Fidesmo servers, you need to add your appId and appKey to your
gradle.properties. If you don't have created an application yet, you can do so on the [developer
portal](https://developer.fidesmo.com/).

    echo 'fidesmoAppId: yourAppID' >> $HOME/.gradle/gradle.properties
    echo 'fidesmoAppKey: yourAppKey' >> $HOME/.gradle/gradle.properties

The fidesmo plugin installs per default to the aid a00000061700<fidesmoAppKey>0101.

Example usage
-------------

- `./gradlew build` - Build the application and create the CAP-file.
- `./gradlew installToLocalCard` - Build the application and install it on a Fidesmo card connected to a local reader.
- `./gradlew deleteFromLocalCard` - Remove application from a Fidesmo card connected to a local reader.
- `./gradlew uploadExecutableLoadFile` - Upload the application to the Fidesmo Service. You can verify that the application has been correctly uploaded using the executableLoadFiles operation of the [Fidesmo API](https://developer.fidesmo.com/api)

Running the cardlet
-------------------

The example cardlet provided in this projects accepts select and will after selection accept case 4
APDUs and echo the entire APDU (with header and data) back plus the success status word
(0x9000). As an example the following script was with [gpshell](http://sourceforge.net/projects/globalplatform/files):

    enable_trace
    mode_211
    establish_context
    card_connect
    select -AID a00000061700e26b8f120101
    send_apdu -APDU 80ca000004deadbeef00
    card_disconnect
    release_context

Running this resulted in the following trace:

    Command --> 00A404000CA00000061700E26B8F120101
    Wrapped command --> 00A404000CA00000061700E26B8F120101
    Response <-- 9000
    Command --> 80CA000004DEADBEEF00
    Wrapped command --> 80CA000004DEADBEEF00
    Response <-- 80CA000004DEADBEEF9000
    send_APDU() returns 0x80209000 (9000: Success. No error.)
