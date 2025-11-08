package org.example;

public class SearchResult {
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
        return "https://ru.wikipedia.org/w/index.php?curid=" + this.pageid;
    }
}
