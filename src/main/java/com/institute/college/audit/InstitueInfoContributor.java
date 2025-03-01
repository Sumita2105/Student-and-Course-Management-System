package com.institute.college.audit;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InstitueInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder){
        Map<String, String> eazyMap = new HashMap<String, String>();
        eazyMap.put("App Name", "Institute application");
        eazyMap.put("App Version","1.0.0");
        builder.withDetail("info is",eazyMap);
    }



}
