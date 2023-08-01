
## ODIR-5k眼科数据集
class_label文件夹
- Normal(N):2873
- DR(D):1608
- Glaucoma(G):284
- Cataract(C):293
- AMD(A):266
- Hypertension(H):128
- Myopia(M):232
- Others(O):708

图片预处理后的原图片preprocessed_images

sum：6392

- label_extraction_all.py
    提取csv文件的图片文件名和label信息保存到txt文件
- label_extration_se.py
    只需要提取想要的label的图片（比如只要label为N和D的）
- copy.py
    按label名给图片分类到不同文件夹
    
[数据集划分、label生成及按label将图片分类到不同文件夹](https://blog.csdn.net/weixin_43760844/article/details/113944260)
