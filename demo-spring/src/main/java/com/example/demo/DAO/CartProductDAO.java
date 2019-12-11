package com.example.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.CartProductDTO;
import com.example.demo.Entities.Cart;
import com.example.demo.Entities.Cart_ProductB;
import com.example.demo.Entities.Product;


@Repository
@Transactional
public class CartProductDAO {
	@Autowired
	private EntityManager entityManager;
	
	public void addToCart(CartProductDTO cartProductDTO) {
		entityManager.createNativeQuery("insert into cart_productb (cart_id,productb_id,amount) Values (?,?,?)")
					.setParameter(1, cartProductDTO.getCartId())
					.setParameter(2, cartProductDTO.getProductBId())
					.setParameter(3, cartProductDTO.getAmount())
					.executeUpdate();
	}
	
	public void updateToCart(CartProductDTO cartProductDTO) {
		entityManager.createNativeQuery("update cart_productb set amount = ? where cart_id =? and productb_id =?")
					.setParameter(1, cartProductDTO.getAmount())
					.setParameter(2, cartProductDTO.getCartId())
					.setParameter(3, cartProductDTO.getProductBId())
					.executeUpdate();
	}
	public void updateProductAfterBuy(Long cartId) {
		entityManager.createNativeQuery("update product p set amount = p.amount -cp.amount from cart_productb cp  where p.id = cp.productb_id and  cart_id=? and Sign(p.amount -cp.amount)>0")
					.setParameter(1, cartId)
					.executeUpdate();
	}
	public void resetToCart(Long cartId) {
		updateProductAfterBuy(cartId);
		entityManager.createNativeQuery("Delete from cart_productb where cart_id =?")
					.setParameter(1, cartId)
					.executeUpdate();
	}
	public void intoCart(CartProductDTO cartProductDTO) {
		List<Cart_ProductB> listCart = entityManager.createQuery("Select cp from Cart_ProductB cp where cp.cart.id = :cartId and cp.productB.id = :productId")
													.setParameter("cartId", cartProductDTO.getCartId())
													.setParameter("productId", cartProductDTO.getProductBId())
													.getResultList();
		if(listCart.size() > 0) {
			updateToCart(cartProductDTO);
		} else {
			addToCart(cartProductDTO);
		}
	}
	
	public Cart getCartByUserId(Long userId) {
		List<Cart> listCart = 
				entityManager.createQuery("Select c from Cart c join AppUser au on c.appUser = au where au.id = :userId")
							.setParameter("userId", userId)
							.getResultList();
		Cart cart = new Cart();
		cart = listCart.size() > 0 ? listCart.get(0) : cart;
		return cart;
	}
	
	
	public List<Product> getProductinCartByCartId(Long cartId) {
		List<Product> listProduct = entityManager.createQuery("Select p from Product p join Cart_ProductB cp on p = cp.productB where cp.cart.id = :cartId")
												.setParameter("cartId", cartId)
												.getResultList();
		
		return listProduct;
	}
	
	public List<Cart_ProductB> getCartProductinCartByCartId(Long cartId) {
		List<Cart_ProductB> listCartProduct = entityManager.createQuery("Select cp from Cart_ProductB cp join Product p on p = cp.productB where cp.cart.id = :cartId")
												.setParameter("cartId", cartId)
												.getResultList();
		
		return listCartProduct;
	}
	public Page<Cart_ProductB> getCartProductinCartPageByCartId(Pageable pageable, Long cartId) {
		List<Cart_ProductB> listCartProduct = entityManager.createQuery("Select cp from Cart_ProductB cp join Product p on p = cp.productB where cp.cart.id = :cartId")
												.setFirstResult(pageable.getPageNumber()*5) // offset
										         .setMaxResults(pageable.getPageSize()) // limit
												.setParameter("cartId", cartId)
												.getResultList();
		List<Long> count = entityManager.createQuery("Select count(cp) from Cart_ProductB cp join Product p on p = cp.productB where cp.cart.id = :cartId")
				.setParameter("cartId", cartId)
				.getResultList();
		Page<Cart_ProductB> pageCartProduct = new PageImpl<>(listCartProduct, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), count.get(0));
		return pageCartProduct;
	}
	
}
