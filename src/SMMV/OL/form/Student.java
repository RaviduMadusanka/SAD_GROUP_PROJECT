/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SMMV.OL.form;

import SMMV.Connection.connection_ol;
import SMMV.Main.Dashboard_ol;
import SMMV.OL.gui.OL_Subject;
import SMMV.OL.model.OtherSubject;
import SMMV.Validation.Validation;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author user
 */
public class Student extends javax.swing.JPanel {
    
    
    HashMap<String, String> grade_map = new HashMap<>();
    HashMap<String, String> class_map = new HashMap<>();
//    HashMap<String, String> other_subject_map = new HashMap<>();
//    HashMap<String, OtherSubject> otherSubjectHashMap = new HashMap<>();
    String index_number = "";
    
    public JTable getOlTable() {
        return olTable;
    }
    
    public void setOlTable(JTable olTable) {
        this.olTable = olTable;
    }
    
    private Dashboard_ol dashboard;

    /**
     * Creates new form Student
     */
    public Student() {
        initComponents();
        setOpaque(false);
        JTableHeader tableHeader = student_table.getTableHeader();
        tableHeader.setBackground(new Color(0, 0, 255));
        tableHeader.setForeground(Color.WHITE);
        search_field_student.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Student First Name or Last Name");
        loadGrade();
        loadClass();
        loadStudentTable();
    }
    
    private void clear() {
        enrollment_no_field.setText("");
        fname_field.setText("");
        lname_field.setText("");
        classCombo.setSelectedIndex(0);
        male.setSelected(false);
        female.setSelected(false);
        email_field1.setText("");
        ol_subject_button.setEnabled(true);
        String grade = String.valueOf(gradeCombo.getSelectedItem());
        if (grade.equals("10") | grade.equals("11")) {
            sinhala.setEnabled(true);
            english.setEnabled(true);
            maths.setEnabled(true);
            science.setEnabled(true);
            buddhist.setEnabled(true);
            civic.setEnabled(true);
            ict.setEnabled(true);
            health.setEnabled(true);
            history.setEnabled(true);
            pts.setEnabled(true);
            tamil.setEnabled(true);
            geography.setEnabled(true);
            music.setEnabled(true);
            dance.setEnabled(true);
            drama.setEnabled(true);
            art.setEnabled(true);
            DefaultTableModel model = (DefaultTableModel) olTable.getModel();
            model.setRowCount(0);
        } else {
            sinhala.setSelected(false);
            english.setSelected(false);
            maths.setSelected(false);
            science.setSelected(false);
            buddhist.setSelected(false);
            civic.setSelected(false);
            ict.setSelected(false);
            health.setSelected(false);
            history.setSelected(false);
            pts.setSelected(false);
            tamil.setSelected(false);
            geography.setSelected(false);
            music.setSelected(false);
            dance.setSelected(false);
            drama.setSelected(false);
            art.setSelected(false);
            ol_subject_button.setEnabled(true);
        }
        mother_name_feild.setText("");
        father_name_field.setText("");
        guardian_name_field.setText("");
        mobile_field.setText("");
        land_number_field.setText("");
        line1_field.setText("");
        line2_field.setText("");
        gradeCombo.setSelectedIndex(0);
        
    }
    
