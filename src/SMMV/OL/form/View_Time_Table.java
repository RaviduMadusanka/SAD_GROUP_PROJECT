/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SMMV.OL.form;

import SMMV.Connection.connection_ol;
import java.awt.Label;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class View_Time_Table extends javax.swing.JPanel {

    HashMap<String, String> teacherMap = new HashMap<>();
    HashMap<String, String> classtMap = new HashMap<>();

    Vector MONDAYV = new Vector();
    Vector TUESDAYV = new Vector();
    Vector WEDNESDAYV = new Vector();
    Vector THURSDAYV = new Vector();
    Vector FRIDAYV = new Vector();

    /**
     * Creates new form Class_Time_Table
     */
    public View_Time_Table() {
        initComponents();
        init();
    }

    public void clear() {

        MONDAYV.clear();
        TUESDAYV.clear();
        WEDNESDAYV.clear();
        THURSDAYV.clear();
        FRIDAYV.clear();

        MONDAY1.setText("");
        MONDAY2.setText("");
        MONDAY3.setText("");
        MONDAY4.setText("");
        MONDAY5.setText("");
        MONDAY6.setText("");
        MONDAY7.setText("");
        MONDAY8.setText("");

        TUESDAY1.setText("");
        TUESDAY2.setText("");
        TUESDAY3.setText("");
        TUESDAY4.setText("");
        TUESDAY5.setText("");
        TUESDAY6.setText("");
        TUESDAY7.setText("");
        TUESDAY8.setText("");

        WEDNESDAY1.setText("");
        WEDNESDAY2.setText("");
        WEDNESDAY3.setText("");
        WEDNESDAY4.setText("");
        WEDNESDAY5.setText("");
        WEDNESDAY6.setText("");
        WEDNESDAY7.setText("");
        WEDNESDAY8.setText("");

        THURSDAY1.setText("");
        THURSDAY2.setText("");
        THURSDAY3.setText("");
        THURSDAY4.setText("");
        THURSDAY5.setText("");
        THURSDAY6.setText("");
        THURSDAY7.setText("");
        THURSDAY8.setText("");

        FRIDAY1.setText("");
        FRIDAY2.setText("");
        FRIDAY3.setText("");
        FRIDAY4.setText("");
        FRIDAY5.setText("");
        FRIDAY6.setText("");
        FRIDAY7.setText("");
        FRIDAY8.setText("");
    }

    public void init() {

        try {
            Vector user_typeVector = new Vector();
            user_typeVector.add("Select Teacher...");

            ResultSet teacherRs = connection_ol.search("SELECT * FROM `teacher` WHERE `status_status_id` = '1'");

            while (teacherRs.next()) {

                user_typeVector.add(teacherRs.getString("first_name") + " " + teacherRs.getString("last_name"));
                teacherMap.put((teacherRs.getString("first_name") + " " + teacherRs.getString("last_name")), teacherRs.getString("teacher_id"));
            }

            jComboBox6.setModel(new DefaultComboBoxModel<>(user_typeVector));
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
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadData(Vector name, JLabel lableName1, JLabel lableName2, JLabel lableName3, JLabel lableName4, JLabel lableName5, JLabel lableName6, JLabel lableName7, JLabel lableName8) {

        for (Integer i = 0; i < name.size(); i++) {
            if (i == 0) {
                lableName1.setText(name.get(i).toString());
            } else if (i == 1) {
                lableName2.setText(name.get(i).toString());
            } else if (i == 2) {
                lableName3.setText(name.get(i).toString());
            } else if (i == 3) {
                lableName4.setText(name.get(i).toString());
            } else if (i == 4) {
                lableName5.setText(name.get(i).toString());
            } else if (i == 5) {
                lableName6.setText(name.get(i).toString());
            } else if (i == 6) {
                lableName7.setText(name.get(i).toString());
            } else if (i == 7) {
                lableName8.setText(name.get(i).toString());
            }
        }

    }
    
    public void loadTable(boolean type, String selectData) {

        String data1 = "";
        String data2 = "";
        String data3 = "";
        String data4 = "";
        String data5 = "";
        String data6 = "";
        String data7 = "";
        String data8 = "";

        String data9 = "";
        String data10 = "";
        String data11 = "";
        String data12 = "";
        String data13 = "";
        String data14 = "";
        String data15 = "";
        String data16 = "";

        String data17 = "";
        String data18 = "";
        String data19 = "";
        String data20 = "";
        String data21 = "";
        String data22 = "";
        String data23 = "";
        String data24 = "";

        String data25 = "";
        String data26 = "";
        String data27 = "";
        String data28 = "";
        String data29 = "";
        String data30 = "";
        String data31 = "";
        String data32 = "";

        String data33 = "";
        String data34 = "";
        String data35 = "";
        String data36 = "";
        String data37 = "";
        String data38 = "";
        String data39 = "";
        String data40 = "";

        clear();

        String query = "";

        if (type) {
            query = "WHERE `time_table`.`class_has_grade_id` = '" + classtMap.get(selectData) + "'";
        } else {
            query = "WHERE `time_table`.`teacher_teacher_id` = '" + teacherMap.get(selectData) + "'";
        }

        try {

            ResultSet rs = connection_ol.search("SELECT * FROM `time_table` INNER JOIN `days` ON `time_table`.`days_day_id` = `days`.`day_id` "
                    + "INNER JOIN `subject` ON `time_table`.`subject_subject_id` = `subject`.`subject_id` "
                    + "INNER JOIN `class_has_grade` ON `time_table`.`class_has_grade_id` = `class_has_grade`.`class_has_grade_id` "
                    + "INNER JOIN `class` ON `class_has_grade`.`class_class_id` = `class`.`class_id` "
                    + "INNER JOIN `grade` ON `class_has_grade`.`grade_grade_id` = `grade`.`grade_id` "
                    + "INNER JOIN `period` ON `time_table`.`period_id` = `period`.`id` " + query + " ORDER BY `days`.`day_id` ASC, `period`.`id` ASC");

            while (rs.next()) {

                if (rs.getString("day_name").equals("MONDAY")) {

                    if (type) {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data1 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data2 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data3 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data4 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data5 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data6 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data7 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data8 = rs.getString("subject.subject_name");
                        }
                    } else {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data8 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data2 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data3 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data4 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data5 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data6 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data7 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data8 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        }
                    }

                } else if (rs.getString("day_name").equals("TUESDAY")) {
                    if (type) {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data9 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data10 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data11 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data12 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data13 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data14 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data15 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data16 = rs.getString("subject.subject_name");
                        }
                    } else {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data9 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data10 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data11 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data12 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data13 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data14 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data15 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data16 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        }
                    }

                } else if (rs.getString("day_name").equals("WEDNESDAY")) {
                    if (type) {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data17 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data18 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data19 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data20 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data21 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data22 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data23 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data24 = rs.getString("subject.subject_name");
                        }
                    } else {

                        if (rs.getString("period.period_name").equals("period_01")) {
                            data17 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data18 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data19 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data20 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data21 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data22 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data23 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data24 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        }
                    }

                } else if (rs.getString("day_name").equals("THURSDAY")) {
                    if (type) {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data25 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data26 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data27 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data28 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data29 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data30 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data31 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data32 = rs.getString("subject.subject_name");
                        }
                    } else {

                        if (rs.getString("period.period_name").equals("period_01")) {
                            data25 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data26 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data27 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data28 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data29 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data30 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data31 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data32 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        }
                    }

                } else if (rs.getString("day_name").equals("FRIDAY")) {
                    if (type) {
                        if (rs.getString("period.period_name").equals("period_01")) {
                            data33 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data34 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data35 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data36 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data37 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data38 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data39 = rs.getString("subject.subject_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data40 = rs.getString("subject.subject_name");
                        }
                    } else {

                        if (rs.getString("period.period_name").equals("period_01")) {
                            data33 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_02")) {
                            data34 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_03")) {
                            data35 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_04")) {
                            data36 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_05")) {
                            data37 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_06")) {
                            data38 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_07")) {
                            data39 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        } else if (rs.getString("period.period_name").equals("period_08")) {
                            data40 = rs.getString("grade.grade") + "_" + rs.getString("class.class_name");
                        }
                    }

                }
            }
            if (!rs.next()) {

                if (!data1.equals("")) {
                    MONDAYV.add(data1);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data2.equals("")) {
                    MONDAYV.add(data2);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data3.equals("")) {
                    MONDAYV.add(data3);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data4.equals("")) {
                    MONDAYV.add(data4);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data5.equals("")) {
                    MONDAYV.add(data5);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data6.equals("")) {
                    MONDAYV.add(data6);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data7.equals("")) {
                    MONDAYV.add(data7);
                } else {
                    MONDAYV.add("FREE");
                }
                if (!data8.equals("")) {
                    MONDAYV.add(data8);
                } else {
                    MONDAYV.add("FREE");
                }
                
                if (!data9.equals("")) {
                    TUESDAYV.add(data9);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data10.equals("")) {
                    TUESDAYV.add(data10);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data11.equals("")) {
                    TUESDAYV.add(data11);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data12.equals("")) {
                    TUESDAYV.add(data12);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data13.equals("")) {
                    TUESDAYV.add(data13);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data14.equals("")) {
                    TUESDAYV.add(data14);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data15.equals("")) {
                    TUESDAYV.add(data15);
                } else {
                    TUESDAYV.add("FREE");
                }
                if (!data16.equals("")) {
                    TUESDAYV.add(data16);
                } else {
                    TUESDAYV.add("FREE");
                }
                
                if (!data17.equals("")) {
                    WEDNESDAYV.add(data17);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data18.equals("")) {
                    WEDNESDAYV.add(data18);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data19.equals("")) {
                    WEDNESDAYV.add(data19);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data20.equals("")) {
                    WEDNESDAYV.add(data20);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data21.equals("")) {
                    WEDNESDAYV.add(data21);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data22.equals("")) {
                    WEDNESDAYV.add(data22);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data23.equals("")) {
                    WEDNESDAYV.add(data23);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                if (!data24.equals("")) {
                    WEDNESDAYV.add(data24);
                } else {
                    WEDNESDAYV.add("FREE");
                }
                
                if (!data25.equals("")) {
                    THURSDAYV.add(data25);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data26.equals("")) {
                    THURSDAYV.add(data26);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data27.equals("")) {
                    THURSDAYV.add(data27);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data28.equals("")) {
                    THURSDAYV.add(data28);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data29.equals("")) {
                    THURSDAYV.add(data29);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data30.equals("")) {
                    THURSDAYV.add(data30);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data31.equals("")) {
                    THURSDAYV.add(data31);
                } else {
                    THURSDAYV.add("FREE");
                }
                if (!data32.equals("")) {
                    THURSDAYV.add(data32);
                } else {
                    THURSDAYV.add("FREE");
                }
                
                if (!data33.equals("")) {
                    FRIDAYV.add(data33);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data34.equals("")) {
                    FRIDAYV.add(data34);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data35.equals("")) {
                    FRIDAYV.add(data35);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data36.equals("")) {
                    FRIDAYV.add(data36);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data37.equals("")) {
                    FRIDAYV.add(data37);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data38.equals("")) {
                    FRIDAYV.add(data38);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data39.equals("")) {
                    FRIDAYV.add(data39);
                } else {
                    FRIDAYV.add("FREE");
                }
                if (!data40.equals("")) {
                    FRIDAYV.add(data40);
                } else {
                    FRIDAYV.add("FREE");
                }

            }

            loadData(MONDAYV, MONDAY1, MONDAY2, MONDAY3, MONDAY4, MONDAY5, MONDAY6, MONDAY7, MONDAY8);
            loadData(TUESDAYV, TUESDAY1, TUESDAY2, TUESDAY3, TUESDAY4, TUESDAY5, TUESDAY6, TUESDAY7, TUESDAY8);
            loadData(WEDNESDAYV, WEDNESDAY1, WEDNESDAY2, WEDNESDAY3, WEDNESDAY4, WEDNESDAY5, WEDNESDAY6, WEDNESDAY7, WEDNESDAY8);
            loadData(THURSDAYV, THURSDAY1, THURSDAY2, THURSDAY3, THURSDAY4, THURSDAY5, THURSDAY6, THURSDAY7, THURSDAY8);
            loadData(FRIDAYV, FRIDAY1, FRIDAY2, FRIDAY3, FRIDAY4, FRIDAY5, FRIDAY6, FRIDAY7, FRIDAY8);

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

        timePicker1 = new com.raven.swing.TimePicker();
        timePicker2 = new com.raven.swing.TimePicker();
        background1 = new SMMV.Component.Background();
        background2 = new SMMV.Component.Background();
        jLabel3 = new javax.swing.JLabel();
        background7 = new SMMV.Component.Background();
        jLabel9 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        buttonGradient6 = new SMMV.Component.ButtonGradient();
        background3 = new SMMV.Component.Background();
        background4 = new SMMV.Component.Background();
        background5 = new SMMV.Component.Background();
        jLabel48 = new javax.swing.JLabel();
        background6 = new SMMV.Component.Background();
        background13 = new SMMV.Component.Background();
        jLabel1 = new javax.swing.JLabel();
        background18 = new SMMV.Component.Background();
        MONDAY1 = new javax.swing.JLabel();
        background19 = new SMMV.Component.Background();
        MONDAY2 = new javax.swing.JLabel();
        background20 = new SMMV.Component.Background();
        MONDAY3 = new javax.swing.JLabel();
        background21 = new SMMV.Component.Background();
        MONDAY4 = new javax.swing.JLabel();
        background44 = new SMMV.Component.Background();
        MONDAY5 = new javax.swing.JLabel();
        background8 = new SMMV.Component.Background();
        background14 = new SMMV.Component.Background();
        jLabel2 = new javax.swing.JLabel();
        background23 = new SMMV.Component.Background();
        TUESDAY1 = new javax.swing.JLabel();
        background24 = new SMMV.Component.Background();
        TUESDAY2 = new javax.swing.JLabel();
        background25 = new SMMV.Component.Background();
        TUESDAY3 = new javax.swing.JLabel();
        background26 = new SMMV.Component.Background();
        TUESDAY4 = new javax.swing.JLabel();
        background27 = new SMMV.Component.Background();
        TUESDAY5 = new javax.swing.JLabel();
        background10 = new SMMV.Component.Background();
        background15 = new SMMV.Component.Background();
        jLabel4 = new javax.swing.JLabel();
        background28 = new SMMV.Component.Background();
        WEDNESDAY1 = new javax.swing.JLabel();
        background29 = new SMMV.Component.Background();
        WEDNESDAY2 = new javax.swing.JLabel();
        background30 = new SMMV.Component.Background();
        WEDNESDAY3 = new javax.swing.JLabel();
        background31 = new SMMV.Component.Background();
        WEDNESDAY4 = new javax.swing.JLabel();
        background32 = new SMMV.Component.Background();
        WEDNESDAY5 = new javax.swing.JLabel();
        background11 = new SMMV.Component.Background();
        background17 = new SMMV.Component.Background();
        jLabel6 = new javax.swing.JLabel();
        background38 = new SMMV.Component.Background();
        FRIDAY1 = new javax.swing.JLabel();
        background39 = new SMMV.Component.Background();
        FRIDAY2 = new javax.swing.JLabel();
        background40 = new SMMV.Component.Background();
        FRIDAY3 = new javax.swing.JLabel();
        background41 = new SMMV.Component.Background();
        FRIDAY4 = new javax.swing.JLabel();
        background42 = new SMMV.Component.Background();
        FRIDAY5 = new javax.swing.JLabel();
        background12 = new SMMV.Component.Background();
        background16 = new SMMV.Component.Background();
        jLabel5 = new javax.swing.JLabel();
        background33 = new SMMV.Component.Background();
        THURSDAY1 = new javax.swing.JLabel();
        background34 = new SMMV.Component.Background();
        THURSDAY2 = new javax.swing.JLabel();
        background35 = new SMMV.Component.Background();
        THURSDAY3 = new javax.swing.JLabel();
        background36 = new SMMV.Component.Background();
        THURSDAY4 = new javax.swing.JLabel();
        background37 = new SMMV.Component.Background();
        THURSDAY5 = new javax.swing.JLabel();
        background43 = new SMMV.Component.Background();
        jLabel49 = new javax.swing.JLabel();
        background45 = new SMMV.Component.Background();
        background22 = new SMMV.Component.Background();
        MONDAY8 = new javax.swing.JLabel();
        background46 = new SMMV.Component.Background();
        MONDAY6 = new javax.swing.JLabel();
        background47 = new SMMV.Component.Background();
        MONDAY7 = new javax.swing.JLabel();
        background48 = new SMMV.Component.Background();
        background49 = new SMMV.Component.Background();
        TUESDAY8 = new javax.swing.JLabel();
        background50 = new SMMV.Component.Background();
        TUESDAY6 = new javax.swing.JLabel();
        background51 = new SMMV.Component.Background();
        TUESDAY7 = new javax.swing.JLabel();
        background52 = new SMMV.Component.Background();
        background53 = new SMMV.Component.Background();
        WEDNESDAY8 = new javax.swing.JLabel();
        background54 = new SMMV.Component.Background();
        WEDNESDAY6 = new javax.swing.JLabel();
        background55 = new SMMV.Component.Background();
        WEDNESDAY7 = new javax.swing.JLabel();
        background56 = new SMMV.Component.Background();
        background57 = new SMMV.Component.Background();
        THURSDAY8 = new javax.swing.JLabel();
        background58 = new SMMV.Component.Background();
        THURSDAY6 = new javax.swing.JLabel();
        background59 = new SMMV.Component.Background();
        THURSDAY7 = new javax.swing.JLabel();
        background70 = new SMMV.Component.Background();
        background71 = new SMMV.Component.Background();
        FRIDAY7 = new javax.swing.JLabel();
        background72 = new SMMV.Component.Background();
        FRIDAY6 = new javax.swing.JLabel();
        background73 = new SMMV.Component.Background();
        FRIDAY8 = new javax.swing.JLabel();
        background74 = new SMMV.Component.Background();
        buttonGradient7 = new SMMV.Component.ButtonGradient();

        timePicker2.setForeground(new java.awt.Color(204, 0, 0));

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new java.awt.BorderLayout());

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

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox6MouseClicked(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox8MouseClicked(evt);
            }
        });

        buttonGradient6.setText("Search Time Table");
        buttonGradient6.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient6.setColor2(new java.awt.Color(139, 139, 252));
        buttonGradient6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(buttonGradient6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        background4.setBackground(new java.awt.Color(0, 51, 51));

        background5.setBackground(new java.awt.Color(0, 0, 51));

        jLabel48.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("T  I  M  E      T  A  B  L  E");

        javax.swing.GroupLayout background5Layout = new javax.swing.GroupLayout(background5);
        background5.setLayout(background5Layout);
        background5Layout.setHorizontalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        background5Layout.setVerticalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
            .addGroup(background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(background5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        background6.setBackground(new java.awt.Color(0, 0, 51));
        background6.setPreferredSize(new java.awt.Dimension(195, 30));

        background13.setBackground(new java.awt.Color(0, 51, 51));
        background13.setPreferredSize(new java.awt.Dimension(195, 30));
        background13.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MONDAY");
        background13.add(jLabel1, java.awt.BorderLayout.CENTER);

        background18.setBackground(new java.awt.Color(0, 51, 51));
        background18.setPreferredSize(new java.awt.Dimension(195, 30));
        background18.setLayout(new java.awt.BorderLayout());

        MONDAY1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background18.add(MONDAY1, java.awt.BorderLayout.CENTER);

        background19.setBackground(new java.awt.Color(0, 51, 51));
        background19.setPreferredSize(new java.awt.Dimension(195, 30));
        background19.setLayout(new java.awt.BorderLayout());

        MONDAY2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background19.add(MONDAY2, java.awt.BorderLayout.CENTER);

        background20.setBackground(new java.awt.Color(0, 51, 51));
        background20.setPreferredSize(new java.awt.Dimension(195, 30));
        background20.setLayout(new java.awt.BorderLayout());

        MONDAY3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background20.add(MONDAY3, java.awt.BorderLayout.CENTER);

        background21.setBackground(new java.awt.Color(0, 51, 51));
        background21.setPreferredSize(new java.awt.Dimension(195, 30));
        background21.setLayout(new java.awt.BorderLayout());

        MONDAY4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background21.add(MONDAY4, java.awt.BorderLayout.CENTER);

        background44.setBackground(new java.awt.Color(0, 51, 51));
        background44.setPreferredSize(new java.awt.Dimension(195, 30));
        background44.setLayout(new java.awt.BorderLayout());

        MONDAY5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background44.add(MONDAY5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background18, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background20, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background44, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background8.setBackground(new java.awt.Color(0, 0, 51));
        background8.setPreferredSize(new java.awt.Dimension(195, 30));

        background14.setBackground(new java.awt.Color(0, 51, 51));
        background14.setPreferredSize(new java.awt.Dimension(195, 30));
        background14.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TUESDAY");
        background14.add(jLabel2, java.awt.BorderLayout.CENTER);

        background23.setBackground(new java.awt.Color(0, 51, 51));
        background23.setPreferredSize(new java.awt.Dimension(195, 30));
        background23.setLayout(new java.awt.BorderLayout());

        TUESDAY1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background23.add(TUESDAY1, java.awt.BorderLayout.CENTER);

        background24.setBackground(new java.awt.Color(0, 51, 51));
        background24.setPreferredSize(new java.awt.Dimension(195, 30));
        background24.setLayout(new java.awt.BorderLayout());

        TUESDAY2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background24.add(TUESDAY2, java.awt.BorderLayout.CENTER);

        background25.setBackground(new java.awt.Color(0, 51, 51));
        background25.setPreferredSize(new java.awt.Dimension(195, 30));
        background25.setLayout(new java.awt.BorderLayout());

        TUESDAY3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background25.add(TUESDAY3, java.awt.BorderLayout.CENTER);

        background26.setBackground(new java.awt.Color(0, 51, 51));
        background26.setPreferredSize(new java.awt.Dimension(195, 30));
        background26.setLayout(new java.awt.BorderLayout());

        TUESDAY4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background26.add(TUESDAY4, java.awt.BorderLayout.CENTER);

        background27.setBackground(new java.awt.Color(0, 51, 51));
        background27.setPreferredSize(new java.awt.Dimension(195, 30));
        background27.setLayout(new java.awt.BorderLayout());

        TUESDAY5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background27.add(TUESDAY5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background8Layout = new javax.swing.GroupLayout(background8);
        background8.setLayout(background8Layout);
        background8Layout.setHorizontalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background23, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background25, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background27, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background8Layout.setVerticalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background10.setBackground(new java.awt.Color(0, 0, 51));
        background10.setPreferredSize(new java.awt.Dimension(195, 30));

        background15.setBackground(new java.awt.Color(0, 51, 51));
        background15.setPreferredSize(new java.awt.Dimension(195, 30));
        background15.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("WEDNESDAY");
        background15.add(jLabel4, java.awt.BorderLayout.CENTER);

        background28.setBackground(new java.awt.Color(0, 51, 51));
        background28.setPreferredSize(new java.awt.Dimension(195, 30));
        background28.setLayout(new java.awt.BorderLayout());

        WEDNESDAY1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background28.add(WEDNESDAY1, java.awt.BorderLayout.CENTER);

        background29.setBackground(new java.awt.Color(0, 51, 51));
        background29.setPreferredSize(new java.awt.Dimension(195, 30));
        background29.setLayout(new java.awt.BorderLayout());

        WEDNESDAY2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background29.add(WEDNESDAY2, java.awt.BorderLayout.CENTER);

        background30.setBackground(new java.awt.Color(0, 51, 51));
        background30.setPreferredSize(new java.awt.Dimension(195, 30));
        background30.setLayout(new java.awt.BorderLayout());

        WEDNESDAY3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background30.add(WEDNESDAY3, java.awt.BorderLayout.CENTER);

        background31.setBackground(new java.awt.Color(0, 51, 51));
        background31.setPreferredSize(new java.awt.Dimension(195, 30));
        background31.setLayout(new java.awt.BorderLayout());

        WEDNESDAY4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background31.add(WEDNESDAY4, java.awt.BorderLayout.CENTER);

        background32.setBackground(new java.awt.Color(0, 51, 51));
        background32.setPreferredSize(new java.awt.Dimension(195, 30));
        background32.setLayout(new java.awt.BorderLayout());

        WEDNESDAY5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background32.add(WEDNESDAY5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background10Layout = new javax.swing.GroupLayout(background10);
        background10.setLayout(background10Layout);
        background10Layout.setHorizontalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background28, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background30, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background32, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background10Layout.setVerticalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background11.setBackground(new java.awt.Color(0, 0, 51));
        background11.setPreferredSize(new java.awt.Dimension(195, 30));

        background17.setBackground(new java.awt.Color(0, 51, 51));
        background17.setPreferredSize(new java.awt.Dimension(195, 30));
        background17.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FRIDAY");
        background17.add(jLabel6, java.awt.BorderLayout.CENTER);

        background38.setBackground(new java.awt.Color(0, 51, 51));
        background38.setPreferredSize(new java.awt.Dimension(195, 30));
        background38.setLayout(new java.awt.BorderLayout());

        FRIDAY1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background38.add(FRIDAY1, java.awt.BorderLayout.CENTER);

        background39.setBackground(new java.awt.Color(0, 51, 51));
        background39.setPreferredSize(new java.awt.Dimension(195, 30));
        background39.setLayout(new java.awt.BorderLayout());

        FRIDAY2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background39.add(FRIDAY2, java.awt.BorderLayout.CENTER);

        background40.setBackground(new java.awt.Color(0, 51, 51));
        background40.setPreferredSize(new java.awt.Dimension(195, 30));
        background40.setLayout(new java.awt.BorderLayout());

        FRIDAY3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background40.add(FRIDAY3, java.awt.BorderLayout.CENTER);

        background41.setBackground(new java.awt.Color(0, 51, 51));
        background41.setPreferredSize(new java.awt.Dimension(195, 30));
        background41.setLayout(new java.awt.BorderLayout());

        FRIDAY4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background41.add(FRIDAY4, java.awt.BorderLayout.CENTER);

        background42.setBackground(new java.awt.Color(0, 51, 51));
        background42.setPreferredSize(new java.awt.Dimension(195, 30));
        background42.setLayout(new java.awt.BorderLayout());

        FRIDAY5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background42.add(FRIDAY5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background11Layout = new javax.swing.GroupLayout(background11);
        background11.setLayout(background11Layout);
        background11Layout.setHorizontalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background38, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background40, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background42, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background11Layout.setVerticalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background12.setBackground(new java.awt.Color(0, 0, 51));
        background12.setPreferredSize(new java.awt.Dimension(195, 30));

        background16.setBackground(new java.awt.Color(0, 51, 51));
        background16.setPreferredSize(new java.awt.Dimension(195, 30));
        background16.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("THURSDAY");
        background16.add(jLabel5, java.awt.BorderLayout.CENTER);

        background33.setBackground(new java.awt.Color(0, 51, 51));
        background33.setPreferredSize(new java.awt.Dimension(195, 30));
        background33.setLayout(new java.awt.BorderLayout());

        THURSDAY1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background33.add(THURSDAY1, java.awt.BorderLayout.CENTER);

        background34.setBackground(new java.awt.Color(0, 51, 51));
        background34.setPreferredSize(new java.awt.Dimension(195, 30));
        background34.setLayout(new java.awt.BorderLayout());

        THURSDAY2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background34.add(THURSDAY2, java.awt.BorderLayout.CENTER);

        background35.setBackground(new java.awt.Color(0, 51, 51));
        background35.setPreferredSize(new java.awt.Dimension(195, 30));
        background35.setLayout(new java.awt.BorderLayout());

        THURSDAY3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background35.add(THURSDAY3, java.awt.BorderLayout.CENTER);

        background36.setBackground(new java.awt.Color(0, 51, 51));
        background36.setPreferredSize(new java.awt.Dimension(195, 30));
        background36.setLayout(new java.awt.BorderLayout());

        THURSDAY4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background36.add(THURSDAY4, java.awt.BorderLayout.CENTER);

        background37.setBackground(new java.awt.Color(0, 51, 51));
        background37.setPreferredSize(new java.awt.Dimension(195, 30));
        background37.setLayout(new java.awt.BorderLayout());

        THURSDAY5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background37.add(THURSDAY5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background12Layout = new javax.swing.GroupLayout(background12);
        background12.setLayout(background12Layout);
        background12Layout.setHorizontalGroup(
            background12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background33, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background35, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background37, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background12Layout.setVerticalGroup(
            background12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(background33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background43.setBackground(new java.awt.Color(0, 0, 51));

        jLabel49.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("I   N   T   E   R   V   A   L                           I   N   T   E   R   V   A   L                         I   N   T   E   R   V   A   L ");

        javax.swing.GroupLayout background43Layout = new javax.swing.GroupLayout(background43);
        background43.setLayout(background43Layout);
        background43Layout.setHorizontalGroup(
            background43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 1032, Short.MAX_VALUE)
                .addContainerGap())
        );
        background43Layout.setVerticalGroup(
            background43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        background45.setBackground(new java.awt.Color(0, 0, 51));

        background22.setBackground(new java.awt.Color(0, 51, 51));
        background22.setPreferredSize(new java.awt.Dimension(195, 30));
        background22.setLayout(new java.awt.BorderLayout());

        MONDAY8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background22.add(MONDAY8, java.awt.BorderLayout.CENTER);

        background46.setBackground(new java.awt.Color(0, 51, 51));
        background46.setPreferredSize(new java.awt.Dimension(195, 30));
        background46.setLayout(new java.awt.BorderLayout());

        MONDAY6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background46.add(MONDAY6, java.awt.BorderLayout.CENTER);

        background47.setBackground(new java.awt.Color(0, 51, 51));
        background47.setPreferredSize(new java.awt.Dimension(195, 30));
        background47.setLayout(new java.awt.BorderLayout());

        MONDAY7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        MONDAY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background47.add(MONDAY7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background45Layout = new javax.swing.GroupLayout(background45);
        background45.setLayout(background45Layout);
        background45Layout.setHorizontalGroup(
            background45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background46, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background22, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background47, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background45Layout.setVerticalGroup(
            background45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background48.setBackground(new java.awt.Color(0, 0, 51));

        background49.setBackground(new java.awt.Color(0, 51, 51));
        background49.setPreferredSize(new java.awt.Dimension(195, 30));
        background49.setLayout(new java.awt.BorderLayout());

        TUESDAY8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background49.add(TUESDAY8, java.awt.BorderLayout.CENTER);

        background50.setBackground(new java.awt.Color(0, 51, 51));
        background50.setPreferredSize(new java.awt.Dimension(195, 30));
        background50.setLayout(new java.awt.BorderLayout());

        TUESDAY6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background50.add(TUESDAY6, java.awt.BorderLayout.CENTER);

        background51.setBackground(new java.awt.Color(0, 51, 51));
        background51.setPreferredSize(new java.awt.Dimension(195, 30));
        background51.setLayout(new java.awt.BorderLayout());

        TUESDAY7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        TUESDAY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background51.add(TUESDAY7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background48Layout = new javax.swing.GroupLayout(background48);
        background48.setLayout(background48Layout);
        background48Layout.setHorizontalGroup(
            background48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background50, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background49, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background51, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background48Layout.setVerticalGroup(
            background48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background52.setBackground(new java.awt.Color(0, 0, 51));

        background53.setBackground(new java.awt.Color(0, 51, 51));
        background53.setPreferredSize(new java.awt.Dimension(195, 30));
        background53.setLayout(new java.awt.BorderLayout());

        WEDNESDAY8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background53.add(WEDNESDAY8, java.awt.BorderLayout.CENTER);

        background54.setBackground(new java.awt.Color(0, 51, 51));
        background54.setPreferredSize(new java.awt.Dimension(195, 30));
        background54.setLayout(new java.awt.BorderLayout());

        WEDNESDAY6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background54.add(WEDNESDAY6, java.awt.BorderLayout.CENTER);

        background55.setBackground(new java.awt.Color(0, 51, 51));
        background55.setPreferredSize(new java.awt.Dimension(195, 30));
        background55.setLayout(new java.awt.BorderLayout());

        WEDNESDAY7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        WEDNESDAY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background55.add(WEDNESDAY7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background52Layout = new javax.swing.GroupLayout(background52);
        background52.setLayout(background52Layout);
        background52Layout.setHorizontalGroup(
            background52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background54, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background53, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background55, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background52Layout.setVerticalGroup(
            background52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background56.setBackground(new java.awt.Color(0, 0, 51));

        background57.setBackground(new java.awt.Color(0, 51, 51));
        background57.setPreferredSize(new java.awt.Dimension(195, 30));
        background57.setLayout(new java.awt.BorderLayout());

        THURSDAY8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background57.add(THURSDAY8, java.awt.BorderLayout.CENTER);

        background58.setBackground(new java.awt.Color(0, 51, 51));
        background58.setPreferredSize(new java.awt.Dimension(195, 30));
        background58.setLayout(new java.awt.BorderLayout());

        THURSDAY6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background58.add(THURSDAY6, java.awt.BorderLayout.CENTER);

        background59.setBackground(new java.awt.Color(0, 51, 51));
        background59.setPreferredSize(new java.awt.Dimension(195, 30));
        background59.setLayout(new java.awt.BorderLayout());

        THURSDAY7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        THURSDAY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background59.add(THURSDAY7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background56Layout = new javax.swing.GroupLayout(background56);
        background56.setLayout(background56Layout);
        background56Layout.setHorizontalGroup(
            background56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background58, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background57, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background59, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background56Layout.setVerticalGroup(
            background56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background56Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        background70.setBackground(new java.awt.Color(0, 0, 51));
        background70.setPreferredSize(new java.awt.Dimension(195, 30));

        background71.setBackground(new java.awt.Color(0, 51, 51));
        background71.setPreferredSize(new java.awt.Dimension(195, 30));
        background71.setLayout(new java.awt.BorderLayout());

        FRIDAY7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background71.add(FRIDAY7, java.awt.BorderLayout.CENTER);

        background72.setBackground(new java.awt.Color(0, 51, 51));
        background72.setPreferredSize(new java.awt.Dimension(195, 30));
        background72.setLayout(new java.awt.BorderLayout());

        FRIDAY6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background72.add(FRIDAY6, java.awt.BorderLayout.CENTER);

        background73.setBackground(new java.awt.Color(0, 51, 51));
        background73.setPreferredSize(new java.awt.Dimension(195, 30));
        background73.setLayout(new java.awt.BorderLayout());

        FRIDAY8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        FRIDAY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background73.add(FRIDAY8, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout background70Layout = new javax.swing.GroupLayout(background70);
        background70.setLayout(background70Layout);
        background70Layout.setHorizontalGroup(
            background70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background72, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background71, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(background73, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        background70Layout.setVerticalGroup(
            background70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background70Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(background72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background4Layout.createSequentialGroup()
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(background45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(background48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(background8, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(background52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(background10, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(background12, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(background56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(background11, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(background70, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
                .addContainerGap())
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(background12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background11, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background70, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        background74.setPreferredSize(new java.awt.Dimension(195, 30));

        buttonGradient7.setText("Add New Time Table");
        buttonGradient7.setColor1(new java.awt.Color(0, 0, 255));
        buttonGradient7.setColor2(new java.awt.Color(139, 139, 252));
        buttonGradient7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonGradient7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background74Layout = new javax.swing.GroupLayout(background74);
        background74.setLayout(background74Layout);
        background74Layout.setHorizontalGroup(
            background74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background74Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonGradient7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        background74Layout.setVerticalGroup(
            background74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background74Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(background74, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
                .addContainerGap())
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background74, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        add(background1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        if (jComboBox8.isEnabled()) {
            loadTable(true, jComboBox8.getSelectedItem().toString());
            if (jComboBox8.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Please Select Filtering Conditions", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            if (jComboBox6.isEnabled()) {
                loadTable(false, jComboBox6.getSelectedItem().toString());
                if (jComboBox6.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Please Select Teacher", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Select Filtering Condition", "Warning", JOptionPane.WARNING_MESSAGE);
                clear();
            }
        }
    }//GEN-LAST:event_buttonGradient6ActionPerformed

    private void buttonGradient7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonGradient7ActionPerformed

    private void jComboBox8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox8MouseClicked
        jComboBox6.setEnabled(false);
        jComboBox6.setSelectedIndex(0);
        jComboBox8.setEnabled(true);
    }//GEN-LAST:event_jComboBox8MouseClicked

    private void jComboBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseClicked
        jComboBox6.setEnabled(true);
        jComboBox8.setEnabled(false);
        jComboBox8.setSelectedIndex(0);
    }//GEN-LAST:event_jComboBox6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FRIDAY1;
    private javax.swing.JLabel FRIDAY2;
    private javax.swing.JLabel FRIDAY3;
    private javax.swing.JLabel FRIDAY4;
    private javax.swing.JLabel FRIDAY5;
    private javax.swing.JLabel FRIDAY6;
    private javax.swing.JLabel FRIDAY7;
    private javax.swing.JLabel FRIDAY8;
    private javax.swing.JLabel MONDAY1;
    private javax.swing.JLabel MONDAY2;
    private javax.swing.JLabel MONDAY3;
    private javax.swing.JLabel MONDAY4;
    private javax.swing.JLabel MONDAY5;
    private javax.swing.JLabel MONDAY6;
    private javax.swing.JLabel MONDAY7;
    private javax.swing.JLabel MONDAY8;
    private javax.swing.JLabel THURSDAY1;
    private javax.swing.JLabel THURSDAY2;
    private javax.swing.JLabel THURSDAY3;
    private javax.swing.JLabel THURSDAY4;
    private javax.swing.JLabel THURSDAY5;
    private javax.swing.JLabel THURSDAY6;
    private javax.swing.JLabel THURSDAY7;
    private javax.swing.JLabel THURSDAY8;
    private javax.swing.JLabel TUESDAY1;
    private javax.swing.JLabel TUESDAY2;
    private javax.swing.JLabel TUESDAY3;
    private javax.swing.JLabel TUESDAY4;
    private javax.swing.JLabel TUESDAY5;
    private javax.swing.JLabel TUESDAY6;
    private javax.swing.JLabel TUESDAY7;
    private javax.swing.JLabel TUESDAY8;
    private javax.swing.JLabel WEDNESDAY1;
    private javax.swing.JLabel WEDNESDAY2;
    private javax.swing.JLabel WEDNESDAY3;
    private javax.swing.JLabel WEDNESDAY4;
    private javax.swing.JLabel WEDNESDAY5;
    private javax.swing.JLabel WEDNESDAY6;
    private javax.swing.JLabel WEDNESDAY7;
    private javax.swing.JLabel WEDNESDAY8;
    private SMMV.Component.Background background1;
    private SMMV.Component.Background background10;
    private SMMV.Component.Background background11;
    private SMMV.Component.Background background12;
    private SMMV.Component.Background background13;
    private SMMV.Component.Background background14;
    private SMMV.Component.Background background15;
    private SMMV.Component.Background background16;
    private SMMV.Component.Background background17;
    private SMMV.Component.Background background18;
    private SMMV.Component.Background background19;
    private SMMV.Component.Background background2;
    private SMMV.Component.Background background20;
    private SMMV.Component.Background background21;
    private SMMV.Component.Background background22;
    private SMMV.Component.Background background23;
    private SMMV.Component.Background background24;
    private SMMV.Component.Background background25;
    private SMMV.Component.Background background26;
    private SMMV.Component.Background background27;
    private SMMV.Component.Background background28;
    private SMMV.Component.Background background29;
    private SMMV.Component.Background background3;
    private SMMV.Component.Background background30;
    private SMMV.Component.Background background31;
    private SMMV.Component.Background background32;
    private SMMV.Component.Background background33;
    private SMMV.Component.Background background34;
    private SMMV.Component.Background background35;
    private SMMV.Component.Background background36;
    private SMMV.Component.Background background37;
    private SMMV.Component.Background background38;
    private SMMV.Component.Background background39;
    private SMMV.Component.Background background4;
    private SMMV.Component.Background background40;
    private SMMV.Component.Background background41;
    private SMMV.Component.Background background42;
    private SMMV.Component.Background background43;
    private SMMV.Component.Background background44;
    private SMMV.Component.Background background45;
    private SMMV.Component.Background background46;
    private SMMV.Component.Background background47;
    private SMMV.Component.Background background48;
    private SMMV.Component.Background background49;
    private SMMV.Component.Background background5;
    private SMMV.Component.Background background50;
    private SMMV.Component.Background background51;
    private SMMV.Component.Background background52;
    private SMMV.Component.Background background53;
    private SMMV.Component.Background background54;
    private SMMV.Component.Background background55;
    private SMMV.Component.Background background56;
    private SMMV.Component.Background background57;
    private SMMV.Component.Background background58;
    private SMMV.Component.Background background59;
    private SMMV.Component.Background background6;
    private SMMV.Component.Background background60;
    private SMMV.Component.Background background61;
    private SMMV.Component.Background background62;
    private SMMV.Component.Background background63;
    private SMMV.Component.Background background64;
    private SMMV.Component.Background background65;
    private SMMV.Component.Background background66;
    private SMMV.Component.Background background67;
    private SMMV.Component.Background background68;
    private SMMV.Component.Background background69;
    private SMMV.Component.Background background7;
    private SMMV.Component.Background background70;
    private SMMV.Component.Background background71;
    private SMMV.Component.Background background72;
    private SMMV.Component.Background background73;
    private SMMV.Component.Background background74;
    private SMMV.Component.Background background8;
    private SMMV.Component.Background background9;
    private SMMV.Component.ButtonGradient buttonGradient6;
    private SMMV.Component.ButtonGradient buttonGradient7;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private com.raven.swing.TimePicker timePicker1;
    private com.raven.swing.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables
}
