package com.pl.admin.service.cdn;

import com.pl.data.common.api.CommonResult;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HwCdnServiceTest {
    @Test
    void put(){
        HwCdnService c=new HwCdnService();
        CommonResult<String> r= c.putObject("logo",new File("d:\\dog.jpg"));
        assertNotNull(r);
    }

    @Test
    void add(){
        List<String> r=new ArrayList<>();
        r.add(null);
        assertEquals(1,r.size());
    }

}