#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams
// Read Videos
int main(){

    string video_path = "Resources/test_video.mp4";
    VideoCapture cap(video_path);

    Mat video_img;

    // read images in a loop 循环读取图像
    while (true){
        cap.read(video_img);
        imshow("Video_Image", video_img);
        waitKey(1);  // faster
//        waitKey(20);  // slower
    }

    return 0;
}