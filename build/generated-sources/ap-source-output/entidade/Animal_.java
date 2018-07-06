package entidade;

import entidade.Responsavel;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-04T16:33:53")
@StaticMetamodel(Animal.class)
public class Animal_ { 

    public static volatile SingularAttribute<Animal, String> nomeDoAnimal;
    public static volatile SingularAttribute<Animal, String> idade;
    public static volatile SingularAttribute<Animal, String> vacinas;
    public static volatile SingularAttribute<Animal, String> porte;
    public static volatile SingularAttribute<Animal, Long> id;
    public static volatile SingularAttribute<Animal, Responsavel> responsavel;

}