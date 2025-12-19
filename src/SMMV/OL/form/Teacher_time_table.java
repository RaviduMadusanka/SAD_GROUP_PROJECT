/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SMMV.OL.form;

import SMMV.Connection.connection_ol;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Teacher_time_table extends javax.swing.JPanel {

    HashMap<String, String> dayMap = new HashMap<>();
    HashMap<String, String> teacherMap = new HashMap<>();
    HashMap<String, String> subjectMap = new HashMap<>();
    HashMap<String, String> classtMap = new HashMap<>();

    String updateID = "";

    /**
     * Creates new form Teacher_time_table
     */
    public Teacher_time_table() {
        initComponents();
        init();
        loadTable("","","");
        buttonGradient6.setEnabled(true);
        buttonGradient7.setEnabled(false);
    }

    public void init() {

        try {
            Vector user_typeVector = new Vector();
            user_typeVector.add("Select Day...");

            ResultSet dayRs = connection_ol.search("SELECT * FROM `days`");

            while (dayRs.next()) {

                user_typeVector.add(dayRs.getString("day_name"));
                dayMap.put(dayRs.getString("day_name"), dayRs.getString("day_id"));
            }

            jComboBox5.setModel(new DefaultComboBoxModel<>(user_typeVector));
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        try {
            Vector user_typeVector = new Vector();
            user_typeVector.add("Select Teacher...");

            ResultSet teacherRs = connection_ol.search("SELECT * FROM `teacher` WHERE `status_status_id` = '1'");

            while (teacherRs.next()) {

                user_typeVector.add(teacherRs.getString("first_name") + " " + teacherRs.getString("last_name"));
                teacherMap.put((teacherRs.getString("first_name") + " " + teacherRs.getString("last_name")), teacherRs.getString("teacher_id"));
            }

            jComboBox6.setModel(new DefaultComboBoxModel<>(user_typeVector));
            jComboBox9.setModel(new DefaultComboBoxModel<>(user_typeVector));
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        try {
            Vector user_typeVector = new Vector();
            user_typeVector.add("Select Subject...");

            ResultSet subjectRs = connection_ol.search("SELECT * FROM `subject`");

            while (subjectRs.next()) {

                user_typeVector.add(subjectRs.getString("subject_name"));
                subjectMap.put(subjectRs.getString("subject_name"), subjectRs.getString("subject_id"));
            }

            jComboBox7.setModel(new DefaultComboBoxModel<>(user_typeVector));
            jComboBox11.setModel(new DefaultComboBoxModel<>(user_typeVector));
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        try {
            Vector user_typeVector = new Vector();
            user_typeVector.add("Select Class...");

            ResultSet classRs = connection_ol.search("SELECT * FROM `class_has_grade` INNER JOIN `class` ON `class_has_grade`.`class_class_id` = `class`.`class_id`"
                    + "INNER JOIN `grade` ON `class_has_grade`.`grade_grade_id` = `grade`.`grade_id` ");

            while (classRs.next()) {

                user_typeVector.add(classRs.getString("grade.grade") + "_" + classRs.getString("class.class_name"));
                classtMap.put(classRs.getString("grade.grade") + "_" + classRs.getString("class.class_name"), classRs.getString("class_has_grade_id"));
            }

            jComboBox8.setModel(new DefaultComboBoxModel<>(user_typeVector));
            jComboBox10.setModel(new DefaultComboBoxModel<>(user_typeVector));
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadTable(String teacher, String ClassGrade, String Subject) {

        String quary = "";

        if (!teacher.isEmpty() || !ClassGrade.isEmpty() || !Subject.isEmpty()) {

            if (!teacher.isEmpty()) {
                if (!ClassGrade.isEmpty()) {
                    if (!Subject.isEmpty()) {
                        quary = "WHERE `teacher_teacher_id` = '" + teacherMap.get(teacher) + "' AND `time_table`.`class_has_grade_id` = '" + classtMap.get(ClassGrade) + "' AND `subject_subject_id` = '" + subjectMap.get(Subject) + "'";
                    }else{
                        quary = "WHERE `teacher_teacher_id` = '" + teacherMap.get(teacher) + "' AND `time_table`.`class_has_grade_id` = '" + classtMap.get(ClassGrade) + "'";
                    }
                } else {
                    if (!Subject.isEmpty()) {
                        quary = "WHERE `teacher_teacher_id` = '" + teacherMap.get(teacher) + "' AND `subject_subject_id` = '" + subjectMap.get(Subject) + "'";
                    }else{
                        quary = "WHERE `teacher_teacher_id` = '" + teacherMap.get(teacher) + "'";
                    }
                }
            } else {
                if (!ClassGrade.isEmpty()) {
                    if (!Subject.isEmpty()) {
                        quary = "WHERE `time_table`.`class_has_grade_id` = '" + classtMap.get(ClassGrade) + "' AND `subject_subject_id` = '" + subjectMap.get(Subject) + "'";
                    }else{
                        quary = "WHERE `time_table`.`class_has_grade_id` = '" + classtMap.get(ClassGrade) + "'";
                    }
                } else {
                    if (!Subject.isEmpty()) {
                        quary = "WHERE `subject_subject_id` = '" + subjectMap.get(Subject) + "'";
                    }else{
                        quary = "WHERE `time_table`.`class_has_grade_id` = '" + classtMap.get(ClassGrade) + "'";
                    }
                }
            }
        }

        try {

            ResultSet rs = connection_ol.search("SELECT * FROM `time_table` "
                    + "INNER JOIN `class_has_grade` ON `time_table`.`class_has_grade_id` = `class_has_grade`.`class_has_grade_id` "
                    + "INNER JOIN `class` ON `class`.`class_id` = `class_has_grade`.`class_class_id` "
                    + "INNER JOIN `grade` ON `grade`.`grade_id` = `class_has_grade`.`grade_grade_id` "
                    + "INNER JOIN `subject` ON `time_table`.`subject_subject_id` = `subject`.`subject_id` "
                    + "INNER JOIN `teacher` ON `time_table`.`teacher_teacher_id` = `teacher`.`teacher_id` "
                    + "INNER JOIN `days` ON `time_table`.`days_day_id` = `days`.`day_id` " + quary + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector employeeVector = new Vector();
                employeeVector.add(rs.getString("time_table_id"));
                employeeVector.add(rs.getString("teacher.first_name") + " " + rs.getString("teacher.last_name"));
                employeeVector.add(rs.getString("start_time"));
                employeeVector.add(rs.getString("end_time"));
                employeeVector.add(rs.getString("days.day_name"));
                employeeVector.add(rs.getString("grade.grade") + "_" + rs.getString("class.class_name"));
                employeeVector.add(rs.getString("subject.subject_name"));

                model.addRow(employeeVector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clear() {

        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
        
        jComboBox9.setSelectedIndex(0);
        jComboBox10.setSelectedIndex(0);
        jComboBox11.setSelectedIndex(0);
        buttonGradient6.setEnabled(true);
        buttonGradient7.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.raven.swing.TimePicker();
        timePicker2 = new com.raven.swing.TimePicker();
        background1 = new SMMV.Component.Background();
        background2 = new SMMV.Component.Background();
        jLabel3 = new javax.swing.JLabel();
        background7 = new SMMV.Component.Background();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        start_time = new javax.swing.JTextField();
        endTimeButton = new javax.swing.JButton();
        end_time = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        buttonGradient6 = new SMMV.Component.ButtonGradient();
        buttonGradient7 = new SMMV.Component.ButtonGradient();
        background3 = new SMMV.Component.Background();
        background6 = new SMMV.Component.Background();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGradient9 = new SMMV.Component.ButtonGradient();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        timePicker1.setDisplayText(start_time);

        timePicker2.setForeground(new java.awt.Color(255, 0, 0));
        timePicker2.setDisplayText(end_time);

        setBackground(new java.awt.Color(0, 0, 0));

        background1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Teacher Time Table");

        background7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/admin.png"))); // NOI18N

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Select Day");

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Select Teacher");

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Select Start Time");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        start_time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        endTimeButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        endTimeButton.setText("Select End Time");
        endTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTimeButtonActionPerformed(evt);
            }
        });

        end_time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Select Subject");

        jComboBox7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Select Class");

        jComboBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGradient6.setText("Add New Time Table");
        buttonGradient6.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient6.setColor2(new java.awt.Color(139, 139, 252));
        buttonGradient6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        buttonGradient7.setText("Update Time Table");
        buttonGradient7.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient7.setColor2(new java.awt.Color(139, 139, 252));
        buttonGradient7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonGradient7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(start_time)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addComponent(endTimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(end_time))
                .addGap(25, 25, 25)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, 0, 238, Short.MAX_VALUE)
                            .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox6, 0, 228, Short.MAX_VALUE)
                            .addComponent(buttonGradient6, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonGradient7, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(jComboBox7, 0, 228, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(background7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(95, 95, 95))
                            .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(background2Layout.createSequentialGroup()
                                    .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(15, 15, 15)
                                    .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(start_time, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(25, 25, 25)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(endTimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(end_time, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        background6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SMMV/OL/img/search.png"))); // NOI18N

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Teacher Name", "Start Time", "End Time", "Day", "Class", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        buttonGradient9.setText("Filter");
        buttonGradient9.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient9.setColor2(new java.awt.Color(139, 139, 252));
        buttonGradient9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonGradient9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient9ActionPerformed(evt);
            }
        });

        jComboBox9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(" Filter");

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(background3Layout.createSequentialGroup()
                        .addComponent(background6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(jComboBox9, 0, 200, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox10, 0, 200, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox11, 0, 200, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonGradient9, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox11)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGradient9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        timePicker1.showPopup(this, 100, 100);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void endTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTimeButtonActionPerformed
        // TODO add your handling code here:
        timePicker2.showPopup(this, 100, 100);
    }//GEN-LAST:event_endTimeButtonActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed

        String sTime = start_time.getText();
        String eTime = end_time.getText();
        String day = jComboBox5.getSelectedItem().toString();
        String teacher = jComboBox6.getSelectedItem().toString();
        String subject = jComboBox7.getSelectedItem().toString();
        String classGrade = jComboBox8.getSelectedItem().toString();

        if (sTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select Start Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (eTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select End Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox5.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox6.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Teacher", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox7.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox8.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Class", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String sTimeString = SMMV.OL.model.DateFormat.Formact(sTime);
            String eTimeString = SMMV.OL.model.DateFormat.Formact(eTime);

            try {
                ResultSet test1Rs = connection_ol.search("SELECT * FROM `subject_has_teacher` WHERE `teacher_teacher_id`= '" + teacherMap.get(teacher) + "'");

                if (test1Rs.next()) {
                    ResultSet test2Rs = connection_ol.search("SELECT * FROM `subject_has_teacher` WHERE `teacher_teacher_id`= '" + teacherMap.get(teacher) + "' "
                            + "AND `subject_subject_id`= '" + subjectMap.get(subject) + "'");

                    if (!test2Rs.next()) {
                        connection_ol.iud("INSERT INTO `subject_has_teacher` (`subject_subject_id`,`teacher_teacher_id`) "
                                + "VALUES ('" + subjectMap.get(subject) + "','" + teacherMap.get(teacher) + "')");
                    }
                } else {
                    connection_ol.iud("INSERT INTO `subject_has_teacher` (`subject_subject_id`,`teacher_teacher_id`) "
                            + "VALUES ('" + subjectMap.get(subject) + "','" + teacherMap.get(teacher) + "')");
                }

                connection_ol.iud("INSERT INTO `time_table` (`class_has_grade_id`,`subject_subject_id`,`teacher_teacher_id`,`days_day_id`,`start_time`,`end_time`) "
                        + "VALUES ('" + classtMap.get(classGrade) + "','" + subjectMap.get(subject) + "','" + teacherMap.get(teacher) + "','" + dayMap.get(day) + "',"
                        + "'" + sTimeString + "','" + eTimeString + "')");

                clear();
                loadTable("", "", "");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_buttonGradient6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {

            buttonGradient6.setEnabled(false);
            buttonGradient7.setEnabled(true);

            int row = jTable1.getSelectedRow();

            updateID = String.valueOf(jTable1.getValueAt(row, 0));

            jComboBox5.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 4)));
            jComboBox6.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 1)));
            jComboBox7.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 6)));
            jComboBox8.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 5)));

            try {

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonGradient7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient7ActionPerformed
        String sTime = start_time.getText();
        String eTime = end_time.getText();
        String day = jComboBox5.getSelectedItem().toString();
        String teacher = jComboBox6.getSelectedItem().toString();
        String subject = jComboBox7.getSelectedItem().toString();
        String classGrade = jComboBox8.getSelectedItem().toString();

        if (sTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select Start Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (eTime.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select End Time", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox5.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox6.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Teacher", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox7.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jComboBox8.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Class", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String sTimeString = SMMV.OL.model.DateFormat.Formact(sTime);
            String eTimeString = SMMV.OL.model.DateFormat.Formact(eTime);

            try {

                connection_ol.iud("UPDATE `time_table` SET `class_has_grade_id` = '" + classtMap.get(classGrade) + "' , `subject_subject_id` = '" + subjectMap.get(subject) + "' , "
                        + "`teacher_teacher_id` = '" + teacherMap.get(teacher) + "', `days_day_id` = '" + dayMap.get(day) + "', "
                        + "`start_time` = '" + sTimeString + "', `end_time` = '" + eTimeString + "' "
                        + "WHERE `time_table_id` = '" + updateID + "'");

                clear();
                loadTable("","","");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_buttonGradient7ActionPerformed

    private void buttonGradient9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient9ActionPerformed
        String teacher = jComboBox9.getSelectedItem().toString();
        String classGrade = jComboBox10.getSelectedItem().toString();
        String subject = jComboBox11.getSelectedItem().toString();
        
        if(jComboBox9.getSelectedIndex() == 0){
            teacher = "";
        }
        
        if(jComboBox10.getSelectedIndex() == 0){
            classGrade = "";
        }
        
        if(jComboBox11.getSelectedIndex() == 0){
            subject = "";
        }
        
        loadTable(teacher, classGrade, subject);
    }//GEN-LAST:event_buttonGradient9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SMMV.Component.Background background1;
    private SMMV.Component.Background background2;
    private SMMV.Component.Background background3;
    private SMMV.Component.Background background6;
    private SMMV.Component.Background background7;
    private SMMV.Component.ButtonGradient buttonGradient6;
    private SMMV.Component.ButtonGradient buttonGradient7;
    private SMMV.Component.ButtonGradient buttonGradient9;
    private javax.swing.JButton endTimeButton;
    private javax.swing.JTextField end_time;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField start_time;
    private com.raven.swing.TimePicker timePicker1;
    private com.raven.swing.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables
}
