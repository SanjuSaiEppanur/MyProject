package com.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bean.Customer;
import com.bean.CustomerItem;

import com.dao.ICustomerItemRepository;
import com.service.ICustomerItemService;

@SpringBootTest
public class CustomerItemServiceTest {

	
	@Autowired
	ICustomerItemService icustomeritemservice;
	
	@MockBean
	ICustomerItemRepository crepo;
	
	@Test
	void testaddItem()
	
	{
		
		Customer c=new Customer();
		c.setName("Sanju");
		c.setEmail("sanju@gmail.com");
		c.setContactNo("1998765432");
		c.setDob(LocalDate.of(1998, 8, 8));
		
		
		CustomerItem item=new CustomerItem();
		item.setItemId(10001);
		item.setName("Shirt");
		item.setCategory("Washing");
		item.setColor("Blue");
		item.setDescription("Good Condition");
		item.setMaterial("Cotton");
		item.setQuantity(2);
		item.setCustomer(c);
		
		Mockito.when(crepo.save(item)).thenReturn(item);
		CustomerItem response=icustomeritemservice.addItem(item);
		assertThat(response.getItemId()).isEqualTo(10001);
		assertThat(response.getName()).isEqualTo("Shirt");
		assertThat(response.getCategory()).isEqualTo("Washing");
		assertThat(response.getColor()).isEqualTo("Blue");
		assertThat(response.getDescription()).isEqualTo("Good Condition");
		assertThat(response.getMaterial()).isEqualTo("Cotton");
		assertThat(response.getQuantity()).isEqualTo(2);
		
		assertThat(response.getCustomer().getName()).isEqualTo("Sanju");
		assertThat(response.getCustomer().getEmail()).isEqualTo("sanju@gmail.com");
		assertThat(response.getCustomer().getContactNo()).isEqualTo("1998765432");
		assertThat(response.getCustomer().getDob()).isEqualTo(LocalDate.of(1998, 8, 8));
		
}
	
	@Test
	void testremoveItem()
	{
		CustomerItem item=new CustomerItem();
		item.setItemId(10001);
		item.setName("Pant");
		item.setCategory("DryCleaning");
		item.setColor("black");
		item.setDescription("Coloured");
		Optional<CustomerItem> item1=Optional.of(item);
		Mockito.when(crepo.findById((long) 10001)).thenReturn(item1);
		Mockito.when(crepo.existsById(item.getItemId())).thenReturn(false);
		assertFalse(crepo.existsById(item.getItemId()));
	}
	
	@Test
	void testupdateItem()
	{
	
		CustomerItem item=new CustomerItem();
		item.setItemId(10001);
		item.setName("Pant");
		item.setCategory("DryCleaning");
		item.setColor("black");
		item.setDescription("Coloured");
		Optional<CustomerItem> item1=Optional.of(item);
		Mockito.when(crepo.findById((long) 10001)).thenReturn(item1);
		item.setDescription("Good Condition");
		Mockito.when(crepo.save(item)).thenReturn(item);
		assertThat(icustomeritemservice.updateItem(item)).isEqualTo(item);
	
	}
	
	@Test
	void testgetItem()
	{
	CustomerItem item=new CustomerItem();
		item.setItemId(10001);
		item.setName("Pant");
		item.setCategory("DryCleaning");
		item.setColor("black");
		item.setDescription("Coloured");
		Optional<CustomerItem> item1=Optional.of(item);
		Mockito.when(crepo.findById((long) 10001)).thenReturn(item1);
		Optional<CustomerItem> item2=Optional.of(icustomeritemservice.getItem(10001));
		
	}
	
	@Test
	void testgetAllCustomerItems()
	{
	
		
		CustomerItem item=new CustomerItem();
		item.setItemId(10001);
		item.setName("Pant");
		List<CustomerItem> ls=new ArrayList<>();
		ls.add(item);
		Mockito.when(crepo.findAll()).thenReturn(ls);
		assertThat(icustomeritemservice.getAllCustomerItems()).isEqualTo(ls);
		
		item.setCategory("DryCleaning");
		item.setColor("black");
		item.setDescription("Coloured");
		Optional<CustomerItem> item1=Optional.of(item);
		Mockito.when(crepo.findById((long) 10001)).thenReturn(item1);
		
	}
	
}