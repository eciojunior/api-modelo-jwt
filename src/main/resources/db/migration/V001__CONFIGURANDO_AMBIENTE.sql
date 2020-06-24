create table public.user (
   id SERIAL PRIMARY KEY,
   name VARCHAR not null,
   email VARCHAR not null,
   cel varchar,
   password varchar  not null,
   wallet varchar not null,
   available boolean not null default false,
   authority VARCHAR not null default  'User',
   indicate int,
   CONSTRAINT unq_email UNIQUE(email)
);
