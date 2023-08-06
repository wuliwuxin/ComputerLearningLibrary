#include <opencv2/imgproc.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int main(){
    // white: 255, 255, 255  Black: 0, 0, 0  Blue: 255, 0, 0  Purple: 255, 0, 255  Red: 0, 0, 255
    Mat img(512,512,CV_8UC3,Scalar( 255, 255, 255));

//    circle(img, Point(256, 256), 150, Scalar(0, 0, 255));  // 圆心坐标为(256, 256)，半径为150, 边界线宽度默认为1。
//    circle(img, Point(256, 256), 150, Scalar(0, 0, 255), 10);  // 边界线宽度为10
//    circle(img, Point(256, 256), 150, Scalar(0, 0, 255), FILLED);  // 填充整个圆形，即实心圆。
    circle(img, Point(256, 256), 150, Scalar(0, 0, 255), -1);  // 填充整个圆形，即实心圆。
    // 画矩形
    rectangle(img, Point(126, 246), Point(386, 296), Scalar(255, 255, 255), FILLED);

    // 画直线
    line(img, Point(126, 306), Point(386,306), Scalar(255, 255, 255), 2);

    putText(img, "Fighting!", Point(186, 282), FONT_HERSHEY_SCRIPT_SIMPLEX, 1.25, Scalar(255, 0, 0), 2 );

    imshow("Image", img);

    waitKey(0);

    return 0;
}