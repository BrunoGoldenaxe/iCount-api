package br.com.itech.icount_api.repository;

import br.com.itech.icount_api.entity.ContasReceber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Long> {

    Long countByIsPagaFalse();

    Long countByIsPagaTrue();

    Page<ContasReceber> findAllByIsPagaFalseOrderByDtVencimentoDesc(Pageable pageable);

    Page<ContasReceber> findAllByIsPagaTrueOrderByDtRecebimentoDesc(Pageable pageable);

    ContasReceber findByClienteAndValReceberAndDtVencimento(String cliente, BigDecimal valReceber, LocalDate dtVencimento);

    ContasReceber findByOidContasReceber(Long oidConta);
}
