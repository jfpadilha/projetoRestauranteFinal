package Restaurante.controladores;

import core.BancoDados;
import core.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Joao F P S
 * @author Luis Felipe W.
 * @author Alex Roveda
 * 
 * @since 10/06/2015
 */
public class ControladorMesa
{

    private int mesaId;
    private boolean mesaAberta;
    private int quantPessoas;
    private BancoDados dbMesa;

    //Construtor basico
    public ControladorMesa( int mesaId, boolean mesaAberta, int quantPessoas )
    {
        this.mesaId = mesaId;
        this.mesaAberta = mesaAberta;
        this.quantPessoas = quantPessoas;
    }

    //Construtor padrao
    public ControladorMesa( boolean mesaAberta, int quantPessoas )
    {
        this.mesaAberta = mesaAberta;
        this.quantPessoas = quantPessoas;
    }

    //Construtor que inicializa a base e cria a tabela caso ainda nao exista
    public ControladorMesa()
    {
        dbMesa = new BancoDados("Restaurante");
        dbMesa.conectar();
        dbMesa.executarSQL("CREATE TABLE IF NOT EXISTS mesa(mesaId INT NOT NULL PRIMARY KEY, aberta BOOLEAN NOT NULL DEFAULT FALSE, quantPessoas INT)");
        dbMesa.fechar();
    }

    //Sets
    private void setMesaId( int mesaId )
    {
        this.mesaId = mesaId;
    }

    private void setMesaAberta( boolean mesaAberta )
    {
        this.mesaAberta = mesaAberta;
    }

    private void setQuantPessoas( int quantPessoas )
    {
        this.quantPessoas = quantPessoas;
    }

    //Gets
    public int getMesaId()
    {
        return mesaId;
    }

    public boolean isMesaAberta()
    {
        return mesaAberta;
    }

    public int getQuantPessoas()
    {
        return quantPessoas;
    }

///////Metodos de controle para a base de dados
    
    /**
     * Inicializa um consumo para uma mesa, ou simplesmente, inicia uma comanda
     * em uma mesa
     * 
     * @param mesaid
     */
    public void abrirMesa( int mesaid )
    {
        dbMesa = new BancoDados("Restaurante");
//        Data dtAb = new Data();
        boolean aberta = true;

        String sql = "UPDATE mesa SET(aberta) = ('"
                + aberta + "') WHERE mesaid = "
                + mesaid;

        dbMesa.conectar();
        dbMesa.executarSQL(sql);
        dbMesa.fechar();
    }

    /**
     * Finaliza a mesa e libera para ser usada por outras pessoas
     * 
     * @param mesaId 
     */
    public void fecharMesa( int mesaId )
    {
        dbMesa = new BancoDados("Restaurante");
        Data dtfec = new Data();
        int qtdPessoas = 0;
        boolean aberta = false;

        String sql = "UPDATE mesa SET(aberta, quantpessoas) = ('"
                + aberta + "',"
                + qtdPessoas + ") WHERE mesaid = "
                + mesaId;

        dbMesa.conectar();
        dbMesa.executarSQL(sql);
        
        sql = "DELETE FROM consumo WHERE mesaId = " + mesaId;
        dbMesa.executarSQL(sql);
        dbMesa.fechar();
    }

    /**
     * Adiciona uma nova mesa ao restaurante, usada caso o dono do restaurante
     * comprar mais mesas para colocar a disposicao dos clientes
     * 
     * @return  boolean
     */
    public boolean criarMesa()
    {
        boolean result = false;

        String sqlGet = "SELECT (CASE WHEN MAX(mesaId) IS NULL THEN 1 ELSE (MAX(mesaId) + 1) END) AS max FROM mesa";
        
        dbMesa.conectar();
        int maxCod = 0;
        try
        {
            ResultSet dados = dbMesa.executarConsultaSQL(sqlGet);

            if ( dados.next() )
            {
                maxCod = dados.getInt("max");
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao obter maior codigo da mesa");
        }
        finally
        {
            dbMesa.fechar();
        }
       
        if ( maxCod > 0 )
        {
            boolean m = false;
            String sql = "INSERT INTO mesa (mesaId, aberta) VALUES ("
                    + maxCod + ",'" + m + "')";

            dbMesa.conectar();
            dbMesa.executarSQL(sql);
            dbMesa.fechar();
            result = true;
        }
        return result;
    }
    
    /**
     * Calcula o valor que foi consumido na mesa ap partir do codigo da
     * mesa que foi informado ao sistema
     * 
     * @param mesaId
     * @return Double
     */
    public Double obterValorDaMesa(int mesaId)
    {
        dbMesa = new BancoDados("Restaurante");
        Double result = 0.0;
        String sql = "SELECT SUM(preco * quant) AS valorTotalDaMesa FROM produto a\n" +
                    "INNER JOIN consumo b USING (produtoId)\n" +
                    "INNER JOIN mesa c USING (mesaId)\n" +
                    "WHERE c.mesaId = " + mesaId;
        dbMesa.conectar();
        ResultSet dados = dbMesa.executarConsultaSQL(sql);
        try
        {
            if ( dados.next() )
            {
                result = dados.getDouble("valorTotalDaMesa");
            }
            
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao obter saldo da mesa");
        }
        
        dbMesa.fechar();
        
        return result;
    }
    
    /**
     * Faz um select na base de dados e retorna um arrayList (listagem) das
     * mesas.
     * 
     * @return arrayList
     */
    public ArrayList<ControladorMesa> listarMesas()
    {
        ArrayList<ControladorMesa> list = new ArrayList<>();
        ControladorMesa aux = null;
        
        String sql = "SELECT * FROM mesa ORDER BY mesaId";
        dbMesa.conectar();
        ResultSet dados = dbMesa.executarConsultaSQL(sql);
        try
        {
            while ( dados.next() )
            {                
                int codMesa = dados.getInt("mesaId");
                boolean isOpen = dados.getBoolean("aberta");
                int qtPess = dados.getInt("quantPessoas");
                
                aux = new ControladorMesa(codMesa, isOpen, qtPess);
                list.add(aux);
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao criar lista de mesas abertas!");
        }
        finally
        {
            dbMesa.fechar();
        }
        
        return list;
    }
    
    /**
     * Conta a quantidade de mesas que estão abertas.
     * 
     * @return 
     */
    public int contarMesasAbertas()
    {
        String sql = "SELECT COUNT(*) AS contMesas FROM mesa WHERE aberta";
        
        dbMesa = new BancoDados("Restaurante");
        dbMesa.conectar();
        ResultSet dados = dbMesa.executarConsultaSQL(sql);
        
        int cont = 0;
        
        try 
        {
            if ( dados.next() )
            {
                cont = dados.getInt("contMesas");
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro ao tentar obter quantidade de mesas abertas.");
        }
        
        dbMesa.fechar();
        
        return cont;
    }

    /**
     * Exclui todas as mesas do restaurante.
     */
    public void apagarTodasAsMesas()
    {
        String sql = "DELETE FROM mesa";
        
        dbMesa = new BancoDados("Restaurante");
        dbMesa.conectar();
        dbMesa.executarSQL(sql);
        dbMesa.fechar();
    }
}
