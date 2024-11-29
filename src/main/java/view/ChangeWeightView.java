package view;

import java.awt.Component;
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
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.change_weight.ChangeWeightController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user want to change weight.
 */
public class ChangeWeightView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangeWeightController changeWeightController;
    private LogoutController logoutController;

    private JLabel username;

    private JButton logOut;

    private final JTextField weightInputField = new JTextField(15);
    private JButton changeWeight;

    public ChangeWeightView(LoggedInViewModel loggedInViewModel, JLabel username,
                           JButton logOut, JButton changeWeight) {
        this.loggedInViewModel = loggedInViewModel;
        this.username = username;
        this.logOut = logOut;
        this.changeWeight = changeWeight;
    }

    public void LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("New Weight"), weightInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changeWeight = new JButton("Change Weight");
        buttons.add(changeWeight);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        weightInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setWeight(Float.parseFloat(weightInputField.getText()));
                loggedInViewModel.setState(currentState);
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

        JButton finalChangeWeight = changeWeight;
        changeWeight.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(finalChangeWeight)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changeWeightController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getHeight(),
                                currentState.getWeight(),
                                currentState.getGender(),
                                currentState.getAge()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // execute the logout use case through the Controller
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

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
        else if (evt.getPropertyName().equals("weight")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "weight updated for " + state.getUsername());
        }

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
}
