package com.capgemini.mmbankwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.exception.AccountNotFoundException;


@WebServlet("*.mm")
public class MMBankSavingsAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @Override
public void init() throws ServletException {
	super.init();  
	   try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bankapp_db", "root", "root");
			PreparedStatement preparedStatement = 
					connection.prepareStatement("DELETE FROM ACCOUNT");
			preparedStatement.execute();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl();
		SavingsAccount savingsAccount = null;
		PrintWriter out = response.getWriter();
		String path = request.getServletPath();
		System.out.println(path);
	switch (path)
	{
	case "/addNewaccount.mm":
		System.out.println("Hello");
		response.sendRedirect("AddNewSavingsAccount.html");
		break;
	case "/AddNewSavingsAccount.mm":
		String accountHolderName = request.getParameter("Holdername");
		double accountBalance = Double.parseDouble(request.getParameter("accountBalance"));
		boolean salary = request.getParameter("salary").equalsIgnoreCase("Yes")?true:false;
		try {
			savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		break;
		
	case "/closeAccount.mm":
		response.sendRedirect("closeAccount.html");
		break;
		
	case "/closeaccount.mm":
		int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		try {
			String result = savingsAccountService.deleteAccount(accountNumber);
			out.println(result);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/getcurrentbalance.mm":
	response.sendRedirect("currentBalance.html");
	break;
	case "/getCurrentBalance.mm":
		int number = Integer.parseInt(request.getParameter("accountNumber"));
		try {
			//savingsAccountService.checkCurrentBalance(number);
			double balance = savingsAccountService
					.checkCurrentBalance(number);
			
			out.println(balance);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/withdraw.mm":
		response.sendRedirect("Withdraw.html");
		break;
	case "/Withdraw.mm":
		int accountnumber = Integer.parseInt(request.getParameter("accnumber"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		try {
			savingsAccount = savingsAccountService.getAccountById(accountnumber);
			savingsAccountService.withdraw(savingsAccount, amount);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/deposit.mm":
		response.sendRedirect("Deposit.html");
		break;
	case "/Deposit.mm":
		int depositNumber = Integer.parseInt(request.getParameter("accountNumber"));
		int Amount = Integer.parseInt(request.getParameter("amount"));
		try {
			savingsAccount = savingsAccountService.getAccountById(depositNumber);
			savingsAccountService.deposit(savingsAccount, Amount);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/fundtransfer.mm":
	response.sendRedirect("FundTransfer.html");
	break;
	
	case "/FundTransfer.mm":
		int senderAccountnumber = Integer.parseInt(request.getParameter("sender"));
		int receiverAccountNumber = Integer.parseInt(request.getParameter("receiver"));
		double amountToTransfer = Integer.parseInt(request.getParameter("amountToTransfer"));
			
		try {
			SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountnumber);
			SavingsAccount receiverSavingsAccount;
			receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
			savingsAccountService.fundTransfer(senderSavingsAccount,receiverSavingsAccount, amountToTransfer);
		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
			
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
