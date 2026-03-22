package endpoints;



public class AdminEndpoints
{
	
	// LOGIN ENDPOINTS
	
	 public  static final String ADMIN_LOGIN ="/users/login";
	 
	 
	 // ADMIN PROFILE ENDPOINTS
	 
	 public static final String GET_ADMIN_BY_ID = "/admin/{adminId}";
	 
	 
	 public static final String UPDATE_ADMIN_BY_ID ="/admin/{adminId}";
	 
	 public static final String GET_ALL_MERCHANTS ="/merchants";

	 public static final String UPDATE_MERCHANT_STATUS ="/merchants/{merchantId}/status";

	 public static final String GET_MERCHANTS_BY_STATUS_AND_ZONE ="/merchants/status/zoneId";
	 
}
