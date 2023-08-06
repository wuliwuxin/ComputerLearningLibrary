#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams
// Webcam 网络摄像头
int main(){

    VideoCapture cap(0);  // 默认为0

    Mat webcam_img;

    while (true){
        cap.read(webcam_img);
        imshow("webcam_img", webcam_img);
        waitKey(1);
    }

    return 0;

}