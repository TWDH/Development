import net.sf.json.JSONObject;
public class jsonTest {
    public JSONObject getAccessToken() {
        String token = "{\n" +
                "  \"access_token\": \"ACCESS_TOKEN\",\n" +
                "  \"expires_in\": 7200,\n" +
                "  \"refresh_token\": \"REFRESH_TOKEN\",\n" +
                "  \"openid\": \"OPENID\",\n" +
                "  \"scope\": \"SCOPE\"\n" +
                "}";
        JSONObject access_token = JSONObject.fromObject(token);
        return access_token;
    }

    public static void main(String[] args) {
        jsonTest jsonTest = new jsonTest();
        JSONObject accessToken = jsonTest.getAccessToken();
        System.out.println(accessToken.toString());
    }
}
