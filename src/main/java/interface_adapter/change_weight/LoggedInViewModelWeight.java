package interface_adapter.change_weight;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModelWeight extends ViewModel<LoggedInStateWeight> {

    public LoggedInViewModelWeight() {
        super("logged in");
        setState(new LoggedInStateWeight());
    }

}
