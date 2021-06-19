package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Peminjaman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(Anggota.class)
public class Anggota_ { 

    public static volatile SingularAttribute<Anggota, String> password;
    public static volatile CollectionAttribute<Anggota, Peminjaman> peminjamanCollection;
    public static volatile SingularAttribute<Anggota, String> nama;
    public static volatile SingularAttribute<Anggota, String> noHp;
    public static volatile SingularAttribute<Anggota, Boolean> isBanned;
    public static volatile SingularAttribute<Anggota, Integer> id;
    public static volatile SingularAttribute<Anggota, Date> tanggalLahir;
    public static volatile SingularAttribute<Anggota, Date> tanggalBergabung;
    public static volatile SingularAttribute<Anggota, String> username;

}