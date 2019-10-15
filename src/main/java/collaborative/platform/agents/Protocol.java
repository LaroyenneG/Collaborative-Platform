package collaborative.platform.agents;

public interface Protocol {
    public static final String ONTOLOGY = "collaborative-plateform";

    public static final String SERVICE_BUYER = "buyer";
    public static final String SERVICE_SELLER = "seller";
    public static final String SERVICE_DELIVERY = "delivery";
    public static final String SERVICE_BANKER = "banker";

    public static final String CUSTOMER_OFFER = "customerOffer";
    public static final String CUSTOMER_ACCEPT_REJECT = "customerAcceptReject";

    public static final String BUYER_BUY = "buyerBuy";
    public static final String BUYER_OFFER_FROM_SELLER = "buyerOfferFromSeller";
    public static final String BUYER_OFFER_FROM_DELIVERY = "buyerOfferFromDelivery";

    public static final String SELLER_REQUEST_PRICE = "sellerRequestPrice";

    public static final String DELIVERY_REQUEST_PRICE = "deliveryRequestPrice";

    public static final String BANKER_ASK_TRANSACTION = "bankerAskTransaction";
}
