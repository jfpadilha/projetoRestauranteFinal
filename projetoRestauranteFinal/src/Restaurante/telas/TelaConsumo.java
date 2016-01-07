/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.telas;

import Restaurante.controladores.ControladorRestaurante;
import Restaurante.model.ConsumoTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author LuísFelipe
 */
public class TelaConsumo extends javax.swing.JFrame
{

    /**
     * Código da mesa que está sendo editada.
     */
    private int mesaId;

    /**
     * Controlador do consumo.
     */
    private ControladorRestaurante restaurante;

    /**
     * Creates new form TelaConsumo
     */
    public TelaConsumo()
    {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Setar id da mesa sendo editada.
     *
     * @param mesaId
     */
    public void setMesaId( int mesaId )
    {
        this.mesaId = mesaId;
        lblMesa.setText("MESA " + mesaId);
        setTitle("MESA " + mesaId);
        carregarTabelaConsumo();
    }

    /**
     * Setar controlador de restaurante.
     *
     * @param restaurante
     */
    public void setRestaurante( ControladorRestaurante restaurante )
    {
        this.restaurante = restaurante;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMesa = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsumo = new javax.swing.JTable();
        btnAddProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMesa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMesa.setText("MESA");

        btnCancel.setText("Fechar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblConsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblConsumo);

        btnAddProduto.setText("Adicionar produto");
        btnAddProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAddProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddProduto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMesa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento do botão "Cancelar".
     *
     * @param evt
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * Evento ao clicar no botão "Adicionar produto".
     *
     * @param evt
     */
    private void btnAddProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProdutoActionPerformed

        if ( restaurante.getListaProdutos().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "Cadastre pelo menos um produto na tela inicial!");
        }
        else
        {
            int idProduto = 0;
            boolean eInt = false;

            while ( !eInt )
            {
                try
                {
                    idProduto = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código do produto que quer adicionar ao consumo: "));
                    eInt = true;

                    if ( restaurante.getListaProdutos().get(0).localizarProduto(idProduto) == null )
                    {
                        eInt = false;
                        JOptionPane.showMessageDialog(null, "Este código não existe, digite um válido.");
                    }
                }
                catch ( NumberFormatException n )
                {
                    JOptionPane.showMessageDialog(null, "Digite um código válido!");
                }
            }

            int quantProduto = 0;
            eInt = false;

            while ( !eInt )
            {
                try
                {
                    quantProduto = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade do produto: "));
                    eInt = true;
                }
                catch ( NumberFormatException n )
                {
                    JOptionPane.showMessageDialog(null, "Digite uma quantidade válida!");
                }
            }

            restaurante.getConsumo().adicionarConsumo(idProduto, mesaId, quantProduto);
            restaurante.getListaMesas().get(0).abrirMesa(mesaId);
            carregarTabelaConsumo();
        }
    }//GEN-LAST:event_btnAddProdutoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
            {
                if ( "Nimbus".equals(info.getName()) )
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch ( ClassNotFoundException ex )
        {
            java.util.logging.Logger.getLogger(TelaConsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch ( InstantiationException ex )
        {
            java.util.logging.Logger.getLogger(TelaConsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch ( IllegalAccessException ex )
        {
            java.util.logging.Logger.getLogger(TelaConsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch ( javax.swing.UnsupportedLookAndFeelException ex )
        {
            java.util.logging.Logger.getLogger(TelaConsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new TelaConsumo().setVisible(true);
            }
        });
    }

    /**
     * Ajusta tabela de consumo.
     */
    private void carregarTabelaConsumo()
    {
        ConsumoTableModel modelConsumo = new ConsumoTableModel(restaurante.getConsumo().listarConsumosDaMesa(mesaId));
        tblConsumo.setModel(modelConsumo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduto;
    private javax.swing.JButton btnCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JTable tblConsumo;
    // End of variables declaration//GEN-END:variables
}