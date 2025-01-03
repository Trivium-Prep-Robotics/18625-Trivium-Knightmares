package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;

public class NewArm implements Arm{
    public void up(boolean move) {
        if (move) {
           Parts.arm.setPower(1);
           Parts.slide.setPower(0.4);
        }
    }

    public void down(boolean move) {
        if (move) {
            Parts.arm.setPower(-1);
            Parts.slide.setPower(-0.4);
        }
    }

    public void armStop(boolean stop) {
        if (stop) {
            Parts.arm.setPower(0);
        }
    }

    public void extend(double power) {
        if (power != 0/* && Parts.slidePose < Parts.slideHigh*/) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-1);
        }
    }

    public void retract(double power) {
        if (power != 0/* && Parts.slidePose > Parts.slideLow*/) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(1);
        }
    }

    public void slideStop(boolean stop) {
        if (stop) {
            Parts.slide.setPower(0);
        }
    }

    public void setArm(int ticks) {
        Parts.inEncoderA = true;

        Parts.setArm = ticks;
    }

    public void setSlide(int ticks) {
        Parts.inEncoderS = true;

        Parts.setSlide = (int)(ticks);

    }

    public void armLims(int low, int high) {
        Parts.inEncoderA = true;

        if (Parts.arm.getCurrentPosition() <= low) {
            Parts.setArm = low;
        } else if (Parts.arm.getCurrentPosition() >= high) {
            Parts.setArm = high;
        } else {
            Parts.inEncoderA = false;
        }

        Parts.armLow = low;
        Parts.armHigh = high;
    }

    public void slideLims(int low, int high) {
        Parts.inEncoderA = true;

        if (Parts.slidePose <= low) {
            Parts.setSlide = low;
        } else if (Parts.slidePose >= high) {
            Parts.setSlide = high;
        } else {
            Parts.inEncoderA = false;
        }

        Parts.slideLow = low;
        Parts.slideHigh = high;
    }

    public void armGo() {
        if (Parts.inEncoderA) {
            Parts.arm.setTargetPosition(Parts.setSlide);
            Parts.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.arm.setPower(1);
        }

    }

    public void slideGo() {
        if (Parts.inEncoderS) {
            Parts.slide.setTargetPosition(Parts.setSlide);
            Parts.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.slide.setPower(1);
            if (Parts.slide.getCurrentPosition() > Parts.setSlide - 1 || Parts.slide.getCurrentPosition() < Parts.setSlide + 1) {
                Parts.inEncoderS = false;
            }
        }
    }
}
