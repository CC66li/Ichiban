package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.change_weight.ChangeWeightUserDataAccessInterface;
import use_case.get_receipe.GetReceipeInputData;
import use_case.get_receipe.GetReceipeUserDataAccessInterface;
import use_case.logged_in.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        ChangeWeightUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        LoggedInUserDataAccessInterface,
        GetReceipeUserDataAccessInterface{

    private final Map<String, User> users = new HashMap<>();
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String MESSAGE = "message";

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void changeWeight(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public JSONArray getReceipe(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        GetReceipeInputData getReceipeInputData = new GetReceipeInputData(user.getHeight(),
                user.getWeight(),
                user.getGender(),
                user.getAge(),
                user.getMealType(),
                user.getCuisineType(),
                user.getAllergy(),
                user.getIngredient());

        // According to the input get the url
        String requestUrl = "https://api.edamam.com/api/recipes/v2?type=public&app_id=<ff136c6d>&app_key=<009e6cd694e4752490ac362dc7d>";
        if (user.getIngredient() != null){
            for (String item: user.getIngredient()){
                requestUrl += "&q=" + item;
            }
        }
        if (user.getAllergy() != null){
            requestUrl += "&health=" + user.getAllergy();
        }
        if (user.getCuisineType() != null){
            requestUrl += "&cuisineType=" + user.getCuisineType();
        }
        requestUrl += "&calories=0-" + getReceipeInputData.getBMR();

        final Request request = new Request.Builder()
                .url(requestUrl)
                .method("GET", null)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return responseBody.getJSONArray("hits");
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }

        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
