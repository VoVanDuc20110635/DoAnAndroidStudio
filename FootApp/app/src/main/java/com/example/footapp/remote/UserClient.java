package com.example.footapp.remote;


import static com.example.footapp.remote.CategoryClient.BASE_URL;

public class UserClient extends BaseClient{
    public static final String URL_SIGNUP_LOGIN = BASE_URL + "shoppingapp/";
    private static ApiService apiService;
    public static ApiService getInstance(){
        if(apiService == null){
            apiService = createService(ApiService.class, URL_SIGNUP_LOGIN);
        }
        return apiService;
    }
}
