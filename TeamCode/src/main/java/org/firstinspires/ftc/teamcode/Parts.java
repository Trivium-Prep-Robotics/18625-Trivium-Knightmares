package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Parts {

    // declaring parts
    public static DcMotor FR, FL, BR, BL;
    public static DcMotor piv1, piv2, arm;
    public static DcMotor slide;
    public static Servo claw, wrist;

    public static IMU imu;
    IMU.Parameters myIMUparameters;

    /**
     * important variables
     */
    public static double ticksPerRev = 5281.1;
    public static double pivTPR = 5 * ticksPerRev;
    public static double slideTPR = 2 * ticksPerRev;


    public static int setArm = 0;
    public static int setSlide = 0;


    public static double slideTicksZero = 0;
    public static double slidePose = 0;

    public static boolean inEncoderS = false;
    public static boolean inEncoderA = false;

    public static double armPower = 1;
    public static double extendPower = 1;
    public static double armToExtend = 0.1;

    public static double openClaw;
    public static double closeClaw;

    public static double sample;
    public static double specimen;

    public static double kP = 0.1;
    public static double kI = 0.01;
    public static double kD = 0.01;
    public static double integralSum = 0;
    public static ElapsedTime timer = new ElapsedTime();
    public static double lastError = 0;

    public static double sP = 0.1;
    public static double sI = 0.01;
    public static double sD = 0.01;
    public static double SintegralSum = 0;
    public static double SlastError = 0;
    // useless right now
    public static int armLow;
    public static int armHigh;
    public static int slideHigh;
    public static int slideLow;
    public static boolean lims = false;


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

        // assigning arm pivot motors
        piv1 = hardwareMap.get(DcMotor.class, "pivotOne");
        piv2 = hardwareMap.get(DcMotor.class, "pivotTwo");

        piv1.setDirection(DcMotorSimple.Direction.REVERSE);
        piv2.setDirection(DcMotorSimple.Direction.REVERSE);

        piv1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        piv2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        piv1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        piv2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // assigning slides
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // when setPower(0) -> motors brake
        piv1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        piv2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // assigning claw and wrist servo
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(ServoImplEx.class, "wrist");

        // IMU
        imu = hardwareMap.get(IMU.class, "imu"); // Initializing IMU in Drivers Hub
        // Reconfiguring IMU orientation
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                )
        );
        imu.initialize(myIMUparameters);
        imu.resetYaw();
    }
}
