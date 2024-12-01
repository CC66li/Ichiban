package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Password View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public ChangePasswordViewModel() {
        super("change password");
        setState(new ChangePasswordState());
    }
}
