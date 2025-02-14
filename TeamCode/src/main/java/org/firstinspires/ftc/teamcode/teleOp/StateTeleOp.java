package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.StateArm;

public class StateTeleOp extends LinearOpMode {

    public void runOpMode() throws InterruptedException {

        // all the used classes
        Parts robot = new Parts(hardwareMap);
        StateArm arm = new StateArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        waitForStart(); // initialize

        // set the power used for arm motors to 1
        arm.armPower(1);
        arm.extendPower(1);

        // set claw open and close positions
        claw.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
        claw.sampSpecPose(0.075, 0.55);

        while (opModeIsActive()) {

            // arm controls
            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);

            // extend controls
            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);

            // claw controls
            claw.grabs(gamepad2.right_bumper);
            claw.drops(gamepad2.left_bumper);

            // wrist controls
            claw.specimen(gamepad2.y);
            claw.sample(gamepad2.a);

            // drive controls
            drive.feildCentric(gamepad1);

            // run arm
            telemetry.addLine("Running arm");
            arm.armGo();
            telemetry.addLine("Running slide");
            arm.slideGo();
            telemetry.update();
        }
    }
}
