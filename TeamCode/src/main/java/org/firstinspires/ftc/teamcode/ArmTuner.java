package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "ArmTuner", group = "Tuning")
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
            if (gamepad1.dpad_up) {
                kP += 0.001;
            } else if (gamepad1.dpad_down) {
                kP -= 0.001;
            }

            if (gamepad1.dpad_right) {
                kI += 0.001;
            } else if (gamepad1.dpad_left) {
                kI -= 0.001;
            }

            if (gamepad1.y) {
                kD += 0.001;
            } else if (gamepad1.a) {
                kD -= 0.001;
            }

            // Set target position using gamepad triggers
            if (gamepad1.right_trigger > 0.1) {
                targetPosition += 10;
            } else if (gamepad1.left_trigger > 0.1) {
                targetPosition -= 10;
            }
        }
    }
}