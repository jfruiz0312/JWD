package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entities.Account;
import com.example.service.RoleService;
import com.example.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	protected static final String USER_ADD_FORM_VIEW = "users/newUser"; 
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/newUser")
	public String newUser(Model model) {
			model.addAttribute("user", new Account());
			model.addAttribute("roles", roleService.getAllRoles());

		return USER_ADD_FORM_VIEW;
	}
	
	@PostMapping("/create")
	public String createArticle(@Valid Account account, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || result==null) {

			if (account.getUserName().isEmpty() || account.getPassword().isEmpty()) {
				attr.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
				attr.addFlashAttribute("user", account);
				attr.addFlashAttribute("error", "Error al crear el usuario");

				return "redirect:/users/newUser";
			}
			
		}
		Account newAccount = userService.createAccount(account);
		model.addAttribute("user", newAccount);

		return "redirect:/login/" ;
	}

}
