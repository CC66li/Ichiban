package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.*;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user want to change password.
 */
public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private final String viewName = "change password";

    private final ChangePasswordViewModel changePasswordViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private final ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private JButton confirm;
    private JButton cancel;
    private final JLabel username;

    private final JPasswordField passwordInputField = new JPasswordField(15);

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel,
                              ChangePasswordController changePasswordController) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordController = changePasswordController;
        this.changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel title = new JLabel("Change Password");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("New Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        confirm = new JButton("Confirm");
        buttons.add(confirm);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        confirm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            final ChangePasswordState currentState = ChangePasswordView.this.changePasswordViewModel.getState();

                            ChangePasswordView.this.changePasswordController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getHeight(),
                                    currentState.getWeight(),
                                    currentState.getGender(),
                                    currentState.getAge(),
                                    currentState.getMealType(),
                                    currentState.getCuisineType(),
                                    currentState.getAllergy(),
                                    currentState.getIngredient()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                evt -> {
                    if (evt.getSource().equals(cancel)) {
                        // Call switchToLoginView only if logoutController is set
                        if (this.logoutController != null) {
                            this.logoutController.switchToLoginView();
                        }
                        else {
                            System.out.println("LogoutController is not initialized.");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ChangePasswordState currentState = ChangePasswordView.this.changePasswordViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                ChangePasswordView.this.changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePasswordListener();

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        // save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

    private void changePasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setUsername(passwordInputField.getText());
                changePasswordViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }
}
