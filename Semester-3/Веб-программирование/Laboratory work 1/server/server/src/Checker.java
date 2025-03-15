
public class Checker {

    public static boolean hit(float x, float y, float r) {
        return inRect(x, y, r) || inTriangle(x, y, r) || inQuarterCircle(x, y, r);          // Проверка попадания в прямоугольник, треугольник или четверть круга
    }

    // Проверка, находится ли точка внутри границ прямоугольника
    private static boolean inRect(float x, float y, float r) {

        return x >= 0 && x <= r && y >= 0 && y <= r;
    }

    // Проверка, находится ли точка внутри границ треугольника
    private static boolean inTriangle(float x, float y, float r) {
        if(x <= 0 && x >= -(r / 2) && y >= 0 && y <= (r / 2)){
            float newX = - ((r / 2) - y);
            if(x >= newX){
                return true;
            }
            return false;
        }
        return false;
    }

    // Проверка, находится ли точка внутри границ четверти круга
    private static boolean inQuarterCircle(float x, float y, float r) {
        return x <= 0 && y <= 0 && (x * x + y * y <= r * r);            // Проверка, находится ли точка в пределах четверти круга
    }
}