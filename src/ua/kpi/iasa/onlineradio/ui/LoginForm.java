package ua.kpi.iasa.onlineradio.ui;

import ua.kpi.iasa.onlineradio.facade.RadioSystemFacade;
import ua.kpi.iasa.onlineradio.models.Administrator;
import ua.kpi.iasa.onlineradio.models.User;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private final RadioSystemFacade facade;

    public LoginForm(RadioSystemFacade facade) {
        this.facade = facade;

        setTitle("Вхід в систему");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        fieldsPanel.add(new JLabel("Логін:"));
        fieldsPanel.add(usernameField);
        fieldsPanel.add(new JLabel("Пароль:"));
        fieldsPanel.add(passwordField);

        add(fieldsPanel, BorderLayout.CENTER);

        JButton loginButton = new JButton("Увійти");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Використовуємо метод фасаду замість прямого доступу до репозиторіїв
            if (facade.login(username, password)) {
                User user = facade.getCurrentUser();
                JOptionPane.showMessageDialog(this, "Вітаємо, " + user.getUsername() + "!");
                openMainForm(user);
            } else {
                showError("Невірний логін або пароль");
            }
        });
    }

    private void openMainForm(User user) {
        // Передаємо фасад далі
        if (user instanceof Administrator) {
            // Для адмін форми можна створити окремий метод у фасаді getLibrary() або переробити її теж
            // new AdminForm(...).setVisible(true);
            JOptionPane.showMessageDialog(this, "Адмін-панель поки не адаптована під фасад");
        } else {
            new PlayerForm(facade).setVisible(true);
        }
        this.dispose();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Помилка", JOptionPane.ERROR_MESSAGE);
    }
}