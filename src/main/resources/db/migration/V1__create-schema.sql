create table if not exists CENTRO_DE_CUSTO (
        ID_CENTRO_CUSTO bigint not null auto_increment,
        NM_CENTRO_DE_CUSTO varchar(50),
        NM_RESPONSAVEL varchar(50),
        ATIVO bit default true,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (ID_CENTRO_CUSTO)
);

insert into CENTRO_DE_CUSTO values
  (1,'Contabilidade','a',true, "insert", '2024-05-26', 0),
  (2,'Departamento pessoal','b',true, "insert", '2024-05-26', 0),
  (3,'Compras','c',true, "insert", '2024-05-26', 0),
  (4,'Financeiro','d',true, "insert", '2024-05-26', 0),
  (5,'Presidência','e',true, "insert", '2024-05-26', 0),
  (6,'Informática','f',true, "insert", '2024-05-26', 0);

create table if not exists CONTAS (
        ID_CONTA bigint not null auto_increment,
        CLIENTE varchar(50),
        CENTRO_CUSTO varchar(50),
        VAL_PAGAR_RECEBER decimal(38,2),
        VAL_DESCONTO decimal(38,2),
        VAL_JUROS_MULTA decimal(38,2),
        VAL_PAGO_RECEBIDO decimal(38,2),
        DT_EMISSAO date,
        DT_VENCIMENTO date,
        DT_PAGAMENTO_RECEBIMENTO date,
        OBSERVACAO varchar(255),
        IS_PAGA_RECEBIDA bit default false,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        TIPO_CONTA enum ('PAGAR','RECEBER') not null,
        primary key (ID_CONTA)
);

