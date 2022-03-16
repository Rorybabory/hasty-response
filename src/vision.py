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

rpi = NetworkTables.getTable("RPi")


logging.basicConfig(level=logging.DEBUG)
cs = CameraServer.getInstance()
cs.enableLogging()
camera = cs.startAutomaticCapture()
camera.setResolution(160, 120)

sink = cs.getVideo()

img = np.zeros(shape=(120, 160, 3),dtype=np.uint8)
output = cs.putVideo("Threshold Image", 160, 120)
while True:
    time, input_img = sink.grabFrame(img)
    if time == 0:
        continue
    hsv_img = cv2.cvtColor(input_img, cv2.COLOR_BGR2HSV)
    binary_img = cv2.inRange(hsv_img, (28,48,92), (111,255,255))
    _, contours, _ = cv2.findContours(binary_img,cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    avg_x = 0
    avg_y = 0
    num_cont = 0
    if (len(contours) > 0):
        largest = contours[0]
        for contour in contours:
            if cv2.contourArea(contour) > cv2.contourArea(largest):
                largest = contour
            rect_cont = cv2.minAreaRect(contour)
            center_cont, _, _ = rect_cont
            cont_x, cont_y = center_cont
            avg_x+=cont_x
            avg_y+=cont_y
            num_cont+=1
        avg_x /= num_cont
        avg_y /= num_cont
        
        rect = cv2.minAreaRect(largest)
        center, _, _ = rect
        center_x, center_y = center
        kernel = np.ones((3, 3), np.uint8)
        binary_img = cv2.morphologyEx(binary_img, cv2.MORPH_OPEN, kernel)

        binary_img = cv2.circle(binary_img, (int(avg_x), int(avg_y)), 3, (255,0,0), 1)

        rpi.putNumber('tx', ((center_x/150.0)-0.5)*2.0)
        rpi.putNumber('ty', ((center_y/108.0)-0.5)*2.0)
        rpi.putNumber('ta', np.sum(binary_img == 255)/(160.0*120.0))
    getDistance(avg_y)
    output.putFrame(binary_img)