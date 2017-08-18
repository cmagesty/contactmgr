package com.contactmgr;

import com.contactmgr.model.Contact;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    }
}
