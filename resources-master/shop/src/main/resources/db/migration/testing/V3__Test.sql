create table shop_test . customer (id integer not null auto_increment, email_address varchar (255), first_name varchar (255), last_name varchar (255), password varchar (255), username varchar (255), primary key (id)) engine = InnoDB ;
create table shop_test . location (id integer not null auto_increment, address_city varchar (255), address_country varchar (255), address_county varchar (255), address_street varchar (255), name varchar (255), primary key (id)) engine = InnoDB ;
create table shop_test . order_detail (quantity integer, orders_id integer not null auto_increment, product_id integer not null, primary key (orders_id, product_id)) engine = InnoDB ;
create table shop_test . orders (id integer not null auto_increment, address_city varchar (255), address_country varchar (255), address_county varchar (255), address_street varchar (255), created_at datetime (6), customer_id integer, primary key (id)) engine = InnoDB ;
create table shop_test . product (id integer not null auto_increment, description varchar (255), image_url varchar (255), name varchar (255), price decimal (19, 2), weight double precision, category_id integer, supplier_id integer, primary key (id)) engine = InnoDB ;
create table shop_test . product_category (id integer not null auto_increment, description varchar (255), name varchar (255), primary key (id)) engine = InnoDB ;
create table shop_test . revenue (id integer not null auto_increment, date date, sum decimal (19, 2), location_id integer, primary key (id)) engine = InnoDB ;
create table shop_test . stock (quantity integer, location_id integer not null auto_increment, product_id integer not null, primary key (location_id, product_id)) engine = InnoDB ;
create table shop_test . supplier (id integer not null auto_increment, name varchar (255), primary key (id)) engine = InnoDB ;

alter table shop_test.order_detail add constraint order_id_order_detail foreign key (orders_id) references orders (id) ;
alter table shop_test.order_detail add constraint product_id_order_detail foreign key (product_id) references product (id) ;
alter table shop_test.orders add constraint customer_id_orders foreign key (customer_id) references customer (id) ;
alter table shop_test.product add constraint category_id_product foreign key (category_id) references product_category (id) ;
alter table shop_test.product add constraint supplier_id_product foreign key (supplier_id) references supplier (id) ;
alter table shop_test.revenue add constraint location_id_revenue foreign key (location_id) references location (id) ;
alter table shop_test.stock add constraint location_id_stock foreign key (location_id) references location (id) ;
alter table shop_test.stock add constraint product_id_stock foreign key (product_id) references product (id) ;

alter table shop_test.order_detail add column shipped_from_id integer;
alter table shop_test.order_detail add constraint shipped_from_id foreign key (shipped_from_id) references location (id) ;

insert into shop_test.product_category values (1, 'Home appliances', 'Appliances');

insert into shop_test.supplier values (1, 'AEG');
insert into shop_test.supplier values (2, 'Media Galaxy');

insert into shop_test.product values (1, 'Espressor Bosch Tassimo Vivy', 'https://s12emagst.akamaized.net/products/5861/5860979/images/res_33aa74ad56a0b8542ed897b24b1175e1_full.jpg','Espressor Bosch Tassimo Vivy',171.4,35,1,1);
insert into shop_test.product values (2, 'Microwave Oven Unold', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/610ptyvekxl-sl1000-1563196528.jpg?crop=1.00xw:0.502xh;0,0.200xh&resize=1200:*','Microwave Oven Unold',348.4,15,1,2);
insert into shop_test.product values (3, 'Ice cream maker', 'https://megazar.ro/wp-content/uploads/2019/06/HTB1PkffazLuK1Rjy0Fhq6xpdFXaO.jpg','Ice cream maker',90.2,12,1,1);
insert into shop_test.product values (4, 'Ice maker', 'https://image.made-in-china.com/2f0j00unOaAIByQNrv/Portable-Mini-Ice-Maker-Machine-Home-Ice-Maker.jpg','Ice maker',68.5,8,1,1);
insert into shop_test.product values (5, 'Electric Grill Raclette', 'https://www.mirgo-shop.ro/wp-content/uploads/2017/08/Gratar-electric-ECG-KG300-deluxe-2000-W.jpg','Electric Grill Raclette',120,22,1,1);

insert into shop_test.location values (1, 'Bucuresti', 'Romania', 'Ilfov', 'Calea Vitan Nr. 55-59','Bucuresti');
insert into shop_test.location values (2, 'Timisoara', 'Romania', 'Timis', 'Str. Demetriade, Nr. 1, Sp. T 10','Timisoara');
insert into shop_test.location values (3, 'Novi Sad', 'Serbia', 'South Bačka', 'Kralja Aleksandra 3','Novi Sad');
insert into shop_test.location values (4, 'Niš', 'Serbia', 'Nišava', 'Obrenovićeva 1','Niš');

insert into shop_test.stock values (30, 1, 1);
insert into shop_test.stock values (16, 2, 1);
insert into shop_test.stock values (12, 3, 1);

insert into shop_test.stock values (20, 1, 2);
insert into shop_test.stock values (27, 2, 2);
insert into shop_test.stock values (22, 3, 2);

insert into shop_test.stock values (8, 1, 3);
insert into shop_test.stock values (15, 2, 3);
insert into shop_test.stock values (4, 4, 3);