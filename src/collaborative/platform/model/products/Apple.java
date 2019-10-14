package collaborative.platform.model.products;

import collaborative.platform.model.Product;

public class Apple extends Product {

    private static final int ID = 777;
    private static final String NAME = "Apple";
    private static final String DESCRIPTION = "I like apple";

    public Apple() {
        super(ID, NAME);
        setDescription(DESCRIPTION);
    }
}
