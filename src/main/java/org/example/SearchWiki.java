package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchWiki {
    public static final String URL_SEARCH_PREFIX = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=\"";

    public static String searchWikiGetStr(String search) {
        String jsonResponse = "";
        try {
            String encodedSearch = URLEncoder.encode(search, "UTF-8");
            String urlStr = URL_SEARCH_PREFIX + encodedSearch + "\"";

            URL url = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonResponse = response.toString();
            } else {
                System.err.println("HTTP error: " + responseCode);
            }
            connection.disconnect();

        } catch (UnsupportedEncodingException e) {
            System.err.println("Ошибка кодирования: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.err.println("Некорректный URL: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        return jsonResponse;
    }
}
