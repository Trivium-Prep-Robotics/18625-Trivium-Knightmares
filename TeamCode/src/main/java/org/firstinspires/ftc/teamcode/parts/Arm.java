package org.firstinspires.ftc.teamcode.parts;

public interface Arm {
    public void up(boolean move);
    public void down(boolean move);
    public void armStop(boolean stop);
    public void extend(double power);
    public void retract(double power);
    public void slideStop(boolean stop);

    public void setArm(int ticks);
    public void setSlide(int ticks);

    public void armLims();
    public void slideLims();

    public void armGo();
    public void slideGo();
}

