package Exercise1;

import Exercise1.Beans.Cart;
import Exercise1.Beans.Product;
import Exercise1.Repositories.Exceptions.NotExistException;
import Exercise1.Services.MarketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExerciseWebServices1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseWebServices1Application.class, args);
		System.out.println("hi");

	}
}
