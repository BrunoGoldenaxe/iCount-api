package br.com.itech.icount.Data.Conta;

import br.com.itech.icount.Data.Enum.EnumTipoConta;
import br.com.itech.icount.Data.Generic.GenericEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.util.Date;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Entity
@Table(name = "CONTAS", schema = "icount")
@Schema(description = "TABELA GLOBAL PARA CONTAS PAGAR/RECEBER")
@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Conta extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA")
    private Long idConta;

    @Column(name = "VAL_PAGAR_RECEBER")
    private BigDecimal valPagarReceber;

    @Column(name = "VAL_DESCONTO")
    private BigDecimal valDesconto;

    @Column(name = "VAL_JUROS_MULTA")
    private BigDecimal valJurosMulta;

    @Column(name = "VAL_PAGO_RECEBIDO")
    private BigDecimal valPagoRecebido;

    @Column(name = "CLIENTE", length = 50)
    private String cliente;

    @Column(name = "CENTRO_CUSTO", length = 50)
    private String centroCusto;

    @Column(name = "DT_EMISSAO")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtEmissao;

    @Column(name = "DT_VENCIMENTO")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtVencimento;

    @Column(name = "DT_PAGAMENTO_RECEBIMENTO")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dtPagamentoRecebimento;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "IS_PAGA_RECEBIDA")
    private boolean isPagaRecebida = false;

    @Column(name = "TIPO_CONTA")
    @Enumerated(value = EnumType.STRING)
    private EnumTipoConta tipoConta;

    @Override
    public String toString(){
        return "Contas{" +
                "idConta=" + idConta +
                ", cliente='" + cliente + '\'' +
                ", centroCusto='" + centroCusto + '\'' +
                ", observacao='" + observacao + '\'' +
                ", tipoConta='" + tipoConta + '\'' +
                ", isPagaRecebida='" + isPagaRecebida + '\'' +
                '}';
    }
}


