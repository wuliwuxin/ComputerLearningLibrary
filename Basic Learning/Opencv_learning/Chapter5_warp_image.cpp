#include <opencv2/imgcodecs.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int main(){

    Mat image = imread("Resources/cards.jpg");

//    cout<< image.size() <<endl;  // [1250 x 938]
    Mat matrix, img_Warp;
    float width=200, height=300;

    // 定义源图像的四个顶点坐标
    Point2f src[4] = { {529,142},{771,190},{405,395},{674,457} };
    // 定义目标图像的四个顶点坐标，这里使用图像的宽度和高度
    Point2f dst[4] = { {0,0},{width,0},{0, height},{width,height} };

    // 计算透视变换矩阵
    matrix = getPerspectiveTransform(src, dst);

    // 透视变换
//    warpPerspective(image, img_Warp, matrix, Point(width, height));
    warpPerspective(image, img_Warp, matrix, Size(width, height));

    for(int i=0; i < 4; i++){
        circle(image, src[i], 10, Scalar(0, 0, 255), 5);
    }

    imshow("Image", image);
    imshow("Image Warp",img_Warp);

    waitKey(0);

    return 0;
}