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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PacienteMapper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class Test {

        public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ServiceFacadeException {
        //Paciente p=new Paciente(777,"cc","Pedro Rodriguez",new Date(1,1,1));
        //p.getConsultas().add(new Consulta(new Date(2,2,2), "todo ok"));
        //p.getConsultas().add(new Consulta(new Date(2,2,2), "todo mal"));
        //p.getConsultas().add(new Consulta(new Date(2,2,2), "todo bien"));
        
        //ServicesFacade.getInstance("applicationconfig.properties").registrarNuevoPaciente(p);
        
        
        ServicesFacade.getInstance("applicationconfig.properties").agregarConsultaAPaciente(333,"cc",new Consulta(new Date(2,2,2), "el ultimo resumen"));
        /*SqlSessionFactory sqlsf=getSqlSessionFactory();
        SqlSession sf=sqlsf.openSession();
        PacienteMapper pm=sf.getMapper(PacienteMapper.class);
        
        Consulta c=new Consulta(new Date(2000, 1, 1), "resumen");
        pm.insertConsulta(333, "cc", c);
        
        System.out.println(c);
        
        sf.commit();
        sf.close();*/
    }
    
}
