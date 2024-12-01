package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.get_receipe.GetReceipeController;
import interface_adapter.get_receipe.GetReceipeState;
import interface_adapter.get_receipe.GetReceipeViewModel;
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

/**
 * The View for when the user is generating the receipt.
 */
public class InputIngredientView extends JPanel implements PropertyChangeListener{

    private final String viewName = "receipt generate";
    private GetReceipeViewModel getReceipeViewModel;

    private final JButton cancel;
    private GetReceipeController getReceipeController;

    public InputIngredientView(GetReceipeViewModel getReceipeViewModel) {
        final JLabel title = new JLabel("Receipt Generator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Set title"Login Screen"

        final GetReceipeState currentState = getReceipeViewModel.getState();
        final JPanel ingredientPanel = new JPanel();
        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.Y_AXIS));

        // Get ingredients
        JSONArray ingredients = getReceipeController.execute(
                currentState.getUsername(),
                currentState.getPassword(),
                currentState.getHeight(),
                currentState.getWeight(),
                currentState.getGender(),
                currentState.getAge(),
                currentState.getMealType(),
                currentState.getCuisineType(),
                currentState.getAllergy(),
                currentState.getIngredient());

        // three ingredients are provided.
        for (int i = 0; i < 3; i++) {
            JSONObject hit = ingredients.getJSONObject(i);
            JSONObject recipe = hit.getJSONObject("recipe");

            // Extract specific values
            String label = recipe.getString("label");
            String image = recipe.getString("image");
            JSONArray listIngredients = recipe.getJSONArray("ingredients");

            // Add label
            JLabel labelTitle = new JLabel(label);
            labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            ingredientPanel.add(labelTitle);

            // Add Image
            try {
                URL imageUrlObject = new URL(image); // Replace with a valid image URL
                BufferedImage givenMage = ImageIO.read(imageUrlObject);
                JLabel imageLabel = new JLabel(new ImageIcon(givenMage));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                ingredientPanel.add(imageLabel);
            } catch (Exception e) {
                JLabel imageLabel = new JLabel("[Image not available]");
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                ingredientPanel.add(imageLabel);
            }

            // Add listIngredients
            JLabel ingredientsTitle = new JLabel("Ingredients:");
            ingredientsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            ingredientPanel.add(ingredientsTitle);

            for (int j = 0; j < listIngredients.length(); j++) {
                JLabel ingredientLabel = new JLabel("- " + listIngredients.getString(j));
                ingredientLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                ingredientPanel.add(ingredientLabel);
            }
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setGetReceipeController(GetReceipeController getReceipeController) {
        this.getReceipeController = getReceipeController;
    }
}
