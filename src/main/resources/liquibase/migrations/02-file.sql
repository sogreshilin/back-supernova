--changeset sogreshilin:supernova-08-uploaded-file-table
create table uploaded_file
(
  id           bigserial primary key,
  name         text                     not null,
  content_type text                     not null,
  created      timestamp with time zone not null default now()
);


--changeset sogreshilin:supernova-08-event-image-table
create table event_image
(
  event_id bigint references event,
  image_id bigint references uploaded_file,
  primary key (event_id, image_id)
);


