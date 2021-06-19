package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Buku;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(Kategori.class)
public class Kategori_ { 

    public static volatile SingularAttribute<Kategori, String> nama;
    public static volatile SingularAttribute<Kategori, Integer> id;
    public static volatile CollectionAttribute<Kategori, Buku> bukuCollection;

}