package org.firstinspires.ftc.teamcode.Betas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous
public class new_Auto extends LinearOpMode {
    DcMotorEx front_left;
    DcMotorEx front_right;
    DcMotorEx back_left;
    DcMotorEx back_right;
    DcMotorEx lif;
    IMU imu;
    Rev2mDistanceSensor a;
    Rev2mDistanceSensor b;
    RevColorSensorV3 front;
    RevColorSensorV3 back;
    Servo l;
    Servo r;


    @Override
    public void runOpMode() throws InterruptedException {



        Robot_beta robot = new Robot_beta();
        Auto_home home = new Auto_home();
        Lift lift = new Lift();
        To_stack stak = new To_stack();
        Claw claw = new Claw();
        imu = hardwareMap.get(IMU.class, "imu");
        front_left = hardwareMap.get(DcMotorEx.class, "0");
        front_right = hardwareMap.get(DcMotorEx.class, "1");
        back_left = hardwareMap.get(DcMotorEx.class, "2");
        back_right = hardwareMap.get(DcMotorEx.class, "3");
        lif = hardwareMap.get(DcMotorEx.class, "e0");
        a = hardwareMap.get(Rev2mDistanceSensor.class,"i2c0");
        b = hardwareMap.get(Rev2mDistanceSensor.class,"i2c1");
        front = hardwareMap.get(RevColorSensorV3.class, "i2c2");
        back= hardwareMap.get(RevColorSensorV3.class, "i2c3");
        l = hardwareMap.get(Servo.class,"s0");
        r = hardwareMap.get(Servo.class,"s1");
        imu.resetYaw();
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("x", 3.7);
        packet.put("status", "alive");
        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(packet);
        Thread tel = new telem();

        tel.start();


        robot.define(front_left, front_right, back_left, back_right, imu);
        home.define(front_left, front_right, back_left, back_right, a, b);
        stak.define(front_left, front_right, back_left, back_right, imu, front, back, a);
        lift.define(lif);
        claw.define(l, r);




        telemetry.addData("> ", "Press start");


        waitForStart();

        claw.close();
        sleep(1000);

        robot.set_speed(0.4);


        //score on the first low
        robot.drive_forward(3);
        lift.low();
        robot.turn_left(0);
        robot.strafe_right(18);
        home.set_speed(0.3);
        home.home();
        claw.open();
        sleep(100);
        robot.drive_backward(5);
        claw.close();



        //get to stack
        sleep(300);
        robot.strafe_left(15);
        robot.set_speed(0.7);
        lift.ground();
        robot.drive_forward(5);
        robot.set_turn_speed(0.4);
        robot.turn_left(80);
        robot.set_speed(0.6);
        robot.strafe_right(52);
        sleep(500);
        robot.set_turn_speed(0.1);
        robot.turn_left(90);
        robot.set_speed(0.4);
        robot.drive_forward(18);
        robot.set_speed(0.3);
        robot.strafe_right(3);
        //robot.drive_forward(3);


        //grab from stack

        lift.medium();
        claw.open();
        stak.stack();
        claw.close();
        sleep(300);
        lift.low();
        sleep(200);

        //score on 2nd low
        robot.drive_backward(26);
        robot.strafe_left(14);
        sleep(300);
        home.home();
        robot.drive_backward(1);
        claw.open();
        sleep(300);
        robot.drive_backward(5);
        robot.strafe_right(14);
        claw.close();
        lift.ground();
        robot.drive_forward(22);
        lift.medium();
        claw.open();
        stak.stack();
        sleep(400);
        claw.close();
        sleep(400);
        lift.low();
        sleep(300);
        robot.set_speed(0.5);
        robot.drive_backward(48);
        lift.ground();


        //insert parking here




    }


    public class telem extends Thread {
        @Override
        public void run() {
            TelemetryPacket packet = new TelemetryPacket();

            FtcDashboard dashboard = FtcDashboard.getInstance();
            RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
            RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

            RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

            // Now initialize the IMU with this mounting orientation
            // Note: if you choose two conflicting directions, this initialization will cause a code exception.
            imu.initialize(new IMU.Parameters(orientationOnRobot));


            while(!isStopRequested()) {



                YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
                packet.put("status", "alive");
                packet.put("front_color_Raw: ", front.getRawLightDetected());
                packet.put("back_color_Raw: ", back.getRawLightDetected());
                packet.put("front_dist_sen: ", a.getDistance(DistanceUnit.MM));
                packet.put("back_dist_sen: ", b.getDistance(DistanceUnit.MM));
                packet.put("lift_pos: ", lif.getCurrentPosition());
                packet.put("IMU_yaw: ", orientation.getYaw(AngleUnit.DEGREES));
                packet.put("IMU_pitch: ", orientation.getPitch(AngleUnit.DEGREES));
                packet.put("IMU_roll: ", orientation.getRoll(AngleUnit.DEGREES));
                packet.put("front_left_pos: ", front_left.getCurrentPosition());
                packet.put("front_right_pos: ", front_right.getCurrentPosition());
                packet.put("back_left_pos: ", back_left.getCurrentPosition());
                packet.put("back_right_pos: ", back_right.getCurrentPosition());
                dashboard.sendTelemetryPacket(packet);
            }

        }
    }
}
