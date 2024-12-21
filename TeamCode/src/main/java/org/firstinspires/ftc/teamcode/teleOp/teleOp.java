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
            Parts.slidePose = (Parts.slide.getCurrentPosition() / Parts.slideTPR) - Parts.slideTicksZero;

            /*arm.armLims(pivLow, pivTop);
            arm.slideLims(slideLow, slideTop);*/

//            arm.up(gamepad2.dpad_up);
//            arm.down(gamepad2.dpad_down);
            arm.extend(gamepad2.left_bumper);
            arm.retract(gamepad2.left_trigger);

//            claw.grabs(gamepad2.right_bumper);
//            claw.drops(gamepad2.left_bumper);

//            drive.feildCentric(gamepad1);

            // update every loop
            telemetry.addLine("arm:" + Parts.arm.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());
            telemetry.update();

            arm.armGo();
            arm.slideGo();

        }

    }
}
