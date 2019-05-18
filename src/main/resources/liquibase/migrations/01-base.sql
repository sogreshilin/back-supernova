--liquibase formatted sql

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
  author_id     bigint                   not null references person,
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
  primary key (event_id, person_id)
);

--changeset sogreshilin:supernova-01-event-image-table
create table event_image
(
  event_id  bigint not null references event,
  image_url text   not null
);

--changeset miliaev:supernova-04-person-favourite-types-table
create table person_favourite_types
(
  person_id bigint not null references person,
  type      text   not null
);

--changeset miliaev:supernova-05-event-person-likes-table
create table person_event_likes
(
  event_id  bigint references event,
  person_id bigint references person,
  primary key (event_id, person_id)
);
