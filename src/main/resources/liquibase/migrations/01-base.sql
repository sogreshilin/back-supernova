--liquibase formatted sql

--changeset sogreshilin:supernova-01-creator-table
create table creator
(
  id   bigserial primary key,
  name text not null,
  type text not null
);

--changeset sogreshilin:supernova-01-person-table
create table person
(
  id         bigserial primary key,
  vk_id      text not null,
  last_name  text not null,
  first_name text not null
);

create table location
(
  id        bigserial primary key,
  latitude  double precision,
  longitude double precision,
  city      text not null,
  address   text not null
);

--changeset sogreshilin:supernova-01-event-table
create table event
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
create table event_type
(
  event_id bigint not null references event,
  type     text   not null,
  unique (event_id, type)
);

--changeset sogreshilin:supernova-01-event-member-table
create table event_person
(
  event_id  bigint references event,
  person_id bigint references person,
  weight    double precision not null,
  primary key (event_id, person_id)
);

comment on column event_person.weight is 'Насколько данному пользователю подходит данное событие. Используется в рекомендательной системе';

--changeset sogreshilin:supernova-01-event-image-table
create table event_image
(
  event_id  bigint not null references event,
  image_url text   not null
);

--changeset miliaev:supernova-04-person-favourites-table
create table person_favourites
(
  person_id bigint not null references person,
  type      text   not null
);
