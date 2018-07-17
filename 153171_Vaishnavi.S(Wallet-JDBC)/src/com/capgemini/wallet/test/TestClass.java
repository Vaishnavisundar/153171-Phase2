package com.capgemini.wallet.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.wallet.beans.Customer;
import com.capgemini.wallet.beans.Wallet;
import com.capgemini.wallet.exception.InvalidInputException;
import com.capgemini.wallet.service.WalletService;
import com.capgemini.wallet.service.WalletServiceImpl;

public class TestClass {

	WalletService service;
	Customer cust1, cust2, cust3, cust4;

	@Before
	public void initData() {
		Map<String, Customer> data = new HashMap<String, Customer>();

		cust1 = new Customer("Vaishu", "9876543211", new Wallet(new BigDecimal(
				9000)));
		cust2 = new Customer("Ravali", "9876543212", new Wallet(new BigDecimal(
				6000)));
		cust3 = new Customer("Priyanka", "9876543213", new Wallet(
				new BigDecimal(7000)));
		cust4 = new Customer("Rekha", "9876543214", new Wallet(
				new BigDecimal(0)));

		data.put("9876543211", cust1);
		data.put("9876543212", cust2);
		data.put("9876543213", cust3);
		data.put("9876543214", cust4);
		service = new WalletServiceImpl(data);

	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccount() {

		service.createAccount(null, null, null);

	}

	@Test
	public void testCreateAccount1() {

		Customer c = new Customer();
		Customer cust = new Customer();
		cust = service.createAccount("Vaishu", "9876543211", new BigDecimal(
				7000));
		c.setMobileNo("9876543211");
		c.setName("Vaishu");
		c.setWallet(new Wallet(new BigDecimal(7000)));
		assertEquals(cust.getMobileNo(), c.getMobileNo());
		assertEquals(cust.getName(), c.getName());

	}

	@Test
	public void testCreateAccount2() {

		Customer cust = new Customer();
		cust = service.createAccount("Vaishu", "9876543211", new BigDecimal(
				7000));
		assertEquals("Amit", cust.getName());

	}

	@Test
	public void testCreateAccount3() {

		Customer cust = new Customer();
		cust = service.createAccount("Vaishu", "9876543211", new BigDecimal(
				7000));
		assertEquals("9876543211", cust.getMobileNo());

	}

	@Test(expected = InvalidInputException.class)
	public void testShowBalance() {
		Customer cust = new Customer();
		cust = service.showBalance("9579405744");

	}

	@Test
	public void testShowBalance2() {

		Customer cust = new Customer();

		cust = service.showBalance("9876543213");
		assertEquals(cust, cust3);

	}

	@Test
	public void testShowBalance3() {

		Customer cust = new Customer();
		cust = service.showBalance("9876543211");
		BigDecimal actual = cust.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(9000);
		assertEquals(expected, actual);

	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null, new BigDecimal(7000));
	}

	@Test
	public void testFundTransfer2() {
		cust1 = service.fundTransfer("9876543211", "9876543212",
				new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(8000);
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testDeposit() {
		service.depositAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testDeposit2() {
		cust1 = service.depositAmount("9876543212", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(8000);
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testWithdraw() {
		service.withdrawAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testWithdraw2() {
		cust1 = service.withdrawAmount("9876543212", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(4000);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetName() {
		Customer cust = new Customer("Capgemini", "9876543214");
		assertEquals("Capgemini", cust.getName());
	}

	@Test
	public void testGetMobileNo() {
		Customer cust = new Customer("Capgemini", "9876543214");
		assertEquals("9876543214", cust.getMobileNo());
	}

	@Test
	public void testGetBalance() {
		Wallet wallet = new Wallet(new BigDecimal(1000));
		Customer cust = new Customer("Capgemini", "9876543214", wallet);
		assertEquals(new BigDecimal(1000), cust.getWallet().getBalance());
	}

	@Test
	public void TestValidate() {
		Customer customer = new Customer("Vaish", "9876543214", new Wallet(
				new BigDecimal(10)));
		service.acceptCustomerDetails(customer);
	}

	@After
	public void testAfter() {
		service = null;
	}

}