    private void loadGrade() {
        try {
            Vector gradeVector = new Vector();
            gradeVector.add("Select Grade");
            
            ResultSet gradeRs = connection_ol.search("SELECT * FROM `grade`");
            
            while (gradeRs.next()) {
                
                gradeVector.add(gradeRs.getString("grade"));
                grade_map.put(gradeRs.getString("grade"), gradeRs.getString("grade_id"));
            }
            
            gradeCombo.setModel(new DefaultComboBoxModel<>(gradeVector));
        } catch (java.sql.SQLException e) {
//                        SignIn.logger.warning(e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    private void loadClass() {
        try {
            Vector classVector = new Vector();
            classVector.add("Select Class");
            
            ResultSet classRs = connection_ol.search("SELECT * FROM `class`");
            
            while (classRs.next()) {
                
                classVector.add(classRs.getString("class_name"));
                class_map.put(classRs.getString("class_name"), classRs.getString("class_id"));
            }
            
            classCombo.setModel(new DefaultComboBoxModel<>(classVector));
        } catch (java.sql.SQLException e) {
//                        SignIn.logger.warning(e.getMessage());
            System.out.println(e);
        }
        
    }
    
    private void subjectAdd(String subjectName, String indexNumber) {
        try {
            ResultSet subjectIdRs = connection_ol.search("SELECT * FROM `subject` WHERE `subject_name`='" + subjectName + "'");
            if (subjectIdRs.next()) {
                String subjectId = subjectIdRs.getString("subject_id");
                connection_ol.iud("INSERT INTO `student_has_subject` (`subject_subject_id`,`student_student_id`) "
                        + "VALUES ('" + subjectId + "','" + indexNumber + "')");
            }
        } catch (java.sql.SQLException | NullPointerException e) {
            System.out.println(e);
        }
    }
    
    private void loadStudentTable() {
        
        try {
            
            ResultSet rs = connection_ol.search("SELECT * FROM `student` INNER JOIN `status` ON `student`.`status_status_id` = `status`.`status_id` "
                    + "INNER JOIN `gender` ON `student`.`gender_gender_id` = `gender`.`gender_id` "
                    + "INNER JOIN `parent_details` ON `student`.`parent_details_details_id` = `parent_details`.`details_id` WHERE `status_status_id`='1'");
            
            DefaultTableModel model = (DefaultTableModel) student_table.getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
                Vector studentVector = new Vector();
                studentVector.add(rs.getString("student_id"));
                studentVector.add(rs.getString("first_name"));
                studentVector.add(rs.getString("last_name"));
                studentVector.add(rs.getString("email"));
                studentVector.add(rs.getString("parent_details.parent_mobile"));
                studentVector.add(rs.getString("parent_details.land_number"));
                studentVector.add(rs.getString("parent_details.mothser's_name"));
                studentVector.add(rs.getString("parent_details.farther's_name"));
                studentVector.add(rs.getString("parent_details.guardian's_name"));
                
                model.addRow(studentVector);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        background1 = new SMMV.Component.Background();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        background2 = new SMMV.Component.Background();
        jLabel4 = new javax.swing.JLabel();
        lname_field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fname_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        gradeCombo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        classCombo = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        email_field1 = new javax.swing.JTextField();
        background5 = new SMMV.Component.Background();
        sinhala = new javax.swing.JCheckBox();
        english = new javax.swing.JCheckBox();
        maths = new javax.swing.JCheckBox();
        civic = new javax.swing.JCheckBox();
        science = new javax.swing.JCheckBox();
        buddhist = new javax.swing.JCheckBox();
        art = new javax.swing.JCheckBox();
        dance = new javax.swing.JCheckBox();
        ict = new javax.swing.JCheckBox();
        music = new javax.swing.JCheckBox();
        drama = new javax.swing.JCheckBox();
        history = new javax.swing.JCheckBox();
        pts = new javax.swing.JCheckBox();
        health = new javax.swing.JCheckBox();
        tamil = new javax.swing.JCheckBox();
        geography = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        background3 = new SMMV.Component.Background();
        ol_subject_button = new SMMV.Component.ButtonGradient();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        olTable = new javax.swing.JTable();
        enrollment_no_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        mother_name_feild = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        father_name_field = new javax.swing.JTextField();
        guardian_name_field = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        mobile_field = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        land_number_field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        studentAddButton = new SMMV.Component.ButtonGradient();
        buttonGradient2 = new SMMV.Component.ButtonGradient();
        buttonGradient3 = new SMMV.Component.ButtonGradient();
        line1_field = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        line2_field = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        background6 = new SMMV.Component.Background();
        jScrollPane2 = new javax.swing.JScrollPane();
        student_table = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        background7 = new SMMV.Component.Background();
        jLabel29 = new javax.swing.JLabel();
        search_field_student = new SMMV.Component.Rount_textfieald();
        background4 = new SMMV.Component.Background();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(0, 0, 0));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(217, 217, 217));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Student Last Name");

        lname_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gender");

        buttonGroup1.add(male);
        male.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        male.setText("Male");

        buttonGroup1.add(female);
        female.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        female.setText("Female");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Student Enrollment No");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student First Name");

