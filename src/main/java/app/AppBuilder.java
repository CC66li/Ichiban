package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.change_weight.ChangeWeightController;
import interface_adapter.change_weight.ChangeWeightPresenter;
import interface_adapter.change_weight.ChangeWeightViewModel;
import interface_adapter.get_receipe.GetReceipeController;
import interface_adapter.get_receipe.GetReceipePresenter;
import interface_adapter.get_receipe.GetReceipeViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_weight.ChangeWeightInputBoundary;
import use_case.change_weight.ChangeWeightInteractor;
import use_case.change_weight.ChangeWeightOutputBoundary;
import use_case.get_receipe.GetReceipeInputBoundary;
import use_case.get_receipe.GetReceipeInteractor;
import use_case.get_receipe.GetReceipeOutputBoundary;
import use_case.logged_in.*;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.logged_in.LoggedInOutputBoundary;
import view.*;

/**
 * The AppBuilder class configures the application's MVC components 
 * and sets up the views and use cases.
 */
public class AppBuilder {

        // GUI components
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

        // Core application dependencies
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

         // View and ViewModel references
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangeWeightViewModel changeWeightViewModel;
    private ChangePasswordView changePasswordView;
    private ChangeWeightView changeWeightView;
    private GetReceipeViewModel getReceipeViewModel;
    private InputIngredientView inputIngredientView;

        /**
     * Initializes the card layout for managing views.
     */
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the ChangePassword View to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    /**
     * Adds the ChangeWeight View to the application.
     * @return this builder
     */
    public AppBuilder addChangeWeightView() {
        changeWeightViewModel = new ChangeWeightViewModel();
        changeWeightView = new ChangeWeightView(changeWeightViewModel);
        cardPanel.add(changeWeightView, changeWeightView.getViewName());
        return this;
    }

    /**
     * Adds the Generate Receipt View to the application.
     * @return this builder
     */
    public AppBuilder addInputIngredientView() {
        getReceipeViewModel = new GetReceipeViewModel();
        inputIngredientView = new InputIngredientView(getReceipeViewModel);
        cardPanel.add(inputIngredientView, inputIngredientView.getViewName());
        return this;
    }

    /**
     * Configures the Signup Use Case.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Configures the Login Use Case.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Configures the Change Weight Use Case.
     * @return this builder
     */
    public AppBuilder addChangeWeightUseCase() {
        final ChangeWeightOutputBoundary changeWeightOutputBoundary =
                new ChangeWeightPresenter(loggedInViewModel, viewManagerModel, changeWeightViewModel);

        final ChangeWeightInputBoundary changeWeightInteractor =
                new ChangeWeightInteractor(userDataAccessObject, changeWeightOutputBoundary, userFactory);

        final ChangeWeightController changeWeightController =
                new ChangeWeightController(changeWeightInteractor);
        loggedInView.setChangeWeightController(changeWeightController);
        return this;
    }

    /**
     * Configures the Change Password Use Case.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel, viewManagerModel, changePasswordViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Configures the Get Receipt Use Case.
     * @return this builder
     */
    public AppBuilder addGetReceiptUseCase() {
        final GetReceipeOutputBoundary getReceipeOutputBoundary = new GetReceipePresenter(viewManagerModel,
                getReceipeViewModel, loggedInViewModel);

        final GetReceipeInputBoundary getReceipeInteractor =
                new GetReceipeInteractor(userDataAccessObject, getReceipeOutputBoundary, userFactory);

        final GetReceipeController getReceipeController =
                new GetReceipeController(getReceipeInteractor);

        inputIngredientView.setGetReceipeController(getReceipeController);
        return this;
    }

    /**
     * Configures the LoggedIn Use Case.
     * @return this builder
     */
    public AppBuilder addLoggedInUseCase() {
        final LoggedInOutputBoundary loggedInOutputBoundary =
                new LoggedInPresenter(loggedInViewModel, getReceipeViewModel, viewManagerModel);

        final LoggedInInputBoundary loggedInInteractor =
                new LoggedInInteractor(userDataAccessObject, loggedInOutputBoundary, userFactory);

        final LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        loggedInView.setLoggedInController(loggedInController);
        return this;
    }

    /**
     * Configures the Logout Use Case.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Configures the Cancel Use Case.
     * @return this builder
     */
    public AppBuilder addCancelUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Builds the JFrame and sets the initial view.
     * @return the application JFrame
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        // Set the initial state to Signup View
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
