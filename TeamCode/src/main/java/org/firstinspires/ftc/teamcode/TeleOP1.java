package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "teleop")
public class TeleOP1 extends LinearOpMode {
    Hardware1 robot = Hardware1.getInstance();
    boolean manualControl = true;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initTele(hardwareMap);
        telemetry.addData("Status", "Hellow Drivers");

        robot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rightMotor.setMode((DcMotor.RunMode.RUN_WITHOUT_ENCODER));
        double scaleFactor = 1;

        waitForStart();
        while (opModeIsActive()) {
            double movement = gamepad1.left_stick_y;
            double turning = gamepad1.right_stick_x;

            double left = movement + turning;
            double right  = movement - turning;
            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;

            }
            robot.leftMotor.setPower(left);
            robot.rightMotor.setPower(right);

            if (gamepad1.a) {
                robot.servo1.setPosition(0);
            }





        }


    }
}
