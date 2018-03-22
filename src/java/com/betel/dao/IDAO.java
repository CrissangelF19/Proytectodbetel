package com.betel.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    
    public String insertar(Object obj)throws SQLException;

    public String eliminar(Object obj) throws SQLException;

    public String modificar(Object obj)throws SQLException;

    public List<?> listar() throws SQLException;

    public Object buscarPorID(String id) throws SQLException;

    public int contar() throws SQLException;

    public boolean existe(Object id) throws SQLException;
    
    List<?> busquedaPorParametro(String field, Object param) throws SQLException;
    
    public List<?> existeUsuario(String usuario, String clave) throws Exception;
    
    public String generarCodigo() throws SQLException;
    

}
