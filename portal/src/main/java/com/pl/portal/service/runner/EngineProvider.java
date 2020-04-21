package com.pl.portal.service.runner;



import java.util.Map;

public interface EngineProvider {
    Engine getByLang(String lang);
    Map<String,String> getLangs();
}
