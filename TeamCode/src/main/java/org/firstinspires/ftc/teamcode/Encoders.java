package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Encoders extends LinearOpMode {

    DcMotor arm;
    public void runOpMode() throws InterruptedException {
        int ticksPerRev = 500;

        arm.setTargetPosition((int)(500 * 0.25));
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(1);

    }
}
