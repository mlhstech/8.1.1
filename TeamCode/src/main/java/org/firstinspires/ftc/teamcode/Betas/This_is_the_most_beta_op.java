package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.Timer;
import java.util.TimerTask;


@TeleOp
public class This_is_the_most_beta_op extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    DcMotorEx lift;
    DistanceSensor distance;
    DistanceSensor distancea;
    Servo a;
    Servo b;
    IMU imu;
    int po = 0;
    int enc = 0;
    Auto_home2 home = new Auto_home2();
    double powerr = 0;
    double powerl = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");
        lift = hardwareMap.get(DcMotorEx.class, "e0");
        distance = hardwareMap.get(DistanceSensor.class, "i2c0");
        distancea = hardwareMap.get(DistanceSensor.class, "i2c1");
        a = hardwareMap.get(Servo.class, "s0");
        b = hardwareMap.get(Servo.class, "s1");
        imu = hardwareMap.get(IMU.class, "imu");
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.REVERSE);
        //front_right.setDirection(DcMotorEx.Direction.REVERSE);
        //back_right.setDirection(DcMotorEx.Direction.REVERSE);
        lift.setTargetPosition(0);
       // home.define(front_left, front_right, back_left, back_right, distance, distancea);
        Robot_tele robot = new Robot_tele();
       // robot.define(front_left, front_right, back_left, back_right, imu);
        front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Thread homem = new Auto_homes();
        Thread lifts = new lift_ops();
        Thread straight = new stay_straight();
        Timer timer = new Timer();
        TimerTask endgame = new TimerTask() {
            @Override
            public void run() {
                gamepad1.rumbleBlips(4);
                gamepad1.rumble(500);
                gamepad1.rumbleBlips(4);
                gamepad1.rumble(500);
            }
        };
        gamepad1.setLedColor(1,0,0.5,1000);




        waitForStart();
        lifts.start();
        straight.start();
        timer.schedule(endgame, 85000);






        while (!isStopRequested()) {   //this is driver code
            double speed = gamepad1.left_stick_y;
            double steering = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            // boolean cancel = gamepad1.left_stick_button;

            double ho = gamepad1.left_trigger;


            if (lift.getCurrentPosition() > enc + 1867) {
                speed = speed / 2.5;
                steering = steering / 2.5;
                strafe = strafe / 2.5;
            } else if (lift.getCurrentPosition() > enc + 1092) {
                speed = speed / 1.5;
                steering = steering / 1.5;
                strafe = strafe / 1.5;
            }


            front_left.setPower(((speed - steering) - strafe) + powerl);
            front_right.setPower(((speed + steering) + strafe) + powerr);
            back_left.setPower(((speed - steering) + strafe) + powerl);
            back_right.setPower(((speed + steering) - strafe) + powerr);



            if (ho >= 0.5) {
                homem.start();
            }

            telemetry.addData("throttle: ", speed);
            telemetry.addData("steering: ", steering);
            telemetry.addData("strafe: ", strafe);
            telemetry.addData("front_left: ", front_left.getPower());
            telemetry.addData("front_right: ", front_right.getPower());
            telemetry.addData("back_left: ", back_left.getPower());
            telemetry.addData("back_right: ", back_right.getPower());
            telemetry.addData("auto_home_button: ", ho);
            telemetry.addData("turning>", powerl);
            telemetry.update();



        }




    }


    public class lift_ops extends Thread {

        @Override
        public void run() {  //this is all the code for our lift

            int stack = 5;



            while (!isInterrupted()) {
                boolean pos_1 = gamepad2.a;
                boolean pos_2 = gamepad2.x;
                boolean pos_3 = gamepad2.b;
                boolean pos_4 = gamepad2.y;
                boolean f = gamepad2.left_bumper;
                boolean g = gamepad2.right_bumper;
                boolean up = gamepad2.dpad_up;
                boolean down = gamepad2.dpad_down;
                boolean stack_button = gamepad2.left_stick_button;
                double offset = gamepad2.left_trigger;




                if (stack_button) {

                    //fix these vals
                    if (stack == 5) {
                        po = enc + 2171;
                    } else if (stack == 4) {
                        po = enc + 2171;
                    } else if (stack == 3) {
                        po = enc + 2171;
                    } else if (stack == 2) {
                        po = enc + 2171;
                    } else if (stack == 1) {

                    }
                }



                if (up && offset > 0.5) {
                    enc += 2;
                } else if (down && offset > 0.5) {
                    enc -= 2;
                } else if (up) {
                    po = po += 2;
                } else if (down) {
                    po = po - +2;
                }


                if (pos_1) {
                    gamepad1.rumble(1000);
                    po = enc + 0;
                    a.setPosition(1);
                    b.setPosition(0);
                } else if (pos_2) {
                    gamepad1.rumbleBlips(1);
                    po = enc + 2171;
                    a.setPosition(1);
                    b.setPosition(0);
                } else if (pos_3) {
                    gamepad1.rumbleBlips(2);
                    po = enc + 3354;
                    a.setPosition(1);
                    b.setPosition(0);
                } else if (pos_4) {
                    gamepad1.rumbleBlips(3);
                    po = enc + 4470;
                    a.setPosition(1);
                    b.setPosition(0);
                }

                if (f) {
                    a.setPosition(1);
                    b.setPosition(0);
                }
                if (g) {
                    a.setPosition(0);
                    b.setPosition(1);
                }

                lift.setTargetPosition(po);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setPower(0.75);
            }

        }
    }

    public class Auto_homes extends Thread {

        @Override
        public void run() {  //this is all the code for our auto homeing

            boolean cancel = false;




                cancel = gamepad1.left_stick_button;


                while (distance.getDistance(DistanceUnit.MM) >= 200 && !cancel) {

                    front_left.setPower(-0.2);
                    front_right.setPower(-0.2);
                    back_left.setPower(-0.2);
                    back_right.setPower(-0.2);
                    cancel = gamepad1.left_stick_button;

                }

                front_left.setPower(0);
                front_right.setPower(0);
                back_left.setPower(0);
                back_right.setPower(0);


                if (distance.getDistance(DistanceUnit.MM) < 140 && !cancel) {
                    while (distancea.getDistance(DistanceUnit.MM) >= 150 && !cancel) {

                        front_left.setPower(-0.2);
                        front_right.setPower(0.2);
                        back_left.setPower(0.2);
                        back_right.setPower(-0.2);
                        cancel = gamepad1.left_stick_button;

                    }

                    front_left.setPower(0);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(0);
                } else if (distance.getDistance(DistanceUnit.MM) > 140 && !cancel) {
                    while (distancea.getDistance(DistanceUnit.MM) >= 150 && !cancel) {

                        front_left.setPower(0.2);
                        front_right.setPower(-0.2);
                        back_left.setPower(-0.2);
                        back_right.setPower(0.2);
                        cancel = gamepad1.left_stick_button;

                    }

                    front_left.setPower(0);
                    front_right.setPower(0);
                    back_left.setPower(0);
                    back_right.setPower(0);
                }








        }
    }

    public class stay_straight extends Thread {
        @Override
        public void run() {
            //YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            double yaw = 0;


            while (!isInterrupted()) {
                double steering = gamepad1.right_stick_x;
                YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
                telemetry.addData("hello","");
                if(steering < -0.1 || steering > 0.1) {

                    yaw = orientation.getYaw(AngleUnit.DEGREES);

                }

                while (steering >= -0.1 && steering <= 0.1) {
                     steering = gamepad1.right_stick_x;
                     orientation = imu.getRobotYawPitchRollAngles();
                    telemetry.addData("hello","");
                    if(steering < -0.1 || steering > 0.1) {

                        yaw = orientation.getYaw(AngleUnit.DEGREES);

                    }
                    orientation = imu.getRobotYawPitchRollAngles();

                        //telemetry.addData("this: >", "is me running :)");
                    if (orientation.getYaw(AngleUnit.DEGREES) >= yaw - 1 && orientation.getYaw(AngleUnit.DEGREES) <= yaw + 1) {
                        telemetry.addData("1"," ");
                        powerr = 0;
                        powerl = 0;
                    } else if (orientation.getYaw(AngleUnit.DEGREES) >= yaw -1) {
                        powerr = 0.2;
                        powerl = -0.2;
                        telemetry.addData("2"," ");
                    } else if (orientation.getYaw(AngleUnit.DEGREES) <= yaw + 1) {
                        powerl = 0.2;
                        powerr = -0.2;
                        telemetry.addData("3"," ");
                    }
                }

            }

        }




    }



}
