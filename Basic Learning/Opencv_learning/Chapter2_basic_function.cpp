#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// imgGray, imgBlur, imgCanny, imgDil, imgErode
//  图像模糊, 腐蚀图片
int main() {

    string path = "Resources/road.png";
    Mat image = imread(path);
    Mat GrayImage, BlurredImage5, BlurredImage7, GaussianblurredImage, MedianblurredImage, BilateralfilteredImage, CannyedgesImage, HoughLinesImage, DilImage, ErodedImage;

    cvtColor(image, GrayImage, COLOR_BGR2GRAY);
    // blur
    blur(image, BlurredImage5, Size(5, 5));
    blur(image, BlurredImage7, Size(7, 7));

    GaussianBlur(image, GaussianblurredImage, Size(5, 5), 0);
    medianBlur(image, MedianblurredImage, 5);
    bilateralFilter(image, BilateralfilteredImage, 9, 75, 75);

    Canny(GaussianblurredImage, CannyedgesImage, 100, 200); // 100为低阈值，200为高阈值


    // 定义结构元素（内核）
    Mat kernel = getStructuringElement(MORPH_RECT, Size(5, 5)); // 5x5的矩形内核
    dilate(CannyedgesImage, DilImage, kernel);
    // 腐蚀操作
    erode(DilImage, ErodedImage, kernel);

    // 复制原始图像到一个新的变量中，以免修改原始图像
    Mat imageWithLines = image.clone();
    Mat ImageWithLinesCanny;

    // 检测直线
    vector<Vec2f> lines;
    HoughLines(CannyedgesImage, lines, 1, CV_PI / 180, 100); // 线宽为1像素，CV_PI / 180为角度精度，100为投票阈值
    // 在原始图像上绘制直线
    for (size_t i = 0; i < lines.size(); i++) {
        float rho = lines[i][0];
        float theta = lines[i][1];
        double a = cos(theta), b = sin(theta);
        double x0 = a * rho, y0 = b * rho;
        Point pt1, pt2;
//        Point pt1(cvRound(x0 + 1000 * (-b)), cvRound(y0 + 1000 * (a)));
//        Point pt2(cvRound(x0 - 1000 * (-b)), cvRound(y0 - 1000 * (a)));
        pt1.x = saturate_cast<int>(x0 + 1000 * (-b));
        pt1.y = saturate_cast<int>(y0 + 1000 * (a));
        pt2.x = saturate_cast<int>(x0 - 1000 * (-b));
        pt2.y = saturate_cast<int>(y0 - 1000 * (a));
        line(imageWithLines, pt1, pt2, Scalar(0, 0, 255), 1, LINE_AA);  //Scalar(0, 0, 255)作为红色，并将线宽设置为1
    }


    imshow("Origin Image", image);
    imshow("GrayImage", GrayImage);
    imshow("BlurredImage5", BlurredImage5);
    imshow("BlurredImage7", BlurredImage7);
    imshow("GaussianblurredImage", GaussianblurredImage);
    imshow("MedianblurredImage", MedianblurredImage);
    imshow("BilateralfilteredImage", BilateralfilteredImage);
    imshow("CannyedgesImage", CannyedgesImage);
    imshow("DilateImage", DilImage);
    imshow("ErodedImage", ErodedImage);
    imshow("Image with Lines", imageWithLines);

    waitKey(0);

    return 0;
}
