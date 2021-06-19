package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.AdminPerpus;
import model.Anggota;
import model.PeminjamanDetails;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(Peminjaman.class)
public class Peminjaman_ { 

    public static volatile SingularAttribute<Peminjaman, Date> tanggalPengajuan;
    public static volatile CollectionAttribute<Peminjaman, PeminjamanDetails> peminjamanDetailsCollection;
    public static volatile SingularAttribute<Peminjaman, Integer> totalBukuYangDiajukan;
    public static volatile SingularAttribute<Peminjaman, Anggota> idAnggota;
    public static volatile SingularAttribute<Peminjaman, Integer> id;
    public static volatile SingularAttribute<Peminjaman, AdminPerpus> idAdminPerpus;

}