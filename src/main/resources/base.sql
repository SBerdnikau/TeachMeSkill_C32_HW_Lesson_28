create table if not exists public.security
(
    id       bigserial
        constraint security_pk
            primary key,
    login    varchar(50) not null,
    password varchar(50) not null,
    user_id  bigint      not null
        constraint user_id
            references public.users
            on update cascade on delete cascade
);

alter table public.security
    owner to postgres;

create table if not exists public.tasks
(
    id          bigserial
        constraint task_data_pkey
            primary key,
    description varchar   not null,
    created     timestamp not null,
    changed     timestamp,
    user_id     bigint    not null
        constraint user_id
            references public.users
            on update cascade on delete cascade
);

alter table public.tasks
    owner to postgres;

create table if not exists public.users
(
    id          bigserial
        constraint users_pk
            primary key,
    first_name  varchar(50)                         not null,
    second_name varchar(50)                         not null,
    created     timestamp default CURRENT_TIMESTAMP not null,
    changed     timestamp default CURRENT_TIMESTAMP not null,
    age         integer                             not null
);

alter table public.users
    owner to postgres;