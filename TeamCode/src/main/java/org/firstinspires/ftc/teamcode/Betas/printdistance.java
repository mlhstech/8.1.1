package org.firstinspires.ftc.teamcode.Betas;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class printdistance extends LinearOpMode {
    DistanceSensor distance;
    DistanceSensor distancea;
    DcMotorEx lift;


    @Override
    public void runOpMode() {
        // Get the distance sensor and motor from hardwareMap
        distance = hardwareMap.get(DistanceSensor.class, "i2c0");
        distancea = hardwareMap.get(DistanceSensor.class, "i2c1");
        lift = hardwareMap.get(DcMotorEx.class,"e0");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Loop while the Op Mode is running
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("port 0 >", distance.getDistance(DistanceUnit.MM));
            telemetry.addData("port 1 >", distancea.getDistance(DistanceUnit.MM));
            telemetry.addData("lift: ", lift.getCurrentPosition());
            telemetry.update();

        }
    }
}
