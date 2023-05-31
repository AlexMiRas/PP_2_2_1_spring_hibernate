package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> findUserByCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("from User u where u.car.model= : model and u.car.series = : series",
                      User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      List<User> users = query.getResultList();
      if (users.isEmpty()) {
         System.out.println("--------------- \n" + "Users with such cars is not exist");
      }
      return users;
   }
}
