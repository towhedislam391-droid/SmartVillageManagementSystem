
package com.smartvillage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class DashboardFrame extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    private JLabel residentCountLabel;
    private JLabel reportCountLabel;
    private JLabel jobCountLabel;

    private JTable residentTable;
    private DefaultTableModel residentModel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField villageField;
    private JTextField phoneField;
    private JTextField searchField;
    private JComboBox<String> genderBox;

    private JTable reportTable;
    private DefaultTableModel reportModel;

    private JTextField reportIdField;
    private JTextField reportResidentField;
    private JTextField reportLocationField;
    private JTextArea reportDescriptionArea;
    private JComboBox<String> reportCategoryBox;
    private JComboBox<String> reportStatusBox;

    private JTable jobTable;
    private DefaultTableModel jobModel;

    private JTextField jobIdField;
    private JTextField jobTitleField;
    private JTextField organizationField;
    private JTextField jobLocationField;
    private JTextField salaryField;
    private JTextArea jobDescriptionArea;

    public DashboardFrame() {

        setTitle("Smart Village Management System");
        setSize(1150, 750);
        setMinimumSize(new Dimension(950, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 75, 52));
        header.setPreferredSize(new Dimension(1150, 75));

        JLabel title = new JLabel("  SMART VILLAGE MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);

        JLabel slogan = new JLabel("Digital Village, Better Tomorrow   ");
        slogan.setForeground(Color.WHITE);
        slogan.setFont(new Font("Arial", Font.ITALIC, 15));

        header.add(title, BorderLayout.WEST);
        header.add(slogan, BorderLayout.EAST);

        // SIDEBAR
        JPanel sidebar = new JPanel(new GridLayout(4, 1, 5, 12));
        sidebar.setBackground(new Color(0, 65, 50));
        sidebar.setPreferredSize(new Dimension(225, 600));
        sidebar.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10)
        );

        String[] menus = {
            "Dashboard",
            "Residents",
            "Problem Reports",
            "Jobs Management"
        };

        // MAIN PANEL
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createDashboard(), "Dashboard");
        mainPanel.add(createResidentPanel(), "Residents");
        mainPanel.add(createProblemPanel(), "Problem Reports");
        mainPanel.add(createJobPanel(), "Jobs Management");

        for (String menu : menus) {

            JButton button = new JButton(menu);
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.setBackground(new Color(30, 110, 85));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            button.addActionListener(e -> {
                cardLayout.show(mainPanel, menu);
                refreshDashboardCounts();
            });

            sidebar.add(button);
        }

        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        refreshDashboardCounts();
    }

    // ================= DASHBOARD =================

    private JPanel createDashboard() {

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(new Color(240, 248, 244));
        panel.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        JLabel welcome = new JLabel("Welcome to Smart Village!");
        welcome.setFont(new Font("Arial", Font.BOLD, 27));
        welcome.setForeground(new Color(0, 90, 60));

        JLabel subtitle = new JLabel(
                "Manage village information and services easily."
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        topPanel.setOpaque(false);
        topPanel.add(welcome);
        topPanel.add(subtitle);

        JPanel cards = new JPanel(new GridLayout(1, 3, 20, 20));
        cards.setOpaque(false);

        residentCountLabel = new JLabel("0");
        reportCountLabel = new JLabel("0");
        jobCountLabel = new JLabel("0");

        cards.add(createCountCard("Total Residents", residentCountLabel));
        cards.add(createCountCard("Problem Reports", reportCountLabel));
        cards.add(createCountCard("Available Jobs", jobCountLabel));

        JPanel center = new JPanel(new BorderLayout(15, 20));
        center.setOpaque(false);

        JLabel villageTitle = new JLabel("Village Overview");
        villageTitle.setFont(new Font("Arial", Font.BOLD, 21));

        JLabel villageInfo = new JLabel(
                "<html><div style='text-align:center;'>"
                + "<h2>Smart Village</h2>"
                + "<p>Digital services for a better village life.</p>"
                + "<p>Resident Management | Problem Reports | Job Management</p>"
                + "</div></html>",
                SwingConstants.CENTER
        );

        villageInfo.setOpaque(true);
        villageInfo.setBackground(new Color(220, 240, 228));
        villageInfo.setBorder(
                BorderFactory.createLineBorder(
                        new Color(150, 190, 165)
                )
        );

        center.add(villageTitle, BorderLayout.NORTH);
        center.add(villageInfo, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(cards, BorderLayout.CENTER);
        panel.add(center, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCountCard(String title, JLabel countLabel) {

        JPanel card = new JPanel(new GridLayout(2, 1, 5, 5));
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(190, 215, 200)
                ),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        countLabel.setFont(new Font("Arial", Font.BOLD, 30));
        countLabel.setForeground(new Color(0, 120, 75));
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLabel);
        card.add(countLabel);

        return card;
    }

    private void refreshDashboardCounts() {

        if (residentCountLabel != null && residentModel != null) {
            residentCountLabel.setText(
                    String.valueOf(residentModel.getRowCount())
            );
        }

        if (reportCountLabel != null && reportModel != null) {
            reportCountLabel.setText(
                    String.valueOf(reportModel.getRowCount())
            );
        }

        if (jobCountLabel != null && jobModel != null) {
            jobCountLabel.setText(
                    String.valueOf(jobModel.getRowCount())
            );
        }
    }

    // ================= FILE HANDLING =================

    private void loadFile(DefaultTableModel model, String fileName) {

        try {
            FileService.loadData(model, fileName);

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load file: " + fileName
                    + "\n" + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean saveFile(DefaultTableModel model, String fileName) {

        try {
            FileService.saveData(model, fileName);
            return true;

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save file: " + fileName
                    + "\n" + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }
    }

    private boolean isDuplicateID(
            DefaultTableModel model, String id, int selectedRow
    ) {

        for (int i = 0; i < model.getRowCount(); i++) {

            if (i != selectedRow
                    && model.getValueAt(i, 0).toString().equals(id)) {
                return true;
            }
        }

        return false;
    }

    // ================= RESIDENT MANAGEMENT =================

    private JPanel createResidentPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(240, 248, 244));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel heading = new JLabel("Resident Management");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setForeground(new Color(0, 90, 60));

        JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
        form.setBorder(
                BorderFactory.createTitledBorder("Resident Information")
        );

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        villageField = new JTextField();
        phoneField = new JTextField();

        genderBox = new JComboBox<>(
                new String[]{"Male", "Female", "Other"}
        );

        form.add(new JLabel("Resident ID:"));
        form.add(idField);
        form.add(new JLabel("Name:"));
        form.add(nameField);

        form.add(new JLabel("Age:"));
        form.add(ageField);
        form.add(new JLabel("Gender:"));
        form.add(genderBox);

        form.add(new JLabel("Village:"));
        form.add(villageField);
        form.add(new JLabel("Phone:"));
        form.add(phoneField);

        residentModel = new DefaultTableModel(
                new String[]{
                    "ID", "Name", "Age", "Gender", "Village", "Phone"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        residentTable = new JTable(residentModel);
        residentTable.setRowHeight(25);

        residentTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && residentTable.getSelectedRow() != -1) {

                int row = residentTable.getSelectedRow();

                idField.setText(residentModel.getValueAt(row, 0).toString());
                nameField.setText(residentModel.getValueAt(row, 1).toString());
                ageField.setText(residentModel.getValueAt(row, 2).toString());
                genderBox.setSelectedItem(residentModel.getValueAt(row, 3).toString());
                villageField.setText(residentModel.getValueAt(row, 4).toString());
                phoneField.setText(residentModel.getValueAt(row, 5).toString());
            }
        });

        JScrollPane tableScroll = new JScrollPane(residentTable);

        JButton addButton = new JButton("Add Resident");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        addButton.addActionListener(e -> addResident());
        updateButton.addActionListener(e -> updateResident());
        deleteButton.addActionListener(e -> deleteResident());
        clearButton.addActionListener(e -> clearResidentFields());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);

        searchField = new JTextField(18);

        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");

        searchButton.addActionListener(e -> searchResidents());
        showAllButton.addActionListener(e -> loadResidents());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(buttons, BorderLayout.WEST);
        bottom.add(searchPanel, BorderLayout.EAST);

        JPanel top = new JPanel(new BorderLayout(5, 10));
        top.setOpaque(false);
        top.add(heading, BorderLayout.NORTH);
        top.add(form, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        loadResidents();

        return panel;
    }

    private void addResident() {

        try {

            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderBox.getSelectedItem().toString();
            String village = villageField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || village.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be greater than zero.");
                return;
            }

            if (isDuplicateID(residentModel, String.valueOf(id), -1)) {
                JOptionPane.showMessageDialog(this, "This Resident ID already exists.");
                return;
            }

            residentModel.addRow(new Object[]{
                id, name, age, gender, village, phone
            });

            if (saveFile(residentModel, "data/residents.txt")) {

                loadResidents();
                refreshDashboardCounts();
                clearResidentFields();

                JOptionPane.showMessageDialog(this, "Resident added successfully!");

            } else {
                loadResidents();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "ID and Age must be valid numbers."
            );
        }
    }

    private void updateResident() {

        try {

            int id = Integer.parseInt(idField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());

            String name = nameField.getText().trim();
            String gender = genderBox.getSelectedItem().toString();
            String village = villageField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || village.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be greater than zero.");
                return;
            }

            int row = findRow(residentModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Resident not found.");
                return;
            }

            residentModel.setValueAt(name, row, 1);
            residentModel.setValueAt(age, row, 2);
            residentModel.setValueAt(gender, row, 3);
            residentModel.setValueAt(village, row, 4);
            residentModel.setValueAt(phone, row, 5);

            if (saveFile(residentModel, "data/residents.txt")) {

                loadResidents();
                clearResidentFields();

                JOptionPane.showMessageDialog(this, "Resident updated successfully!");

            } else {
                loadResidents();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "Please enter a valid ID and Age."
            );
        }
    }

    private void deleteResident() {

        try {

            int id = Integer.parseInt(idField.getText().trim());

            int row = findRow(residentModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Resident not found.");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this resident?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            residentModel.removeRow(row);

            if (saveFile(residentModel, "data/residents.txt")) {

                loadResidents();
                refreshDashboardCounts();
                clearResidentFields();

                JOptionPane.showMessageDialog(this, "Resident deleted successfully!");

            } else {
                loadResidents();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Resident ID.");
        }
    }

    private void searchResidents() {

        String search = searchField.getText().trim().toLowerCase();

        loadResidents();

        int i = 0;

        while (i < residentModel.getRowCount()) {

            String name = residentModel.getValueAt(i, 1)
                    .toString().toLowerCase();

            if (!name.contains(search)) {
                residentModel.removeRow(i);
            } else {
                i++;
            }
        }
    }

    private void loadResidents() {

        loadFile(residentModel, "data/residents.txt");
        refreshDashboardCounts();
    }

    private void clearResidentFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        villageField.setText("");
        phoneField.setText("");
        genderBox.setSelectedIndex(0);

        residentTable.clearSelection();
    }

    // ================= PROBLEM REPORT MANAGEMENT =================

    private JPanel createProblemPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(240, 248, 244));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel heading = new JLabel("Problem Reports");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setForeground(new Color(0, 90, 60));

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(
                BorderFactory.createTitledBorder("Report Information")
        );

        reportIdField = new JTextField();
        reportResidentField = new JTextField();
        reportLocationField = new JTextField();

        reportCategoryBox = new JComboBox<>(
                new String[]{
                    "Road Problem", "Water Problem",
                    "Electricity Problem", "Sanitation", "Other"
                }
        );

        reportDescriptionArea = new JTextArea(3, 20);
        reportDescriptionArea.setLineWrap(true);
        reportDescriptionArea.setWrapStyleWord(true);

        reportStatusBox = new JComboBox<>(
                new String[]{"Pending", "In Progress", "Resolved"}
        );

        form.add(new JLabel("Report ID:"));
        form.add(reportIdField);
        form.add(new JLabel("Resident Name:"));
        form.add(reportResidentField);
        form.add(new JLabel("Category:"));
        form.add(reportCategoryBox);
        form.add(new JLabel("Location:"));
        form.add(reportLocationField);

        reportModel = new DefaultTableModel(
                new String[]{
                    "Report ID", "Resident", "Category",
                    "Location", "Description", "Status"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        reportTable = new JTable(reportModel);
        reportTable.setRowHeight(25);

        reportTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && reportTable.getSelectedRow() != -1) {

                int row = reportTable.getSelectedRow();

                reportIdField.setText(reportModel.getValueAt(row, 0).toString());
                reportResidentField.setText(reportModel.getValueAt(row, 1).toString());
                reportCategoryBox.setSelectedItem(reportModel.getValueAt(row, 2).toString());
                reportLocationField.setText(reportModel.getValueAt(row, 3).toString());
                reportDescriptionArea.setText(reportModel.getValueAt(row, 4).toString());
                reportStatusBox.setSelectedItem(reportModel.getValueAt(row, 5).toString());
            }
        });

        JPanel descriptionPanel = new JPanel(new BorderLayout(5, 5));
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Description"));
        descriptionPanel.add(new JScrollPane(reportDescriptionArea), BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Report Status:"));
        statusPanel.add(reportStatusBox);

        JButton submitButton = new JButton("Submit Report");
        JButton updateButton = new JButton("Update Status");
        JButton deleteButton = new JButton("Delete Report");
        JButton clearButton = new JButton("Clear");

        submitButton.addActionListener(e -> addReport());
        updateButton.addActionListener(e -> updateReport());
        deleteButton.addActionListener(e -> deleteReport());
        clearButton.addActionListener(e -> clearReportFields());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(submitButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);

        JPanel top = new JPanel(new BorderLayout(5, 10));
        top.setOpaque(false);
        top.add(heading, BorderLayout.NORTH);
        top.add(form, BorderLayout.CENTER);
        top.add(descriptionPanel, BorderLayout.SOUTH);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(statusPanel, BorderLayout.NORTH);
        bottom.add(buttons, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(reportTable), BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        loadReports();

        return panel;
    }

    private void addReport() {

        try {

            int id = Integer.parseInt(reportIdField.getText().trim());

            String residentName = reportResidentField.getText().trim();
            String category = reportCategoryBox.getSelectedItem().toString();
            String location = reportLocationField.getText().trim();
            String description = reportDescriptionArea.getText().trim();
            String status = reportStatusBox.getSelectedItem().toString();

            if (residentName.isEmpty() || location.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            if (isDuplicateID(reportModel, String.valueOf(id), -1)) {
                JOptionPane.showMessageDialog(this, "This Report ID already exists.");
                return;
            }

            reportModel.addRow(new Object[]{
                id, residentName, category, location, description, status
            });

            if (saveFile(reportModel, "data/reports.txt")) {

                loadReports();
                refreshDashboardCounts();
                clearReportFields();

                JOptionPane.showMessageDialog(this, "Problem report submitted successfully!");

            } else {
                loadReports();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Report ID.");
        }
    }

    private void updateReport() {

        try {

            int id = Integer.parseInt(reportIdField.getText().trim());

            int row = findRow(reportModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Report not found.");
                return;
            }

            String status = reportStatusBox.getSelectedItem().toString();

            reportModel.setValueAt(status, row, 5);

            if (saveFile(reportModel, "data/reports.txt")) {

                loadReports();
                clearReportFields();

                JOptionPane.showMessageDialog(this, "Report status updated successfully!");

            } else {
                loadReports();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Report ID.");
        }
    }

    private void deleteReport() {

        try {

            int id = Integer.parseInt(reportIdField.getText().trim());

            int row = findRow(reportModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Report not found.");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this report?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            reportModel.removeRow(row);

            if (saveFile(reportModel, "data/reports.txt")) {

                loadReports();
                refreshDashboardCounts();
                clearReportFields();

                JOptionPane.showMessageDialog(this, "Report deleted successfully!");

            } else {
                loadReports();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Report ID.");
        }
    }

    private void loadReports() {

        loadFile(reportModel, "data/reports.txt");
        refreshDashboardCounts();
    }

    private void clearReportFields() {

        reportIdField.setText("");
        reportResidentField.setText("");
        reportLocationField.setText("");
        reportDescriptionArea.setText("");

        reportCategoryBox.setSelectedIndex(0);
        reportStatusBox.setSelectedIndex(0);

        reportTable.clearSelection();
    }

    // ================= JOB MANAGEMENT =================

    private JPanel createJobPanel() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(240, 248, 244));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel heading = new JLabel("Job Management");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setForeground(new Color(0, 90, 60));

        JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Job Information"));

        jobIdField = new JTextField();
        jobTitleField = new JTextField();
        organizationField = new JTextField();
        jobLocationField = new JTextField();
        salaryField = new JTextField();

        jobDescriptionArea = new JTextArea(3, 20);
        jobDescriptionArea.setLineWrap(true);
        jobDescriptionArea.setWrapStyleWord(true);

        form.add(new JLabel("Job ID:"));
        form.add(jobIdField);
        form.add(new JLabel("Job Title:"));
        form.add(jobTitleField);
        form.add(new JLabel("Organization:"));
        form.add(organizationField);
        form.add(new JLabel("Location:"));
        form.add(jobLocationField);
        form.add(new JLabel("Salary:"));
        form.add(salaryField);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(jobDescriptionArea));

        jobModel = new DefaultTableModel(
                new String[]{
                    "Job ID", "Job Title", "Organization",
                    "Location", "Salary", "Description"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jobTable = new JTable(jobModel);
        jobTable.setRowHeight(25);

        jobTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && jobTable.getSelectedRow() != -1) {

                int row = jobTable.getSelectedRow();

                jobIdField.setText(jobModel.getValueAt(row, 0).toString());
                jobTitleField.setText(jobModel.getValueAt(row, 1).toString());
                organizationField.setText(jobModel.getValueAt(row, 2).toString());
                jobLocationField.setText(jobModel.getValueAt(row, 3).toString());
                salaryField.setText(jobModel.getValueAt(row, 4).toString());
                jobDescriptionArea.setText(jobModel.getValueAt(row, 5).toString());
            }
        });

        JButton addButton = new JButton("Add Job");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");
        JButton showAllButton = new JButton("Show All");

        addButton.addActionListener(e -> addJob());
        updateButton.addActionListener(e -> updateJob());
        deleteButton.addActionListener(e -> deleteJob());
        clearButton.addActionListener(e -> clearJobFields());
        showAllButton.addActionListener(e -> loadJobs());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);
        buttons.add(showAllButton);

        JPanel top = new JPanel(new BorderLayout(5, 10));
        top.setOpaque(false);
        top.add(heading, BorderLayout.NORTH);
        top.add(form, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(jobTable), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        loadJobs();

        return panel;
    }

    private void addJob() {

        try {

            int id = Integer.parseInt(jobIdField.getText().trim());

            String title = jobTitleField.getText().trim();
            String organization = organizationField.getText().trim();
            String location = jobLocationField.getText().trim();

            double salary = Double.parseDouble(salaryField.getText().trim());

            String description = jobDescriptionArea.getText().trim();

            if (title.isEmpty() || organization.isEmpty()
                    || location.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all job fields.");
                return;
            }

            if (salary < 0) {
                JOptionPane.showMessageDialog(this, "Salary cannot be negative.");
                return;
            }

            if (isDuplicateID(jobModel, String.valueOf(id), -1)) {
                JOptionPane.showMessageDialog(this, "This Job ID already exists.");
                return;
            }

            jobModel.addRow(new Object[]{
                id, title, organization, location, salary, description
            });

            if (saveFile(jobModel, "data/jobs.txt")) {

                loadJobs();
                refreshDashboardCounts();
                clearJobFields();

                JOptionPane.showMessageDialog(this, "Job added successfully!");

            } else {
                loadJobs();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Job ID must be an integer and Salary must be a number."
            );
        }
    }

    private void updateJob() {

        try {

            int id = Integer.parseInt(jobIdField.getText().trim());

            double salary = Double.parseDouble(salaryField.getText().trim());

            String title = jobTitleField.getText().trim();
            String organization = organizationField.getText().trim();
            String location = jobLocationField.getText().trim();
            String description = jobDescriptionArea.getText().trim();

            if (title.isEmpty() || organization.isEmpty()
                    || location.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all job fields.");
                return;
            }

            if (salary < 0) {
                JOptionPane.showMessageDialog(this, "Salary cannot be negative.");
                return;
            }

            int row = findRow(jobModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Job not found.");
                return;
            }

            jobModel.setValueAt(title, row, 1);
            jobModel.setValueAt(organization, row, 2);
            jobModel.setValueAt(location, row, 3);
            jobModel.setValueAt(salary, row, 4);
            jobModel.setValueAt(description, row, 5);

            if (saveFile(jobModel, "data/jobs.txt")) {

                loadJobs();
                clearJobFields();

                JOptionPane.showMessageDialog(this, "Job updated successfully!");

            } else {
                loadJobs();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Job ID and Salary.");
        }
    }

    private void deleteJob() {

        try {

            int id = Integer.parseInt(jobIdField.getText().trim());

            int row = findRow(jobModel, String.valueOf(id));

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Job not found.");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this job?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            jobModel.removeRow(row);

            if (saveFile(jobModel, "data/jobs.txt")) {

                loadJobs();
                refreshDashboardCounts();
                clearJobFields();

                JOptionPane.showMessageDialog(this, "Job deleted successfully!");

            } else {
                loadJobs();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Enter a valid Job ID.");
        }
    }

    private void loadJobs() {

        loadFile(jobModel, "data/jobs.txt");
        refreshDashboardCounts();
    }

    private void clearJobFields() {

        jobIdField.setText("");
        jobTitleField.setText("");
        organizationField.setText("");
        jobLocationField.setText("");
        salaryField.setText("");
        jobDescriptionArea.setText("");

        jobTable.clearSelection();
    }

    // ================= COMMON METHODS =================

    private int findRow(DefaultTableModel model, String id) {

        for (int i = 0; i < model.getRowCount(); i++) {

            if (model.getValueAt(i, 0).toString().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}
