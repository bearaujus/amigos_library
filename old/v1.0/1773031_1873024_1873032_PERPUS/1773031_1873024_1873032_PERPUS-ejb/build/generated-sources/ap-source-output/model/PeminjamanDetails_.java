package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Buku;
import model.Denda;
import model.Peminjaman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(PeminjamanDetails.class)
public class PeminjamanDetails_ { 

    public static volatile SingularAttribute<PeminjamanDetails, Date> tanggalPengembalian;
    public static volatile SingularAttribute<PeminjamanDetails, Buku> idBuku;
    public static volatile SingularAttribute<PeminjamanDetails, Date> tanggalPengambilan;
    public static volatile SingularAttribute<PeminjamanDetails, Boolean> statusKonfirmasi;
    public static volatile SingularAttribute<PeminjamanDetails, Integer> id;
    public static volatile SingularAttribute<PeminjamanDetails, Peminjaman> idPeminjaman;
    public static volatile SingularAttribute<PeminjamanDetails, Denda> idDenda;

}