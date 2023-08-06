#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams

// Read Images
int main() {


    string img_path = "Resources/test.jpg";  // Double quotes 双引号


    Mat img = imread(img_path);

    imshow("test", img);  // Double quotes

    // Delay in milliseconds. 0 is the special value that means "forever". 以毫秒为单位的延迟。0是特殊值，表示不关闭。
    waitKey(0);

    return 0;
}
