package com.example.footapp.remote;

public class CategoryClient extends BaseClient{
    public static final String BASE_URL = "http://app.iotstar.vn/";
    public static final String URL_CATEGORY_PRODUCT = BASE_URL + "appfoods/";
    private static ApiService apiService;
    public static ApiService getInstance(){
        if(apiService == null){
            apiService = createService(ApiService.class, URL_CATEGORY_PRODUCT);
        }
        return apiService;
    }
}
