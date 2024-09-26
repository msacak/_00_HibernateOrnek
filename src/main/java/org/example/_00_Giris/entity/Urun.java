package org.example._00_Giris.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    Double fiyat;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER) //CASCADE.PERSIST sayesinde ürünü kaydetmeden önce resimleri manuel kaydetmemize(persist) gerek yok.
    @JoinTable(
            name = "urun resim tablosu",
            joinColumns = @JoinColumn(name = "urunun idsi"),
            inverseJoinColumns = @JoinColumn(name = "resmin idsi")
    )
    List<Resim> resimler;

    @OneToMany(mappedBy = "urun") //Urun sınıfı -> Urun urun mapliyorsun.
//    @JoinColumn(name="urun_id",referencedColumnName = "ozellik_id") //bu olmadan mappedBy ile yaptık. Bidirectional
    List<Ozellik> ozellikler;


}
