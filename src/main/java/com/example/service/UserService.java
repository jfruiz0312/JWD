package com.example.service;

import java.util.List;


import com.example.entities.Account;


public interface UserService {

	List<Account> getAllAccount();

	Account createAccount(Account account);

	Account updateAccount(Long id, Account account);

	void deleteAccount(Long accountId);

	Account findById(Long id);

}
