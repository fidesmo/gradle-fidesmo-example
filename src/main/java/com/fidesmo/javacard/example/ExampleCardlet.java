package com.fidesmo.javacard.example;

import javacard.framework.*;

public class ExampleCardlet extends Applet
{
    private byte[] received;
    private static final short MAX_LENGTH = 256;

    protected ExampleCardlet(){
        received = new byte[MAX_LENGTH];
        register();
    }

    /**
     * Installs this applet.
     * @param bArray the array containing installation parameters
     * @param bOffset the starting offset in bArray
     * @param bLength the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength){
        new ExampleCardlet();
    }

    /**
     * Processes an incoming APDU.
     * @see APDU
     * @param apdu the incoming APDU
     * @exception ISOException with the response bytes per ISO 7816-4
     */
    public void process(APDU apdu){
        byte buffer[] = apdu.getBuffer();
        short bytesRead = apdu.setIncomingAndReceive();

        if (buffer[ISO7816.OFFSET_CLA] == ISO7816.CLA_ISO7816
            && buffer[ISO7816.OFFSET_INS] == ISO7816.INS_SELECT) {

            apdu.setOutgoing();
            apdu.setOutgoingLength( (short) 0);

        } else {
            short echoOffset = (short)0;

            while ( bytesRead > 0 ) {
                Util.arrayCopyNonAtomic(buffer, ISO7816.OFFSET_CDATA, received, echoOffset, bytesRead);
                echoOffset += bytesRead;
                bytesRead = apdu.receiveBytes(ISO7816.OFFSET_CDATA);
            }

            apdu.setOutgoing();
            apdu.setOutgoingLength( (short) (echoOffset + 5) );

            apdu.sendBytes( (short)0, (short) 5);
            apdu.sendBytesLong( received, (short) 0, echoOffset );
        }
    }
}
