package br.com.itech.icount_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagarDTO {

    private BigDecimal valPagar;
    private String cliente;
    private String centroCusto;
    private LocalDate dtEmissao;
    private LocalDate dtVencimento;
    private String observacao;
}
