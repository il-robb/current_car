
    create table alimentazione (
        id_alimentazione integer not null,
        descrizione varchar(255) not null,
        primary key (id_alimentazione)
    ) engine=InnoDB;

    create table bici (
        id_bici integer not null auto_increment,
        id_veicolo integer,
        numero_marce integer not null,
        pieghevole bit not null,
        tipo_sospensione integer,
        primary key (id_bici)
    ) engine=InnoDB;

    create table categoria (
        id_categoria integer not null,
        descrizione varchar(255) not null,
        primary key (id_categoria)
    ) engine=InnoDB;

    create table colore (
        id_colore integer not null auto_increment,
        descrizione varchar(255) not null,
        primary key (id_colore)
    ) engine=InnoDB;

    create table macchina (
        cilindrata integer not null,
        id_macchina integer not null auto_increment,
        id_veicolo integer,
        numero_porte integer not null,
        targa varchar(255) not null,
        primary key (id_macchina)
    ) engine=InnoDB;

    create table marca (
        id_marca integer not null auto_increment,
        descrizione varchar(255) not null,
        primary key (id_marca)
    ) engine=InnoDB;

    create table moto (
        cilindrata integer not null,
        id_moto integer not null auto_increment,
        id_veicolo integer,
        targa varchar(255) not null,
        primary key (id_moto)
    ) engine=InnoDB;

    create table sospensione (
        id_sospensione integer not null auto_increment,
        descrizione varchar(255) not null,
        primary key (id_sospensione)
    ) engine=InnoDB;

    create table tipo_veicolo (
        id_tipo_veicolo integer not null auto_increment,
        descrizione varchar(255) not null,
        pattern varchar(255) not null,
        primary key (id_tipo_veicolo)
    ) engine=InnoDB;

    create table veicolo (
        id_alimentazione integer,
        id_categoria integer,
        id_colore integer,
        id_marca integer,
        id_tipo_veicolo integer,
        id_veicolo integer not null auto_increment,
        numero_ruote integer not null,
        anno_prod varchar(100) not null,
        modello varchar(100) not null,
        primary key (id_veicolo)
    ) engine=InnoDB;

    alter table bici 
       add constraint UK3e7ndancssysy5dhd5kmyr9sj unique (id_veicolo);

    alter table macchina 
       add constraint UKjr2kmttt6uv2ccoqox0hptm35 unique (id_veicolo);

    alter table macchina 
       add constraint UKadlvjltj5y5k8d7jh9dgla7hr unique (targa);

    alter table moto 
       add constraint UKj4rnimgksqb3jslhw7nsgoayp unique (id_veicolo);

    alter table moto 
       add constraint UK2i3efrjuh6fy8isx13wtioxpr unique (targa);

    alter table tipo_veicolo 
       add constraint UKjljq7sysqqwjv5bpculhax60h unique (pattern);

    alter table bici 
       add constraint FK5g19r1b6qttfo2sry68x6et96 
       foreign key (tipo_sospensione) 
       references sospensione (id_sospensione);

    alter table bici 
       add constraint FKswktcdeh5uuwbhf8ducphchxo 
       foreign key (id_veicolo) 
       references veicolo (id_veicolo);

    alter table macchina 
       add constraint FKktm44xfs8hvv5evg3gdfq50u3 
       foreign key (id_veicolo) 
       references veicolo (id_veicolo);

    alter table moto 
       add constraint FK45syifcllae09quumt3uhcckk 
       foreign key (id_veicolo) 
       references veicolo (id_veicolo);

    alter table veicolo 
       add constraint FKmulkx4clibsa9a7v0hkv33yq2 
       foreign key (id_alimentazione) 
       references alimentazione (id_alimentazione);

    alter table veicolo 
       add constraint FKadjynej4kplqqaia8h3t3w5m7 
       foreign key (id_categoria) 
       references categoria (id_categoria);

    alter table veicolo 
       add constraint FK1c8wuj83gmqgnjcyerakq2vge 
       foreign key (id_colore) 
       references colore (id_colore);

    alter table veicolo 
       add constraint FKl2uxral8sefwlju24c4sx1fmw 
       foreign key (id_marca) 
       references marca (id_marca);

    alter table veicolo 
       add constraint FK8anvnj7a7qu0jfikivb1qi4g 
       foreign key (id_tipo_veicolo) 
       references tipo_veicolo (id_tipo_veicolo);
