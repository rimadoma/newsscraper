package newsscraper;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Holds the data of a single news item from the Hacker News feed.
 */
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

    /**
     * Sets the author field.
     * <p>
     * If argument is longer than {@link #MAX_STR_LENGTH}, then it's length is trimmed.
     * </p>
     *
     * @param author the name of the author of the news item.
     * @throws IllegalArgumentException if author is empty.
     */
    public void setAuthor(final String author) throws IllegalArgumentException {
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = trimLength(author);
    }

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title field.
     * <p>
     * If argument is longer than {@link #MAX_STR_LENGTH}, then it's length is trimmed.
     * </p>
     *
     * @param title the name of the title of the news item.
     * @throws IllegalArgumentException if title is empty.
     */
    public void setTitle(final String title) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = trimLength(title);
    }

    public URI getUri() {
        return uri;
    }

    /**
     * Sets the uri field.
     *
     * @param uri a uri address.
     * @throws URISyntaxException if uri is malformed, e.g. contains a '%' character.
     */
    public void setUri(final String uri) throws URISyntaxException {
        this.uri = new URI(uri);
    }

    public int getComments() {
        return comments;
    }

    /**
     * Sets the value of comments.
     *
     * @param comments number of comments the news item has.
     * @throws IllegalArgumentException if argument is negative.
     */
    public void setComments(final String comments) throws IllegalArgumentException {
        this.comments = parseNonNegativeInt(comments);
    }

    public int getPoints() {
        return points;
    }

    /**
     * Sets the value of points.
     *
     * @param points number of points the news item has.
     * @throws IllegalArgumentException if argument is negative.
     */
    public void setPoints(final String points) throws IllegalArgumentException {
        this.points = parseNonNegativeInt(points);
    }

    public int getRank() {
        return rank;
    }

    /**
     * Sets the value of rank.
     *
     * @param rank the rank (i.e. ordinal) of the news item.
     * @throws IllegalArgumentException if argument is negative.
     */
    public void setRank(final String rank) throws IllegalArgumentException {
        this.rank = parseNonNegativeInt(rank);
    }

    public JSONObject toJSONObject() {
        final JSONObject object = new JSONObject();
        object.put("title", title);
        object.put("uri", uri.toString());
        object.put("author", author);
        object.put("points", points);
        object.put("comments", comments);
        object.put("rank", rank);
        return object;
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
