create table if not exists CENTRO_DE_CUSTO (
        OID_CENTRO_CUSTO bigint not null auto_increment,
        NM_CENTRO_DE_CUSTO varchar(255),
        NM_RESPONSAVEL varchar(255),
        ATIVO bit,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_CENTRO_CUSTO)
);

insert into CENTRO_DE_CUSTO values
  (1,'Contabilidade','a',true, "insert", '2024-05-26', 0),
  (2,'Departamento pessoal','b',true, "insert", '2024-05-26', 0),
  (3,'Compras','c',true, "insert", '2024-05-26', 0),
  (4,'Financeiro','d',true, "insert", '2024-05-26', 0),
  (5,'Presidência','e',true, "insert", '2024-05-26', 0),
  (6,'Informática','f',true, "insert", '2024-05-26', 0);

create table if not exists CONTAS_RECEBER (
        OID_CONTAS_RECEBER bigint not null auto_increment,
        CLIENTE varchar(255),
        CENTRO_CUSTO varchar(255),
        VAL_RECEBER decimal(38,2),
        VAL_DESCONTO decimal(38,2),
        VAL_JUROS_MULTA decimal(38,2),
        VAL_RECEBIDO decimal(38,2),
        DT_EMISSAO date,
        DT_VENCIMENTO date,
        DT_RECEBIMENTO date,
        OBSERVACAO varchar(255),
        IS_PAGA bit default false,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_CONTAS_RECEBER)
);

create table if not exists CONTAS_PAGAR (
        OID_CONTAS_PAGAR bigint not null auto_increment,
        CLIENTE varchar(255),
        CENTRO_CUSTO varchar(255),
        VAL_PAGAR decimal(38,2),
        VAL_DESCONTO decimal(38,2),
        VAL_JUROS_MULTA decimal(38,2),
        VAL_PAGO decimal(38,2),
        DT_EMISSAO date,
        DT_VENCIMENTO date,
        DT_PAGAMENTO date,
        OBSERVACAO varchar(255),
        IS_PAGA bit default false,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_CONTAS_PAGAR)
);

create table if not exists PLANO_DE_CONTAS (
        OID_CONTA bigint not null auto_increment,
        CD_CONTA varchar(255),
        NM_CONTA varchar(255),
        DESCRICAO varchar(255),
        NATUREZA enum ('D','C'),
        ATIVO bit,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_CONTA)
);

