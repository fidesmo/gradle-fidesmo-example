package com.fidesmo.javacard.example;

import javacard.framework.*;

public class ExampleCardlet extends Applet
{
    private byte[] received;
    private static final short MAX_LENGTH = 256;
    private static final byte[] helloFidesmo = {(byte)'H',(byte)'e',(byte)'l',(byte)'l',(byte)'o',(byte)' ',(byte)'F',(byte)'i',(byte)'d',(byte)'e',(byte)'s',(byte)'m',(byte)'o',(byte)'!'};
 

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
     * Processes an incoming APDU. Will always respond with the helloFidesmo string,
     * regardless of what is received.
     * @see APDU
     * @param apdu the incoming APDU
     * @exception ISOException with the response bytes per ISO 7816-4
     */
    public void process(APDU apdu){
        byte buffer[] = apdu.getBuffer();        
        short length = (short) helloFidesmo.length;
                
        Util.arrayCopyNonAtomic(helloFidesmo, (short)0, buffer, (short)0, (short)length);
        apdu.setOutgoingAndSend((short)0, length);               
    }
}
