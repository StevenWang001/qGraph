create database qgraph;

create table vertex(
id bigint primary key,
label varchar(8)
);


create table edge(
id serial primary key,
src_id bigint,
dest_id bigint,
label varchar(8)
);
CREATE INDEX edge_idx_src_id ON edge(src_id);
CREATE INDEX edge_idx_dest_id ON edge(dest_id);

create table vertex_properties(
id serial primary key,
property varchar(8)
);

create table edge_properties(
id serial primary key,
property varchar(8)
);
