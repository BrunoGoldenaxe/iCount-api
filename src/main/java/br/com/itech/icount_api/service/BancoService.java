package br.com.itech.icount_api.service;

import br.com.itech.icount_api.dto.RetornoBancoDTO;
import br.com.itech.icount_api.entity.Banco;
import br.com.itech.icount_api.exception.NotFoundException;
import br.com.itech.icount_api.repository.BancoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BancoService {

    private final BancoRepository repository;

    public Page<RetornoBancoDTO> getBancosPaged(Pageable pageable){
        List<RetornoBancoDTO> listaRetornoBancos = new ArrayList<>();

        Page<Banco> bancos = repository.findAll(pageable);

        if(bancos.isEmpty()){
            throw new NotFoundException("BANCOS_NOT_FOUND", "Não existem bancos cadastrados.");
        }

        bancos.forEach(b -> {
            RetornoBancoDTO dto = new RetornoBancoDTO();
            dto.setNmBanco(b.getNmBanco());
            dto.setNrAgencia(b.getNrAgencia());
            dto.setNrConta(b.getNrConta());
            dto.setSaldo(b.getSaldo());
            dto.setDtUltAlter(b.getDtUltAlter());

            listaRetornoBancos.add(dto);
        });

        ModelMapper modelMapper = new ModelMapper();
        List<RetornoBancoDTO> listaBancos =
                modelMapper.map(
                        listaRetornoBancos,
                        new TypeToken<List<RetornoBancoDTO>>() {}.getType());

        return new PageImpl<RetornoBancoDTO>(
                listaBancos, pageable, repository.count());
    }
}
