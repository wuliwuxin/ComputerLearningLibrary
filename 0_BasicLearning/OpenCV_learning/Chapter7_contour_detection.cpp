#include <opencv2/imgcodecs.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int getContours(Mat imgDil, Mat img){

    vector<vector<Point>> contours;  // 每个向量包含一个轮廓的点集
    vector<Vec4i> hierarchy;  // 四个整数值的向量

    // RETR_EXTERNAL 仅提取最外层的轮廓, CHAIN_APPROX_SIMPLE 仅保留端点
    findContours(imgDil, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);
    // -1 表示绘制了所有找到的轮廓。
//    drawContours(img, contours, -1, Scalar(0, 0, 255), 2);

    // 每个向量中存储的是一个逼近多边形的点集, conPoly 中存储了每个轮廓的多边形逼近结果。
    vector<vector<Point>> conPoly(contours.size());

    // boundRect 中存储了每个轮廓的边界矩形, 包含了矩形的位置（左上角坐标）和尺寸（宽度和高度）。
    vector<Rect> boundRect(contours.size());

    string objType;
    for(int i = 0; i < contours.size(); i++){
        float area = contourArea(contours[i]);
        cout << i << ": "<<area << endl;  // 0: 36743  1: 38826
        if(area > 1000){
            double peri = arcLength(contours[i], true);
            approxPolyDP(contours[i], conPoly[i], 0.02*peri, true);  // conPoly[i] 是输入的多边形点集
            boundRect[i] = boundingRect(conPoly[i]);  // boundRect[i] 每个多边形的边界矩形的位置和尺寸信息。
//            cout << i << ": "<< boundRect[i].size() << endl;  // 0: [250 x 148]
//            cout << i << ": "<< conPoly[i].size() << endl;  // 0: 4  1: 8
            int obj = conPoly[i].size();
            if(obj==3){
                objType = "Triangle";  // 三角形
            }
            else if (obj==4){
//                objType = "Quads";  // 四边形
                double aspRatio = (float) boundRect[i].width/ (float) boundRect[i].height;
                if (aspRatio > 0.95 && aspRatio < 1.05){
                    objType = "Square";  // 正方形
                } else{
                    objType = "Rectangle";  // 长方形
                }
            } else{
                objType = "Circle";  // 圆

            }
            drawContours(img, contours, i, Scalar(0, 0, 255), 2);
            rectangle(img, boundRect[i].tl(), boundRect[i].br(), Scalar(0, 255, 0), 5);
            putText(img, objType, {boundRect[i].x+25, boundRect[i].y-2}, FONT_HERSHEY_PLAIN, 1,Scalar(0, 0, 255), 1);
        }
    }

    return 0;
}

int main(){

    Mat img = imread("Resources/shapes.png");
    Mat imgGray, imgCanny, imgBlur, imgDil;

    cvtColor(img, imgGray, COLOR_BGR2GRAY);
    Canny(imgGray, imgCanny, 25, 75);
    GaussianBlur(imgGray, imgBlur, Size(7,7), 0);

    Mat kernel = getStructuringElement(MORPH_RECT, Size(5,5));
    dilate(imgCanny, imgDil, kernel);

    getContours(imgDil,img);

    imshow("Image", img);
//    imshow("Image Gray", imgGray);
//    imshow("Image GaussianBlur", imgBlur);
//    imshow("Image Dilation", imgDil);

    waitKey(0);

    return 0;
}