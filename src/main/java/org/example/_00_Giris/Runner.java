package org.example._00_Giris;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example._00_Giris.entity.Urun;
import org.example._00_Giris.repository.Sorgular;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
//        new UrunImpl().createUrun();
        new Sorgular().selectManyColumn();



    }

    private void test(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("URUN");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Urun> cq = cb.createQuery(Urun.class);
        Root<Urun> root = cq.from(Urun.class);
        cq.select(root);
        List<Urun> urunListesi = em.createQuery(cq).getResultList();
        urunListesi.forEach(u->{
            System.out.println("id....: "+u.getId());
            System.out.println("ad....: "+u.getAd());
            System.out.println("fiyat....: "+u.getFiyat());
            System.out.println("resimler.....: "+u.getResimler());
        });

        em.close();
        emf.close();


    }

}