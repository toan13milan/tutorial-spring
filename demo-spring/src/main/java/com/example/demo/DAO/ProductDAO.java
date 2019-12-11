package com.example.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Entities.Product;


@Repository
@Transactional
public class ProductDAO {
	@Autowired
    private EntityManager entityManager;
	
	public List<Product> getAllProduct() {
		 String sql = "Select pr from Product pr ";
		 Query query = this.entityManager.createQuery(sql, Product.class);
		 return query.getResultList();
	}
	
	public void createProduct(ProductDTO productDTO) {
		 entityManager.createNativeQuery("INSERT INTO Product (name_product,amount,pro_cost,user_id,category_id) VALUES (?,?,?,?,?)")
	      .setParameter(1, productDTO.getNameProduct())
	      .setParameter(2, productDTO.getAmount())
	      .setParameter(3, productDTO.getCost())
	      .setParameter(4, productDTO.getAppUser().getUserId())
	      .setParameter(5, productDTO.getCategories())
	      .executeUpdate();
	}
	
	public void editProduct(ProductDTO productDTO) {
		entityManager.createNativeQuery("update Product set name_product = ?, amount = ?, pro_cost = ?, category_id =? where id = ?")
			.setParameter(1, productDTO.getNameProduct())
			.setParameter(2, productDTO.getAmount())
			.setParameter(3, productDTO.getCost())
			.setParameter(4, productDTO.getCategories())
			.setParameter(5, productDTO.getId())
			.executeUpdate();
	}
	
	public void deleteProductById(Long id) {
		 entityManager.createNativeQuery("delete from Product where id = ?")
		 	.setParameter(1, id)
		 	.executeUpdate();
	}
	
	public Product getProductById(Long id) {
		String sql = "Select pr from Product pr join Categories c on pr.categories = c where pr.id= :id";
		 Query query = this.entityManager.createQuery(sql, Product.class);
		 query.setParameter("id", id);
		 List<Product> listPro = query.getResultList();
		 Product pro = null;
		 if(listPro.size() > 0) {
			 pro = (Product) query.getResultList().get(0);
		 }
		 return pro;
	}
	
	public Page<Product> searchProduct(Pageable pageable, String search) {
		List<Product> listProduct =  entityManager.createQuery(
			    "Select pr from Product pr WHERE pr.nameProduct LIKE :search")
				.setFirstResult(pageable.getPageNumber()*5) // offset
		         .setMaxResults(pageable.getPageSize()) // limit
			    .setParameter("search", search+'%')
			    .getResultList();
		List<Long> count = entityManager.createQuery("Select count(pr) from Product pr WHERE pr.nameProduct LIKE :search")
				.setParameter("search", search+'%')
			    .getResultList();
		Page<Product> pageProduct = new PageImpl<>(listProduct, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), count.get(0));
		return pageProduct;
	}
	
	public Page<Product> searchProductByType(Pageable pageable, String search, Long type) {
		List<Product> listProduct = entityManager.createQuery(
			    "Select pr from Product pr where pr.nameProduct LIKE :search and pr.categories.id= :type")
				.setFirstResult(pageable.getPageNumber()*5) // offset
		         .setMaxResults(pageable.getPageSize()) // limit
			    .setParameter("search", search+'%')
			    .setParameter("type", type)
			    .getResultList();
		List<Long> count = entityManager.createQuery("Select count(pr) from Product pr where pr.nameProduct LIKE :search and pr.categories.id= :type")
			    .setParameter("search", search+'%')
			    .setParameter("type", type)
			    .getResultList();
		Page<Product> pageProduct = new PageImpl<>(listProduct, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), count.get(0));
		return pageProduct;
	}
}
