package Testers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import Testers.OdometryLocalization;

@Autonomous(name = "Odometry Autonomous")
public class OdometryAutonomous extends LinearOpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private OdometryLocalization odometry;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize drivetrain motors
        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "lb");
        backRight = hardwareMap.get(DcMotor.class, "rb");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE );
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);



        // Set up odometry
        odometry = new OdometryLocalization(hardwareMap);

        // Wait for strart
        waitForStart();

        if (opModeIsActive()) {
            // Move to position (24, 24) on the field
            moveToPosition(-10, 0, 0.5);

            // Pause at the position
            sleep(1000);

            // Move to position (0, 0)
            moveToPosition(0, 0, 0.5);
        }
    }

    private void moveToPosition(double targetX, double targetY, double speed) {
        double Kp = 0.1; // Proportional constant for speed control
        while (opModeIsActive()) {
            odometry.update();

            double xError = targetX - odometry.getX();
            double yError = targetY - odometry.getY();

            double distance = Math.sqrt(xError * xError + yError * yError);
            if (distance < 1.0) break; // Stop if within 1 inch of the target

            double targetAngle = Math.atan2(yError, xError);
            double angleError = targetAngle - odometry.getHeading();

            double xPower = Kp * xError;
            double yPower = Kp * yError;
            double turnPower = Kp * angleError;

            drive(xPower, yPower, turnPower * speed);
        }
        drive(0, 0, 0); // Stop the robot
    }

    private void drive(double x, double y, double turn) {
        double frontLeftPower = y + x + turn;
        double frontRightPower = y - x - turn;
        double backLeftPower = y - x + turn;
        double backRightPower = y + x - turn;

        double maxPower = Math.max(1.0, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeft.setPower(frontLeftPower / maxPower);
        frontRight.setPower(frontRightPower / maxPower);
        backLeft.setPower(backLeftPower / maxPower);
        backRight.setPower(backRightPower / maxPower);
    }
}