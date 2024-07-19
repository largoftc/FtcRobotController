package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp

public abstract class Main extends LinearOpMode {
    public DcMotor leftFrontDrive;
    public DcMotor rightFrontDrive;
    public DcMotor leftBackDrive;
    public DcMotor rightBackDrive;
    public DcMotor arm;
    public DcMotor arm2;
    public Servo  servo1;
    public Servo servo2;
    public DcMotor airbus;




        public void initHardware () {
            DcMotor leftFrontDrive = hardwareMap.dcMotor.get("leftFrontDrive");
            DcMotor rightFrontDrive = hardwareMap.dcMotor.get("rightFrontDrive");
            DcMotor rightBackDrive = hardwareMap.dcMotor.get("rightBackDrive");
            DcMotor leftBackDrive = hardwareMap.dcMotor.get("leftBackDrive");
            DcMotor airbus = hardwareMap.dcMotor.get("airbus");
            DcMotor arm = hardwareMap.dcMotor.get("arm");
            DcMotor arm2 = hardwareMap.dcMotor.get("arm2");
            leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBackDrive.setDirection(DcMotorSimple.Direction.REVERSE);


            Servo servo1 = hardwareMap.get(Servo.class, "servo1");
            Servo servo2= hardwareMap.get(Servo.class, "servo2");

        }
}
