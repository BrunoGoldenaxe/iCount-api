package br.com.itech.icount.Data.CentroDeCusto;

import br.com.itech.icount.Infra.MessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/centro-de-custo")
@Tag(name = "Centro de Custo", description = "API de centro de custo")
public class CentroDeCustoController {

    @Autowired
    private CentroDeCustoService service;

    @GetMapping()
    @Operation(summary = "Listar os centros de custo")
    public ResponseEntity<List<CentroDeCusto>> getCentrosDeCustoWithFilter(
            @RequestParam(required = false) String nmCentroDeCusto,
            @RequestParam(required = false) String nmResponsavel,
            @RequestParam(required = false, defaultValue = "true") Boolean ativo){
        return ResponseEntity.ok().body(service.getCentrosDeCustoWithFilter(
                nmCentroDeCusto, nmResponsavel, ativo));
    }

    @GetMapping(value = "/{idCentroCusto}")
    @Operation(summary = "Buscar um centro de custo através do (idCentroCusto)")
    public ResponseEntity<CentroDeCusto> getCentroDeCustoById(@RequestParam Long idCentroCusto){
        return ResponseEntity.ok().body(service.getCentroDeCustoById(idCentroCusto));
    }

    @PostMapping()
    @Operation(summary = "Gravar novo centro de custo")
    public ResponseEntity<MessageDTO> createCentroDeCusto(
            @RequestParam String nmCentroDeCusto,
            @RequestParam String nmResponsavel){
        return ResponseEntity.ok().body(service.createCentroDeCusto(nmCentroDeCusto, nmResponsavel));
    }

    @DeleteMapping(value = "/{idCentroCusto}")
    @Operation(summary = "Deletar um centro de custo através do (idCentroCusto)")
    public ResponseEntity<MessageDTO> deleteCentroDeCusto(@RequestParam Long idCentroCusto){
        return ResponseEntity.ok().body(service.deleteCentroDeCusto(idCentroCusto));
    }


}
