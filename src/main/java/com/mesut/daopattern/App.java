package com.mesut.daopattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
//Yorum denemeeee
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        OffersDAO offersDAO = context.getBean(OffersDAO.class);
//        List<Offer> offers = offersDAO.findOffers();
//        Offer offerById = offersDAO.findById(2);
//        for (Offer offer : offers) {
//            System.out.println("List: " + offer);
//        }
//        System.out.println("---------------------------------");
//        System.out.println("Find By Id: " + offerById);
//        System.out.println("---------------------------------");
//        System.out.println("Deleye By Id: "+offersDAO.deleteById(5));
//        System.out.println("---------------------------------");
//        System.out.println("Create offer: "+offersDAO.addOffer(new Offer("new name", "newuser@user.com", "new text lorem ipsum ")));
//        System.out.println("---------------------------------");
//        System.out.println("Update Offer: "+offersDAO.updateOffer(new Offer(1, "upd name", "upd@mail.com", "upd text it should be lorem ipsum again I think")));
        List<Offer> offers=new ArrayList<Offer>();
        offers.add(new Offer("yeni isim 1", "yeni1@mail.com", "yeni yazı bir lorem ipsum 1"));
        offers.add(new Offer("yeni isim 2", "yeni2@mail.com", "yeni yazı 22222"));
        offers.add(new Offer("yeni isim 3", "yeni3@mail.com", "yeni yazı 333333333"));
        System.out.println("BatchUpdate create exanple: "+Arrays.toString(offersDAO.createOffers(offers)));
        context.close();
    }
}
