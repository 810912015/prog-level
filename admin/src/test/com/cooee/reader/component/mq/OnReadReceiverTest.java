package com.cooee.reader.component.mq;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnReadReceiverTest {

    @Test
    void save() {
        String s="0000aaabdef123cde000def123f12000def1233456def1230aa9def120038def1237";
        String r=OnReadReceiver.toHex(OnReadReceiver.fromHex(s));
        assertEquals(r,s);

    }
    @Test
    void t1(){
        String s="a0000a";
        String r=OnReadReceiver.toHex(OnReadReceiver.fromHex(s));
        assertEquals(r,s);
    }
}