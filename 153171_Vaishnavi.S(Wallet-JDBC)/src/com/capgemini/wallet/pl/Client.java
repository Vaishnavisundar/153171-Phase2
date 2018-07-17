package com.capgemini.wallet.pl;

import java.math.BigDecimal;
import java.util.Scanner;
import com.capgemini.wallet.beans.Customer;
import com.capgemini.wallet.service.WalletService;
import com.capgemini.wallet.service.WalletServiceImpl;

public class Client {

	private WalletService walletService;

	public Client() throws Exception {
		walletService = new WalletServiceImpl();
	}

	public void menu() {
		String ans = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("****Paytm Application****");
		Customer customer = new Customer();
		do {
			System.out.println("1 : Create account");
			System.out.println("2 : Show balance");
			System.out.println("3 : Fund transfer");
			System.out.println("4 : Deposit amount");
			System.out.println("5 : Withdraw amount");
			System.out.println("6 : Exit");

			System.out.println("please enter ur choice");
			int no = sc.nextInt();
			switch (no) {
			case 1:

				System.out.println("Enter name          : ");
				String name = sc.next();
				System.out.println("Enter Mobile Number :");
				String mobileNo = sc.next();
				System.out.println("Enter Balance       :");
				BigDecimal amount = sc.nextBigDecimal();
				customer = walletService.createAccount(name, mobileNo, amount);
				System.out.println("Your Account is successfully created");
				System.out.println("Account holder : " + customer.getName());
				System.out
						.println("Mobile number  : " + customer.getMobileNo());
				System.out.println("Balance        : "
						+ customer.getWallet().getBalance());
				break;
			case 2:
				System.out.println("Enter mobile number to view balance : ");
				String mobileNo2 = sc.next();

				customer = walletService.showBalance(mobileNo2);
				System.out.println("Balance for mobile number           : "
						+ mobileNo2 + "is" + customer.getWallet());
				break;
			case 3:
				System.out.println("Enter source mobile number : ");
				String sourceMobileNo = sc.next();

				System.out.println("Enter target mobile number :");
				String targetMobileNo = sc.next();

				System.out.println("Enter amount to transfer   :");
				BigDecimal amount1 = sc.nextBigDecimal();

				customer = walletService.fundTransfer(sourceMobileNo,
						targetMobileNo, amount1);
				System.out.println("Your Account is created");
				System.out.println("Account holder         : "
						+ customer.getName());
				System.out.println("mobile number          : "
						+ customer.getMobileNo());
				System.out.println("Balance after transfer : "
						+ customer.getWallet().getBalance());
				break;
			case 4:
				System.out.println("enter mobile number");
				String mobileNo3 = sc.next();

				System.out.println("enter amount to deposit");
				BigDecimal amount3 = sc.nextBigDecimal();

				customer = walletService.depositAmount(mobileNo3, amount3);
				System.out.println("Your Account is created");
				System.out.println("Account holder:" + customer.getName());
				System.out.println("mobile number:" + customer.getMobileNo());
				System.out.println("Balance after deposit :"
						+ customer.getWallet().getBalance());
				break;
			case 5:

				System.out.println("enter mobile number");
				String mobileNo4 = sc.next();

				System.out.println("enter amount to withdraw");
				BigDecimal amount4 = sc.nextBigDecimal();

				customer = walletService.withdrawAmount(mobileNo4, amount4);
				System.out.println("Your Account is created");
				System.out.println("Account holder:" + customer.getName());
				System.out.println("mobile number:" + customer.getMobileNo());
				System.out.println("Balance after withdraw :"
						+ customer.getWallet().getBalance());
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid options");
				break;
			}
			System.out.println("\n Do you want to continue Y/N");
			ans = sc.next();

		} while (ans.equalsIgnoreCase("y"));
		System.out.println("Thank you for using this service!");
	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.menu();
	}
}