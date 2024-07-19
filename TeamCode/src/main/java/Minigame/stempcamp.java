package Minigame;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class stempcamp extends LinearOpMode {
    public DcMotor left1;
    public DcMotor right1;
   // public Servo servo1;
    //public DcMotor arm;


    @Override
    public void runOpMode() throws InterruptedException {

        left1 = hardwareMap.get(DcMotor.class, "left1");
        right1 = hardwareMap.get(DcMotor.class, "right1");
        //arm = hardwareMap.get(DcMotor.class, "arm");
       // servo1 = hardwareMap.get(Servo.class, "servo1");

        telemetry.addData("BackLeftEncoder", left1.getCurrentPosition());
        telemetry.addData("BackRightEncoder", right1.getCurrentPosition());
        //telemetry.addData("arm", arm.getCurrentPosition());

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            left1.setPower(0);
            right1.setPower(0);
            //arm.setPower(0);

            left1.setDirection(DcMotorSimple.Direction.FORWARD);
            right1.setDirection(DcMotorSimple.Direction.REVERSE);


            if (gamepad1.dpad_up) {
                left1.setDirection(DcMotorSimple.Direction.REVERSE);
                right1.setDirection(DcMotorSimple.Direction.FORWARD);
                left1.setPower(1);
                right1.setPower(1);
            }
            if (gamepad1.dpad_down) {
                left1.setDirection(DcMotorSimple.Direction.FORWARD);
                right1.setDirection(DcMotorSimple.Direction.REVERSE);
                left1.setPower(1);
                right1.setPower(1);
            }
            if (gamepad1.dpad_left) {
                left1.setDirection(DcMotorSimple.Direction.FORWARD);
                right1.setDirection(DcMotorSimple.Direction.FORWARD);
                left1.setPower(.5);
                right1.setPower(.5);
            }
            if (gamepad1.dpad_right) {
                left1.setDirection(DcMotorSimple.Direction.REVERSE);
                right1.setDirection(DcMotorSimple.Direction.REVERSE);left1.setPower(.5);
                right1
                        .setPower(.5);
            }
            if (gamepad1.a) {
                //arm.setDirection(DcMotorSimple.Direction.FORWARD);
                //arm.setPower(1);
            }
            if (gamepad1.b) {
                //arm.setDirection(DcMotorSimple.Direction.REVERSE);
                //arm.setPower(1);
            }
            if (gamepad2.a) {
                //servo1.setPosition(-1);
            }
            if (gamepad2.b) {
                //servo1.setPosition(1);
            }
        }





    }

}
