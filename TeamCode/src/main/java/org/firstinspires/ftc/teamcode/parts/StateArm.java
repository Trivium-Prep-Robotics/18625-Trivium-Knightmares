package org.firstinspires.ftc.teamcode.parts;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;

public class StateArm implements Arm {
    /* Set the arm power used */
    public void armPower(double power) {
        Parts.armPower = power;
    }

    /* Set the extend power used */
    public void extendPower(double power) {
        Parts.extendPower = power;
    }

    /* up movement methods for the arm */
    public void up(boolean move) {
        if (move) {
            Parts.setArm += 10;
        }
    }

    public void up(int sec) throws InterruptedException { // TODO: when arm and slide are tuned this will need to change
        Parts.piv1.setPower(Parts.armPower);
        Parts.piv2.setPower(Parts.armPower);
        Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.piv1.setPower(0);
        Parts.piv2.setPower(0);
        Parts.slide.setPower(0);
    }

    /* down movement methods for the arm */
    public void down(boolean move) {
        if (move) {
            Parts.setArm -= 10;
        }
    }

    public void down(int sec) throws InterruptedException { // TODO: when arm and slide are tuned this will need to change
        Parts.piv1.setPower(-Parts.armPower);
        Parts.piv2.setPower(-Parts.armPower);
        Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.piv1.setPower(0);
        Parts.piv2.setPower(0);
        Parts.slide.setPower(0);
    }

    /* method to check to stop the arm */
    public void armStop(boolean stop) {
        if (stop) {
            Parts.piv1.setPower(0);
            Parts.piv2.setPower(0);
        }
    }

    /* extend movement methods for the arm */
    public void extend(double power) {
        if (power != 0 && !Parts.lims) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-Parts.extendPower);
        } else if (power != 0 && (Parts.slidePose > Parts.slideLow || Parts.slidePose == 0)) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-1);
        }
    }

    public void extend(int sec) throws InterruptedException { // TODO: when arm and slide are tuned this will need to change
        Parts.slide.setPower(-Parts.extendPower);
        Thread.sleep(sec);
        Parts.slide.setPower(0);
    }

    /* retract movement methods for the arm */
    public void retract(double power) {
        if (power != 0 && !Parts.lims) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(Parts.extendPower);
        } else if (power != 0 && Parts.slidePose < Parts.slideHigh) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(Parts.extendPower);
        }
    }

    public void retract(int sec) throws InterruptedException { // TODO: when arm and slide are tuned this will need to change
        Parts.slide.setPower(Parts.extendPower);
        Thread.sleep(sec);
        Parts.slide.setPower(0);
    }

    /* method to stop the extention */
    public void slideStop(boolean stop) {
        if (stop) {
            Parts.slide.setPower(0);
        }
    }

    /* setting the arm ticks */
    public void setArm(int ticks) {
        Parts.setArm = ticks * (int)(Parts.pivTPR);
    }

    /* setting the slide ticks */
    public void setSlide(int ticks) {
        Parts.setSlide = ticks * (int)(Parts.slideTPR);
    }

    /* limit methods for the arm and extention */
    public void armLims() {

    }

    public void slideLims() {

    }

    /* methods to start encoders */
    public void armGo() {
        // Get the current position of the motor
        double currentPosition = Parts.piv1.getCurrentPosition();

        // Calculate the error between the target and current position
        double error = Parts.setArm - currentPosition;

        // Calculate the integral sum and derivative
        Parts.integralSum += error * Parts.timer.seconds();
        double derivative = (error - Parts.lastError) / Parts.timer.seconds();

        // Calculate the output power using PID formula
        double output = Parts.kP * error + Parts.kI * Parts.integralSum + Parts.kD * derivative;

        // Set the motor power
        Parts.piv1.setPower(output);
        Parts.piv2.setPower(output);
        Parts.slide.setPower(output * Parts.armToExtend);

        // Update the last error and reset the timer
        Parts.lastError = error;
        Parts.timer.reset();

        // Send telemetry data to the driver station
        telemetry.addData("Target Position", Parts.setArm);
        telemetry.addData("Current Position", currentPosition);
        telemetry.addData("Error", error);
        telemetry.addData("Output", output);
    }


    public void slideGo() {

    }

}