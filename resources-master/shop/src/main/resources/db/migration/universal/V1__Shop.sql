create table customer (id integer not null auto_increment, email_address varchar (255), first_name varchar (255), last_name varchar (255), password varchar (255), username varchar (255), primary key (id)) engine = InnoDB ;
create table location (id integer not null auto_increment, address_city varchar (255), address_country varchar (255), address_county varchar (255), address_street varchar (255), name varchar (255), primary key (id)) engine = InnoDB ;
create table order_detail (quantity integer, orders_id integer not null auto_increment, product_id integer not null, primary key (orders_id, product_id)) engine = InnoDB ;
create table orders (id integer not null auto_increment, address_city varchar (255), address_country varchar (255), address_county varchar (255), address_street varchar (255), created_at datetime (6), customer_id integer, primary key (id)) engine = InnoDB ;
create table product (id integer not null auto_increment, description varchar (255), image_url varchar (255), name varchar (255), price decimal (19, 2), weight double precision, category_id integer, supplier_id integer, primary key (id)) engine = InnoDB ;
create table product_category (id integer not null auto_increment, description varchar (255), name varchar (255), primary key (id)) engine = InnoDB ;
create table revenue (id integer not null auto_increment, date date, sum decimal (19, 2), location_id integer, primary key (id)) engine = InnoDB ;
create table stock (quantity integer, location_id integer not null auto_increment, product_id integer not null, primary key (location_id, product_id)) engine = InnoDB ;
create table supplier (id integer not null auto_increment, name varchar (255), primary key (id)) engine = InnoDB ;

alter table order_detail add constraint FK7xf2gmq3yok90kilflnu8aa7e foreign key (orders_id) references orders (id) ;
alter table order_detail add constraint FKb8bg2bkty0oksa3wiq5mp5qnc foreign key (product_id) references product (id) ;
alter table orders add constraint FK624gtjin3po807j3vix093tlf foreign key (customer_id) references customer (id) ;
alter table product add constraint FK5cypb0k23bovo3rn1a5jqs6j4 foreign key (category_id) references product_category (id) ;
alter table product add constraint FK2kxvbr72tmtscjvyp9yqb12by foreign key (supplier_id) references supplier (id) ;
alter table revenue add constraint FK6xukepd8ssa1ok6iakkhp83p7 foreign key (location_id) references location (id) ;
alter table stock add constraint FK6t3m0kaf6fubuus331gf7xmn8 foreign key (location_id) references location (id) ;
alter table stock add constraint FKjghkvw2snnsr5gpct0of7xfcf foreign key (product_id) references product (id) ;

alter table order_detail add column shipped_from_id integer;
alter table order_detail add constraint FK9iremrlr1rfnpck8pljb9dgfs foreign key (shipped_from_id) references location (id) ;