package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Auto_home2 extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DistanceSensor x;
    DistanceSensor y;
    int count = 0;

    public void define(DcMotor front_left, DcMotor front_right, DcMotor back_left, DcMotor back_right, DistanceSensor a, DistanceSensor b) {

        fl = front_left;
        fr = front_right;
        bl = back_left;
        br = back_right;
        x = a;
        y = b;



        //fl.setDirection(DcMotor.Direction.REVERSE);
        //  bl.setDirection(DcMotor.Direction.REVERSE);


    }

    public boolean home() {

        Auto_home2 homes = new Auto_home2();
        homes.define(fl, fr, bl, br, x, y);
        count = 0;


        while (x.getDistance(DistanceUnit.MM) >= 200 && count <= 500) {

            fl.setPower(-0.2);
            fr.setPower(-0.2);
            bl.setPower(-0.2);
            br.setPower(-0.2);
            count++;

        }

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        homes.homeb();




        return true;
    }

    public boolean homeb() {



        count = 0;
        if (x.getDistance(DistanceUnit.MM) < 140) {
            while (y.getDistance(DistanceUnit.MM) >= 150 && count <= 500) {

                fl.setPower(-0.2);
                fr.setPower(0.2);
                bl.setPower(0.2);
                br.setPower(-0.2);

                count++;
            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        } else if (x.getDistance(DistanceUnit.MM) > 140) {
            while (y.getDistance(DistanceUnit.MM) >= 150) {

                fl.setPower(0.2);
                fr.setPower(-0.2);
                bl.setPower(-0.2);
                br.setPower(0.2);

            }

            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }





        return true;
    }

    @Override
    public void runOpMode() throws InterruptedException {

    }
}

