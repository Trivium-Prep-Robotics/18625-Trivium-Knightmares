package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Parts {

    // declaring parts
    public static DcMotor FR, FL, BR, BL;
    public static DcMotor arm;
    public static DcMotor slide;
    public static Servo claw;

    public static IMU imu;
    IMU.Parameters myIMUparameters;

    public static int armLow;
    public static int armHigh;
    public static int slideLow;
    public static int slideHigh;

    public static int setArm = 0;
    public static int setSlide = 0;

    public static double ticksPerRev = 5281.1;
    public static double pivTPR = 10 * ticksPerRev;
    public static double slideTPR = 2 * ticksPerRev;

    public static double slideTicksZero = 0;
    public static double slidePose = 0;

    public static boolean inEncoderS = false;
    public static boolean inEncoderA = false;

    public Parts(HardwareMap hardwareMap) {

        // assigning drive train motors
        FR = hardwareMap.get(DcMotor.class, "rightFront");
        FL = hardwareMap.get(DcMotor.class, "leftFront");
        BR = hardwareMap.get(DcMotor.class, "rightBack");
        BL = hardwareMap.get(DcMotor.class, "leftBack");

        // reverse left side
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        // when setPower(0) -> motors brake
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // reverse
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        // when setPower(0) -> motors brake
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.get(Servo.class, "claw");

        // IMU
        imu = hardwareMap.get(IMU.class, "imu"); // Initializing IMU in Drivers Hub
        // Reconfiguring IMU orientation
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );
        imu.initialize(myIMUparameters);
        imu.resetYaw();
    }
}
