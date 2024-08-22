package br.com.itech.icount.Data.Conta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Data()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Long idConta;
    @NotNull
    private String cliente;
    @NotNull
    private BigDecimal valPagarReceber;
    @NotNull
    private String centroCusto;
    private BigDecimal valJurosMulta;
    private BigDecimal valPagoRecebido;
    @NotNull
    private Date dtEmissao;
    @NotNull
    private Date dtVencimento;
    private Date dtPagamentoRecebimento;
    @NotNull
    private String observacao;
    private Boolean isPagaRecebida;
    @NotNull
    private String tipoConta;

}
