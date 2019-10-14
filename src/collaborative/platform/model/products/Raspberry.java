package collaborative.platform.model.products;

import collaborative.platform.model.Product;

public class Raspberry extends Product {

    private static final int ID = 444;
    private static final String NAME = "Raspberry";
    private static final String DESCRIPTION = "I like raspberry";

    public Raspberry() {
        super(ID, NAME);
        setDescription(DESCRIPTION);
    }
}
