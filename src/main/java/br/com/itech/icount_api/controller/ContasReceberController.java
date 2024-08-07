package br.com.itech.icount_api.controller;


import br.com.itech.icount_api.dto.*;
import br.com.itech.icount_api.service.ContasReceberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/contas-receber")
public class ContasReceberController {

    private final ContasReceberService service;

    @RequestMapping(method = GET, value = "/listar-contas-receber-paginadas")
    @Operation(summary = "Lista de contas à receber paginadas")
    public ResponseEntity<Page<RetornoContasDTO>> getContasReceberPaginated(Pageable pageable){
        return ResponseEntity.ok().body(service.getContasReceberPaginated(pageable));
    }

    @RequestMapping(method = POST, value = "/gravar-conta")
    @Operation(summary = "Grava uma nova conta à receber")
    public ResponseEntity<MessageDTO> createContasReceber(@RequestBody ContasReceberDTO dto){
        return ResponseEntity.ok().body(service.createContasReceber(dto));
    }

    @RequestMapping(method = DELETE, value = "/deletar-conta-por-id/{oidContasReceber}")
    @Operation(summary = "Deletar uma conta através do (oidContasReceber)")
    public ResponseEntity<MessageDTO> deleteContasPagar(@PathVariable Long oidContasReceber){
        return ResponseEntity.ok().body(service.deleteContasReceber(oidContasReceber));
    }

    @RequestMapping(method = GET, value = "/listar-contas-recebidas-paginadas")
    @Operation(summary = "Lista de contas recebidas paginadas")
    public ResponseEntity<Page<RetornoContasRecebidasDTO>> getContasRecebidasPaginated(Pageable pageable){
        return ResponseEntity.ok().body(service.getContasRecebidasPaged(pageable));
    }

    @RequestMapping(method = GET, value = "/buscar-conta-por-id/{oidContas}")
    @Operation(summary = "Buscar uma conta à receber por id")
    public ResponseEntity<ContasReceberDTO> getContaPagarById(@PathVariable Long oidContas){
        return ResponseEntity.ok().body(service.getContaReceberById(oidContas));
    }
}
