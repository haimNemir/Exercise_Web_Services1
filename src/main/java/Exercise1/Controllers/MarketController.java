package Exercise1.Controllers;

import Exercise1.Beans.Cart;
import Exercise1.Beans.Product;
import Exercise1.Repositories.Exceptions.AlreadyExistException;
import Exercise1.Repositories.Exceptions.NotExistException;
import Exercise1.Services.MarketService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarketController {
    private MarketService service;

    public MarketController(MarketService service) {
        this.service = service;
    }

    // http://localhost:8080/add_product
    @PostMapping("add_product")
    public void addProduct(@RequestBody Product product) throws AlreadyExistException {
        service.addNewProduct(product);
    }

    // http://localhost:8080/add_cart
    @PostMapping("add_cart")
    public void addCart(@RequestBody Cart cart){
        service.addNewCart(cart);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam int id) throws NotExistException {
        service.deleteProduct(service.getOneProduct(id));
    }

    @GetMapping
    public Product getOneProduct(int id) throws NotExistException {
        return service.getOneProduct(id);
    }
}
