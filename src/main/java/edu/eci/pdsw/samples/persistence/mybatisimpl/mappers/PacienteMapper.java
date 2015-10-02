/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author hcadavid
 */
public interface PacienteMapper {
  
    @Select("select pac.id as id, pac.tipo_id as tipoid, pac.nombre, pac.fecha_nacimiento from PACIENTES as pac where pac.id=#{id} and pac.tipo_id=#{tipoid}")
    @Results(
            value={
                @Result(column = "id",property = "id"),
                @Result(column = "tipoid",property = "tipo_id"), 
                @Result(column = "nombre",property = "nombre"),
                @Result(column = "fecha_nacimiento",property = "fechaNacimiento"),
                @Result(property = "consultas" ,javaType = Set.class ,many = @Many(select = "selectConsultasPaciente"), column = "{idparam=id,tipoidparam=tipoid}")
            }
    )                    
    Paciente selectPaciente(@Param("id") int id,@Param("tipoid") String tipoid);
    
        
    
    @Select("select con.idCONSULTAS, con.fecha_y_hora, con.resumen from CONSULTAS as con where con.PACIENTES_id=#{idparam} "
            + "and con.PACIENTES_tipo_id=#{tipoidparam}")
    @Results(
            value={
                @Result(column = "idCONSULTAS",property = "id"),
                @Result(column = "fecha_y_hora",property = "fechayHora"),
                @Result(column = "resumen",property = "resumen"),
            }
    )        
    Set<Consulta> selectConsultasPaciente(Map<String,String> params);

    
    
    @Insert("insert into PACIENTES(id,tipo_id,nombre,fecha_nacimiento) values (#{id},#{tipo_id},#{nombre},#{fechaNacimiento})")    
    public void insertPaciente(Paciente p);

    @Insert("insert into CONSULTAS(fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id) values (#{consulta.fechayHora},#{consulta.resumen},#{idpaciente},#{tipoidpaciente})")
    @Options(useGeneratedKeys = true, keyProperty = "consulta.id")
    public void insertConsulta(@Param("idpaciente") int idpaciente,@Param("tipoidpaciente") String tipoidpaciente,@Param("consulta") Consulta c);
    
    //Si la consulta ya existe, ignora la inserción
    
    @Insert("insert IGNORE into CONSULTAS(fecha_y_hora,resumen,PACIENTES_id,PACIENTES_tipo_id) values (#{consulta.fechayHora},#{consulta.resumen},#{idpaciente},#{tipoidpaciente})")
    @Options(useGeneratedKeys = true, keyProperty = "consulta.id")
    public void insertConsultaWithIgnore(@Param("idpaciente") int idpaciente,@Param("tipoidpaciente") String tipoidpaciente,@Param("consulta") Consulta c);
    
    
    
}
