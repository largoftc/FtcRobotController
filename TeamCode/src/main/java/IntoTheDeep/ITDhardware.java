package IntoTheDeep;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ITDhardware {
    public DcMotor lf;
    public DcMotor rf;
    public DcMotor lb;
    public DcMotor rb;
    public DcMotor slide1;
    public DcMotor act1;
    public Servo s1;
    public Servo s2;
    public DcMotor act2;
    public DcMotor slide2;
    public ColorSensor colorSensor;
    public DcMotor rotater1;

    private static ITDhardware myInstance = null;
    
    public static ITDhardware getInstance() 
    {
        if (myInstance == null) {
            myInstance = new ITDhardware();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap) {
       lf = hwMap.get(DcMotor.class, "lf");
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        lf.setPower(0);

        lb = hwMap.get(DcMotor.class, "lb");
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        lb.setPower(0);

        rf = hwMap.get(DcMotor.class, "rf");
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rf.setPower(0);

        rb = hwMap.get(DcMotor.class, "rb");
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rb.setPower(0);

        slide1 = hwMap.get(DcMotor.class, "slide1");
        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        slide1.setPower(0);
/*
        slide2 = hwMap.get(DcMotor.class, "slide2");
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        slide2.setPower(0);

        act1 = hwMap.get(DcMotor.class, "act1");
        act1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        act1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        act1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        act1.setPower(0);
*/
        rotater1 = hwMap.get(DcMotor.class, "rotater1");
        rotater1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotater1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rotater1.setPower(0);


        s1 = hwMap.get(Servo.class, "s1");
        s1.setPosition(0);

        s2 = hwMap.get(Servo.class, "s2");
        s2.setPosition(0);
        //colorSensor = hwMap.get(ColorSensor.class, "color");






    }

    public void initTele(HardwareMap hwMap) {
        lf = hwMap.get(DcMotor.class, "lf");
        lf.setDirection(DcMotorSimple.Direction.REVERSE);
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        lf.setPower(0);

        lb = hwMap.get(DcMotor.class, "lb");
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        lb.setPower(0);

        rf = hwMap.get(DcMotor.class, "rf");
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rf.setPower(0);

        rb = hwMap.get(DcMotor.class, "rb");
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rb.setPower(0);


/*
        act1 = hwMap.get(DcMotor.class, "act1");
        act1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        act1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        act1.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        act1.setPower(0);
*/
        slide1 = hwMap.get(DcMotor.class, "slide1");
        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide1.setPower(0);
/*
        slide2 = hwMap.get(DcMotor.class, "slide2");
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        slide2.setPower(0);
*/




        rotater1 = hwMap.get(DcMotor.class, "rotater1");
        rotater1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotater1.setPower(0);

       s1 = hwMap.get(Servo.class, "s1");
        s1.setPosition(.43);

        s2 = hwMap.get(Servo.class, "s2");
        s2.setPosition(.3);


        //colorSensor = hwMap.get(ColorSensor.class, "color");

    }


}


