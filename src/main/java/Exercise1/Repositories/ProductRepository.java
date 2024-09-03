package Exercise1.Repositories;

import Exercise1.Beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByTitle(String title);

    @Query(nativeQuery = true,value = "insert into online_market.cart_products(products_id, cart_id) values (?1, ?2)")
    void addProductToCart(int productId, int cartId);
}
