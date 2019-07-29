
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTML;
import oracle.jdbc.pool.OracleDataSource;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import static javax.xml.ws.Endpoint.publish;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */



public final class Manager extends javax.swing.JFrame {
    /**
     * Creates new form Manager
     */
    String srtUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
    String strUser = "scott";
    String strPass = "tiger";
    private DefaultTableModel model;
    private DefaultTableModel bBill;
    private DefaultTableModel chmodel;
    private DefaultTableModel Casemodel;
    private DefaultTableModel importmodel;
    private DefaultTableModel powermodel;
    private DefaultTableModel Hpmodel;
    private DefaultTableModel HistoryModel;
    Color gray = new Color(107, 119, 141);
    Color red = new Color(255, 103, 104);
    Color Dark_blue = new Color(23, 34, 59);
    Color Light_blue = new Color (38, 56, 89);
    Object billColumn[]={"Barcode","Item Type","Description","Quantity","price"};
    Object column[]={"Manufacture","Phone Name","supplier","Selling Price","purchase Price","Quantity"};
    Object ChColumn[]={"Quality","Type","Supplier","Selling Price","purchase Price","Quantity"};
    Object caseColumn[]={"Material","Phone Type","Color","supplier","Selling Price","purchase Price","Quantity"};
    Object importColumn[]={"FirstName","LastName","PhoneNo.","Product_ID"};
    Object powerColumn[]={"Manufacture","Capacity","Supplier","Selling Price","purchase Price","Quantity"};
    Object HpColumn[]={"Manufacture","Connectivity","Supplier","Selling Price","purchase Price","Quantity"};
    Object HistoryTableTitles[]={"Item Name","Item Description","Quantity","Total Selling Price","Total Profit"};
    Object HistoryRow[]= new Object[5];
    Object Hprow[]=new Object[6];
    Object row[]= new Object[6];
    Object Chrow[]=new Object[6];
    Object caserow[]=new Object[7];
    Object importrow[]=new Object[4];
    Object powerrow[]=new Object[6];
    Object billrow[]=new Object[5];
    DefaultListModel defaultListMod ;
    private static final String COMMIT_ACTION = "commit";
    
    int xp;
    int yp;
    NewAccount customer;
    ArrayList <Supplier> Suppliers = new ArrayList <Supplier>();
     ArrayList<Product> products= new ArrayList<Product>();
    public void addName (String firstname , String lastname){
        char [] firstNameArr = firstname.toCharArray();
        char [] lastNameArr = lastname.toCharArray();
        Character newa = Character.toUpperCase(firstNameArr[0]);        
        firstNameArr[0] = newa;
        Character newb = Character.toUpperCase(lastNameArr[0]);
        lastNameArr[0] = newb;
        String newFirstname = new String(firstNameArr);
        String newlastname = new String(lastNameArr);
        System.out.print(newFirstname +" "+newlastname);
        this.Welcome.setText("Welcome "+newFirstname+" "+newlastname+" !");
    }
   
     public void addsuppliersfromDB(){
        try{
         OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        while(rs.next()){
                        Suppliers.add(new Supplier( rs.getString(1) , rs.getString(2) , Integer.parseInt(rs.getString(3)) , Integer.parseInt(rs.getString(4)) ));
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
      
    }
       public void addproductfromDB(){
        try{
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from product";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        while(rs.next()){
                        products.add(new Product(Integer.parseInt(rs.getString(1)) , Integer.parseInt(rs.getString(2)) , Integer.parseInt(rs.getString(3)) , Integer.parseInt(rs.getString(4)),Integer.parseInt(rs.getString(5)) ));
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }}
    public void ChangeHistoryTable(String strDate){
          while(HistoryModel.getRowCount()>0){
           HistoryModel.removeRow(0);
       }
        
        try{
            OracleDataSource ods = new OracleDataSource();
            ods.setURL(srtUrl);
            ods.setUser(strUser);
            ods.setPassword(strPass);
            java.sql.Connection conn = ods.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            String SelectStatement ="select * from bill where datep = '"+strDate+"'";
            ResultSet rs = stmt.executeQuery(SelectStatement);
            while(rs.next()){
                  this.HistoryRow[0]=(rs.getString(4));
                  this.HistoryRow[1]=(rs.getString(1));
                  this.HistoryRow[2]=rs.getString(5);
                  this.HistoryRow[3]=Integer.parseInt(rs.getString(6));
                  this.HistoryRow[4]=Integer.parseInt(rs.getString(7));
                  this.HistoryModel.addRow(HistoryRow);
            }
            conn.commit();
            conn.close();
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
    }
    public Manager() {
        initComponents();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) this.jDateChooser1.getDateEditor();
        editor.setEditable(false);
        this.jDateChooser1.getDateEditor().addPropertyChangeListener(  new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            if ("date".equals(e.getPropertyName())) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                     String strDate = dateFormat.format((Date) e.getNewValue());  
                System.out.println( strDate  );
                ChangeHistoryTable(strDate);
            }
        } 
    });
        addsuppliersfromDB();
        addproductfromDB();
        currencyRates();
       

