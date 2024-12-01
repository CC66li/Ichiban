package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addChangePasswordView()
                                            .addChangeWeightView()
                                            .addInputIngredientView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addLogoutUseCase()
                                            .addCancelUseCase()
                                            .addChangePasswordUseCase()
                                            .addLoggedInUseCase()
                                            .addChangeWeightUseCase()
                                            .addGetReceiptUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
