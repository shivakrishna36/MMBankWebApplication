package com.capgemini.mmbankwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
	int count = 1;
  @Override
public void init() throws ServletException {
	super.init();  
	   try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bankapp_db", "root", "root");
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNT");
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
		response.sendRedirect("AddNewSavingsAccount.jsp");
		break;
	case "/AddNewSavingsAccount.mm":
		String accountHolderName = request.getParameter("Holdername");
		double accountBalance = Double.parseDouble(request.getParameter("accountBalance"));
		boolean salary = request.getParameter("salary").equalsIgnoreCase("Yes")?true:false;
		try {
			savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
			response.sendRedirect("getAllAccounts.mm");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		break;
		
	case "/closeAccount.mm":
		response.sendRedirect("closeAccount.jsp");
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
	response.sendRedirect("currentBalance.jsp");
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
		response.sendRedirect("Withdraw.jsp");
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
		response.sendRedirect("Deposit.jsp");
		break;
	case "/Deposit.mm":
		int depositNumber = Integer.parseInt(request.getParameter("accountNumber"));
		int amount1 = Integer.parseInt(request.getParameter("amount"));
		try {
			savingsAccount = savingsAccountService.getAccountById(depositNumber);
			savingsAccountService.deposit(savingsAccount, amount1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/fundtransfer.mm":
	response.sendRedirect("FundTransfer.jsp");
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
	case "/search.mm":
		response.sendRedirect("searchAccountById.jsp");
		break;
	case "/searchAccountById.mm":
		int accountNumber1 = Integer.parseInt(request.getParameter("accountNumber"));
		try {
			SavingsAccount account = savingsAccountService.getAccountById(accountNumber1);
			request.setAttribute("account", account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
			dispatcher.forward(request,response);
		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e) {
			e.printStackTrace();
		}
		break;
	case "/getAll.mm":
		response.sendRedirect("getAllAccounts.mm");
		break;
	case "/getAllAccounts.mm":
		try {
			List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
			request.setAttribute("accounts", accounts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/update.mm":
		response.sendRedirect("update.jsp");
		break;
	case "/updateAccount.mm":
		int accountNumberSearch = Integer.parseInt(request.getParameter("accountNumber"));
		try {
			SavingsAccount account = savingsAccountService.getAccountById(accountNumberSearch);
			request.setAttribute("account", account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateAccount.jsp");
			dispatcher.forward(request,response);
		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e) {
			e.printStackTrace();
		}
		break;
	case "/updateDetails.mm":
		int accountNumberUpdate = Integer.parseInt(request.getParameter("accountNumber"));
		SavingsAccount account;
		try {
			account = savingsAccountService.getAccountById(accountNumberUpdate);
			String accountHolderName1 = request.getParameter("name");
			boolean salary1 = request.getParameter("salary").equalsIgnoreCase("Yes")?true:false;
			savingsAccountService.updateAccount(account,accountHolderName1,salary1);
		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case "/sortByName.mm":
		count++;
		Collection<SavingsAccount> accountsName;
		try {
			accountsName = savingsAccountService.getAllSavingsAccount();
			ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(accountsName);
			Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
				@Override
				public int compare(SavingsAccount arg0, SavingsAccount arg1) {
					int result =  arg0.getBankAccount().getAccountHolderName().compareTo(arg1.getBankAccount().getAccountHolderName());
				if(count %2 ==0)
					return result;
				
				else 
					return -result;
				}
			});
			request.setAttribute("accounts", accountsNameList);
			//request.setAttribute("accounts", accounts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		break;
		
	case "/sortByAccountNumber.mm":
		count++;
		Collection<SavingsAccount> accountsNumber;
		try {
			accountsNumber = savingsAccountService.getAllSavingsAccount();
			ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(accountsNumber);
			Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
				@Override
				public int compare(SavingsAccount arg0, SavingsAccount arg1) {
					int result =  arg0.getBankAccount().getAccountNumber()- arg1.getBankAccount().getAccountNumber();
				if(count %2 ==0)
					return result;
				
				else 
					return -result;
				}
			});
			request.setAttribute("accounts", accountsNameList);
			//request.setAttribute("accounts", accounts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		break;
		
	case "/sortByBalance.mm":
		count ++;
		Collection<SavingsAccount> accountsbalance;
		try {
			accountsbalance = savingsAccountService.getAllSavingsAccount();
			ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(accountsbalance);
			Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
				public int compare(SavingsAccount one,SavingsAccount two){
					int result = (int) (one.getBankAccount().getAccountBalance() - two.getBankAccount().getAccountBalance());
				if(count%2 == 0)
					return result;
				else
					return -result;
				}
			});
		request.setAttribute("accounts", accountsNameList);
		//request.setAttribute("accounts", accounts);
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("AccountDetails.jsp");
		dispatcher1.forward(request, response);
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
				
		break;
		
	case "/sortBySalaried.mm":
		count ++;
		Collection<SavingsAccount> salaried;
		try {
			salaried = savingsAccountService.getAllSavingsAccount();
			ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(salaried);
			Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
				public int compare(SavingsAccount one,SavingsAccount two){
					int result = Boolean.compare(one.isSalary(),two.isSalary());
				if(count%2 == 0)
					return result;
				else
					return -result;
				}
			});
		request.setAttribute("accounts", accountsNameList);
		//request.setAttribute("accounts", accounts);
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("AccountDetails.jsp");
		dispatcher1.forward(request, response);
	} catch (ClassNotFoundException | SQLException e) {
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
