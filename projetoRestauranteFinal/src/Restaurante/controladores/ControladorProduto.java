package Restaurante.controladores;

import core.BancoDados;
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
public class ControladorProduto
{

    private int produtoId;
    private String descricao;
    private Double preco;
    private BancoDados dbProduto;

    //Construtor padrao
    public ControladorProduto( String descricao, Double preco )
    {
        this.descricao = descricao;
        this.preco = preco;
    }

    //construtor privado para ser utilizado somente na forma "this"
    private ControladorProduto( int produtoId, String descricao, Double preco )
    {
        this.produtoId = produtoId;
        this.descricao = descricao;
        this.preco = preco; 
    }

    //Construtor que inicializa a base e criar a tabela caso nao exista
    public ControladorProduto()
    {
        dbProduto = new BancoDados("Restaurante");
        dbProduto.conectar();
        dbProduto.executarSQL("CREATE TABLE IF NOT EXISTS produto(produtoId SERIAL PRIMARY KEY, descricao VARCHAR (255) NOT NULL, preco NUMERIC NOT NULL)");
        dbProduto.fechar();
    }

    //Sets
    private void setProdutoId( int produtoId )
    {
        this.produtoId = produtoId;
    }

    private void setDescricao( String descricao )
    {
        this.descricao = descricao;
    }

    private void setPreco( Double preco )
    {
        this.preco = preco;
    }

    //Gets
    public int getProdutoId()
    {
        return produtoId;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public Double getPreco()
    {
        return preco;
    }

    //////Metodos de controle da base de dados
    /**
     * verifica o maior numero de codigo de produto já existente e soma 1 para
     * salvar o novo código do próximo produto
     *
     * @return int
     */
    public int obterProximoProdutoId()
    {
        String sql = "SELECT MAX(produtoId) AS maiorCod FROM produto";
        int cod = 0;

        dbProduto.conectar();
        ResultSet dados = dbProduto.executarConsultaSQL(sql);
        try
        {
            if ( dados.next() )
            {
                cod = dados.getInt("maiorCod") + 1;
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro!");
        }
        finally
        {
            dbProduto.fechar();
        }

        return cod;
    }

    /**
     * Salva um novo produto na base de dados informar a descricao e o preco
     *
     * @param descricao
     * @param preco
     */
    public void adicionarProduto( String descricao, Double preco )
    {
        int cod = obterProximoProdutoId();
        String sql = "INSERT INTO produto (produtoId, descricao, preco) VALUES ('"
                + cod + "', '"
                + descricao + "', '"
                + preco + "')";

        dbProduto.conectar();
        dbProduto.executarSQL(sql);
        dbProduto.fechar();
    }

    /**
     * salva um novo produto na base de dados informar um objeto do tipo
     * ControladorProduto
     *
     * @param produto
     */
    public void adicionarProduto( ControladorProduto produto )
    {
        int cod = obterProximoProdutoId();
        String sql = "INSERT INTO produto VALUES ('"
                + cod + "', '"
                + produto.getDescricao() + "', '"
                + produto.getPreco() + "')";

        dbProduto.conectar();
        dbProduto.executarSQL(sql);
        dbProduto.fechar();
    }

    /**
     * Procura por um produto pelo codigo
     *
     * @param produtoId
     * @return produto
     */
    public ControladorProduto localizarProduto( int produtoId )
    {
        dbProduto = new BancoDados("Restaurante");
        String sql = "SELECT * FROM produto WHERE produtoId = '" + produtoId + "'";
        dbProduto.conectar();
        ResultSet dados = dbProduto.executarConsultaSQL(sql);
        ControladorProduto p = null;
        try
        {
            if ( dados.next() )
            {
                int cod = dados.getInt("produtoid");
                String desc = dados.getString("descricao");
                Double pre = dados.getDouble("preco");

                p = new ControladorProduto(cod, desc, pre);
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao localizar produto!");
        }
        finally
        {
            dbProduto.fechar();
        }
        return p;
    }

    /**
     * Faz um select na base e traz uma lista de todos os produtos que tiverem
     * por la
     *
     * @return arrayList
     */
    public ArrayList<ControladorProduto> listarTodosOsProdutos()
    {
        ArrayList<ControladorProduto> lista = new ArrayList<>();
        ControladorProduto listaAux = null;

        String sql = "SELECT * FROM produto ORDER BY produtoId";
        dbProduto.conectar();
        ResultSet dados = dbProduto.executarConsultaSQL(sql);
        try
        {
            while ( dados.next() )
            {
                int cod = dados.getInt("produtoid");
                String desc = dados.getString("descricao");
                Double pre = dados.getDouble("preco");

                listaAux = new ControladorProduto(cod, desc, pre);
                lista.add(listaAux);
            }
        }
        catch ( Exception e )
        {
            System.out.println("Erro ao criar lista de produtos!");
        }
        finally
        {
            dbProduto.fechar();
        }
        return lista;
    }

    /**
     * Atualizar um produto passando codigo e um objeto
     *
     * @param produtoId
     * @param produto
     * @return boolean
     */
    public boolean editarProduto( int produtoId, ControladorProduto produto )
    {
        boolean result = false;

        if ( localizarProduto(produtoId) != null )
        {
            String sql = "UPDATE produto SET(descricao, preco) = ( '"
                    + produto.getDescricao() + "','"
                    + produto.getPreco() + "') WHERE produtoid = '"
                    + produtoId + "'";
            dbProduto.conectar();
            dbProduto.executarSQL(sql);
            dbProduto.fechar();
            result = true;
        }
        return result;
    }

    /**
     * atualiza um produto passando todos os dados separadamente
     *
     * @param produtoId
     * @param descricao
     * @param preco
     * @return boolean
     */
    public boolean editarProduto( int produtoId, String descricao, Double preco )
    {
        boolean result = false;

        if ( localizarProduto(produtoId) != null )
        {
            String sql = "UPDATE produto SET(descricao, preco) = ( '"
                    + descricao + "', '"
                    + preco + "') WHERE produtoid = '" + produtoId + "'";
            dbProduto.conectar();
            dbProduto.executarSQL(sql);
            dbProduto.fechar();
            result = true;
        }
        return result;
    }

    
    /**
     * Apaga um produto
     * 
     * @param produtoId
     * @return 
     */
    public boolean removerProduto( int produtoId )
    {
        boolean result = false;
        
        dbProduto = new BancoDados("Restaurante");
        
        if ( localizarProduto(produtoId) != null )
        {
            // Pode ser que esteja sendo consumido, daí não pode excluir
            String sql = "SELECT produtoId FROM consumo WHERE produtoId = '" + produtoId  + "'";
            dbProduto.conectar();
            ResultSet dados = dbProduto.executarConsultaSQL(sql);
            
            try 
            {
                if ( dados.next() )
                {
                    return false;
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro ao verificar se produto está sendo consumido.");
            }
            
            sql = "DELETE FROM produto "
                    + "WHERE produtoid = '"
                    + produtoId + "'";
            dbProduto.conectar();
            dbProduto.executarSQL(sql);
            dbProduto.fechar();
            result = true;
        }

        return result;
    }

}
