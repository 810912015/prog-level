package com.pl.admin.service.runner;


import com.pl.admin.service.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DefaultEngineProvider implements EngineProvider {
    private static Map<String,String> langs;
    static {
        langs=new HashMap<>();
        langs.put("java","java");
        langs.put("c","c");
        langs.put("c++","cpp");
        langs.put("c#","csharp");
        langs.put("python","python");
        langs.put("javascript","javascript");
        langs.put("go","go");
    }
    @Override
    public Engine getByLang(String lang) {
        for(Engine e:engines){
            if(lang.equals(e.getLang())){
                return e;
            }
        }
        return null;
    }

    @Autowired
    private List<Engine> engines;
    @Override
    public Map<String, String> getLangs() {
        return langs;
    }
}
