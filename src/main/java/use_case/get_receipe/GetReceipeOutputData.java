package use_case.get_receipe;

/**
 * Output Data for the Change Password Use Case.
 */
public class GetReceipeOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public GetReceipeOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
