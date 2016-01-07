package Restaurante.model;

import Restaurante.controladores.ControladorMesa;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MesaTableModel implements TableModel
{
    /**
     * Coleção de produtos.
     */
    private ArrayList<ControladorMesa> lista;
    
    /**
     * Construtor principal.
     * @param lista 
     */
    public MesaTableModel(ArrayList<ControladorMesa> lista)
    {
        this.lista = lista;
    }
    
    /**
     * Obter quantidade de linhas.
     * 
     * @return 
     */
    @Override
    public int getRowCount()
    {
        return lista.size();
    }

    /**
     * Obter número de colunas.
     * 
     * @return 
     */
    @Override
    public int getColumnCount()
    {
        return 2;
    }

    /**
     * Obter título da coluna.
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public String getColumnName(int columnIndex)
    {
        String name = "";
        if ( columnIndex == 0 )
        {
            name = "Código";
        }
        else if ( columnIndex == 1 )
        {
            name = "Aberta";
        }
        
        return name;
    }

    /**
     * Seta classe da coluna.
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        // Somente a última coluna é string
        if ( columnIndex == 1 )
        {
            return String.class;
        }
        
        return Integer.class;
    }

    /**
     * Células não são editáveis.
     * 
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    /**
     * Obtem valor na posição.
     * 
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ControladorMesa linha = lista.get(rowIndex);
        
        if ( columnIndex == 0 )
        {
            return linha.getMesaId();
        }
        else if ( columnIndex == 1 )
        {
            boolean aberta = linha.isMesaAberta();

            if  ( aberta )
            {
                return "Sim";
            }
            else
            {
                return "Não";
            }
        }
        
        return null;
    }

    /**
     * Não implementado.
     * 
     * @param aValue
     * @param rowIndex
     * @param columnIndex 
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    /**
     * Não implementado.
     * 
     * @param l 
     */
    @Override
    public void addTableModelListener(TableModelListener l)
    {
    }

    /**
     * Não implementado.
     * 
     * @param l 
     */
    @Override
    public void removeTableModelListener(TableModelListener l)
    {
    }  
}
