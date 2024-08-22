package br.com.itech.icount.Data.Generic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenericEntity is a Querydsl query type for GenericEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QGenericEntity extends EntityPathBase<GenericEntity> {

    private static final long serialVersionUID = -574206387L;

    public static final QGenericEntity genericEntity = new QGenericEntity("genericEntity");

    public final StringPath dsUsuAlter = createString("dsUsuAlter");

    public final DatePath<java.util.Date> dtUltAlter = createDate("dtUltAlter", java.util.Date.class);

    public final NumberPath<Long> vsVersao = createNumber("vsVersao", Long.class);

    public QGenericEntity(String variable) {
        super(GenericEntity.class, forVariable(variable));
    }

    public QGenericEntity(Path<? extends GenericEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenericEntity(PathMetadata metadata) {
        super(GenericEntity.class, metadata);
    }

}

