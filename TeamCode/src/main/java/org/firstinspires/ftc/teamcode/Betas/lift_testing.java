package org.firstinspires.ftc.teamcode.Betas;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class lift_testing extends LinearOpMode {
    DcMotorEx lif;
    Servo a;
    Servo b;
    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift();
        Claw claw = new Claw();
        lif = hardwareMap.get(DcMotorEx.class, "e0");
        a = hardwareMap.get(Servo.class, "s0");
        b = hardwareMap.get(Servo.class, "s1");

        lift.define(lif);
        claw.define(a,b);

        lift.ground();
        lift.medium();
        claw.close();
        sleep(1000);
        claw.open();
        sleep(1000);
        lift.ground();
    }
}
