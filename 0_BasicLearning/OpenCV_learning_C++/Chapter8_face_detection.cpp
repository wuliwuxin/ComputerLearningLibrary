#include <opencv2/imgproc.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/objdetect.hpp>
#include <iostream>

using namespace std;
using namespace cv;

int main(){

    Mat img = imread("Resources/anne.jpg");

    CascadeClassifier faceCascades;
    faceCascades.load("Resources/haarcascade_frontalface_default.xml");
    if(faceCascades.empty()){
        cout<< "XML file is empty!" << endl;
    }

    vector<Rect> faces;

    faceCascades.detectMultiScale(img, faces,1.1, 3);

    for(int i = 0; i < faces.size(); i ++){
        rectangle(img, faces[i].tl(), faces[i].br(), Scalar(0,0, 255), 5);
    }

    imshow("Image", img);

    waitKey(0);

    return 0;
}
