package newsscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* If scrapers for other news sites are needed in the future,
create interface "NewsItem" with method "String toJSON()",
and interface "NewsScraper" with method "List<NewsItem> parse(int)".
Then refactor current code as concrete classes that implement those interfaces.
*/
public class NewsScraper {
    private static final int MAX_POSTS = 100;

    private static List<NewsItem> parse(final int posts) {
        final int capacity = Math.min(posts, MAX_POSTS);
        final List<NewsItem> items = new ArrayList<>(capacity);
        try {
            int page = 1;
            while (items.size() < posts) {
                final URL url = new URL("http://news.ycombinator.com/news?p=" + page);
                final Document document = Jsoup.parse(url, 10_000);
                final List<NewsItem> pageItems = parsePage(document);
                // TODO Exit if all items null (parsing failed for all)?
                if (pageItems.size() == 0) {
                    return items;
                }
                items.addAll(pageItems);
                page++;
                // TODO Should exit if page number reaches max (if there is one)
            }
        } catch (IOException e) {
            // TODO Log exception trace
            System.out.println("There was an error in the connection - please try again");
        }
        return items;
    }

    public static List<NewsItem> parsePage(final Document document) {
        final Iterator<Element> titleRows = document.select("tr.athing").iterator();
        final Iterator<Element> metaRows = document.select("tr > td.subtext").iterator();
        final List<NewsItem> items = new ArrayList<>();
        while (titleRows.hasNext() && metaRows.hasNext()) {
            final Element titleRow = titleRows.next();
            final Element metaRow = metaRows.next();
            final NewsItem item = parseItem(titleRow, metaRow);
            items.add(item);
        }
        // TODO Log an error if number of titleRows and metaRows does not match
        return items;
    }

    private static NewsItem parseItem(final Element titleRow, final Element metaRow) {
        final String rank = titleRow.select("span.rank").text().split("\\.")[0];
        final Elements link = titleRow.select("td.title > a.storylink");
        final String title = link.text();
        final String uri = link.attr("href");
        final String author = metaRow.select("a.hnuser").text();
        final String points = metaRow.select("span.score").text().split("\\s")[0];
        final String comments = metaRow.select("a").last().text().split("\\s")[0];
        NewsItem item;
        try {
            item = new NewsItem(title, uri, author);
            item.setRank(rank);
            item.setPoints(points);
            item.setComments(comments);
        } catch (final URISyntaxException | IllegalArgumentException e) {
            // TODO Log exception trace
            return null;
        }
        return item;
    }

    public static void main(final String[] args) {
        final List<NewsItem> items = parse(MAX_POSTS);
        items.stream().filter(Objects::nonNull).forEach(i -> System.out.println(i.toJSON()));
        final long badItems = items.stream().filter(Objects::isNull).count();
        if (badItems > 0) {
            System.out.println("There were " + badItems + " news items that could not be read");
        }
    }
}
