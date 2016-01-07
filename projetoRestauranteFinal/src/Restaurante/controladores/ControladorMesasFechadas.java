package Restaurante.controladores;

import core.BancoDados;
import core.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao Francisco Padilha Souza
 * @since 10/07/2015
 * 
 */
public class ControladorMesasFechadas
{
    
    int mesaId;
    Double valor;
    private BancoDados dbMf;
    public ControladorMesasFechadas()
    {
        dbMf = new BancoDados("Restaurante");
        dbMf.conectar();
        dbMf.executarSQL("CREATE TABLE IF NOT EXISTS mesasFechadas(mesaId INT, valor DECIMAL (12,2))");
        dbMf.fechar();
    }
    
    public double consultarValorTotalConsumido()
    {
        Double valorTotal = 0.0;
        String sql = "SELECT SUM(valor) AS total FROM mesasfechadas";
        dbMf.conectar();
        ResultSet dados = dbMf.executarConsultaSQL(sql);
        try
        {
            if (dados.next())
            {
                valorTotal = dados.getDouble("total");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao realizar a soma");
        }
        finally
        {
            dbMf.fechar();
        }
        System.out.println("na bus" + valorTotal);
        return valorTotal;
    }
    
    public double consultarValorTotalConsumidoPorMesa( int mesaId)
    {
        Double valorTotal = 0.0;
        String sql = "SELECT SUM(valor) AS total FROM mesasfechadas WHERE mesaId = " + mesaId ;
        dbMf.conectar();
        ResultSet dados = dbMf.executarConsultaSQL(sql);
        try
        {
            if (dados.next())
            {
                valorTotal = dados.getDouble("total");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao realizar a soma por mesa");
        }
        finally
        {
            dbMf.fechar();
        }
        return valorTotal;
    }
    
    
    /**
     * esse metodo é chamado quando fechar mesa
     * ele pega o valor da mesa fechada e acrescenta nesta tabela
     */
    public void adicionarValorDeMesaFechada(int mesaId, Double valor)
    {
        String sql = "INSERT INTO mesasfechadas (mesaId, valor) VALUES (" + mesaId + ", "+ valor + ")";
        dbMf.conectar();
        dbMf.executarSQL(sql);
        dbMf.fechar();
    }
    public double consultarMediaDeConsumo()
    {
        int totalMesas = 0;
        Double media = 0.0;
        Double totalConsumido = 0.0;
        
        String sqlMesas = "SELECT COUNT(mesaId) mesas FROM mesa";
        dbMf.conectar();
        ResultSet dados = dbMf.executarConsultaSQL(sqlMesas);
        try
        {
            if (dados.next())
            {
                totalMesas = dados.getInt("mesas");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao contar as mesas");
        }
        finally
        {
            dbMf.fechar();
        }
        
        if (totalMesas > 0)
        {
            totalConsumido = this.consultarValorTotalConsumido();
            if (totalConsumido > 0.0 )
            {
                media = totalConsumido /totalMesas;
            }
        }
        return media;
    }
    
    public void apagarRelarorios()
    {
        String sql = "DELETE FROM mesasfechadas";
        dbMf.conectar();
        dbMf.executarSQL(sql);
        dbMf.fechar();
    }

    
}
