
import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName()); // Создание логгера
    private static final String RESPONSE_TEMPLATE = "Content-Type: application/json\nContent-Length: %d\n\n%s"; // Шаблон для JSON-ответа

    static {
        try {
            FileHandler fh = new FileHandler("/home/studs/s373432/httpd-root/fcgi-bin/server.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FCGIInterface fcgi = new FCGIInterface();
        // Цикл обработки входящих запросов
        while (fcgi.FCGIaccept() >= 0) {
            long startTime = System.currentTimeMillis();        // Запись времени начала обработки запроса
            try {
                // Чтение и логирование тела запроса
                String body = readRequestBody();
                logger.info("Received request body: " + body);

                HashMap<String, String> params = Parameters.parse(body);        // Парсинг тела запроса в параметры

                if (!params.containsKey("x") || !params.containsKey("y") || !params.containsKey("r")) {
                    sendJson("{\"error\": \"missed necessary query param\"}");
                    continue;
                }

                // Преобразование параметров в числа с плавающей запятой
                float x = Float.parseFloat(params.get("x"));
                float y = Float.parseFloat(params.get("y"));
                float r = Float.parseFloat(params.get("r"));

                if (Validator.validateX(x) && Validator.validateY(y) && Validator.validateR(r)) {
                    boolean isInside = Checker.hit(x, y, r);        // Проверка попадает ли точка в заданную область
                    long endTime = System.currentTimeMillis();
                    sendJson(String.format(
                            "{\"result\": %b, \"currentTime\": \"%s\", \"executionTime\": \"%dms\"}",
                            isInside, java.time.LocalDateTime.now(), (endTime - startTime)
                    ));
                } else {
                    sendJson("{\"error\": \"invalid data\"}");
                }
            } catch (Exception e) {
                sendJson(String.format("{\"error\": \"%s\"}", e.toString()));
            }
        }
    }

    // Метод для отправки JSON-ответа
    private static void sendJson(String jsonDump) {
        logger.info("Sending JSON response: " + jsonDump);
        System.out.printf(RESPONSE_TEMPLATE + "%n", jsonDump.getBytes(StandardCharsets.UTF_8).length, jsonDump);
    }

    // Метод для чтения тела запроса
    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();
        int contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes = FCGIInterface.request.inStream.read(buffer.array(), 0, contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}