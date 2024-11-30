package IntoTheDeep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hardware1 {
    //Declare Motors
    public DcMotor lf;
    public DcMotor lr;
    public DcMotor rf;
    public DcMotor rr;
    public DcMotor lift;
    public Servo claw;

    private static hardware1 myInstance = null;

    public static hardware1 getInstance() {

        if (myInstance == null) {

            myInstance = new hardware1();
        }
        return myInstance;
    }

    public void init(HardwareMap hwMap)
    {
        lf = hwMap.get(DcMotor.class, " lf");


        rf = hwMap.get(DcMotor.class, "rf");


        lr = hwMap.get(DcMotor.class, "lr");


        rr = hwMap.get(DcMotor.class, "rr");


    }
}
