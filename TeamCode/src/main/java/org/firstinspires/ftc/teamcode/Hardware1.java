package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import Minigame.Hardware;

public class Hardware1 {
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public Servo servo1;

    private static Hardware1 myInstance = null;

    public static Hardware1 getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware1();

        }
        return myInstance;
    }
    public void init(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "leftMMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftMotor.setPower(0);

        rightMotor = hwMap.get(DcMotor.class, "rightMMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightMotor.setPower(0);
    }

    public void initTele(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "leftMMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftMotor.setPower(0);

        rightMotor = hwMap.get(DcMotor.class, "rightMMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightMotor.setPower(0);

    }
}

