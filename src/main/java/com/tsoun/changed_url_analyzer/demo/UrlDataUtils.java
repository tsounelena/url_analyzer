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

    public static final List<URI> URIS = getListUri();
    public static final Map<URI, String> YESTERDAY = addDataToHashtable();
    public static final Map<URI, String> TODAY = addDataToHashtable();


    private static List<URI> getListUri() {

        List<URI> result = new ArrayList<>();

        try {
            result = Arrays.asList(new URI("https://ru.wikipedia.org/wiki/Бернулли,_Даниил"),
                    new URI("https://ru.wikipedia.org/wiki/Лагранж,_Жозеф_Луи"),
                    new URI("https://ru.wikipedia.org/wiki/Ляпунов,_Алексей_Андреевич"),
                    new URI("https://ru.wikipedia.org/wiki/Тьюринг,_Алан"),
                    new URI("https://ru.wikipedia.org/wiki/Перельман,_Григорий_Яковлевич"),
                    new URI("https://ru.wikipedia.org/wiki/Ферма,_Пьер"),
                    new URI("https://ru.wikipedia.org/wiki/Ломоносов,_Михаил_Васильевич"),
                    new URI("https://ru.wikipedia.org/wiki/Риман,_Бернхард"),
                    new URI("https://ru.wikipedia.org/wiki/Бернулли,_Иоганн")
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;

    }

    private static Map<URI, String> addDataToHashtable() {
        Map<URI, String> result = new Hashtable();
        for (int i = 0; i < URIS.size() - 1;
                i += Math.random() * 2 + 1) { //для имитации удаления/добавления url добавлен рандомный шаг
            URI currentUri = URIS.get(i);
            String html = getHtmlByUri(currentUri);
            if (i < Math.random() * 9) { //для имитации изменений html изменяем строку
                html += "change";
            }
            result.put(currentUri, html);
        }
        return result;
    }

    private static String getHtmlByUri(URI uri) {
        StringBuilder htmlResult = new StringBuilder();
        String inputLine;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()))) {
            while ((inputLine = reader.readLine()) != null) {
                htmlResult.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlResult.toString();
    }
}
