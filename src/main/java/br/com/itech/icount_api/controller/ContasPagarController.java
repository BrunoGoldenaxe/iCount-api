package br.com.itech.icount_api.controller;

import br.com.itech.icount_api.dto.*;
import br.com.itech.icount_api.service.ContasPagarService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController extends GenericController {

    private final ContasPagarService service;

    @RequestMapping(method = GET, value = "/listar-contas-pagar-paginadas")
    @Operation(summary = "Lista de contas à vencer paginadas")
    public ResponseEntity<Page<RetornoContasDTO>> getContasPagarPaginated(Pageable pageable){
        return ResponseEntity.ok().body(service.getContasPagarPaginated(pageable));
    }

    @RequestMapping(method = GET, value = "/listar-contas-pagar-paginadas-da-semana")
    @Operation(summary = "Lista contas à vencer no período de uma semana")
    public ResponseEntity<Page<RetornoContasDTO>> getContasPagarPaginatedBetweenDates(Pageable pageable){
        return ResponseEntity.ok().body(service.getContasPagarPaginatedBetweenDates(pageable));
    }

    @RequestMapping(method = POST, value = "/gravar-conta")
    @Operation(summary = "Grava uma nova conta à pagar")
    public ResponseEntity<MessageDTO> createContasPagar(@RequestBody ContasPagarDTO dto){
        return ResponseEntity.ok().body(service.createContasPagar(dto));
    }

    @RequestMapping(method = POST, value = "/pagamento-de-conta")
    @Operation(summary = "Pagamento de uma conta")
    public ResponseEntity<MessageDTO> billPayment(
            @RequestParam(name = "oidConta") Long oidConta,
            @RequestParam(name = "valDesconto", required = false) BigDecimal valDesconto,
            @RequestParam(name = "valJurosMulta", required = false) BigDecimal valJurosMulta,
            @RequestParam(name = "dtPagamento", required = false) LocalDate dtPagamento){
        return ResponseEntity.ok().body(service.billPayment(oidConta, valDesconto,
                valJurosMulta, dtPagamento));
    }

    @RequestMapping(method = DELETE, value = "/deletar-conta-por-id/{oidContasPagar}")
    @Operation(summary = "Deletar uma conta através do (oidContasPagar)")
    public ResponseEntity<MessageDTO> deleteContasPagar(@PathVariable Long oidContasPagar){
        return ResponseEntity.ok().body(service.deleteContasPagar(oidContasPagar));
    }

    @RequestMapping(method = POST, value = "/editar")
    @Operation(summary = "Editar os dados de uma conta")
    public ResponseEntity<MessageDTO> updateContasPagar(
            @RequestParam(name = "oidConta") Long oidConta,
            @RequestParam(name = "valPagar", required = false) BigDecimal valPagar,
            @RequestParam(name = "cliente", required = false) String cliente,
            @RequestParam(name = "centroCusto", required = false) String centroCusto,
            @RequestParam(name = "dtEmissao", required = false) LocalDate dtEmissao,
            @RequestParam(name = "dtVencimento", required = false) LocalDate dtVencimento,
            @RequestParam(name = "observacao", required = false) String observacao){
        return ResponseEntity.ok().body(service.updateContasPagar(oidConta, valPagar, cliente,
                centroCusto, dtEmissao, dtVencimento, observacao));
    }

    @RequestMapping(method = GET, value = "/listar-contas-pagas-paginadas")
    @Operation(summary = "Lista de contas pagas paginadas")
    public ResponseEntity<Page<RetornoContasPagasDTO>> getContasPagasPaginated(Pageable pageable){
        return ResponseEntity.ok().body(service.getContasPagasPaged(pageable));
    }

    @RequestMapping(method = GET, value = "/buscar-conta-por-id/{oidContas}")
    @Operation(summary = "Buscar uma conta à pagar por id")
    public ResponseEntity<ContasPagarDTO> getContaPagarById(@PathVariable Long oidContas){
        return ResponseEntity.ok().body(service.getContaPagarById(oidContas));
    }

}
