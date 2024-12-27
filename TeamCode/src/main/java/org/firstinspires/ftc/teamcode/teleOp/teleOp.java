package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;

@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {
    int pivTop = (int)(Parts.pivTPR * 0.25);
    int pivLow = (int)(Parts.pivTPR * 0.00);

    int slideTop = (int)(Parts.slideTPR * 4000);
    int slideLow = (int)(Parts.slideTPR * 0.00);

    public void runOpMode() throws InterruptedException {

        Parts robot = new Parts(hardwareMap);
        NewArm arm = new NewArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        waitForStart();
        Parts.slide.setTargetPosition(0);
        Parts.arm.setTargetPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            Parts.slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Parts.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            // update each loop
            Parts.slideTicksZero = (Parts.arm.getCurrentPosition() / Parts.pivTPR) * Parts.slideTPR;

            /*arm.armLims(pivLow, pivTop);
            arm.slideLims(slideLow, slideTop);*/

            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);
            arm.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);
            arm.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0));

//            claw.grabs(gamepad2.right_bumper);
//            claw.drops(gamepad2.left_bumper);

//            drive.feildCentric(gamepad1);

            /*if (gamepad2.dpad_up) {
                Parts.arm.setPower(1);
            } else if (gamepad2.dpad_down) {
                Parts.arm.setPower(-1);
            } else {
                Parts.arm.setPower(0);
            }*/


            // update every loop
            telemetry.addLine("arm:" + Parts.arm.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());
            telemetry.addLine("slidePos:" + Parts.slidePose);
            telemetry.addLine("slide zero:" + Parts.slideTicksZero);
            telemetry.update();

            arm.armGo();
            arm.slideGo();

        }

    }
}
