package collaborative.platform.model.products;

import collaborative.platform.model.Product;

public class Lemon extends Product {

    private static final int ID = 756;
    private static final String NAME = "Lemon";
    private static final String DESCRIPTION = "I like lemon";

    public Lemon() {
        super(ID, NAME);
        setDescription(DESCRIPTION);
    }
}
