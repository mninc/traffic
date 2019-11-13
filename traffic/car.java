import greenfoot.*;

public class car extends Actor
{
    public char r;
    public char d;
    private boolean pastLights;
    private boolean turned;
    private int queuePos;
    private int mt;
    
    public void init() {
        junction w = (junction)getWorld();
        
        int total = w.in_a + w.in_b + w.in_c + w.in_d;
        int a_bound = w.in_a - 1;
        int b_bound = a_bound + w.in_b;
        int c_bound = b_bound + w.in_c;
        int rd = Greenfoot.getRandomNumber(total);
        if (rd <= a_bound) {
            r = 'a';
            w.a.carsQueued++;
            queuePos = w.a.carsQueued;
        } else if (rd <= b_bound) {
            r = 'b';
            w.b.carsQueued++;
            queuePos = w.b.carsQueued;
        } else if (rd <= c_bound) {
            r = 'c';
            w.c.carsQueued++;
            queuePos = w.c.carsQueued;
        } else {
            r = 'd';
            w.d.carsQueued++;
            queuePos = w.d.carsQueued;
        }
        
        total = w.out_a + w.out_b + w.out_c + w.out_d;
        a_bound = w.out_a - 1;
        b_bound = a_bound + w.out_b;
        c_bound = b_bound + w.out_c;
        d = r;
        while (d == r) {
            int dd = Greenfoot.getRandomNumber(total);
            if (dd <= a_bound) d = 'a';
            else if (dd <= b_bound) d = 'b';
            else if (dd <= c_bound) d = 'c';
            else d = 'd';
        }
        
        pastLights = false;
        turned = false;
        
        startingPos();
    }
    
    private void startingPos() {
        if (r == 'a') {
            setLocation(569, 5);
            setRotation(90);
        } else if (r == 'b') {
            setLocation(1075, 363);
            setRotation(180);
        } else if (r == 'c') {
            setLocation(527, 715);
            setRotation(270);
        } else if (r == 'd') {
            setLocation(10, 322);
            setRotation(0);
        }
    }
    
    public void act() 
    {
        junction w = (junction)getWorld();
        int step = w.step;
        if (pastLights) {
            move(1);
            if (!turned) {
                if ((r == 'a' && d == 'c') || (r == 'b' && d == 'd') || (r == 'c' && d == 'a') || (r == 'd' && d == 'b')) {
                    turned = true;
                    return;
                }
                if ((r == 'a' || r == 'c') && d == 'b' && getY() == 322) {
                    turned = true;
                    setRotation(0);
                } else if ((r == 'a' || r == 'c') && d == 'd' && getY() == 363) {
                    turned = true;
                    setRotation(180);
                } else if ((r == 'b' || r == 'd') && d == 'a' && getX() == 527) {
                    turned = true;
                    setRotation(270);
                } else if ((r == 'b' || r == 'd') && d == 'c' && getX() == 569) {
                    turned = true;
                    setRotation(90);
                }
            }
        } else {
            if (r == 'a') {
                int moveTo = 213 - (queuePos - w.a.carsPassed - 1)*62;
                if (getY() < moveTo || step == 3) move (1);
                if (getY() > 213) {
                    pastLights = true;
                    w.a.carsPassed++;
                }
            } else if (r == 'b') {
                int moveTo = 690 + (queuePos - w.b.carsPassed - 1)*62;
                if (getX() > moveTo || step == 7) move(1);
                if (getX() < 690) {
                    pastLights = true;
                    w.b.carsPassed++;
                }
            } else if (r == 'c') {
                int moveTo = 478 + (queuePos - w.c.carsPassed - 1)*62;
                if (getY() > moveTo || step == 11) move(1);
                if (getY() < 478) {
                    pastLights = true;
                    w.c.carsPassed++;
                }
            } else if (r == 'd') {
                int moveTo = 408 - (queuePos - w.d.carsPassed - 1)*62;
                mt = moveTo;
                if (getX() < moveTo || step == 15) move(1);
                if (getX() > 408) {
                    pastLights = true;
                    w.d.carsPassed++;
                }
            }
        }   
        
        if (isAtEdge()) getWorld().removeObject(this);
    }    
}
