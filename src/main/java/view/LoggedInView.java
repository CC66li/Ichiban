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
import interface_adapter.get_receipe.GetReceipeViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private ChangeWeightController changeWeightController;
    private ChangePasswordController changePasswordController;
    private final JTextField ingredientField = new JTextField(150);
    private final JRadioButton breakButton = new JRadioButton("Breakfast");
    private final JRadioButton lunchButton = new JRadioButton("Lunch");
    private final JRadioButton dinnerButton = new JRadioButton("Dinner");
    private final ButtonGroup mealGroup = new ButtonGroup();
  
    private final JRadioButton alcoButton = new JRadioButton("Alcohol-Free");
    private final JRadioButton dairyButton = new JRadioButton("Dairy-Free");
    private final JRadioButton peanutButton = new JRadioButton("Peanut-Free");
    private final JRadioButton vegetarianButton = new JRadioButton("Vegetarian");
    private final JRadioButton glutenButton = new JRadioButton("Gluten-Free");
    private final JRadioButton eggButton = new JRadioButton("Egg-Free");
    private final JRadioButton healthButton = new JRadioButton("No Allergy");
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
    private GetReceipeController getReceipeController;

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
        final LabelTextPanel ingredientInfo = new LabelTextPanel(
                new JLabel(LoggedInViewModel.INGREDIENT_LABEL), ingredientField);

        // Meal types chooser
        final JPanel mealInfo = new JPanel();
        mealInfo.setLayout(new BoxLayout(mealInfo, BoxLayout.Y_AXIS));
        mealInfo.add(new JLabel(LoggedInViewModel.MEAL_LABEL));
        mealInfo.add(breakButton);
        mealInfo.add(lunchButton);
        mealInfo.add(dinnerButton);
        mealGroup.add(breakButton);
        mealGroup.add(lunchButton);
        mealGroup.add(dinnerButton);

        breakButton.addActionListener(e -> updateMeal("Breakfast"));
        lunchButton.addActionListener(e -> updateMeal("Lunch"));
        dinnerButton.addActionListener(e -> updateMeal("Dinner"));

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();

        // Cuisine types chooser
        final JPanel cuisineInfo = new JPanel();
        cuisineInfo.setLayout(new BoxLayout(cuisineInfo, BoxLayout.Y_AXIS));
        cuisineInfo.add(new JLabel(LoggedInViewModel.CUISINE_LABEL));

        cuisineInfo.add(chiButton);
        cuisineInfo.add(freButton);
        cuisineInfo.add(indButton);
        cuisineInfo.add(japButton);
        cuisineInfo.add(korButton);
        cuisineInfo.add(ameButton);
        cuisineInfo.add(itaButton);
        typeGroup.add(chiButton);
        typeGroup.add(freButton);
        typeGroup.add(indButton);
        typeGroup.add(japButton);
        typeGroup.add(korButton);
        typeGroup.add(ameButton);
        typeGroup.add(itaButton);

        chiButton.addActionListener(e -> updateType("Chinese"));
        freButton.addActionListener(e -> updateType("French"));
        indButton.addActionListener(e -> updateType("Indian"));
        japButton.addActionListener(e -> updateType("Japanese"));
        korButton.addActionListener(e -> updateType("Korean"));
        ameButton.addActionListener(e -> updateType("American"));
        itaButton.addActionListener(e -> updateType("Italian"));

        // Allergy types chooser
        final JPanel allergyInfo = new JPanel();
        allergyInfo.setLayout(new BoxLayout(allergyInfo, BoxLayout.Y_AXIS));
        allergyInfo.add(new JLabel(LoggedInViewModel.ALLERGY_LABEL));

        allergyInfo.add(alcoButton);
        allergyInfo.add(dairyButton);
        allergyInfo.add(peanutButton);
        allergyInfo.add(vegetarianButton);
        allergyInfo.add(glutenButton);
        allergyInfo.add(eggButton);
        allergyInfo.add(healthButton);
        allergyGroup.add(alcoButton);
        allergyGroup.add(dairyButton);
        allergyGroup.add(peanutButton);
        allergyGroup.add(vegetarianButton);
        allergyGroup.add(glutenButton);
        allergyGroup.add(eggButton);
        allergyGroup.add(healthButton);

        alcoButton.addActionListener(e -> updateAllergy("alcohol-free"));
        dairyButton.addActionListener(e -> updateAllergy("dairy-free"));
        peanutButton.addActionListener(e -> updateAllergy("peanut-free"));
        vegetarianButton.addActionListener(e -> updateAllergy("vegetarian"));
        glutenButton.addActionListener(e -> updateAllergy("gluten-free"));
        eggButton.addActionListener(e -> updateAllergy("egg-free"));
        healthButton.addActionListener(e -> updateAllergy(null));


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
                                currentState.getAge(),
                                null, null, null, null
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
                                currentState.getAge(),
                                null, null, null, null
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

        generateReceipt.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(generateReceipt)) {
                            final LoggedInState currentState = loggedInViewModel.getState();

                            getReceipeController.execute(
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

        addIngredientListener();
        addMealTypeListener();
        addCuisineTypeListener();
        addAllergyListener();

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(ingredientInfo);
        this.add(mealInfo);
        this.add(cuisineInfo);
        this.add(allergyInfo);
        this.add(buttons);
    }

    // Cuisine types generator
    private void updateType(String cuisine) {
        final LoggedInState currentState = loggedInViewModel.getState();
        currentState.setCuisineType(cuisine);
        loggedInViewModel.setState(currentState);
    }

    private void addCuisineTypeListener() {
        typeGroup.add(chiButton);
        typeGroup.add(freButton);
        typeGroup.add(indButton);
        typeGroup.add(japButton);
        typeGroup.add(korButton);
        typeGroup.add(ameButton);
        typeGroup.add(itaButton);

        // Add ActionListener to update gender in the SignupState
        ActionListener cuisineTypeListener = e -> {
            final LoggedInState currentState = loggedInViewModel.getState();
            if (chiButton.isSelected()) {
                currentState.setCuisineType("Chinese");
            } else if (freButton.isSelected()) {
                currentState.setCuisineType("French");
            } else if (indButton.isSelected()) {
                currentState.setCuisineType("Indian");
            } else if (japButton.isSelected()) {
                currentState.setCuisineType("Japanese");
            } else if (korButton.isSelected()) {
                currentState.setCuisineType("Korean");
            } else if (ameButton.isSelected()) {
                currentState.setCuisineType("American");
            } else if (itaButton.isSelected()) {
                currentState.setCuisineType("Italian");
            }
            loggedInViewModel.setState(currentState);
        };

        chiButton.addActionListener(cuisineTypeListener);
        freButton.addActionListener(cuisineTypeListener);
        indButton.addActionListener(cuisineTypeListener);
        japButton.addActionListener(cuisineTypeListener);
        korButton.addActionListener(cuisineTypeListener);
        ameButton.addActionListener(cuisineTypeListener);
        itaButton.addActionListener(cuisineTypeListener);
    }

    // Ingredients collectors
    private void addIngredientListener() {
        ingredientField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setUsername(ingredientField.getText());
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
    }

    // Meal types generator
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
                currentState.setMealType("Breakfast");
            } else if (lunchButton.isSelected()) {
                currentState.setMealType("Lunch");
            } else if (dinnerButton.isSelected()) {
                currentState.setMealType("Dinner");
            }
            loggedInViewModel.setState(currentState);
        };

        breakButton.addActionListener(mealTypeListener);
        lunchButton.addActionListener(mealTypeListener);
        dinnerButton.addActionListener(mealTypeListener);
    }

    // Allergy types collector
    private void updateAllergy(String allergy) {
        final LoggedInState currentState = loggedInViewModel.getState();
        currentState.setAllergy(allergy);
        loggedInViewModel.setState(currentState);
    }

    private void addAllergyListener() {
        allergyGroup.add(alcoButton);
        allergyGroup.add(dairyButton);
        allergyGroup.add(peanutButton);
        allergyGroup.add(vegetarianButton);
        allergyGroup.add(glutenButton);
        allergyGroup.add(eggButton);
        allergyGroup.add(healthButton);

        // Add ActionListener to update gender in the SignupState
        ActionListener allergyListener = e -> {
            final LoggedInState currentState = loggedInViewModel.getState();
            if (alcoButton.isSelected()) {
                currentState.setAllergy("alcohol-free");
            } else if (dairyButton.isSelected()) {
                currentState.setAllergy("dairy-free");
            } else if (peanutButton.isSelected()) {
                currentState.setAllergy("peanut-free");
            } else if (vegetarianButton.isSelected()) {
                currentState.setAllergy("vegetarian");
            } else if (glutenButton.isSelected()) {
                currentState.setAllergy("gluten-free");
            } else if (eggButton.isSelected()) {
                currentState.setAllergy("egg-free");
            } else if (healthButton.isSelected()) {
                currentState.setAllergy(null);
            }
            loggedInViewModel.setState(currentState);
        };

        alcoButton.addActionListener(allergyListener);
        dairyButton.addActionListener(allergyListener);
        peanutButton.addActionListener(allergyListener);
        vegetarianButton.addActionListener(allergyListener);
        glutenButton.addActionListener(allergyListener);
        eggButton.addActionListener(allergyListener);
        healthButton.addActionListener(allergyListener);
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

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