insert into PLANO_DE_CONTAS values
   (1,'1','ATIVO','totalizador','D',true, "insert", '2024-05-26', 0),
   (2,'1.1','CIRCULANTE','totalizador','D',true, "insert", '2024-05-26', 0),
   (3,'1.1.1','DISPONÍVEL','totalizador','D',true, "insert", '2024-05-26', 0),
   (4,'1.1.1.01','Caixa','disponível','D',true, "insert", '2024-05-26', 0),
   (5,'1.1.1.02','Banco','disponível','D',true, "insert", '2024-05-26', 0),
   (6,'1.1.2','DIREITOS A RECEBER','totalizador','D',true, "insert", '2024-05-26', 0),
   (7,'1.1.2.01','Clientes a Receber','direitos','D',true, "insert", '2024-05-26', 0),
   (8,'1.1.2.02','Cheques a Receber','direitos','D',true, "insert", '2024-05-26', 0),
   (9,'1.1.2.03','Cartões a Receber','direitos','D',true, "insert", '2024-05-26', 0),
   (10,'1.1.2.99','(-) CONTAS REDUTORAS','totalizador','C',true, "insert", '2024-05-26', 0),
   (11,'1.1.2.99.01','(-) Devoluções de Clientes','redutora','C',true, "insert", '2024-05-26', 0),
   (12,'1.1.2.99.02','(-) Prov.p/Créd.de Liquidação Duvidosa','redutora','C',true, "insert", '2024-05-26', 0),
   (13,'1.1.3','ESTOQUES','totalizador','D',true, "insert", '2024-05-26', 0),
   (14,'1.1.4','ADIANTAMENTOS','totalizador','D',true, "insert", '2024-05-26', 0),
   (15,'1.1.4.01','Adiantamento de Salários','adiantamentos','D',true, "insert", '2024-05-26', 0),
   (16,'1.1.4.02','Adiantamento de 13º Salário','adiantamentos','D',true, "insert", '2024-05-26', 0),
   (17,'1.1.4.03','Adiantamentos a Fornecedores ','adiantamentos','D',true, "insert", '2024-05-26', 0),
   (18,'1.1.5','OUTROS CRÉDITOS','totalizador','D',true, "insert", '2024-05-26', 0),
   (19,'1.1.5.01','Outros Créditos Diversos a Receber','outros créditos','D',true, "insert", '2024-05-26', 0),
   (20,'1.3','IMOBILIZADO','totalizador','D',true, "insert", '2024-05-26', 0),
   (21,'1.3.1','Imobilizações Técnicas','imobilizado','D',true, "insert", '2024-05-26', 0),
   (22,'1.3.2','(-)Depreciações Acumuladas','imobilizado','C',true, "insert", '2024-05-26', 0),
   (23,'1.3.3','Adiantamento a Fornecedores','imobilizado','D',true, "insert", '2024-05-26', 0),
   (24,'2','PASSIVO','totalizador','C',true, "insert", '2024-05-26', 0),
   (25,'2.1','CIRCULANTE','totalizador','C',true, "insert", '2024-05-26', 0),
   (26,'2.1.1','DÉBITOS DE FUNCIONAMENTO','totalizador','C',true, "insert", '2024-05-26', 0),
   (27,'2.1.1.01','Fornecedores','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (28,'2.1.1.02','Salários à Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (29,'2.1.1.03','FGTS à Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (30,'2.1.1.04','INSS à Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (31,'2.1.1.05','Férias à Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (32,'2.1.1.06','Décimo Terceiro à Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (33,'2.1.1.07','Adiantamento de Clientes','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (34,'2.1.1.08','Recuperações a Pagar','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (35,'2.1.1.09','Obrigações Sociais a Recolher','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (36,'2.1.1.10','Obrigações Tributárias a Recolher','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (37,'2.1.1.11','Parcelamento de Tributos','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (38,'2.1.1.12','Cartões de Crédito','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (39,'2.1.2','DÉBITOS DE FINANCIAMENTO','totalizador','C',true, "insert", '2024-05-26', 0),
   (40,'2.1.2.01','Financiamentos de Capital de giro','débitos de financiamento','C',true, "insert", '2024-05-26', 0),
   (41,'2.1.2.02','Financiamentos de Bens do Ativo Permanente','débitos de financiamento','C',true, "insert", '2024-05-26', 0),
   (42,'2.2','PASSIVO EXIGÍVEL A LONGO PRAZO','totalizador','C',true, "insert", '2024-05-26', 0),
   (43,'2.2.1','DÉBITOS DE FUNCIONAMENTO','totalizador','C',true, "insert", '2024-05-26', 0),
   (44,'2.2.1.01','Fornecedores','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (45,'2.2.1.02','Parcelamentos de Tributos','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (46,'2.2.1.03','Cartões de Crédito','débitos de funcionamento','C',true, "insert", '2024-05-26', 0),
   (47,'2.4','PATRIMÔNIO LÍQUIDO','totalizador','C',true, "insert", '2024-05-26', 0),
   (48,'2.4.1','CAPITAL SOCIAL','totalizador','C',true, "insert", '2024-05-26', 0),
   (49,'2.4.1.1','Capital Realizado','capital social','C',true, "insert", '2024-05-26', 0),
   (50,'2.4.2','RESERVAS DE CAPITAL','totalizador','C',true, "insert", '2024-05-26', 0),
   (51,'2.4.2.1','Reservas de Correção Monetária Do Capital','reservas de capital','C',true, "insert", '2024-05-26', 0),
   (52,'2.4.2.2','Reservas de Ágio','reservas de capital','C',true, "insert", '2024-05-26', 0),
   (53,'2.4.2.3','Reservas de Reavaliação','reservas de capital','C',true, "insert", '2024-05-26', 0),
   (54,'2.4.3','RESERVAS DE REAVALIAÇÃO','totalizador','C',true, "insert", '2024-05-26', 0),
   (55,'2.4.3.1','Reservas de Reavaliação de Bens Próprios','reservas de reavaliação','C',true, "insert", '2024-05-26', 0),
   (56,'2.4.3.2','Reserva de Reavaliação de Bens de Coligadas','reservas de reavaliação','C',true, "insert", '2024-05-26', 0),
   (57,'2.4.4','RESERVAS DE LUCROS','totalizador','C',true, "insert", '2024-05-26', 0),
   (58,'2.4.4.1','Reservas Contratuais','reservas de lucros','C',true, "insert", '2024-05-26', 0),
   (59,'2.4.4.2','Reservas para Contingências','reservas de lucros','C',true, "insert", '2024-05-26', 0),
   (60,'2.4.4.9','Outras Reservas','reservas de lucros','C',true, "insert", '2024-05-26', 0),
   (61,'2.4.9','RESULTADOS ACUMULADOS','totalizador','C',true, "insert", '2024-05-26', 0),
   (62,'2.4.9.1','Lucros Acumulados','resultados acumulados','C',true, "insert", '2024-05-26', 0),
   (63,'2.4.9.2','(-)Prejuízos Acumulados','resultados acumulados','D',true, "insert", '2024-05-26', 0),
   (64,'2.4.9.9','Lucros ou Prejuízos do Período','resultados acumulados','C',true, "insert", '2024-05-26', 0),
   (65,'3','RESULTADO LÍQUIDO','totalizador','D',true, "insert", '2024-05-26', 0),
   (66,'3.1','RESULTADO OPERACIONAL','totalizador','D',true, "insert", '2024-05-26', 0),
   (67,'3.1.1','RECEITAS OPERACIONAIS','totalizador','C',true, "insert", '2024-05-26', 0),
   (68,'3.1.1.1','Receitas de Prestação de Serviços','receitas operacionais','C',true, "insert", '2024-05-26', 0),
   (69,'3.1.1.2','Receitas de vendas','receitas operacionais','C',true, "insert", '2024-05-26', 0),
   (70,'3.1.1.3','Receitas S/Aplic.Financeiras','receitas operacionais','C',true, "insert", '2024-05-26', 0),
   (71,'3.1.1.9','(-)Devoluções de vendas/serviços','receitas operacionais','D',true, "insert", '2024-05-26', 0),
   (72,'3.1.2','(-)CUSTOS OPERACIONAIS','totalizador','C',true, "insert", '2024-05-26', 0),
   (73,'3.1.2.1','(-)Custos dos Materiais Vendidos','custos operacionais','D',true, "insert", '2024-05-26', 0),
   (74,'3.1.2.2','(-)Custos dos Serviços Prestados','custos operacionais','D',true, "insert", '2024-05-26', 0),
   (75,'3.1.3','DESPESAS OPERACIONAIS','totalizador','D',true, "insert", '2024-05-26', 0),
   (76,'3.1.3.01','Despesas Administrativas','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (77,'3.1.3.02','Despesas Comerciais','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (78,'3.1.3.03','Despesas Financeiras Líquidas','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (79,'3.1.3.04','Serviços Prestados PJ','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (80,'3.1.3.05','Serviços Contábeis','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (81,'3.1.3.06','Material de Escritório','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (82,'3.1.3.07','Material de Informática','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (83,'3.1.3.08','Propaganda e Publicidade','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (84,'3.1.3.09','Conservação e Manutenção','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (85,'3.1.3.10','Despesas Bancárias','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (86,'3.1.3.11','Juros e Multas','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (87,'3.1.3.12','IR S/Aplicações','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (88,'3.1.3.13','Amortizações e Depreciações','despesas operacionais','D',true, "insert", '2024-05-26', 0),
   (89,'3.1.4','DESPESAS COM PESSOAL','totalizador','D',true, "insert", '2024-05-26', 0),
   (90,'3.1.4.01','Salários','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (91,'3.1.4.02','Horas-extras','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (92,'3.1.4.03','Adicional Noturno','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (93,'3.1.4.04','Férias','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (94,'3.1.4.05','Abono de Férias','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (95,'3.1.4.06','Décimo Terceiro Salário','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (96,'3.1.4.07','FGTS','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (97,'3.1.4.08','Previdência Social','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (98,'3.1.4.09','Auxílio Refeição','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (99,'3.1.4.10','Plano de Saúde','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (100,'3.1.4.11','Vale Transporte','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (101,'3.1.4.12','Aviso Prévio Indenizado','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (102,'3.1.4.13','Aviso Prévio Trabalho','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (103,'3.1.4.14','Indenização FGTS (40%)','despesas com pessoal','D',true, "insert", '2024-05-26', 0),
   (104,'3.1.5','EFEITOS INFLACIONÁRIOS','totalizador','D',true, "insert", '2024-05-26', 0),
   (105,'3.1.5.1','Correção Monetária de Balanço','efeitos inflacionários','D',true, "insert", '2024-05-26', 0),
   (106,'3.2.1','RECEITAS NÃO OPERACIONAIS','totalizador','C',true, "insert", '2024-05-26', 0),
   (107,'3.2.1.1','Ganhos de Equivalência Patrimonial','receitas não operacionais','C',true, "insert", '2024-05-26', 0),
   (108,'3.2.1.2','Receitas Diversas','receitas não operacionais','C',true, "insert", '2024-05-26', 0),
   (109,'3.2.2','DESPESAS NÃO OPERACIONAIS','totalizador','D',true, "insert", '2024-05-26', 0),
   (110,'3.2.2.1','Perdas de Equivalência Patrimonial','despesas não operacionais','D',true, "insert", '2024-05-26', 0),
   (111,'3.2.2.2','Despesas Diversas','despesas não operacionais','D',true, "insert", '2024-05-26', 0);

create table if not exists HISTORICO_PADRAO (
    OID_HISTORICO bigint not null auto_increment,
    HISTORICO varchar(255),
    ATIVO bit,
    DS_USU_ALTER varchar(30) not null,
    DT_ULT_ALTER date not null,
    VS_VERSAO bigint not null,
    primary key (OID_HISTORICO)
);


insert into HISTORICO_PADRAO values
(1,'CHEQUES EMITIDOS',true, "insert", '2024-05-26', 0),
(2,'RECEBIDO DUPLICATA',true, "insert", '2024-05-26', 0),
(3,'VALOR A RECOLHER',true, "insert", '2024-05-26', 0),
(4,'VALOR A PAGAR',true, "insert", '2024-05-26', 0),
(5,'PAGTO CF-e. GUIA',true, "insert", '2024-05-26', 0),
(6,'PAGTO NFS-e',true, "insert", '2024-05-26', 0),
(7,'PAGTO CONTA',true, "insert", '2024-05-26', 0),
(8,'ESTORNO',true, "insert", '2024-05-26', 0),
(9,'DESCONTOS OBTIDOS',true, "insert", '2024-05-26', 0),
(10,'PAGTO FORNECEDORES',true, "insert", '2024-05-26', 0),
(11,'RECEBIDO VALOR DA DUPLICATA',true, "insert", '2024-05-26', 0),
(12,'PAGTO.DESP.CONSUMO',true, "insert", '2024-05-26', 0),
(13,'RETIDO IRRF S/ SERVICOS',true, "insert", '2024-05-26', 0),
(14,'RETIDO IR ESTADUAL S/ SERVICOS',true, "insert", '2024-05-26', 0),
(15,'VENDA DE IMOBILIZADO',true, "insert", '2024-05-26', 0),
(16,'A PAGAR FOLHA DE PAGTO. REF.',true, "insert", '2024-05-26', 0),
(17,'RETIDO INSS DOS EMPREG. REF.',true, "insert", '2024-05-26', 0),
(18,'RETIDO CONTRIB. SINDICAL REF.',true, "insert", '2024-05-26', 0),
(19,'DESPESAS A RECUPERAR',true, "insert", '2024-05-26', 0),
(20,'DESPESAS DE SALARIO',true, "insert", '2024-05-26', 0),
(21,'FGTS S/ FOLHA DE PAGAMENTO',true, "insert", '2024-05-26', 0),
(22,'FOLHA DE PAGAMENTO DO MES',true, "insert", '2024-05-26', 0),
(23,'PRO-LABORE DO MES',true, "insert", '2024-05-26', 0),
(24,'ADIANTAMENTO',true, "insert", '2024-05-26', 0),
(25,'ADIANT. DE SALARIO A FUNCION.',true, "insert", '2024-05-26', 0),
(26,'SALDO DE SALARIO',true, "insert", '2024-05-26', 0),
(27,'FGTS MULTA RESCISAO',true, "insert", '2024-05-26', 0),
(28,'FERIAS PROPORCIONAIS',true, "insert", '2024-05-26', 0),
(29,'LIQUIDO RESCISAO',true, "insert", '2024-05-26', 0),
(30,'13. SALARIO',true, "insert", '2024-05-26', 0),
(31,'PAGTO IMPOSTOS E TAXAS',true, "insert", '2024-05-26', 0),
(32,'DEPOSITO EFETUADO NO MES',true, "insert", '2024-05-26', 0),
(33,'DUPLIC. RECEBIDAS E CREDITADAS',true, "insert", '2024-05-26', 0),
(34,'JUROS REC. N/ REC. DUPL. CRED.',true, "insert", '2024-05-26', 0),
(35,'APLICACOES EFETUADAS CF',true, "insert", '2024-05-26', 0),
(36,'RESGATE DAS APLICACOES CF.',true, "insert", '2024-05-26', 0),
(37,'RENDIMENTO S/ APLICAÇÕES',true, "insert", '2024-05-26', 0),
(38,'DESP.BANCARIAS',true, "insert", '2024-05-26', 0),
(39,'ENCARGOS FINANC. CF. EXTRATOS',true, "insert", '2024-05-26', 0),
(40,'TRANSFERENCIA ENTRE CONTAS',true, "insert", '2024-05-26', 0),
(41,'COMPRA DE MATERIAL DE CONSUMO',true, "insert", '2024-05-26', 0),
(42,'PGTO.PREST.SERVICOS',true, "insert", '2024-05-26', 0),
(43,'ACERTO ADIANT.',true, "insert", '2024-05-26', 0),
(44,'FORNEC.A PAGAR',true, "insert", '2024-05-26', 0),
(45,'PROVISÃO.',true, "insert", '2024-05-26', 0),
(46,'CONTAS DE CONSUMO A PAGAR',true, "insert", '2024-05-26', 0),
(47,'DESCONTOS CONCEDIDOS',true, "insert", '2024-05-26', 0),
(48,'RECEITAS PROVISIONADAS',true, "insert", '2024-05-26', 0),
(49,'PAGTO JUROS/MULTAS',true, "insert", '2024-05-26', 0);

create table if not exists CONTABIL (
        OID_LANCAMENTO bigint not null auto_increment,
        OID_LOTE bigint not null,
        ANO_BASE integer,
        DT_OPERACAO date,
        HISTORICO varchar(255),
        CONTA varchar(255),
        NM_CONTA varchar(255),
        VALOR decimal(38,2),
        NATUREZA enum ('D','C'),
        DT_LANCAMENTO date,
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_LANCAMENTO)
);

create table if not exists BANCO (
        OID_BANCO bigint not null auto_increment,
        SALDO_INICIAL decimal(38,2),
        SALDO decimal(38,2),
        NM_BANCO varchar(255),
        NR_AGENCIA varchar(255),
        NR_CONTA varchar(255),
        DS_USU_ALTER varchar(30) not null,
        DT_ULT_ALTER date not null,
        VS_VERSAO bigint not null,
        primary key (OID_BANCO)
);

insert into BANCO values
(1, 20.000 , 20.000, 'Bradesco', '1890', '868-0', 'insert', '2024-08-02', 0),
(2, 5.000 , 5.000, 'Santander', '348', '19434-1', 'insert', '2024-08-02', 0);