package br.com.itech.icount_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "BANCO", schema = "icount.api")
@Schema(description = "TABELA DE BANCOS")
@Data()
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Banco extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OID_BANCO")
    private Long oidBanco;

    @Column(name = "SALDO_INICIAL")
    private BigDecimal saldoInicial;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "NM_BANCO")
    private String nmBanco;

    @Column(name = "NR_AGENCIA")
    private String nrAgencia;

    @Column(name = "NR_CONTA")
    private String nrConta;




}
