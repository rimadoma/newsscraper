package newsscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NewsScraperTest {

    // Test html with three news items, the second one should fail parsing.
    private static final String HACKER_NEWS_HTML = "" +
            "<table>" +
            "<tr class='athing'>" +
            "<td><span class='rank'>1.</span></td>" +
            "<td class='title'><a href='https://testurl.org' class='storylink'>Link text</a></td>" +
            "</tr>" +
            "<tr>" +
            "<td class='subtext'>" +
            "<span class='score'>37 points</span>" +
            "<a class='hnuser'>User 1</a>" +
            "<a>5&nbsp;comments</a>" +
            "</td>" +
            "</tr>" +
            "<tr class='athing'>" +
            "<td><span class='rank'>2.</span></td>" +
            "<td class='title'><a href='https://testurl.org' class='storylink'>Link text</a></td>" +
            "</tr>" +
            "<tr>" +
            "<td class='subtext'>" +
            "<span class='score'>1 points</span>" +
            "<a class='hnuser'></a>" +
            "<a>5&nbsp;comments</a>" +
            "</td>" +
            "</tr>" +
            "<tr class='athing'>" +
            "<td><span class='rank'>3.</span></td>" +
            "<td class='title'><a href='https://testurl.org' class='storylink'>Link text</a></td>" +
            "</tr>" +
            "<tr>" +
            "<td class='subtext'>" +
            "<span class='score'>38 points</span>" +
            "<a class='hnuser'>User 3</a>" +
            "<a>6&nbsp;comments</a>" +
            "</td>" +
            "</tr>" +
            "</table>";

    @Test
    public void parsePage() {
        final Document document = Jsoup.parse(HACKER_NEWS_HTML);

        final List<NewsItem> newsItems = NewsScraper.parsePage(document);

        assertEquals(3, newsItems.size());
        assertEquals("User 1", newsItems.get(0).getAuthor());
        assertNull(newsItems.get(1));
        assertEquals("User 3", newsItems.get(2).getAuthor());
    }
}