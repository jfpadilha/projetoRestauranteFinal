package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerencia a conexão com um banco de dados HSQLDB. Realiza as operações básicas
 * sobre este bando de dados.
 *
 * @author mouriac
 * @version 2015
 */
public class BancoDados
{

    private Connection con;
    private final String Restaurante;

    public BancoDados(String base)
    {
        this.Restaurante = base;
    }

    public void conectar()
    {

        try
        {
//            Class.forName("org.hsqldb.jdbcDriver");
            Class.forName("org.postgresql.Driver");
            //Connection con = DriverManager.getConnection("jdbc:hsqldb:file:/banco/dbteste", "", "");
//            Connection con = DriverManager.getConnection("jdbc:hsqldb:file:" + agendaDB, "", "");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/" + Restaurante, "postgres", "postgres");
            this.con = con;

        }
        catch ( ClassNotFoundException e )
        {
            System.out.println("Erro ao carregar o driver JDBC");
        }
        catch ( SQLException e )
        {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
        }
    }

    public void fechar()
    {
//        executarSQL("shutdown");
        try
        {
            con.close();
        }
        catch ( SQLException e )
        {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
        }
    }

    public ResultSet executarConsultaSQL(String sql)
    {
        ResultSet rs = null;
        try
        {
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);

        }
        catch ( SQLException e )
        {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
        }
        return rs;
    }

    public boolean executarSQL(String sql)
    {
        boolean ok = false;
        try
        {
            Statement stm = con.createStatement();
            ok = stm.execute(sql);

        }
        catch ( SQLException e )
        {
            System.out.println("Erro de SQL: " + e);
            e.printStackTrace();
        }
        return ok;
    }

}
