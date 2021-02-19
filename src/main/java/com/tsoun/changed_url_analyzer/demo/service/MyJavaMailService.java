package com.tsoun.changed_url_analyzer.demo.service;

import java.net.URI;
import java.util.List;

public interface MyJavaMailService {

    void sendMail();

    String createTextOfEmail(List<URI> deletedUris, List<URI> addedUris, List<URI> changedUris);
}
