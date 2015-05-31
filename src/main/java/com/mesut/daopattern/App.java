package com.mesut.daopattern;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
        OffersDAO offersDAO=context.getBean(OffersDAO.class);
        List<Offer> offers=offersDAO.getOffers();
        for (Offer offer : offers) {
            System.out.println("Sonuc: "+offer);
        }
    }
}
