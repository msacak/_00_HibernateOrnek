package org.example.Ornek02.entity;

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
@Table(name = "tbldepartman")
public class Departman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ad;
    private String aciklama;
    private Long yetkiliId;
    @OneToMany(mappedBy = "departman")
    private List<Personel> personelListesi;

    @Override
    public String toString() {
        return "Departman{" +
                "yetkiliId=" + yetkiliId +
                ", aciklama='" + aciklama + '\'' +
                ", ad='" + ad + '\'' +
                ", id=" + id +
                '}';
    }
}
