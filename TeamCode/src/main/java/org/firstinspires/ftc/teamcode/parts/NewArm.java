package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.PartsBackUp;

public class NewArm implements Arm{
    public void up(boolean move) {
        if (move) {
            Parts.inEncoderS = true;
            Parts.inEncoderA = false;

            Parts.slidePose = (Parts.slide.getCurrentPosition() / Parts.slideTPR) - Parts.slideTicksZero;
            Parts.arm.setPower(0.5);
            Parts.slideTicksZero = (Parts.arm.getCurrentPosition() / Parts.pivTPR) * Parts.slideTPR;
            Parts.setSlide = (int)(Parts.slidePose + Parts.slideTicksZero);
            Parts.slide.setTargetPosition(Parts.setSlide);
        }
    }

    public void down(boolean move) {
        if (move) {
            Parts.inEncoderS = true;
            Parts.inEncoderA = false;

            Parts.slidePose = (Parts.slide.getCurrentPosition() / Parts.slideTPR) - Parts.slideTicksZero;
            Parts.arm.setPower(-0.5);
            Parts.slideTicksZero = (Parts.arm.getCurrentPosition() / Parts.pivTPR) * Parts.slideTPR;
            Parts.setSlide = (int)(Parts.slidePose + Parts.slideTicksZero);
            Parts.slide.setTargetPosition(Parts.setSlide);
        }
    }

    public void extend(double power) {
        if (power != 0) {
            Parts.inEncoderS = false;
        }
        PartsBackUp.slide.setPower(power * 0.5);
    }

    public void retract(double power) {
        if (power != 0) {
            Parts.inEncoderS = false;
        }
        PartsBackUp.slide.setPower(-power * 0.5);
    }

    public void setArm(int ticks) {
        
    }

    public void setSlide(int ticks) {

    }

    public void armLims(int low, int high) {

    }

    public void slideLims(int low, int high) {

    }

    public void armGo() {
        if (Parts.inEncoderA) {
            Parts.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.arm.setPower(0.5);
        }
    }

    public void slideGo() {
        if (Parts.inEncoderS) {
            Parts.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.slide.setPower(0.5);
        }
    }
}
