package com.tsoun.changed_url_analyzer.demo.service;

import com.tsoun.changed_url_analyzer.demo.MyURLComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyJavaMailServiceImpl implements MyJavaMailService{

    @Value("${sender}")
    private String sender;

    @Value("${receiver}")
    private String receiver;

    @Value("${subject}")
    private String subject;

    private final JavaMailSender javaMailSender;
    private final MyURLComparator myURLComparator;

    @Override
    public void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(receiver);
        msg.setSubject(subject + " " + LocalDate.now());
        msg.setText(
                createTextOfEmail(myURLComparator.deletedUrls, myURLComparator.addedUrls, myURLComparator.changedUrls));

        javaMailSender.send(msg);
    }

    @Override
    public String createTextOfEmail(List<String> deletedUris, List<String> addedUris, List<String> changedUris) {

        StringBuilder builder = new StringBuilder();
        builder.append("Здравствуйте, дорогая и.о. секретаря!\n\n")
                .append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n");

        if (!deletedUris.isEmpty()) {
            builder.append("\nИсчезли следующие страницы:\n");
            deletedUris.forEach(uri -> builder.append(uri).append("\n"));
        } else {
            builder.append("\nИсчезнувших страниц не обнаружено.\n");
        }
        if (!addedUris.isEmpty()) {
            builder.append("\nПоявились следующие новые страницы:\n");
            addedUris.forEach(uri -> builder.append(uri).append("\n"));
        } else {
            builder.append("\nНовых страниц не обнаружено.\n");
        }
        if (!changedUris.isEmpty()) {
            builder.append("\nИзменились следующие страницы:\n");
            changedUris.forEach(uri -> builder.append(uri).append("\n"));
        } else {
            builder.append("\nИзмененных страниц не обнаружено.\n");
        }
        builder.append("\nС уважением,\n Автоматизированная система мониторинга.");

        return builder.toString();
    }
}
