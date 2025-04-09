drop table jugadores;
drop table enfrentamientos;
drop table jornadas;
drop table roles;
drop table competiciones;
drop table equipos;
drop table usuarios;
drop table juegos;

create table juegos(
    cod_juego number(2) generated always as identity,
    fecha_salida date not null,
    nombre varchar2(20) not null,
    constraint jue_cod_pk primary key(cod_juego)
);

create table usuarios(
    cod_usuario number(2) generated always as identity,
    nombre varchar2(20) not null unique,
    contraseña varchar2(20),
    tipo_usuario varchar(1),
    constraint usu_cod_pk primary key(cod_usuario)
);

create table equipos(
    cod_equipo number(2) generated always as identity,
    nombre varchar2(15) not null,
    fecha_fundacion date,
    puntuacion number(2),
    constraint equ_cod_pk primary key(cod_equipo)
);

create table competiciones(
    cod_comp number(2) generated always as identity,
    estado varchar(1) not null,
    cod_juego number (2),
    constraint com_cod_pk primary key(cod_comp),
    constraint com_jue_fk foreign key (cod_juego) 
        references juegos (cod_juego)
);

create table roles(
    cod_rol number(2) generated always as identity,
    rol varchar2(20) not null,
    cod_juego number (2),
    constraint rol_cod_pk primary key(cod_rol),
    constraint rol_jue_fk foreign key (cod_juego) 
        references juegos (cod_juego)
);

create table jornadas(
    num_jornada number(2) generated always as identity,
    fecha_inicio date not null,
    cod_comp number (2),
    constraint jor_num_pk primary key(num_jornada),
    constraint jor_com_fk foreign key (cod_comp) 
        references competiciones (cod_comp)
);

create table enfrentamientos(
    id_enfrentamiento number(2) generated always as identity,
    hora timestamp not null,
    resultados_eq_1 number(2),
    resultados_eq_2 number(2),
    cod_equ_1 number(2),
    cod_equ_2 number(2),
    num_jornada number(2),
    constraint enf_id_pk primary key(id_enfrentamiento),
    constraint enf_eq1_fk foreign key (cod_equ_1) 
        references equipos (cod_equipo),
    constraint enf_eq2_fk foreign key (cod_equ_2) 
        references equipos (cod_equipo),
    constraint enf_jor_fk foreign key (num_jornada) 
        references jornadas (num_jornada)
);

create table jugadores(
    cod_jugador number(2) generated always as identity,
    nombre varchar2(20) not null,
    apellido varchar2(20) not null,
    nacionalidad varchar2(20) not null,
    fecha_nac date not null,
    sueldo number(5) not null,
    nicnkame varchar2(20) not null unique,
    cod_rol number(2),
    cod_equipo number(2),
    constraint jug_cod_pk primary key(cod_jugador),
    constraint jug_rol_fk foreign key (cod_rol) 
        references roles (cod_rol),
    constraint jug_equ_fk foreign key (cod_equipo) 
        references equipos (cod_equipo)
    );

create or replace trigger tr_jug_nac
before insert or update of fecha_nac on jugadores
for each row
    declare
        e_mensaje varchar2(255);
        edad_mayor exception;
        edad_menor exception;
    begin
        if :new.fecha_nac > add_months(sysdate,-192) then
            raise edad_menor;
        elsif :new.fecha_nac < add_months(sysdate,-780) then
           raise edad_mayor;
        end if;
    exception
        when edad_mayor then
            e_mensaje := 'La persona que se ha intentado ';
            if inserting then
                e_mensaje := e_mensaje || 'insertar';
            else
                e_mensaje := e_mensaje || 'actualizar';
            end if;
            e_mensaje := e_mensaje || ' tiene mas de 65 años.';
            raise_application_error(-20001,e_mensaje);
        when edad_menor then
            e_mensaje := 'La persona que se ha intentado ';
            if inserting then
                e_mensaje := e_mensaje || 'insertar';
            else
                e_mensaje := e_mensaje || 'actualizar';
            end if;
            e_mensaje := e_mensaje || ' tiene menos de 16 años.';
            raise_application_error(-20001,e_mensaje);
end tr_jug_nac;

create or replace trigger tr_jug_sueldo
before insert or update of sueldo on jugadores
for each row when (new.sueldo < 1137)
    declare
        e_mensaje varchar2(255);
        sueldo exception;
    begin
        raise sueldo;
    exception
        when sueldo then
            e_mensaje := 'La persona que se ha intentado ';
            if inserting then
                e_mensaje := e_mensaje || 'insertar';
            else
                e_mensaje := e_mensaje || 'actualizar';
            end if;
            e_mensaje := e_mensaje || ' tiene un sueldo menos a 1137.';
            raise_application_error(-20002,e_mensaje);
end tr_jug_sueldo;

create or replace trigger tr_equi_fecha_fund
before insert or update of fecha_fundacion on equipos
for each row 
    declare
        fecha_salida date;
    
        e_mensaje varchar2(255);
        fecha_no_valida exception;
    begin
    
        select fecha_salida into fecha_salida
        from juegos
        where cod_juego = 1;
        
        if :new.fecha_fundacion < fecha_salida then
            raise fecha_no_valida;
        end if;
    exception
        when fecha_no_valida then
            e_mensaje := 'El equipo que se ha intentado ';
            if inserting then
                e_mensaje := e_mensaje || 'insertar';
            else
                e_mensaje := e_mensaje || 'actualizar';
            end if;
            e_mensaje := e_mensaje || ' se funda antes de la creacion ' ||
            'del juego, que es: ' || fecha_salida;
            raise_application_error(-20003,e_mensaje);
end tr_equi_fecha_fund;



create or replace package paquete_reto_equipo_3 is
    
end paquete_reto_equipo_3;
create or replace package body paquete_reto_equipo_3 as
    
end paquete_reto_equipo_3;
