package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;

public class NewArm implements Arm{
    public void up(boolean move) {
        if (move && !Parts.lims) {
            Parts.arm.setPower(1);
            Parts.slide.setPower(0.4);
        } else if (move && (Parts.arm.getCurrentPosition() < Parts.armHigh || Parts.arm.getCurrentPosition() == 0)) {
            Parts.arm.setPower(1);
            Parts.slide.setPower(0.4);
        }
    }

    public void down(boolean move) {
        if (move && !Parts.lims) {
            Parts.arm.setPower(-1);
            Parts.slide.setPower(-0.4);
        } else if (move && Parts.arm.getCurrentPosition() > Parts.armLow) {
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

        Parts.setSlide = (int)(ticks + Parts.slideTicksZero);

    }

    public void armLims() {
        if ((Parts.arm.getCurrentPosition() < Parts.armHigh || Parts.arm.getCurrentPosition() < Parts.armHigh) && (Parts.arm.getCurrentPosition() > Parts.armLow)) {
            Parts.inEncoderA = false;
        } else if (Parts.arm.getCurrentPosition() < Parts.armLow) {
            Parts.inEncoderA = true;
            Parts.setArm = Parts.armLow;
        } else if (Parts.arm.getCurrentPosition() > Parts.armHigh) {
            Parts.inEncoderA = true;
            Parts.setArm = Parts.armHigh;
        } else {
            Parts.inEncoderA = false;
        }
    }

    public void slideLims() {
        Parts.inEncoderA = true;

        if (Parts.slidePose < Parts.slideLow) {
            Parts.setSlide = Parts.slideLow;
        } else if (Parts.slidePose > Parts.slideHigh) {
            Parts.setSlide = Parts.slideHigh;
        } else {
            Parts.inEncoderA = false;
        }
    }

    public void armGo() {
        if (Parts.inEncoderA) {
            Parts.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            Parts.arm.setTargetPosition(Parts.setArm);
            Parts.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.arm.setPower(1);
            if (Parts.arm.getCurrentPosition() > Parts.setArm - 1 || Parts.arm.getCurrentPosition() < Parts.setArm + 1) {
                Parts.inEncoderA = false;
            }

            Parts.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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
