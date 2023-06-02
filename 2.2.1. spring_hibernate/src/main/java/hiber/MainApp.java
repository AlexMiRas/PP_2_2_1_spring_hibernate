package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.dao.UserDaoImp;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("Name1", "LastName1", "mail1@mail.ru");
      User user2 = new User("Name2", "LastName2","mail2@mail.ru");
      User user3 = new User("Name3", "LastName3","mail3@mail.ru");
      User user4 = new User("Name4", "LastName4","mail4@mail.ru");

//      user1.setCar(new Car("BMW", 7));
//      user2.setCar(new Car("Mazda", 6));
//      user3.setCar(new Car("AUDI", 5));
//      user4.setCar(new Car("AUDI", 5));

      Car car1 = new Car("Mazda", 3);
      Car car2 = new Car("Audi", 6);
      Car car3 = new Car("BMW", 2);
      Car car4 = new Car("Zhiguli", 6);

      car1.setUser(user1);
      car2.setUser(user2);
      car3.setUser(user3);
      car4.setUser(user4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);


//      userService.add(user1);
//      userService.add(user2);
//      userService.add(user3);
//      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println("------------------ \n" + userService.findUserByCar("BMW", 2));
      System.out.println("------------------ \n" + carService.listCar());

      context.close();
   }
}
