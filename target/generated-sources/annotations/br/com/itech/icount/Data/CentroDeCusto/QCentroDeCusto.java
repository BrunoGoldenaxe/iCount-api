package br.com.itech.icount.Data.CentroDeCusto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCentroDeCusto is a Querydsl query type for CentroDeCusto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCentroDeCusto extends EntityPathBase<CentroDeCusto> {

    private static final long serialVersionUID = 383177672L;

    public static final QCentroDeCusto centroDeCusto = new QCentroDeCusto("centroDeCusto");

    public final br.com.itech.icount.Data.Generic.QGenericEntity _super = new br.com.itech.icount.Data.Generic.QGenericEntity(this);

    public final BooleanPath ativo = createBoolean("ativo");

    //inherited
    public final StringPath dsUsuAlter = _super.dsUsuAlter;

    //inherited
    public final DatePath<java.util.Date> dtUltAlter = _super.dtUltAlter;

    public final NumberPath<Long> idCentroCusto = createNumber("idCentroCusto", Long.class);

    public final StringPath nmCentroDeCusto = createString("nmCentroDeCusto");

    public final StringPath nmResponsavel = createString("nmResponsavel");

    //inherited
    public final NumberPath<Long> vsVersao = _super.vsVersao;

    public QCentroDeCusto(String variable) {
        super(CentroDeCusto.class, forVariable(variable));
    }

    public QCentroDeCusto(Path<? extends CentroDeCusto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCentroDeCusto(PathMetadata metadata) {
        super(CentroDeCusto.class, metadata);
    }

}

