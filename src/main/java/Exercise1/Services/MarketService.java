package Exercise1.Services;


import Exercise1.Repositories.Exceptions.AlreadyExistException;
import Exercise1.Beans.Cart;
import Exercise1.Beans.Product;
import Exercise1.Repositories.Exceptions.NotExistException;
import Exercise1.Repositories.CartRepository;
import Exercise1.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public MarketService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addNewProduct(Product product) throws AlreadyExistException {
        if (!(productRepository.existsByTitle(product.getTitle()))){
            productRepository.save(product);
        } else
            throw new AlreadyExistException("This product is already exist by title!");
    }
    public void addNewCart(Cart cart){
        cartRepository.save(cart);
    }

    // need to add deleting the product from !!all!! the carts
    @Transactional//need to add if you want to delete from here, quote= GPT
    public void deleteProduct(Product product) throws NotExistException {
        // Update total_price in "cart" table:
        List<Integer> allCarts = cartRepository.getAllCartsIdFromProductId(product.getId());
        System.out.println(allCarts);
        int count = 0;
        for (int i = 0; i < allCarts.size(); i++) {
            Cart cart = getOneCart(allCarts.get(i));
            cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
            count++;
        }
        System.out.println(count);
        //-----------------
        //delete from "cart_products" purchased products:
        cartRepository.deleteFromCart_ProductsWhereProductId(product.getId());

        //delete product from "products"
        productRepository.deleteById(product.getId());
    }

    //need to delete all products to this cart
    public void deleteCart(Cart cart) throws NotExistException {
        if (cartRepository.existsById(cart.getId())){
            cartRepository.deleteById(cart.getId());
        } else
            throw new NotExistException("This cart does not exist! Please enter existing cart..");
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getOneProduct(int id) throws NotExistException {
        return productRepository.findById(id).orElseThrow( () -> (new NotExistException("This product does not exist!")));
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart getOneCart(int id) throws NotExistException {
        return cartRepository.findById(id).orElseThrow( () -> (new NotExistException("This cart does not exist!")));
    }

    public void addProductToCart(int productId, int cartId){
        //add to the total price
    }

    public void removeProductFromCart(int productId, int cartId){
        //Remove from the total price
    }
}
