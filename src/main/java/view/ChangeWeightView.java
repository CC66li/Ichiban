package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_weight.ChangeWeightController;
import interface_adapter.change_weight.ChangeWeightState;
import interface_adapter.change_weight.ChangeWeightViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user want to change weight.
 */
public class ChangeWeightView extends JPanel implements PropertyChangeListener {

    private final String viewName = "change weight";

    private final ChangeWeightViewModel changeWeightViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangeWeightController changeWeightController;
    private LogoutController logoutController;

    private JButton confirm;
    private JButton cancel;
    private final JLabel username;

    private final JTextField weightInputField = new JTextField(15);

    public ChangeWeightView(ChangeWeightViewModel changeWeightViewModel,
                            ChangeWeightController changeWeightController) {
        this.changeWeightViewModel = changeWeightViewModel;
        this.changeWeightController = changeWeightController;
        this.changeWeightViewModel.addPropertyChangeListener(this);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel title = new JLabel("Change Weight");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel weightInfo = new LabelTextPanel(new JLabel("New Weight"), weightInputField);

        final JPanel buttons = new JPanel();
        confirm = new JButton("Confirm");
        buttons.add(confirm);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        confirm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            final ChangeWeightState currentState = ChangeWeightView.this.changeWeightViewModel.getState();

                            ChangeWeightView.this.changeWeightController.execute(
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
                        } else {
                            System.out.println("LogoutController is not initialized.");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        weightInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final ChangeWeightState currentState = ChangeWeightView.this.changeWeightViewModel.getState();
                currentState.setPassword(weightInputField.getText());
                ChangeWeightView.this.changeWeightViewModel.setState(currentState);
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

        changeWeightListener();

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(weightInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangeWeightController(ChangeWeightController changeWeightController) {
        this.changeWeightController = changeWeightController;
    }

    public void setLogoutController(LogoutController logoutController) {
        // save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

    private void changeWeightListener() {
        weightInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangeWeightState currentState = changeWeightViewModel.getState();
                currentState.setUsername(weightInputField.getText());
                changeWeightViewModel.setState(currentState);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if (evt.getPropertyName().equals("weight")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "weight updated for " + state.getUsername());
        }
    }
}
