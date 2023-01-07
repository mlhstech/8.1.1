package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class lift_multi_test extends LinearOpMode implements Runnable{

    DcMotorEx lift;

    public boolean define (DcMotorEx a) {

        lift = a;

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        return true;
    }

    public boolean ground() {
        while (lift.getCurrentPosition() != 0) {
            lift.setTargetPosition(0);
            lift.setPower(0.5);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }


        return true;
    }

    public boolean low() {
        while (lift.getCurrentPosition() != 2171) {
            lift.setTargetPosition(2171);
            lift.setPower(0.5);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        return true;
    }

    public boolean medium() {
        while (lift.getCurrentPosition() != 3354) {
            lift.setTargetPosition(3354);
            lift.setPower(0.5);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        return true;
    }


    @Override
    public void runOpMode() throws InterruptedException {

    }


    @Override
    public void run() {

    }

    public void run(DcMotorEx a, int b) {
        lift = a;

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift_multi_test li = new lift_multi_test();


        if (b == 0) {
        li.ground();
        } else if (b == 1) {
        li.low();
        } else if (b == 2) {
        li.medium();
        }
    }
}
