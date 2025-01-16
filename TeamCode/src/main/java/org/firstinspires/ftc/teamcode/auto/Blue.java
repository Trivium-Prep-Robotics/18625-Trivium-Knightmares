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

        arm.up(500);
        arm.extend(500);

        drive.moveRobot(0.5, 0, 0);
        sleep(500);
        drive.moveRobot(0, 0, 0);
        arm.down(100);

        claw.drops();

        sleep(1000);

        
    }
}
