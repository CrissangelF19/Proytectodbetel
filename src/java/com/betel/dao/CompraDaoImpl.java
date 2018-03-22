
package com.betel.dao;

import com.betel.db.ConectarDB;
import com.betel.modelo.Compra;
import com.betel.modelo.DetalleCompra;
import com.betel.dao.DetalleCompraDaoImpl;
import com.betel.modelo.Producto;
import com.betel.modelo.Proveedor;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDaoImpl implements IDAO{
    
    ConectarDB con = new ConectarDB();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    String respuesta = null;
    Compra compra;   
    Proveedor prov;
    int totalUnd = 0;
    
    DetalleCompraDaoImpl daoDetalle = new DetalleCompraDaoImpl();
    
    ProductoDaoImpl pc = new ProductoDaoImpl();

    public CompraDaoImpl() {
    }       

    @Override
    public String insertar(Object obj) throws SQLException {
        Compra objCompra = (Compra) obj; 
        List<DetalleCompra> listaDetalle = new ArrayList<>();
        
        CompraDaoImpl daoCompra = new CompraDaoImpl();
        
        try {
            psmt = con.conectar().prepareStatement("INSERT INTO compra VALUES (null,?,?,?,?,?,?,?,?,?,?)");
        
            psmt.setString(1, objCompra.getnCompra());
            psmt.setString(2, objCompra.getIdProveedor());            
            psmt.setString(3, objCompra.getObservacion());
            psmt.setDouble(4, objCompra.getNeto());
            psmt.setDouble(5, objCompra.getIva());
            psmt.setDouble(6, objCompra.getTotalPagar());
            psmt.setDouble(7, objCompra.getSaldoPendiente());            
            psmt.setDate(8, objCompra.getFecha());
            psmt.setString(9, objCompra.getPago());
            psmt.setString(10, objCompra.getEstado());
            
            psmt.executeUpdate();
            
            Compra c = (Compra) buscarPorID(objCompra.getnCompra());
            
            listaDetalle = objCompra.getDetalleCompra();           
            
            for (DetalleCompra detalleCompra : listaDetalle) {
                
                Producto prod = (Producto) pc.buscarPorID(detalleCompra.getIdProducto());
                
                totalUnd = detalleCompra.getCajas() * prod.getUnidadesCaja() + detalleCompra.getUnidades();
                
                daoDetalle.insertar(new DetalleCompra(null,c.getIdCompra(), detalleCompra.getIdProducto(), detalleCompra.getPrecioCompra(), detalleCompra.getDescuento(), detalleCompra.getCajas(), detalleCompra.getUnidades(), totalUnd));
            }                        
            
            if (c.getPago().equals("Pendiente")) {
                
                daoCompra.actualizarSaldoPendiente(objCompra.getnCompra(), objCompra.getTotalPagar(), "sumar");
                                
            }
            
            /*System.out.println("Id de Compra: " + buscarPorID(objCompra.getnCompra()));
            System.out.println("N°Compra: " + objCompra.getnCompra());*/
            
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
        Compra objCompra = (Compra) obj;
        
        try {
            psmt = con.conectar().prepareStatement("UPDATE compra SET estado = ? WHERE idCompra = ?");
            psmt.setString(1, "Inactivo");
            psmt.setString(2, objCompra.getIdCompra());
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Compra> listar() throws SQLException {
        List<Compra> listaCompra = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarCompra()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaCompra.add(Compra.load(rs));
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
        
        return listaCompra;
    }

    @Override
    public Object buscarPorID(String id) throws SQLException {
        List<Compra> listaCompra = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM compra WHERE nCompra = ?");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
               listaCompra.add(Compra.load(rs));               
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
        
        return listaCompra.get(0);
    }

    @Override
    public int contar() throws SQLException{
        
        int nRegistro = 0;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM compra WHERE estado = 'Activo'");
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
        Compra objCompra = (Compra) id;        
        Boolean respuesta = false;
        
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM compra WHERE nCompra LIKE ?");
            psmt.setString(1,objCompra.getnCompra());
            rs = psmt.executeQuery();
            
            while (rs.next()) {                
                respuesta = true;                  
            }
            
        } catch (SQLException e) {
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
        
        return respuesta;
    }

    @Override
    public List<Compra> busquedaPorParametro(String field, Object param) throws SQLException {
        
        List<Compra> listaBusquedaCompra = new ArrayList<>();
        Compra compra = (Compra) param;        
        int item = Integer.valueOf(field);
        String sql = null;
        
        try{
            switch (item) {
                case 0:
                    sql = "{CALL bNCompra(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, compra.getnCompra());
                    break;
                case 1:
                    sql = "{CALL bProvCompra(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, compra.getIdProveedor());
                    break;
                case 2:
                    sql = "{CALL bPagoCompra(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, compra.getPago());
                    break;
                case 3:
                    sql = "{CALL bEstadoCompra(?)}";
                    psmt=con.conectar().prepareStatement(sql);
                    psmt.setString(1, compra.getEstado());
                    break;                   
                    
                default:
                    throw new AssertionError();
        }
            rs = psmt.executeQuery();
            while(rs.next()){
                listaBusquedaCompra.add(Compra.load(rs));
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
        
        return listaBusquedaCompra;
        
    }

    @Override
    public List<?> existeUsuario(String usuario, String clave) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String generarCodigo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
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
            
    //######### Metodo Para Buscar buscar Por ID Proveedor ##########
    public Object buscarPorIDProveedor(String id) throws SQLException {        
        try {
            psmt = con.conectar().prepareStatement("{CALL bIDProv(?)}");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                prov = Proveedor.load(rs);              
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
        
        return prov;
    }
    
    //######### Metodo Para Listar Pagos Pendiente  de Compra##########
    public List<Compra> listarPago() throws SQLException {
        List<Compra> listaCompra = new ArrayList<>();
        
        try {
            psmt = con.conectar().prepareStatement("{CALL listarPgCompra()}");            
            rs = psmt.executeQuery();
            while (rs.next()) {                
                listaCompra.add(Compra.load(rs));
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
        
        return listaCompra;
    }
    
    //######### Metodo Para Actualizar SaldoPendiente ##########
    public String actualizarSaldoPendiente(String nCompra, double saldo, String operacion) throws SQLException{        
        
        try {
            
            CompraDaoImpl daoCompra = new CompraDaoImpl();
            
            //Aumentar
            if(daoCompra.buscarPorID(nCompra)!=null && operacion=="sumar"){
                
                compra = (Compra) daoCompra.buscarPorID(nCompra);                
                double saldoPendiente =  compra.getSaldoPendiente() + saldo;
                
                psmt = con.conectar().prepareStatement("UPDATE compra SET saldoPendiente = ? WHERE nCompra = ?");            
                psmt.setDouble(1, saldoPendiente);
                psmt.setString(2, nCompra);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
                
            //Disminuir    
            }else if(daoCompra.buscarPorID(nCompra)!=null && operacion=="restar"){
                
                compra = (Compra) daoCompra.buscarPorID(nCompra);                
                double saldoPendiente =  compra.getSaldoPendiente() - saldo;
                
                psmt = con.conectar().prepareStatement("UPDATE compra SET saldoPendiente = ? WHERE nCompra = ?");            
                psmt.setDouble(1, saldoPendiente);
                psmt.setString(2, nCompra);           

                psmt.executeUpdate();

                respuesta = "El registro se realizo con exito";
                
            }else if (daoCompra.buscarPorID(nCompra)!=null && operacion=="estado") {
                
                compra = (Compra) daoCompra.buscarPorID(nCompra);
                
                psmt = con.conectar().prepareStatement("UPDATE compra SET pago = ? WHERE nCompra = ?");
                psmt.setString(1, "Pagado");
                psmt.setString(2, nCompra);
                psmt.executeUpdate();
                respuesta = "El registro se realizó correctamente";
                
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
    public String actualizarPago(String nCompra) throws SQLException{        
        
        try {
            
            CompraDaoImpl daoCompra = new CompraDaoImpl();
            
            //pagada
            if (daoCompra.buscarPorID(nCompra)!=null) {
                
                compra = (Compra) daoCompra.buscarPorID(nCompra);
                
                psmt = con.conectar().prepareStatement("UPDATE compra SET pago = ? WHERE nCompra = ?");
                psmt.setString(1, "Pagado");
                psmt.setString(2, nCompra);
                psmt.executeUpdate();
                respuesta = "El registro se realizó correctamente";
                
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
    
    //######### Metodo Para Por ID de Compra ##########
    public Object buscarPorIDCompra(String id) throws SQLException {
        List<Compra> listaCompra = new ArrayList<>();
        try {
            psmt = con.conectar().prepareStatement("SELECT * FROM compra WHERE idCompra = ?");
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
               listaCompra.add(Compra.load(rs));               
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
        
        return listaCompra.get(0);
    }
    
    
    //############## Metodo para Registrar la Compra #########
    
    /*public static void main(String[] args) throws SQLException {
        
        CompraDaoImpl objDao = new CompraDaoImpl();
        DetalleCompra objDetCompra = new DetalleCompra(null,"2","3", 4, 5, 6, 7, 8);
        List<DetalleCompra> listaDetalle = new ArrayList();
        listaDetalle.add(objDetCompra);
        
        Compra objCompra = new Compra("null", "1", "1", "1", "observacion", 11, 23, 344, Date.valueOf("2018-06-05"), "Pagado", "Activo",listaDetalle);
                
        objDao.insertar(objCompra);
        
    }*/
    
    //############## Metodo para Actualizar SaldoPendiente #########
    /*public static void main(String[] args) throws SQLException {
        
        CompraDaoImpl objDao = new CompraDaoImpl();
        
        System.out.println("Mesaje: " + objDao.actualizarPago("00001"));
    }*/
}
