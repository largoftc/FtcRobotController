package Minigame;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "RedSideAuto")
public class        AutonomousTesting extends LinearOpMode {
//
    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();
    int colorNo = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        //Autonomous Runs Here


        telemetry.addData("Hello", "World");
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();
        moveForwardInches(15, 0.2);
        while(checkColorSensor() == 0){
            sleep(30);
        }
        robot.demoMotor1.setPower(0);
        robot.demoMotor2.setPower(0);
        //redConeAuto();
        if (checkColorSensor() == 1) {
            telemetry.addLine("Red detected");
            redConeAuto();
        }
        else {
            telemetry.addLine("Blue detected");
            blueConeAuto();
        }
        telemetry.update();

        //sleep(20000);





    }

    public void redConeAuto() {
        robot.arm.setTargetPosition(1350);
        robot.arm.setPower(1);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moveForwardInches(18, 0.5);
        sleep(1000);
        robot.demoServo.setPosition(0.5);
        robot.demoServo.setPosition(1);
        sleep(1000);
        moveForwardInches(11,0.5);
        TurningNoReverse(25, -0.3);
        robot.arm.setTargetPosition(400);
        sleep(1000);
        moveForwardInches(5, -0.5);
        robot.demoServo.setPosition(0);
        sleep(1000);
        moveBackwardInches(52,-1);
        sleep(1000);
        Turning(11, -0.5);
        sleep(5000);
        moveForwardInches(63,1);
        Turning(3,0.5);
        //turnLeft(1.5, .4);
        //moveForwardInches(42,-1);
        sleep(1000);
    }

    public void blueConeAuto() {
        robot.arm.setTargetPosition(1350);
        robot.arm.setPower(1);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        moveForwardInches(18, 0.5);
        sleep(1000);
        robot.demoServo.setPosition(0.5);
        robot.demoServo.setPosition(1);
        sleep(1000);
        moveForwardInches(35,0.5);
        TurningNoReverse(25, -0.3);
        robot.arm.setTargetPosition(400);
        sleep(1000);
        moveForwardInches(5, -0.5);
        robot.demoServo.setPosition(0);
        sleep(1000);
        moveBackwardInches(52,-1);
        sleep(1000);
        Turning(11, -0.5);
        sleep(5000);
        moveForwardInches(80,1);
        Turning(3,0.5);
        //turnLeft(1.5, .4);
        //moveForwardInches(42,-1);
        sleep(1000);
    }

    public void moveForwardInches (double inches, double speed) {
        double wheelCircumference = 4.0 * Math.PI;
        double ticksPerRevolution = 537.7;
        double ticks = (inches * (ticksPerRevolution / wheelCircumference));

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.demoMotor1.setTargetPosition((int) Math.round(ticks));
        robot.demoMotor2.setTargetPosition((int) Math.round(ticks));

        robot.demoMotor1.setPower(speed);
        robot.demoMotor2.setPower(speed);

        while (opModeIsActive() && (robot.demoMotor1.isBusy() || robot.demoMotor2.isBusy()))
        {
            telemetry.addData("Mode: ", "Run to Position" + inches + "inches");
            telemetry.addData("Encoder positions demoMotor1", robot.demoMotor1.getCurrentPosition());
            telemetry.addData("Encoder positions demoMotor2", robot.demoMotor2.getCurrentPosition());
            telemetry.addData("Power demoMotor1", robot.demoMotor1.getPower());
            telemetry.addData("Power demoMotor1", robot.demoMotor2.getPower());
            telemetry.update();
        }

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveBackwardInches (double inches, double speed) {
        double wheelCircumference = 4.0 * Math.PI;
        double ticksPerRevolution = 537.7;
        double ticks = (inches * (ticksPerRevolution / wheelCircumference));

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.demoMotor1.setTargetPosition(-(int) Math.round(ticks));
        robot.demoMotor2.setTargetPosition(-(int) Math.round(ticks));

        robot.demoMotor1.setPower(speed);
        robot.demoMotor2.setPower(speed);

        while (opModeIsActive() && robot.demoMotor1.isBusy() && robot.demoMotor2.isBusy ());
        {
            telemetry.addData("Mode: ", "Run to Position" + inches + "inches");
        }
    }
    //left = + | right = - speed
    public void Turning (double inches, double speed) {
        double wheelCircumference = 4.0 * Math.PI;
        double ticksPerRevolution = 537.7;
        double ticks = (inches * (ticksPerRevolution / wheelCircumference));

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.demoMotor1.setTargetPosition(-(int) Math.round(ticks));
        robot.demoMotor2.setTargetPosition((int) Math.round(ticks));

        robot.demoMotor1.setPower(-speed);
        robot.demoMotor2.setPower(speed);

        while (opModeIsActive() && robot.demoMotor1.isBusy() && robot.demoMotor2.isBusy ());
        {
            telemetry.addData("Mode: ", "Run to Position" + inches + "inches");
        }
    }

    public void TurningNoReverse (double inches, double speed) {
        double wheelCircumference = 4.0 * Math.PI;
        double ticksPerRevolution = 537.7;
        double ticks = (inches * (ticksPerRevolution / wheelCircumference));

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.demoMotor1.setTargetPosition(-(int) Math.round(ticks));
        robot.demoMotor2.setTargetPosition((int) Math.round(ticks));

        robot.demoMotor1.setPower(0);
        robot.demoMotor2.setPower(speed);

        while (opModeIsActive() && robot.demoMotor1.isBusy() && robot.demoMotor2.isBusy ());
        {
            telemetry.addData("Mode: ", "Run to Position" + inches + "inches");
        }
    }
    public void turnRight(double time, double speed) {
        runtime.reset();

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.demoMotor1.setPower(speed);
        robot.demoMotor2.setPower(-speed);

        while(runtime.seconds() < time)
        {
            //Block
            telemetry.addData("Run Mode: ", "Drive Forward Time Based");
            telemetry.update();
        }

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void turnLeft(double time, double speed) {
        runtime.reset();

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.demoMotor1.setPower(-speed);
        robot.demoMotor2.setPower(speed);

        while(runtime.seconds() < time)
        {
            //Block
            telemetry.addData("Run Mode: ", "Drive Forward Time Based");
            telemetry.update();
        }

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void timedMoveForward(double time, double speed) {
        runtime.reset();

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.demoMotor1.setPower(speed);
        robot.demoMotor2.setPower(speed);

        while(runtime.seconds() < time)
        {
            //Block
            telemetry.addData("Run Mode: ", "Drive Forward Time Based");
            telemetry.update();
        }

        robot.demoMotor1.setPower(0.0);
        robot.demoMotor2.setPower(0.0);

        robot.demoMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.demoMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int checkColorSensor() {
        if(colorNo == 0 && robot.colorSensor.red() > robot.colorSensor.blue() + 400 && robot.colorSensor.red() > robot.colorSensor.green()) {
            colorNo = 1;
        }
        else if (colorNo == 0 && robot.colorSensor.blue() > robot.colorSensor.red() + 400 && robot.colorSensor.blue() > robot.colorSensor.green()) {
            colorNo = 2;
        }

        return colorNo;
    }

}

