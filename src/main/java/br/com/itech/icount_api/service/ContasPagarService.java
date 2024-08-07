package br.com.itech.icount_api.service;

import br.com.itech.icount_api.dto.*;
import br.com.itech.icount_api.entity.ContasPagar;
import br.com.itech.icount_api.exception.BusinessException;
import br.com.itech.icount_api.exception.NotFoundException;
import br.com.itech.icount_api.repository.ContasPagarRepository;
import br.com.itech.icount_api.utils.DateUtils;
import br.com.itech.icount_api.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ContasPagarService {

    private final ContasPagarRepository repository;

    public Page<RetornoContasDTO> getContasPagarPaginated(Pageable pageable){
        List<RetornoContasDTO> listaRetornoContasPagar = new ArrayList<>();

        Page<ContasPagar> contasPagar = repository.findAllByIsPagaFalseOrderByDtVencimentoDesc(pageable);

        if(contasPagar.isEmpty()){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Não existem contas à pagar.");
        }

        contasPagar.forEach(cp -> {
            RetornoContasDTO dto = new RetornoContasDTO();

            var dtVencimentoFormat = DateUtils.parseDate(cp.getDtVencimento());

            dto.setCliente(cp.getCliente());
            dto.setOidContas(cp.getOidContasPagar());
            dto.setValPagar(cp.getValPagar());
            dto.setDtVencimento(dtVencimentoFormat);
            dto.setObservacao(cp.getObservacao());
            dto.setCentroCusto(cp.getCentroCusto());

            listaRetornoContasPagar.add(dto);

        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoContasDTO> listaContasPagar =
                modelMapper.map(
                        listaRetornoContasPagar,
                        new TypeToken<List<RetornoContasDTO>>() {}.getType());

        return new PageImpl<RetornoContasDTO>(
                listaContasPagar, pageable, repository.countByIsPagaFalse());
    }

    public Page<RetornoContasDTO> getContasPagarPaginatedBetweenDates(Pageable pageable){
        List<RetornoContasDTO> listaRetornoContasPagar = new ArrayList<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(7);

        Page<ContasPagar> contasPagar = repository.findAllByIsPagaFalseAndDtVencimentoBetween(pageable, startDate, endDate);

        if(contasPagar.isEmpty()){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Não existem contas à pagar.");
        }

        contasPagar.forEach(cp -> {
            RetornoContasDTO dto = new RetornoContasDTO();

            var dtVencimentoFormat = DateUtils.parseDate(cp.getDtVencimento());

            dto.setCliente(cp.getCliente());
            dto.setOidContas(cp.getOidContasPagar());
            dto.setValPagar(cp.getValPagar());
            dto.setDtVencimento(dtVencimentoFormat);
            dto.setObservacao(cp.getObservacao());
            dto.setCentroCusto(cp.getCentroCusto());

            listaRetornoContasPagar.add(dto);

        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoContasDTO> listaContasPagar =
                modelMapper.map(
                        listaRetornoContasPagar,
                        new TypeToken<List<RetornoContasDTO>>() {}.getType());

        return new PageImpl<RetornoContasDTO>(
                listaContasPagar, pageable, repository.countByIsPagaFalseAndDtVencimentoBetween(startDate, endDate));
    }

    @Transactional
    public MessageDTO createContasPagar(ContasPagarDTO dto){
        ContasPagar contas = repository.findByClienteAndValPagarAndDtVencimento(
                dto.getCliente(), dto.getValPagar(), dto.getDtVencimento());

        var dtVencimentoFormatada = DateUtils.parseDate(dto.getDtVencimento());

        if(contas != null){
            throw new BusinessException("BUSINESS_EXCEPTION", "Já existe uma conta à pagar para o cliente " +
                    dto.getCliente() + " , no valor de R$ " + dto.getValPagar() + " reais com vencimento" +
                    " para " + dtVencimentoFormatada + ".");
        }

        ContasPagar contasPagar = new ContasPagar();
        ModelMapperUtils.map(dto, contasPagar);

        contasPagar.setDsUsuAlter("insert");

        repository.save(contasPagar);

        return new MessageDTO("SUCCESS", "Conta cadastrada com sucesso!");
    }

    @Transactional
    public MessageDTO billPayment(Long oidConta, BigDecimal valDesconto, BigDecimal valJurosMulta, LocalDate dtPagamento){
        ContasPagar contasPagarEntity = repository.findByOidContasPagar(oidConta);

        var dtPagamentoFormat = DateUtils.parseDate(dtPagamento);

        if(contasPagarEntity.isPaga()){
            throw new BusinessException("BUSINESS_EXCEPTION", "Já existe pagamento para a "
            + contasPagarEntity.getObservacao() + " " + contasPagarEntity.getCliente()
            + " na data de " +  dtPagamentoFormat + ".");
        }else{
            contasPagarEntity.setPaga(true);

            contasPagarEntity.setDtPagamento(dtPagamento);

            if(dtPagamento.isBefore(contasPagarEntity.getDtVencimento())
                    || dtPagamento.isEqual(contasPagarEntity.getDtVencimento())){
                contasPagarEntity.setValDesconto(valDesconto != null ?
                        valDesconto : BigDecimal.ZERO);

                contasPagarEntity.setValPago(contasPagarEntity.getValPagar().subtract(
                        valDesconto != null ? valDesconto : BigDecimal.ZERO));

                contasPagarEntity.setValJurosMulta(BigDecimal.ZERO);

            }else{
                contasPagarEntity.setValJurosMulta(valJurosMulta != null ?
                        valJurosMulta : BigDecimal.ZERO);

                contasPagarEntity.setValPago(contasPagarEntity.getValPagar().add(
                        valJurosMulta != null ? valJurosMulta : BigDecimal.ZERO));

                contasPagarEntity.setValDesconto(BigDecimal.ZERO);
            }

            repository.save(contasPagarEntity);
        }

        return new MessageDTO("SUCCESS", "Pagamento efetuado com sucesso!");
    }

    @Transactional
    public MessageDTO deleteContasPagar(Long oidConta){
        ContasPagar contasPagarEntity = repository.findByOidContasPagar(oidConta);
        if(contasPagarEntity.isPaga()){
            throw new BusinessException("BUSINESS_EXCEPTION", "Não é possível excluir uma conta paga!");
        }else{
            repository.delete(contasPagarEntity);
        }
        return new MessageDTO("SUCCESS", "Conta deletada com sucesso!");
    }

    public ContasPagarDTO getContaPagarById(Long oidConta){
        ContasPagar contaPagarEntity = repository.findByOidContasPagar(oidConta);

        if(contaPagarEntity == null){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Conta não encontrada.");
        }

        ContasPagarDTO contaPagar = new ContasPagarDTO();
        ModelMapperUtils.map(contaPagarEntity, contaPagar);

        return contaPagar;
    }

    @Transactional
    public MessageDTO updateContasPagar(Long oidConta, BigDecimal valPagar, String cliente, String centroCusto,
                                        LocalDate dtEmissao, LocalDate dtVencimento, String observacao){
        ContasPagar contasPagarEntity = repository.findByOidContasPagar(oidConta);

        if(contasPagarEntity == null){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Conta com o id " + oidConta + ", não encontrada.");
        }

        if(contasPagarEntity.isPaga()){
            throw new BusinessException("BUSINESS_EXCEPTION", "Não é possível editar uma conta já paga.");
        }else{
            if(valPagar == null && cliente == null && centroCusto == null && dtEmissao == null && dtVencimento == null
                    && observacao == null){
                throw new BusinessException("BUSINESS_EXCEPTION", "Não é possível editar uma conta sem alguma alteração.");
            }else{
                contasPagarEntity.setValPagar(valPagar != null ? valPagar : contasPagarEntity.getValPagar());
                contasPagarEntity.setCliente(cliente != null ? cliente : contasPagarEntity.getCliente());
                contasPagarEntity.setCentroCusto(centroCusto != null ? centroCusto : contasPagarEntity.getCentroCusto());
                contasPagarEntity.setDtEmissao(dtEmissao != null ? dtEmissao : contasPagarEntity.getDtEmissao());
                contasPagarEntity.setDtVencimento(dtVencimento != null ? dtVencimento : contasPagarEntity.getDtVencimento());
                contasPagarEntity.setObservacao(observacao != null ? observacao : contasPagarEntity.getObservacao());
                contasPagarEntity.setDsUsuAlter("update");

                repository.save(contasPagarEntity);
            }
        }
        return new MessageDTO("SUCCESS", "Conta atualizada com sucesso!");
    }

    public Page<RetornoContasPagasDTO> getContasPagasPaged(Pageable pageable){
        List<RetornoContasPagasDTO> listaRetornoContasPagas = new ArrayList<>();

        Page<ContasPagar> contasPagas = repository.findAllByIsPagaTrueOrderByDtPagamentoDesc(pageable);

        if(contasPagas.isEmpty()){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Não existem contas pagas.");
        }

        contasPagas.forEach(cp -> {
            RetornoContasPagasDTO dto = new RetornoContasPagasDTO();

            var dtPagamentoFormat = DateUtils.parseDate(cp.getDtPagamento());

            dto.setOidContasPagar(cp.getOidContasPagar());
            dto.setObservacao(cp.getObservacao());
            dto.setCentroCusto(cp.getCentroCusto());
            dto.setValPago(cp.getValPago());
            dto.setCliente(cp.getCliente());
            dto.setDtPagamento(dtPagamentoFormat);

            listaRetornoContasPagas.add(dto);

        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoContasPagasDTO> listaContasPagar =
                modelMapper.map(
                        listaRetornoContasPagas,
                        new TypeToken<List<RetornoContasPagasDTO>>() {}.getType());

        return new PageImpl<RetornoContasPagasDTO>(
                listaContasPagar, pageable, repository.countByIsPagaTrue());
    }
}
