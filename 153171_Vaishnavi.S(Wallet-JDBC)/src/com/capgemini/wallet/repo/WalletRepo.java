package com.capgemini.wallet.repo;

import com.capgemini.wallet.beans.Customer;

public interface WalletRepo {

	public boolean save(Customer customer);

	public Customer findOne(String mobileNo);

	public Customer update(Customer customer);

}
