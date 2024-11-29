package use_case.change_weight;


/**
 * The output boundary for the Change Weight Use Case.
 */
public interface ChangeWeightOutputBoundary {
    /**
     * Prepares the success view for the Change Weight Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangeWeightOutputData outputData);

    /**
     * Prepares the failure view for the Change Weight Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
