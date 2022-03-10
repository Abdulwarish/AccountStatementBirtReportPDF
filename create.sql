
    create table check_paids (
       id integer not null auto_increment,
        amount double precision,
        check_number varchar(255),
        created_at datetime(6),
        data_paid varchar(255),
        reference_number varchar(255),
        unique_reference_number integer not null,
        primary key (id)
    ) engine=InnoDB
