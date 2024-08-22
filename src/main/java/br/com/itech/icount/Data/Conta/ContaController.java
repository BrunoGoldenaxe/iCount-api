package br.com.itech.icount.Data.Conta;

import br.com.itech.icount.Data.Generic.GenericController;
import br.com.itech.icount.Infra.MessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.text.ParseException;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/contas")
@Tag(name = "Conta", description = "API de contas")
public class ContaController extends GenericController {

    @Autowired
    private ContaService service;

    @GetMapping()
    @Operation(summary = "Listar contas com filtro e paginadas")
    public ResponseEntity<PagedModel<EntityModel<Conta>>> getContasWithFilterAndPaged(
            @RequestParam(defaultValue = "0") @Parameter(hidden = true) int pageNo,
            @RequestParam(defaultValue = "10") @Parameter(hidden = true) int pageSize,
            @RequestParam(defaultValue = "dtPagamentoRecebimento") @Parameter(hidden = true) String sortBy,
            @RequestParam(defaultValue = "DESC") @Parameter(hidden = true) String sortDirection,
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) String centroCusto,
            @RequestParam(required = false) String observacao,
            @RequestParam(required = false) String tipoConta,
            @RequestParam(required = false, defaultValue = "false") Boolean isPaga){
        return ResponseEntity.ok().body(service.getContasWithFilterAndPaged(
                pageNo, pageSize, sortBy, sortDirection, cliente, centroCusto, observacao, tipoConta, isPaga));
    }

    @PostMapping()
    @Operation(summary = "Gravar uma nova conta")
    public ResponseEntity<MessageDTO> createConta(@RequestBody ContaDTO dto){
        return ResponseEntity.ok().body(service.createConta(dto));
    }

    @DeleteMapping(value = "/{idConta}")
    @Operation(summary = "Deletar uma conta através do (idConta)")
    public ResponseEntity<MessageDTO> deleteConta(@RequestParam Long idConta){
        return ResponseEntity.ok().body(service.deleteConta(idConta));
    }

    @GetMapping(value = "/{idConta}")
    @Operation(summary = "Buscar uma conta através do (idConta)")
    public ResponseEntity<Conta> getContaById(@RequestParam Long idConta){
        return ResponseEntity.ok().body(service.getContaById(idConta));
    }

    @PatchMapping(value = "/baixar/{idConta}")
    @Operation(summary = "Baixar(pagamento/recebimento) conta através do seu (idConta)")
    public ResponseEntity<MessageDTO> payConta(
            @RequestParam Long idConta,
            @RequestParam String dtPagamentoRecebimento,
            @RequestParam(defaultValue = "0") BigDecimal valDesconto,
            @RequestParam(defaultValue = "0") BigDecimal valJurosMulta) throws ParseException {
        return ResponseEntity.ok().body(service.payConta(idConta, dtPagamentoRecebimento, valDesconto,
                valJurosMulta));
    }
}
