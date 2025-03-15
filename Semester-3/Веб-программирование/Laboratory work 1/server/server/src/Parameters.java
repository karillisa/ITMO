

import org.json.JSONObject;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Parameters {

    public static HashMap<String, String> parse(String jsonString) {
        // Создаем новый HashMap для хранения результата
        HashMap<String, String> params = new HashMap<>();

        // Парсим JSON строку
        JSONObject jsonObject = new JSONObject(jsonString);

        // Получаем значения по ключам и кладем в HashMap
        params.put("x", String.valueOf(jsonObject.getInt("x")));
        params.put("y", String.valueOf(jsonObject.getInt("y")));
        params.put("r", String.valueOf(jsonObject.getInt("r")));

        return params;
    }
}