create table if not exists public.security
(
    id         bigserial
        constraint security_pk
            primary key,
    login      varchar(50) not null,
    password   varchar(50) not null,
    id_user_fk bigint      not null
        constraint users_id_fk
            references public.users
);

alter table public.security
    owner to postgres;
