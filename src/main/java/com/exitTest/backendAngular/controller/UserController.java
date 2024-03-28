package com.exitTest.backendAngular.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exitTest.backendAngular.impl.ProductServiceImpl;
import com.exitTest.backendAngular.impl.ReviewServiceImpl;
import com.exitTest.backendAngular.model.Product;
import com.exitTest.backendAngular.model.Review;
import com.exitTest.backendAngular.model.Status;
import com.exitTest.backendAngular.model.User;
import com.exitTest.backendAngular.service.UserService;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private ReviewServiceImpl reviewService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
		
	}
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser( @RequestBody User user) {
		
		return userService.registerNewUser(user);	
	}
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL is only accessibile to admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "This URL is only accessibile to user";
	}
	
	@GetMapping({"/Count"})
	public Long countUser() {
		return userService.countUser();	
		
	}
	@PostMapping("/allProducts")
	public Status saveProduct( @RequestBody Product product) {
		return productService.addProduct(product);
		
	}
	@GetMapping("/Searchproducts")
	@CrossOrigin("*")
	public List<Product> product(@RequestParam String query) throws Exception {

		try {
			List<Product> products = this.productService.fetchProductByProductNameOrBrandOrProductCode(query);

			return products;
		} catch (Exception e) {
			throw new Exception("Product Not Found!");
		}
	}
	
	@GetMapping("/getallProducts")
	@CrossOrigin
	public List<Product> products() throws Exception {

		try {
			List<Product> products = this.productService.findAll();

			return products;
		} catch (Exception e) {
			throw new Exception("Product Not Found!");
		}
	}
	
	@GetMapping("/user/products")
	@CrossOrigin("*")
	public List<Product> showProducts() {
		return this.productService.findAll();
	}
	
	//For getting reviews
	
	@GetMapping("/user/reviews")
	public List<Review> showReviews() {
		return this.reviewService.findAllReviews();
	}
	
	//reviews for admin
	@GetMapping("/admin/reviews")
	public List<Review> showAllReviews() {
		return this.reviewService.findAll();
	}
	
	@CrossOrigin("*")
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) throws Exception {
		try {
			System.out.println(product);
			return this.productService.saveProduct(product);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	//To add Review to the Product
	
	@CrossOrigin("*")
	@PostMapping("/addReview")
	public Review addReview(@RequestBody Review review) throws Exception {
		try {
			System.out.println(review);
			return this.reviewService.addReview(review);
		} catch (Exception e) {
			throw new Exception("Bad Data");
		}
	}
	
	//Show review Based On ProductId
	@CrossOrigin("*")
	@GetMapping("products/{productId}/showReviews")
	public List<Review> showProductReview(@PathVariable Long productId) throws Exception {
		try {
			return this.reviewService.showProductReview(productId);
		} catch (Exception e) {
			throw new Exception("Product Not Found");
		}
	}
	
	//Stats for Home Screen
	@CrossOrigin("*")
	@GetMapping("home/stats")
	public List<Integer> showStates() {
		//List<User> users = 24; //this.userService.findAll();
		Long totalUsers =userService.countUser();	 ;// users.size();
		int userCounts=totalUsers.intValue();
		int posts = this.reviewService.findAllReviews().size();
		int onlineUsers = 0;
		
		List<Integer> stats = new ArrayList<>();
		stats.add(userCounts);
		stats.add(posts);
		stats.add(onlineUsers);
		return stats;
	}
	
	//Approve For Review
	@PutMapping("review/approve")
	@CrossOrigin("*")
	public Boolean approved(@RequestBody Review review) throws Exception {
		try {
			review.setApproved(true);
			this.reviewService.save(review);
			return true;
		} catch (Exception e) {
			throw new Exception("Something went wrong!!");
		}
	}
	
	
	@PutMapping("review/request")
	@CrossOrigin("*")
	public List<Review> requestReview(@RequestBody Product product) {
		Product p = this.productService.findByProductCode(product.getProductCode());
		if (p != null) {
			return this.reviewService.findByProductId(p.getProductId());
		} else if (p == null) {
			this.productService.saveProduct(product);
		}
		return null;
	}
	

}
