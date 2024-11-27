create table topico(
    id int auto_increment primary key not null ,
    titulo varchar(250) not null ,
    mensaje varchar(500) not null ,
    fecha_creacion DATETIME not null ,
    status ENUM('ACTIVE', 'INACTIVE', 'RESOLVED') not null ,
    autor_id int not null ,
    curso varchar(250) not null ,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
)