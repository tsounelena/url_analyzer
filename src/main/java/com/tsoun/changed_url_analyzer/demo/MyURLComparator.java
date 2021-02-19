package com.tsoun.changed_url_analyzer.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyURLComparator {

    public final List<URI> addedUrls = new ArrayList<>();
    public final List<URI> deletedUrls = new ArrayList<>();
    public final List<URI> changedUrls = new ArrayList<>();

    @PostConstruct
    public void addDataToTable() {
        compareTables(UrlDataUtils.YESTERDAY, UrlDataUtils.TODAY);
    }

    public void compareTables(Map<URI, String> yesterday, Map<URI, String> today) {
        for (Map.Entry<URI, String> values : yesterday.entrySet()) {
            if (today.containsKey(values.getKey())) {
                if (!today.get(values.getKey()).equals(values.getValue())) {
                    changedUrls.add(values.getKey());
                }
            } else {
                deletedUrls.add(values.getKey());
            }
        }
        for (Map.Entry<URI, String> values : today.entrySet()) {
            if (!yesterday.containsKey(values.getKey())) {
                addedUrls.add(values.getKey());
            }
        }
    }
}
