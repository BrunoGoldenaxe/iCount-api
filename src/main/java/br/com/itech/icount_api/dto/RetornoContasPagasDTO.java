package br.com.itech.icount_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetornoContasPagasDTO {

    private Long oidContasPagar;
    private BigDecimal valPagar;
    private BigDecimal valJurosMultas;
    private BigDecimal valDesconto;
    private BigDecimal valPago;
    private String cliente;
    private String dtEmissao;
    private String dtVencimento;
    private String dtPagamento;
    private String observacao;
    private String centroCusto;
}
