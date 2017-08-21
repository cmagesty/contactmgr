package com.contactmgr;

import com.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Chris on 8/10/17.
 */
public class Application {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public static void main(String[] args) {

        Contact contact = new Contact.ContactBuilder("Chris", "Magesty")
                .withEmail("cmagesty@gmail.com")
                .withPhone(5516970672L)
                .build();
        save(contact);

        int id = save(contact);

        //before update
        for(Contact c : fetchAllContacts()) {
            System.out.println(c);
        }

        Contact c = findContactById(id);

        c.setFirstName("Bea");

        update(c);

        fetchAllContacts().stream().forEach(System.out::println);

    }

    private static Contact findContactById(int id) {
        Session session = sessionFactory.openSession();

        Contact contact = session.get(Contact.class,id);

        session.close();

        return contact;
    }

    private static void update(Contact contact) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.update(contact);

        session.getTransaction().commit();

        session.close();

    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Contact.class);

        List<Contact> contacts = criteria.list();

        session.close();

        return contacts;
    }

    private static int save(Contact contact) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        int id = (int)session.save(contact);

        session.getTransaction().commit();

        session.close();

        return id;

    }

    private  static int delete(Contact contact) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(contact);

        session.getTransaction().commit();

        session.close();
    }
}
