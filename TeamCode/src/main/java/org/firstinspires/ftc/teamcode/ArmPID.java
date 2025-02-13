package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "ArmPID", group = "PID")
public class ArmPID extends LinearOpMode {

    // Declare the motor and PID coefficients

    private double kP = 0.1;
    private double kI = 0.01;
    private double kD = 0.01;
    private double targetPosition = 0;
    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Reset and set the motor to run using the encoder
        Parts.piv1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Parts.piv1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the start of the op mode
        waitForStart();
        timer.reset();

        // Main loop to control the motor using PID
        while (opModeIsActive()) {
            // Get the current position of the motor
            double currentPosition = Parts.piv1.getCurrentPosition();

            // Calculate the error between the target and current position
            double error = targetPosition - currentPosition;

            // Calculate the integral sum and derivative
            integralSum += error * timer.seconds();
            double derivative = (error - lastError) / timer.seconds();

            // Calculate the output power using PID formula
            double output = kP * error + kI * integralSum + kD * derivative;

            // Set the motor power
            Parts.piv1.setPower(output);
            Parts.piv2.setPower(output);
            Parts.slide.setPower(output * Parts.armToExtend);

            // Update the last error and reset the timer
            lastError = error;
            timer.reset();

            // Send telemetry data to the driver station
            telemetry.addData("Target Position", targetPosition);
            telemetry.addData("Current Position", currentPosition);
            telemetry.addData("Error", error);
            telemetry.addData("Output", output);
            telemetry.update();
        }
    }
}