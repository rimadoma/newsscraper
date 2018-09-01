package newsscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsScraper {
    public static void main(String[] args) {
        try {
            int page = 1;
            final URL url = new URL("http://news.ycombinator.com/news?p=" + page);
            final Document document = Jsoup.parse(url, 10_000);
            parseItems(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseItems(final Document document) {
        final List<String> titles = parseTitles(document);
        titles.stream().forEach(System.out::println);
    }

    private static List<String> parseTitles(final Document document) {
        return document.select("tr.athing > td.title > a.storylink").eachText();
    }
}
