drop schema riabooksdb;
create schema RiaBooksDB;
use RiaBooksDB;

create table Utente (
	email varchar(320) primary key not null,
    nome varchar (50) not null,
    cognome varchar(50) not null,
    pass_hash char(124) not null,
    is_admin boolean default false not null -- false = utente normale, true = admin
);

create table Libro (
	id varchar(13) primary key not null,
    titolo varchar(50) not null,
    autore varchar(50) not null,
    prezzo decimal(10,2) not null,
    descrizione varchar (500),
    categoria varchar(50) not null,
    immagine mediumblob DEFAULT NULL,
    mostra boolean default true
);

create table Carrello (
	email varchar(320) not null,
    foreign key (email) references Utente(email) on delete cascade on update cascade
);

create table Contiene (
	email varchar(320) not null,
    libro_id varchar(13) not null,
    foreign key (email) references Utente(email) on delete cascade on update cascade,
	foreign key (libro_id) references Libro(id) on update cascade on delete cascade 
    );
    
    
create table Ordine (
	id int auto_increment primary key,
    importo decimal(10,2) not null,
    metodo varchar(20),
    indirizzo varchar(100) not null,
    email varchar(320) not null,
    data_emissione DATE not null,
    foreign key (email) references Utente(email) on delete cascade on update cascade
);

create table riferisce (
	ordine_id int not null,
    libro_id varchar(13) not null,
    quantita int not null,
	prezzo decimal(10,2) not null,
    foreign key (libro_id) references Libro(id) on update cascade on delete cascade,
    foreign key (ordine_id) references Ordine(id) on update cascade on delete cascade
);

