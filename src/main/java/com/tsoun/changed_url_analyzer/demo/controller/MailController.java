package com.tsoun.changed_url_analyzer.demo.controller;

import com.tsoun.changed_url_analyzer.demo.service.MyJavaMailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MyJavaMailServiceImpl myJavaMailServiceImpl;

    @GetMapping("/send_report")
    public void sendReport() {
        myJavaMailServiceImpl.sendMail();
    }


}
