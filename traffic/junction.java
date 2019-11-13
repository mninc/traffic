import greenfoot.*;

public class junction extends World
{
    // road lights
    public road a;
    public int in_a;
    public int out_a;
    public road b;
    public int in_b;
    public int out_b;
    public road c;
    public int in_c;
    public int out_c;
    public road d;
    public int in_d;
    public int out_d;
    
    private road[] rl;
    
    // pedestrian lights
    private pedestrian[] pl;
    
    // wait light
    private wait pedestrianWait;
    // pedestrian button is pressed
    private boolean pedestrians;
    // pedestrians can cross
    private boolean pedestriansCross;
    // step lights are on (0: off; 1-3: a turning on, on, turning off; 4: off; 5-7: b; 8: off; 9-11: c; 12: off; 13-15: d)
    public int step;
    // step pedestrian lights are on (0: turning on; 1: on; 2: turning off)
    private int pedestrianStep;
    
    // timings, - 1 each act
    private int timer;
    // time car spawns
    private int spawnTimer;
    
    // acts for lights to change
    private final static int lightChangeTime = 50;
    // multiplier for length of time to stay green
    private final static double greenMultiplier = 100.0;
    // spawn rate - spawn a car every x acts
    private int spawnRate;
    
    public junction(int in_a_val, int in_b_val, int in_c_val, int in_d_val, int out_a_val, int out_b_val, int out_c_val, int out_d_val, int spawn_rate_val)
    {
        super(1080, 720, 1);
        
        a = addRoadLight(576, 286, 180);
        in_a = in_a_val;
        out_a = out_a_val;
        b = addRoadLight(612, 362, 270);
        in_b = in_b_val;
        out_b = out_b_val;
        c = addRoadLight(525, 399, 0);
        in_c = in_c_val;
        out_c = out_c_val;
        d = addRoadLight(487, 322, 90);
        in_d = in_d_val;
        out_d = out_d_val;
        
        spawnRate = spawn_rate_val;
        
        rl = new road[]{a, b, c, d};
        setLights(rl, 0);
        
        pl = new pedestrian[]{
          addPLight(600, 255, 270),
          addPLight(500, 255, 90),
          addPLight(644, 297, 180),
          addPLight(644, 387, 0),
          addPLight(600, 432, 270),
          addPLight(500, 432, 90),
          addPLight(455, 297, 180),
          addPLight(455, 387, 0)
        };
        setLights(pl, 0);
        
        pedestrianWait = new wait();
        addObject(pedestrianWait, 870, 567);
        
        pedestrians = false;
        pedestriansCross = false;
        step = 0;
        pedestrianStep = 0;
        timer = 0;
        spawnTimer = 20;
    }
    
    private void init() {
        
    }
    
    private road addRoadLight(int x, int y, int rotation) {
        road r = new road();
        addObject(r, x, y);
        r.setRotation(rotation);
        return r;
    }
    
    private pedestrian addPLight(int x, int y, int rotation) {
        pedestrian p = new pedestrian();
        addObject(p, x, y);
        p.setRotation(rotation);
        return p;
    }
    
    private void setLights(light[] lights, int status) {
        for (int i = 0; i < lights.length; i++) {
            lights[i].setLightStatus(status);
        }
    }
    
    private boolean anyPressed(pedestrian[] lights) {
        for (int i = 0; i < lights.length; i++) {
            if (Greenfoot.mouseClicked(lights[i])) return true;
        }
        return false;
    }
    
    private void addCar() {
        car c = new car();
        addObject(c, 0, 0);
        c.init();
    }
    
    public void act() {
        spawnTimer--;
        
        if (spawnTimer <= 0) {
            spawnTimer = spawnRate;
            
            addCar();
        }
        
        timer--;
        if (!pedestrians && !pedestriansCross && anyPressed(pl)) {
            pedestrianWait.on();
            pedestrians = true;
        }
        
        if (timer <= 0) {
            if (pedestriansCross) {
                pedestrianStep++;
                if (pedestrianStep == 1) {
                    setLights(pl, 3);
                    timer = 500;
                } else if (pedestrianStep == 2) {
                    pedestriansCross = false;
                    timer = lightChangeTime;
                    setLights(pl, 0);
                }
                return;
            }
            
            if (step > 15) step = 0;
            if (step == 0 || step == 4 || step == 8 || step == 12) {
                setLights(rl, 0);
                if (pedestrians) {
                    pedestrians = false;
                    pedestriansCross = true;
                    pedestrianStep = 0;
                    timer = lightChangeTime;
                    pedestrianWait.off();
                } else {
                    step++;
                    timer = lightChangeTime;
                }
            } else if (step == 1) {
                a.setLightStatus(1);
                timer = lightChangeTime;
                step++;
            } else if (step == 2) {
                a.setLightStatus(3);
                timer = (int)(in_a*greenMultiplier);
                step++;
            } else if (step == 3) {
                a.setLightStatus(2);
                timer = lightChangeTime;
                step++;
            } else if (step == 5) {
                b.setLightStatus(1);
                timer = lightChangeTime;
                step++;
            } else if (step == 6) {
                b.setLightStatus(3);
                timer = (int)(in_b*greenMultiplier);
                step++;
            } else if (step == 7) {
                b.setLightStatus(2);
                timer = lightChangeTime;
                step++;
            } else if (step == 9) {
                c.setLightStatus(1);
                timer = lightChangeTime;
                step++;
            } else if (step == 10) {
                c.setLightStatus(3);
                timer = (int)(in_c*greenMultiplier);
                step++;
            } else if (step == 11) {
                c.setLightStatus(2);
                timer = lightChangeTime;
                step++;
            } else if (step == 13) {
                d.setLightStatus(1);
                timer = lightChangeTime;
                step++;
            } else if (step == 14) {
                d.setLightStatus(3);
                timer = (int)(in_d*greenMultiplier);
                step++;
            } else if (step == 15) {
                d.setLightStatus(2);
                timer = lightChangeTime;
                step++;
            }
        }
    }
}
