package Restaurante.controladores;

import core.BancoDados;
import java.sql.ResultSet;
import core.Data;
import java.util.ArrayList;
/*
 * @author Joao F P S
 * @author Luis Felipe W.
 * @author Alex Roveda
 * 
 * @since 10/06/2015
 */

public class ControladorConsumo
{

    private int produtoId;
    private int mesaId;
    private int quantProduto;
    private Data dataConsumo;
    private BancoDados dbConsumo;

    //construtor usado somente como this
    private ControladorConsumo( int produtoId, int mesaId, int quantProduto, Data dataConsumo )
    {
        this.produtoId = produtoId;
        this.mesaId = mesaId;
        this.quantProduto = quantProduto;
    }

    //construtor padrao
    public ControladorConsumo( int produtoId, int mesaId, int quantProduto )
    {
        this.produtoId = produtoId;
        this.mesaId = mesaId;
        this.quantProduto = quantProduto;
    }

    //Construtor que inicializa a base e criar a tabela caso ainda nao exista
    public ControladorConsumo()
    {
        dbConsumo = new BancoDados("Restaurante");
        dbConsumo.conectar();
        dbConsumo.executarSQL("CREATE TABLE IF NOT EXISTS consumo(produtoId INT NOT NULL REFERENCES produto (produtoId), mesaId INT NOT NULL REFERENCES mesa (mesaId), quant INT NOT NULL, dataconsumo DATE)");
        dbConsumo.fechar();
    }

    //Sets
    private void setMesaId( int mesaId )
    {
        this.mesaId = mesaId;
    }

    private void setQuantProduto( int quantProduto )
    {
        this.quantProduto = quantProduto;
    }

    private void setDataConsumo( Data dataConsumo )
    {
        this.dataConsumo = dataConsumo;
    }

    private void setProdutoId( int produtoId )
    {
        this.produtoId = produtoId;
    }

    //Gets
    public int getProdutoId()
    {
        return produtoId;
    }

    public int getMesaId()
    {
        return mesaId;
    }

    public int getQuantProduto()
    {
        return quantProduto;
    }

    public Data getDataConsumo()
    {
        return dataConsumo;
    }

///////Metodos de controle para a base de dados
    
    /**
     * Adicionar um consumo informando os itens separadamente
     * 
     * @param produtoId
     * @param mesaId
     * @param qtdProduto 
     */
    public void adicionarConsumo( int produtoId, int mesaId, int qtdProduto )
    {
        Data datacons = new Data();

        String sql = "INSERT INTO consumo(produtoid, mesaid, quant, dataConsumo) VALUES('"
                + produtoId + "', '"
                + mesaId + "', '"
                + qtdProduto + "', '"
                + datacons.obterData() + "')";

        dbConsumo.conectar();
        dbConsumo.executarSQL(sql);
        dbConsumo.fechar();
    }

    /**
     * adicionar um consumo passando um objeto do tipo ControladorConsumo
     * 
     * @param consumo 
     */
    public void adicionarConsumo( ControladorConsumo consumo )
    {
        Data datacons = new Data();

        String sql = "INSERT INTO consumo(produtoid, mesaid, quant, dataConsumo) VALUES('"
                + consumo.produtoId + "', '"
                + consumo.mesaId + "', '"
                + consumo.quantProduto + "', '"
                + datacons.obterData() + "')";

        dbConsumo.conectar();
        dbConsumo.executarSQL(sql);
        dbConsumo.fechar();
    }

    /**
     * Faz um select na base e retorna um arrayList (lista) com todos os dados
     * consumidos na mesa cujo codigo foi informado
     * 
     * @param mesaId
     * @return arrayList
     */
    public ArrayList<ControladorConsumo> listarConsumosDaMesa( int mesaId )
    {
        ArrayList<ControladorConsumo> lista = new ArrayList<>();
        ControladorConsumo listaAux = null;

        String sql = "SELECT * FROM consumo WHERE mesaId = " + mesaId;
        dbConsumo.conectar();
        ResultSet dados = dbConsumo.executarConsultaSQL(sql);
        try
        {
            while ( dados.next() )
            {
                int cod = dados.getInt("produtoid");
                int mesa = dados.getInt("mesaid");
                int qtProd = dados.getInt("quant");
                String d = dados.getString("dataconsumo");

                Data dt = new Data(d);

                listaAux = new ControladorConsumo(cod, mesa, qtProd, dt);
                lista.add(listaAux);
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao listar consumo!");
        }
        finally
        {
            dbConsumo.fechar();
        }
        return lista;
    }

    /**
     * Elmina todos os consumos efetuados na mesa e libera a mesa para ser
     * usada para novas pessoas
     * 
     * @param mesaId 
     */
    public void limparConsumos( int mesaId )
    {
        String sql = "DELETE FROM consumo "
                + "WHERE mesaid = "
                + mesaId;
        dbConsumo.conectar();
        dbConsumo.executarSQL(sql);
        dbConsumo.fechar();
    }
}
