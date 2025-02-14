package org.firstinspires.ftc.teamcode.tunering;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Parts;

@TeleOp(name = "PrintConstants", group = "Tuning")
public class PrintConstants extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Wait for the start of the op mode
        waitForStart();

        while (opModeIsActive()) {
            // Print the constants
            telemetry.addData("Arm Power", Parts.armPower);
            telemetry.addData("kP", Parts.kP);
            telemetry.addData("kI", Parts.kI);
            telemetry.addData("kD", Parts.kD);
            telemetry.addData("Extend Power", Parts.extendPower);
            telemetry.addData("kP", Parts.sP);
            telemetry.addData("kI", Parts.sI);
            telemetry.addData("kD", Parts.sD);
            telemetry.update();
        }
    }
}
