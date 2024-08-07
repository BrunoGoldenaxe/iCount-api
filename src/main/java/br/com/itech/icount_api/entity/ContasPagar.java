package br.com.itech.icount_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CONTAS_PAGAR", schema = "icount.api")
@Schema(description = "TABELA DE CONTAS À PAGAR")
@Data()
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagar extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID_CONTAS_PAGAR")
    private Long oidContasPagar;

    @Column(name = "VAL_PAGAR")
    private BigDecimal valPagar;

    @Column(name = "VAL_DESCONTO")
    private BigDecimal valDesconto;

    @Column(name = "VAL_JUROS_MULTA")
    private BigDecimal valJurosMulta;

    @Column(name = "VAL_PAGO")
    private BigDecimal valPago;

    @Column(name = "CLIENTE")
    private String cliente;

    @Column(name = "CENTRO_CUSTO")
    private String centroCusto;

    @Column(name = "DT_EMISSAO")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dtEmissao;

    @Column(name = "DT_VENCIMENTO")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dtVencimento;

    @Column(name = "DT_PAGAMENTO")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dtPagamento;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @Column(name = "IS_PAGA")
    private boolean isPaga = false;

}
