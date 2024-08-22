package br.com.itech.icount.Data.Conta;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConta is a Querydsl query type for Conta
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConta extends EntityPathBase<Conta> {

    private static final long serialVersionUID = 926517818L;

    public static final QConta conta = new QConta("conta");

    public final br.com.itech.icount.Data.Generic.QGenericEntity _super = new br.com.itech.icount.Data.Generic.QGenericEntity(this);

    public final StringPath centroCusto = createString("centroCusto");

    public final StringPath cliente = createString("cliente");

    //inherited
    public final StringPath dsUsuAlter = _super.dsUsuAlter;

    public final DatePath<java.util.Date> dtEmissao = createDate("dtEmissao", java.util.Date.class);

    public final DatePath<java.util.Date> dtPagamentoRecebimento = createDate("dtPagamentoRecebimento", java.util.Date.class);

    //inherited
    public final DatePath<java.util.Date> dtUltAlter = _super.dtUltAlter;

    public final DatePath<java.util.Date> dtVencimento = createDate("dtVencimento", java.util.Date.class);

    public final NumberPath<Long> idConta = createNumber("idConta", Long.class);

    public final BooleanPath isPagaRecebida = createBoolean("isPagaRecebida");

    public final StringPath observacao = createString("observacao");

    public final EnumPath<br.com.itech.icount.Data.Enum.EnumTipoConta> tipoConta = createEnum("tipoConta", br.com.itech.icount.Data.Enum.EnumTipoConta.class);

    public final NumberPath<java.math.BigDecimal> valDesconto = createNumber("valDesconto", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valJurosMulta = createNumber("valJurosMulta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valPagarReceber = createNumber("valPagarReceber", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valPagoRecebido = createNumber("valPagoRecebido", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> vsVersao = _super.vsVersao;

    public QConta(String variable) {
        super(Conta.class, forVariable(variable));
    }

    public QConta(Path<? extends Conta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConta(PathMetadata metadata) {
        super(Conta.class, metadata);
    }

}

