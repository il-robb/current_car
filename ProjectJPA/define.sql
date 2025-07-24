
    create table certificato_medico (
        data_certificato date,
        id integer not null auto_increment,
        socio_id integer,
        tipo_certificato bit,
        primary key (id)
    ) engine=InnoDB;

    create table socio (
        id integer not null auto_increment,
        codice_fiscale varchar(16) not null,
        cognome varchar(100) not null,
        nome varchar(100) not null,
        email varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table certificato_medico 
       add constraint UKfh7x211m11apxus0ygp7io1of unique (socio_id);

    alter table socio 
       add constraint UKtox6grm7ohkl1mrr70yb2fyan unique (codice_fiscale);

    alter table certificato_medico 
       add constraint FKr17u6bpruq0epjt5g760mbe7c 
       foreign key (socio_id) 
       references socio (id);
