package com.tsoun.changed_url_analyzer.demo.service;

import java.util.List;

public interface MyJavaMailService {

    void sendMail();

    String createTextOfEmail(List<String> deletedUris, List<String> addedUris, List<String> changedUris);
}
