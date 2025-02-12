create table public.users
(
    id         bigserial
        constraint users_pk
            primary key,
    firstname  varchar(50)                         not null,
    secondname varchar(50)                         not null,
    created    timestamp default CURRENT_TIMESTAMP not null,
    changed    timestamp default CURRENT_TIMESTAMP not null,
    age        integer                             not null
);

alter table public.users
    owner to postgres;
