create table curator (
  id serial primary key,
  name varchar(150) not null,
  phone_number varchar(50) not null,
  dogs_limit integer not null
);

create table dog (
  id serial primary key,
  sex varchar(10) not null,
  age integer not null,
  breed varchar(150),
  curator_id bigint not null,
  constraint curator_id_fk foreign key (curator_id) references curator(id)
)