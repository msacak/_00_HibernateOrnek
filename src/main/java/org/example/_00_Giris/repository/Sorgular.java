package org.example._00_Giris.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.example._00_Giris.entity.Urun;

import java.util.List;

public class Sorgular {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    public Sorgular(){
        emf = Persistence.createEntityManagerFactory("URUN");
        em = emf.createEntityManager();
        criteriaBuilder = em.getCriteriaBuilder();
    }
    public void findAll(){
        /**
         * sql = ( SELECT * FROM tblurun)
         */
        CriteriaQuery<Urun> cq = criteriaBuilder.createQuery(Urun.class); //return Type
        Root<Urun> root = cq.from(Urun.class); //from
        cq.select(root); // SELECT *
        List<Urun> urunList = em.createQuery(cq).getResultList(); //result list
        urunList.forEach(urun->{
            System.out.println(urun.getId()+" - "+urun.getAd());
        });

    }
    public void selectOneColumn(){
        // select ad from tblurun
        CriteriaQuery<String> cq = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = cq.from(Urun.class);
        cq.select(root.get("ad"));
        List<String> urunAdListesi = em.createQuery(cq).getResultList();
        urunAdListesi.forEach(System.out::println);

    }

    public void selectManyColumn(){
        CriteriaQuery<Object[]> cq = criteriaBuilder.createQuery(Object[].class);
        Root<Urun> root = cq.from(Urun.class);
        Path<Long> idPath = root.get("id");
        Path<String> adPath = root.get("ad");
        cq.select(criteriaBuilder.array(idPath,adPath));
//        cq.multiselect(idPath,adPath);
        List<Object[]> urunIdAdListesi = em.createQuery(cq).getResultList();
        urunIdAdListesi.forEach(row->{
            System.out.println(row[0]+" - "+row[1]);
        });
    }
}
