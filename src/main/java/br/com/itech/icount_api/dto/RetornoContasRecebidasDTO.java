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
public class RetornoContasRecebidasDTO {

    private Long oidContasReceber;
    private BigDecimal valReceber;
    private BigDecimal valJurosMultas;
    private BigDecimal valDesconto;
    private BigDecimal valRecebido;
    private String cliente;
    private String dtEmissao;
    private String dtVencimento;
    private String dtRecebimento;
    private String observacao;
    private String centroCusto;
}
