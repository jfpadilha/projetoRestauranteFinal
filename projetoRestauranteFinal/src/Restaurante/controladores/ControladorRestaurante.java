/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.controladores;

import java.util.ArrayList;
import java.util.ResourceBundle;

/*
 * @author Joao F P S
 * @author Luis Felipe W.
 * @author Alex Roveda
 * 
 * @since 10/06/2015
 */

public class ControladorRestaurante 
{
   /**
    * Coleção de produtos.
    */
    private ArrayList<ControladorProduto> listaProdutos;

    /**
     * Coleção de mesas.
     */
    private ArrayList<ControladorMesa> listaMesas;
    
    /**
     * Controlador de caixa.
     */
    private ControladorCaixa caixa;
    
    /**
     * Controlador de consumo.
     */
    private ControladorConsumo consumo;
    
    /**
     * Construtor principal.
     */
    public ControladorRestaurante() 
    {
        caixa = new ControladorCaixa();
        consumo = new ControladorConsumo();
        
        this.carregarProdutos();
        this.carregarMesas();
    }
    
    /** 
     * Carrega produtos.
     */
    private void carregarProdutos()
    {
        // Cria tabela de produtos
        ControladorProduto produto = new ControladorProduto();
        
        // Obtem todos os produtos
        this.listaProdutos = produto.listarTodosOsProdutos();
    }
    
    /**
     * Carrega mesas.
     */
    private void carregarMesas()
    {
        // Cria tabela de produtos
        ControladorMesa mesa = new ControladorMesa();
        
        // Obtem todos os produtos
        this.listaMesas = mesa.listarMesas();
    }

    /**
     * Obter lista de produtos.
     * 
     * @return 
     */
    public ArrayList<ControladorProduto> getListaProdutos() 
    {
        carregarProdutos();
        return listaProdutos;
    }

    /**
     * Obter lista de mesas.
     * 
     * @return 
     */
    public ArrayList<ControladorMesa> getListaMesas() 
    {
        carregarMesas();
        return listaMesas;
    }
    
    /**
     * Retorna o controlador de caixa.
     * 
     * @return 
     */
    public ControladorCaixa getCaixa() 
    {
        return caixa;
    }

    /**
     * Retorna o controlador de consumo.
     * 
     * @return 
     */
    public ControladorConsumo getConsumo() {
        return consumo;
    }
    
    /**
     * Insere novo produto.
     * 
     * @param descricao
     * @param preco 
     */
    public void novoProduto(String descricao, double preco)
    {
        ControladorProduto produto = new ControladorProduto();
        produto.adicionarProduto(descricao, preco);
    }

    /**
     * Salvar produto.
     * 
     * @param idProduto
     * @param descricao
     * @param preco 
     */
    public void salvarProduto(int idProduto, String descricao, double preco)
    {
        ControladorProduto produto = new ControladorProduto();
        produto.editarProduto(idProduto, descricao, preco);
    }
    
    /**
     * Gera uma nova mesa.
     */
    public void novaMesa()
    {
        ControladorMesa mesa = new ControladorMesa();
        mesa.criarMesa();
    }
}
