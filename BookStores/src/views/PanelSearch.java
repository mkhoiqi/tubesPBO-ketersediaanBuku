/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import inc.Database;
import static inc.Database.con;
import static inc.Database.stm;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class PanelSearch extends javax.swing.JPanel {
    public DefaultTableModel model;
    /**
     * Creates new form PanelSearch
     */
    public PanelSearch() throws SQLException{
        initComponents();
        model = new DefaultTableModel();
        tableBuku.setModel(model);
        
        model.addColumn("ISBN");
        model.addColumn("Judul Buku");
        model.addColumn("Penulis");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Tempat Terbit");
        model.addColumn("Harga");
        model.addColumn("Stock Tersedia");
        model.addColumn("Lokasi");
        
        tampilkanTable("SELECT * FROM book");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableBuku = new javax.swing.JTable();
        tfSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(630, 500));

        tableBuku.setBackground(new java.awt.Color(204, 255, 255));
        tableBuku.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jScrollPane1.setViewportView(tableBuku);

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(54, 33, 89));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Search!");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_search_16px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setText("(Masukan judul buku, penerbit, atau nama penulis)");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        String search = tfSearch.getText();
        tfSearch.setText("");
        try {
            tampilkanTable("SELECT * FROM book WHERE judul_buku LIKE '%"+search+"%' OR pengarang LIKE '%"+search+"%' OR penerbit LIKE '%"+search+"%'");
        } catch (SQLException ex) {
            Logger.getLogger(PanelSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBuku;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
public void tampilkanTable(String query) throws SQLException {
        Database db = new Database();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ResultSet rs = db.tampilBuku(query);  
        try{
            while(rs.next()){
            Object[] data = new Object[9];
            data[0]=rs.getString("isbn");
            data[1]=rs.getString("judul_buku");
            data[2]=rs.getString("pengarang");
            data[3]=rs.getString("penerbit");
            data[4]=rs.getString("tahun_terbit");
            data[5]=rs.getString("tempat_terbit");
            data[6]=rs.getString("harga");
            data[7]=rs.getString("jumlah_stok");
            data[8]=rs.getString("lokasi_penempatan");
            model.addRow(data);
         }   
        }catch(SQLException e){
            System.out.println("gagal");
        }
    }

    
}