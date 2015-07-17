    drop table if exists Auto;

    drop table if exists Auto_Benutzer;

    drop table if exists Autoritaet;

    drop table if exists Benutzer;

    drop table if exists Kraftstoff;

    drop table if exists Kraftstoffsorte;

    drop table if exists Laenderorte;

    drop table if exists Land;

    drop table if exists Ort;

    drop table if exists Role;

    drop table if exists Role_Autoritaet;

    drop table if exists Tank;

    drop table if exists Tanken;

    drop table if exists Version;

    drop table if exists benutzer_auto;

    drop table if exists benutzer_role;

    drop table if exists kraftstoff_kraftstoffsorte;

    drop table if exists sonstigeausgaben;

    create table Auto (
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

    create table Auto_Benutzer (
        Auto_id integer not null,
        users_id integer not null
    );

    create table Autoritaet (
        id integer not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    );

    create table Benutzer (
        id integer not null auto_increment,
        benutzername varchar(255),
        email varchar(255),
        name varchar(255),
        passwort varchar(255),
        vorname varchar(255),
        currentautoid integer,
        primary key (id)
    );

    create table Kraftstoff (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table Kraftstoffsorte (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table Laenderorte (
        idland integer not null,
        idort integer not null,
        primary key (idland, idort)
    );

    create table Land (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table Ort (
        id integer not null auto_increment,
        ort varchar(255),
        primary key (id)
    );

    create table Role (
        id double precision not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table Role_Autoritaet (
        Role_id double precision not null,
        autoritaet_id integer not null
    );

    create table Tank (
        id integer not null auto_increment,
        beschreibung varchar(255),
        primary key (id)
    );

    create table Tanken (
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

    create table Version (
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
        references Kraftstoff (id);

    alter table Auto_Benutzer 
        add constraint FK_2t6gyyx2hcsdapqdmq7fdwiuj 
        foreign key (users_id) 
        references Benutzer (id);

    alter table Auto_Benutzer 
        add constraint FK_ii0vch2wqan0516dbns7q916v 
        foreign key (Auto_id) 
        references Auto (id);

    alter table Benutzer 
        add constraint FK_pgi2ycepxoecplfx8lug3dxe9 
        foreign key (currentautoid) 
        references Auto (id);

    alter table Laenderorte 
        add constraint FK_nylnxam3kf3wx3g6dtdsb3gwk 
        foreign key (idort) 
        references Ort (id);

    alter table Laenderorte 
        add constraint FK_7vte171wm2mjeax3nvd3uh3s6 
        foreign key (idland) 
        references Land (id);

    alter table Role_Autoritaet 
        add constraint FK_ibwuqqqjgroadqyl2qrewxljk 
        foreign key (autoritaet_id) 
        references Autoritaet (id);

    alter table Role_Autoritaet 
        add constraint FK_crp36hl4y3h81vw42bnu5mayn 
        foreign key (Role_id) 
        references Role (id);

    alter table Tanken 
        add constraint FK_ocqfvjobcxmdf58bcpplg15p2 
        foreign key (auto_id) 
        references Auto (id);

    alter table Tanken 
        add constraint FK_5n03kc2dmw9q7ga82ig06vmw 
        foreign key (benzinart_id) 
        references Kraftstoffsorte (id);

    alter table Tanken 
        add constraint FK_fxbrofpqod3ticax5yl08gr4p 
        foreign key (land_id) 
        references Land (id);

    alter table Tanken 
        add constraint FK_g4wuu74alnaqunxnugr9fcgo0 
        foreign key (ort_id) 
        references Ort (id);

    alter table Tanken 
        add constraint FK_bj6fca1xx6tc6c2sqf8ocbc1 
        foreign key (tank_id) 
        references Tank (id);

    alter table benutzer_auto 
        add constraint FK_8lkl6gcxxn6cuk9h3m06yh3hf 
        foreign key (autos_id) 
        references Auto (id);

    alter table benutzer_auto 
        add constraint FK_lkysk249xp47h65f9gw4wydle 
        foreign key (Benutzer_id) 
        references Benutzer (id);

    alter table benutzer_role 
        add constraint FK_n0yvy2q77ohdig0gwp7idvb7x 
        foreign key (roles_id) 
        references Role (id);

    alter table benutzer_role 
        add constraint FK_nixxvwdnga9p18gn6i7g1qd6k 
        foreign key (Benutzer_id) 
        references Benutzer (id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_8jhnom7tppa7m6864ivtxmrle 
        foreign key (kraftstoffsorten_id) 
        references Kraftstoffsorte (id);

    alter table kraftstoff_kraftstoffsorte 
        add constraint FK_oku0nf0tqrehs4itbo1ensirj 
        foreign key (Kraftstoff_id) 
        references Kraftstoff (id);

    alter table sonstigeausgaben 
        add constraint FK_eaioye1hv31v8ku65dqufsuc2 
        foreign key (auto_id) 
        references Auto (id);
