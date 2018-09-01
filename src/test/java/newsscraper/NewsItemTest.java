package newsscraper;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static newsscraper.NewsItem.MAX_STR_LENGTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewsItemTest {

    private static final String TEST_TITLE = "Everything is just fine";
    private static final String TEST_URI = "https://www.bbc.co.uk";
    private static final String TEST_AUTHOR = "Hans Mustermann";
    private static String loongString;

    @BeforeClass
    public static void oneTimeSetup() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < MAX_STR_LENGTH + 1; i++) {
            builder.append("A");
        }
        loongString = builder.toString();
    }

    @Test
    public void testGettersSetters() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);
        item.setPoints("1");
        item.setComments("2");
        item.setRank("3");

        assertEquals(TEST_TITLE, item.getTitle());
        assertEquals(new URI(TEST_URI), item.getUri());
        assertEquals(TEST_AUTHOR, item.getAuthor());
        assertEquals(1, item.getPoints());
        assertEquals(2, item.getComments());
        assertEquals(3, item.getRank());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAuthorThrowsIAEIfEmpty() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setAuthor("");
    }

    @Test(expected = NullPointerException.class)
    public void setAuthorThrowsNPEIfNull() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setAuthor(null);
    }

    @Test
    public void setAuthorTrimsLength() throws URISyntaxException {
        assertTrue("Sanity check failed: test string is already shorter than the limit", loongString.length() > MAX_STR_LENGTH);

        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, loongString);

        assertEquals("String length trimmed incorrectly", item.getAuthor().length(), MAX_STR_LENGTH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitleThrowsIAEIfEmpty() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setTitle("");
    }

    @Test(expected = NullPointerException.class)
    public void setTitleThrowsNPEIfEmpty() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setTitle(null);
    }

    @Test
    public void setTitleTrimsLength() throws URISyntaxException {
        assertTrue("Sanity check failed: test string is already shorter than the limit", loongString.length() > MAX_STR_LENGTH);

        final NewsItem item = new NewsItem(loongString, TEST_URI, TEST_AUTHOR);

        assertEquals("String length trimmed incorrectly", item.getTitle().length(), MAX_STR_LENGTH);
    }

    @Test(expected = URISyntaxException.class)
    public void setUriThrowsURISyntaxExceptionIfMalformed() throws URISyntaxException {
        new NewsItem(TEST_TITLE, "https://www.bb%c.co.uk", TEST_AUTHOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCommentsThrowsIAEIfNegative() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setComments("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPointsThrowsIAEIfNegative() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setPoints("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRankThrowsIAEIfNegative() throws URISyntaxException {
        final NewsItem item = new NewsItem(TEST_TITLE, TEST_URI, TEST_AUTHOR);

        item.setRank("-1");
    }
}