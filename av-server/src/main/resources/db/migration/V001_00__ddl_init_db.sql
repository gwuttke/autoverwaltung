    drop table if exists Auto;

    drop table if exists Benutzer;

    drop table if exists Benzinart;

    drop table if exists Laenderorte;

    drop table if exists Land;

    drop table if exists Ort;

    drop table if exists Tank;

    drop table if exists Tanken;

    drop table if exists Version;

    drop table if exists auto_benutzer;

    drop table if exists auto_benzinart;

    drop table if exists sonstigeausgaben;

    create table Auto (
        id integer not null auto_increment,
        erstzulassung datetime,
        kauf datetime,
        kfz varchar(255),
        kmaktuell integer,
        kmkauf integer,
        benutzer_id integer,
        primary key (id)
    );

    create table Benutzer (
        id integer not null auto_increment,
        benutzername varchar(255),
        email varchar(255),
        name varchar(255),
        passwort varchar(255),
        vorname varchar(255),
        primary key (id)
    );

    create table Benzinart (
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

    create table auto_benutzer (
        idbenutzer integer not null,
        idauto integer not null
    );

    create table auto_benzinart (
        idauto integer not null,
        idbenzinart integer not null,
        primary key (idauto, idbenzinart)
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

    alter table Benutzer 
        add constraint ui_name_passwort  unique (benutzername, passwort);

    alter table Benutzer 
        add constraint ui_name_email_vorname  unique (name, vorname, email);

    alter table Benutzer 
        add constraint UK_t9bp7irfyjjjldki65t589vuc  unique (email);

    alter table Laenderorte 
        add constraint UK_nylnxam3kf3wx3g6dtdsb3gwk  unique (idort);

    alter table Auto 
        add constraint FK_hyuc6nhyn11xs2k9mlxpn689g 
        foreign key (benutzer_id) 
        references Benutzer (id);

    alter table Laenderorte 
        add constraint FK_nylnxam3kf3wx3g6dtdsb3gwk 
        foreign key (idort) 
        references Ort (id);

    alter table Laenderorte 
        add constraint FK_7vte171wm2mjeax3nvd3uh3s6 
        foreign key (idland) 
        references Land (id);

    alter table Tanken 
        add constraint FK_ocqfvjobcxmdf58bcpplg15p2 
        foreign key (auto_id) 
        references Auto (id);

    alter table Tanken 
        add constraint FK_5n03kc2dmw9q7ga82ig06vmw 
        foreign key (benzinart_id) 
        references Benzinart (id);

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

    alter table auto_benutzer 
        add constraint FK_p68y3dy4x3dxrlimyy4967hj 
        foreign key (idauto) 
        references Auto (id);

    alter table auto_benutzer 
        add constraint FK_6o0i1ecjuwkaaucdoe643qapp 
        foreign key (idbenutzer) 
        references Benutzer (id);

    alter table auto_benzinart 
        add constraint FK_m60fjwg1i5q7da07g1i03vgat 
        foreign key (idbenzinart) 
        references Benzinart (id);

    alter table auto_benzinart 
        add constraint FK_hqwe92pppt2t3v4c1crkch6li 
        foreign key (idauto) 
        references Auto (id);

    alter table sonstigeausgaben 
        add constraint FK_eaioye1hv31v8ku65dqufsuc2 
        foreign key (auto_id) 
        references Auto (id);
