package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Betas.auto_1;
import org.firstinspires.ftc.teamcode.Betas.auto_2;
import org.firstinspires.ftc.teamcode.Betas.auto_3;


public class Tensor_math extends regular_math{

    int bolt = 0;
    int bulb = 1;
    int panel = 0;

    auto_1 position_1 = new auto_1();
    auto_2 position_2 = new auto_2();
    auto_3 position_3 = new auto_3();

    public void def(DcMotorEx lf, DcMotorEx rf, DcMotorEx bl, DcMotorEx br, DcMotorEx li, IMU im, DistanceSensor c, DistanceSensor d, Servo y, Servo p) {
        position_1.define(lf, rf, bl, br, li,  im, c,  d, y, p);
        position_2.define(lf, rf, bl, br, li,  im, c,  d, y, p);
        position_3.define(lf, rf, bl, br, li,  im, c,  d, y, p);
    }




    public void adding(String name) {
        if (name.equals("1 red")) {
            bolt++;
        } else if (name.equals("2 green")) {
            bulb++;
        } else if (name.equals("3 blue")) {
            panel++;
        }
    }

    public String detected() {
        if (bolt > bulb) {
            if (bolt > panel) {
                return "1 red";
            } else {
                return "3 blue";
            }
        } else if (bulb > bolt) {
            if (bulb > panel) {
                return "2 green";
            } else {
                return "3 blue";
            }
        } else if (panel > bulb && panel > bolt) {
            return "3 blue";
        } else {
            return "No objects detected OR no object detected most";

        }
    }

    public void find_position() {
        if (bolt > bulb) {
            if (bolt > panel) {
                position_1.go();
            } else {
                position_3.go();
            }
        } else if (bulb > bolt) {
            if (bulb > panel) {
                position_2.go();
            } else {
                position_3.go();
            }
        } else if (panel > bulb && panel > bolt) {
            position_3.go();
        }
    }
    public String toString() {

        return "1 bolt: " + bolt + "2 bulb: " + bulb + "3 panel: " + panel;
    }

}
