package edu.fu.demoshop.reponsitory;

import edu.fu.demoshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , Long> {
    @Query(value = "select * from product where rule_drop = 1", nativeQuery = true)
    List<Product> findAllProduct();

    @Query(value = "select * from product where name like '%?1%' and rule_drop = 1", nativeQuery = true)
    List<Product> search(String name);

    @Query(value = "select * from product where rule_drop = 1 and category_id=?1", nativeQuery = true)
    List<Product> findAllProductByCategoryId(@Param("category_id")Long category_id);


    @Query(value = "SELECT * FROM product \n" +
            "where rule_drop = 1 \n" +
            "Order by name DESC\n" +
            "OFFSET ?1 Rows\n" +
            "Fetch next ?2 rows only;", nativeQuery = true)

    List<Product> getAllProductPage(int page ,int pageSize);
}


