# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                            bigint auto_increment not null,
  postal_code                   integer,
  city                          varchar(255),
  rest_of_address               varchar(255),
  constraint pk_address primary key (id)
);

create table book (
  id                            bigint auto_increment not null,
  book_title                    varchar(255),
  price                         integer,
  author                        varchar(255),
  constraint pk_book primary key (id)
);


# --- !Downs

drop table if exists address;

drop table if exists book;

