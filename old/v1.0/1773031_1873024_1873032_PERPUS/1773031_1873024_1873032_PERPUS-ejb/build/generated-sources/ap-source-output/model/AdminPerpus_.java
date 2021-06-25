package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Peminjaman;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(AdminPerpus.class)
public class AdminPerpus_ { 

    public static volatile SingularAttribute<AdminPerpus, String> password;
    public static volatile CollectionAttribute<AdminPerpus, Peminjaman> peminjamanCollection;
    public static volatile SingularAttribute<AdminPerpus, String> nama;
    public static volatile SingularAttribute<AdminPerpus, Integer> id;
    public static volatile SingularAttribute<AdminPerpus, Date> tanggalLahir;
    public static volatile SingularAttribute<AdminPerpus, String> username;

}