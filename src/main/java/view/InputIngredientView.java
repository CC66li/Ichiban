package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.get_receipe.GetReceipeController;
import interface_adapter.get_receipe.GetReceipeState;
import interface_adapter.get_receipe.GetReceipeViewModel;
import interface_adapter.logout.LogoutController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

/**
 * The View for when the user is generating the receipt.
 */
public class InputIngredientView extends JPanel implements PropertyChangeListener{

    private final String viewName = "receipt generate";
    private LogoutController logoutController;
    private GetReceipeViewModel getReceipeViewModel;
    private GetReceipeController getReceipeController;

    private final JButton returnto;


    public InputIngredientView(GetReceipeViewModel getReceipeViewModel,
                               GetReceipeController getReceipeController) {
        this.getReceipeViewModel = getReceipeViewModel;
        this.getReceipeViewModel.addPropertyChangeListener(this);
        this.getReceipeController = getReceipeController;

        setLayout(new BorderLayout());

        // Title
        final JLabel title = new JLabel("Receipt Generator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Set title"Login Screen"

        // Buttons panel
        final JPanel buttons = new JPanel();
        returnto = new JButton("Return");
        buttons.add(returnto);
        add(buttons, BorderLayout.SOUTH);

        // Ingredient display area
        final JPanel ingredientPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(ingredientPanel);
        add(scrollPane, BorderLayout.CENTER);
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));

        // Event Listener for the Return button
        returnto.addActionListener(evt -> {
            if (logoutController != null) {
                logoutController.switchToLoginView();
            } else {
                System.out.println("LogoutController is not initialized.");
            }
        });

        // Fetch ingredients and populate the UI
        fetchAndDisplayIngredients(ingredientPanel);

//        // Get ingredients
//        JSONArray ingredients = getReceipeController.execute(
//                currentState.getUsername(),
//                currentState.getPassword(),
//                currentState.getHeight(),
//                currentState.getWeight(),
//                currentState.getGender(),
//                currentState.getAge(),
//                currentState.getMealType(),
//                currentState.getCuisineType(),
//                currentState.getAllergy(),
//                currentState.getIngredient());

//        returnto.addActionListener(
//                evt -> {
//                    if (evt.getSource().equals(returnto)) {
//                        // Call switchToLoginView only if logoutController is set
//                        if (this.logoutController != null) {
//                            this.logoutController.switchToLoginView();
//                        }
//                        else {
//                            System.out.println("LogoutController is not initialized.");
//                        }
//                    }
//                }
//        );

//        // three ingredients are provided.
//        for (int i = 0; i < 3; i++) {
//            JSONObject hit = ingredients.getJSONObject(i);
//            JSONObject recipe = hit.getJSONObject("recipe");
//
//            // Extract specific values
//            String label = recipe.getString("label");
//            String image = recipe.getString("image");
//            JSONArray listIngredients = recipe.getJSONArray("ingredients");
//
//            // Add label
//            JLabel labelTitle = new JLabel(label);
//            labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//            ingredientPanel.add(labelTitle);
//
//            // Add Image
//            try {
//                URL imageUrlObject = new URL(image); // Replace with a valid image URL
//                BufferedImage givenMage = ImageIO.read(imageUrlObject);
//                JLabel imageLabel = new JLabel(new ImageIcon(givenMage));
//                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//                ingredientPanel.add(imageLabel);
//            } catch (Exception e) {
//                JLabel imageLabel = new JLabel("[Image not available]");
//                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//                ingredientPanel.add(imageLabel);
//            }
//
//            // Add listIngredients
//            JLabel ingredientsTitle = new JLabel("Ingredients:");
//            ingredientsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
//            ingredientPanel.add(ingredientsTitle);
//
//            for (int j = 0; j < listIngredients.length(); j++) {
//                JLabel ingredientLabel = new JLabel("- " + listIngredients.getString(j));
//                ingredientLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//                ingredientPanel.add(ingredientLabel);
//            }
//        }
//
//        this.add(title);
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(buttons);

    }

    private void fetchAndDisplayIngredients(JPanel ingredientPanel) {
        try {
            // Get the current state
            if (getReceipeController == null){
                JLabel errorLabel = new JLabel("Controllers are not initialized.");
                ingredientPanel.add(errorLabel);
            }
            if (getReceipeViewModel == null) {
                JLabel errorLabel = new JLabel("ViewModels are not initialized.");
                ingredientPanel.add(errorLabel);
                return;
            }

            // Fetch ingredients
            JSONArray ingredients = getReceipeController.execute(
                    getReceipeViewModel.getState().getUsername(),
                    getReceipeViewModel.getState().getPassword(),
                    getReceipeViewModel.getState().getHeight(),
                    getReceipeViewModel.getState().getWeight(),
                    getReceipeViewModel.getState().getGender(),
                    getReceipeViewModel.getState().getAge(),
                    getReceipeViewModel.getState().getMealType(),
                    getReceipeViewModel.getState().getCuisineType(),
                    getReceipeViewModel.getState().getAllergy(),
                    getReceipeViewModel.getState().getIngredient());

            // Display each ingredient
            if (ingredients != null){
                for (int i = 0; i < ingredients.length(); i++) {
                    JSONObject hit = ingredients.getJSONObject(i);
                    JSONObject recipe = hit.getJSONObject("recipe");

                    // Add recipe label
                    JLabel labelTitle = new JLabel(recipe.getString("label"));
                    labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
                    ingredientPanel.add(labelTitle);

                    // Add recipe image
                    try {
                        URL imageUrlObject = new URL(recipe.getString("image"));
                        BufferedImage recipeImage = ImageIO.read(imageUrlObject);
                        JLabel imageLabel = new JLabel(new ImageIcon(recipeImage));
                        ingredientPanel.add(imageLabel);
                    } catch (Exception e) {
                        JLabel errorLabel = new JLabel("[Image not available]");
                        ingredientPanel.add(errorLabel);
                    }

                    // Add ingredients list in a JTable
                    JSONArray listIngredients = recipe.getJSONArray("ingredients");
                    JTable ingredientTable = createTableFromJSONArray(listIngredients);
                    JScrollPane tableScrollPane = new JScrollPane(ingredientTable);
                    tableScrollPane.setPreferredSize(new Dimension(400, 100));
                    ingredientPanel.add(tableScrollPane);
                }
            }
            else{
                System.out.println("No ingredients found.");
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Error fetching or displaying ingredients: " + e.getMessage());
            ingredientPanel.add(errorLabel);
            e.printStackTrace();
        }
    }

    private JTable createTableFromJSONArray(JSONArray jsonArray) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Ingredient");

        for (int i = 0; i < jsonArray.length(); i++) {
            tableModel.addRow(new Object[]{jsonArray.getString(i)});
        }

        return new JTable(tableModel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setGetReceipeController(GetReceipeController getReceipeController) {
        this.getReceipeController = getReceipeController;
    }

    public String getViewName() {
        return viewName;
    }
}
