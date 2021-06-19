package com.service;

import java.util.List;

import com.bean.CustomerItem;

public interface ICustomerItemService {
	
	 CustomerItem addItem(CustomerItem customerItem); 
	 
	 CustomerItem removeItem(long id);
	 
	 CustomerItem updateItem(CustomerItem customerItem);
	 
	 CustomerItem getItem(long id);

	List<CustomerItem> getItemsByCustomer(String customerId);

	List<CustomerItem> getAllCustomerItems();
	 
	 

}
