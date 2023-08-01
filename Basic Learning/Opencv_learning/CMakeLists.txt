cmake_minimum_required(VERSION 3.26)
project(opencv_demo)

set(CMAKE_CXX_STANDARD 11)

# 读取OpenCV构建环境配置文件
find_package(OpenCV)
# 设置与OpenCV相关的包含目录的路径
include_directories(${OpenCV_INCLUDE_DIRS})

# add_executable()和target_linnk_libraries()顺序不能写错

# 可配置的文本名称
add_executable(Chapter1_read_Images Chapter1_read_Images.cpp)
# 加载链接库
target_link_libraries(Chapter1_read_Images ${OpenCV_LIBS})

add_executable(Chapter1_read_videos Chapter1_read_videos.cpp)
target_link_libraries(Chapter1_read_videos ${OpenCV_LIBS})

add_executable(Chapter1_read_webcam Chapter1_read_webcam.cpp)
target_link_libraries(Chapter1_read_webcam ${OpenCV_LIBS})

add_executable(Chapter2_basic_function Chapter2_basic_function.cpp)
target_link_libraries(Chapter2_basic_function ${OpenCV_LIBS})

add_executable(Chapter3_resize_crop Chapter3_resize_crop.cpp)
target_link_libraries(Chapter3_resize_crop ${OpenCV_LIBS})

add_executable(Chapter4_draw_putext Chapter4_draw_putext.cpp)
target_link_libraries(Chapter4_draw_putext ${OpenCV_LIBS})

add_executable(Chapter5_warp_image Chapter5_warp_image.cpp)
target_link_libraries(Chapter5_warp_image ${OpenCV_LIBS})

add_executable(Chapter6_color_detection Chapter6_color_detection.cpp)
target_link_libraries(Chapter6_color_detection ${OpenCV_LIBS})

add_executable(Chapter7_contour_detection Chapter7_contour_detection.cpp)
target_link_libraries(Chapter7_contour_detection ${OpenCV_LIBS})

add_executable(Chapter8_face_detection Chapter8_face_detection.cpp)
target_link_libraries(Chapter8_face_detection ${OpenCV_LIBS})

add_executable(try try.cpp)
target_link_libraries(try ${OpenCV_LIBS})

add_executable(Project_plate_recognition Project_plate_recognition.cpp)
target_link_libraries(Project_plate_recognition ${OpenCV_LIBS})