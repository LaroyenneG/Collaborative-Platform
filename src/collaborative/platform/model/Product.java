package collaborative.platform.model;

import java.io.Serializable;

public abstract class Product implements Serializable {

    private long id;

    private String name;
    private String description;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
        description = "";
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
