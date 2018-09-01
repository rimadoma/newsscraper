package newsscraper;

import java.net.URI;
import java.net.URISyntaxException;

public class NewsItem {
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
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
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
        this.rank = rank;
    }
}
