package interface_adapter.change_weight;

import entity.CommonUser;
import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Password View.
 */
public class ChangeWeightViewModel extends ViewModel<ChangeWeightState>{

    private CommonUser user;

    public ChangeWeightViewModel() {
        super("change weight");
        setState(new ChangeWeightState());
    }
}
