set serveroutput on;

select * from equipos;

insert into equipos values(default, 'gma', to_date('18-02-2022','DD-MM-YYYY'),default);
insert into equipos values(default, 'pat', to_date('18-02-2022','DD-MM-YYYY'),default);
insert into equipos values(default, 'JUU', to_date('18-02-2022','DD-MM-YYYY'),default);
insert into equipos values(default, 'LIK', to_date('18-02-2022','DD-MM-YYYY'),default);

insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'habsf','Centinela',3);
insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'wdsadf','Duelista',3);
insert into jugadores values(default,'pepe','perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'afw4','centinela',1);
insert into jugadores values(default,'popo','poroz','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'ff32','duelista',1);
insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'g53','Centinela',2);
insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'gw43qh5g','Duelista',2);
insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'afs4','Centinela',4);
insert into jugadores values(default,'Pepe','Perez','ESP',
to_date('18-02-2002','DD-MM-YYYY'),2000,'q3g25h','Duelista',4);
delete from jugadores;

declare
    continuar boolean;
begin
    pr_empezar_competicion(continuar);
    if continuar then
        dbms_output.put_line('La competicion se ha cerrado');
    else
        dbms_output.put_line('La competicion no se ha podido cerrar');
    end if;
end;

declare
    c_cursor sys_refcursor;
    c_leer vis_equ_info%rowtype;
begin
    pr_conseguir_info_equipos(c_cursor);
    
    fetch c_cursor into c_leer;
    
    while c_cursor%found loop
        dbms_output.put_line(c_leer.nombre || c_leer.fecha_fundacion ||
        ',' || c_leer.cantidad_jugadores || ',' ||  c_leer.salario_maximo || 
        ',' || c_leer.salario_minimo || ',' || c_leer.salario_medio);
        
        fetch c_cursor into c_leer;
    end loop;
end;

declare
    c_cursor sys_refcursor;
    TYPE jugadores_datos IS RECORD
        (
        nombre jugadores.nombre%TYPE,
        apellido jugadores.apellido%TYPE,
        rol jugadores.rol%TYPE,
        sueldo jugadores.sueldo%TYPE
        );
    c_leer jugadores_datos;
begin
    pr_conseguir_info_jugadores('puta',c_cursor);
    
    fetch c_cursor into c_leer;
    
    while c_cursor%found loop
        dbms_output.put_line(c_leer.nombre || c_leer.apellido ||
        ',' || c_leer.rol || ',' ||  c_leer.sueldo);
        fetch c_cursor into c_leer;
    end loop;
end;