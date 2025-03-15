package classes;

public class Coordinates {
    private Double x; //Значение поля должно быть больше -267, Поле не может быть null
    private long y; //Максимальное значение поля: 803

    public Coordinates(Double x, long y){
        this.setX(x);
        this.setY(y);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        if(x > -267){
            this.x = x;
        }else{
            System.out.println("Значение поля Coordinates.X должно быть больше -267, Поле не может быть null");
        }
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        if(y <= 803){
            this.y = y;
        }else{
            System.out.println("Максимальное значение поля Coordinates.Y: 803");
        }
    }
}