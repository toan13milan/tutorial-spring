package com.example.demo.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.CartProductDTO;
import com.example.demo.DTO.HistoryUserDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.TypePayDTO;
import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.Cart;
import com.example.demo.Entities.Cart_ProductB;
import com.example.demo.Entities.HistoryUser;
import com.example.demo.Entities.Orders;
import com.example.demo.Entities.Product;
import com.example.demo.Entities.ProductB;
import com.example.demo.service.AppUserService;
import com.example.demo.service.CartProductService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.HistoryUserService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductBService;
import com.example.demo.service.ProductService;
import com.example.demo.utils.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class DemoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private HistoryUserService historyUserService;
	
	@Autowired
	private CartProductService cartProductService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductBService productBService;
	
	@Autowired
	private CategoryService categoryService;
	
    // inject via application.properties
   @GetMapping({"", "/welcome"})
   public String welcomePage(Model model) {
	   model.addAttribute("title", "Welcome");
	   model.addAttribute("message", "This is welcome page");
	   return "welcomePage";
   }
  @GetMapping("/admin")
  public String adminPage(Model model, Principal principal) {
	  User user = (User) ((Authentication) principal).getPrincipal();
	  String userInfo = WebUtils.toString(user);
	  model.addAttribute("userInfo", userInfo);
	  return "adminPage";
  }
  @GetMapping("/login")
  public String loginPage(Model model) {
	  return "loginPage";
  }
  @GetMapping("/logoutSuccessful")
  public String logoutSuccessfull(Model model) {
	  model.addAttribute("title", "Logout");
	  return "logoutSuccessful";
  }
  
  @GetMapping("/userInfo")
  public String userInfo(Model model, Principal principal) {
	  if(principal !=null) {
		  String userName = principal.getName();
		  User user = (User)((Authentication)principal).getPrincipal();
		  String userInfo = WebUtils.toString(user);
		  model.addAttribute("userInfo", userInfo);
		  return "userInfo";
	  }
	  return "403Page";
  }
  
  @RequestMapping(value = "/403", method = RequestMethod.GET)
  public String accessDenied(Model model, Principal principal) {

      if (principal != null) {
          User loginedUser = (User) ((Authentication) principal).getPrincipal();

          String userInfo = WebUtils.toString(loginedUser);

          model.addAttribute("userInfo", userInfo);

          String message = "Hi " + principal.getName() //
                  + "<br> You do not have permission to access this page!";
          model.addAttribute("message", message);

      }

      return "403Page";
  }
  
  @GetMapping("/addProductPage")
  public String addProductPage(Model model, Principal principal) {
	  if (principal != null ) {
		  ProductDTO productDto = new ProductDTO();
		  model.addAttribute("productDto", productDto);
		  model.addAttribute("categories", categoryService.findAll());
		  return "addProductPage";
	  }
	  return "403Page";
  }
  
