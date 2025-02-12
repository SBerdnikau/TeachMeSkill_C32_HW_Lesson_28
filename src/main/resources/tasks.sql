create table if not exists public.tasks
(
    id          bigint default nextval('task_data_id_task_seq'::regclass) not null
        constraint task_data_pkey
            primary key,
    description varchar                                                   not null,
    created     timestamp                                                 not null,
    changed     timestamp,
    id_user_fk  bigint                                                    not null
        constraint users_id_fk
            references public.users
);

alter table public.tasks
    owner to postgres;