package web.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        try {
            Query query = (Query) entityManager.createQuery("select u from User u where u.name = :name ");
            query.setParameter("name", name);
            return (User) query.getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public void removeUserById(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

}
