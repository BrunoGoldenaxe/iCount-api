package br.com.itech.icount_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetornoBancoDTO {

    private String nmBanco;
    private String nrAgencia;
    private String nrConta;
    private BigDecimal saldo;
    private Date dtUltAlter;
}
