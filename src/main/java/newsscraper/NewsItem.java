package newsscraper;

import java.net.URI;
import java.net.URISyntaxException;

public class NewsItem {
    public static final int MAX_STR_LENGTH = 256;

    private String title;
    private URI uri;
    private String author;
    private int points;
    private int comments;
    private int rank;

    public NewsItem(final String title, final String uri, final String author) throws URISyntaxException {
        setTitle(title);
        setUri(uri);
        setAuthor(author);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) throws IllegalArgumentException {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = trimLength(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = trimLength(title);
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(final String uri) throws URISyntaxException {
        this.uri = new URI(uri);
    }

    public int getComments() {
        return comments;
    }

    public void setComments(final String comments) throws IllegalArgumentException {
        this.comments = parseNonNegativeInt(comments);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final String points) throws IllegalArgumentException {
        this.points = parseNonNegativeInt(points);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(final String rank) throws IllegalArgumentException {
        this.rank = parseNonNegativeInt(rank);
    }

    private String trimLength(final String s) {
        final int endIndex = Math.min(s.length(), MAX_STR_LENGTH);
        return s.substring(0, endIndex);
    }

    private int parseNonNegativeInt(String s) {
        final int value = Integer.parseInt(s);
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        return value;
    }
}
