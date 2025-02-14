package org.firstinspires.ftc.teamcode.tunering;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Parts;

@TeleOp(name = "Arm Tuner", group = "Tuning")
public class ArmTuner extends LinearOpMode {

    private double kP = Parts.kP;
    private double kI = Parts.kI;
    private double kD = Parts.kD;
    private double targetPosition = 0;
    private double integralSum = 0;
    private double lastError = 0;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        Gamepad currentGamepad = new Gamepad();
        Gamepad previousGamepad =  new Gamepad();

        // Wait for the start of the op mode
        waitForStart();
        timer.reset();

        // Main loop to control the motor using PID
        while (opModeIsActive()) {
            previousGamepad.copy(currentGamepad);
            currentGamepad.copy(gamepad1);

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
            telemetry.addData("kP", kP);
            telemetry.addData("kI", kI);
            telemetry.addData("kD", kD);
            telemetry.update();

            // Adjust PID constants using gamepad buttons
            if (currentGamepad.dpad_up && !previousGamepad.dpad_up) {
                kP += 0.001;
            } else if (gamepad1.dpad_down && !previousGamepad.dpad_down) {
                kP -= 0.001;
            }

            if (currentGamepad.dpad_right && !previousGamepad.dpad_right) {
                kI += 0.001;
            } else if (currentGamepad.dpad_left && !previousGamepad.dpad_left) {
                kI -= 0.001;
            }

            if (currentGamepad.y && !previousGamepad.y) {
                kD += 0.001;
            } else if (currentGamepad.a && !previousGamepad.a) {
                kD -= 0.001;
            }

            // Set target position using gamepad triggers
            if (gamepad1.right_trigger > 0.1) {
                targetPosition += 10;
            } else if (gamepad1.left_trigger > 0.1) {
                targetPosition -= 10;
            }

            if (gamepad1.b) {
                Parts.kP = kP;
                Parts.kI = kI;
                Parts.kD = kD;
            }
        }
    }
}