package org.example;

import java.util.Scanner;

import static org.example.Article.parseSearch;
import static org.example.SearchWiki.searchWikiGetStr;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input request:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Вы ввели: " + input);

        String jsonstr = searchWikiGetStr(input);
        System.out.println(jsonstr);
        parseSearch(jsonstr);
        scanner.close();
    }
}