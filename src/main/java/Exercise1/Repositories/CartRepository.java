package Exercise1.Repositories;

import Exercise1.Beans.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(nativeQuery = true, value = "select cart_id from online_market.cart_products where products_id = ?1")
    List<Integer> getAllCartsIdFromProductId(int productId);

    @Modifying // when you use delete id Query you must use @Modifying annotation
    @Query(nativeQuery = true, value = "delete from online_market.cart_products where products_id = ?1")
    void deleteFromCart_ProductsWhereProductId(int productID);
}
