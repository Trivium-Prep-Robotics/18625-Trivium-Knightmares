package org.firstinspires.ftc.teamcode.parts;

public interface Arm {
    public void up(boolean move);
    public void down(boolean move);
    public void extend(boolean power);
    public void retract(double power);

    public void setArm(int ticks);
    public void setSlide(int ticks);

    public void armLims(int low, int high);
    public void slideLims(int low, int high);

    public void armGo();
    public void slideGo();
}

