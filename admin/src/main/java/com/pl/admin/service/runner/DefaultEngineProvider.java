package com.pl.admin.service.runner;


import com.pl.admin.service.Engine;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
        switch (lang){
            case "java":
                return new JavaRunner();
            case "c":
                return new LangRunners.C();
            case "cpp":
                return new LangRunners.Cpp();
            case "csharp":
                return new LangRunners.Csharp();
            case "python":
                return new LangRunners.Python();
            case "javascript":
                return new LangRunners.Js();
            case "go":
                return new LangRunners.Go();
        }
        return null;
    }

    @Override
    public Map<String, String> getLangs() {
        return langs;
    }
}
