package org.firstinspires.ftc.teamcode.tunering;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Parts;

@TeleOp(name = "Arm PID", group = "Tuning")
public class ArmPID extends LinearOpMode {

    // Declare the motor and PID coefficients


    private double targetPosition = 0;
    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        // Reset and set the motor to run using the encoder
        Parts.piv1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Parts.piv1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Gamepad currentGamepad = new Gamepad();
        Gamepad previousGamepad =  new Gamepad();

        // Wait for the start of the op mode
        waitForStart();
        timer.reset();

        // Main loop to control the motor using PID
        while (opModeIsActive()) {
            previousGamepad.copy(currentGamepad);
            currentGamepad.copy(gamepad1);
            // Check if the gamepad button is pressed
            if (currentGamepad.a && !previousGamepad.a) {
                // Set the target position to 0
                targetPosition = 0;
            } else if (currentGamepad.b && !previousGamepad.b) {
                // Set the target position to 90
                targetPosition = 0.15 * Parts.pivTPR;
            } else if (currentGamepad.x && !previousGamepad.x) {
                // Set the target position to 180
                targetPosition = 0.25  * Parts.pivTPR;
            }
            // Get the current position of the motor
            double currentPosition = Parts.piv1.getCurrentPosition();

            // Calculate the error between the target and current position
            double error = targetPosition - currentPosition;

            // Calculate the integral sum and derivative
            integralSum += error * timer.seconds();
            double derivative = (error - lastError) / timer.seconds();

            // Calculate the output power using PID formula
            double output = Parts.kP * error + Parts.kI * integralSum + Parts.kD * derivative;

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