package com.example.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Article;
import com.example.entities.Category;
import com.example.service.CategoryService;
import com.example.util.PageInitPagination;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	protected static final String CATEGORY_VIEW = "categories/showCategory";
	protected static final String CATEGORY_ADD_FORM_VIEW = "categories/newCategory";
	protected static final String CATEGORY_EDIT_FORM_VIEW = "categories/editCategory";

	protected static final String CATEGORY_PAGE_VIEW = "categories/allCategories";
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getCategoryById(@PathVariable(value = "id") Long categoryId, Model model) {
		model.addAttribute("category", categoryService.findById(categoryId));
		return CATEGORY_VIEW;
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllCategories(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationCat(pageSize, page, CATEGORY_PAGE_VIEW);
		return modelAndView;

	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newCategory(Model model) {

		model.addAttribute("category", new Category());
		return CATEGORY_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createCategory(@Valid Category category, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() ) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
			attr.addFlashAttribute("category", category);

			attr.addFlashAttribute("error", "Error al crear categoria");

			return "redirect:/categories/new";
		}
		Category newCategory = categoryService.createCategory(category);
		model.addAttribute("category", newCategory);

		return "redirect:/categories/" + newCategory.getCategoryId();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editCategory(@PathVariable(value = "id") Long categoryId, Model model) {

		model.addAttribute("category", categoryService.findById(categoryId));
		return CATEGORY_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateCategory(@PathVariable(value = "id") Long categoryId, @Valid Category categoryDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
			attr.addFlashAttribute("category", categoryDetails);
			attr.addFlashAttribute("error", "Error al actuzaliar categoria");

			return "redirect:/categories/" + categoryDetails.getCategoryId() + "/edit";

		}
		categoryService.updateCategory(categoryId, categoryDetails);
		model.addAttribute("category", categoryService.findById(categoryId));
		return "redirect:/categories/" + categoryId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteCategory(@PathVariable("id") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return "redirect:/categories";
	}
}
