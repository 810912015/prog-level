package com.pl.admin.p3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransApiTest {
    private static final String APP_ID = "20200422000426456";
    private static final String SECURITY_KEY = "IETaofPI4iM4rJzzKOxE";
    @Test
    void getTransResult() {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Beam Balance is a device for measuring unknown weights by balancing them with a set of known masses. As you may see from the picture above, they were known even in Ancient Egypt more than 3000 years ago.\n" +
                "\n" +
                "The device consists of the horizontal beam holding two pans. The object to be measured is placed at one pan and some masses of known weights are collected upon another until the beam will return to horizontal position showing that weights on both pans become equal.\n" +
                "\n" +
                "In the case above the object is the heart of a man and the feather of truth is used as a mass. At this scene participants are not interested in the exact weight of the heart but rather try to check whether it is lighter or heavier in comparison to some standard.";
        String r=api.getTransResult(query, "en", "zh");
        String rr=org.apache.commons.lang.StringEscapeUtils.unescapeJava(r);
        assertNotNull(rr);
        TransApi.TransResult r0=api.translate(query,"en","zh");
        assertNotNull(r0);
    }
}