package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import util.DBConnect;

import java.sql.SQLException;
import java.util.List;

public class UserDAOHibernate implements UserDao {
    private SessionFactory sessionFactory;

    public UserDAOHibernate() {
        Configuration configuration = DBConnect.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void create(User user) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(long id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List getAll() throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User").list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET name =:name, surname =:surname, age =:age WHERE id =:id");
            query.setParameter("id", user.getId());
            query.setParameter("name", user.getName());
            query.setParameter("surname", user.getSurname());
            query.setParameter("age", user.getAge());
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
