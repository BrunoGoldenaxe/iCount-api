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
public class RetornoContasDTO {

    private Long oidContas;
    private String cliente;
    private BigDecimal valPagar;
    private String centroCusto;
    private String dtVencimento;
    private String observacao;
}
