
    alter table bici 
       drop 
       foreign key FK5g19r1b6qttfo2sry68x6et96;

    alter table bici 
       drop 
       foreign key FKswktcdeh5uuwbhf8ducphchxo;

    alter table macchina 
       drop 
       foreign key FKktm44xfs8hvv5evg3gdfq50u3;

    alter table moto 
       drop 
       foreign key FK45syifcllae09quumt3uhcckk;

    alter table veicolo 
       drop 
       foreign key FKmulkx4clibsa9a7v0hkv33yq2;

    alter table veicolo 
       drop 
       foreign key FKadjynej4kplqqaia8h3t3w5m7;

    alter table veicolo 
       drop 
       foreign key FK1c8wuj83gmqgnjcyerakq2vge;

    alter table veicolo 
       drop 
       foreign key FKl2uxral8sefwlju24c4sx1fmw;

    alter table veicolo 
       drop 
       foreign key FK8anvnj7a7qu0jfikivb1qi4g;

    drop table if exists alimentazione;

    drop table if exists bici;

    drop table if exists categoria;

    drop table if exists colore;

    drop table if exists macchina;

    drop table if exists marca;

    drop table if exists moto;

    drop table if exists sospensione;

    drop table if exists tipo_veicolo;

    drop table if exists veicolo;