        fname_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Grade");

        gradeCombo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gradeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gradeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gradeComboItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Class ");

        classCombo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        classCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Select Subject ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Email");

        email_field1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        email_field1.setForeground(new java.awt.Color(0, 0, 0));

        background5.setBackground(new java.awt.Color(0, 0, 0));
        background5.setEnabled(false);

        sinhala.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        sinhala.setText("Sinhala");

        english.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        english.setText("English");

        maths.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        maths.setText("Mathematics");

        civic.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        civic.setText("Civic");

        science.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        science.setText("Science");

        buddhist.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        buddhist.setText("Buddhist");

        art.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        art.setText("Art");

        dance.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        dance.setText("Dancing");

        ict.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        ict.setText("ICT");

        music.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        music.setText("Music");

        drama.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        drama.setText("Drama");

        history.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        history.setText("History");

        pts.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pts.setText("P.T.S");

        health.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        health.setText("Health");

        tamil.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tamil.setText("Tamil");

        geography.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        geography.setText("Geography");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Select Aesthetic subject");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Select Grade 6 - 9 Subject");

        javax.swing.GroupLayout background5Layout = new javax.swing.GroupLayout(background5);
        background5.setLayout(background5Layout);
        background5Layout.setHorizontalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background5Layout.createSequentialGroup()
                        .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background5Layout.createSequentialGroup()
                                .addComponent(ict, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(219, 219, 219))
                            .addGroup(background5Layout.createSequentialGroup()
                                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background5Layout.createSequentialGroup()
                                        .addComponent(science, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                    .addComponent(sinhala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(120, 120, 120)
                                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background5Layout.createSequentialGroup()
                                        .addComponent(english, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(15, 15, 15))
                                    .addGroup(background5Layout.createSequentialGroup()
                                        .addComponent(buddhist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8))
                                    .addComponent(health, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tamil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dance)))
                            .addGroup(background5Layout.createSequentialGroup()
                                .addComponent(pts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(185, 185, 185))
                            .addComponent(music))
                        .addGap(117, 117, 117)
                        .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background5Layout.createSequentialGroup()
                                .addComponent(civic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(background5Layout.createSequentialGroup()
                                .addComponent(maths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8))
                            .addGroup(background5Layout.createSequentialGroup()
                                .addComponent(history, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addComponent(geography, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(drama))
                        .addGap(26, 26, 26))
                    .addGroup(background5Layout.createSequentialGroup()
                        .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(art, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background5Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        background5Layout.setVerticalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sinhala)
                    .addComponent(english)
                    .addComponent(maths))
                .addGap(18, 18, 18)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(science)
                    .addComponent(buddhist)
                    .addComponent(civic))
                .addGap(18, 18, 18)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ict)
                    .addComponent(health)
                    .addComponent(history))
                .addGap(18, 18, 18)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pts)
                    .addComponent(tamil)
                    .addComponent(geography))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dance)
                    .addComponent(music)
                    .addComponent(drama))
                .addGap(18, 18, 18)
                .addComponent(art)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        background3.setBackground(new java.awt.Color(0, 0, 0));

        ol_subject_button.setText("Select Subject");
        ol_subject_button.setColor1(new java.awt.Color(0, 0, 204));
        ol_subject_button.setColor2(new java.awt.Color(139, 139, 252));
        ol_subject_button.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ol_subject_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ol_subject_buttonActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Select Grade 10 & 11 Subject");

        olTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Id", "Subject Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(olTable);

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ol_subject_button, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ol_subject_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        enrollment_no_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        enrollment_no_field.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                                        .addComponent(background5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(background2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(male)
                                .addGap(43, 43, 43)
                                .addComponent(female))
                            .addComponent(enrollment_no_field, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(gradeCombo, 0, 232, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fname_field, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lname_field, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(classCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(email_field1))))
                .addGap(9, 9, 9))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(5, 5, 5)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lname_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enrollment_no_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(male)
                                    .addComponent(female)))
                            .addGroup(background2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gradeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(5, 5, 5)
                        .addComponent(email_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Student Deatils", jPanel1);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mother's Name");

        mother_name_feild.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Father's Name");

        father_name_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        guardian_name_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Guardian's Name");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Mobile Number");

        mobile_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Land Number");

        land_number_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Parent's Details");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Address Details");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Enter Address Line 01");

        studentAddButton.setText("Add New Student");
        studentAddButton.setColor1(new java.awt.Color(0, 0, 255));
        studentAddButton.setColor2(new java.awt.Color(139, 139, 252));
        studentAddButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studentAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentAddButtonActionPerformed(evt);
            }
        });

        buttonGradient2.setText("Update Student");
        buttonGradient2.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient2.setColor2(new java.awt.Color(135, 138, 242));
        buttonGradient2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        buttonGradient3.setText("Deactive Student");
        buttonGradient3.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient3.setColor2(new java.awt.Color(149, 149, 248));
        buttonGradient3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        line1_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Enter Address Line 02");

        line2_field.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 255, 255));
        jLabel22.setText("First enter your details.After You can admit new student.");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/noti.png"))); // NOI18N

        jSeparator4.setForeground(new java.awt.Color(0, 255, 204));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 255, 204));
        jLabel24.setText("Notice");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/noti.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 255, 255));
        jLabel26.setText("Do you have parents,please fill mother and father name.you don't want to fill Guardian feild");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 255, 255));
        jLabel30.setText("Do you want to update student details.Please double click student row in table.");

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/noti.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 255, 255));
        jLabel33.setText("But you don't can update Added student subject");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mother_name_feild, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mobile_field, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(land_number_field, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(349, 349, 349))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(father_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(guardian_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonGradient3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(studentAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(line1_field, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(line2_field, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardian_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(father_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mother_name_feild, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mobile_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(land_number_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(line1_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(line2_field, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(studentAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addGap(29, 29, 29))))
        );

        jTabbedPane1.addTab("Parents Details", jPanel2);

        jPanel3.setBackground(new java.awt.Color(217, 217, 217));

        student_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Student  Admission Number", "Student First Name", "Student Last Name", "Email ", "Parent Mobile", "Land Number", "Mothser's Name", "Father's Name", "Guardian's Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        student_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                student_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(student_table);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Search Employee");

        background7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/search.png"))); // NOI18N

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        search_field_student.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        search_field_student.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_field_studentKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background6Layout.createSequentialGroup()
                        .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(search_field_student, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_field_student, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Student Table", jPanel3);

        background4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/student.png"))); // NOI18N

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Manage Student");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(768, 768, 768))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Student Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void studentAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentAddButtonActionPerformed
        
        String parentId = "";
        
        String index_number = enrollment_no_field.getText();
        String first_name = fname_field.getText();
        String last_name = lname_field.getText();
        String class_id = String.valueOf(classCombo.getSelectedItem());
        int genser = 0;
        
        if (male.isSelected()) {
            genser = 1;
        }
        if (female.isSelected()) {
            genser = 2;
        }
        String grade = String.valueOf(gradeCombo.getSelectedItem());
        String email = email_field1.getText();
        String motherName = mother_name_feild.getText();
        String fatherName = father_name_field.getText();
        String guardianName = guardian_name_field.getText();
        String mobileNumber = mobile_field.getText();
        String landNumber = land_number_field.getText();
        String line1 = line1_field.getText();
        String line2 = line2_field.getText();
        
        if (index_number.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Index Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (first_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (last_name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (class_id.equals("Select Class")) {
            JOptionPane.showMessageDialog(this, "Please Select Student Class", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!male.isSelected() && !female.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please Select Gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (grade.equals("Select Grade")) {
            JOptionPane.showMessageDialog(this, "Please Select Student Grade", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!email.matches(Validation.EMAIL_VALIDATION.validate())) {
            JOptionPane.showMessageDialog(this, "Invalid Email Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (motherName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student's Mother Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (fatherName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student's Father Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobileNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Perant's Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!mobileNumber.matches(Validation.MOBILE_NUMBER_VALIDATION.validate())) {
            JOptionPane.showMessageDialog(this, "Invalid Mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (landNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Land Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!landNumber.matches(Validation.LAND_NUMBER_VALIDATION.validate())) {
            JOptionPane.showMessageDialog(this, "Invalid Land Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (line1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Address Line 01", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (line2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Address Line 02", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            
            try {
                ResultSet rs = connection_ol.search("SELECT * FROM `student` WHERE `email`='" + email + "' OR `student_id`='" + index_number + "'");
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Email Or Student Index Number Already Use!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    
                    ResultSet perentRs = connection_ol.search("SELECT * FROM `parent_details` WHERE `parent_mobile`='" + mobileNumber + "' OR `land_number`='" + landNumber + "'");
                    
                    if (perentRs.next()) {
                        JOptionPane.showMessageDialog(this, "Parent Mobile Number or Land Number Already Use!", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        
                        ResultSet classRs = connection_ol.search("SELECT * FROM `class_has_grade` "
                                + "WHERE `class_class_id`='" + class_map.get(class_id) + "' AND `grade_grade_id`='" + grade_map.get(grade) + "'");
                        
                        if (classRs.next()) {
                            
                            connection_ol.iud("INSERT INTO `parent_details` (`mothser's_name`,`farther's_name`,`guardian's_name`,`parent_mobile`,`land_number`) "
                                    + "VALUES ('" + motherName + "','" + fatherName + "','" + guardianName + "','" + mobileNumber + "','" + landNumber + "')");
                            
                            ResultSet perentResult = connection_ol.search("SELECT * FROM `parent_details` WHERE `parent_mobile`='" + mobileNumber + "' AND `land_number`='" + landNumber + "'");
                            
                            if (perentResult.next()) {
                                parentId = perentResult.getString("details_id");
                                
                                connection_ol.iud("INSERT INTO `student` (`student_id`,`first_name`,`last_name`,`email`,`mobile`,`status_status_id`,`gender_gender_id`,`parent_details_details_id`) "
                                        + "VALUES ('" + index_number + "','" + first_name + "','" + last_name + "','" + email + "','" + mobileNumber + "','1','" + genser + "','" + parentId + "')");
                                
                                String class_grade_id = classRs.getString("class_has_grade_id");
                                
                                connection_ol.iud("INSERT INTO `class_has_grade_has_student` (`student_student_id`,`class_has_grade_class_has_grade_id`) "
                                        + "VALUES ('" + index_number + "','" + class_grade_id + "')");
                                
                                if (!sinhala.isSelected() && english.isSelected() && maths.isSelected() && science.isSelected()
                                        && buddhist.isSelected() && history.isSelected() | dance.isSelected() | drama.isSelected() | music.isSelected() | art.isSelected()) {
                                    
                                    JOptionPane.showMessageDialog(this, "Please Select Important Subject!", "Warning", JOptionPane.WARNING_MESSAGE);
                                } else {

                                    //Subject Add
                                    if (sinhala.isSelected()) {
                                        subjectAdd(sinhala.getText(), index_number);
                                    }
                                    if (english.isSelected()) {
                                        subjectAdd(english.getText(), index_number);
                                    }
                                    if (maths.isSelected()) {
                                        subjectAdd(maths.getText(), index_number);
                                    }
                                    if (science.isSelected()) {
                                        subjectAdd(science.getText(), index_number);
                                    }
                                    if (buddhist.isSelected()) {
                                        subjectAdd(buddhist.getText(), index_number);
                                    }
                                    if (civic.isSelected()) {
                                        subjectAdd(civic.getText(), index_number);
                                    }
                                    if (ict.isSelected()) {
                                        subjectAdd(ict.getText(), index_number);
                                    }
                                    if (health.isSelected()) {
                                        subjectAdd(health.getText(), index_number);
                                    }
                                    if (history.isSelected()) {
                                        subjectAdd(history.getText(), index_number);
                                    }
                                    if (pts.isSelected()) {
                                        subjectAdd(pts.getText(), index_number);
                                    }
                                    if (tamil.isSelected()) {
                                        subjectAdd(tamil.getText(), index_number);
                                    }
                                    if (geography.isSelected()) {
                                        subjectAdd(geography.getText(), index_number);
                                    }
                                    if (music.isSelected()) {
                                        subjectAdd(music.getText(), index_number);
                                    }
                                    if (dance.isSelected()) {
                                        subjectAdd(dance.getText(), index_number);
                                    }
                                    if (drama.isSelected()) {
                                        subjectAdd(drama.getText(), index_number);
                                    }
                                    if (art.isSelected()) {
                                        subjectAdd(art.getText(), index_number);
                                    }
                                    
                                    if (grade.equals("10") | grade.equals("11")) {
                                        for (int i = 0; i < 9; i++) {
                                            String olSubjectId = String.valueOf(olTable.getValueAt(i, 0));
                                            ResultSet olSubjectRs = connection_ol.search("SELECT * FROM `category_subjects` WHERE `subject_subject_id`='" + olSubjectId + "'");
                                            while (olSubjectRs.next()) {
                                                String category_id = olSubjectRs.getString("category_subjects_id");
                                                connection_ol.iud("INSERT INTO `student_for_category_subject` (`student_student_id`,`category_subjects_category_subjects_id`) "
                                                        + "VALUES ('" + index_number + "','" + category_id + "')");
                                            }
                                            connection_ol.iud("INSERT INTO `student_has_subject` (`subject_subject_id`,`student_student_id`) "
                                                    + "VALUES ('" + olSubjectId + "','" + index_number + "')");
                                        }
                                    }
                                    
                                    connection_ol.iud("INSERT INTO `address` (`line_1`,`line_2`,`student_student_id`) "
                                            + "VALUES ('" + line1 + "','" + line2 + "','" + index_number + "')");
                                    
                                    JOptionPane.showMessageDialog(this, "New Student Added Successfully", "Success", JOptionPane.DEFAULT_OPTION);
                                    clear();
                                    
                                }
                                
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid Parent!", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                            
                        } else {
                            //grade ekata classs tika insert karanawa 
                            JOptionPane.showMessageDialog(this, "Please Update Grade For Class", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }
                    
                }
                
            } catch (java.sql.SQLException e) {
                System.out.println(e);
            }
            
        }

    }//GEN-LAST:event_studentAddButtonActionPerformed

    private void gradeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gradeComboItemStateChanged
        String grade = String.valueOf(gradeCombo.getSelectedItem());
        if (grade.equals("10") | grade.equals("11")) {
            sinhala.setEnabled(false);
            english.setEnabled(false);
            maths.setEnabled(false);
            science.setEnabled(false);
            buddhist.setEnabled(false);
            civic.setEnabled(false);
            ict.setEnabled(false);
            health.setEnabled(false);
            history.setEnabled(false);
            pts.setEnabled(false);
            tamil.setEnabled(false);
            geography.setEnabled(false);
            music.setEnabled(false);
            dance.setEnabled(false);
            drama.setEnabled(false);
            art.setEnabled(false);
            ol_subject_button.setEnabled(true);
        } else {
            
            ol_subject_button.setEnabled(false);
            sinhala.setEnabled(true);
            english.setEnabled(true);
            maths.setEnabled(true);
            science.setEnabled(true);
            buddhist.setEnabled(true);
            civic.setEnabled(true);
            ict.setEnabled(true);
            health.setEnabled(true);
            history.setEnabled(true);
            pts.setEnabled(true);
            tamil.setEnabled(true);
            geography.setEnabled(true);
            music.setEnabled(true);
            dance.setEnabled(true);
            drama.setEnabled(true);
            art.setEnabled(true);
        }
    }//GEN-LAST:event_gradeComboItemStateChanged

    private void ol_subject_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ol_subject_buttonActionPerformed
        OL_Subject subject = new OL_Subject(dashboard, true, this);
        subject.setLocationRelativeTo(dashboard);
        subject.setVisible(true);
    }//GEN-LAST:event_ol_subject_buttonActionPerformed

    private void search_field_studentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_field_studentKeyPressed
        
        String search = search_field_student.getText();
        
        try {
            ResultSet rs = connection_ol.search("SELECT * FROM `student` INNER JOIN `status` ON `student`.`status_status_id` = `status`.`status_id` "
                    + "INNER JOIN `gender` ON `student`.`gender_gender_id` = `gender`.`gender_id` "
                    + "INNER JOIN `parent_details` ON `student`.`parent_details_details_id` = `parent_details`.`details_id` "
                    + "WHERE `first_name`  LIKE '" + search + "%' OR `last_name` LIKE '" + search + "%'");
            
            DefaultTableModel model = (DefaultTableModel) student_table.getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
                Vector studentVector = new Vector();
                studentVector.add(rs.getString("student_id"));
                studentVector.add(rs.getString("first_name"));
                studentVector.add(rs.getString("last_name"));
                studentVector.add(rs.getString("email"));
                studentVector.add(rs.getString("parent_details.parent_mobile"));
                studentVector.add(rs.getString("parent_details.land_number"));
                studentVector.add(rs.getString("parent_details.mothser's_name"));
                studentVector.add(rs.getString("parent_details.farther's_name"));
                studentVector.add(rs.getString("parent_details.guardian's_name"));
                
                model.addRow(studentVector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_search_field_studentKeyPressed

    private void student_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_student_tableMouseClicked
        
        if (evt.getClickCount() == 2) {
            studentAddButton.setEnabled(false);
            email_field1.setEditable(false);
            enrollment_no_field.setEditable(false);
            mobile_field.setEditable(false);
            int row = student_table.getSelectedRow();
            
            index_number = String.valueOf(student_table.getValueAt(row, 0));
            try {
                ResultSet rs = connection_ol.search("SELECT * FROM `student` INNER JOIN `status` ON `student`.`status_status_id` = `status`.`status_id` "
                        + "INNER JOIN `gender` ON `student`.`gender_gender_id` = `gender`.`gender_id` "
                        + "INNER JOIN `parent_details` ON `student`.`parent_details_details_id` = `parent_details`.`details_id` WHERE `student_id`='" + index_number + "'");
                
                if (rs.next()) {
                    enrollment_no_field.setText(String.valueOf(student_table.getValueAt(row, 0)));
                    fname_field.setText(String.valueOf(student_table.getValueAt(row, 1)));
                    lname_field.setText(String.valueOf(student_table.getValueAt(row, 2)));
                    email_field1.setText(String.valueOf(student_table.getValueAt(row, 3)));
                    mobile_field.setText(String.valueOf(student_table.getValueAt(row, 4)));
                    land_number_field.setText(String.valueOf(student_table.getValueAt(row, 5)));
                    mother_name_feild.setText(String.valueOf(student_table.getValueAt(row, 6)));
                    father_name_field.setText(String.valueOf(student_table.getValueAt(row, 7)));
                    guardian_name_field.setText(String.valueOf(student_table.getValueAt(row, 8)));
                    
                    String genderId = rs.getString("gender.name");
                    String perentId = rs.getString("parent_details_details_id");
                    
                    if (genderId.equals("Male")) {
                        male.setSelected(true);
                    } else {
                        female.setSelected(true);
                    }
                    
                    ResultSet addressRs = connection_ol.search("SELECT * FROM `address` WHERE `student_student_id`='" + index_number + "'");
                    if (addressRs.next()) {
                        String line1 = addressRs.getString("line_1");
                        String line2 = addressRs.getString("line_2");
                        line1_field.setText(line1);
                        line2_field.setText(line2);
                    }
                    
                    ResultSet classRs = connection_ol.search("SELECT * FROM `class_has_grade_has_student` WHERE `student_student_id`='" + index_number + "'");
                    if (classRs.next()) {
                        String class_grade_id = classRs.getString("class_has_grade_class_has_grade_id");
                        
                        ResultSet classHasGradeRs = connection_ol.search("SELECT * FROM `class_has_grade` WHERE `class_has_grade_id`='" + class_grade_id + "'");
                        if (classHasGradeRs.next()) {
                            String classId = classHasGradeRs.getString("class_class_id");
                            String gradeId = classHasGradeRs.getString("grade_grade_id");
                            
                            classCombo.setSelectedIndex(Integer.parseInt(classId));
                            gradeCombo.setSelectedIndex(Integer.parseInt(gradeId));
                        }
                    }
                    
                    ResultSet subjectRs = connection_ol.search("SELECT * FROM `student_has_subject`"
                            + " INNER JOIN `subject` ON `student_has_subject`.`subject_subject_id` = `subject`.`subject_id` WHERE `student_student_id`='" + index_number + "'");
                    
                    OL_Subject olSubject = new OL_Subject(dashboard, true, this);
                    
                    while (subjectRs.next()) {        
                        String subjectName = subjectRs.getString("subject.subject_name");
                        String english = olSubject.getEnglish().getText();
                        if(subjectName.equals(english)){
                            olSubject.getEnglish().setSelected(true);
                        }
                    }
                    olSubject.setVisible(true);
                    
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_student_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox art;
    private SMMV.Component.Background background1;
    private SMMV.Component.Background background2;
    private SMMV.Component.Background background3;
    private SMMV.Component.Background background4;
    private SMMV.Component.Background background5;
    private SMMV.Component.Background background6;
    private SMMV.Component.Background background7;
    private javax.swing.JCheckBox buddhist;
    private SMMV.Component.ButtonGradient buttonGradient2;
    private SMMV.Component.ButtonGradient buttonGradient3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox civic;
    private javax.swing.JComboBox<String> classCombo;
    private javax.swing.JCheckBox dance;
    private javax.swing.JCheckBox drama;
    private javax.swing.JTextField email_field1;
    private javax.swing.JCheckBox english;
    private javax.swing.JTextField enrollment_no_field;
    private javax.swing.JTextField father_name_field;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField fname_field;
    private javax.swing.JCheckBox geography;
    private javax.swing.JComboBox<String> gradeCombo;
    private javax.swing.JTextField guardian_name_field;
    private javax.swing.JCheckBox health;
    private javax.swing.JCheckBox history;
    private javax.swing.JCheckBox ict;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField land_number_field;
    private javax.swing.JTextField line1_field;
    private javax.swing.JTextField line2_field;
    private javax.swing.JTextField lname_field;
    private javax.swing.JRadioButton male;
    private javax.swing.JCheckBox maths;
    private javax.swing.JTextField mobile_field;
    private javax.swing.JTextField mother_name_feild;
    private javax.swing.JCheckBox music;
    private javax.swing.JTable olTable;
    private SMMV.Component.ButtonGradient ol_subject_button;
    private javax.swing.JCheckBox pts;
    private javax.swing.JCheckBox science;
    private SMMV.Component.Rount_textfieald search_field_student;
    private javax.swing.JCheckBox sinhala;
    private SMMV.Component.ButtonGradient studentAddButton;
    private javax.swing.JTable student_table;
    private javax.swing.JCheckBox tamil;
    // End of variables declaration//GEN-END:variables
}
