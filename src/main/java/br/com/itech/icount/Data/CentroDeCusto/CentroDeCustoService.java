package br.com.itech.icount.Data.CentroDeCusto;

import br.com.itech.icount.Infra.Exception.BusinessException;
import br.com.itech.icount.Infra.Exception.NotFoundException;
import br.com.itech.icount.Infra.MessageDTO;
import br.com.itech.icount.Infra.Utils.GenericSpesification;
import br.com.itech.icount.Infra.Utils.ModelMapperUtils;
import br.com.itech.icount.Infra.Utils.SearchCriteria;
import br.com.itech.icount.Infra.Utils.SearchOperationEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository repository;

    public List<CentroDeCusto> getCentrosDeCustoWithFilter(String nmCentroDeCusto, String nmResponsavel, Boolean ativo){
        GenericSpesification<CentroDeCusto> genericSpesification = new GenericSpesification<CentroDeCusto>();
        genericSpesification.add(new SearchCriteria(
                "nmCentroDeCusto", nmCentroDeCusto != null ? nmCentroDeCusto : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "nmResponsavel", nmResponsavel != null ? nmResponsavel : "", SearchOperationEnum.MATCH));
        genericSpesification.add(new SearchCriteria(
                "ativo", ativo, SearchOperationEnum.EQUAL));

        List<CentroDeCusto> centroDeCustoFiltered = repository.findAll(genericSpesification);

        if(centroDeCustoFiltered.isEmpty())
            throw new NotFoundException(HttpStatus.NOT_FOUND, "Não existem centros de custo cadastrados.");

        else
            return centroDeCustoFiltered;

    }

    public CentroDeCusto getCentroDeCustoById(Long idCentroCusto){
        CentroDeCusto centroDeCusto = repository.findByIdCentroCusto(idCentroCusto);
        if(centroDeCusto == null)
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    "Centro de custo com o id " + idCentroCusto + ", não encontrado.");
        else
            return centroDeCusto;
    }

    @Transactional
    public MessageDTO createCentroDeCusto(String nmCentroDeCusto, String nmResponsavel){
        checksIfCentroDeCustoExists(nmCentroDeCusto);

        CentroDeCusto centroDeCusto = new CentroDeCusto();
        centroDeCusto.setNmCentroDeCusto(nmCentroDeCusto);
        centroDeCusto.setNmResponsavel(nmResponsavel);
        centroDeCusto.setDsUsuAlter("insert");

        repository.save(centroDeCusto);
        return new MessageDTO(HttpStatus.OK, "Centro de custo cadastrado com sucesso!");
    }

    @Transactional
    public MessageDTO deleteCentroDeCusto(Long idCentroCusto){
        CentroDeCusto centroDeCusto = repository.findByIdCentroCusto(idCentroCusto);

        if(centroDeCusto == null)
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    "Centro de custo com o id " + idCentroCusto + ", não encontrado.");
        else
            repository.delete(centroDeCusto);
            return new MessageDTO(HttpStatus.OK, "Centro de custo deletado com sucesso!");
    }

    public void checksIfCentroDeCustoExists(String nmCentroDeCusto){
        CentroDeCusto centroDeCusto = repository.findByNmCentroDeCusto(nmCentroDeCusto);

        if(centroDeCusto != null)
            throw new BusinessException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um centro de custo: " + nmCentroDeCusto +
                    " cadastrado.");
    }
}
