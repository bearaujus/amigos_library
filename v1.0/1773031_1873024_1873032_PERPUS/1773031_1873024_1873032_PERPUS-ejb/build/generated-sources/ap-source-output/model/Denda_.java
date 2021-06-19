package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.PeminjamanDetails;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-06-20T01:40:01")
@StaticMetamodel(Denda.class)
public class Denda_ { 

    public static volatile SingularAttribute<Denda, String> keterangan;
    public static volatile CollectionAttribute<Denda, PeminjamanDetails> peminjamanDetailsCollection;
    public static volatile SingularAttribute<Denda, Integer> totalDenda;
    public static volatile SingularAttribute<Denda, Integer> id;

}