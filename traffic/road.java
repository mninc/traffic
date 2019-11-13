import greenfoot.*;

/**
 * Write a description of class road here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class road extends light
{
    public int carsPassed;
    public int carsQueued;
    
    public road() {
        super();
        
        isRoadLight = true;
        carsPassed = 0;
        carsQueued = 0;
    }
}
