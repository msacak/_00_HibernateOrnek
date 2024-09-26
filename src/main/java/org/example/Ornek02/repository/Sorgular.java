package org.example.Ornek02.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.*;
import org.example.Ornek02.Views.VwPersonel;
import org.example.Ornek02.entity.Adres;
import org.example.Ornek02.entity.Departman;
import org.example.Ornek02.entity.Personel;
import org.example.Ornek02.utility.EUnvan;
import org.example.Ornek02.utility.HibernateConnection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sorgular {
    //1-) Adı verilen bir departmanda çalışan personeller listesi -(ad Like + ignoreCase)
    public List<Personel> findPersonelsByDepartmanName(String departmanName){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Departman> cq = cb.createQuery(Departman.class);
        Root<Departman> root = cq.from(Departman.class);
        cq.select(root).where(cb.like(cb.lower(root.get("ad")), "%"+departmanName.toLowerCase()+"%"));
        List<Departman> departmanList = HibernateConnection.em.createQuery(cq).getResultList();

        List<Personel> personelList = new ArrayList<>();
        for(Departman departman : departmanList){
            personelList.addAll(departman.getPersonelListesi());
        }

        return personelList;
    }
    // 2- Belli bir ilde ikamet eden personellerin listesi
    public Set<Personel> findPersonelsByCityName(String cityName){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Adres> cq = cb.createQuery(Adres.class);
        Root<Adres> root = cq.from(Adres.class);
        cq.select(root).where(cb.equal(cb.lower(root.get("il")), cityName.toLowerCase()));
        List<Adres> adresList = HibernateConnection.em.createQuery(cq).getResultList();
        Set<Personel> personelList = new HashSet<>();
        for(Adres adres : adresList){
            personelList.add(adres.getPersonel());
        }
        return personelList;
    }

    // * 3- Adını verdiğim Müdür ile çalışan personellerin listesi
    public List<Personel> findPersonelsByManagersId(String managerName){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Personel> cq = cb.createQuery(Personel.class);
        Root<Personel> root = cq.from(Personel.class);
        cq.select(root)
                .where(cb.and(cb.equal(cb.lower(root.get("ad")), managerName.toLowerCase())
                        ,cb.equal(root.get("eUnvan"), EUnvan.MUDUR)));
        Personel mudur = HibernateConnection.em.createQuery(cq).getSingleResult();
        Departman departman = mudur.getDepartman();


        CriteriaBuilder cb2 = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Personel> pcq = cb2.createQuery(Personel.class);
        Root<Personel> pc = pcq.from(Personel.class);
        pcq.select(pc).where(cb2.equal(pc.get("departman"), departman));
        return HibernateConnection.em.createQuery(pcq).getResultList();


    }

    //4- Belli bir ünvana sahip personellerin listesi.
    public List<Personel> findPersonelsByUnvan(EUnvan unvan){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Personel> cq = cb.createQuery(Personel.class);
        Root<Personel> root = cq.from(Personel.class);
        cq.select(root).where(cb.equal(root.get("eUnvan"), unvan));
        return HibernateConnection.em.createQuery(cq).getResultList();
    }
    //5- departmanlarda çalışan personellerin sayıları -> bilgiişlem: 5, insankaynakları: 3 gibi
    //SELECT d.name,COUNT(*)
    //FROM personel p JOIN department d ON p.department_id=d.id
    //GROUP BY d.name;
    public List<Object[]> findNumberOfPersonelInDepartment(){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Personel> root = cq.from(Personel.class);

        cq.multiselect(root.get("departman"),cb.count(root));
        cq.groupBy(root.get("departman"));
        return HibernateConnection.em.createQuery(cq).getResultList();
    }

    //6- ünvanlarına göre personel sayıları
    public List<Object[]> findNumberOfPersonelByUnvans(){
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Personel> root = cq.from(Personel.class);

        cq.multiselect(root.get("eUnvan"),cb.count(root));
        cq.groupBy(root.get("eUnvan"));
        return HibernateConnection.em.createQuery(cq).getResultList();
    }
    //7- personel Listesi;
    /**
     *  *          * 7- personel Listesi;
     *  *          * id: 44
     *  *          * ad: Muhammet
     *  *          * ünvan: Eğitmen
     *  *          * departmanı: Yazılım
     *  *          * müdür: Ayhan
     *  *          * -------------
     *  *          * şeklinde bir çıktı verin.
     *  *          *
     *  *          * ÖRN:
     *  *          * public List<VwPersonel> findAllViewPersonel(){
     *  *          *     return null;
     *  *          * }
     *  *          *
     *  *          */

    public List<VwPersonel> findAllViewPersonel(){
        List<VwPersonel> vwPersonelList = new ArrayList<>();
        CriteriaBuilder cb = HibernateConnection.em.getCriteriaBuilder();
        CriteriaQuery<Personel> cq = cb.createQuery(Personel.class);
        Root<Personel> root = cq.from(Personel.class);
        cq.select(root);
        List<Personel> resultList = HibernateConnection.em.createQuery(cq).getResultList();
        List<Personel> mudurList = resultList.stream().filter(p -> p.getEUnvan().equals(EUnvan.MUDUR)).toList();
        for(Personel personel : resultList){
            VwPersonel vwPersonel = new VwPersonel();
            vwPersonel.setId(personel.getId());
            vwPersonel.setDepartmantName(personel.getDepartman().getAd());
            vwPersonel.setUnvan(personel.getEUnvan());
            vwPersonel.setFullName(personel.getAd()+" "+personel.getSoyad());
            for(Personel mudur : mudurList){
                if(mudur.getId().equals(personel.getDepartman().getYetkiliId())){
                    vwPersonel.setManagerName(mudur.getAd()+" "+mudur.getSoyad());
                    break;
                }
            }
            if(vwPersonel.getFullName().equalsIgnoreCase(vwPersonel.getManagerName())){
                vwPersonel.setManagerName("N/A");
            }
            vwPersonelList.add(vwPersonel);
        }
        return vwPersonelList;
    }
}

