#include <opencv2/imgcodecs.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int main(){

    string img_path = "Resources/road.png";

    Mat img = imread(img_path);
    Mat img_resize, image_crop;

//    cout << "image size: " << img.size() << endl;  // [1006 x 577]

    // 调整图像大小
    int reWidth = 640;
    int reHeight = 480;

    resize(img, img_resize, Size(reWidth, reHeight));
//    resize(img, img_resize, Size(640, 480));
//    resize(img, img_resize, Size(), 0.5, 0.5);

    // 裁剪图像（使用ROI）
    int startX = 100; // 裁剪区域左上角的x坐标
    int startY = 50;  // 裁剪区域左上角的y坐标
    int cWithd = 200; // 裁剪区域的宽度
    int cHight = 300; // 裁剪区域的高度
//    Rect roi(100, 50, 200, 300);
    Rect roi(startX, startY, cWithd, cHight);
    image_crop = img(roi).clone();

    imshow("Origin Image", img);
    imshow("Image Resize", img_resize);
    imshow("Image Crop", image_crop);

    waitKey(0);

    return 0;
}
