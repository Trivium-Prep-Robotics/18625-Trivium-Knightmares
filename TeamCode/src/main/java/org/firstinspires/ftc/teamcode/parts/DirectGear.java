package org.firstinspires.ftc.teamcode.parts;


import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PartsBackUp;

public class DirectGear implements Arm {
    public void up(boolean move) {
        boolean inLims = PartsBackUp.piv1.getCurrentPosition() < PartsBackUp.armHigh && PartsBackUp.piv1.getCurrentPosition() < PartsBackUp.armHigh;

        if (move && inLims) {
            PartsBackUp.piv1.setPower(0.5);
            PartsBackUp.piv2.setPower(0.5);
        } else {
            PartsBackUp.piv1.setPower(0);
            PartsBackUp.piv2.setPower(0);
        }
    }

    public void down(boolean move) {
        boolean inLims = PartsBackUp.piv1.getCurrentPosition() > PartsBackUp.armLow && PartsBackUp.piv1.getCurrentPosition() > PartsBackUp.armLow;

        if (move && inLims) {
            PartsBackUp.piv1.setPower(-0.5);
            PartsBackUp.piv2.setPower(-0.5);
        } else {
            PartsBackUp.piv1.setPower(0);
            PartsBackUp.piv2.setPower(0);
        }
    }

    public void extend(double power) {
        if (PartsBackUp.slide.getCurrentPosition() < PartsBackUp.slideHigh) {
            PartsBackUp.slide.setPower(power * 0.5);
        } else {
            PartsBackUp.slide.setPower(0);
        }

    }

    public void retract(double power) {
        if (PartsBackUp.slide.getCurrentPosition() > PartsBackUp.slideLow) {
            PartsBackUp.slide.setPower(-power * 0.5);
        } else {
            PartsBackUp.slide.setPower(0);
        }

    }

    public void slideStop(boolean stop) {}
    public void armStop(boolean stop) {}

    public void setArm(int ticks) {
        PartsBackUp.setArm = ticks;
        PartsBackUp.piv1.setTargetPosition(PartsBackUp.setArm);
        PartsBackUp.piv2.setTargetPosition(PartsBackUp.setArm);
    }

    public void setSlide(int ticks) {
        PartsBackUp.setSlide = ticks;
        PartsBackUp.slide.setTargetPosition(PartsBackUp.setSlide);
    }

    public void armLims(int low, int high) {
        if (PartsBackUp.piv2.getCurrentPosition() <= low || PartsBackUp.piv1.getCurrentPosition() <= low) {
            PartsBackUp.piv1.setPower(0);
            PartsBackUp.piv2.setPower(0);
        }

        if (PartsBackUp.piv2.getCurrentPosition() >= high || PartsBackUp.piv1.getCurrentPosition() >= high) {
            PartsBackUp.piv1.setPower(0);
            PartsBackUp.piv2.setPower(0);
        }

        PartsBackUp.armLow = low;
        PartsBackUp.armHigh = high;
    }

    public void slideLims(int low, int high) {
        if (PartsBackUp.slide.getCurrentPosition() <= low || PartsBackUp.slide.getCurrentPosition() >= high) {
            PartsBackUp.slide.setPower(0);
        }

        PartsBackUp.slideLow = low;
        PartsBackUp.slideHigh = high;
    }

    public void armGo() {
        if (PartsBackUp.setArm == 0) {
        } else {
            PartsBackUp.piv1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            PartsBackUp.piv2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            PartsBackUp.piv1.setPower(0.5);
            PartsBackUp.piv2.setPower(0.5);
        }

    }

    public void slideGo() {
        if (PartsBackUp.setSlide == 0) {
        } else {
            PartsBackUp.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            PartsBackUp.slide.setPower(0.5);
        }

    }
}
