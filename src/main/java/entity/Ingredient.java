package entity;

/**
 * Represents an ingredient with an associated input value.
 * <p>
 * This class provides getter and setter methods to manage the input value
 * of the ingredient, which can represent user-provided or system-processed data.
 */
public class Ingredient {

        // The input value associated with the ingredient
    private String inputValue;

    /**
     * Retrieves the input value of the ingredient.
     *
     * @return the input value as a {@link String}
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * Sets the input value for the ingredient.
     *
     * @param inputValue the value to associate with the ingredient
     */
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

}
