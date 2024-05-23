public abstract class AbstractEntity {
    protected int id;
    private static int nextId = 0;

    public AbstractEntity() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}
