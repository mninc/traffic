import greenfoot.*;

/**
 * Write a description of class light here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class light extends Actor
{
    protected boolean isRoadLight;
    private int lightStatus; // 0 - red, 1 - red-amber, 2 - amber, 3 - green, 4 - off
    
    public void setLightStatus(int status) {
        if (status > 4 || status < 0) return;
        String imageFilename;
        if (isRoadLight) imageFilename = "l";
        else imageFilename = "p";
        
        imageFilename += "_";
        
        if (status == 0) imageFilename += "red";
        else if (status == 1) imageFilename += "red_amber";
        else if (status == 2) imageFilename += "amber";
        else if (status == 3) imageFilename += "green";
        else imageFilename += "off";
        
        imageFilename += ".png";
        setImage(imageFilename);
    }
}
