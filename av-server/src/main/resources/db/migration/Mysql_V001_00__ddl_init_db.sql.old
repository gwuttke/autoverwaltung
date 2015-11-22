    drop table if exists auto;

    drop table if exists auto_benutzer;

    drop table if exists autoritaet;

    drop table if exists benutzer;

    drop table if exists kraftstoff;

    drop table if exists kraftstoffsorte;

    drop table if exists laenderorte;

    drop table if exists land;

    drop table if exists ort;

    drop table if exists role;

    drop table if exists role_autoritaet;

    drop table if exists tank;

    drop table if exists tanken;

    drop table if exists version;

    drop table if exists benutzer_auto;

    drop table if exists benutzer_role;

    drop table if exists kraftstoff_kraftstoffsorte;

    drop table if exists sonstigeausgaben;

    create table auto (
        id integer not null auto_increment,
        erstzulassung datetime,
        kauf datetime,
        kfz varchar(255),
        kmaktuell integer,
        kmkauf integer,
        kmstart integer,
        kraftstoff_id integer,
        primary key (id)
    );

    create table auto_benutzer (
        Auto_id integer not null,
        users_id integer not null
    );

    create table autoritaet (
        id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    );

    create table benutzer (
        id integer not null auto_increment,
        benutzername varchar(255),
        email varchar(255),
        name varchar(255),
        passwort varchar(255),
        vorname varchar(255),
        currentautoid integer,
        primary key (id)
    );

    create table kraftstoff (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table kraftstoffsorte (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table laenderorte (
        idland integer not null,
        idort integer not null,
        primary key (idland, idort)
    );

    create table land (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table ort (
        id integer not null auto_increment,
        ort varchar(255),
        primary key (id)
    );

    create table role (
        id double precision not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table role_autoritaet (
        Role_id double precision not null,
        autoritaet_id integer not null
    );

    create table tank (
        id integer not null auto_increment,
        beschreibung varchar(255),
        primary key (id)
    );

    create table tanken (
        id integer not null auto_increment,
        datum datetime,
        kmstand integer,
        kosten decimal(19,2),
        liter decimal(19,2),
        preisproliter decimal(10,3),
        auto_id integer,
        benzinart_id integer,
        land_id integer,
        ort_id integer,
        tank_id integer,
        primary key (id)
    );

    create table version (
        id integer not null auto_increment,
        nummer integer not null,
        plattform varchar(255),
        primary key (id)
    );

    create table benutzer_auto (
        Benutzer_id integer not null,
        autos_id integer not null
    );

    create table benutzer_role (
        Benutzer_id integer not null,
        roles_id double precision not null
    );

    create table kraftstoff_kraftstoffsorte (
        Kraftstoff_id integer not null,
        kraftstoffsorten_id integer not null,
        primary key (Kraftstoff_id, kraftstoffsorten_id)
    );

    create table sonstigeausgaben (
        id integer not null auto_increment,
        datum date,
        kmstand integer,
        kommentar varchar(255),
        kosten decimal(19,2),
        auto_id integer,
        primary key (id)
    );

    alter table auto 
        add constraint UK_jhb5bayjw7ry9omwtt21k4v91  unique (kfz);

    alter table autoritaet 
        add constraint UK_3h2vqx7bbefeabr9dqpt8ef1y  unique (name);

    alter table benutzer 
        add constraint ui_name_passwort  unique (benutzername, passwort);

    alter table benutzer 
        add constraint ui_name_email_vorname  unique (name, vorname, email);

    alter table benutzer 
        add constraint UK_t9bp7irfyjjjldki65t589vuc  unique (email);

    alter table laenderorte 
        add constraint UK_nylnxam3kf3wx3g6dtdsb3gwk  unique (idort);

    alter table role_autoritaet 
        add constraint UK_ibwuqqqjgroadqyl2qrewxljk  unique (autoritaet_id);

    alter table benutzer_role 
        add constraint UK_n0yvy2q77ohdig0gwp7idvb7x  unique (roles_id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint UK_8jhnom7tppa7m6864ivtxmrle  unique (kraftstoffsorten_id);

    alter table auto 
        add constraint FK_cbtv9niidsum4gm1avv09puse 
        foreign key (kraftstoff_id) 
        references kraftstoff (id);

    alter table auto_benutzer 
        add constraint FK_2t6gyyx2hcsdapqdmq7fdwiuj 
        foreign key (users_id) 
        references benutzer (id);

    alter table auto_benutzer 
        add constraint FK_ii0vch2wqan0516dbns7q916v 
        foreign key (auto_id) 
        references auto (id);

    alter table benutzer 
        add constraint FK_pgi2ycepxoecplfx8lug3dxe9 
        foreign key (currentautoid) 
        references auto (id);

    alter table laenderorte 
        add constraint FK_nylnxam3kf3wx3g6dtdsb3gwk 
        foreign key (idort) 
        references ort (id);

    alter table laenderorte 
        add constraint FK_7vte171wm2mjeax3nvd3uh3s6 
        foreign key (idland) 
        references land (id);

    alter table role_autoritaet 
        add constraint FK_ibwuqqqjgroadqyl2qrewxljk 
        foreign key (autoritaet_id) 
        references autoritaet (id);

    alter table role_autoritaet 
        add constraint FK_crp36hl4y3h81vw42bnu5mayn 
        foreign key (role_id) 
        references role (id);

    alter table tanken 
        add constraint FK_ocqfvjobcxmdf58bcpplg15p2 
        foreign key (auto_id) 
        references auto (id);

    alter table tanken 
        add constraint FK_5n03kc2dmw9q7ga82ig06vmw 
        foreign key (benzinart_id) 
        references kraftstoffsorte (id);

    alter table tanken 
        add constraint FK_fxbrofpqod3ticax5yl08gr4p 
        foreign key (land_id) 
        references land (id);

    alter table tanken 
        add constraint FK_g4wuu74alnaqunxnugr9fcgo0 
        foreign key (ort_id) 
        references ort (id);

    alter table tanken 
        add constraint FK_bj6fca1xx6tc6c2sqf8ocbc1 
        foreign key (tank_id) 
        references tank (id);

    alter table benutzer_auto 
        add constraint FK_8lkl6gcxxn6cuk9h3m06yh3hf 
        foreign key (autos_id) 
        references auto (id);

    alter table benutzer_auto 
        add constraint FK_lkysk249xp47h65f9gw4wydle 
        foreign key (benutzer_id) 
        references benutzer (id);

    alter table benutzer_role 
        add constraint FK_n0yvy2q77ohdig0gwp7idvb7x 
        foreign key (roles_id) 
        references role (id);

    alter table benutzer_role 
        add constraint FK_nixxvwdnga9p18gn6i7g1qd6k 
        foreign key (benutzer_id) 
        references benutzer (id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_8jhnom7tppa7m6864ivtxmrle 
        foreign key (kraftstoffsorten_id) 
        references kraftstoffsorte (id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_oku0nf0tqrehs4itbo1ensirj 
        foreign key (kraftstoff_id) 
        references kraftstoff (id);

    alter table sonstigeausgaben 
        add constraint FK_eaioye1hv31v8ku65dqufsuc2 
        foreign key (auto_id) 
        references auto (id);
