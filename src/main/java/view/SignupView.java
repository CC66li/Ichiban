package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
//    private final JTextField heightField = new JTextField(15);
//    private final JTextField weightField = new JTextField(15);
//    private final JTextField ageField = new JTextField(5);
    private final SpinnerNumberModel ageRange = new SpinnerNumberModel(0, 0, 150, 1);
    private final JSpinner ageField = new JSpinner(ageRange);
    private final SpinnerNumberModel weightRange = new SpinnerNumberModel(0, 0, 600, 0.1);
    private final JSpinner weightField = new JSpinner(weightRange);
    private final SpinnerNumberModel heightRange = new SpinnerNumberModel(0, 0, 300, 0.1);
    private final JSpinner heightField = new JSpinner(heightRange);
    private SignupController signupController;
    private final JRadioButton maleButton = new JRadioButton("Male");
    private final JRadioButton femaleButton = new JRadioButton("Female");
    private final ButtonGroup genderGroup = new ButtonGroup();

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        final LabelTextPanel heightInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.HEIGHT_LABEL), heightField);
        final LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.WEIGHT_LABEL), weightField);
//        final LabelTextPanel genderInfo = new LabelTextPanel(
//                new JLabel(SignupViewModel.GENDER_LABEL), genderField);
        final LabelTextPanel ageInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.AGE_LABEL), ageField);

        final JPanel genderInfo = new JPanel();
        genderInfo.setLayout(new BoxLayout(genderInfo, BoxLayout.Y_AXIS));
        genderInfo.add(new JLabel(SignupViewModel.GENDER_LABEL));
        genderInfo.add(maleButton);
        genderInfo.add(femaleButton);
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Add ActionListeners to gender buttons
        maleButton.addActionListener(e -> updateGender("Male"));
        femaleButton.addActionListener(e -> updateGender("Female"));


        final JPanel buttons = new JPanel();
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getHeight(),
                                    currentState.getWeight(),
                                    currentState.getGender(),
                                    currentState.getAge()
                                    );
                        }
                    }
                }
        );

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        signupController.switchToLoginView();
                    }
                }
        );

        cancel.addActionListener(this);

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();
        addHeightListener();
        addWeightListener();
        addGenderListener();
        addAgeListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(heightInfo);
        this.add(weightInfo);
        this.add(genderInfo);
        this.add(ageInfo);
        this.add(buttons);
    }

    private void updateGender(String gender) {
        final SignupState currentState = signupViewModel.getState();
        currentState.setGender(gender);
        signupViewModel.setState(currentState);
    }

    private void addGenderListener() {
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Add ActionListener to update gender in the SignupState
        ActionListener genderListener = e -> {
            final SignupState currentState = signupViewModel.getState();
            if (maleButton.isSelected()) {
                currentState.setGender("Male");
            } else if (femaleButton.isSelected()) {
                currentState.setGender("Female");
            }
            signupViewModel.setState(currentState);
        };

        maleButton.addActionListener(genderListener);
        femaleButton.addActionListener(genderListener);
    }

    private void addAgeListener() {
//        ageField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final SignupState currentState = signupViewModel.getState();
//
//                try {
//                    Integer text = (Integer) ageField.getValue();
//                    currentState.setAge(text);
//                    signupViewModel.setState(currentState);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter a valid integer.");
//                }
//
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
        // Add a ChangeListener to the JSpinner
        ageField.addChangeListener(e -> {
            final SignupState currentState = signupViewModel.getState();

            // Get the value from the JSpinner (assuming it's an Integer)
            try {
                Integer value = (Integer) ageField.getValue(); // Casting to Integer if using SpinnerNumberModel
                currentState.setAge(value);
                signupViewModel.setState(currentState);
            } catch (ClassCastException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        });
    }

    private void addWeightListener() {
//        weightField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final SignupState currentState = signupViewModel.getState();
//                String text = weightField.getText();
//                try {
//                    // Convert the text to an integer
//                    float value = Float.parseFloat(text);
//                    currentState.setWeight(value);
//                    signupViewModel.setState(currentState);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter a valid float.");
//                }
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
        // Add a ChangeListener to the JSpinner
        weightField.addChangeListener(e -> {
            final SignupState currentState = signupViewModel.getState();

            // Get the value from the JSpinner (assuming it's an Integer)
            try {
                Float value = (Float) weightField.getValue(); // Casting to Integer if using SpinnerNumberModel
                currentState.setWeight(value);
                signupViewModel.setState(currentState);
            } catch (ClassCastException ex) {
                System.out.println("Invalid input. Please enter a valid weight.");
            }
        });
    }

    private void addHeightListener() {
//        heightField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final SignupState currentState = signupViewModel.getState();
//                String text = heightField.getText();
//                try {
//                    // Convert the text to an integer
//                    float value = Float.parseFloat(text);
//                    currentState.setHeight(value);
//                    signupViewModel.setState(currentState);
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter a valid float.");
//                }
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
        // Add a ChangeListener to the JSpinner
        heightField.addChangeListener(e -> {
            final SignupState currentState = signupViewModel.getState();

            // Get the value from the JSpinner (assuming it's an Integer)
            try {
                Float value = (Float) heightField.getValue(); // Casting to Integer if using SpinnerNumberModel
                currentState.setHeight(value);
                signupViewModel.setState(currentState);
            } catch (ClassCastException ex) {
                System.out.println("Invalid input. Please enter a valid height.");
            }
        });
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
