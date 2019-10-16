package collaborative.platform.agents;

public interface Protocol {
    String ONTOLOGY = "collaborative-plateform";

    String SERVICE_BUYER = "buyer";
    String SERVICE_SELLER = "seller";
    String SERVICE_DELIVERY = "delivery";
    String SERVICE_BANKER = "banker";

    String CUSTOMER_OFFER = "customerOffer";
    String TRANSACTION_REPLY = "TransactionReply";

    String BUYER_BUY = "buyerBuy";
    String BUYER_OFFER_FROM_SELLER = "buyerOfferFromSeller";
    String BUYER_OFFER_FROM_DELIVERY = "buyerOfferFromDelivery";

    String SELLER_REQUEST_PRICE = "sellerRequestPrice";

    String DELIVERY_REQUEST_PRICE = "deliveryRequestPrice";

    String BANKER_ASK_TRANSACTION = "bankerAskTransaction";
}
