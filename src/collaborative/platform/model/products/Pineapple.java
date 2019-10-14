package collaborative.platform.model.products;

import collaborative.platform.model.Product;

public class Pineapple extends Product {

    private static final int ID = 333;
    private static final String NAME = "Pineapple";
    private static final String DESCRIPTION = "I love pineapple";

    public Pineapple() {
        super(ID, NAME);
        setDescription(DESCRIPTION);
    }
}
