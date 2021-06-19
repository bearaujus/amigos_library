package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Kategori;
import model.PeminjamanDetails;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(Buku.class)
public class Buku_ { 

    public static volatile SingularAttribute<Buku, Integer> jumlahHalaman;
    public static volatile SingularAttribute<Buku, Kategori> idKategori;
    public static volatile CollectionAttribute<Buku, PeminjamanDetails> peminjamanDetailsCollection;
    public static volatile SingularAttribute<Buku, Integer> harga;
    public static volatile SingularAttribute<Buku, Date> tahunTerbit;
    public static volatile SingularAttribute<Buku, String> gambarLink;
    public static volatile SingularAttribute<Buku, Integer> rating;
    public static volatile SingularAttribute<Buku, Integer> stok;
    public static volatile SingularAttribute<Buku, Integer> id;
    public static volatile SingularAttribute<Buku, String> gambarSrc;
    public static volatile SingularAttribute<Buku, String> judul;
    public static volatile SingularAttribute<Buku, String> pengarang;

}