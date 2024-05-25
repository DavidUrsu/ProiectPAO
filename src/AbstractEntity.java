public abstract class AbstractEntity {
    protected int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
