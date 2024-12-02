package view;

import interface_adapter.get_receipe.GetReceipeController;
import interface_adapter.get_receipe.GetReceipeViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.logout.LogoutController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

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

        fetchAndDisplayIngredients(ingredientPanel);

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
                    JSONObject eachReceipt = ingredients.getJSONObject(i);

                    // Add recipe label
                    JLabel labelTitle = new JLabel(eachReceipt.getString("title"));
                    labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
                    ingredientPanel.add(labelTitle);

                    // Add recipe image
                    try {
                        URL imageUrlObject = new URL(eachReceipt.getString("image"));
                        BufferedImage recipeImage = ImageIO.read(imageUrlObject);
                        JLabel imageLabel = new JLabel(new ImageIcon(recipeImage));
                        ingredientPanel.add(imageLabel);
                    } catch (Exception e) {
                        JLabel errorLabel = new JLabel("[Image not available]");
                        ingredientPanel.add(errorLabel);
                    }
                }
            } else{
                JLabel errorLabel = new JLabel("No recipes found.");
                ingredientPanel.add(errorLabel);
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Error fetching or displaying ingredients: " + e.getMessage());
            ingredientPanel.add(errorLabel);
            e.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public String getViewName() {
        return viewName;
    }
}
