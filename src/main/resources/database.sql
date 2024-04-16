create schema phone_catalog;

create table phone_catalog.contacts
(
    id     int auto_increment,
    name   varchar(255) null,
    number varchar(15)  not null,
    constraint contacts_pk
        primary key (id)
);
