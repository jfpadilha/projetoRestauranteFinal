package Restaurante.controladores;

import core.BancoDados;
import core.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Joao F P S
 * @author Luis Felipe W.
 * @author Alex Roveda
 * 
 * @since 10/06/2015
 */
public class ControladorCaixa
{

    private Double valorInicial;
    public static boolean caixaAberto;
    private Data dataMovimentacao;
    private BancoDados dbCaixa;

    //Construtor padrao
    public ControladorCaixa( Double valorInicial )
    {
        this.valorInicial = valorInicial;
    }

    //construtor que inicializa a base e criar a tabela caso ainda nao exista
    public ControladorCaixa()
    {
        dbCaixa = new BancoDados("Restaurante");
        dbCaixa.conectar();
        dbCaixa.executarSQL("CREATE TABLE IF NOT EXISTS caixa (saldoemcaixa NUMERIC NOT NULL, caixaAberto BOOLEAN NOT NULL DEFAULT FALSE, datafechamento DATE)");
        dbCaixa.fechar();
    }

    //Sets
    private void setValorInicial( Double valorInicial )
    {
        this.valorInicial = valorInicial;
    }

    private static void setCaixaAberto( boolean caixaAberto )
    {
        ControladorCaixa.caixaAberto = caixaAberto;
    }

    private void setDataMovimentacao( Data dataMovimentacao )
    {
        this.dataMovimentacao = dataMovimentacao;
    }

    //Gets
    public Double getValorInicial()
    {
        return valorInicial;
    }

    public static boolean isCaixaAberto()
    {
        return caixaAberto;
    }

    public Data getDataMovimentacao()
    {
        return dataMovimentacao;
    }

    //Metodos de controle para a base de dados
    
    /**
     * Verifica se caixa está aberto.
     * @return 
     */
    public boolean verificarCaixaAberto()
    {
        String sql = "SELECT caixaAberto FROM caixa;";
        dbCaixa.conectar();
        ResultSet dados = dbCaixa.executarConsultaSQL(sql);
        dbCaixa.fechar();
        
        boolean caixaAberto = false;
        try 
        {
            if ( dados.next() )
            {
                caixaAberto = dados.getBoolean("caixaAberto");
            }
        }
        catch (SQLException ex) 
        {
            System.out.println("Erro ao verificar se caixa está aberto.");
        }
        
        return caixaAberto;
    }
    /**
     * Define um valor $ inicial no caixa ao iniciar as atividades no restaurante
     * 
     * @param saldoInicial 
     */
    public void definirEstadoInicial( Double saldoInicial )
    {
        boolean aberto = true;
        String sql = "INSERT INTO caixa (saldoEmCaixa, caixaaberto) VALUES ("
                + saldoInicial + ",'"
                + aberto + "')";

        dbCaixa.conectar();
        dbCaixa.executarSQL(sql);
        dbCaixa.fechar();
    }

    /**
     * Finaliza as operacoes de caixa e zera o valor em caixa
     */
    public void fecharCaixa()
    {
        String sql = "UPDATE caixa SET(caixaaberto, datafechamento, saldoEmCaixa) = (false, null, 0.0)";

        dbCaixa.conectar();
        dbCaixa.executarSQL(sql);
        dbCaixa.fechar();
    }

    /**
     * Faz uma consulta na base e retorna o saldo do caixa naquele momento
     *      
     * @return Double
     */
    public Double obterSaldoCaixa()
    {
        Double result = 0.0;
        String sql = "SELECT saldoEmCaixa FROM caixa WHERE caixaaberto IS TRUE";
        dbCaixa.conectar();
        ResultSet dados = dbCaixa.executarConsultaSQL(sql);
        try
        {
            if ( dados.next() )
            {
                Double val = dados.getDouble("saldoEmCaixa");
                result = val;
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro obter o saldo");
        }
        finally
        {
            dbCaixa.fechar();
        }
        
        return result;
    }

    /**
     * Adiciona o valor correspondente ao pagamento de consumo realizado
     * por algum cliente
     * 
     * @param valor
     */
    public void adicionarValorAoCaixa( Double valor )
    {
        Double saldoAtual = this.obterSaldoCaixa();
        Double saldoAtualizado = saldoAtual + valor;
        
        String sql = "UPDATE caixa SET(saldoEmCaixa) = ("
                + saldoAtualizado + ") WHERE caixaaberto IS TRUE";
        
        dbCaixa.conectar();
        dbCaixa.executarSQL(sql);
        dbCaixa.fechar();
    }
}
