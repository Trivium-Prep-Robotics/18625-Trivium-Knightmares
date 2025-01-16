package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;

@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {


    public void runOpMode() throws InterruptedException {

        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad2 = new Gamepad();

        Parts robot = new Parts(hardwareMap);
        NewArm arm = new NewArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        waitForStart();
        Parts.slide.setTargetPosition(0);
        Parts.arm.setTargetPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            // update each loop
            Parts.slideTicksZero = Parts.arm.getCurrentPosition() * 0.1;
            Parts.slidePose = (Parts.slide.getCurrentPosition() - Parts.slideTicksZero);

            if (currentGamepad2.dpad_left && !previousGamepad2.dpad_left) {
                if (Parts.lims) {
                    Parts.lims = false;
                } else {
                    Parts.lims = true;
                }
            }

            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);
            arm.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);
            arm.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            claw.grabs(gamepad2.right_bumper);
            claw.drops(gamepad2.left_bumper);

            drive.feildCentric(gamepad1);


            // update every loop
            telemetry.addLine("arm:" + Parts.arm.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());
            telemetry.addLine("slidePos:" + Parts.slidePose);
            telemetry.addLine("slide zero:" + Parts.slideTicksZero);
            telemetry.update();

//            arm.armGo();
//            arm.slideGo();

        }

    }
}
