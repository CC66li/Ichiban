package interface_adapter.change_password;

import entity.CommonUser;
import entity.User;
import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Password View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    private CommonUser currentUser;

    public ChangePasswordViewModel() {
        super("change password");
        setState(new ChangePasswordState());
    }
}
