
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Cliente;
import com.betel.modelo.DetalleVenta;
import com.betel.modelo.Producto;
import com.betel.modelo.Venta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Venta venta;
    Cliente client;
    int totalUnd = 0;
    
    DetalleVentaDaoImpl daoDetalle = new DetalleVentaDaoImpl();
    
    ProductoDaoImpl pv = new ProductoDaoImpl();

    public VentaDaoImpl() {
    }        

    @Override
    public String insertar(Object obj) throws SQLException {
        Venta objVenta = (Venta) obj;
        List<DetalleVenta> listaDetalle = new ArrayList<>();  
        
        VentaDaoImpl daoVenta = new VentaDaoImpl();
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO venta VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
            psmt.setString(1, objVenta.getnVenta());
            psmt.setString(2, objVenta.getIdCliente());
            psmt.setString(3, objVenta.getIdEmpleado());
            psmt.setString(4, objVenta.getIdEmpresa());
            psmt.setDouble(5, objVenta.getEfectivo());
            psmt.setString(6, objVenta.getObservacion());            
            psmt.setDouble(7, objVenta.getSubtotal());
            psmt.setDouble(8, objVenta.getIva());
            psmt.setDouble(9, objVenta.getTotalPagar());
            psmt.setDouble(10, objVenta.getSaldoPendiente());
            psmt.setDate(11, objVenta.getFecha());
            psmt.setString(12, objVenta.getPago());
            psmt.setString(13, objVenta.getEstado());
            
            psmt.executeUpdate();
            
            Venta v = (Venta) buscarPorID(objVenta.getnVenta());
            
            listaDetalle = objVenta.getDetalleVenta();
            
            for (DetalleVenta detalleVenta : listaDetalle) {
                
                Producto prod = (Producto) pv.buscarPorID(detalleVenta.getIdProducto());
                
                totalUnd = detalleVenta.getCajas() *  prod.getUnidadesCaja() + detalleVenta.getUnidades(); 
                                
                daoDetalle.insertar(new DetalleVenta(null, v.getIdVenta(), detalleVenta.getIdProducto(), detalleVenta.getCajas(), detalleVenta.getUnidades(), totalUnd, detalleVenta.getPrecioVenta(), detalleVenta.getDescuento()));
            }                      
            
            if (v.getPago().equals("Pendiente")) {
                
               daoVenta.actualizarSaldoPendiente(objVenta.getnVenta(), objVenta.getTotalPagar(), "sumar");
            }
            
            /*System.out.println("Id de Venta: " + buscarPorID(objVenta.getnVenta()));
            System.out.println("N°Venta: " + objVenta.getnVenta());*/

            
            respuesta = "El registro exitoso";
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            
            con.desconectar();            
        }
        
        return respuesta;
        
    }

    @Override
    public String eliminar(Object obj) throws SQLException {
        Venta objVenta = (Venta) obj;
        
        try {
            psmt = con.conectar().prepareStatement("UPDATE venta SET estado = ? WHERE idVenta = ?");
            psmt.setString(1, "Inactivo");
            psmt.setString(2, objVenta.getIdVenta());
            psmt.executeUpdate();
            respuesta = "El registro se realizó correctamente";
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString()); 
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            con.desconectar();
        }
        
        return respuesta;
    }

    @Override
    public String modificar(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listar() throws SQLException {
        List<Venta> listaVenta = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarVenta()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaVenta.add(Venta.load(rs));
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaVenta; 
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        List<Venta> listaVenta = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM venta WHERE nVenta = ?");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            
            while (rs.next()) {
                listaVenta.add(Venta.load(rs));               
            }
        } catch (Exception e) {
            System.out.println("Error en el Id: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaVenta.get(0);
    }

    @Override
    public int contar() throws SQLException{
        int nRegistro = 0;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM venta WHERE estado = 'Activo'");
            rs = psmt.executeQuery();
            while (rs.next()) { 
                
                nRegistro ++;            
            }             
            
        } catch (Exception e) {
            System.out.println("Error en la consulta Contar: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return nRegistro;
    }

    @Override
    public boolean existe(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> busquedaPorParametro(String field, Object param) throws SQLException {
        
        List<Venta> listaBusquedaVenta = new ArrayList<>();
        Venta venta = (Venta) param;
        int item = Integer.valueOf(field);
        String sql = null;
        
        try{
            switch (item) {
                case 0:
                    sql = "{CALL bNVenta(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, venta.getnVenta());
                    break;
                case 1:
                    sql = "{CALL bClientVenta(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, venta.getIdCliente());
                    break;
                case 2:
                    sql = "{CALL bEmplVenta(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, venta.getIdEmpleado());
                    break;
                case 3:
                    sql = "{CALL bPagoVenta(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, venta.getPago());
                    break; 
                case 4:
                    sql = "{CALL bEstadoVenta(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, venta.getEstado());
                    break;     
                    
                default:
                    throw new AssertionError();
        }
            rs = psmt.executeQuery();
            while(rs.next()){
                listaBusquedaVenta.add(Venta.load(rs));
            }
        }catch (SQLException e){
            System.out.println("Error en la consulta Busqueda Venta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            con.desconectar();
        }
        
        return listaBusquedaVenta;
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generarCodigo() throws SQLException {
        
        String codigo = null;
         try {
         psmt = con.conectar().prepareStatement("SELECT COUNT(idVenta) FROM venta");
         rs = psmt.executeQuery();
           
            while(rs.next()){

                switch(rs.getString(1).length()){
                    case 1:
                       codigo = "DB-0000000" + rs.getString(1);
                       break;
                    case 2:
                       codigo = "DB-000000" + rs.getString(1);
                       break;
                    case 3:
                       codigo = "DB-00000" + rs.getString(1);
                       break;
                    case 4:
                       codigo = "DB-0000" + rs.getString(1);
                       break;
                    case 5:
                       codigo = "DB-000" + rs.getString(1);
                       break;
                    case 6:
                       codigo = "DB-00" + rs.getString(1);
                       break;
                    case 7:
                       codigo = "DB-0" + rs.getString(1);
                       break;
                    case 8:
                       codigo = "DB-" + rs.getString(1);
                       break;   
                    default: break;
                }      
            }

        } catch (Exception e) {
            throw new SQLException("Error al generar código: " + e.toString());
        }finally{
            if(psmt!=null){
                psmt.close();
            }
             if(rs!=null){
                rs.close();
            }
            con.desconectar();
        }

        return codigo;
        
    }
    
    //######### Metodo Para Buscar Producto ##########
    public List<Producto> busquedaPorParametroProducto(String field, Object param) throws SQLException {
        
        List<Producto> listaBusquedaProd = new ArrayList<>();
        Producto prod = (Producto) param;
        int item = Integer.valueOf(field);
        String sql = null;
        
        try{
            switch (item) {
                case 0:
                    sql = "{CALL bCodigoProduct(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, prod.getCodProducto());
                    break;
                case 1:
                    sql = "{CALL bNombreProduct(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, prod.getNombre());
                    break;
                    
                default:
                    throw new AssertionError();
        }
            rs = psmt.executeQuery();
            while(rs.next()){
                listaBusquedaProd.add(Producto.load(rs));
            }
        }catch (SQLException e){
            System.out.println("Error en la consulta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            con.desconectar();
        }
        
        return listaBusquedaProd;
    }
    
    //######### Metodo Para Buscar buscarPorIDCliente ##########
    public Object buscarPorIDCliente(String id) throws SQLException {        
        try {
            psmt = con.conectar().prepareStatement("{CALL  bIDClient (?)}");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {                
               client = Cliente.load(rs);
            }
            
        } catch (Exception e) {
            System.out.println("Error en el Id: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return client;
    } 
    
    //######### Metodo Para Listar Pagos Pendiente de Venta ##########
    public List<Venta> listarPago() throws SQLException {
        List<Venta> listaVenta = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarPgVenta()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {
                listaVenta.add(Venta.load(rs));
            }
            
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaVenta; 
    }
    
    //######### Metodo Para Actualizar SaldoPendiente ##########
    public String actualizarSaldoPendiente(String nVenta, double saldo, String operacion) throws SQLException{        
        
        try {
            
            VentaDaoImpl daoVenta = new VentaDaoImpl();
            
            //Aumentar
            if(daoVenta.buscarPorID(nVenta)!=null && operacion=="sumar"){
                
                venta = (Venta) daoVenta.buscarPorID(nVenta);               
                double saldoPendiente =  venta.getSaldoPendiente() + saldo;
                
                psmt = con.conectar().prepareStatement("UPDATE venta SET saldoPendiente = ? WHERE nVenta = ?");            
                psmt.setDouble(1, saldoPendiente);
                psmt.setString(2, nVenta);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
                
            //Disminuir    
            }else if (daoVenta.buscarPorID(nVenta)!=null && operacion=="restar"){
                
                venta = (Venta) daoVenta.buscarPorID(nVenta);               
                double saldoPendiente =  venta.getSaldoPendiente() - saldo;
                
                psmt = con.conectar().prepareStatement("UPDATE venta SET saldoPendiente = ? WHERE nVenta = ?");            
                psmt.setDouble(1, saldoPendiente);
                psmt.setString(2, nVenta);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
            }else{
                
                respuesta = "La actualización no se realizo con exito";
            }
            
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
        }finally{
            
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }            
            con.desconectar();
        }
        
        return respuesta;
        
    }
    
    //######### Metodo Para Para Actualizar el Estado de Pago a 'Pagado' ##########
    public String actualizarPago(String nVenta) throws SQLException{        
        
        try {
            
             VentaDaoImpl daoVenta = new VentaDaoImpl();
            
            //pagada
            if(daoVenta.buscarPorID(nVenta)!=null){
                
                venta = (Venta) daoVenta.buscarPorID(nVenta);
                
                psmt = con.conectar().prepareStatement("UPDATE venta SET pago = ? WHERE nVenta = ?");            
                psmt.setString(1, "Pagado");
                psmt.setString(2, nVenta);
                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
                
            }else{
                
                respuesta = "La actualización no se realizo con exito";
            }
            
        } catch (Exception e) {
            throw new SQLException("Error al registrar: " + e.toString());
        }finally{
            
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }            
            con.desconectar();
        }
        
        return respuesta;
        
    }
    
    //######### Metodo Para Por ID de Venta ##########
    public Object buscarPorIDVenta(String id) throws SQLException {
        List<Venta> listaVenta = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM venta WHERE idVenta = ?");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            
            while (rs.next()) {
                listaVenta.add(Venta.load(rs));               
            }
        } catch (Exception e) {
            System.out.println("Error en el Id: " + e);
        }finally{
            if(psmt!=null){
                psmt.close();
            }
            if(rs!=null){
                rs.close();
            }
            
            con.desconectar();
        }
        
        return listaVenta.get(0);
    }
    
    //######### Metodo Prueba de Insertar ##########
    /*public static void main(String[] args) throws SQLException{
        
        VentaDaoImpl dao = new VentaDaoImpl();
        DetalleVenta objDC = new DetalleVenta(null, "1", "1", 1, 2, 3, 2, 3);
        List<DetalleVenta> listaDetalle = new ArrayList<>();
        listaDetalle.add(objDC);
        
        Venta venta = new Venta("null", "011", "1", "2", "1", 12, "fdf", 1, 2, Date.valueOf("2018-06-05"), "Pagado", "Activo", listaDetalle);
        
        dao.insertar(venta);
    }*/
    
    //######### Metodo Prueba Buscar por ID ##########
    /*public static void main(String[] args) throws SQLException{
        
        ClienteDaoImpl clDao = new ClienteDaoImpl();
        
        System.out.println("Mesaje: " + clDao.buscarPorIDCliente("1"));
    }*/
    
    //############## Metodo para Actualizar SaldoPendiente #########
    /*public static void main(String[] args) throws SQLException {
        
        VentaDaoImpl objDao = new VentaDaoImpl();
        
        System.out.println("Mesaje: " + objDao.actualizarSaldoPendiente("DB-00000000", 10 , "restar"));
    }*/
    
    /*public static void main(String[] args) throws SQLException{
        
        VentaDaoImpl objDao = new VentaDaoImpl();
        
        System.out.println("Mesaje: " + objDao.actualizarPago("DB-00000001"));
    }*/
    
}
