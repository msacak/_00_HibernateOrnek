package org.example.Ornek02;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Ornek02.entity.Adres;
import org.example.Ornek02.entity.Departman;
import org.example.Ornek02.entity.Personel;
import org.example.Ornek02.repository.Sorgular;
import org.example.Ornek02.utility.DemoData;
import org.example.Ornek02.utility.EUnvan;
import org.example.Ornek02.utility.HibernateConnection;

import java.util.List;

public class Runner {

    // 3 adet personel, 1 adet müdür personel, 2 departman ve her personel için en az 2 adres tanımlayın.
    public static void main(String[] args) {
        HibernateConnection.em.getTransaction().begin();
//        DemoData.createData();
//
        Sorgular sorgular = new Sorgular();
//        sorgular.findPersonelsByDepartmanName("bilgisayar").forEach(System.out::println);
//        sorgular.findPersonelsByCityName("ankara").forEach(System.out::println);
//        sorgular.findPersonelsByManagersId("Mehmet").forEach(System.out::println);
//        sorgular.findPersonelsByUnvan(EUnvan.MUDUR).forEach(System.out::println);
//        sorgular.findNumberOfPersonelInDepartment().forEach(p->{
//            Departman departman = (Departman) p[0];
//            Long count = (Long) p[1];
//            System.out.println(departman.getAd()+" = "+count);
//        });
//            sorgular.findNumberOfPersonelByUnvans().forEach(p->{
//                System.out.println(p[0]+" - "+p[1]+" kişi");
//            });
            sorgular.findAllViewPersonel().forEach(System.out::println);





        HibernateConnection.em.getTransaction().commit();
        HibernateConnection.em.close();
        HibernateConnection.emf.close();
    }
}
