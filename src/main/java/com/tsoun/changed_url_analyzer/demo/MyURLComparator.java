package com.tsoun.changed_url_analyzer.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyURLComparator {

    public final List<String> addedUrls = new ArrayList<>();
    public final List<String> deletedUrls = new ArrayList<>();
    public final List<String> changedUrls = new ArrayList<>();

    @PostConstruct
    public void addDataToTable() {
        compareTables(UrlDataUtils.YESTERDAY, UrlDataUtils.TODAY);
    }

    public void compareTables(Map<String, String> yesterday, Map<String, String> today) {
        for (Map.Entry<String, String> values : yesterday.entrySet()) {
            if (today.containsKey(values.getKey())) {
                if (!today.get(values.getKey()).equals(values.getValue())) {
                    changedUrls.add(values.getKey());
                    today.remove(values.getKey());
                }
            } else {
                deletedUrls.add(values.getKey());
                today.remove(values.getKey());
            }
        }
        addedUrls.addAll(today.keySet());
    }
}
