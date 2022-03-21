from cscore import CameraServer
from networktables import NetworkTables
import time
import logging
import math
import cv2
import numpy as np
def toRad(val):
    return val*(3.1415*180)
def getDistance(pos_y):
    res_h = 120.0
    ay = -(pos_y - (res_h/2)) / (res_h/2)
    dy = 6.0
    dz = 8.0
    fov = 2*math.tan((0.5*dy)/dz)
    pitch = (ay/2.0)*fov
    target_h = 104.02
    camera_h = 35.0
    camera_a = toRad(28.0)
    distance = (target_h-camera_h)/(math.tan((camera_a+pitch)))
    print("distance is: " + str(distance) + " ay is " + str(ay) + " fov is: " + str(fov))


NetworkTables.initialize(server='roborio-2035-frc.local')

sd = NetworkTables.getTable("SmartDashboard")


logging.basicConfig(level=logging.DEBUG)
cs = CameraServer.getInstance()
cs.enableLogging()
camera = cs.startAutomaticCapture()
camera.setResolution(160, 120)



sink = cs.getVideo()

img = np.zeros(shape=(120, 160, 3),dtype=np.uint8)
output = cs.putVideo("Camera View", 160, 120)
while True:
    time, input_img = sink.grabFrame(img)
    distance = sd.getNumber("Target Distance")
    input_img = cv2.putText(input_img, str(distance), (0,0), cv2.FONT_HERSHEY_SIMPLEX, 
                   1, (255,0,0), 1, cv2.LINE_AA)
    if time == 0:
        continue
    
    output.putFrame(input_img)