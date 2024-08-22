package br.com.itech.icount.Data.CentroDeCusto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CentroDeCustoDTO {

    private Long idCentroCusto;
    @NotNull
    private String nmCentroDeCusto;
    @NotNull
    private String nmResponsavel;

    private Boolean ativo;
}
