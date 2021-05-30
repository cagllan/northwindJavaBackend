package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	Product getByProductName(String productName);
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	
	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);
	//select * from products where categories.category_id in(1,2,3,4)
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	//select * from products where product_name='Chai' and categoryId=8
	
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, "
			+ "p.productName, c.categoryName) From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	//select p.productId,p.productName,c.categoryName from Category c inner join Product p
	//on c.categoryId = p.CategoryId
}
