package com.tsoun.changed_url_analyzer.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class UrlDataUtils {

    public static final List<String> URIS = getListUri();
    public static final Map<String, String> YESTERDAY = addDataToHashtable();
    public static final Map<String, String> TODAY = addDataToHashtable();


    private static List<String> getListUri() {

        List<String> result = new ArrayList<>();
            result = Arrays.asList("https://ru.wikipedia.org/wiki/Бернулли,_Даниил",
                    "https://ru.wikipedia.org/wiki/Лагранж,_Жозеф_Луи",
                   "https://ru.wikipedia.org/wiki/Ляпунов,_Алексей_Андреевич",
                    "https://ru.wikipedia.org/wiki/Тьюринг,_Алан",
                    "https://ru.wikipedia.org/wiki/Перельман,_Григорий_Яковлевич",
                    "https://ru.wikipedia.org/wiki/Ферма,_Пьер",
                    "https://ru.wikipedia.org/wiki/Ломоносов,_Михаил_Васильевич",
                    "https://ru.wikipedia.org/wiki/Риман,_Бернхард",
                    "https://ru.wikipedia.org/wiki/Бернулли,_Иоганн");

        return result;

    }

    private static Map<String, String> addDataToHashtable() {
        Map<String, String> result = new Hashtable();
        for (int i = 0; i < URIS.size() - 1;
                i += Math.random() * 2 + 1) { //для имитации удаления/добавления url добавлен рандомный шаг
            String currentUri = URIS.get(i);
            String html = getHtmlByUri(currentUri);
            if (i < Math.random() * 9) { //для имитации изменений html изменяем строку
                html += "change";
            }
            result.put(currentUri, html);
        }
        return result;
    }

    private static String getHtmlByUri(String uri) {
        StringBuilder htmlResult = new StringBuilder();
        String inputLine;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URI(uri).toURL().openStream()))) {
            while ((inputLine = reader.readLine()) != null) {
                htmlResult.append(inputLine);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return htmlResult.toString();
    }
}
