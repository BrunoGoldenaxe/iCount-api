package br.com.itech.icount_api.repository;

import br.com.itech.icount_api.entity.ContasPagar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;


@Repository
public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {

    Long countByIsPagaFalse();

    Long countByIsPagaFalseAndDtVencimentoBetween(LocalDate startDate, LocalDate endDate);

    Long countByIsPagaTrue();

    Page<ContasPagar> findAllByIsPagaFalseOrderByDtVencimentoDesc(Pageable pageable);

    Page<ContasPagar> findAllByIsPagaTrueOrderByDtPagamentoDesc(Pageable pageable);

    Page<ContasPagar> findAllByIsPagaFalseAndDtVencimentoBetween(Pageable pageable, LocalDate startDate, LocalDate endDate);

    ContasPagar findByClienteAndValPagarAndDtVencimento(String cliente, BigDecimal valPagar, LocalDate dtVencimento);

    ContasPagar findByOidContasPagar(Long oidConta);
}
