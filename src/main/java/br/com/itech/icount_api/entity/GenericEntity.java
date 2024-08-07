package br.com.itech.icount_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GenericEntity {

    @Column(name = "DS_USU_ALTER", nullable = false, length = 30, columnDefinition = "VARCHAR2(30 CHAR) DEFAULT 'INSERT'")
    @NotBlank
    @Size(max = 30)
    private String dsUsuAlter;

    @Column(name = "DT_ULT_ALTER", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dtUltAlter;

    @Column(name = "VS_VERSAO", nullable = false, precision = 10)
    @NotNull
    @Digits(integer = 10, fraction = 0)
    @Builder.Default
    private Long vsVersao = 0L;

    @PreUpdate
    public void preUpdate() {
        dtUltAlter = new Date();
        vsVersao = vsVersao + 1;
    }

    @PrePersist
    public void prePersist(){
        dtUltAlter = new Date();
        vsVersao = 0L;
    }

    public GenericEntity(String dsUsuAlter) {
        this.dsUsuAlter = dsUsuAlter;
    }
}
