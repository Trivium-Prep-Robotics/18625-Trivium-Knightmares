package org.firstinspires.ftc.teamcode.tunering;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Parts;

@TeleOp(name = "SlideTuner", group = "Tuning")
public class SlideTuner extends LinearOpMode {

    private double sP = Parts.sP;
    private double sI = Parts.sI;
    private double sD = Parts.sD;
    private double targetPosition = 0;
    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        // Wait for the start of the op mode
        waitForStart();
        timer.reset();

        // Main loop to control the motor using PID
        while (opModeIsActive()) {
            // Get the current position of the motor
            double currentPosition = Parts.slide.getCurrentPosition();

            // Calculate the error between the target and current position
            double error = targetPosition - currentPosition;

            // Calculate the integral sum and derivative
            integralSum += error * timer.seconds();
            double derivative = (error - lastError) / timer.seconds();

            // Calculate the output power using PID formula
            double output = sP * error + sI * integralSum + sD * derivative;

            // Set the motor power
            Parts.slide.setPower(output);

            // Update the last error and reset the timer
            lastError = error;
            timer.reset();

            // Send telemetry data to the driver station
            telemetry.addData("Target Position", targetPosition);
            telemetry.addData("Current Position", currentPosition);
            telemetry.addData("Error", error);
            telemetry.addData("Output", output);
            telemetry.addData("kP", sP);
            telemetry.addData("kI", sI);
            telemetry.addData("kD", sD);
            telemetry.update();

            // Adjust PID constants using gamepad buttons
            if (gamepad1.dpad_up) {
                sP += 0.001;
            } else if (gamepad1.dpad_down) {
                sP -= 0.001;
            }

            if (gamepad1.dpad_right) {
                sI += 0.001;
            } else if (gamepad1.dpad_left) {
                sI -= 0.001;
            }

            if (gamepad1.y) {
                sD += 0.001;
            } else if (gamepad1.a) {
                sD -= 0.001;
            }

            // Set target position using gamepad triggers
            if (gamepad1.right_trigger > 0.1) {
                targetPosition += 10;
            } else if (gamepad1.left_trigger > 0.1) {
                targetPosition -= 10;
            }

            if (gamepad1.b) {
                Parts.sP = sP;
                Parts.sI = sI;
                Parts.sD = sD;
            }
        }
    }
}