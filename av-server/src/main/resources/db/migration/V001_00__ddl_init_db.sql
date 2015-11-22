    drop table if exists Auto cascade;

    drop table if exists Autoritaet cascade;

    drop table if exists Benutzer cascade;

    drop table if exists Kraftstoff cascade;

    drop table if exists Kraftstoffsorte cascade;

    drop table if exists Laenderorte cascade;

    drop table if exists Land cascade;

    drop table if exists Ort cascade;

    drop table if exists Role cascade;

    drop table if exists Role_Autoritaet cascade;

    drop table if exists Tank cascade;

    drop table if exists Tanken cascade;

    drop table if exists Version cascade;

    drop table if exists auto_benutzer cascade;

    drop table if exists benutzer_auto cascade;

    drop table if exists benutzer_role cascade;

    drop table if exists kraftstoff_kraftstoffsorte cascade;

    drop table if exists sonstigeausgaben cascade;

    create table Auto (
        id int4 not null,
        erstzulassung timestamp,
        kauf timestamp,
        kfz varchar(255),
        kmaktuell int4,
        kmkauf int4,
        kmstart int4,
        kraftstoff_id int4,
        primary key (id)
    );

    create table Autoritaet (
        id int4 not null,
        name varchar(255) not null,
        primary key (id)
    );

    create table Benutzer (
        id int4 not null,
        benutzername varchar(255),
        email varchar(255),
        name varchar(255),
        passwort varchar(255),
        vorname varchar(255),
        currentautoid int4,
        primary key (id)
    );

    create table Kraftstoff (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table Kraftstoffsorte (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table Laenderorte (
        idland int4 not null,
        idort int4 not null,
        primary key (idland, idort)
    );

    create table Land (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table Ort (
        id int4 not null,
        ort varchar(255),
        primary key (id)
    );

    create table Role (
        id float8 not null,
        name varchar(255),
        primary key (id)
    );

    create table Role_Autoritaet (
        Role_id float8 not null,
        autoritaet_id int4 not null
    );

    create table Tank (
        id int4 not null,
        beschreibung varchar(255),
        primary key (id)
    );

    create table Tanken (
        id int4 not null,
        datum timestamp,
        kmstand int4,
        kosten numeric(19, 2),
        liter numeric(19, 2),
        preisproliter numeric(10, 3),
        auto_id int4,
        benzinart_id int4,
        land_id int4,
        ort_id int4,
        tank_id int4,
        primary key (id)
    );

    create table Version (
        id int4 not null,
        nummer int4 not null,
        plattform varchar(255),
        primary key (id)
    );

    create table auto_benutzer (
        Auto_id int4 not null,
        users_id int4 not null
    );

    create table benutzer_auto (
        Benutzer_id int4 not null,
        autos_id int4 not null
    );

    create table benutzer_role (
        Benutzer_id int4 not null,
        roles_id float8 not null
    );

    create table kraftstoff_kraftstoffsorte (
        Kraftstoff_id int4 not null,
        kraftstoffsorten_id int4 not null,
        primary key (Kraftstoff_id, kraftstoffsorten_id)
    );

    create table sonstigeausgaben (
        id int4 not null,
        datum date,
        kmstand int4,
        kommentar varchar(255),
        kosten numeric(19, 2),
        auto_id int4,
        primary key (id)
    );

    alter table Auto 
        add constraint UK_jhb5bayjw7ry9omwtt21k4v91  unique (kfz);

    alter table Autoritaet 
        add constraint UK_3h2vqx7bbefeabr9dqpt8ef1y  unique (name);

    alter table Benutzer 
        add constraint ui_name_passwort  unique (benutzername, passwort);

    alter table Benutzer 
        add constraint ui_name_email_vorname  unique (name, vorname, email);

    alter table Benutzer 
        add constraint UK_t9bp7irfyjjjldki65t589vuc  unique (email);

    alter table Laenderorte 
        add constraint UK_nylnxam3kf3wx3g6dtdsb3gwk  unique (idort);

    alter table Role_Autoritaet 
        add constraint UK_ibwuqqqjgroadqyl2qrewxljk  unique (autoritaet_id);

    alter table benutzer_role 
        add constraint UK_n0yvy2q77ohdig0gwp7idvb7x  unique (roles_id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint UK_8jhnom7tppa7m6864ivtxmrle  unique (kraftstoffsorten_id);

    alter table Auto 
        add constraint FK_cbtv9niidsum4gm1avv09puse 
        foreign key (kraftstoff_id) 
        references Kraftstoff;

    alter table Benutzer 
        add constraint FK_pgi2ycepxoecplfx8lug3dxe9 
        foreign key (currentautoid) 
        references Auto;

    alter table Laenderorte 
        add constraint FK_nylnxam3kf3wx3g6dtdsb3gwk 
        foreign key (idort) 
        references Ort;

    alter table Laenderorte 
        add constraint FK_7vte171wm2mjeax3nvd3uh3s6 
        foreign key (idland) 
        references Land;

    alter table Role_Autoritaet 
        add constraint FK_ibwuqqqjgroadqyl2qrewxljk 
        foreign key (autoritaet_id) 
        references Autoritaet;

    alter table Role_Autoritaet 
        add constraint FK_crp36hl4y3h81vw42bnu5mayn 
        foreign key (Role_id) 
        references Role;

    alter table Tanken 
        add constraint FK_ocqfvjobcxmdf58bcpplg15p2 
        foreign key (auto_id) 
        references Auto;

    alter table Tanken 
        add constraint FK_5n03kc2dmw9q7ga82ig06vmw 
        foreign key (benzinart_id) 
        references Kraftstoffsorte;

    alter table Tanken 
        add constraint FK_fxbrofpqod3ticax5yl08gr4p 
        foreign key (land_id) 
        references Land;

    alter table Tanken 
        add constraint FK_g4wuu74alnaqunxnugr9fcgo0 
        foreign key (ort_id) 
        references Ort;

    alter table Tanken 
        add constraint FK_bj6fca1xx6tc6c2sqf8ocbc1 
        foreign key (tank_id) 
        references Tank;

    alter table auto_benutzer 
        add constraint FK_s8hngex4y8yk4kd6qo2k6yh3n 
        foreign key (users_id) 
        references Benutzer;

    alter table auto_benutzer 
        add constraint FK_ny5g09vai5fwsxmmw6frf09e2 
        foreign key (Auto_id) 
        references Auto;

    alter table benutzer_auto 
        add constraint FK_8lkl6gcxxn6cuk9h3m06yh3hf 
        foreign key (autos_id) 
        references Auto;

    alter table benutzer_auto 
        add constraint FK_lkysk249xp47h65f9gw4wydle 
        foreign key (Benutzer_id) 
        references Benutzer;

    alter table benutzer_role 
        add constraint FK_n0yvy2q77ohdig0gwp7idvb7x 
        foreign key (roles_id) 
        references Role;

    alter table benutzer_role 
        add constraint FK_nixxvwdnga9p18gn6i7g1qd6k 
        foreign key (Benutzer_id) 
        references Benutzer;

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_8jhnom7tppa7m6864ivtxmrle 
        foreign key (kraftstoffsorten_id) 
        references Kraftstoffsorte;

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_oku0nf0tqrehs4itbo1ensirj 
        foreign key (Kraftstoff_id) 
        references Kraftstoff;

    alter table sonstigeausgaben 
        add constraint FK_eaioye1hv31v8ku65dqufsuc2 
        foreign key (auto_id) 
        references Auto;

    create sequence auto_id_seq;

    create sequence hibernate_sequence;

    create sequence kraftstoff_id_seq;

    create sequence kraftstoffsorte_id_seq;

    create sequence land_id_seq;

    create sequence ort_id_seq;

    create sequence role_id_seq;

    create sequence sonstigeAusgaben_id_seq;

    create sequence tank_id_seq;

    create sequence tanken_id_seq;

    create sequence version_id_seq;
