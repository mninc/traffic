import greenfoot.*;

public class menu extends World
{
    private input in_a;
    private int in_a_val = 75;
    private input in_b;
    private int in_b_val = 10;
    private input in_c;
    private int in_c_val = 10;
    private input in_d;
    private int in_d_val = 5;
    
    private input out_a;
    private int out_a_val = 8;
    private input out_b;
    private int out_b_val = 40;
    private input out_c;
    private int out_c_val = 30;
    private input out_d;
    private int out_d_val = 22;
    
    private input spawn_rate;
    private int spawn_rate_val = 300;
    
    private input start;
    
    public menu()
    {    
        super(1080, 720, 1);
        
        in_a = newInput("a.png", 238, 371);
        in_b = newInput("b.png", 238, 401);
        in_c = newInput("c.png", 238, 431);
        in_d = newInput("d.png", 237, 462);
        
        out_a = newInput("a.png", 238, 581);
        out_b = newInput("b.png", 238, 612);
        out_c = newInput("c.png", 238, 645);
        out_d = newInput("d.png", 237, 676);
        
        spawn_rate = newInput("spawn rate.png", 684, 331);
        
        start = newInput("start.png", 721, 582);
    }
    
    private input newInput (String image, int x, int y) {
        input in = new input(image);
        addObject(in, x, y);
        return in;
    }
    
    private int getInput(String text, int cur) {
        String response = Greenfoot.ask(text);
        int ret;
        try {
            ret = Integer.parseInt(response);
        } catch (java.lang.NumberFormatException e) {
            ret = cur;
        }
        return ret;
    }
    
    private boolean c(Actor a) {
        return Greenfoot.mouseClicked(a);
    }
    
    public void act() {
        if (c(in_a)) in_a_val = getInput("Proportion of cars spawning on road A and how long light A stays green?", in_a_val);
        else if (c(in_b)) in_b_val = getInput("Proportion of cars spawning on road B and how long light B stays green?", in_b_val);
        else if (c(in_c)) in_c_val = getInput("Proportion of cars spawning on road C and how long light C stays green?", in_c_val);
        else if (c(in_d)) in_d_val = getInput("Proportion of cars spawning on road D and how long light D stays green?", in_d_val);
        else if (c(out_a)) out_a_val = getInput("Proportion of cars leaving via road A?", out_a_val);
        else if (c(out_b)) out_b_val = getInput("Proportion of cars leaving via road B?", out_b_val);
        else if (c(out_c)) out_c_val = getInput("Proportion of cars leaving via road C?", out_c_val);
        else if (c(out_d)) out_d_val = getInput("Proportion of cars leaving via road D?", out_d_val);
        else if (c(spawn_rate)) spawn_rate_val = getInput("Car spawn rate? A car will spawn after this number of acts have passed, so a higher number means fewer cars spawning", spawn_rate_val);
        else if (c(start)) Greenfoot.setWorld(new junction(in_a_val, in_b_val, in_c_val, in_d_val, out_a_val, out_b_val, out_c_val, out_d_val, spawn_rate_val));
        
        showText(String.valueOf(in_a_val), 280, 371);
        showText(String.valueOf(in_b_val), 277, 400);
        showText(String.valueOf(in_c_val), 273, 433);
        showText(String.valueOf(in_d_val), 276, 463);

        showText(String.valueOf(out_a_val), 279, 581);
        showText(String.valueOf(out_b_val), 274, 611);
        showText(String.valueOf(out_c_val), 274, 648);
        showText(String.valueOf(out_d_val), 273, 678);

        showText(String.valueOf(spawn_rate_val), 822, 331);
    }
}
