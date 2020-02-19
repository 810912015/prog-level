package com.pl.admin.service.runner;



import com.pl.admin.service.Engine;

import java.util.Map;

public interface EngineProvider {
    Engine getByLang(String lang);
    Map<String,String> getLangs();
}
