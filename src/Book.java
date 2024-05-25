public class Book extends AbstractEntity {
    private final String title;
    private final int authorId;
    private final boolean isDestroyed;

    public Book(int id, String title, int authorId) {
        super(id);
        this.title = title;
        this.authorId = authorId;
        this.isDestroyed = false;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
