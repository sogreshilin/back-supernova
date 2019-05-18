--liquibase formatted sql

--changeset sogreshilin:supernova-01-creator-table
create table if not exists creator
(
  id   bigserial primary key,
  name text not null,
  type text not null
);

--changeset sogreshilin:supernova-01-person-table
create table if not exists person
(
  id         bigint primary key,
  vk_id      bigint,
  last_name  text not null,
  first_name text not null,
  city       text not null
);

create table if not exists location
(
  id        bigserial primary key,
  latitude  double precision,
  longitude double precision,
  city      text not null,
  address   text not null
);

--changeset sogreshilin:supernova-01-event-table
create table if not exists event
(
  id            bigserial primary key,
  title         text                     not null,
  description   text                     not null,
  creator_id    bigint                   not null references creator,
  rating        double precision,
  location_id   bigint references location,
  from_datetime timestamp with time zone not null,
  to_datetime   timestamp with time zone not null,
  site_url      text,
  phone         text,
  email         text
);

--changeset sogreshilin:supernova-01-event-type-table
create table if not exists event_type
(
  event_id bigint not null references event,
  type     text   not null
);

--changeset sogreshilin:supernova-01-event-member-table
create table if not exists event_person
(
  event_id  bigint references event,
  person_id bigint references person,
  weight    double precision not null,
  primary key (event_id, person_id)
);

comment on column event_person.weight is 'Насколько данному пользователю подходит данное событие. Используется в рекомендательной системе';

--changeset sogreshilin:supernova-01-event-image-table
create table if not exists event_image
(
  event_id  bigint not null references event,
  image_url text   not null
);
