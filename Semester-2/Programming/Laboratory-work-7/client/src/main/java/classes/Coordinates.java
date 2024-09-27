package classes;

public class Coordinates {
    private Double x;
    private long y;

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
            System.out.println("The value of the Coordinates field.X must be greater than -267, the field cannot be null");
        }
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        if(y <= 803){
            this.y = y;
        }else{
            System.out.println("The maximum value of the Coordinates.Y field is 803");
        }
    }
}