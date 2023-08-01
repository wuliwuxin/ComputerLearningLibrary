#include <opencv2/imgproc.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int main(){

    Mat image = imread("Resources/shapes.png");
    Mat imageHSV, mask;

    int hmin = 0, smin = 0 , vmin = 0;
    int hmax = 179, smax = 255, vmax = 255;

    cvtColor(image, imageHSV, COLOR_BGR2HSV_FULL);

    namedWindow("Trackbars", (640, 200));
    createTrackbar("Hue Min", "Trackbars", &hmin,179 );
    createTrackbar("Hue Max", "Trackbars", &hmax,179 );
    createTrackbar("Sat Min", "Trackbars", &smin,255 );
    createTrackbar("Sat Max", "Trackbars", &smax,255 );
    createTrackbar("Val Min", "Trackbars", &vmin,255 );
    createTrackbar("Val Max", "Trackbars", &vmax,255 );

    while (true){

        Scalar lower(hmin, smin, vmin);
        Scalar upper(hmax, smax, vmax);

        inRange(imageHSV, lower, upper, mask);

        imshow("Origin Image", image);
        imshow("Image HSV", imageHSV);
        imshow("Image Mask", mask);

        waitKey(1);
    }
    return 0;
}