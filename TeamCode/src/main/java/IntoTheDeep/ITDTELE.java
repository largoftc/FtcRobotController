package IntoTheDeep;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import Minigame.Hardware;
@TeleOp (name = "ITD2025")
public class ITDTELE extends LinearOpMode {
    public int currentPosition = 0;
    public int liftPosition = 0;
    ITDhardware robot = ITDhardware.getInstance();
    boolean manualControl = true;
    private static final double SLOW_MODE_MULTIPLIER = 0.3;
    @Override
    public void runOpMode() throws InterruptedException {
        robot.initTele(hardwareMap);
        robot.slide1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rotater1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Hello, Drivers");
        telemetry.update();

        robot.lb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.lf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rb.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.rotater1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (isStopRequested()) return;
        waitForStart();

        robot.slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rotater1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftPosition = 0;
        currentPosition = 0;


        while (opModeIsActive()) {
            telemetry.addData("Lift position", robot.slide1.getCurrentPosition());
            telemetry.addData("Rotater Position", robot.rotater1.getCurrentPosition());
            telemetry.update();

            double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            boolean slowMode = gamepad1.right_bumper;
            double speedMultiplier = slowMode ? SLOW_MODE_MULTIPLIER : 1.0;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double leftFrontPower = (y + x + rx) / denominator * speedMultiplier;
            double leftBackPower = (y - x + rx) / denominator * speedMultiplier;
            double rightFrontPower = (y - x - rx) / denominator * speedMultiplier;
            double rightBackPower = (y + x - rx) / denominator * speedMultiplier;
                                                                                                                                      robot.lf.setPower(leftFrontPower);
            robot.lb.setPower(leftBackPower);
            robot.rb.setPower(rightBackPower);
            robot.rf.setPower(rightFrontPower);
            robot.lf.setPower(leftFrontPower);


           // robot.act1.setPower(0);

            if (gamepad2.right_stick_y > 0) {
                robot.rotater1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                manualControl = true;
                robot.rotater1.setPower(gamepad2.right_stick_y/5);
                currentPosition = robot.rotater1.getCurrentPosition();

            }
            else if (gamepad2.right_stick_y < 0) {
                    robot.rotater1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    manualControl = true;
                    robot.rotater1.setPower(gamepad2.right_stick_y/5);
                    currentPosition = robot.rotater1.getCurrentPosition();
            }
            else {
                robot.rotater1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.rotater1.setTargetPosition(currentPosition);
                robot.rotater1.setPower(.5);
            }

            if (gamepad2.left_stick_x > 0) {
                robot.slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                manualControl = true;
                robot.slide1.setPower(gamepad2.left_stick_x);
                liftPosition = robot.slide1.getCurrentPosition();
            }
            else if (gamepad2.left_stick_x < 0) {
                robot.slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                manualControl = true;
                robot.slide1.setPower(gamepad2.left_stick_x);
                liftPosition = robot.slide1.getCurrentPosition();

            }
            else {
                robot.slide1.setTargetPosition(liftPosition);
                robot.slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.slide1.setPower(.5);
            }
/*
            if (gamepad1.a) {
                manualControl = true;
                robot.act1.setPower(1);
                robot.act1.setDirection(DcMotorSimple.Direction.FORWARD);
            }
/*
            if (gamepad1.b) {
                manualControl = true;
                robot.act1.setPower(1);
                robot.act1.setDirection(DcMotorSimple.Direction.REVERSE);
            }

 */
            if (gamepad2.a) {
                manualControl = true;
                robot.s1.setPosition(.67);
            }
            if (gamepad2.dpad_up) {
                manualControl = true;

                robot.s2.setPosition(.97);
            }

            if (gamepad2.right_bumper) {
                manualControl = true;
                robot.s1.setPosition(0.43);           }
            if (gamepad2.left_bumper) {
                manualControl = true;
                robot.s2.setPosition(.55);
            }
            if (gamepad2.dpad_down){
                manualControl = true;
                robot.s2.setPosition(0);
            }
;
            }
            if (gamepad2.dpad_left) {
                manualControl = true;
                robot.s2.setPosition(97);
        }
    }
}
