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
package edu.eci.pdsw.samples.persistence.mybatisimpl;


import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.DaoPaciente;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PacienteMapper;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author hcadavid
 */
public class MyBatisDaoPaciente implements DaoPaciente{

    
    private PacienteMapper pmap=null;

    public MyBatisDaoPaciente(SqlSession session) {        
        pmap=session.getMapper(PacienteMapper.class);
    }

    @Override
    public Paciente load(int id, String tipoid) throws PersistenceException {        
        return pmap.selectPaciente(id, tipoid);
    }

    @Override
    public void save(Paciente p) throws PersistenceException {
        pmap.insertPaciente(p);
        for (Consulta c:p.getConsultas()){
            pmap.insertConsulta(p.getId(), p.getTipo_id(), c);
        }
    }

    @Override
    public void update(Paciente p) throws PersistenceException {
        for (Consulta c:p.getConsultas()){
            pmap.insertConsultaWithIgnore(p.getId(), p.getTipo_id(), c);
        }
        
    }
    
    
}