        //item.setText(strUser);
        //this.sendtobillfromphone.setEnabled(false);
        bBill = new DefaultTableModel();
        bBill.setColumnIdentifiers(this.billColumn);
        BillTable.setModel(bBill);
        powermodel = new DefaultTableModel();
        powermodel.setColumnIdentifiers(powerColumn);
        PowerTable.setModel(powermodel);
        Hpmodel = new DefaultTableModel();
        Hpmodel.setColumnIdentifiers(HpColumn);
        HPTable.setModel(Hpmodel);
        importmodel=new DefaultTableModel();
        importmodel.setColumnIdentifiers(importColumn);
        importTable.setModel(importmodel);
        chmodel=new DefaultTableModel();
        chmodel.setColumnIdentifiers(ChColumn);
        ChargerTable.setModel(chmodel);
        Casemodel= new DefaultTableModel();
        Casemodel.setColumnIdentifiers(caseColumn);
        CasesTable.setModel(Casemodel);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        HistoryModel = new DefaultTableModel();
        HistoryModel.setColumnIdentifiers(HistoryTableTitles);
        Historytable.setModel(HistoryModel);
        Table.setModel(model);
        home.setForeground(Light_blue);
        home.setBackground(red);
        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        
      //tables
      //to select one row only
      this.Table.setSelectionModel(new ForcedListSelectionModel());
      this.ChargerTable.setSelectionModel(new ForcedListSelectionModel());  
      this.CasesTable.setSelectionModel(new ForcedListSelectionModel());
      this.HPTable.setSelectionModel(new ForcedListSelectionModel());
      this.PowerTable.setSelectionModel(new ForcedListSelectionModel());
    }
    public void currencyRates(){
         try {
            URL url = new URL("https://free.currconv.com/api/v7/convert?q=USD_ILS,JOD_ILS,GBP_ILS&compact=ultra&apiKey=dd7b3e28e32b4baa1b37");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            String info[];
            DateFormat m = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date=new Date();
                
                
                
            while ((output = br.readLine()) != null) {
                
                info=output.split(":|,");
                for(String l: info){
                  System.out.println(l);

                }
                JOD.setText(info[3]+" ₪");
                USD.setText(info[1]+" ₪");
                GBP.setText(info[5].substring(0,info[5].length()-1)+" ₪");
                            


                Date.setText(m.format(date));
            } 
            
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Welcome = new javax.swing.JLabel();
        Min = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        Phones = new javax.swing.JLabel();
        accessories = new javax.swing.JLabel();
        Charger = new javax.swing.JLabel();
        Cases = new javax.swing.JLabel();
        Headphones = new javax.swing.JLabel();
        powerbank = new javax.swing.JLabel();
        importers = new javax.swing.JLabel();
        Bill = new javax.swing.JLabel();
        History = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        USD = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JOD = new javax.swing.JLabel();
        Update = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        GBP = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Acc = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        chargerqualitycombobox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        chargertypecombobox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        sup = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ChargerTable = new javax.swing.JTable();
        ChSave = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        ChargerBuyingPrice = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        ChargerSellingPrice = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        ChargerQuantity = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        sendtobillfromcharger = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        CaseBuyngPrice = new javax.swing.JTextField();
        Type = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        CasesTable = new javax.swing.JTable();
        CaseSave = new javax.swing.JButton();
        Material = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        colorcombobox = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        CaseSellingPrice = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        CaseQuantity = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        supCase = new javax.swing.JComboBox<>();
        sendtobillfromcase = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        HPmanufacture = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        HPConnect = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        HPBuyingprice = new javax.swing.JTextField();
        HPSave = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        HPTable = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        HPSupplier = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        HPSellingPrice = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        HPQuantity = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        sendtobillfromHP = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        PBManufacture = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        PBBuyingPrice = new javax.swing.JTextField();
        Cap = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        PowerTable = new javax.swing.JTable();
        PowerBankSave = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        PBS = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        PBSellingPrice = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        PBQuantity = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        sendtobillfromPB = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        add_phone = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        PhoneM1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        phoneName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        PhoneSupp = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        buyingorice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        sell_price = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        PhoneQuantity = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        sendtobillfromphone = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Sfirstname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        SlastName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        Sphonenumber = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        importTable = new javax.swing.JTable();
        saveSupplier = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SupplementType = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        BillTable = new javax.swing.JTable();
        totalprice = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        SaveBill = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Historytable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(23, 34, 59));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");

        Welcome.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        Welcome.setForeground(new java.awt.Color(255, 255, 255));
        Welcome.setText("Welcome");

        Min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/minus.png"))); // NOI18N
        Min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinMouseClicked(evt);
            }
        });

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/error.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel4MouseDragged(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Welcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1478, Short.MAX_VALUE)
                .addComponent(Min)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(close)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1706, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close)
                    .addComponent(Min))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Welcome, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1720, 90));

        jPanel2.setBackground(new java.awt.Color(38, 56, 89));

        home.setBackground(new java.awt.Color(38, 56, 89));
        home.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        home.setForeground(new java.awt.Color(107, 119, 141));
        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/home2.png"))); // NOI18N
        home.setText("Home");
        home.setOpaque(true);
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });

        Phones.setBackground(new java.awt.Color(38, 56, 89));
        Phones.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Phones.setForeground(new java.awt.Color(107, 119, 141));
        Phones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/smartphone.png"))); // NOI18N
        Phones.setText("Phones");
        Phones.setOpaque(true);
        Phones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhonesMouseClicked(evt);
            }
        });

        accessories.setBackground(new java.awt.Color(38, 56, 89));
        accessories.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        accessories.setForeground(new java.awt.Color(107, 119, 141));
        accessories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/mobile.png"))); // NOI18N
        accessories.setText("Accessories");
        accessories.setOpaque(true);
        accessories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accessoriesMouseClicked(evt);
            }
        });

        Charger.setBackground(new java.awt.Color(38, 56, 89));
        Charger.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Charger.setForeground(new java.awt.Color(107, 119, 141));
        Charger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Charger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rechargeable-battery.png"))); // NOI18N
        Charger.setText("Chargers");
        Charger.setOpaque(true);
        Charger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChargerMouseClicked(evt);
            }
        });

        Cases.setBackground(new java.awt.Color(38, 56, 89));
        Cases.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Cases.setForeground(new java.awt.Color(107, 119, 141));
        Cases.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/phone-case.png"))); // NOI18N
        Cases.setText("Cases");
        Cases.setOpaque(true);
        Cases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CasesMouseClicked(evt);
            }
        });

        Headphones.setBackground(new java.awt.Color(38, 56, 89));
        Headphones.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Headphones.setForeground(new java.awt.Color(107, 119, 141));
        Headphones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Headphones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/headphones.png"))); // NOI18N
        Headphones.setText("Headphones");
        Headphones.setOpaque(true);
        Headphones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HeadphonesMouseClicked(evt);
            }
        });

        powerbank.setBackground(new java.awt.Color(38, 56, 89));
        powerbank.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        powerbank.setForeground(new java.awt.Color(107, 119, 141));
        powerbank.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        powerbank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/smartphone-charger.png"))); // NOI18N
        powerbank.setText("Powerbanks");
        powerbank.setOpaque(true);
        powerbank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                powerbankMouseClicked(evt);
            }
        });

        importers.setBackground(new java.awt.Color(38, 56, 89));
        importers.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        importers.setForeground(new java.awt.Color(107, 119, 141));
        importers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/trolley.png"))); // NOI18N
        importers.setText("Importers");
        importers.setOpaque(true);
        importers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importersMouseClicked(evt);
            }
        });

        Bill.setBackground(new java.awt.Color(38, 56, 89));
        Bill.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Bill.setForeground(new java.awt.Color(107, 119, 141));
        Bill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/online-store.png"))); // NOI18N
        Bill.setText("Bill");
        Bill.setOpaque(true);
        Bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillMouseClicked(evt);
            }
        });

        History.setBackground(new java.awt.Color(38, 56, 89));
        History.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        History.setForeground(new java.awt.Color(107, 119, 141));
        History.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/history-clock-button.png"))); // NOI18N
        History.setText("History");
        History.setOpaque(true);
        History.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoryMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(accessories, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(Phones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Charger, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Cases, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Headphones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(powerbank, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(importers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(History, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(home)
                .addGap(18, 18, 18)
                .addComponent(Bill)
                .addGap(18, 18, 18)
                .addComponent(History)
                .addGap(18, 18, 18)
                .addComponent(Phones)
                .addGap(18, 18, 18)
                .addComponent(importers)
                .addGap(18, 18, 18)
                .addComponent(accessories)
                .addGap(18, 18, 18)
                .addComponent(Charger)
                .addGap(18, 18, 18)
                .addComponent(Cases)
                .addGap(18, 18, 18)
                .addComponent(Headphones)
                .addGap(18, 18, 18)
                .addComponent(powerbank)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 78, 170, 550));

        jPanel4.setBackground(new java.awt.Color(23, 34, 59));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        USD.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        USD.setForeground(new java.awt.Color(255, 255, 255));
        USD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/united-states.png"))); // NOI18N
        USD.setText("USD");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/exchange.png"))); // NOI18N
        jLabel1.setText("Exchange Rates:");

        JOD.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        JOD.setForeground(new java.awt.Color(255, 255, 255));
        JOD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/jordan.png"))); // NOI18N
        JOD.setText("JOD");

        Update.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Update.setForeground(new java.awt.Color(255, 255, 255));
        Update.setText("Updated on");

        Date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 103, 104));

        GBP.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        GBP.setForeground(new java.awt.Color(255, 255, 255));
        GBP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/united-kingdom.png"))); // NOI18N
        GBP.setText("GBA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(USD)
                            .addComponent(JOD)
                            .addComponent(GBP)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Update)
                        .addGap(3, 3, 3)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1154, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(USD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JOD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GBP)
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Update)
                    .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(282, Short.MAX_VALUE))
        );

        tab.addTab("Home", jPanel4);

        jPanel6.setBackground(new java.awt.Color(23, 34, 59));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(23, 34, 59));

        jLabel10.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(107, 119, 141));
        jLabel10.setText("Add new Charger :");

        jLabel14.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(107, 119, 141));
        jLabel14.setText("Quality");

        chargerqualitycombobox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chargerqualitycombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Quailty", "Original", "First Copy", "Commerial" }));

        jLabel15.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(107, 119, 141));
        jLabel15.setText("Type");

        chargertypecombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select USB Type", "MicroUSB", "Type-C", "iPhone" }));
        chargertypecombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chargertypecomboboxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(107, 119, 141));
        jLabel16.setText("Supplier");

        sup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier", "Haitham Hewari", "Ahmad Qabalan", "", "" }));
        sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supActionPerformed(evt);
            }
        });

        ChargerTable.setAutoCreateRowSorter(true);
        ChargerTable.setBackground(new java.awt.Color(23, 34, 59));
        ChargerTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        ChargerTable.setForeground(new java.awt.Color(255, 255, 255));
        ChargerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ChargerTable.setRowHeight(20);
        jScrollPane2.setViewportView(ChargerTable);

        ChSave.setBackground(new java.awt.Color(107, 119, 141));
        ChSave.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ChSave.setText("Save");
        ChSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChSaveActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(107, 119, 141));
        jLabel34.setText("Buying Price");

        ChargerBuyingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChargerBuyingPriceActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(107, 119, 141));
        jLabel43.setText("Quantity");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(107, 119, 141));
        jLabel44.setText("Selling Price");

        jLabel56.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(107, 119, 141));
        jLabel56.setText("Chargers :");

        sendtobillfromcharger.setText("send to bill");
        sendtobillfromcharger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtobillfromchargerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chargerqualitycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chargertypecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ChargerBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ChargerSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ChargerQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ChSave)))
                        .addGap(240, 240, 240))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendtobillfromcharger)
                .addGap(263, 263, 263))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(chargertypecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ChSave)
                        .addComponent(jLabel34)
                        .addComponent(ChargerBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)
                        .addComponent(ChargerSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ChargerQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(chargerqualitycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sendtobillfromcharger)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Acc.addTab("Chargers", jPanel7);

        jPanel8.setBackground(new java.awt.Color(23, 34, 59));

        jLabel11.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(107, 119, 141));
        jLabel11.setText("Add new Case :");

        jLabel17.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(107, 119, 141));
        jLabel17.setText("Material");

        jLabel18.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(107, 119, 141));
        jLabel18.setText("Phone Model");

        jLabel19.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(107, 119, 141));
        jLabel19.setText("Buying Price");

        Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TypeActionPerformed(evt);
            }
        });

        CasesTable.setBackground(new java.awt.Color(23, 34, 59));
        CasesTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        CasesTable.setForeground(new java.awt.Color(255, 255, 255));
        CasesTable.setModel(new javax.swing.table.DefaultTableModel(
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
        CasesTable.setRowHeight(20);
        jScrollPane3.setViewportView(CasesTable);

        CaseSave.setBackground(new java.awt.Color(107, 119, 141));
        CaseSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CaseSave.setText("Save");
        CaseSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaseSaveActionPerformed(evt);
            }
        });

        Material.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Material", "Plastic", "Leather", "Silicone" }));

        jLabel32.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(107, 119, 141));
        jLabel32.setText("Color");

        colorcombobox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        colorcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Yellow", "Green", "Black", "White", "Brown", "Pink", "Blue", "Orange", "Violet", "Gray" }));
        colorcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorcomboboxActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(107, 119, 141));
        jLabel45.setText("Selling Price");

        jLabel46.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(107, 119, 141));
        jLabel46.setText("Quantity");

        jLabel55.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(107, 119, 141));
        jLabel55.setText("Cases :");

        jLabel57.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(107, 119, 141));
        jLabel57.setText("Supplier");

        supCase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supCase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier" }));

        sendtobillfromcase.setText("send to bill");
        sendtobillfromcase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtobillfromcaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel55)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supCase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CaseBuyngPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CaseSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CaseQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CaseSave)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendtobillfromcase)
                .addGap(108, 108, 108))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Material, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19)
                        .addComponent(CaseBuyngPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CaseSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32)
                        .addComponent(colorcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45)
                        .addComponent(CaseSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46)
                        .addComponent(CaseQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57)
                        .addComponent(supCase, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(sendtobillfromcase)
                .addGap(39, 39, 39))
        );

        Acc.addTab("Cases", jPanel8);

        jPanel9.setBackground(new java.awt.Color(23, 34, 59));

        jLabel20.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(107, 119, 141));
        jLabel20.setText("Manufacture");

        HPmanufacture.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HPmanufacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Maufacture", "AKG", "JBL", "Beats", " " }));

        jLabel13.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(107, 119, 141));
        jLabel13.setText("Connectivity ");

        HPConnect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HPConnect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Connectivity Type", "Wireless", "3.5mmWired", "Type-C Wired" }));

        jLabel22.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(107, 119, 141));
        jLabel22.setText("Buying Price");

        HPSave.setBackground(new java.awt.Color(107, 119, 141));
        HPSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HPSave.setText("Save");
        HPSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPSaveActionPerformed(evt);
            }
        });

        HPTable.setBackground(new java.awt.Color(23, 34, 59));
        HPTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        HPTable.setForeground(new java.awt.Color(255, 255, 255));
        HPTable.setModel(new javax.swing.table.DefaultTableModel(
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
        HPTable.setRowHeight(20);
        jScrollPane6.setViewportView(HPTable);

        jLabel31.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(107, 119, 141));
        jLabel31.setText("Add New Headphone :");

        jLabel40.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(107, 119, 141));
        jLabel40.setText("Supplier");

        HPSupplier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        HPSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier" }));

        jLabel47.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(107, 119, 141));
        jLabel47.setText("Selling Price");

        jLabel48.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(107, 119, 141));
        jLabel48.setText("Quantity");

        jLabel54.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(107, 119, 141));
        jLabel54.setText("Headphones :");

        sendtobillfromHP.setText("send to bill");
        sendtobillfromHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtobillfromHPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel54)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPmanufacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPBuyingprice, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(HPSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HPSave)))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendtobillfromHP)
                .addGap(205, 205, 205))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HPmanufacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(HPConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(HPBuyingprice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HPSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel40)
                    .addComponent(HPSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(HPSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(HPQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(sendtobillfromHP)
                .addGap(47, 47, 47))
        );

        Acc.addTab("Headphones", jPanel9);

        jPanel10.setBackground(new java.awt.Color(23, 34, 59));

        jLabel28.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(107, 119, 141));
        jLabel28.setText("Manufacture");

        PBManufacture.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PBManufacture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Manufacture", "Xiaomi", "Samsung", "Rock" }));

        jLabel12.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(107, 119, 141));
        jLabel12.setText("Capacity");

        jLabel29.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(107, 119, 141));
        jLabel29.setText("Supplier");

        PBBuyingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PBBuyingPriceActionPerformed(evt);
            }
        });

        PowerTable.setBackground(new java.awt.Color(23, 34, 59));
        PowerTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        PowerTable.setForeground(new java.awt.Color(255, 255, 255));
        PowerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        PowerTable.setRowHeight(20);
        jScrollPane5.setViewportView(PowerTable);

        PowerBankSave.setBackground(new java.awt.Color(107, 119, 141));
        PowerBankSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PowerBankSave.setText("Save");
        PowerBankSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PowerBankSaveActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(107, 119, 141));
        jLabel33.setText("Add new PowerBank :");

        jLabel41.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(107, 119, 141));
        jLabel41.setText("Buying Price");

        PBS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PBS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selcet Suppiler" }));

        jLabel49.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(107, 119, 141));
        jLabel49.setText("Selling Price");

        jLabel50.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(107, 119, 141));
        jLabel50.setText("Quantity");

        jLabel51.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(107, 119, 141));
        jLabel51.setText("PowerBanks");

        sendtobillfromPB.setText("Send to Bill");
        sendtobillfromPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtobillfromPBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel51)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PBManufacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PBBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PBSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PBQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PowerBankSave)))))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendtobillfromPB)
                .addGap(251, 251, 251))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(PBManufacture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(Cap, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(PBBuyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PowerBankSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(PBS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(PBSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(PBQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(sendtobillfromPB)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        Acc.addTab("powerbank", jPanel10);

        jPanel12.setBackground(new java.awt.Color(23, 34, 59));

        jPanel13.setBackground(new java.awt.Color(23, 34, 59));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Rolling-1s-94px.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(jLabel23)
                .addContainerGap(722, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel23)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1477, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 95, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 96, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 9, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        Acc.addTab("loading", jPanel12);

        jPanel6.add(Acc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 570));

        tab.addTab("Accessories", jPanel6);

        jPanel5.setBackground(new java.awt.Color(23, 34, 59));

        add_phone.setBackground(new java.awt.Color(107, 119, 141));
        add_phone.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        add_phone.setText("Save");
        add_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_phoneActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(107, 119, 141));
        jLabel5.setText("Add New Phone :");

        jLabel6.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(107, 119, 141));
        jLabel6.setText("Manufacture");

        PhoneM1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PhoneM1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Manufacture", "Samsung", "IPhone", "Mi" }));

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(107, 119, 141));
        jLabel3.setText("Phone Name");

        jLabel7.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(107, 119, 141));
        jLabel7.setText("Supplier");

        PhoneSupp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier" }));
        PhoneSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneSuppActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(107, 119, 141));
        jLabel8.setText("Buying Price");

        buyingorice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buyingoriceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buyingoriceKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(107, 119, 141));
        jLabel9.setText("Selling Price");

        sell_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sell_priceKeyTyped(evt);
            }
        });

        Table.setBackground(new java.awt.Color(23, 34, 59));
        Table.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        Table.setForeground(new java.awt.Color(255, 255, 255));
        Table.setModel(new javax.swing.table.DefaultTableModel(
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
        Table.setRowHeight(20);
        jScrollPane1.setViewportView(Table);

        jLabel39.setBackground(new java.awt.Color(107, 119, 141));
        jLabel39.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(107, 119, 141));
        jLabel39.setText("Qunatity");

        PhoneQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneQuantityActionPerformed(evt);
            }
        });
        PhoneQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PhoneQuantityKeyTyped(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(107, 119, 141));
        jLabel52.setText("Phones");

        sendtobillfromphone.setText("Send to Bill");
        sendtobillfromphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendtobillfromphoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhoneM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneName, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buyingorice, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sell_price, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhoneQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_phone))
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendtobillfromphone)
                .addGap(495, 495, 495))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(PhoneM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(phoneName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(PhoneSupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(buyingorice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(sell_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(PhoneQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_phone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(sendtobillfromphone)
                .addGap(55, 55, 55))
        );

        tab.addTab("Phone", jPanel5);

        jPanel3.setBackground(new java.awt.Color(23, 34, 59));

        Sfirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SfirstnameActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(107, 119, 141));
        jLabel24.setText("First name");

        jLabel26.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(107, 119, 141));
        jLabel26.setText("Last name");

        SlastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SlastNameActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(107, 119, 141));
        jLabel27.setText("Phone number");

        Sphonenumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SphonenumberActionPerformed(evt);
            }
        });

        importTable.setBackground(new java.awt.Color(23, 34, 59));
        importTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        importTable.setForeground(new java.awt.Color(255, 255, 255));
        importTable.setModel(new javax.swing.table.DefaultTableModel(
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
        importTable.setRowHeight(20);
        jScrollPane4.setViewportView(importTable);

        saveSupplier.setBackground(new java.awt.Color(107, 119, 141));
        saveSupplier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveSupplier.setText("Save");
        saveSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSupplierActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(107, 119, 141));
        jLabel30.setText("Add New Supplier :");

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 119, 141));
        jLabel2.setText("Supplement Type");

        SupplementType.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        SupplementType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Phones", "Chargers(Original)", "Chargers(First Copy)", "Chargers(Commercial)", "Cases", "Headphones", "PowerBanks" }));
        SupplementType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplementTypeActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(107, 119, 141));
        jLabel53.setText("Suppliers :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(504, 504, 504)
                        .addComponent(jLabel25))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sfirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SlastName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SupplementType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveSupplier))
                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(529, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sfirstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel26)
                        .addComponent(SlastName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)
                        .addComponent(Sphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(SupplementType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveSupplier)))
                .addGap(1, 1, 1)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("import", jPanel3);

        jPanel11.setBackground(new java.awt.Color(23, 34, 59));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Rolling-1s-94px.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(641, 641, 641)
                .addComponent(jLabel21)
                .addContainerGap(810, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel21)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        tab.addTab("Loading ", jPanel11);

        jPanel14.setBackground(new java.awt.Color(23, 34, 59));

        BillTable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        BillTable.setModel(new javax.swing.table.DefaultTableModel(
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
        BillTable.setRowHeight(20);
        jScrollPane7.setViewportView(BillTable);

        totalprice.setEnabled(false);
        totalprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalpriceActionPerformed(evt);
            }
        });
        totalprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalpriceKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(107, 119, 141));
        jLabel37.setText("Total Price");

        SaveBill.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        SaveBill.setText("Save");
        SaveBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBillActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(284, 284, 284)
                        .addComponent(SaveBill)
                        .addGap(38, 38, 38)
                        .addComponent(Cancel)
                        .addGap(0, 716, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SaveBill)
                        .addComponent(Cancel))
                    .addComponent(jLabel37))
                .addContainerGap())
        );

        tab.addTab("sell", jPanel14);

        jPanel15.setBackground(new java.awt.Color(23, 34, 59));

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(107, 119, 141));
        jLabel42.setText("Select Date ");

        Historytable.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        Historytable.setModel(new javax.swing.table.DefaultTableModel(
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
        Historytable.setRowHeight(20);
        jScrollPane8.setViewportView(Historytable);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jButton1)))
                .addContainerGap(536, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jButton1)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        tab.addTab("History", jPanel15);

        getContentPane().add(tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 1550, 560));

        setSize(new java.awt.Dimension(1717, 620));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        tab.setSelectedIndex(0);
        accessories.setBackground(Light_blue);
        accessories.setForeground(gray);
        Phones.setBackground(Light_blue);
        Phones.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);

        
        home.setForeground(Light_blue);
        home.setBackground(red);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_homeMouseClicked
    
    public void playSound(String soundName)
 {
   try 
   {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
    Clip clip = AudioSystem.getClip( );
    clip.open(audioInputStream);
    clip.start( );
   }
   catch(Exception ex)
   {
     System.out.println("Error with playing sound.");
     ex.printStackTrace( );
   }
 }

    private void PhonesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhonesMouseClicked
        // TODO add your handling code here:
        
        while(model.getRowCount()>0){
           model.removeRow(0);
       }
        
         try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID= 1 ";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        PhoneSupp.removeAllItems();
                        PhoneSupp.addItem("Select Supplier");
                        while(rs.next()){
                            PhoneSupp.addItem(rs.getString(1)+" "+rs.getString(2));
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT phone.manufacture , phone.P_name , phone.supnumber , product.sprice , product.pprice , product.Quantity FROM phone left OUTER JOIN product ON phone.p_id = product.product_id  ";
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          row[0]=(rs.getString(1));
                          row[1]=(rs.getString(2));
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              row[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          row[3]=rs.getString(4);
                          row[4]=rs.getString(5);
                          row[5]=rs.getString(6);
                          
                          model.addRow(row);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
      
        loading(2);

        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        accessories.setBackground(Light_blue);
        accessories.setForeground(gray);
        home.setBackground(Light_blue);
        home.setForeground(gray);
        Phones.setForeground(Light_blue);
        Phones.setBackground(red);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_PhonesMouseClicked

    private void MinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinMouseClicked
        // TODO add your handling code here:
        this.setState(ICONIFIED);
    }//GEN-LAST:event_MinMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void accessoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accessoriesMouseClicked
        // TODO add your handling code here:
        while(chmodel.getRowCount()>0){
           chmodel.removeRow(0);
       }
          try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID= 2 or  product_ID= 3 or product_ID= 4";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        sup.removeAllItems();
                        sup.addItem("Select Supplier");
                        while(rs.next()){
                            sup.addItem(rs.getString(1)+" "+rs.getString(2));
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }

          try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT charger.Quality , charger.ctype , charger.supnumber , product.sprice , product.pprice , product.Quantity FROM charger left OUTER JOIN product ON charger.c_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          Chrow[0]=(rs.getString(1));
                          Chrow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              Chrow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          this.Chrow[3]=rs.getString(4);
                          Chrow[4]=rs.getString(5);
                          Chrow[5]=rs.getString(6);
                          
                          chmodel.addRow(Chrow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
          
          
          
        Charger.setVisible(true);
        Headphones.setVisible(true);
        Cases.setVisible(true);
        powerbank.setVisible(true);
        tab.setSelectedIndex(1);
        home.setBackground(Light_blue);
        home.setForeground(gray);
        Phones.setBackground(Light_blue);
        Phones.setForeground(gray);
        accessories.setForeground(Light_blue);
        accessories.setBackground(red);
        
        loadingt(0);
        Charger.setForeground(Light_blue);
        Charger.setBackground(red);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_accessoriesMouseClicked

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        // TODO add your handling code here:
        int xLoc=evt.getXOnScreen();
        int yLoc=evt.getYOnScreen();
        this.setLocation(xLoc-xp,yLoc-yp);
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // TODO add your handling code here:
        xp=evt.getX();
        yp=evt.getY();
    }//GEN-LAST:event_jLabel4MousePressed

    private void ChargerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChargerMouseClicked
        // TODO add your handling code here:
        while(chmodel.getRowCount()>0){
           chmodel.removeRow(0);
       }
         try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID = 2 or  product_ID= 3 or product_ID= 4 ";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        sup.removeAllItems();
                        sup.addItem("Select Supplier");
                        while(rs.next()){
                            sup.addItem(rs.getString(1)+" "+rs.getString(2));
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        loadingt(0);
        try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT charger.Quality , charger.Ctype , charger.supnumber , product.sprice , product.pprice , product.Quantity FROM CHARGER left OUTER JOIN product ON charger.c_id = product.product_id ";
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          Chrow[0]=(rs.getString(1));
                          Chrow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              Chrow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          Chrow[3]=rs.getString(4);
                          Chrow[4]=rs.getString(5);
                          Chrow[5]=rs.getString(6);
                          
                          chmodel.addRow(Chrow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
        Charger.setForeground(Light_blue);
        Charger.setBackground(red);
        
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_ChargerMouseClicked

    private void CasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CasesMouseClicked
        // TODO add your handling code here:
        while(Casemodel.getRowCount()>0){
          Casemodel.removeRow(0);
       }
        try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID= 5 ";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        this.supCase.removeAllItems();
                        this.supCase.addItem("Select Supplier");
                        while(rs.next()){
                            this.supCase.addItem(rs.getString(1)+" "+rs.getString(2));
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
         try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT case.brand , case.combatibilty,case.color , case.supnumber , product.sprice , product.pprice , product.Quantity FROM case left OUTER JOIN product ON case.case_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          this.caserow[0]=(rs.getString(1));
                          caserow[1]=(rs.getString(2));
                          caserow[2]=rs.getString(3);
                          int suppliernumber = Integer.parseInt(rs.getString(4));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              caserow[3] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          
                          caserow[4]=rs.getString(5);
                          caserow[5]=rs.getString(6);
                          caserow[6]=rs.getString(7);
                          Casemodel.addRow(caserow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
        
        
        loadingt(1);
        Cases.setForeground(Light_blue);
        Cases.setBackground(red);
        
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_CasesMouseClicked
    
    
    public void loading(int index){
    
        
        tab.setSelectedIndex(4);
         //milliseconds
  ActionListener taskPerformer = new ActionListener() {
           
      @Override
      public void actionPerformed(ActionEvent evt) {
          
         tab.setSelectedIndex(index);
         
         
      }
  };
  Timer l= new Timer(1000, taskPerformer);
  l.setRepeats(false);
  l.start();
    }
    private void HeadphonesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadphonesMouseClicked
        // TODO add your handling code here:
        while(Hpmodel.getRowCount()>0){
           Hpmodel.removeRow(0);
       }
        try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID= 6 ";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        HPSupplier.removeAllItems();
                        HPSupplier.addItem("Select Supplier");
                        while(rs.next()){
                            HPSupplier.addItem(rs.getString(1)+" "+rs.getString(2));
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        
        try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT headphone.manufacture , headphone.connectivity , headphone.supnumber , product.sprice , product.pprice , product.Quantity FROM headphone left OUTER JOIN product ON headphone.h_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          Hprow[0]=(rs.getString(1));
                          Hprow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              Hprow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          Hprow[3]=rs.getString(4);
                          Hprow[4]=rs.getString(5);
                          Hprow[5]=rs.getString(6);
                          
                          Hpmodel.addRow(Hprow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
        
       loadingt(2);
        Headphones.setForeground(Light_blue);
        Headphones.setBackground(red);
        
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_HeadphonesMouseClicked

    private void add_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_phoneActionPerformed
        // TODO add your handling code here:
          playSound("save.wav");
          while(model.getRowCount()>0){
          model.removeRow(0);
       }
         
           if(PhoneM1.getSelectedIndex()==0){
               JOptionPane.showMessageDialog(null,"please Select a manufacture");
               return;
           }       
           if(phoneName.getText().equals("")){
               JOptionPane.showMessageDialog(null,"please enter the Phone Name");
               return;
           }
           if(PhoneSupp.getSelectedIndex()==0){
               JOptionPane.showMessageDialog(null,"please Select an Authorized Reseller");
               return;
           }
           if(buyingorice.getText().equals("")){
               JOptionPane.showMessageDialog(null,"please enter the serial number ");
               return;
           }
           if(sell_price.getText().equals("")){
               JOptionPane.showMessageDialog(null,"please enter the price");
               return;
           }
//        ArrayList <String> PhoneNames= new ArrayList<String>();
        String Manufacture= (String) PhoneM1.getSelectedItem();
        String phonename = phoneName.getText();
        String PhoneSupplier = (String) PhoneSupp.getSelectedItem();
        int BuyingPrice =  Integer.parseInt(buyingorice.getText());
        int SellingPrice = Integer.parseInt(this.sell_price.getText());
        int phonequantity = Integer.parseInt(this.PhoneQuantity.getText());
        int profit = (SellingPrice - BuyingPrice);
        String [] PhoneSupplierFullName = PhoneSupplier.split(" ");
        String SuppFirstName = PhoneSupplierFullName[0];
        String SuppLastName =PhoneSupplierFullName[1];
        int SuppPhone =-1 ;
        boolean phoneE=false;
        for(Supplier supplier : Suppliers){
            if(SuppFirstName.equals(supplier.SFirstName)&& SuppLastName.equals(supplier.SLastName)){
                SuppPhone = supplier.SPhoneNumber;
            }
        }
        	try {
		     OracleDataSource ods = new OracleDataSource();
		     ods.setURL(srtUrl);
		     ods.setUser(strUser);
		     ods.setPassword(strPass);
		     java.sql.Connection conn = ods.getConnection();
                     conn.setAutoCommit(false);								 
                     Statement stmt = conn.createStatement();
                     String SelectStatement = "SELECT p_name ,supnumber from phone  ";
	             ResultSet rs = stmt.executeQuery(SelectStatement);
		     while(rs.next()){
                         if(phonename.equals(rs.getString(1)) &&  SuppPhone == Integer.parseInt( rs.getString(2))){
													 //JOptionPane.showMessageDialog(null,"The phone is already saved in DB");
		        phoneE=true;
				}
												 
				}
				 conn.close();
										 
			 } catch (SQLException ex) {
				 Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                         }
        

{ 
    
              try {
                  int e = 0 ;
                   if(phoneE){
                       e =1;
                   }
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String InsertStatment =" Insert into PHONE values('"+Manufacture+"','"+phonename+"',counter.currval,"+SuppPhone +") ";
												phonequantity =phonequantity+e;
                        String insertstmt=" Insert into product values(counter.nextval,"+SellingPrice+","+BuyingPrice+","+phonequantity+","+profit+") ";
                        stmt.executeUpdate(insertstmt);
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
    
            }

try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT phone.manufacture , phone.P_name , phone.supnumber , product.sprice , product.pprice , product.Quantity FROM phone left OUTER JOIN product ON phone.p_id = product.product_id  ";
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          row[0]=(rs.getString(1));
                          row[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              row[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          row[3]=rs.getString(4);
                          row[4]=rs.getString(5);
                          row[5]=rs.getString(6);
                          
                          model.addRow(row);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }


    }//GEN-LAST:event_add_phoneActionPerformed

    private void powerbankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_powerbankMouseClicked
        // TODO add your handling code here:
        while(this.powermodel.getRowCount()>0){
          powermodel.removeRow(0);
        }
         try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier where product_ID= 7 ";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        PBS.removeAllItems();
                        PBS.addItem("Select Supplier");
                        while(rs.next()){
                            PBS.addItem(rs.getString(1)+" "+rs.getString(2));
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
         try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT powerbank.manufacture , powerbank.P_capacity , powerbank.supnumber , product.sprice , product.pprice , product.Quantity FROM powerbank left OUTER JOIN product ON powerbank.power_id = product.product_id  ";
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          powerrow[0]=(rs.getString(1));
                          powerrow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              powerrow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          powerrow[3]=rs.getString(4);
                          powerrow[4]=rs.getString(5);
                          powerrow[5]=rs.getString(6);
                          
                          powermodel.addRow(powerrow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
         
        loadingt(3);
        powerbank.setForeground(Light_blue);
        powerbank.setBackground(red);
        
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_powerbankMouseClicked

    private void supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supActionPerformed

    private void importersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importersMouseClicked
        // TODO add your handling code here:
            while(importmodel.getRowCount()>0){
           importmodel.removeRow(0);
       }
        loading(3);
        try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        while(rs.next()){
                            importrow[0]=(rs.getString(1));
                            importrow[1]=(rs.getString(2));
                            importrow[2]=(rs.getString(3));
                            int pro = Integer.parseInt(rs.getString(4));
                            switch (pro) {
                                case 1:
                                    importrow[3]="Phones";
                                    break;
                                case 2:
                                    importrow[3]="Chargers(Original)";
                                    break;
                                case 3:
                                    importrow[3]="Chargers(FirstCopy)";
                                    break;
                                case 4:
                                    importrow[3]="Chargers(Commercial)";
                                    break;
                                case 5:
                                    importrow[3]="Cases";
                                    break;
                                case 6:
                                    importrow[3]="Headphones";
                                    break;
                                case 7:
                                    importrow[3]="PowerBanks";
                                    break;
                            }
                            
                            importmodel.addRow(importrow);
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        Phones.setBackground(Light_blue);
        Phones.setForeground(gray);
        accessories.setBackground(Light_blue);
        accessories.setForeground(gray);
        home.setBackground(Light_blue);
        home.setForeground(gray);
        importers.setForeground(Light_blue);
        importers.setBackground(red);
         Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
         Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        Bill.setBackground(Light_blue);
        Bill.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
    }//GEN-LAST:event_importersMouseClicked

    private void ChSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChSaveActionPerformed
        // TODO add your handling code here:
         playSound("save.wav");
        while(chmodel.getRowCount()>0){
           chmodel.removeRow(0);
       }
       
//        Chrow[0]=chargerqualitycombobox.getSelectedItem();
//        Chrow[1]=chargertypecombobox.getSelectedItem();
//        Chrow[2]=sup.getSelectedItem();
        if((chargerqualitycombobox.getSelectedIndex()==0)){
            JOptionPane.showMessageDialog(null,"Please Select Quailty");
            return;
        }
        if((chargertypecombobox.getSelectedIndex()==0)){
            JOptionPane.showMessageDialog(null,"Please Select USB Type");
            return;
        }
        if((sup.getSelectedIndex()==0)){
            JOptionPane.showMessageDialog(null,"Please Select Supplier");
            return;
        }
        char ChargerPrice_arr [] = ChargerBuyingPrice.getText().toCharArray();
        for(char c : ChargerPrice_arr){
            if(!Character.isDigit(c)){
                JOptionPane.showMessageDialog(null,"The Price Field contain must contain digits only");
                return;
            }
        }
//        chmodel.addRow(Chrow);
        
       String Quality= (String) this.chargerqualitycombobox.getSelectedItem();
       String Type = (String) this.chargertypecombobox.getSelectedItem();
       String sup = (String) this.sup.getSelectedItem();
       String [] supname = sup.split(" ");
       String supFN = supname[0];
       String supLN = supname[1];
       int SuppPhone =-1 ;
        for(Supplier supplier : Suppliers){
            if(supFN.equals(supplier.SFirstName)&& supLN.equals(supplier.SLastName)){
                SuppPhone = supplier.SPhoneNumber;
            }
        }
       int  BChargerprice  =  Integer.parseInt(ChargerBuyingPrice.getText());
       int SChargerPrice= Integer.parseInt(ChargerSellingPrice.getText());
       int CQ= Integer.parseInt(ChargerQuantity.getText());
       int chargerProfit = (SChargerPrice - BChargerprice)*CQ;

       try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String insertstmt=" Insert into product values(counter.nextval,"+SChargerPrice+","+BChargerprice+","+CQ+","+chargerProfit+")";
                        stmt.executeUpdate(insertstmt);
                        conn.commit();
                        String InsertStatment =" Insert into charger values('"+Type+"','"+Quality+"',counter.currval,"+SuppPhone+")";
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
       try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT charger.Quality , charger.ctype , charger.supnumber , product.sprice , product.pprice , product.Quantity FROM charger left OUTER JOIN product ON charger.c_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          Chrow[0]=(rs.getString(1));
                          Chrow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              Chrow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          Chrow[3]=rs.getString(4);
                          Chrow[4]=rs.getString(5);
                          Chrow[5]=rs.getString(6);
                          
                          chmodel.addRow(Chrow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
    }//GEN-LAST:event_ChSaveActionPerformed

    private void CaseSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaseSaveActionPerformed
        // TODO add your handling code here:
        while(Casemodel.getRowCount()>0){
           Casemodel.removeRow(0);
       }
        playSound("save.wav");
//        caserow[0]=Material.getSelectedItem();
//        caserow[1]=Type.getText();
//        caserow[2]=CaseBuyngPrice.getText();
        if(Material.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Please Select Material Type");
            return;
        }
        if(Type.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please enter your Phone Model ");
            return;
        }
        if(supCase.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Please Select Supplier");
            return;
        }
        if(colorcombobox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Please Select Color");
            return;
        }
        if(CaseBuyngPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Buying Price");
            return;
        }
        if(CaseSellingPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Selling Price");
            return;
        }
        if(CaseQuantity.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Quantity");
            return;
        }
        char CasePrice_arr [] = CaseBuyngPrice.getText().toCharArray();
        for(char c : CasePrice_arr){
            if(!Character.isDigit(c)){
                JOptionPane.showMessageDialog(null,"The Price Field contain must contain digits only");
                return;
            }
        }
//        Casemodel.addRow(caserow);
        
       String sup = (String) this.supCase.getSelectedItem();
       String material = (String) this.Material.getSelectedItem();
       String phonemodel = this.Type.getText();
       String color = (String) this.colorcombobox.getSelectedItem();
       int BuyingPrice =  Integer.parseInt(this.CaseBuyngPrice.getText());
       int SellingPrice = Integer.parseInt(this.CaseSellingPrice.getText());
       int quantity = Integer.parseInt(this.CaseQuantity.getText());
       int profit = (SellingPrice - BuyingPrice)*quantity;
       String [] supname = sup.split(" ");
       String supFN = supname[0];
       String supLN = supname[1];
       int SuppPhone =-1 ;
       for(Supplier supplier : Suppliers){
            if(supFN.equals(supplier.SFirstName)&& supLN.equals(supplier.SLastName)){
                SuppPhone = supplier.SPhoneNumber;
            }
       }
        try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String InsertStatment =" Insert into case values('"+material+"','"+phonemodel+"',counter.currval,"+SuppPhone+",'"+color+"') ";
                        String insertstmt=" Insert into product values(counter.nextval,"+SellingPrice+","+BuyingPrice+","+quantity+","+profit+") ";
                        stmt.executeUpdate(insertstmt);
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
       
        try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT case.brand , case.combatibilty,case.color , case.supnumber , product.sprice , product.pprice , product.Quantity FROM case left OUTER JOIN product ON case.case_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          this.caserow[0]=(rs.getString(1));
                          caserow[1]=(rs.getString(2));
                          caserow[2]=rs.getString(3);
                          int suppliernumber = Integer.parseInt(rs.getString(4));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              caserow[3] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          
                          caserow[4]=rs.getString(5);
                          caserow[5]=rs.getString(6);
                          caserow[6]=rs.getString(7);
                          Casemodel.addRow(caserow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
    }//GEN-LAST:event_CaseSaveActionPerformed

    private void chargertypecomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chargertypecomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chargertypecomboboxActionPerformed

    private void SlastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SlastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SlastNameActionPerformed

    private void saveSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSupplierActionPerformed
        // TODO add your handling code here:
        playSound("save.wav");
       while(importmodel.getRowCount()>0){
           importmodel.removeRow(0);
       }
        
        Boolean isEmpty = false;
        Boolean isPhoneNumber = false;
        Boolean PhoneNumberExist= false;
        ArrayList<String> PhoneNumbers = new ArrayList<String>();
        String FirstName= Sfirstname.getText();
        String lastName = SlastName.getText();
        String PhoneNo= Sphonenumber.getText();
        int Type =  SupplementType.getSelectedIndex(); 
        
        
        
      if(Sfirstname.equals("")||SlastName.equals("")||Sphonenumber.equals("")||SupplementType.getSelectedIndex()==0){
        JOptionPane.showMessageDialog(null,"Please Make sure you Filled all the requirements");
        isEmpty=true;
        
      }
      
      for(char c : PhoneNo.toCharArray()){
            if(!Character.isDigit(c)){
                isPhoneNumber = false;
                break;
            }
            else{
                isPhoneNumber = true ;
            }
        }
        if(!isPhoneNumber){
            JOptionPane.showMessageDialog(null, "the phone number field must contain Digits only");
        }
      if(!isEmpty){
        try {
                OracleDataSource ods = new OracleDataSource();
                ods.setURL(srtUrl);
                ods.setUser(strUser);
                ods.setPassword(strPass);
                java.sql.Connection conn = ods.getConnection();
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                String SelectStatement = "select * from Supplier";
                ResultSet rs = stmt.executeQuery(SelectStatement);
                while(rs.next()){
                    PhoneNumbers.add(rs.getString(3));
                }
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(String use : PhoneNumbers){
                if(use.equals(PhoneNo)){
                    PhoneNumberExist = true;
                }
            }
            
            if(!PhoneNumberExist && isPhoneNumber){
              try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String InsertStatment =" Insert into supplier values('"+FirstName+"'  ,'"+lastName+"',"+PhoneNo+","+Type+",NULL) " ;
                        this.Suppliers.add(new Supplier(FirstName,lastName,Integer.parseInt(PhoneNo),Type));
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else{
              JOptionPane.showMessageDialog(null,"phone number exist");
            }

      try {
                        OracleDataSource ods = new OracleDataSource();
                        ods.setURL(srtUrl);
                        ods.setUser(strUser);
                        ods.setPassword(strPass);
                        java.sql.Connection conn = ods.getConnection();
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement = "select * from supplier";
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        while(rs.next()){
                            importrow[0]=(rs.getString(1));
                            importrow[1]=(rs.getString(2));
                            importrow[2]=(rs.getString(3)); 
                            int pro = Integer.parseInt(rs.getString(4));
                            switch (pro) {
                                case 1:
                                    importrow[3]="Phones";
                                    break;
                                case 2:
                                    importrow[3]="Chargers(Original)";
                                    break;
                                case 3:
                                    importrow[3]="Chargers(FirstCopy)";
                                    break;
                                case 4:
                                    importrow[3]="Chargers(Commercial)";
                                    break;
                                case 5:
                                    importrow[3]="Cases";
                                    break;
                                case 6:
                                    importrow[3]="Headphones";
                                    break;
                                case 7:
                                    importrow[3]="PowerBanks";
                                    break;
                            }
                            importmodel.addRow(importrow);
                            
                        }
                        conn.close();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }

      
 
    }//GEN-LAST:event_saveSupplierActionPerformed
    }
    private void PBBuyingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PBBuyingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PBBuyingPriceActionPerformed

    private void PowerBankSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PowerBankSaveActionPerformed
        // TODO add your handling code here:
        while(this.powermodel.getRowCount()>0){
          powermodel.removeRow(0);
       }
        playSound("save.wav");
//        powerrow[0]=PBManufacture.getSelectedItem();
//        powerrow[1]=Cap.getText()+"mAh";
//        powerrow[2]=PBBuyingPrice.getText();
        if(PBManufacture.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Please Select a Manufacture");
            return;
        }
        if(PBS.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Please Select Supplier");
            return;
        }
        if(Cap.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter Capacity");
            return;
        }
        if(PBBuyingPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Buying Price");
            return;
        }
        if(PBSellingPrice.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Selling Price");
            return;
        }
        if(PBQuantity.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter the Selling Price");
            return;
        }
        char PPrice_arr [] = PBBuyingPrice.getText().toCharArray();
        for(char c : PPrice_arr){
            if(!Character.isDigit(c)){
                JOptionPane.showMessageDialog(null,"The Price Field contain must contain digits only");
                return;
            }
        }
//        powermodel.addRow(powerrow);
       String manufacture = (String) this.PBManufacture.getSelectedItem();
       String capacitance = this.Cap.getText();
       String sup = (String) this.PBS.getSelectedItem();
       int BuyingPrice =  Integer.parseInt(this.PBBuyingPrice.getText());
       int SellingPrice = Integer.parseInt(this.PBSellingPrice.getText());
       int quantity = Integer.parseInt(this.PBQuantity.getText());
       int profit = (SellingPrice - BuyingPrice)*quantity;
       String [] supname = sup.split(" ");
       String supFN = supname[0];
       String supLN = supname[1];
       int SuppPhone =-1 ;
       for(Supplier supplier : Suppliers){
            if(supFN.equals(supplier.SFirstName)&& supLN.equals(supplier.SLastName)){
                SuppPhone = supplier.SPhoneNumber;
            }
       }
        try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String InsertStatment =" Insert into powerbank values('"+manufacture+"',"+capacitance+",counter.currval,"+SuppPhone+") ";
                        String insertstmt=" Insert into product values(counter.nextval,"+SellingPrice+","+BuyingPrice+","+quantity+","+profit+") ";
                        stmt.executeUpdate(insertstmt);
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
         try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT powerbank.manufacture , powerbank.P_capacity , powerbank.supnumber , product.sprice , product.pprice , product.Quantity FROM powerbank left OUTER JOIN product ON powerbank.power_id = product.product_id  ";
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          powerrow[0]=(rs.getString(1));
                          powerrow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              powerrow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          powerrow[3]=rs.getString(4);
                          powerrow[4]=rs.getString(5);
                          powerrow[5]=rs.getString(6);
                          
                          powermodel.addRow(powerrow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
    }//GEN-LAST:event_PowerBankSaveActionPerformed

    private void HPSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPSaveActionPerformed
        // TODO add your handling code here:
        playSound("save.wav");
//        Hprow[0]=HPmanufacture.getSelectedItem();
//        Hprow[1]=HPConnect.getSelectedItem();
//        Hprow[2]=HPBuyingprice.getText();
        while(Hpmodel.getRowCount()>0){
           Hpmodel.removeRow(0);
       }

        if(HPmanufacture.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Please Select a Manufacture");
            return;
        }
        if(HPConnect.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Please Select Connectivity Type");
            return;
        }
        if(HPSupplier.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null,"Please Select Supplier");
            return;
        }
        if(HPBuyingprice.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Please Enter Buying Price");
            return;
        }
        if(HPSellingPrice.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Please Enter Selling Price");
            return;
        }
        if(HPQuantity.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Please Enter the Quantity");
            return;
        }
        
        char HPprice_arr [] = HPBuyingprice.getText().toCharArray();
        for(char c : HPprice_arr){
            if(!Character.isDigit(c)){
                JOptionPane.showMessageDialog(null,"The Price Field contain must contain digits only");
                return;
            }
        }
//        Hpmodel.addRow(Hprow);
        
       String sup = (String) this.HPSupplier.getSelectedItem();
       String Manufacture = (String) this.HPmanufacture.getSelectedItem();
       String conncet = (String) this.HPConnect.getSelectedItem();
       int BuyingPrice =  Integer.parseInt(this.HPBuyingprice.getText());
       int SellingPrice = Integer.parseInt(this.HPSellingPrice.getText());
       int quantity = Integer.parseInt(this.HPQuantity.getText());
       int profit = (SellingPrice - BuyingPrice)*quantity;
       String [] supname = sup.split(" ");
       String supFN = supname[0];
       String supLN = supname[1];
       int SuppPhone =-1 ;
       for(Supplier supplier : Suppliers){
            if(supFN.equals(supplier.SFirstName)&& supLN.equals(supplier.SLastName)){
                SuppPhone = supplier.SPhoneNumber;
            }
       }
        try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String InsertStatment =" Insert into headphone values('"+Manufacture+"','"+conncet+"',counter.currval,"+SuppPhone+") ";
                        String insertstmt=" Insert into product values(counter.nextval,"+SellingPrice+","+BuyingPrice+","+quantity+","+profit+") ";
                        stmt.executeUpdate(insertstmt);
                        stmt.executeUpdate(InsertStatment);
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    } 
        try {
                      OracleDataSource ods = new OracleDataSource();
                      ods.setURL(srtUrl);
                      ods.setUser(strUser);
                      ods.setPassword(strPass);
                      java.sql.Connection conn = ods.getConnection();
                      conn.setAutoCommit(false);
                      Statement stmt = conn.createStatement();
                      String SelectStatement = "SELECT headphone.manufacture , headphone.connectivity , headphone.supnumber , product.sprice , product.pprice , product.Quantity FROM headphone left OUTER JOIN product ON headphone.h_id = product.product_id"  ;
                      ResultSet rs = stmt.executeQuery(SelectStatement);
                      while(rs.next()){
                          Hprow[0]=(rs.getString(1));
                          Hprow[1]=(rs.getString(2));
                        
                          int suppliernumber = Integer.parseInt(rs.getString(3));
                          for(Supplier supplier : Suppliers){
                            if(suppliernumber == supplier.SPhoneNumber){
                              Hprow[2] = supplier.SFirstName + " "+ supplier.SLastName;
                            }
                          }
                          Hprow[3]=rs.getString(4);
                          Hprow[4]=rs.getString(5);
                          Hprow[5]=rs.getString(6);
                          
                          Hpmodel.addRow(Hprow);
                          
                      }
                      conn.close();
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                  }
    }//GEN-LAST:event_HPSaveActionPerformed

    private void TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TypeActionPerformed

    private void SfirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SfirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SfirstnameActionPerformed

    private void colorcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorcomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorcomboboxActionPerformed

    private void ChargerBuyingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChargerBuyingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChargerBuyingPriceActionPerformed

    private void SupplementTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplementTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplementTypeActionPerformed

    private void PhoneSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneSuppActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneSuppActionPerformed

    private void sell_priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sell_priceKeyTyped
        // TODO add your handling code here:
        if (!(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'))
                   evt.consume();
    }//GEN-LAST:event_sell_priceKeyTyped

    private void SphonenumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SphonenumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SphonenumberActionPerformed

    private void BillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillMouseClicked
        // TODO add your handling code here:
        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        tab.setSelectedIndex(5);
        accessories.setBackground(Light_blue);
        accessories.setForeground(gray);
        Phones.setBackground(Light_blue);
        Phones.setForeground(gray);
        home.setBackground(Light_blue);
        home.setForeground(gray);

        
        Bill.setForeground(Light_blue);
        Bill.setBackground(red);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
        History.setBackground(Light_blue);
        History.setForeground(gray);
        
        
    }//GEN-LAST:event_BillMouseClicked

    private void totalpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalpriceActionPerformed

    private void PhoneQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneQuantityActionPerformed

    private void HistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryMouseClicked
        // TODO add your handling code here:
        Charger.setVisible(false);
        Headphones.setVisible(false);
        Cases.setVisible(false);
        powerbank.setVisible(false);
        tab.setSelectedIndex(6);
        accessories.setBackground(Light_blue);
        accessories.setForeground(gray);
        Phones.setBackground(Light_blue);
        Phones.setForeground(gray);
        home.setBackground(Light_blue);
        home.setForeground(gray);

        
        History.setForeground(Light_blue);
        History.setBackground(red);
        Bill.setForeground(gray);
        Bill.setBackground(Light_blue);
        Charger.setForeground(gray);
        Charger.setBackground(Light_blue);
        Headphones.setForeground(gray);
        Headphones.setBackground(Light_blue);
        powerbank.setForeground(gray);
        powerbank.setBackground(Light_blue);
        Cases.setForeground(gray);
        Cases.setBackground(Light_blue);
        importers.setBackground(Light_blue);
        importers.setForeground(gray);
    }//GEN-LAST:event_HistoryMouseClicked

    private void SaveBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBillActionPerformed
            // TODO add your handling code here:
                    playSound("save.wav");
                    ArrayList name = new ArrayList();
                    ArrayList id= new ArrayList();
                    ArrayList item_ty= new ArrayList();
                    ArrayList list = new ArrayList();
                    ArrayList price= new ArrayList();
                    ArrayList profit =new ArrayList(); 
                     for(int i = 0;i<BillTable.getModel().getRowCount();i++)
                  {
                   item_ty.add(BillTable.getModel().getValueAt(i,1)); //get the all row values at item_type
                  }
                    for(int i = 0;i<BillTable.getModel().getRowCount();i++)
                  {
                   name.add(BillTable.getModel().getValueAt(i,2)); //get the all row values at column name
                  }
                    for(int i = 0;i<BillTable.getModel().getRowCount();i++)
                  {
                   price.add(BillTable.getModel().getValueAt(i,4)); //get the all row values at column price
                  }
                    for(int i = 0;i<BillTable.getModel().getRowCount();i++)
                  {
                   id.add(BillTable.getModel().getValueAt(i,0)); //get the all row values at column product_ID
                  }
                 for(int i = 0;i<BillTable.getModel().getRowCount();i++)
                  {
                   list.add(BillTable.getModel().getValueAt(i,3)); //get the all row values at QTY.
                  }
                 int sum=0;
                 for(Object l : price){
                     int pp= Integer.parseInt((String) l);
                     sum+=pp;
                 }
                  totalprice.setText(sum+"");
                  DateFormat m = new SimpleDateFormat("YYYY-MM-dd");
                  Date date=new Date();
                  String ti= m.format(date);
                     try {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        for(int i=0;i<list.size();i++){
                            int pp= Integer.parseInt((String) price.get(i));
                            String dname = (String) name.get(i);
                            int pid1=Integer.parseInt((String) id.get(i) );
                            String ty= (String) item_ty.get(i);
                            int qty = Integer.parseInt((String) list.get(i));
                            
                        String SelectState = "Select profit from product where product_id = "+Integer.parseInt((String) id.get(i) )+" ";
                        ResultSet rs = stmt.executeQuery(SelectState);
                        while(rs.next()){
                            profit.add(Integer.parseInt(rs.getString(1)));
                        }
                        int pro =  (int) profit.get(i);
                        String InsertStatment =" Insert into bill values('"+dname+"',"+pid1+",'"+ti+"','"+ty+"',"+qty+","+pp+","+pro+")";                       
                        stmt.executeUpdate(InsertStatment);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    try {
        OracleDataSource ods = new OracleDataSource();
        ods.setURL(srtUrl);
        ods.setUser(strUser);
        ods.setPassword(strPass);
        java.sql.Connection conn = ods.getConnection();
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        for(int i=0;i<list.size();i++){
                        int qty = Integer.parseInt((String) list.get(i));
                        int pid= Integer.parseInt((String) id.get(i));
                    
        String SelectStatement = "UPDATE product SET quantity =quantity -"+qty+" WHERE product_id = '"+ pid +"' ";
        ResultSet rs = stmt.executeQuery(SelectStatement);
        
        }
        conn.close();
    } catch (SQLException ex) {
        Logger.getLogger(Forgetyourpassword.class.getName()).log(Level.SEVERE, null, ex);
    }
//            int item_Type= BCombo.getSelectedIndex();
//            String name = item.getText();
//            int Bbarcode= Integer.parseInt(barcode.getText());
//            int Tp= Integer.parseInt(totalprice.getText());

//            
    }//GEN-LAST:event_SaveBillActionPerformed

    private void buyingoriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buyingoriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buyingoriceKeyPressed

    private void buyingoriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buyingoriceKeyTyped
        // TODO add your handling code here:
         if (!(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'))
                   evt.consume();
    }//GEN-LAST:event_buyingoriceKeyTyped

    private void PhoneQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneQuantityKeyTyped
        // TODO add your handling code here:
         if (!(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'))
                   evt.consume();
    }//GEN-LAST:event_PhoneQuantityKeyTyped

    private void totalpriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalpriceKeyTyped
        // TODO add your handling code here:
         if (!(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'))
                   evt.consume();
    }//GEN-LAST:event_totalpriceKeyTyped

    private void sendtobillfromphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtobillfromphoneActionPerformed
        if(Table.getSelectionModel().isSelectionEmpty()){
          JOptionPane.showMessageDialog(null, "Select Row to insert it to Bill");
        }
        else{
            DefaultTableModel model = (DefaultTableModel) Table.getModel();
            int selectedrowindex = Table.getSelectedRow();
            int SuppPhone =-1;
            String Manufacture = model.getValueAt(selectedrowindex, 0).toString();
            String PhoneName = model.getValueAt(selectedrowindex, 1).toString();
            String Supplier = model.getValueAt(selectedrowindex, 2).toString();
            String Supp [] = Supplier.split(" ");
            String SuppFN = Supp[0];
            String SuppLN = Supp[1];
            for(Supplier sup : Suppliers){
              if(sup.SFirstName.equals(SuppFN)&& sup.SLastName.equals(SuppLN))
              SuppPhone = sup.SPhoneNumber;
            }
//            String BuyingPrice = model.getValueAt(selectedrowindex, 4).toString();
//            String Quantity = model.getValueAt(selectedrowindex, 5).toString();
//            String Description = Manufacture +" "+PhoneName ;
             try {
                 
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement ="SELECT  phone.Manufacture , phone.p_Name , product.product_ID , product.sprice , product.quantity FROM phone left OUTER JOIN product ON phone.p_id = product.product_id  where phone.manufacture = '"+Manufacture+"' and phone.P_name = '"+PhoneName+"' and phone.supnumber = "+SuppPhone+" ";     
                        ResultSet rs = stmt.executeQuery(SelectStatement);
             
                        while(rs.next()){
                        this.billrow[0]=rs.getString(3);
                        this.billrow[1]="Phone";
                        this.billrow[2]=rs.getString(1)+" "+rs.getString(2);
                        this.billrow[3]="1";
                        this.billrow[4]=rs.getString(4);
                        bBill.addRow(billrow);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }//GEN-LAST:event_sendtobillfromphoneActionPerformed

    private void sendtobillfromchargerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtobillfromchargerActionPerformed
        // TODO add your handling code here:
        if(ChargerTable.getSelectionModel().isSelectionEmpty()){
          JOptionPane.showMessageDialog(null, "Select Row to insert it to Bill");
        }
        else{
            DefaultTableModel model = (DefaultTableModel) ChargerTable.getModel();
            int selectedrowindex = ChargerTable.getSelectedRow();
            int SuppPhone =-1;
            String Quality = model.getValueAt(selectedrowindex, 0).toString();
            String ctype = model.getValueAt(selectedrowindex, 1).toString();
            String Supplier = model.getValueAt(selectedrowindex, 2).toString();
            String BuyingPrice = model.getValueAt(selectedrowindex, 4).toString();
            String Quantity = model.getValueAt(selectedrowindex, 5).toString();
            String Description = Quality +" "+Type ;
            String Supp [] = Supplier.split(" ");
            String SuppFN = Supp[0];
            String SuppLN = Supp[1];
            for(Supplier sup : Suppliers){
              if(sup.SFirstName.equals(SuppFN)&& sup.SLastName.equals(SuppLN))
              SuppPhone = sup.SPhoneNumber;
            }
            try {
                 
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement ="SELECT  charger.quality , charger.ctype , product.product_ID , product.sprice , product.quantity FROM charger left OUTER JOIN product ON charger.c_id = product.product_id  where charger.quality = '"+Quality+"' and charger.ctype = '"+ctype+"' and charger.supnumber = "+SuppPhone+" ";     
                        ResultSet rs = stmt.executeQuery(SelectStatement);
             
                        while(rs.next()){
                        this.billrow[0]=rs.getString(3);
                        this.billrow[1]="Charger";
                        this.billrow[2]=rs.getString(1)+" "+rs.getString(2);
                        this.billrow[3]="1";
                        this.billrow[4]=rs.getString(4);
                        bBill.addRow(billrow);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }//GEN-LAST:event_sendtobillfromchargerActionPerformed

    private void sendtobillfromcaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtobillfromcaseActionPerformed
        // TODO add your handling code here
        if(this.CasesTable.getSelectionModel().isSelectionEmpty()){
          JOptionPane.showMessageDialog(null, "Select Row to insert it to Bill");
        }
        else{
            DefaultTableModel model = (DefaultTableModel) CasesTable.getModel();
            int selectedrowindex = CasesTable.getSelectedRow();
            int SuppPhone =-1;
            String Material = model.getValueAt(selectedrowindex, 0).toString();
            String PhoneType = model.getValueAt(selectedrowindex, 1).toString();
            String Color = model.getValueAt(selectedrowindex, 2).toString();
            String Supplier = model.getValueAt(selectedrowindex, 3).toString();
            String BuyingPrice = model.getValueAt(selectedrowindex, 5).toString();
            String Quantity = model.getValueAt(selectedrowindex, 6).toString();
            String Description = Material +" "+PhoneType+" "+Color ;
            String Supp [] = Supplier.split(" ");
            String SuppFN = Supp[0];
            String SuppLN = Supp[1];
            for(Supplier sup : Suppliers){
              if(sup.SFirstName.equals(SuppFN)&& sup.SLastName.equals(SuppLN))
              SuppPhone = sup.SPhoneNumber;
            }
            try {
                 
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement ="SELECT product.product_id , case.brand , case.COMBATIBILTY , case.color , product.sprice , product.quantity FROM case left OUTER JOIN product ON case.case_id = product.product_id  where case.brand = '"+Material+"' and case.COMBATIBILTY = '"+PhoneType+"' and case.color ='"+Color+"' and case.supnumber = "+SuppPhone+" ";     
                        ResultSet rs = stmt.executeQuery(SelectStatement);
             
                        while(rs.next()){
                        this.billrow[0]=rs.getString(1);
                        this.billrow[1]="case";
                        this.billrow[2]=rs.getString(3)+" "+rs.getString(2)+" "+rs.getString(4);
                        this.billrow[3]="1";
                        this.billrow[4]=rs.getString(5);
                        bBill.addRow(billrow);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }//GEN-LAST:event_sendtobillfromcaseActionPerformed

    private void sendtobillfromHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtobillfromHPActionPerformed
        // TODO add your handling code here:
        if(this.HPTable.getSelectionModel().isSelectionEmpty()){
          JOptionPane.showMessageDialog(null, "Select Row to insert it to Bill");
        }
        else{
            DefaultTableModel model = (DefaultTableModel) HPTable.getModel();
            int selectedrowindex = HPTable.getSelectedRow();
            int SuppPhone =-1;
            String manufacture = model.getValueAt(selectedrowindex, 0).toString();
            String connect = model.getValueAt(selectedrowindex, 1).toString();
            String Supplier = model.getValueAt(selectedrowindex, 2).toString();
            String BuyingPrice = model.getValueAt(selectedrowindex, 4).toString();
            String Quantity = model.getValueAt(selectedrowindex, 5).toString();
            //String Description = Quality +" "+Type ;
            String Supp [] = Supplier.split(" ");
            String SuppFN = Supp[0];
            String SuppLN = Supp[1];
            for(Supplier sup : Suppliers){
              if(sup.SFirstName.equals(SuppFN)&& sup.SLastName.equals(SuppLN))
              SuppPhone = sup.SPhoneNumber;
            }
            try {
                 
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement ="SELECT  product.product_ID , headphone.manufacture , headphone.CONNECTIVITY , product.sprice , product.quantity FROM headphone left OUTER JOIN product ON headphone.H_id = product.product_id  where headphone.manufacture = '"+manufacture+"' and headphone.CONNECTIVITY = '"+connect+"' and headphone.supnumber = "+SuppPhone+" ";     
                        ResultSet rs = stmt.executeQuery(SelectStatement);
             
                        while(rs.next()){
                        this.billrow[0]=rs.getString(1);
                        this.billrow[1]="Headphone";
                        this.billrow[2]=rs.getString(2)+" "+rs.getString(3);
                        this.billrow[3]="1";
                        this.billrow[4]=rs.getString(4);
                        bBill.addRow(billrow);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }//GEN-LAST:event_sendtobillfromHPActionPerformed

    private void sendtobillfromPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendtobillfromPBActionPerformed
        // TODO add your handling code here:
        if(this.PowerTable.getSelectionModel().isSelectionEmpty()){
          JOptionPane.showMessageDialog(null, "Select Row to insert it to Bill");
        }
        else{
            DefaultTableModel model = (DefaultTableModel) PowerTable.getModel();
            int selectedrowindex = PowerTable.getSelectedRow();
            int SuppPhone =-1;
            String manufacture = model.getValueAt(selectedrowindex, 0).toString();
            String capacity = model.getValueAt(selectedrowindex, 1).toString();
            String Supplier = model.getValueAt(selectedrowindex, 2).toString();
            String BuyingPrice = model.getValueAt(selectedrowindex, 4).toString();
            String Quantity = model.getValueAt(selectedrowindex, 5).toString();
            //String Description = Quality +" "+Type ;
            String Supp [] = Supplier.split(" ");
            String SuppFN = Supp[0];
            String SuppLN = Supp[1];
            for(Supplier sup : Suppliers){
              if(sup.SFirstName.equals(SuppFN)&& sup.SLastName.equals(SuppLN))
              SuppPhone = sup.SPhoneNumber;
            }
            try {
                 
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                        java.sql.Connection conn = DriverManager.getConnection(srtUrl,strUser,strPass);
                        conn.setAutoCommit(false);
                        Statement stmt = conn.createStatement();
                        String SelectStatement ="SELECT powerbank.MANUFACTURE, powerbank.P_capacity , product.product_ID,product.sprice , product.quantity FROM powerbank left OUTER JOIN product ON powerbank.power_id = product.product_id  where powerbank.manufacture = '"+manufacture+"'  and powerbank.supnumber = "+SuppPhone+" ";     
                        ResultSet rs = stmt.executeQuery(SelectStatement);
                        while(rs.next()){
                        this.billrow[0]=rs.getString(1);
                        this.billrow[1]="PowerBank";
                        this.billrow[2]=rs.getString(2)+" "+rs.getString(3);
                        this.billrow[3]="1";
                        this.billrow[4]=rs.getString(4);
                       
                        bBill.addRow(billrow);
                        }
                        conn.commit();
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewAccount.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }//GEN-LAST:event_sendtobillfromPBActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        while(this.bBill.getRowCount()>0){
          bBill.removeRow(0);
       }
    }//GEN-LAST:event_CancelActionPerformed

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked
        // TODO add your handling code here:
        Date date = jDateChooser1.getDate();
        System.out.printf("fc");
        System.out.printf(date.toString());
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       try{
           Connection con;
InputStream input;
JasperDesign jasperDesign;
JasperReport jasperReport;
JasperPrint jasperPrint;
OutputStream output;
OracleDataSource ods;
ods=new OracleDataSource();
ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
ods.setUser("scott");
ods.setPassword("tiger");
con=ods.getConnection();
input=new FileInputStream(new File("Jasper.jrxml"));
jasperDesign=JRXmlLoader.load(input);
jasperReport=JasperCompileManager.compileReport(jasperDesign);
jasperPrint=JasperFillManager.fillReport(jasperReport, null, con);
output= new FileOutputStream(new File("RevnueReport.pdf"));
JasperExportManager.exportReportToPdfStream(jasperPrint,output);
//you can also use the viewer, so replace the last two lines with
//the following code
JFrame frame = new JFrame("Report");
frame.getContentPane().add(new JRViewer(jasperPrint));
frame.pack();
frame.setVisible(true);
       
       } catch (FileNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

//
//    Table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//        public void valueChanged(ListSelectionEvent event) {
//            // do some actions here, for example
//            // print first column value from selected row
//            System.out.println(Table.getValueAt(Table.getSelectedRow(), 0).toString());
//        }
//    } );
//    


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Manager().setVisible(true);
        }); 

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Acc;
    private javax.swing.JLabel Bill;
    private javax.swing.JTable BillTable;
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField Cap;
    private javax.swing.JTextField CaseBuyngPrice;
    private javax.swing.JTextField CaseQuantity;
    private javax.swing.JButton CaseSave;
    private javax.swing.JTextField CaseSellingPrice;
    private javax.swing.JLabel Cases;
    private javax.swing.JTable CasesTable;
    private javax.swing.JButton ChSave;
    private javax.swing.JLabel Charger;
    private javax.swing.JTextField ChargerBuyingPrice;
    private javax.swing.JTextField ChargerQuantity;
    private javax.swing.JTextField ChargerSellingPrice;
    private javax.swing.JTable ChargerTable;
    public javax.swing.JLabel Date;
    private javax.swing.JLabel GBP;
    private javax.swing.JTextField HPBuyingprice;
    private javax.swing.JComboBox<String> HPConnect;
    private javax.swing.JTextField HPQuantity;
    private javax.swing.JButton HPSave;
    private javax.swing.JTextField HPSellingPrice;
    private javax.swing.JComboBox<String> HPSupplier;
    private javax.swing.JTable HPTable;
    private javax.swing.JComboBox<String> HPmanufacture;
    private javax.swing.JLabel Headphones;
    private javax.swing.JLabel History;
    private javax.swing.JTable Historytable;
    private javax.swing.JLabel JOD;
    private javax.swing.JComboBox<String> Material;
    private javax.swing.JLabel Min;
    private javax.swing.JTextField PBBuyingPrice;
    private javax.swing.JComboBox<String> PBManufacture;
    private javax.swing.JTextField PBQuantity;
    private javax.swing.JComboBox<String> PBS;
    private javax.swing.JTextField PBSellingPrice;
    private javax.swing.JComboBox<String> PhoneM1;
    private javax.swing.JTextField PhoneQuantity;
    private javax.swing.JComboBox<String> PhoneSupp;
    private javax.swing.JLabel Phones;
    private javax.swing.JButton PowerBankSave;
    private javax.swing.JTable PowerTable;
    private javax.swing.JButton SaveBill;
    private javax.swing.JTextField Sfirstname;
    private javax.swing.JTextField SlastName;
    private javax.swing.JTextField Sphonenumber;
    private javax.swing.JComboBox<String> SupplementType;
    private javax.swing.JTable Table;
    private javax.swing.JTextField Type;
    private javax.swing.JLabel USD;
    private javax.swing.JLabel Update;
    private javax.swing.JLabel Welcome;
    private javax.swing.JLabel accessories;
    private javax.swing.JButton add_phone;
    private javax.swing.JTextField buyingorice;
    private javax.swing.JComboBox<String> chargerqualitycombobox;
    private javax.swing.JComboBox<String> chargertypecombobox;
    private javax.swing.JLabel close;
    private javax.swing.JComboBox<String> colorcombobox;
    private javax.swing.JLabel home;
    private javax.swing.JTable importTable;
    private javax.swing.JLabel importers;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField phoneName;
    private javax.swing.JLabel powerbank;
    private javax.swing.JButton saveSupplier;
    private javax.swing.JTextField sell_price;
    private javax.swing.JButton sendtobillfromHP;
    private javax.swing.JButton sendtobillfromPB;
    private javax.swing.JButton sendtobillfromcase;
    private javax.swing.JButton sendtobillfromcharger;
    private javax.swing.JButton sendtobillfromphone;
    private javax.swing.JComboBox<String> sup;
    private javax.swing.JComboBox<String> supCase;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTextField totalprice;
    // End of variables declaration//GEN-END:variables

    private void loadingt(int index) {
    Acc.setSelectedIndex(4);
         //milliseconds
  ActionListener taskPerformer = new ActionListener() {
           
      @Override
      public void actionPerformed(ActionEvent evt) {
          
         Acc.setSelectedIndex(index);
         
         
      }
  };
  Timer l= new Timer(1000, taskPerformer);
  l.setRepeats(false);
  l.start();    }
}