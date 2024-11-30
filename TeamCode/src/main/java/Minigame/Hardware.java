package Minigame;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class  Hardware {

    public DcMotor demoMotor1;
    public DcMotor demoMotor2;
    public DcMotor arm;
    public Servo demoServo;
    public ColorSensor colorSensor;

    private static Hardware myInstance = null;

    public static Hardware getInstance() {
        if (myInstance == null) {
            myInstance = new Hardware();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
        demoMotor1 = hwMap.get(DcMotor.class, "left1");
        demoMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        demoMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoMotor1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        demoMotor1.setPower(0);

        demoMotor2 = hwMap.get(DcMotor.class, "right1");
        demoMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoMotor2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        demoMotor2.setPower(0);

        arm = hwMap.get(DcMotor.class, "demoLift");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        arm.setPower(0);

        demoServo = hwMap.get(Servo.class, "claw");
        demoServo.setPosition(0);

        colorSensor = hwMap.get(ColorSensor.class, "color");






    }

    public void initTele(HardwareMap hwMap) {
        demoMotor1 = hwMap.get(DcMotor.class, "left1");
        demoMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        demoMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoMotor1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        demoMotor1.setPower(0);

        demoMotor2 = hwMap.get(DcMotor.class, "right1");
        demoMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        demoMotor2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        demoMotor2.setPower(0);

        arm = hwMap.get(DcMotor.class, "demoLift");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        arm.setPower(0);

        demoServo = hwMap.get(Servo.class, "claw");
        demoServo.setPosition(0);

        colorSensor = hwMap.get(ColorSensor.class, "color");






    }

}
