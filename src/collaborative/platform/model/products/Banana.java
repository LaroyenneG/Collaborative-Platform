package collaborative.platform.model.products;

import collaborative.platform.model.Product;

public class Banana extends Product {

    private static final long ID = 888;
    private static final String NAME = "Banana";
    private static final String DESCRIPTION = "I love banana";

    public Banana() {
        super(ID, NAME);
        setDescription(DESCRIPTION);
    }

}
