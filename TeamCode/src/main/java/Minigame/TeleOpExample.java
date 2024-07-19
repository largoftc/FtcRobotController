package Minigame;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp")

public class TeleOpExample extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    boolean manualControl = true;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initTele(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers");
        telemetry.update();

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double scaleFactor = 1;

        waitForStart();
        // DURING TELEOP
        while (opModeIsActive()) {
            telemetry.addData("Lift position", robot.arm.getCurrentPosition());
            telemetry.update();
            double movement = -gamepad1.left_stick_y;
            double turning = gamepad1.right_stick_x;

            double left = movement + turning;
            double right = movement - turning;
            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            if (gamepad1.right_trigger > 0) {
                scaleFactor = Math.max(Math.abs(1 - gamepad1.right_trigger), 0.2);
                left *= scaleFactor;
                right *= scaleFactor;
            }

            robot.demoMotor1.setPower(left);
            robot.demoMotor2.setPower(right);

            if (gamepad2.right_stick_y > 0) {
                robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                manualControl = true;
                robot.arm.setPower(gamepad2.right_stick_y);
            }
            else if (gamepad2.right_stick_y < 0) {
                robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                manualControl = true;
                robot.arm.setPower(gamepad2.right_stick_y);
            }

            else if(manualControl)
                robot.arm.setPower(0);

            if(gamepad2.a) {
                robot.demoServo.setPosition(1);
            }
            if(gamepad2.b) {
                robot.demoServo.setPosition(0.5);
            }
            if (gamepad2.x) {
                robot.demoServo.setPosition(0);

            }
            if (gamepad2.right_bumper) {
                manualControl = false;
                robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.arm.setTargetPosition(1483);
                robot.arm.setPower(1);
            }

        }
    }

}
