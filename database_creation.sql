# drop DATABASE redsocialroar;
create database redsocialroar;
use redsocialroar;

create table pass(
	id_password int auto_increment,
    pass varchar(50),
    primary key (id_password)
);

create table usuario (
	id_usuario int auto_increment,
    nick varchar(20) unique not null,
    correo varchar(50) not null,
	pass int,
    primary key (id_usuario),
    foreign key (pass) references pass(id_password) on delete cascade
);

create table mensaje (
	id_mensaje int auto_increment,
    id_usuario int,
    mensaje varchar(180),
    hora time,
    fecha date,
    primary key (id_mensaje),
    foreign key (id_usuario) references usuario (id_usuario)
);

create table seguir(
	id_seguir int auto_increment,
    id_siguiendo int,
    id_seguidor int,
    primary key (id_seguir),
    foreign key (id_siguiendo) references usuario (id_usuario), 
    foreign key (id_seguidor) references usuario (id_usuario)
);

INSERT INTO `pass` VALUES (1,'j'),(2,'s'),(4,'m');
INSERT INTO `usuario` VALUES (1,'juan','juan@gmail.com',1),(2,'sara','sara@gmail.com',2),(4,'marina','marina@gmail.com',4);
INSERT INTO `mensaje` VALUES (1,1,'Hola mundo','11:04:04','2019-11-14'),(2,1,'Hay alguien más?','11:04:35','2019-11-14'),(3,2,'esto cómo funciona?','11:04:48','2019-11-14');
INSERT INTO `seguir` VALUES (4,1,4),(5,1,2),(6,4,2);

