package org.example.Ornek02.Views;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Ornek02.utility.EUnvan;

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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwPersonel {
    private Long id;
    private String fullName;
    private EUnvan unvan;
    private String departmantName;
    private String managerName;

    @Override
    public String toString() {
        System.out.println("-------------------------------------");
        System.out.println("ID            : "+this.id);
        System.out.println("FullName      : "+this.fullName);
        System.out.println("Unvan         : "+this.unvan);
        System.out.println("Departman     : "+this.departmantName);
        System.out.println("Müdür         : "+this.managerName);
        System.out.print("-------------------------------------");
        return "";
    }
}
