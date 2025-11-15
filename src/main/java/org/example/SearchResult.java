package org.example;

public class SearchResult {
    public static final String URL_PAGE_PREFIX = "https://ru.wikipedia.org/w/index.php?curid=";

    private String title;
    private long pageid;
    private int wordcount;
    private String snippet;
    private String timestamp;

    public String getTitle() { return title; }

    public int getWordcount() { return wordcount; }

    public String getSnippet() {
        if (snippet != null) {
            return snippet.replaceAll("<[^>]*>", "");
        }
        return snippet;
    }

    public String getTimestamp() { return timestamp; }

    public String getArticleUrl() {
        return URL_PAGE_PREFIX + this.pageid;
    }
}