//  @GetMapping("/listProduct")
//  public String listProduct(Model model, Principal principal, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//	  int currentPage = page.orElse(1);
//	  int pageSize = size.orElse(5);
//	  Page<Product> productPage = productService.pageableProducct(PageRequest.of(currentPage - 1, pageSize));
//	  
//	  model.addAttribute("productPage", productPage);
//	  
//	  int totalPages = productPage.getTotalPages();
//	  
//	  if(totalPages > 0) {
//		  List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//				  .boxed()
//				  .collect(Collectors.toList());
//		  model.addAttribute("pageNumbers", pageNumbers);
//	  }
//	  if(principal != null) {
//		  User user = (User)((Authentication)principal).getPrincipal();
//		  String userInfo = WebUtils.toString(user);
//		  model.addAttribute("userInfo", userInfo);
//	  }
//	 
//	  return "listProduct";
//  }
  
  @PostMapping("/addProduct")
  public String addProduct(@Valid ProductDTO productDto,BindingResult bingdingResult, Model model, Principal principal) {
	  if(principal != null) {
		  if(bingdingResult.hasErrors()) {
			  model.addAttribute("productDto", productDto);
			  model.addAttribute("search", "");
			  model.addAttribute("categories", categoryService.findAll());
			  return "addProductPage";
		  }
		  
		//history
		  User loginedUser = (User) ((Authentication) principal).getPrincipal();
		  AppUser apUser = appUserService.findAppUser(loginedUser.getUsername());
		  productDto.setAppUser(new AppUser(apUser.getUserId()));
		  productService.createProduct(productDto);
		  HistoryUserDTO history = new HistoryUserDTO();
		  history.setProductName(productDto.getNameProduct());
		  history.setAppUser(apUser);
		  history.setAction("CREATE");
		  history.setDate(new Date().toInstant());
		  historyUserService.addHistory(history);
		  model.addAttribute("search", "");
		  return "redirect:listProduct?search";
	  }
	  return null;
  }
  
  @PostMapping("/editProduct")
  public String editProduct(Model model, Principal principal,@ModelAttribute("productDto") ProductDTO productDto) {
	  if(principal != null) {
		  User loginedUser = (User) ((Authentication) principal).getPrincipal();
		  AppUser apUser = appUserService.findAppUser(loginedUser.getUsername());
		  productDto.setAppUser(new AppUser(apUser.getUserId()));
		  productService.editProduct(productDto);
		  //history
		  HistoryUserDTO history = new HistoryUserDTO();
		  history.setProductName(productDto.getNameProduct());
		  history.setAppUser(apUser);
		  history.setAction("EDIT");
		  history.setDate(new Date().toInstant());
		  historyUserService.addHistory(history);
		  return "redirect:/listProduct?search";
	  }
	  return "403Page";
  }
  
  @GetMapping("/deleteProduct")
  public String deleteProduct(Model model, Principal principal, @RequestParam("id") Long id, @RequestParam("name") String name) {
	  if(principal != null) {
	  productService.deleteProductById(id);
	  List<Product> list = productService.listProduct();
	  model.addAttribute("products", list);
	  //history
	  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  AppUser apUser = appUserService.findAppUser(loginedUser.getUsername());
	  HistoryUserDTO history = new HistoryUserDTO();
	  history.setProductName(name);
	  history.setAppUser(apUser);
	  history.setAction("DELETE");
	  history.setDate(new Date().toInstant());
	  historyUserService.addHistory(history);
	  return "redirect:listProduct?search";
	  }
	  return "403Page";
  }
  
  @GetMapping("/getProduct")
  public String getProductById(Model model, @RequestParam("id") Long id) {
	  Product pro = productService.getProductById(id);
	  ProductDTO productDto = new ProductDTO();
	  model.addAttribute("productDto", productDto);
	  model.addAttribute("nameProduct", pro.getNameProduct());
	  model.addAttribute("amount", pro.getAmount());
	  model.addAttribute("cost", pro.getCost());
	  model.addAttribute("id", pro.getId());
	  model.addAttribute("categoryCurrentId", pro.getCategories().getId());
	  model.addAttribute("categoryCurrentName", pro.getCategories().getNameCat());
	  model.addAttribute("categories", categoryService.findAll());
	  return "editProductPage";
  }
  
  @GetMapping("/listProduct")
  public String searchProduct(Model model,Long type, @RequestParam("search") String search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Principal principal) {
	  if(principal != null ) {
		  User user = (User) ((Authentication) principal).getPrincipal();
		  Collection<String> authorities = user.getAuthorities().stream().map(u -> u.getAuthority()).collect(Collectors.toList());
		  if(authorities.contains("ROLE_ADMIN")) {
			  model.addAttribute("user", "admin");
		  } else {
			  model.addAttribute("user", "user");
		  }
	  }
	  int currentPage = page.orElse(1);
	  int pageSize = size.orElse(5);
	  Page<Product> pageSearch = productService.pageableSearchProduct(PageRequest.of(currentPage - 1, pageSize),search, type);
	  model.addAttribute("productPage", pageSearch);
	  model.addAttribute("search", search);
	  //return amount
	  model.addAttribute("amount", 0);
	  int totalPages = 3;
	  
	  if(totalPages > 0) {
		  List<Integer> pageNumbers = IntStream.rangeClosed(1, 3)
				  .boxed()
				  .collect(Collectors.toList());
		  model.addAttribute("pageNumbers", pageNumbers);
	  }
	  return "ListProduct";
  }
  
  @GetMapping("/history")
  public String getHistory(Model model, @RequestParam("search") String search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
	  int pageSize = size.orElse(5);
	  int currentPage = page.orElse(1);
	  
	  Page<HistoryUser> pageHistory = historyUserService.pagingHistory(PageRequest.of(currentPage-1, pageSize), search);
	  model.addAttribute("histories", pageHistory);
	  model.addAttribute("search", search);
	  
	  int totalPages = pageHistory.getTotalPages();
	  
	  if(totalPages > 0) {
		  List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		  model.addAttribute("pageNumbers", pageNumbers);
	  }
	  return "History";
  }
  
  @GetMapping("/addCart")
  public String addCart(Model model,Principal principal, @RequestParam("id") Long id, @RequestParam("amount") Long amount) {
	  if(principal != null) {
		  User loginedUser = (User) ((Authentication) principal).getPrincipal();
		  AppUser apUser = appUserService.findAppUser(loginedUser.getUsername());
		  Cart cart = cartProductService.getCartByUserId(apUser.getUserId());
		  CartProductDTO cartProduct = new CartProductDTO();
		  cartProduct.setCartId(cart.getId());
		  cartProduct.setProductBId(id);
		  cartProduct.setAmount(amount);
		  cartProductService.intoCart(cartProduct);
		  return "redirect:listProduct?search";
	  }
	  return "403Page";
  }
  
  @GetMapping("/listCart")
  public String listCart(Model model, @RequestParam("cartId") Long cartId, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
	  int pageSize = size.orElse(5);
	  int currentPage = page.orElse(1);
	  Page<Cart_ProductB> pageCart_ProductB = cartProductService.getCartProductinCartPageByCartId(PageRequest.of(currentPage-1, pageSize),cartId);
	  model.addAttribute("productPage", pageCart_ProductB);
	  List<TypePayDTO> listTypePay = new ArrayList<>();
	  listTypePay.add(new TypePayDTO(1L,"Pay Offline"));
	  listTypePay.add(new TypePayDTO(2L,"Pay Online"));
	  CartDTO cartDto = new CartDTO();
	  model.addAttribute("cartDto", cartDto);
	  model.addAttribute("typePays", listTypePay);
	  int totalPages = pageCart_ProductB.getTotalPages();
	  
	  if(totalPages > 0) {
		  List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		  model.addAttribute("pageNumbers", pageNumbers);
	  }
	  return "ListProductinCart";
  }
  
  @PostMapping("/buyProduct")
  public String buyProduct(@Valid CartDTO cartDto, Model model,Principal principal) {
	  List<Cart_ProductB> listProduct = cartProductService.getCartProductinCartByCartId(cartDto.getCartId());
	  User loginedUser = (User) ((Authentication) principal).getPrincipal();
	  AppUser apUser = appUserService.findAppUser(loginedUser.getUsername());
	  Orders order = new Orders();
	  order.setNameUser(cartDto.getNameUser());
	  order.setAddress(cartDto.getAddress());
	  order.setTypePay(cartDto.getTypePay());
	  Orders resultOrder = orderService.save(order);
	  List<ProductB> listProductB = listProduct.stream().map(p -> new ProductB(apUser,resultOrder,p.getProductB().getCost(),p.getAmount(),p.getProductB().getNameProduct())).collect(Collectors.toList());
	  productBService.saveAll(listProductB);
	  cartProductService.resetCart(cartDto.getCartId());
	  return "redirect:listProduct?search";
  }
  	
 }
