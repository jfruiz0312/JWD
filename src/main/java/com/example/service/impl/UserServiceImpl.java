package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.Account;
import com.example.repository.UserRepository;
import com.example.securityconfig.JpaUserDetailsService;
import com.example.service.UserService;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository  userRepository;
	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account createAccount(Account account) {
		Account newAccount;
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		newAccount =userRepository.save(account);
		return newAccount;
	}

	@Override
	public Account updateAccount(Long id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(Long accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
