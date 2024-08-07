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
public class RetornoContasPagarDTO {

    private Long oidContasPagar;
    private BigDecimal valPagar;
    private String cliente;
    private String dtEmissao;
    private String dtVencimento;
}
