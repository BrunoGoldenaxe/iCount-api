package br.com.itech.icount_api.controller;

import br.com.itech.icount_api.dto.RetornoBancoDTO;
import br.com.itech.icount_api.service.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@AllArgsConstructor
@RestController
@RequestMapping("/api/banco")
public class BancoController {

    private final BancoService service;

    @RequestMapping(method = GET, value = "/listar-bancos-paginados")
    @Operation(summary = "Lista de contas bancárias")
    public ResponseEntity<Page<RetornoBancoDTO>> getBancosPaged(Pageable pageable){
        return ResponseEntity.ok().body(service.getBancosPaged(pageable));
    }


}
