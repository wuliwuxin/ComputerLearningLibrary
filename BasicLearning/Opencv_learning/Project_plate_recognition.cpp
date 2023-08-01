#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/objdetect.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Plates Recognition
int main(){

    VideoCapture cap(0);

    Mat img;
    CascadeClassifier plateCascades;
    plateCascades.load("Resources/haarcascade_russian_plate_number.xml");
    if(plateCascades.empty()){
        cout<< "XML file is empty!" << endl;
    }

    vector<Rect> plates;

    while (true){
        cap.read(img);
        plateCascades.detectMultiScale(img, plates,1.1, 3);

        for(int i = 0; i < plates.size(); i++){
            Mat imgcrop = img(plates[i]);
//            imshow(to_string(i), imgcrop);
            imwrite("Resources/plates/"+to_string(i)+".png", imgcrop);
            rectangle(img, plates[i].tl(), plates[i].br(), Scalar(0,0, 255), 5);
        }
        imshow("Image", img);
        waitKey(1);
    }

    return 0;
}