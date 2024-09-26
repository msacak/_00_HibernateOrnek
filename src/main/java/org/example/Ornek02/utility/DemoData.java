package org.example.Ornek02.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.Ornek02.entity.Adres;
import org.example.Ornek02.entity.Departman;
import org.example.Ornek02.entity.Personel;

import java.util.List;

public class DemoData {
    public static void createData(){


        Departman departman1 = Departman.builder().ad("İletişim Departmanı").aciklama("İletişimi sağlar").yetkiliId(1L).build();
        Departman departman2 = Departman.builder().ad("Bilgisayar Departmanı").aciklama("Teknolojik gelişmelerden sorumludur.").yetkiliId(3L).build();


        Personel personel1 = Personel.builder().ad("Mehmet").soyad("Kaya").eUnvan(EUnvan.MUDUR).maas(90_000.0).build();
        Personel personel2 = Personel.builder().ad("Ahmet").soyad("Eker").eUnvan(EUnvan.OFIS_ELEMANI).maas(65_000.0).build();
        Personel personel3 = Personel.builder().ad("Hande").soyad("Akar").eUnvan(EUnvan.MUDUR).maas(90_000.0).build();
        Personel personel4 = Personel.builder().ad("Gizem").soyad("Sayan").eUnvan(EUnvan.OFIS_ELEMANI).maas(65_000.0).build();
        personel1.setDepartman(departman1);
        personel2.setDepartman(departman1);
        personel3.setDepartman(departman2);
        personel4.setDepartman(departman2);

        Adres adres1 = Adres.builder().personel(personel1).ad("Ev1").il("Kocaeli").ilce("İzmit").acikAdres("Kanarya mah. No:42 D-6").telefon("0536-206-5151").build();
        Adres adres2 = Adres.builder().personel(personel1).ad("Ev2").il("Kocaeli").ilce("Başiskele").acikAdres("Akcakoca mah. No:176 D-12").telefon("0536-206-5151").build();
        Adres adres3 = Adres.builder().personel(personel2).ad("Ev1").il("İstanbul").ilce("Kadıköy").acikAdres("Barbaros Mah. Acik Sk. No:42 D-4").telefon("0534-355-4253").build();
        Adres adres4 = Adres.builder().personel(personel2).ad("Ev2").il("İstanbul").ilce("Üsküdar").acikAdres("Gazi Mah. Karaca Sk. No:3 D-1").telefon("0534-355-4253").build();
        Adres adres5 = Adres.builder().personel(personel3).ad("Ev1").il("Ankara").ilce("Çankaya").acikAdres("Barbaros Mah. Acik Sk. No:42 D-4").telefon("0538-766-6261").build();
        Adres adres6 = Adres.builder().personel(personel3).ad("Ev2").il("Ankara").ilce("Etimesgut").acikAdres("Paşa Mah. Bey Sk. No:24 D-12").telefon("0538-766-6261").build();
        Adres adres7 = Adres.builder().personel(personel4).ad("Ev1").il("İstanbul").ilce("Üsküdar").acikAdres("Kurucesme Mah. Fatih Sk. No:2 D-3").telefon("0532-455-3677").build();
        Adres adres8 = Adres.builder().personel(personel4).ad("Ev2").il("İstanbul").ilce("Üsküdar").acikAdres("İskele Mah. Manav Sk. No:2 D-4").telefon("0532-455-3677").build();

        personel1.setAdresListesi(List.of(adres1, adres2));
        personel2.setAdresListesi(List.of(adres3, adres4));
        personel3.setAdresListesi(List.of(adres5, adres6));
        personel4.setAdresListesi(List.of(adres7, adres8));





        HibernateConnection.em.persist(departman1);
        HibernateConnection.em.persist(departman2);
        HibernateConnection.em.persist(personel1);
        HibernateConnection.em.persist(personel2);
        HibernateConnection.em.persist(personel3);
        HibernateConnection.em.persist(personel4);
        HibernateConnection.em.persist(adres1);
        HibernateConnection.em.persist(adres2);
        HibernateConnection.em.persist(adres3);
        HibernateConnection.em.persist(adres4);
        HibernateConnection.em.persist(adres5);
        HibernateConnection.em.persist(adres6);
        HibernateConnection.em.persist(adres7);
        HibernateConnection.em.persist(adres8);







    }
}
