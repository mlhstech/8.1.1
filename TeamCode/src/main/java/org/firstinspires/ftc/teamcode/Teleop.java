package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Betas.Auto_home;
import org.firstinspires.ftc.teamcode.Betas.Auto_home2;


@TeleOp
public class Teleop extends OpMode {
    DcMotor front_left;
    DcMotor front_right;
    DcMotor back_left;
    DcMotor back_right;
    DcMotorEx lift;
    DistanceSensor distance;
    DistanceSensor distancea;
    Servo a;
    Servo b;
    int po = 0;
    int enc = 0;
    Auto_home2 home = new Auto_home2();

    @Override
    public void init() {
        front_left = hardwareMap.get(DcMotor.class, "0");
        front_right = hardwareMap.get(DcMotor.class, "1");
        back_left = hardwareMap.get(DcMotor.class, "2");
        back_right = hardwareMap.get(DcMotor.class, "3");
        lift = hardwareMap.get(DcMotorEx.class, "e0");
        distance = hardwareMap.get(DistanceSensor.class, "i2c0");
        distancea = hardwareMap.get(DistanceSensor.class, "i2c1");
        a = hardwareMap.get(Servo.class, "s0");
        b = hardwareMap.get(Servo.class, "s1");
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);
        lift.setTargetPosition(0);
        home.define(front_left, front_right, back_left, back_right, distance, distancea);
    }

    @Override
    public void loop() {
        double speed = gamepad1.left_stick_y;
        double steering = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        boolean pos_1 = gamepad2.a;
        boolean pos_2 = gamepad2.x;
        boolean pos_3 = gamepad2.b;
        boolean pos_4 = gamepad2.y;
        // double serv = gamepad2.right_trigger;
        boolean f = gamepad2.left_bumper;
        boolean g = gamepad2.right_bumper;
        boolean up = gamepad2.dpad_up;
        boolean down = gamepad2.dpad_down;
        double ho = gamepad1.left_trigger;
        double offset = gamepad2.left_trigger;

        if (lift.getCurrentPosition() > enc +1867) {
            speed = speed / 2.5;
            steering = steering / 2.5;
            strafe = strafe / 2.5;
        } else if (lift.getCurrentPosition() > enc + 1092) {
            speed = speed / 1.5;
            steering = steering / 1.5;
            strafe = strafe / 1.5;
        }



        front_left.setPower((speed - steering) - strafe);
        front_right.setPower((speed + steering) + strafe);
        back_left.setPower((speed - steering) + strafe);
        back_right.setPower((speed + steering) - strafe);
        if (up && offset > 0.5) {
            enc += 2;
        } else if (down && offset > 0.5){
            enc -= 2;
        } else if (up){
            po = po += 2;
        } else if (down){
            po = po -+ 2;
        }


        if (pos_1) {
            po = enc + 0;
            a.setPosition(1);
            b.setPosition(0);
        } else if (pos_2) {
            po = enc + 2171;
            a.setPosition(1);
            b.setPosition(0);
        } else if (pos_3) {
            po = enc + 3354;
            a.setPosition(1);
            b.setPosition(0);
        } else if (pos_4) {
            po = enc + 4470;
            a.setPosition(1);
            b.setPosition(0);
        }


        if (ho >= 0.5) {
            home.home();
        }

        /*if (pos_1) {
            lift.setTargetPosition(0+enc);
            a.setPosition(1);
            b.setPosition(0);
            // po = enc += 0;

        } else if (pos_2) {
            lift.setTargetPosition(2141+enc);
            // po = (enc += 1092);
        } else if (pos_3) {
            lift.setTargetPosition(3324+enc);
            // po = enc += 1869;
        } else if (pos_4) {
            lift.setTargetPosition(4440 + enc);
            lift.getCurrentPosition();
            //po = enc += 2371;
        } a */


        //lift.setTargetPosition(po);
        //lift.setTargetPositionTolerance(9999999);
        lift.setTargetPosition(po);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(0.75);


        if (f) {
            a.setPosition(1);
            b.setPosition(0);
        }
        if (g) {
            a.setPosition(0);
            b.setPosition(1);
        }
        //a.setPosition(serv);
        //b.setPosition(serv);

    }
}
