package use_case.change_weight;

/**
 * The Change Weight Use Case.
 */
public interface ChangeWeightInputBoundary {

    /**
     * Execute the Change Weight Use Case.
     * @param changeWeightInputData the input data for this use case
     */
    void execute(ChangeWeightInputData changeWeightInputData);

}
