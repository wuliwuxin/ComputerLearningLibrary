# 4小时快速学习OpenCV C++

[OpenCV 开源计算机视觉库](https://github.com/opencv/opencv/tree/master)

目录
- [环境配置和安装软件](#环境配置和安装软件)
- [读取读取图像、视频和摄像头](#读取图像视频和摄像头)
- [基本功能](#基本功能)
- [调整大小和裁剪](#调整大小和裁剪)
- [绘制形状和文本](#绘制形状和文本)
- [扭曲图像](#扭曲图像)
- [颜色检测](#颜色检测)
- [形状/轮廓检测](#形状轮廓检测)
- [人脸检测](#人脸检测)
- [项目](#车牌识别)

## 环境配置和安装软件
1. 安装Homebrew

安装Homebrew，terminal安装
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

如果安装太慢或者报错443，可打开浏览器，输入 [https://brew.sh/](https://brew.sh/)，全选然后复制，在本地创建一个`install.sh`
```bash
vi install.sh
```
然后粘贴上述复制的全部内容，`:wq`退出并保存编辑的文件，然后需要给文件添加权限
```bash
chmod a+X install.sh
```
安装Homebrew
```bash
/bin/bash install.sh
```
2. 添加清华源镜像，安装OpenCV

首先，需要确保系统中安装了 bash、git 和 curl，对于 macOS 用户需额外要求安装 Command Line Tools (CLT) for Xcode。
- 对于 macOS 用户，系统自带 bash、git 和 curl，在命令行输入 `xcode-select --install` 安装 CLT for Xcode 即可。
  
- 在终端输入以下几行命令设置环境变量
```bash
export HOMEBREW_INSTALL_FROM_API=1
export HOMEBREW_API_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles/api"
export HOMEBREW_BOTTLE_DOMAIN="https://mirrors.tuna.tsinghua.edu.cn/homebrew-bottles"
export HOMEBREW_BREW_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/brew.git"
export HOMEBREW_CORE_GIT_REMOTE="https://mirrors.tuna.tsinghua.edu.cn/git/homebrew/homebrew-core.git"
```
安装opencv
```bash
brew install opencv
```

您可以使用以下命令检查版本。
```bash
brew list --versions | grep opencv
```
我安装的版本是`opencv 4.8.0_1`。

3. 安装Clion

[下载Clion安装包](https://www.jetbrains.com/clion/download/?source=google&medium=cpc&campaign=11959979190&term=clion&content=489240779750&gad=1&gclid=CjwKCAjwq4imBhBQEiwA9Nx1BuWrBeB9CSKLBqNVnLNlGGWcFgQF2BnY9thNnF2vbsc9sCdznID_LRoCLvQQAvD_BwE#section=mac)

下载完成后点击安装即可。

4. Clion激活

[下载破解包](https://pan.baidu.com/s/1oOd-VqjiBejUpdYAMtcs8w) 提取码: 37bj

进入scripts目录，选中uninstall.sh，复制路径`option+command+c`终端输入, `/Users/XXXX/Downloads/Clion-2023.1/scripts/uninstall.sh`为你复制的路径
```
sh /Users/XXXX/Downloads/Clion-2023.1/scripts/uninstall.sh
```
出现done说明成功。

再执行install.sh，与上面同理，出现done，the kill xxxxx 说明成功。

```
sh /Users/XXXX/Downloads/Clion-2023.1/scripts/install.sh
```

然后关闭CLion软件，重新CLion打开，输入破解码激活即可

选择下面的选项，并输入激活码，点击Activate进行激活。

```bash
GSSXZEZ56G-eyJsaWNlbnNlSWQiOiJHU1NYWkVaNTZHIiwibGljZW5zZWVOYW1lIjoic2lnbnVwIHNjb290ZXIiLCJhc3NpZ25lZU5hbWUiOiIiLCJhc3NpZ25lZUVtYWlsIjoiIiwibGljZW5zZVJlc3RyaWN0aW9uIjoiIiwiY2hlY2tDb25jdXJyZW50VXNlIjpmYWxzZSwicHJvZHVjdHMiOlt7ImNvZGUiOiJQU0kiLCJmYWxsYmFja0RhdGUiOiIyMDI1LTA4LTAxIiwicGFpZFVwVG8iOiIyMDI1LTA4LTAxIiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBTVyIsImZhbGxiYWNrRGF0ZSI6IjIwMjUtMDgtMDEiLCJwYWlkVXBUbyI6IjIwMjUtMDgtMDEiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFdTIiwiZmFsbGJhY2tEYXRlIjoiMjAyNS0wOC0wMSIsInBhaWRVcFRvIjoiMjAyNS0wOC0wMSIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJDTCIsImZhbGxiYWNrRGF0ZSI6IjIwMjUtMDgtMDEiLCJwYWlkVXBUbyI6IjIwMjUtMDgtMDEiLCJleHRlbmRlZCI6ZmFsc2V9LHsiY29kZSI6IlBDV01QIiwiZmFsbGJhY2tEYXRlIjoiMjAyNS0wOC0wMSIsInBhaWRVcFRvIjoiMjAyNS0wOC0wMSIsImV4dGVuZGVkIjp0cnVlfV0sIm1ldGFkYXRhIjoiMDEyMDIyMDkwMlBTQU4wMDAwMDUiLCJoYXNoIjoiVFJJQUw6NTM5ODE5MDIyIiwiZ3JhY2VQZXJpb2REYXlzIjo3LCJhdXRvUHJvbG9uZ2F0ZWQiOmZhbHNlLCJpc0F1dG9Qcm9sb25nYXRlZCI6ZmFsc2V9-iAUL9c89hA+oXgFAajsV9QyCOVsENJGkZiGQvW7XKaJID5XtXbxU4iBdMPaiJE1B8dZJy27Vt1WJjin+oQtnSC47t0MCx61vPOBGk/KAvEBoi6d8mfP5u729g0GHva3nPXsY3+IbnsWm7SIsB0syvtr1VRmqt7tBKm2F6gRn4Z8SnvkoQuyrvtZN8juJBEz3hYGHH4h+jpMnAYjXZ0M92WaD4aIvtGazp41f4/FuXW6IcSHTwualWVHuvS95fuxxAwpbyxPyYLom1Uh/la6kY9+zi3R5Z1amOHEOCyZnSoFDcMeKF3yhQU6/AjYiT/fOfICNcaK/BHYZ0VFMI8zOQA==-MIIETDCCAjSgAwIBAgIBDTANBgkqhkiG9w0BAQsFADAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMB4XDTIwMTAxOTA5MDU1M1oXDTIyMTAyMTA5MDU1M1owHzEdMBsGA1UEAwwUcHJvZDJ5LWZyb20tMjAyMDEwMTkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCUlaUFc1wf+CfY9wzFWEL2euKQ5nswqb57V8QZG7d7RoR6rwYUIXseTOAFq210oMEe++LCjzKDuqwDfsyhgDNTgZBPAaC4vUU2oy+XR+Fq8nBixWIsH668HeOnRK6RRhsr0rJzRB95aZ3EAPzBuQ2qPaNGm17pAX0Rd6MPRgjp75IWwI9eA6aMEdPQEVN7uyOtM5zSsjoj79Lbu1fjShOnQZuJcsV8tqnayeFkNzv2LTOlofU/Tbx502Ro073gGjoeRzNvrynAP03pL486P3KCAyiNPhDs2z8/COMrxRlZW5mfzo0xsK0dQGNH3UoG/9RVwHG4eS8LFpMTR9oetHZBAgMBAAGjgZkwgZYwCQYDVR0TBAIwADAdBgNVHQ4EFgQUJNoRIpb1hUHAk0foMSNM9MCEAv8wSAYDVR0jBEEwP4AUo562SGdCEjZBvW3gubSgUouX8bOhHKQaMBgxFjAUBgNVBAMMDUpldFByb2ZpbGUgQ0GCCQDSbLGDsoN54TATBgNVHSUEDDAKBggrBgEFBQcDATALBgNVHQ8EBAMCBaAwDQYJKoZIhvcNAQELBQADggIBABqRoNGxAQct9dQUFK8xqhiZaYPd30TlmCmSAaGJ0eBpvkVeqA2jGYhAQRqFiAlFC63JKvWvRZO1iRuWCEfUMkdqQ9VQPXziE/BlsOIgrL6RlJfuFcEZ8TK3syIfIGQZNCxYhLLUuet2HE6LJYPQ5c0jH4kDooRpcVZ4rBxNwddpctUO2te9UU5/FjhioZQsPvd92qOTsV+8Cyl2fvNhNKD1Uu9ff5AkVIQn4JU23ozdB/R5oUlebwaTE6WZNBs+TA/qPj+5/we9NH71WRB0hqUoLI2AKKyiPw++FtN4Su1vsdDlrAzDj9ILjpjJKA1ImuVcG329/WTYIKysZ1CWK3zATg9BeCUPAV1pQy8ToXOq+RSYen6winZ2OO93eyHv2Iw5kbn1dqfBw1BuTE29V2FJKicJSu8iEOpfoafwJISXmz1wnnWL3V/0NxTulfWsXugOoLfv0ZIBP1xH9kmf22jjQ2JiHhQZP7ZDsreRrOeIQ/c4yR8IQvMLfC0WKQqrHu5ZzXTH4NO3CwGWSlTY74kE91zXB5mwWAx1jig+UXYc2w4RkVhy0//lOmVya/PEepuuTTI4+UJwC7qbVlh5zfhj8oTNUXgN0AOc+Q0/WFPl1aw5VV/VrO8FCoB15lFVlpKaQ1Yh+DVU8ke+rt9Th0BCHXe0uZOEmH0nOnH/0onD
```
然后就成了，时间是假的，已经永久激活了，而且是官网最新版本哦，如果你重新更新了，再执行上面的操作即可。

4. Clion汉化

安装插件Chinese即可汉化。

5. 安装插件 `C/C++ Sigal File Execution`

[遇到的问题](https://blog.csdn.net/wuli_xin/article/details/131968147)

## 读取图像、视频和摄像头
[Chapter_read.cpp](Chapter1_read_Images.cpp)

- `#include <opencv2/imgcodecs.hpp>`

   imgcodecs = image + codec，由名称可知，肯定与图像的编解码有关，opencv 通过该模块，对各种格式类型的图片进行解码，载入并创建 生成对应的 Mat 对象，或者，将程序中的 Mat 对象，输出为指定格式的图片文件。
- `#include <opencv2/highgui.hpp>`

   highgui.hpp就是关于GUI图形交互的模块
- `#include <opencv2/imgproc.hpp>`

  Image Process，即图像处理模块。

`Mat`是可以存储图像的变量类型。


`imread`

    CV_EXPORTS_W Mat imread( const String& filename, int flags = IMREAD_COLOR );

`imshow`

    CV_EXPORTS_W void imshow(const String& winname, InputArray mat);


读图片  [Chapter1_read_Images.cpp](Chapter1_read_Images.cpp)。

注意图像的位置。
```bash
.
├── cmake-build-debug
├──├── CMakeFiles
├──├── Resources
├──├──├── test.jpg
├── CMakeLists.txt
└── main.cpp
```
如果图像和`main.cpp`在同一目录下，图像路径必须是`绝对路径`。

```c++
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams

// Read Images
int main() {

    string img_path = "Resources/test.jpg";  // 双引号
    

    Mat img = imread(img_path);

    imshow("test", img);  // 双引号

    waitKey(0);

    return 0;
}
```

读视频 [Chapter1_read_videos.cpp](Chapter1_read_videos.cpp)。
```c++
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams
// Read Videos
int main(){

    string video_path = "Resources/test_video.mp4";
    VideoCapture cap(video_path);

    Mat video_img;

    // 循环读取图像
    while (true){
        cap.read(video_img);
        imshow("Video_Image", video_img);
        waitKey(20);
    }

    return 0;
}
```
调用自己电脑摄像头 [Chapter1_read_webcam.cpp](Chapter1_read_webcam.cpp)。
```c++
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>

using namespace std;
using namespace cv;

// Read Images Videos and Webcams
// Webcam
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
```
## 基本功能
GrayImage, BlurredImage5, BlurredImage7, GaussianblurredImage, MedianblurredImage, BilateralfilteredImage, CannyedgesImage, HoughLinesImage, DilImage, ErodedImage

复制原始图像到一个新的变量中，以免修改原始图像

```c++
 Mat ImageCopy = image.clone();
```
- cvtColor (COLOR_BGR2GRAY) , 将彩色图像转换为黑白图像。将图像的颜色转换为另一种格式。`cvtColor`需要三个参数：原始图像、转换后的图像和转换方法。
 
      OpenCV是如何存储图像的?
      图像由像素组成，每个像素都有颜色信息。颜色通常由三个 BGR 值（蓝色、绿色、红色）表示。垂直像素的数量称为行，水平像素的数量称为列。一张 BGR 图像可以存储在 3 个矩阵中。保存多少个矩阵称为通道数，对于BGR图像，通道数为3。在黑白图像中，一个像素中仅存储一定程度的亮度。所以通道数为1。img.channels()检查通道数。在该BGR图像中，元素类型为unsinged char，大小为8位，通道数为3，因此type表示CV_8UC3为。类似地，黑白图像CV_8UC1也是如此。
  
  ![rows_cols.png](readme_pictrue%2Frows_cols.png)
  
  顺便说一下，当保存的BGR图像cv::Mat从顶部地址开始按顺序展开时，其排列如下
  
  ![rows_cols_3.png](readme_pictrue%2Frows_cols_3.png)
  
  也可以认为行排列如下，这种情况下图像每行的像素数称为步长
  ![cols_3.png](readme_pictrue%2Fcols_3.png)
```c++
cvtColor(image, GrayImage, COLOR_BGR2GRAY);
```
- 线性滤波图像模糊 (blur) 指卷积运算，通过在图像上滑动一个滤波器（也称为卷积核或掩膜）来改变像素值。原始图像、输出图像，卷积核大小。Size ksize 为一个滤波器（卷积核），它是一个小的二维矩阵，可以是任意尺寸，但通常是奇数尺寸，以便有一个中心像素。
  - 线性滤波的过程如下：
    1. 定义一个滤波器（卷积核），它是一个小的二维矩阵，可以是任意尺寸，但通常是奇数尺寸，以便有一个中心像素。
    2. 将滤波器放在图像上的每个像素位置，滤波器的中心与当前像素对齐。
    3. 对于滤波器覆盖的每个像素，将滤波器的值与其下对应像素值进行加权平均或其他数学操作，得到当前像素的新值。
    4. 重复步骤`b`和`c`，直到覆盖整个图像。
  
```c++
blur(image, blurredImage, Size(5, 5));
```

- 高斯滤波（GaussianBlur）: 采用高斯核对图像进行平滑，高斯核内像素的权重根据高斯分布来计算，越靠近中心的像素权重越大，越远离中心的像素权重越小，从而实现平滑效果。高斯模糊对于去除高频噪声效果较好。高斯核的大小越大，模糊程度越强，处理后的图像越平滑。相反，较小的核将产生较轻的模糊效果。
  - 高斯滤波的步骤如下：
    1. 定义一个高斯核，它是一个二维矩阵，通常是一个奇数大小的正方形。高斯核的大小（即矩阵的宽度和高度）影响着模糊的程度，较大的核会导致更强的模糊效果。 
    2. 高斯核的每个元素都表示一个权重值，这些权重值是根据高斯分布计算得出的。通常，越靠近中心的像素具有较大的权重，而越远离中心的像素权重逐渐减小。 
    3. 将高斯核放置在图像的每个像素位置上，滤波器的中心与当前像素对齐。 
    4. 对于滤波器覆盖的区域内的所有像素，将像素值与高斯核对应位置的权重值相乘，然后将所有结果相加，得到当前像素的新值。

```c++
GaussianBlur(image, GaussianblurredImage, Size(5, 5), 0);
```
    
- 中值滤波（medianBlur）: 将卷积核内的像素值取平均，用平均值来替代中心像素的值，实现简单的平滑。
  - 中值滤波的步骤如下：
    1. 定义一个滤波器（通常是一个奇数大小的正方形），以中心像素为准。 
    2. 将滤波器放置在图像的每个像素位置上，滤波器的中心与当前像素对齐。 
    3. 对于滤波器覆盖的区域内的所有像素，将像素值进行排序，然后取排序后的像素值的中值（如果滤波器大小是奇数，则中值就是中间像素值；如果大小是偶数，则中值是中间两个像素值的平均值）。 
    4. 将中值作为当前像素的新值。

```c++
medianBlur(image, medianblurredImage, 5);
```
- 双边滤波（bilateralFilter）: 将卷积核内的像素值按照大小排序，取其中值来替代中心像素的值，适用于去除噪声等。与线性滤波（如均值滤波、高斯滤波）不同，双边滤波考虑了像素间的空间距离和像素值相似性，从而能够更好地保留图像的边缘信息，同时有效地去除噪声。
  - 双边滤波的原理如下：
    1. 定义一个双边滤波核，它是一个二维矩阵，通常是一个奇数大小的正方形。双边滤波核包含两个权重：空间权重和像素值权重。 
    2. 双边滤波核的空间权重用于度量像素之间的空间距离。距离越近的像素具有较大的权重，距离越远的像素权重逐渐减小。 
    3. 双边滤波核的像素值权重用于度量像素值的相似性。像素值越接近的像素具有较大的权重，像素值差异较大的像素权重逐渐减小。 
    4. 将双边滤波核放置在图像的每个像素位置上，滤波器的中心与当前像素对齐。 
    5. 对于滤波器覆盖的区域内的所有像素，将像素值与双边滤波核对应位置的权重值相乘，然后将所有结果相加，得到当前像素的新值。

双边滤波通过同时考虑空间信息和像素值信息，能够在平滑图像的同时保留图像的边缘和细节，因此特别适合用于去除噪声并保持图像边缘的清晰度。
```c++
bilateralFilter(image, bilateralfilteredImage, 9, 75, 75);
```
- canny边缘检测 (canny) , 它总共有四个参数：原始图像、输出图像和两个阈值。
  - Canny边缘检测算法的步骤如下：
    1. 去噪：首先对图像进行高斯模糊来减少噪声的影响，从而降低假阳性边缘的产生。 
    2. 计算梯度：然后对模糊后的图像使用Sobel算子计算梯度，得到图像中每个像素的梯度强度和梯度方向。 
    3. 非极大值抑制：在图像中寻找局部梯度最大值，抑制非极大值，使得只有边缘上的像素点得到保留。 
    4. 双阈值检测：使用双阈值（高阈值和低阈值）来确定哪些边缘是真正的边缘。高阈值用于确定强边缘，低阈值用于确定弱边缘。 
    5. 边缘跟踪：最后，通过连接强边缘和与之相邻的弱边缘来形成完整的边缘。

Canny边缘检测的结果是一幅二值图像，其中边缘点被标记为白色，非边缘点被标记为黑色。
```c++
Canny(image, CannyedgesImage, 100, 200); // 100为低阈值，200为高阈值
```
- 霍夫变换 (HoughLines) , 它共有五个参数：原始图像、直线保存目的地、距离分辨率、角度分辨率和阈值。霍夫变换用于检测直线和其他参数化形状的方法。霍夫变换可以通过在参数空间进行累加来识别图像中的直线。它对于检测图像中的直线边缘非常有效，即使直线在图像中存在间断或被部分遮挡。
  - Hough变换的基本原理如下：
    1. 对图像中的每个边缘点（通常通过Canny边缘检测等方法获得）进行遍历。 
    2. 对于每个边缘点，在参数空间中建立一个投票。参数空间是由直线的参数（通常为极坐标形式）构成的空间。 
    3. 根据所有边缘点投票结果，在参数空间中找到得票最多的点。这些点对应于潜在的直线。 
    4. 根据参数空间中得票最多的点，反投影回原始图像，得到检测到的直线。

```c++
Canny(image, CannyedgesImage, 100, 200); // 100为低阈值，200为高阈值
HoughLines(CannyedgesImage, HoughLinesImage, 1, CV_PI / 180, 100); // 1为像素精度，CV_PI / 180为角度精度，100为投票阈值
```
- 膨胀（dilation）: 膨胀是形态学图像处理中的一种操作，它用于增加图像中明亮区域（前景）的大小。通常，膨胀操作会扩张图像中的白色区域（高亮区域），使其变得更大。

void dilate(InputArray src, OutputArray dst, InputArray kernel, Point anchor = Point(-1,-1), int iterations = 1, int borderType = BORDER_CONSTANT, const Scalar& borderValue = morphologyDefaultBorderValue() ):
- `src`：输入图像。这应该是一个8位单通道二进制图像（比如阈值化后的图像或边缘检测的结果），或者一个多通道图像的索引通道。
- `dst`：输出图像，与输入图像具有相同的大小和类型。 
- `kernel`：结构元素，用于指定膨胀操作的形状和大小。可以使用getStructuringElement函数创建结构元素。 
- `anchor`：锚点位置，用于指定结构元素的中心点。 
- `iterations`：可选参数，指定执行膨胀操作的次数。默认为1次。 
- `borderType`：可选参数，指定边界填充方式。默认为BORDER_CONSTANT，表示使用常数值进行填充。 
- `borderValue`：可选参数，用于指定边界填充的常数值。默认情况下，使用morphologyDefaultBorderValue()提供的默认值。

- 腐蚀 (erode) :用图像中的暗色部分"腐蚀"掉图像中的高亮部分。它是基于图像的结构元素（也称为内核或核）来操作图像像素的过程。腐蚀操作可以用于消除图像中的小白噪声，缩小白色区域（前景区域），或者分离连接在一起的物体。
  - Hough变换的基本原理如下：
    1. 将结构元素（内核）放置在图像上的每个像素位置上，将内核的中心与当前像素对齐。 
    2. 对于内核覆盖的每个像素，将内核与覆盖区域内的像素做逐元素的逻辑与操作。如果内核的所有元素与覆盖区域内的像素都匹配（即全为白色），则当前像素保持不变，否则当前像素被腐蚀为黑色。

腐蚀操作可以用于去除噪声、分割连接的物体或者改变物体的大小和形状。腐蚀操作会使白色区域缩小，黑色区域扩大，因此可以用来去除小的白色区域。

getStructuringElement(int shape, Size ksize, Point anchor = Point(-1, -1)):
- `shape`：指定结构元素的形状。它可以是以下之一：
  - `MORPH_RECT`：矩形结构元素。 
  - `MORPH_CROSS`：交叉形结构元素。 
  - `MORPH_ELLIPE`：椭圆形结构元素。
- `ksize`：指定结构元素的尺寸大小，即结构元素的宽度和高度。在本例中，Size(5, 5)表示一个5x5的结构元素。
- `anchor`：指定锚点（anchor）位置。锚点是结构元素的中心点，用于确定结构元素如何与图像进行卷积。默认情况下，锚点被设置为Point(-1, -1)，表示结构元素的中心。

```c++
// 定义结构元素（内核）
Mat kernel = getStructuringElement(MORPH_RECT,Size(5, 5)); // 5x5的矩形内核
//膨胀操作
dilate(CannyedgesImage, DilImage, kernel);
// 腐蚀操作
erode(image, erodedImage, kernel);
```

## 调整大小和裁剪

相关代码 [Chapter3_resize_crop.cpp](Chapter3_resize_crop.cpp)

使用`cv::resize()`函数来调整图像大小，使用ROI（感兴趣区域）来实现裁剪。将图像调整为指定的新宽度和高度。然后，我们使用`ROI`来裁剪图像，从原始图像中提取感兴趣的区域，并通过`.clone()`创建裁剪后的图像的副本，以免影响原始图像。

void resize(InputArray src, OutputArray dst, Size dsize, double fx = 0, double fy = 0, int interpolation = INTER_LINEAR): 
- `dsize`: 目标图像的尺寸，可以设置为Size(width, height)，也可以使用Size()，在这种情况下，通过fx和fy来指定缩放因子。
- `fx`: 沿水平方向的缩放因子。
- `fy`: 沿垂直方向的缩放因子。
- `interpolation`: 插值方法，默认为INTER_LINEAR，你还可以使用其他插值方法，例如INTER_NEAREST等。

```c++
resize(img, img_resize, Size(640, 480));
resize(img, img_resize, Size(), 0.5, 0.5);//在水平和垂直方向上都将图像缩小一半。
```

## 绘制形状和文本
相关代码 [Chapter4_draw_putext.cpp](Chapter4_draw_putext.cpp)

画圆

CV_EXPORTS_W void circle(InputOutputArray img, Point center, int radius,
const Scalar& color, int thickness = 1, int lineType = LINE_8, int shift = 0);
- `mg`: 输入输出图像，即要在其上绘制圆形的图像。
- `center`: 圆心的坐标，可以使用Point(x, y)来表示。
- `radius`: 圆的半径。
- `color`: 圆的颜色，可以使用Scalar(B, G, R)来表示，其中B、G、R分别代表蓝、绿、红三个通道的颜色强度。
- `thickness`: 圆边界的厚度。如果设置为负值，表示填充整个圆形，即实心圆。
- `lineType`: 圆边界的线型，默认为LINE_8。
- `shift`: 坐标点的小数点位数。


```c++
circle(img, Point(256, 256), 150, Scalar(0, 0, 255));
circle(img, Point(256, 256), 150, Scalar(0, 0, 255), FILLED);
// 与 FILLED 效果相同
circle(img, Point(256, 256), 150, Scalar(0, 0, 255), -1);
```

画直线
```c++
line(img, Point(126, 306), Point(386,306), Scalar(255, 255, 255), 2);
```
放置文本

void putText(InputOutputArray img, const String& text, Point org, int fontFace, double fontScale, const Scalar& color, int thickness = 1, int lineType = LINE_8, bool bottomLeftOrigin = false);
- `img`: 输入输出图像，即要在其上添加文本的图像。 
- `text`: 要添加的文本内容。 
- `org`: 文本的起始坐标，可以使用Point(x, y)来表示。 
- `fontFace`: 字体类型，例如FONT_HERSHEY_SIMPLEX、FONT_HERSHEY_PLAIN等。在这里使用了FONT_HERSHEY_SCRIPT_SIMPLEX，表示使用手写字体。 
- `fontScale`: 字体大小缩放因子。 
- `color`: 字体颜色，可以使用Scalar(B, G, R)来表示，其中B、G、R分别代表蓝、绿、红三个通道的颜色强度。 
- `thickness`: 字体边界的厚度。 
- `lineType`: 字体边界的线型，默认为LINE_8。 
- `bottomLeftOrigin`: 如果为true，文本起始坐标将被视为文本框左下角。如果为false，文本起始坐标将被视为文本框左上角。

```c++
putText(img, "Fighting!", Point(186, 282), FONT_HERSHEY_SCRIPT_SIMPLEX, 1.25, Scalar(255, 0, 0), 2 );
```
在img图像上添加了文本"Fighting!"，起始坐标为(186, 282)，使用手写字体FONT_HERSHEY_SCRIPT_SIMPLEX，字体大小缩放因子为1.25，字体颜色为蓝色(255, 0, 0)，边界厚度为2。

## 扭曲图像
相关代码[Chapter5_warp_image.cpp](Chapter5_warp_image.cpp)

扭曲图像是一种基于透视变换的操作，用于将图像从一个平面映射到另一个平面，从而实现图像的扭曲、旋转或倾斜。以下是扭曲图像的基本步骤：

1. 定义源图像的四个关键点: 首先，你需要定义源图像的四个关键点，这些关键点将被用于透视变换。这四个关键点可以是图像中的四个角点，也可以是图像中的任意四个点。这些关键点决定了透视变换的映射关系。
2. 定义目标图像的四个关键点: 接下来，你需要定义目标图像的四个关键点，这些关键点将决定扭曲后的图像的形状和位置。你可以根据需要选择合适的目标关键点位置。

3. 计算透视变换矩阵: 使用源图像的四个关键点和目标图像的四个关键点，通过 getPerspectiveTransform() 函数计算透视变换矩阵。透视变换矩阵将建立源图像与目标图像之间的映射关系。

4. 进行透视变换: 使用计算得到的透视变换矩阵，通过 warpPerspective() 函数对源图像进行透视变换。这将得到扭曲后的图像。


Mat getPerspectiveTransform(const Point2f src[], const Point2f dst[]):
- `src`: 输入图像的四个关键点，用 Point2f 类型的数组表示。
- `dst`: 输出图像的四个关键点，用 Point2f 类型的数组表示。

`getPerspectiveTransform()`函数计算从源图像到目标图像的透视变换矩阵 matrix。这个透视变换矩阵将源图像的四个顶点映射到目标图像的四个顶点，从而实现透视变换。
```c++
getPerspectiveTransform(src, dst);
```

`warpPerspective()`函数来进行透视变换。

void warpPerspective(InputArray src, OutputArray dst, InputArray M, Size dsize, int flags = INTER_LINEAR, int borderMode = BORDER_CONSTANT, const Scalar& borderValue = Scalar()):
- `src`: 输入图像，即要进行透视变换的原始图像。
- `dst`: 输出图像，即透视变换后的图像。
- `M`: 变换矩阵，即3x3的透视变换矩阵。
- `dsize`: 输出图像的尺寸，可以使用Size(width, height)来表示。
- `flags`: 插值方法，默认为INTER_LINEAR，你还可以使用其他插值方法，例如INTER_NEAREST等。
- `borderMode`: 边界处理方式，默认为BORDER_CONSTANT，表示使用常数填充边界。
- `borderValue`: 填充边界的颜色值，默认为Scalar()，即使用黑色填充边界。

## 颜色检测
将图像转换为 `HSV` 颜色空间，并创建一个窗口，其中包含一组滑动条（Trackbars），允许用户调整颜色阈值的下限和上限。接着，代码在循环中实时根据滑动条的值生成`二值掩膜图像（mask）`，并将原始图像、HSV 转换后的图像和生成的二值掩膜图像实时显示在窗口中。

HSV 颜色空间中，有三个通道，即色相 (Hue)、饱和度 (Saturation) 和明度 (Value)。

用户可以通过移动滑动条来调整不同颜色通道（Hue、Saturation 和 Value）的下限和上限，从而在实时显示中选择特定颜色区域。

创建窗口
```c++
namedWindow("Trackbars", (640, 200));
```

创建一个滑动条
```c++
createTrackbar("Hue Min", "Trackbars", &hmin,179 );
```
`Scalar` 对象可以用来表示多通道数据, 使用 Scalar 对象来表示颜色的下限和上限阈值。

```c++
Scalar lower(hmin, smin, vmin);
Scalar upper(hmax, smax, vmax);
```

`inRange() 函数`是 OpenCV 中用于实现颜色范围选择（或颜色阈值化）的函数。它将根据指定的颜色下限和上限阈值，将输入图像中在阈值范围内的像素设置为白色（255），不在阈值范围内的像素设置为黑色（0），从而生成一个二值掩膜图像。

void inRange(InputArray src, InputArray lowerb, InputArray upperb, OutputArray dst):
- `src`: 输入的图像，通常是转换为 HSV 颜色空间后的图像。
- `lowerb`: 包含了颜色阈值`下限`的 `Scalar 对象`，表示允许的最小颜色值。
- `upperb`: 包含了颜色阈值`上限`的 `Scalar 对象`，表示允许的最大颜色值。
- `dst`: 输出的二值掩膜图像，用于存储处理后的结果。

```c++
inRange(imageHSV, lower, upper, mask);
```

## 形状/轮廓检测

相关代码[Chapter7_contour_detection.cpp](Chapter7_contour_detection.cpp)

形状/轮廓检测的一般步骤如下：
1. 读取图像：首先，使用 OpenCV 的图像读取函数（如 `imread()`）读取待处理的图像。确保图像是二值化的或者经过边缘检测等预处理步骤，以便更好地进行轮廓检测。 
2. 图像预处理（可选）：根据实际情况，可能需要对图像进行一些预处理，例如灰度化、高斯模糊、二值化或边缘检测，以便更好地突出图像中的轮廓。 
3. 查找轮廓：使用`findContours()` 函数查找图像中的轮廓。该函数将返回一个包含所有轮廓点集的向量（`vector<vector<Point>>`），并可选地返回轮廓的层次结构信息。 
4. 绘制轮廓（可选）：根据需要，可以使用`drawContours()` 函数将找到的轮廓绘制到原始图像上或创建一个新的图像并在其中绘制轮廓。 
5. 轮廓属性和过滤（可选）：根据应用的需要，可能需要对找到的轮廓进行进一步处理。可以计算轮廓的面积、周长、边界框、最小包围圆等属性，并根据特定的属性对轮廓进行过滤。

`findContours()` 是 OpenCV 库中用于查找图像中物体轮廓的函数。它能够找到二值图像（通常是经过阈值化或边缘检测后的图像）中所有物体的轮廓，并将这些轮廓作为一组点的列表返回。

void findContours(InputOutputArray image, OutputArrayOfArrays contours, OutputArray hierarchy, int mode, int method, Point offset = Point()):
- `image`：输入的二值图像。通常为8位单通道图像（0表示背景，255表示前景），但也可以是32位浮点型图像。 
- `contours`：存储轮廓的容器。它是一个向量（vector）数组，每个向量包含一个轮廓的点集。 每个轮廓点集都表示为一个 `std::vector<cv::Point>`，其中 `Point` 是 OpenCV 中表示二维点的数据结构。每个点包含 `x` 和 `y` 坐标，表示轮廓上的一个点。
- `hierarchy`：可选参数，用于存储图像轮廓的层次结构信息。它是一个包含层次信息的向量数组。 每个元素是一个包含四个整数值的向量（`std::vector<cv::Vec4i>`），表示每个轮廓的层次结构信息。这些整数值分别为： 
  - `hierarchy[i][0]`：后一个轮廓的索引。如果没有下一个轮廓，则为 `-1`。 
  - `hierarchy[i][1`]：前一个轮廓的索引。如果没有前一个轮廓，则为 `-1`。 
  - `hierarchy[i][2]`：子轮廓的索引。如果没有子轮廓，则为 `-1`。 
  - `hierarchy[i][3]`：父轮廓的索引。如果没有父轮廓，则为 `-1`。
- `mode`：表示轮廓检索模式的整数值。它决定了要提取所有轮廓还是只提取外部轮廓，以及如何组织轮廓的层次结构。常用的取值为：
  - `RETR_EXTERNAL`：仅提取最外层的轮廓。 
  - `RETR_LIST`：提取所有轮廓，但不建立层次结构。 
  - `RETR_TREE`：提取所有轮廓，并建立完整的层次结构。
- `method`：表示轮廓近似方法的整数值。它决定了如何压缩轮廓的点集，以便更有效地表示轮廓形状。常用的取值为：
  - `CHAIN_APPROX_NONE`：保留所有的轮廓点。 
  - `CHAIN_APPROX_SIMPLE`：压缩水平、垂直和对角线部分，仅保留端点。
- `offset`：可选参数，表示返回轮廓点坐标时的偏移量

```c++
// 每个向量包含一个轮廓的点集
vector<vector<Point>> contours;
// 四个整数值的向量
vector<Vec4i> hierarchy;  

// RETR_EXTERNAL 仅提取最外层的轮廓, CHAIN_APPROX_SIMPLE 仅保留端点
findContours(imgDil, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);

// -1 表示绘制了所有找到的轮廓。
drawContours(img, contours, -1, Scalar(0, 0, 255), 2);
```

识别形状

opencv中的坐标系，x，y，h，w。`(0, 0)` 表示左上角的像素点，`x` 表示水平方向的坐标，向右递增，`y `表示垂直方向的坐标，向下递增, 如下图所示。
![x_y.png](readme_pictrue%2Fx_y.png)

`contours`是轮廓点集数据。多边形逼近和边界矩形计算。

`conPoly`：这是一个向量（`std::vector`）数组，每个向量存储着对应的轮廓点集进行多边形逼近后的结果。每个向量中存储的是一个逼近多边形的点集，即 `std::vector<cv::Point>`。

`boundRect`：这是一个向量（`std::vector`）数组，每个向量存储着对应的轮廓的边界矩形。每个矩形由 `cv::Rect` 类型表示，其中包含了矩形的位置（左上角坐标）和尺寸（宽度和高度）。
- `boundRect[i].width` 表示第 `i` 个矩形的宽度，即矩形在` x 轴（水平方向）`上的长度。
- `boundRect[i].height` 表示第 `i `个矩形的高度，即矩形在` y 轴（垂直方向`）上的长度。
- `boundRect[i].tl()` 表示第` i `个矩形的左上角坐标点（`x, y）`，
- `boundRect[i].br()` 表示第` i `个矩形的右下角坐标点`（x, y`）。

```c++
vector<vector<Point>> conPoly(contours.size());
vector<Rect> boundRect(contours.size());
```

在多边形逼近之后，我们可能会对逼近后的多边形进行一些处理或者计算其属性，而边界矩形则是对轮廓的一个简单包围，用于快速获取轮廓的外接矩形。

`arcLength`以计算给定轮廓点集的封闭或非封闭多边形的周长。

double arcLength(InputArray curve, bool closed):
- `curve`：输入的轮廓点集。这是一个 `std::vector<cv::Point>`。
- `closed`：一个布尔值，用于指定轮廓是否为闭合形状。如果设置为 `true`，表示轮廓被视为闭合形状，计算的是封闭多边形的周长。如果设置为 `false`，则计算的是非封闭多边形的周长。

`approxPolyDP` 函数用于对当前轮廓点集进行多边形逼近，并将逼近后的多边形点集存储在 conPoly[i] 中。该函数将用多边形逼近替代原始轮廓，以减少轮廓中的点数并简化形状，从而节省内存和计算资源。
```c++
void cv::approxPolyDP(
    InputArray curve,     // 输入曲线（轮廓）
    OutputArray approxCurve,  // 输出近似的多边形曲线
    double epsilon,       // 控制逼近精度的参数
    bool closed           // 是否将曲线视为闭合曲线
);

```
```c++
approxPolyDP(contours[i], conPoly[i], 0.02 * peri, true);
```
`contours[i]` 是输入的轮廓点集，`conPoly[i]` 是输出的逼近多边形点集。`0.02 * peri` 是逼近精度参数，`peri` 是通过 `arcLength(contours[i], true)` 计算得到的轮廓的周长。逼近精度值越小，逼近的多边形与原始轮廓越接近。

```c++
//void rectangle(Mat& img, Point pt1, Point pt2, const Scalar& color, int thickness = 1, int lineType = LINE_8, int shift = 0)
rectangle(img, boundRect[i].tl(), boundRect[i].br(), Scalar(0, 255, 0), 5);
```
- `boundRect[i].tl()` 表示第` i `个矩形的左上角坐标点（`x, y）`, `tl`(top left)，
- `boundRect[i].br()` 表示第` i `个矩形的右下角坐标点`（x, y`）, `br`(bottom right)。

## 人脸检测
相关代码[Chapter8_face_detection.cpp](Chapter8_face_detection.cpp)

OpenCV人脸检测原理

OpenCV采用的是基于Haar的cascade分类器。

基于Haar特征的cascade分类器是Paul Viola和 Michael Jone在2001年发表的论文”Rapid Object Detection using a Boosted Cascade of Simple Features”中提出的一种有效的物品检测方法。它是一种机器学习方法，通过许多正负样例中训练得到cascade方程，然后将其应用于其他图片。

和许多分类器的训练一样，需要根据大量正样例(包含人脸的图片)和负样例(不包含人脸的图片)来进行训练，由此在这些图片中获取特征。 

Haar特征包含三种：边缘特征、线性特征、中心特征和对角线特征。每种分类器都从图片中提取出对应的特征。
![haar.png](cmake-build-debug%2FResources%2Fhaar.png)

OpenCV 人脸检测通常包含以下步骤：
1. 载入级联分类器模型：首先，需要从 OpenCV 载入一个预训练的人脸级联分类器模型。这些模型通常以 XML 文件格式存储，其中包含用于人脸检测的特征信息。 
2. 读取图像：将待检测的图像读取为 OpenCV 的 Mat 对象。确保图像的路径正确，并且图像能够被成功读取。 
3. 转换为灰度图像：人脸检测通常在灰度图像上进行，因此需要将彩色图像转换为灰度图像。可以使用 cv::cvtColor() 函数来进行转换。 
4. 人脸检测：使用 `CascadeClassifier 类`的 `detectMultiScale()` 函数在灰度图像上进行人脸检测。该函数将返回检测到的人脸矩形区域。 
5. 绘制检测结果：将检测到的人脸位置在原始图像上用矩形框标记出来，以便可视化和显示检测结果。

OpenCV自带的Harr级联分类器是成熟的分类器，并不需要再次进行训练，所需代码量非常少。

OpenCV包含许多训练好的分类器，比如脸、眼、微笑等。这些XML文件存储在[opencv/data/haarcascades/](https://github.com/opencv/opencv/tree/master/data/haarcascades)文件夹中，使用时可以直接调取。

`CascadeClassifie`r 是 OpenCV 中用于级联分类器的类。在计算机视觉中，级联分类器是一种用于对象检测的强大工具，特别是在人脸检测中广泛使用。

`faceCascade.detectMultiScale()` 是 OpenCV 中 `CascadeClassifier 类`的一个函数，用于进行对象检测，特别是人脸检测。

void CascadeClassifier::detectMultiScale(InputArray image, std::vector<Rect>& objects, double scaleFactor = 1.1, int minNeighbors = 3, int flags = 0, Size minSize = Size(), Size maxSize = Size()):
- `image`: 输入图像，可以是灰度图像或彩色图像。
- `objects`: 输出参数，用于存储检测到的对象的矩形框，通常用于存储检测到的人脸矩形。
- `scaleFactor`: 缩放因子，用于控制图像金字塔的缩放程度。
- `minNeighbors`: 最小近邻数，用于合并检测到的相邻矩形框。
- `flags`: 额外标志，一般设置为0即可。
- `minSize`: 最小检测窗口的大小。
- `maxSize`: 最大检测窗口的大小

```c++
faceCascade.detectMultiScale(grayImage, faces, 1.1, 3, 0, cv::Size(30, 30));
```
- `grayImage`: 这是输入图像，它是一个灰度图像。人脸检测通常在灰度图像上进行，因为在灰度图像中计算更加高效，而且人脸的特征可以很好地表现在亮度变化上。
- `faces`: 这是一个输出参数，用于存储检测到的人脸矩形区域。`detectMultiScale` 函数会将检测到的人脸矩形存储在这个容器中，每个矩形由 `cv::Rect `类型表示。
- `1.1`: 这是缩放因子（scale factor）。在进行人脸检测时，级联分类器会采用滑动窗口的方式在不同尺度下检测人脸。这个参数指定了每次缩放窗口的比例，通常为 1.1，表示每次将窗口尺寸增加 10%。
- `3`: 这是最小近邻数（minNeighbors）。在多尺度检测的过程中，可能会检测到多个相邻的候选框，这些候选框可能对应同一个人脸。为了避免重复检测，设置这个参数可以合并相邻的候选框。这个参数指定了在合并候选框时允许的最小近邻数。
- `0`: 这是额外标志（flags）的一个可选参数。在人脸检测中，通常不需要设置这个参数，因此可以将其设为 `0`。
- `Size(30, 30)`: 这是最小检测窗口的大小。在检测过程中，级联分类器会使用不同大小的滑动窗口，以便检测不同尺寸的人脸。这个参数指定了最小窗口的大小，如果检测到的矩形框小于这个值，将被忽略。

## 车牌识别
级联分类器是一种机器学习模型，特别适用于对象检测任务。它使用 Haar 特征（Haar-like features）来检测图像中的目标。Haar 特征是一种类似于滤波器的特征，可以用于检测图像中的边缘、纹理等信息。级联分类器通过组合多个 Haar 特征，并应用级联的方式来高效地检测目标。
[Project_plate_recognition.cpp](Project_plate_recognition.cpp)

主要使用`haarcascade_russian_plate_number.xml` 是一个已经训练好的级联分类器模型
```c++
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/objdetect.hpp>
#include <iostream>

using namespace std;
using namespace cv;

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
            imwrite("Resources/plates/"+to_string(i)+".png", imgcrop);
            rectangle(img, plates[i].tl(), plates[i].br(), Scalar(0,0, 255), 5);
        }
        imshow("Image", img);
        waitKey(1);
    }

    return 0;
}
```
请注意，在使用级联分类器进行车牌检测时，结果可能不是完美的，可能会有一些误检或漏检。根据应用场景和数据集的不同，您可能需要对级联分类器的参数进行调整或使用其他技术来进一步优化检测效果。

