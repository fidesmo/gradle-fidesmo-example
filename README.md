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

Instead of a card reader you can now also use your [NFC enabled Android phone](https://github.com/fidesmo/gradle-fidesmo#android-phone-as-card-reader) to communicate with the card.

Running the cardlet
-------------------
Start up the APDU console built in to the SDK using the following
command (`--no-daemon` is required for it to work properly):

    ./gradlew --no-daemon -q console

Then first select the application using the SELECT
(00A404000CA00000061700E26B8F120101) command and then send a command
with a test payload (80CA000004DEADBEEF00). Your session should look
like this:

    > send 00A404000CA00000061700E26B8F120101
    ==> ApduCommand(00A404000CA00000061700E26B8F120101)
    <== ApduResponse(48656C6C6F2046696465736D6F219000)
    > send 80ca000004deadbeef00
    ==> ApduCommand(80CA000004DEADBEEF00)
    <== ApduResponse(48656C6C6F2046696465736D6F219000)
    >
