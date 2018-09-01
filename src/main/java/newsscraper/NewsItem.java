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

    public void setComments(final int comments) throws IllegalArgumentException {
        if (comments < 0) {
            throw new IllegalArgumentException("Number of comments cannot be negative");
        }
        this.comments = comments;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) throws IllegalArgumentException {
        if (points < 0) {
            throw new IllegalArgumentException("Number of points cannot be negative");
        }
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) throws IllegalArgumentException {
        if (rank < 0) {
            throw new IllegalArgumentException("Rank cannot be negative");
        }
        this.rank = rank;
    }

    private String trimLength(final String s) {
        final int endIndex = Math.min(s.length(), MAX_STR_LENGTH);
        return s.substring(0, endIndex);
    }
}
