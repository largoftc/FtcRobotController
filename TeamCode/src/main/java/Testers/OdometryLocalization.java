package Testers;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OdometryLocalization {
    private DcMotorEx leftEncoder, rightEncoder, horizontalEncoder;

    private int leftEncoderPos, rightEncoderPos, horizontalEncoderPos;

    private static final double WHEEL_DIAMETER = 2.0; // Inches
    private static final double TICKS_PER_REV = 8192; // Example for REV encoders
    private static final double INCHES_PER_TICK = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;

    private static final double LATERAL_DISTANCE = 13; // Distance between left and right wheels (inches)
    private static final double FORWARD_OFFSET = 7.5;    // Offset of horizontal encoder from robot center (inches)

    private double xPos = 0.0, yPos = 0.0, heading = 0.0;

    public OdometryLocalization(HardwareMap hardwareMap) {
        leftEncoder = hardwareMap.get(DcMotorEx.class, "rb");
        rightEncoder = hardwareMap.get(DcMotorEx.class, "rf");
        horizontalEncoder = hardwareMap.get(DcMotorEx.class, "lf");

        leftEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        horizontalEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        horizontalEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void update() {
        int newLeftPos = leftEncoder.getCurrentPosition();
        int newRightPos = rightEncoder.getCurrentPosition();
        int newHorizontalPos = horizontalEncoder.getCurrentPosition();

        int deltaLeft = newLeftPos - leftEncoderPos;
        int deltaRight = newRightPos - rightEncoderPos;
        int deltaHorizontal = newHorizontalPos - horizontalEncoderPos;

        leftEncoderPos = newLeftPos;
        rightEncoderPos = newRightPos;
        horizontalEncoderPos = newHorizontalPos;

        double leftDistance = deltaLeft * INCHES_PER_TICK;
        double rightDistance = deltaRight * INCHES_PER_TICK;
        double horizontalDistance = deltaHorizontal * INCHES_PER_TICK;

        double deltaHeading = (rightDistance - leftDistance) / LATERAL_DISTANCE;
        double deltaX = horizontalDistance - FORWARD_OFFSET * deltaHeading;
        double deltaY = (leftDistance + rightDistance) / 2;

        xPos += deltaX * Math.cos(heading) - deltaY * Math.sin(heading);
        yPos += deltaX * Math.sin(heading) + deltaY * Math.cos(heading);
        heading += deltaHeading;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getHeading() {
        return heading;
    }
}