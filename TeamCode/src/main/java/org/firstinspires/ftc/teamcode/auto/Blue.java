package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;

@Autonomous (name = "auto")
public class Blue extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap);
        NewArm arm = new NewArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        claw.grabs();

        waitForStart();

        arm.up(2000);
        arm.extend(1200);

        drive.moveRobot(0.5, 0, 0);
        sleep(2000);
        drive.moveRobot(0, 0, 0);
        arm.down(500);


        claw.drops();

        sleep(1000);

        drive.moveRobot(-0.5, 0, 0);
        sleep(2000);
        drive.moveRobot(0, -0.5, 0);
        sleep(2500);
        drive.moveRobot(-0.25, 0, 0);
        sleep(500);
        drive.moveRobot(0, 0, 0);

        arm.retract(1200);
        arm.down(1500);

        
    }
}
