package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.change_weight.ChangeWeightController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.get_receipe.GetReceipeController;
import interface_adapter.logout.LogoutController;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logged_in.LoggedInController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangeWeightController changeWeightController;
    private ChangePasswordController changePasswordController;
    private final JTextField ingredientField = new JTextField(150);
    private final JRadioButton breakButton = new JRadioButton("Breakfast");
    private final JRadioButton lunchButton = new JRadioButton("Lunch");
    private final JRadioButton dinnerButton = new JRadioButton("Dinner");
    private final ButtonGroup mealGroup = new ButtonGroup();
    private final JCheckBox alcoButton = new JCheckBox("alcohol-free");
    private final JCheckBox dairyButton = new JCheckBox("dairy-free");
    private final JCheckBox peanutButton = new JCheckBox("peanut-free");
    private final JCheckBox vegetarianButton = new JCheckBox("vegetarian");
    private final JCheckBox glutenButton = new JCheckBox("gluten-free");
    private final JCheckBox eggButton = new JCheckBox("egg-free");
    private final ButtonGroup allergyGroup = new ButtonGroup();
    private final JRadioButton chiButton = new JRadioButton("Chinese");
    private final JRadioButton freButton = new JRadioButton("French");
    private final JRadioButton indButton = new JRadioButton("Indian");
    private final JRadioButton japButton = new JRadioButton("Japanese");
    private final JRadioButton korButton = new JRadioButton("Korean");
    private final JRadioButton ameButton = new JRadioButton("American");
    private final JRadioButton itaButton = new JRadioButton("Italian");
    private final ButtonGroup typeGroup = new ButtonGroup();
    private LogoutController logoutController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton generateReceipt;
    private final JButton changePassword;
    private final JButton changeWeight;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Set the Types buttons here
        final LabelTextPanel ingredientInputField = new LabelTextPanel(
                new JLabel("Ingredients"), ingredientField);

        final JPanel mealInfo = new JPanel();
        mealInfo.setLayout(new BoxLayout(mealInfo, BoxLayout.Y_AXIS));
        mealInfo.add(new JLabel(LoggedInViewModel.MEAL_LABEL));
        mealInfo.add(breakButton);
        mealInfo.add(lunchButton);
        mealInfo.add(dinnerButton);
        mealGroup.add(breakButton);
        mealGroup.add(lunchButton);
        mealGroup.add(dinnerButton);

        // Add ActionListeners to gender buttons
        breakButton.addActionListener(e -> updateMeal("Breakfast"));
        lunchButton.addActionListener(e -> updateMeal("Lunch"));
        dinnerButton.addActionListener(e -> updateMeal("Dinner"));

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();


        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);
        changePassword = new JButton("Change Password");
        buttons.add(changePassword);
        changeWeight = new JButton("Change Weight");
        buttons.add(changeWeight);
        generateReceipt = new JButton("Generate Receipt");
        buttons.add(generateReceipt);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final LoggedInState currentState = loggedInViewModel.getState();
//                currentState.setPassword(passwordInputField.getText());
//                loggedInViewModel.setState(currentState);
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

        JButton finalChangePassword = changePassword;
        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(finalChangePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
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

        //JButton finalGenerateReceipt = generateReceipt;
        generateReceipt.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(generateReceipt)) {
                            final LoggedInState currentState = loggedInViewModel.getState();
                            GetReceipeController controller = new GetReceipeController();

                            controller.execute(
                                    currentState.getHeight(),
                                    currentState.getWeight(),
                                    currentState.getGender(),
                                    currentState.getAge(),
                                    currentState.getCuisineType(),
                                    currentState.getIngredient()
                            );
                        }
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

    private void updateMeal(String meal) {
        final LoggedInState currentState = loggedInViewModel.getState();
        currentState.setMealType(meal);
        loggedInViewModel.setState(currentState);
    }

    private void addMealTypeListener() {
        mealGroup.add(breakButton);
        mealGroup.add(lunchButton);
        mealGroup.add(dinnerButton);

        // Add ActionListener to update gender in the SignupState
        ActionListener mealTypeListener = e -> {
            final LoggedInState currentState = loggedInViewModel.getState();
            if (breakButton.isSelected()) {
                currentState.setGender("Breakfast");
            } else if (lunchButton.isSelected()) {
                currentState.setGender("Lunch");
            } else if (dinnerButton.isSelected()) {
                currentState.setGender("Dinner");
            }
            loggedInViewModel.setState(currentState);
        };

        breakButton.addActionListener(mealTypeListener);
        lunchButton.addActionListener(mealTypeListener);
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

    public void setChangeWeightController(ChangeWeightController changeWeightController) {
        this.changeWeightController = changeWeightController;
    }

    public void setLogoutController(LogoutController logoutController) {
        // save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
