
public class Validator {

    // Метод для проверки допустимого значения координаты X
    public static boolean validateX(float x) {
        return x >= -3 && x <= 5;
    }

    // Метод для проверки допустимого значения координаты Y
    public static boolean validateY(float y) {
        return y >= -5 && y <= 3;
    }

    // Метод для проверки допустимого значения радиуса R
    public static boolean validateR(float r) {
        return r >= 2 && r <= 5;
    }
}