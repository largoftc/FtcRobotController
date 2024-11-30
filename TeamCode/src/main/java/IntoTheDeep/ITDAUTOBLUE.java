package IntoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "ITD Auto Blue Left", group = "Auto")
public class ITDAUTOBLUE extends LinearOpMode {
    ITDhardware robot = ITDhardware.getInstance();

    // Define motors
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    // Encoder counts per inch (adjust based on your motor and wheel configuration)
    static final double COUNTS_PER_MOTOR_REV = 537.6; // Adjust for your motor
    static final double DRIVE_GEAR_REDUCTION = 1.0; // No external gear reduction
    static final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
        robot.init(hardwareMap);
        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "lb");
        backRight = hardwareMap.get(DcMotor.class, "rb");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Reset and set encoders
        resetEncoders();
        setRunToPosition();

        waitForStart();

        // Perform autonomous actions
        driveForward(24, 0.5);  // Move forward 24 inches
        robot.rotater1.setTargetPosition(1000);
        robot.slide1.setTargetPosition(1000);
        robot.rotater1.setTargetPosition(700);
        robot.s1.setPosition(67);
        driveBackwards(12,0.5);
        strafeRight(12, 0.5);   // Strafe right 12 inches
        rotateRight(90, 0.5);
        driveForward(24,0.5);
        driveForward(24,0.5);
        // Rotate 90 degrees to the right

    }

    // Method to reset encoders
    private void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Method to set motors to RUN_TO_POSITION mode
    private void setRunToPosition() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Drive forward/backward based on encoder counts
    private void driveForward(double inches, double power) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() + targetPosition);

        setMotorPower(power);
        waitUntilMotorsReachTarget();
    }
    // Drive forward/backward based on encoder counts
    private void driveBackwards(double inches, double power) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() - targetPosition);

        setMotorPower(power);
        waitUntilMotorsReachTarget();
    }

    // Strafe right/left based on encoder counts
    private void strafeRight(double inches, double power) {
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() + targetPosition);

        setMotorPower(power);
        waitUntilMotorsReachTarget();
    }

    // Rotate right/left based on encoder counts
    private void rotateRight(double degrees, double power) {
        // Convert degrees to inches (approximated value - adjust as needed)
        double inches = degrees * Math.PI / 180 * (WHEEL_DIAMETER_INCHES / 2);
        int targetPosition = (int) (inches * COUNTS_PER_INCH);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + targetPosition);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - targetPosition);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + targetPosition);
        backRight.setTargetPosition(backRight.getCurrentPosition() - targetPosition);

        setMotorPower(power);
        waitUntilMotorsReachTarget();
    }

    // Helper function to set motor power
    private void setMotorPower(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    // Helper function to wait until motors reach the target position
    private void waitUntilMotorsReachTarget() {
        while (opModeIsActive() &&
                (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {
            telemetry.addData("Front Left Position", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right Position", frontRight.getCurrentPosition());
            telemetry.addData("Back Left Position", backLeft.getCurrentPosition());
            telemetry.addData("Back Right Position", backRight.getCurrentPosition());
            telemetry.update();
        }
        setMotorPower(0);  // Stop all motors once the target is reached
    }
}