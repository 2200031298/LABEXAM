package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();

        // Insert Records
        insertRecords(factory);

        // Fetch All Records
        fetchAllRecords(factory);

        factory.close();
    }

    private static void insertRecords(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create client 1
        Client client1 = new Client();
        client1.setName("Aarthi");
        client1.setGender("Female");
        client1.setAge(20);
        client1.setLocation("Guntur");
        client1.setEmail("aarthi@gmail.com");
        client1.setMobile("1234567890");

        // Create client 2
        Client client2 = new Client();
        client2.setName("Madhavi");
        client2.setGender("Fmale");
        client2.setAge(25);
        client2.setLocation("Chirala");
        client2.setEmail("madhavi@gmail.com");
        client2.setMobile("9876543210");
        
        

        // Save the clients
        session.save(client1);
        session.save(client2);

        // Commit the transaction
        tx.commit();
        session.close();
        System.out.println("Records inserted successfully.");
    }

    private static void fetchAllRecords(SessionFactory factory) {
        Session session = factory.openSession();

        // Fetch all clients
        List<Client> clients = session.createQuery("from Client", Client.class).list();

        for (Client client : clients) {
            System.out.println(client.getId() + " | " + client.getName() + " | " + client.getGender() + " | " +
                    client.getAge() + " | " + client.getLocation() + " | " +
                    client.getEmail() + " | " + client.getMobile());
        }

        session.close();
    }
}
