package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Lift extends LinearOpMode{

    DcMotorEx lift;

    public boolean define (DcMotorEx a) {

        lift = a;

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        return true;
    }

    public boolean ground() {
        while (lift.getCurrentPosition() != 0) {
            lift.setTargetPosition(0);
            lift.setPower(1);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }


        return true;
    }

    public boolean low() {
        while (lift.getCurrentPosition() < 2171) {
            lift.setTargetPosition(2171);
            lift.setPower(1);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        return true;
    }

    public boolean medium() {
        while (lift.getCurrentPosition() != 757) {
            lift.setTargetPosition(757);
            lift.setPower(1);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        return true;
    }


    @Override
    public void runOpMode() throws InterruptedException {

    }



}
