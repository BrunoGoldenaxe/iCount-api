package br.com.itech.icount.Data.Conta;

import br.com.itech.icount.Data.Enum.EnumTipoConta;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>, JpaSpecificationExecutor<Conta> {

    Conta findByClienteAndValPagarReceberAndDtVencimento(String cliente, BigDecimal valPagar, Date dtVencimento);
    Conta findByIdConta(Long idConta);

}
