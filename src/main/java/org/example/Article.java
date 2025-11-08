package org.example;

import com.google.gson.Gson;

import java.net.URI;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Article {
    private Query query;

    public Query getQuery() {
        return query;
    }

    public static void parseSearch(String jsonResponse) {
        Gson gson = new Gson();

        try {
            Article wikiResponse = gson.fromJson(jsonResponse, Article.class);
            List<SearchResult> searchResults = wikiResponse.getQuery().getSearch();

            System.out.println("Найдено результатов: " + searchResults.size());
            System.out.println("======================================");

            int i = 0;
            for (SearchResult result : searchResults) {
                System.out.println("Cтатья № " + i);
                i++;
                System.out.println("Заголовок: " + result.getTitle());
                System.out.println("Сниппет: " + result.getSnippet());
                System.out.println("Слов: " + result.getWordcount());
                System.out.println("Обновлено: " + result.getTimestamp());
                System.out.println("-------------------------------------");
            }

            if (Desktop.isDesktopSupported() && !searchResults.isEmpty()) {
                Desktop desktop = Desktop.getDesktop();
                System.out.println("Выберете номер статьи:");

                Scanner scanner = new Scanner(System.in);
                int input = Integer.parseInt(scanner.nextLine());

                if (input > searchResults.size()) {
                    System.out.println("Не корректный выбор: input > количества статей!");
                } else if (input < 1) {
                    System.out.println("Не корректный выбор: input < количества статей!");
                } else {
                    SearchResult selectedArticle = searchResults.get(input - 1);
                    String articleUrl = selectedArticle.getArticleUrl();

                    System.out.println("Открываю статью: " + selectedArticle.getTitle());
                    System.out.println("URL: " + articleUrl);

                    desktop.browse(new URI(articleUrl));
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
    }
}
