package frc.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FileReadWrite extends SubsystemBase {
    private File log;
    public FileReadWrite() {
        log = new File("/home/lvuser/log.txt");
        
    }
    public void writeToLog(String message) {
        try {
            System.out.println("wrote: " + message);
            FileWriter log_writer = new FileWriter(log, true);
            BufferedWriter writer = new BufferedWriter(log_writer);
            PrintWriter print_writer = new PrintWriter(writer);
            print_writer.println("[" + DriverStation.getMatchTime() + "]" + message);
            print_writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: log.txt");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void printLog() {
        try {
            Scanner log_reader = new Scanner(log);
            while (log_reader.hasNextLine()) {
                System.out.println(log_reader.nextLine());
            }
            log_reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("error: log file not found");
        }
    }
}
