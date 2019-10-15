package collaborative.platform.model;

import collaborative.platform.model.products.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Product implements Serializable {

    private int id;

    private String name;
    private String description;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
        description = "";
    }

    public static Set<Product> productList() {

        Set<Product> productSet = new HashSet<>();

        productSet.add(new Apple());
        productSet.add(new Banana());
        productSet.add(new Lemon());
        productSet.add(new Peach());
        productSet.add(new Pineapple());
        productSet.add(new Raspberry());

        return productSet;
    }

    @Override
    public boolean equals(Object obj) {

        boolean state = false;

        if (obj instanceof Product) {
            Product product = (Product) obj;
            state = product.id == id;
        }

        return state;
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

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
