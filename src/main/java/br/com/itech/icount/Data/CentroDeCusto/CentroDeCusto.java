package br.com.itech.icount.Data.CentroDeCusto;

import br.com.itech.icount.Data.Generic.GenericEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CENTRO_DE_CUSTO", schema = "icount")
@Schema(description = "TABELA DE CENTRO DE CUSTO")
@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CentroDeCusto extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CENTRO_CUSTO")
    private Long idCentroCusto;
    @Column(name = "NM_CENTRO_DE_CUSTO", nullable = false, length = 50)
    private String nmCentroDeCusto;
    @Column(name = "NM_RESPONSAVEL", nullable = false, length = 50)
    private String nmResponsavel;
    @Column(name = "ATIVO")
    private Boolean ativo = true;

    @Override
    public String toString(){
        return "CENTRO_DE_CUSTO{" +
                "idCentroCusto=" + idCentroCusto +
                ", nmCentroDeCusto='" + nmCentroDeCusto + '\'' +
                ", nmResponsavel='" + nmResponsavel + '\'' +
                ", ativo='" + ativo + '\'' +
                '}';
    }
}
