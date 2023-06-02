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

      user1.setCar(new Car("BMW", 7));
      user2.setCar(new Car("Mazda", 6));
      user3.setCar(new Car("AUDI", 5));
      user4.setCar(new Car("AUDI", 5));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


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
