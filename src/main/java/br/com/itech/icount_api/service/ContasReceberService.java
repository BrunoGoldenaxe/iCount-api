package br.com.itech.icount_api.service;

import br.com.itech.icount_api.dto.ContasReceberDTO;
import br.com.itech.icount_api.dto.MessageDTO;
import br.com.itech.icount_api.dto.RetornoContasDTO;
import br.com.itech.icount_api.dto.RetornoContasRecebidasDTO;
import br.com.itech.icount_api.entity.ContasReceber;
import br.com.itech.icount_api.exception.BusinessException;
import br.com.itech.icount_api.exception.NotFoundException;
import br.com.itech.icount_api.repository.ContasReceberRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ContasReceberService {

    private final ContasReceberRepository repository;

    public Page<RetornoContasDTO> getContasReceberPaginated(Pageable pageable){
        List<RetornoContasDTO> listaRetornoContasReceber = new ArrayList<>();

        Page<ContasReceber> contasReceber = repository.findAllByIsPagaFalseOrderByDtVencimentoDesc(pageable);

        if(contasReceber.isEmpty()){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Não existem contas à receber.");
        }

        contasReceber.forEach(cr -> {
            RetornoContasDTO dto = new RetornoContasDTO();

            var dtVencimentoFormat = DateUtils.parseDate(cr.getDtVencimento());

            dto.setCliente(cr.getCliente());
            dto.setOidContas(cr.getOidContasReceber());
            dto.setValPagar(cr.getValReceber());
            dto.setDtVencimento(dtVencimentoFormat);
            dto.setObservacao(cr.getObservacao());
            dto.setCentroCusto(cr.getCentroCusto());

            listaRetornoContasReceber.add(dto);

        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoContasDTO> listaContasReceber =
                modelMapper.map(
                        listaRetornoContasReceber,
                        new TypeToken<List<RetornoContasDTO>>() {}.getType());

        return new PageImpl<>(
                listaContasReceber, pageable, repository.countByIsPagaFalse());
    }

    @Transactional
    public MessageDTO createContasReceber(ContasReceberDTO dto){
        ContasReceber contas = repository.findByClienteAndValReceberAndDtVencimento(
                dto.getCliente(), dto.getValReceber(), dto.getDtVencimento());

        var dtVencimentoFormatada = DateUtils.parseDate(dto.getDtVencimento());

        if(contas != null){
            throw new BusinessException("BUSINESS_EXCEPTION", "Já existe uma conta à pagar para o cliente " +
                    dto.getCliente() + " , no valor de R$ " + dto.getValReceber() + " reais com vencimento" +
                    " para " + dtVencimentoFormatada + ".");
        }

        ContasReceber contasReceber = new ContasReceber();
        ModelMapperUtils.map(dto, contasReceber);

        contasReceber.setDsUsuAlter("insert");

        repository.save(contasReceber);

        return new MessageDTO("SUCCESS", "Conta cadastrada com sucesso!");
    }

    @Transactional
    public MessageDTO deleteContasReceber(Long oidConta){
        ContasReceber contasReceberEntity = repository.findByOidContasReceber(oidConta);
        if(contasReceberEntity.isPaga()){
            throw new BusinessException("BUSINESS_EXCEPTION", "Não é possível excluir uma conta paga!");
        }else{
            repository.delete(contasReceberEntity);
        }
        return new MessageDTO("SUCCESS", "Conta deletada com sucesso!");
    }

    public ContasReceberDTO getContaReceberById(Long oidConta){
        ContasReceber contasReceberEntity = repository.findByOidContasReceber(oidConta);

        if(contasReceberEntity == null){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Conta não encontrada.");
        }

        ContasReceberDTO contaReceber = new ContasReceberDTO();
        ModelMapperUtils.map(contasReceberEntity, contaReceber);

        return contaReceber;
    }

    public Page<RetornoContasRecebidasDTO> getContasRecebidasPaged(Pageable pageable){
        List<RetornoContasRecebidasDTO> listaRetornoContasRecebidas = new ArrayList<>();

        Page<ContasReceber> contasRecebidas = repository.findAllByIsPagaTrueOrderByDtRecebimentoDesc(pageable);

        if(contasRecebidas.isEmpty()){
            throw new NotFoundException("CONTAS_NOT_FOUND", "Não existem contas recebidas.");
        }

        contasRecebidas.forEach(cr -> {
            RetornoContasRecebidasDTO dto = new RetornoContasRecebidasDTO();

            var dtRecebimentoFormat = DateUtils.parseDate(cr.getDtRecebimento());

            dto.setOidContasReceber(cr.getOidContasReceber());
            dto.setObservacao(cr.getObservacao());
            dto.setCentroCusto(cr.getCentroCusto());
            dto.setValRecebido(cr.getValRecebido());
            dto.setCliente(cr.getCliente());
            dto.setDtRecebimento(dtRecebimentoFormat);

            listaRetornoContasRecebidas.add(dto);
        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoContasRecebidasDTO> listaContasReceber =
                modelMapper.map(
                        listaRetornoContasRecebidas,
                        new TypeToken<List<RetornoContasRecebidasDTO>>() {}.getType());

        return new PageImpl<RetornoContasRecebidasDTO>(
                listaContasReceber, pageable, repository.countByIsPagaTrue());
    }
}
