package com.shopbridge.constants;

public class StatusConstance {

	/*******
	 * CODE FOR RESPONSE
	 **************/
	public static final int SUCCESS = 200;

	public static final int MANDATORY = 401;
	
	public static final int USED = 402;

	public static final int NOT_FOUND = 404;

	public static final int SERVER_ERROR = 500;
	
	public static final int ALREADY_PRESENT= 300;
	
	public static final int ALREADY_PRESENT_MOBILE = 310;

	public static final int ALREADY_PRESENT_EMAIL = 311;
	
	public static final int ALREADY_LOGGED = 302;
	
	

	/*******
	 * TITLE FOR RESPONSE
	 **************/
	public static final String ID_REQ = "OBJECT ID REQUIRED";

	public static final String DATA_NOT_FOUND = "RECORD NOT FOUND";

	public static final String DATA_FOUND = "RECORD FOUND";

	public static final String SUCCESS_ADD = "RECORD ADDED SUCCESSFULLY";

	public static final String SUCCESS_UPDATE = "RECORD UPDATED SUCCESSFULLY";

	public static final String BULK_DATA_REQ = "BULK DATA REQUIRED";
	
	// Change Status
	public static final String STATUS_CHANGE = "STATUS CHANGED SUCCESSFULLY";

	// DELETE
	public static final String DELETE = "RECORD DELETED SUCCESSFULLY";
	
	public static final String UNABLE_DELETE = "UNABLE TO DELETE";
	
	public static final String LIST_DATA_TITLE = "LIST OF DATA";
	
	
	//AUTHENTICATION
	public static final String ACCOUNT_NOT_FOUND_TITLE = "ACCOUNT NOT FOUND";
	
	public static final String ALREADY_PRESENT_TITLE = "ALREADY REGISTERED";
	
	
	public static final String REGISTRATION_DONE = "REGISTRATION COMPLETED";
	
	
	public static final String PASSOWORD_REQ_TITILE = "PASSWORD REQUIRED";
	
	
	public static final String WRONG_PASSWORD = " WRONG PASSWORD";
	
	public static final String LOGIN_SUCCESSFULL = "LOGIN SUCCESSFULLY";
	
	public static final String USER_NOT_FOUND = "NOT FOUND ACCOUNT";
	
	public static final String PROFILE_UPDATED_TITLE = "PROFILE UPDATED";
	
	public static final String DEFUALT_ADDRESS_CHANGED = "DEFUALT_ADDRESS_CHANGED";
	
	public static final String RECORD_DELETED = "RECORD_DELETED";

	/*******
	 * MESSAGE FOR RESPONSE
	 **************/

	public static final String RECORD_ADDED = "New record added successfully";

	public static final String RECORD_UPDATED = "Record updated successfully";

	public static final String RECORD_NOT_FOUND = "Record not found";

	public static final String RECORD_FOUND = "Record find successfully";

	public static final String ID_REQ_TO_UPDATE = " Id is required to fetch existing record to perform operation";

	public static final String SERVER_ERROR_MSG = "Server Error. Please Contact to support";
	
	public static final String RECORD_NOT_FOUNDED = "RECORD NOT FOUND";

	// Change Status
	public static final String STATUS_CHANGE_MSG = " Status is changed to --> ";

	// DELETE
	public static final String DELETE_MSG = "Record is deleted successfully";
	
	public static final String UNABLE_DELETE_MSG = "Record is unable to delete because its use in some where";
	
	//list
	public static final String LIST_DATA = "List Data Retrived Successfully";

	// MESSAGE FOR CATEGORY
	public static final String PARENT_REQ = "Parent Category Id is required to add your category as a child category";

	public static final String PARENT_CAT_ADD = "Category added as a parent category";

	public static final String CHILD_CAT_ADD = "Category added as a child  category";

	public static final String CATEGORY_NOT_FOUND = "By Parent Category Id, Parent Category object not found";
	
	
	//Product
	public static final String PRODUCR_NOT_FOUND = "Product data not found by which id you passed";
	
	public static final String PRODUCT_STOCK_STATUS = "Product Stock Status Changed Successfully";
	
	public static final String PRODUCT_UNIT_STOCK_STATUS = "Product Unit Stock Status Changed Successfully";

	public static final String PRODUCT_CATEOGRY_NOT_FOUND = "Product Category not found";
	
	//pramotional
	
	public static final String PRAMOTION_PRODUCT_REQ = "Please add at least 4 product in pramotional category";
	
	
	//AUTHENTICATION
    
	public static final String ACCOUNT_NOT_FOUND = "Oops. We couldn't sign you in. Please check your mobile no or Sing Up";
	
	public static final String REGISTRATION_SUCCESSFULL = "Registration is done successfully";
	
	
	public static final String ALREADY_PRESENT_MOBILE_MSG = "Mobile no is already registered. Please use another  mobile OR go for sign in";
 
	public static final String ALREADY_PRESENT_EMAIL_MSG = "EMail is already registered. Please use another email OR go for sign in ";
	
	public static final String PASSWORD_REQUIRED_MSG = "Password is required to complete registration";
	
	public static final String WRONG_PASSWORD_MSG = "Wrong password. Try again or click forgot password";
	
	public static final String PROFILE_UPDATED_MSG = "Profile Updated Successfully";
	
	
	public static final String LOGIN_DONE = "Login Process done";
	
	public static final String USER_NOT_FOUND_MSG = "User not found";
	
	public static final String DEFUALT_ADDRESS_CHANGED_TITLE = "Defualt address changed successfully ";
	
	public static final String RECORD_DELETED_MSG = "Record Deleted Successfully";
	
	
	
	
	//CHANGE PASSWORD
	public static final String CHANGE_PASS_TITLE = "PASSWORD CHANGED";
	
	public static final String CHANGE_PASS_MSG = "Password changed successfully";
	
	public static final String OLD_PASSWORD = "WRONG OLD PASSWORD";
	
	public static final String OLD_PASSWORD_MSG = "Entered old password is wrong";
	
	
	

}
