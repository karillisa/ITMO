package exception;

import objects.Building;
import process.Work;

public class BuildingIsClosedException extends Exception{
    private Building building;
    public BuildingIsClosedException(String message, Throwable cause, Building building){
        super(message, cause);
        this.building = building;
    }
    public BuildingIsClosedException(String message){
        super(message);
    }
}
