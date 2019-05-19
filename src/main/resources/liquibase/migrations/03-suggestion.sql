--changeset miliaev:supernova-17-suggestion-table
create table suggestion
(
  id                bigserial primary key,
  session_id        bigint                   not null,
  suggestion_offset bigint                   not null,
  event_ids         bigint[],
  created           timestamp with time zone not null default now()
);
