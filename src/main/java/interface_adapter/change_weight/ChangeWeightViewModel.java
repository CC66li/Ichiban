package interface_adapter.change_weight;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Password View.
 */
public class ChangeWeightViewModel extends ViewModel<ChangeWeightState>{

    public ChangeWeightViewModel() {
        super("change weight");
        setState(new ChangeWeightState());
    }
}
