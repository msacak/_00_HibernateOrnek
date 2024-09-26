package org.example.Ornek02.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Ornek02.utility.EUnvan;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblpersonel")
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ad;
    private String soyad;

    @Enumerated(EnumType.STRING)
    private EUnvan eUnvan;
    private Double maas;
    @ManyToOne
    private Departman departman;

    @OneToMany(mappedBy = "personel")
    private List<Adres> adresListesi;

    @Override
    public String toString() {
        return "Personel{" +
                "departman=" + departman +
                ", maas=" + maas +
                ", eUnvan=" + eUnvan +
                ", soyad='" + soyad + '\'' +
                ", ad='" + ad + '\'' +
                ", id=" + id +
                '}';
    }
}
