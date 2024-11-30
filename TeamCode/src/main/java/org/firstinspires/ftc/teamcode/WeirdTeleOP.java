package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class WeirdTeleOP extends LinearOpMode {
    double r =  Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_x);
    double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;

    double rightX = gamepad1.right_stick_x * .9;

    double v1 = r * Math.cos(robotAngle) + rightX;
    double v2 = r * Math.sin(robotAngle) - rightX;
    double v3 = r * Math.sin(robotAngle) + rightX;
    double v4 = r * Math.cos(robotAngle) - rightX;

    double scaleFactor = 1;




    @Override
    public void runOpMode() throws InterruptedException {
        double r =  Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_x);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;

        double rightX = gamepad1.right_stick_x * .9;

        double v1 = r * Math.cos(robotAngle) + rightX;
        double v2 = r * Math.sin(robotAngle) - rightX;
        double v3 = r * Math.sin(robotAngle) + rightX;
        double v4 = r * Math.cos(robotAngle) - rightX;



    }

}
