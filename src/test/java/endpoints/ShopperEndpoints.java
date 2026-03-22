package endpoints;

public class ShopperEndpoints {

    //SHOPPER PROFILE  ENDPOINTS
    public  static final String LOGIN ="/users/login";
       
    public static final String GET_SHOPPER_PROFILE = "/shoppers/{shopperId}";
       
    public static final String UPDATE_SHOPPER_PROFILE ="/shoppers/{shopperId}";
    
   
    
    //PRODUCT VIEW ACTION ENDPOINTS
    
    public static final String GET_PRODUCTS = "/products";
    
    public static final  String GET_DEFAULT_PRODUCTS="/products/alpha";
    
    
    
    //SHOPPER ADDRESS  ENDPOINTS
    public static final String ADD_ADDRESS ="/shoppers/{shopperId}/address";

    public static final String GET_ALL_ADDRESSES ="/shoppers/{shopperId}/address";

    public static final String GET_ADDRESS_BY_ID = "/shoppers/{shopperId}/address/{addressId}";

    public static final String UPDATE_ADDRESS ="/shoppers/{shopperId}/address/{addressId}";

    public static final String DELETE_ADDRESS ="/shoppers/{shopperId}/address/{addressId}";
    
    
    
    //SHOPPER WISHLIST ENDPOINTS 
    
    public static final String ADD_TO_WISHLIST = "/shoppers/{shopperId}/wishlist";
    
    public static final String GET_WISHLIST ="/shoppers/{shopperId}/wishlist";
    
    public static final String DELETE_FROM_WISHLIST ="/shoppers/{shopperId}/wishlist/{productId}";
    
  
    
    
    //SHOPPER CART ENDPOINTS
    
    public static final String ADD_TO_CART ="/shoppers/{shopperId}/carts";
    
    public static final String UPDATE_CART_ITEM = "/shoppers/{shopperId}/carts/{itemId}";
    
    public static final String DELETE_CART_ITEM ="/shoppers/{shopperId}/carts/{productId}";
  
    
    
    
    //SHOPPER ORDER ENDPOINTS
    
    public static final String GET_ALL_ORDERS ="/shoppers/{shopperId}/orders";
    
    public static final String CREATE_ORDER ="/shoppers/{shopperId}/orders";
    
    public static final String UPDATE_ORDER_STATUS ="/shoppers/{shopperId}/orders/{orderId}";
    
    public static final String GET_ORDER_INVOICE ="/shoppers/{shopperId}/orders/{orderId}/invoice";
    
    
    
    //SHOPPER PRODUCT REVIEW ENDPONTS
    
    public static final String ADD_REVIEW ="/reviews";

    public static final String GET_REVIEWS_BY_PRODUCT ="/reviews/{productId}";

    public static final String UPDATE_REVIEW ="/reviews/{reviewId}";

    public static final String DELETE_REVIEW ="/reviews/{reviewId}";
    

}