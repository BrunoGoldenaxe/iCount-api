package br.com.itech.icount_api.repository;

import br.com.itech.icount_api.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
