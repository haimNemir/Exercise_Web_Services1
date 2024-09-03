package Exercise1.Services;


import Exercise1.Exceptions.AlreadyExistException;
import Exercise1.Beans.Cart;
import Exercise1.Beans.Product;
import Exercise1.Exceptions.NotExistException;
import Exercise1.Repositories.CartRepository;
import Exercise1.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
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

    @Transactional//need to add if you want to delete from here, quote= GPT
    public void deleteProduct(Product product) throws NotExistException {
        // Update total_price in "cart" table:
        List<Integer> allCarts = cartRepository.getAllCartsIdFromProductId(product.getId());
        for (int i = 0; i < allCarts.size(); i++) {
            Cart cart = getOneCart(allCarts.get(i));
            cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
        }
        //deleting from "cart_products" purchased products:
        cartRepository.deleteAllFromCart_ProductsWhereProductId(product.getId());

        //delete product from "products"
        productRepository.deleteById(product.getId());
    }

    //need to delete all products to this cart
    public void deleteCart(Cart cart) throws NotExistException {
        if (cartRepository.existsById(cart.getId())){
            cartRepository.deleteAllFromCart_ProductsWhereCartId(cart.getId());
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

    public void addProductToCart(int productId, int cartId) throws NotExistException {
        System.out.println("service: "+productId + " " + cartId);
        //add to the total price
        Cart cart = cartRepository.findById(cartId).orElseThrow( () -> (new NotExistException("This product does not exist!")));
        cart.setTotalPrice(cart.getTotalPrice() + (productRepository.findById(productId)).orElseThrow().getPrice());
        cartRepository.save(cart);
        //adding to the table "products_cart".
        productRepository.addProductToCart(productId, cartId);
    }

    public void removeProductFromCart(int productId, int cartId){
        //Remove from the total price
    }
}
