package br.com.itech.icount.Data.Conta;

import br.com.itech.icount.Data.Enum.EnumTipoConta;
import br.com.itech.icount.Infra.Exception.BusinessException;
import br.com.itech.icount.Infra.Exception.NotFoundException;
import br.com.itech.icount.Infra.MessageDTO;
import br.com.itech.icount.Infra.Utils.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class ContaService {

    @Autowired
    private ContaRepository repository;
    @Autowired
    private PagedResourcesAssembler<Conta> assembler;

    @Transactional
    public MessageDTO createConta(ContaDTO contaDTO){
        checksIfContaExists(contaDTO.getCliente(), contaDTO.getDtVencimento(), contaDTO.getValPagarReceber());

        Conta conta = new Conta();
        ModelMapperUtils.map(contaDTO, conta);

        conta.setDsUsuAlter("insert");

        for (EnumTipoConta enumerador : EnumTipoConta.values())
            if(contaDTO.getTipoConta().equals(enumerador.getChave()))
                contaDTO.setTipoConta(enumerador.getChave());

        repository.save(conta);
        return new MessageDTO(HttpStatus.OK, "Conta cadastrada com sucesso!");
    }

    public PagedModel<EntityModel<Conta>> getContasWithFilterAndPaged(
            int pageNo, int pageSize, String sortBy, String sortDirection,
            String cliente, String centroCusto, String observacao, String tipoConta, Boolean isPaga){

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        GenericSpesification<Conta> genericSpesification = new GenericSpesification<Conta>();
        genericSpesification.add(new SearchCriteria(
                "cliente", cliente != null ? cliente : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "centroCusto", centroCusto != null ? centroCusto : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "observacao", observacao != null ? observacao : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "tipoConta", tipoConta != null ? tipoConta : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "isPagaRecebida", isPaga, SearchOperationEnum.EQUAL));

        Page<Conta> contasFilteredAndPaged = repository.findAll(genericSpesification, pageable);

        if(contasFilteredAndPaged.getContent().isEmpty())
            throw new NotFoundException(HttpStatus.NOT_FOUND, "Não existem contas pagas.");

        else
            return assembler.toModel(contasFilteredAndPaged);
    }

    public Conta getContaById(Long idConta){
        Conta conta = repository.findByIdConta(idConta);
        if(conta == null)
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    "Conta com o id " + idConta + ", não encontrada.");
        else
            return conta;

    }


    @Transactional
    public MessageDTO updateConta(ContaDTO contaDTO){


        return new MessageDTO(HttpStatus.OK, "Conta cadastrada com sucesso!");
    }

    @Transactional
    public MessageDTO deleteConta(Long idConta){
        Conta conta = repository.findByIdConta(idConta);

        if(conta == null)
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    "Conta com o id " + idConta + ", não encontrada.");
        else
            repository.delete(conta);
            return new MessageDTO(HttpStatus.OK, "Conta deletada com sucesso!");

    }
    @Transactional
    public MessageDTO payConta(Long idConta, String dtPagamentoRecebimento, BigDecimal valDesconto,
                               BigDecimal valJurosMulta) throws ParseException {
        Conta conta = repository.findByIdConta(idConta);

        if(conta == null)
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    "Conta com o id " + idConta + ", não encontrada.");
        else
            if(conta.isPagaRecebida())
                throw new BusinessException(HttpStatus.BAD_REQUEST, "Já existe pagamento/recebimento para essa conta");

            conta.setPagaRecebida(true);

            Date dtFormat = DateUtils.parseDate(dtPagamentoRecebimento);
            conta.setDtPagamentoRecebimento(dtFormat);

            conta.setValDesconto(valDesconto);
            conta.setValJurosMulta(valJurosMulta);

            if(valDesconto.compareTo(BigDecimal.ZERO) > 0)
                conta.setValPagoRecebido(conta.getValPagarReceber().subtract(valDesconto));

            else if(valJurosMulta.compareTo(BigDecimal.ZERO) > 0)
                conta.setValPagoRecebido(conta.getValPagarReceber().add(valJurosMulta));

            else
                conta.setValPagoRecebido(conta.getValPagarReceber());

                conta.setDsUsuAlter("alter");
                repository.save(conta);
                return new MessageDTO(HttpStatus.OK, "Baixa registrada com sucesso!");

    }


    public void checksIfContaExists(String cliente, Date dtVencimento, BigDecimal valPagarReceber){
        Conta conta = repository.findByClienteAndValPagarReceberAndDtVencimento(
                cliente, valPagarReceber, dtVencimento);

        if(conta != null)
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Já existe uma conta à pagar/receber para o cliente " +
                    cliente + " , no valor de R$ " + valPagarReceber + " reais com vencimento" +
                    " para " + dtVencimento + ".");

    }


}
