package com.cooee.reader.common.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilTest {

    @Test
    void sha1() {
        String source="ActionCreateUHostInstanceCPU2ChargeTypeMonthDiskSpace10ImageIdf43736e1-65a5-4bea-ad2e-8a46e18883c2LoginModePasswordMemory2048NameHost01PasswordVUNsb3VkLmNuPublicKeyucloudsomeone@example.com1296235120854146120Quantity1Regioncn-bj2Zonecn-bj2-0446f09bb9fab4f12dfc160dae12273d5332b5debe";
        String result="4f9ef5df2abab2c6fccd1e9515cb7e2df8c6bb65";
        String r=HttpUtil.sha1(source);
        assertEquals(result,r);
    }

    @Test
    void postJson(){
        String json="{ \n" +
                "    \"Action\"     :  \"CreateUHostInstance\",\n" +
                "    \"ChargeType\" :  \"Month\",\n" +
                "    \"CPU\"        :  2,\n" +
                "    \"DiskSpace\"  :  10,\n" +
                "    \"ImageId\"    :  \"f43736e1-65a5-4bea-ad2e-8a46e18883c2\", \n" +
                "    \"LoginMode\"  :  \"Password\",\n" +
                "    \"Memory\"     :  2048,\n" +
                "    \"Name\"       :  \"Host01\",\n" +
                "    \"Password\"   :  \"VUNsb3VkLmNu\",\n" +
                "    \"PublicKey\"  :  \"ucloudsomeone@example.com1296235120854146120\",\n" +
                "    \"Quantity\"   :  1,\n" +
                "    \"Region\"     :  \"cn-bj2\",\n" +
                "   \"Zone\"       :  \"cn-bj2-04\",\n" +
                "    \"Signature\"  :  \"4f9ef5df2abab2c6fccd1e9515cb7e2df8c6bb65\"\n" +
                "}";
        String r=HttpUtil.postJson("https://api.ucloud.cn",json);
        assertNotNull(r);
    }
}