#!/user/bin/env python3
# -*- coding: utf-8 -*-
import numpy as np
import cv2 as cv
import matplotlib.pyplot as plt
import imutils

# 1 读取图像
# 1 ：读取原图 可省略 等同于cv2.IMREAD_COLOR
# 0 ：读入图像单通道, 即灰度图 等同于cv2.IMREAD_GRAYSCALE
# -1 ：等同于cv2.IMREAD_UNCHANGED
# img = cv.imread("image/deer.jpeg")  # 加载RGB彩色图像
# img = cv.imread("image/deer.jpeg", 0)   # 加载灰度图像
img = cv.imread("image/deer.jpeg", -1)

# 2 显示图像 Opencv(BGR)&
# 2.1 Opencv
# opencv读+opencv显示+opencv保存
# imshow函数作用是在窗口中显示图像，窗口自动适合于图像大小
cv.imshow("Orial image", img)
# cv.imshow("flag=0 gray image", imutils.resize(img, 1200))  #利用imutils模块调整显示图像大小
# cv.imshow("flag=-1 3-channel image", img)
# cv.imwrite("image/graydeer.png", img)
# cv.waitKey(0)
# 表示等待10秒，将销毁窗口名称为"Orial image"的图像窗口
if cv.waitKey(10000):
    cv.destroyWindow("Orial image") #销毁单个特定窗口
# cv.destroyAllWindows() # 销毁全部窗口

# 2.2 matplotlib Matplotlib(RGB)
# opencv读取的彩色图像是BGR格式，Matplotlib显示彩色图像是RGB格式。因此，通过plt显示时会有扭曲。
# BGR to RGB 图像色彩空间变换函数cv2.cvtColor
# cv2.COLOR_BGR2GRAY： 表示将图像从BGR空间转化成灰度图，最常用
# cv2.COLOR_BGR2HSV： 表示将图像从RGB空间转换到HSV空间
# img = cv.cvtColor(img, cv.COLOR_BGR2RGB)
# # plt.imshow(img, cmap=plt.cm.gray)
# plt.imshow(img)
# plt.show()

# 3 图像保存
cv.imwrite("image/deer.png", img)

px = img[100, 100]
