package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;

@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap);
        NewArm arm = new NewArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        waitForStart();

        while (opModeIsActive()) {

            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);
            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);

            claw.grabs(gamepad2.right_bumper);
            claw.drops(gamepad2.left_bumper);

            drive.feildCentric(gamepad1);

            // update every loop
            arm.armGo();
            arm.slideGo();

        }

    }
}
