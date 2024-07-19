package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumTeleOp;

@TeleOp
public class launch extends OpMode{
    public HardwareMap hardwareMap;
  public  Servo servo1;
   public  Servo servo2;



    @Override
    public void init() {
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            servo1.setPosition(1);
            servo2.setPosition(0);
        }
    }
}

