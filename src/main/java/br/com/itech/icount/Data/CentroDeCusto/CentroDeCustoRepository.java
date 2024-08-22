package br.com.itech.icount.Data.CentroDeCusto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long>, JpaSpecificationExecutor<CentroDeCusto> {

    CentroDeCusto findByNmCentroDeCusto(String nmCentroDeCusto);
    CentroDeCusto findByIdCentroCusto(Long idCentroCusto);
}
