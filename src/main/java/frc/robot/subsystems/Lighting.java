package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lighting extends SubsystemBase {
    DigitalOutput dio_ring;
    public Lighting() {
        dio_ring = new DigitalOutput(Constants.Lighting.RING_DIO);
    }
    public void setRing(boolean state) {
        dio_ring.set(state);
    }
}
