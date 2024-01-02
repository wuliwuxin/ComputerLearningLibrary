# 医学分类公开数据集
## [ODIR-5K](https://www.kaggle.com/datasets/andrewmvd/ocular-disease-recognition-odir5k)

Ocular Disease Intelligent Recognition (ODIR) is a structured ophthalmic database of 5,000 patients with age, color fundus photographs from left and right eyes and doctors' diagnostic keywords from doctors.

This dataset is meant to represent ‘‘real-life’’ set of patient information collected by Shanggong Medical Technology Co., Ltd. from different hospitals/medical centers in China. In these institutions, fundus images are captured by various cameras in the market, such as Canon, Zeiss and Kowa, resulting into varied image resolutions.
Annotations were labeled by trained human readers with quality control management. They classify patient into eight labels including:

眼科疾病智能识别（ODIR）是一个结构化的眼科数据库，包括5000名患者的年龄、左眼和右眼的彩色眼底照片以及医生的诊断关键词。
这个数据集旨在代表上工医疗科技有限公司从中国不同的医院/医疗中心收集的 "真实生活 "的病人信息集。在这些机构中，眼底图像是由市场上的各种相机拍摄的，如佳能、蔡司和高华，从而产生不同的图像分辨率。
注释是由经过培训的人类读者在质量控制管理下进行标记的。他们将病人分为八个标签，包括。

- Normal (N),正常（N）
- Diabetes (D),糖尿病（D）
- Glaucoma (G),青光眼（G）
- Cataract (C),白内障（C）
- Age related Macular Degeneration (A),年龄相关性黄斑变性（A）
- Hypertension (H),高血压（H）
- Pathological Myopia (M),病理性近视（M）
- Other diseases/abnormalities (O),其他疾病/异常情况 (O)

## [南开大学参研OIA系列数据集](https://github.com/nkicsl/OIA)

- [OIA-DDR数据集](https://github.com/nkicsl/DDR-dataset)
    OIA-DDR数据集包含13673张眼底图像，是我国目前最大的公开眼底图像数据集，包含了四种糖尿病视网膜病变相关的病变点的标注，757张包含像素级和bounding-box级的病变点标注。
    If you make use of the DDR dataset, please cite our following paper:


- [OIA-ODIR数据集](https://github.com/nkicsl/OIA-ODIR)
    OIA-ODIR数据集包含10000张眼底图像，取样人群年龄涵盖全年龄段人群，其中30周岁至80周岁的人群占比超过96%；该数据主要针对眼部多疾病同步诊断，每张眼底图像包含8个疾病标签，分别为：正常N、糖网病D、青光眼G、白内障C、老年黄斑变性A、高血压H、近视M、其他疾病/异常O。OIA-ODIR数据集是国际上首次推出的基于一张眼底图像的多类型病变检测数据集。



除了OIA-DDR和OIA-ODIR之外，OIA系列数据集预计在近两年推出针对青光眼、黄斑变性、白内障和更多眼底疾病种类的数据。

OIA是基于临床环境的、高质量的、数据样本充分的系列数据集，填补了中国眼科图像数据领域的空白，已经发布的OIA-DDR和OIA-ODIR在世界上位于公开数据集前列，主要特色有：临床医生对患者图像-文字诊断关联结构的专家标注、7种不同眼底疾病标签的多病变标签（世界首次）、全国26个省400余家医院的临床数据来源、757张图片含病变点标注(世界第一)、4种糖尿病视网膜病变相关病变点超精细标注、42种不同品牌相机拍摄的兼容度、专家组标注和中国医学装备协会认证等。



    @article{LI2019,
      title = "Diagnostic Assessment of Deep Learning Algorithms for Diabetic Retinopathy Screening",
      author = "Tao Li and Yingqi Gao and Kai Wang and Song Guo and Hanruo Liu and Hong Kang",
      journal = "Information Sciences",
      volume = "501",
      pages = "511 - 522",
      year = "2019",
      issn = "0020-0255",
      doi = "https://doi.org/10.1016/j.ins.2019.06.011",
      url = "http://www.sciencedirect.com/science/article/pii/S0020025519305377",
    }


## BCNB乳腺癌穿刺活检

Early Breast Cancer Core-Needle Biopsy WSI (BCNB) Dataset，早期乳腺癌患者的穿刺活检WSI。在早期乳腺癌患者的病理WSI中，标注了部分的肿瘤区域，并提供了一些临床信息（age, tumor size, tumor type, ER, PR, HER2, HER2 expression, histological grading, surgical, Ki67, molecular subtype, number of lymph node metastases, label）

- disease-free axilla (无病 N0):655
- low metastatic burden of axillary disease (低转移 N+(1-2)):210
- heavy metastatic burden of axillary disease (重度转移 N+(≥3)):193

sum: 1058

[挑战比赛](https://bcnb.grand-challenge.org/)
            
    @article{xu2021predicting,
      title={Predicting Axillary Lymph Node Metastasis in Early Breast Cancer Using Deep Learning on Primary Tumor Biopsy Slides},
      author={Xu, Feng and Zhu, Chuang and Tang, Wenqi and Wang, Ying and Zhang, Yu and Li, Jie and Jiang, Hongchuan and Shi, Zhongyue and Liu, Jun and Jin, Mulan},
      journal={Frontiers in Oncology},
      pages={4133},
      year={2021},
      publisher={Frontiers}
    }


## [ISIC2018_TASK3](https://challenge.isic-archive.com/data/#2018)

[isic比赛](https://challenge.isic-archive.com/)

训练集有10015张皮肤病理图像，有七类。

ISIC 2018：皮肤病变分析对黑色素瘤的检测

官网介绍 https://challenge.isic-archive.com/landing/2018/47/

    Melanoma(MEL)
    Melanocytic nevus(NV)
    Basal cell carcinoma(BCC)
    Actinic keratosis / Bowen’s disease (intraepithelial carcinoma)
    Benign keratosis (solar lentigo / seborrheic keratosis / lichen planus-like keratosis)
    Dermatofibroma
    Vascular lesion

官网训练集isic2018train每一类的名称和数量如下表格所示
|疾病名称|图像数量|
| ----------- | ----------- |
|MEL|1113|
|NV|6705|
|BCC|514|
|AKIEC|327|
|BKL|1099|
DF|115|
|VASC|142|

官网训练集isic2018validate每一类的名称和数量如下表格所示
|疾病名称|图像数量|
| -----| ------|
|MEL|21|
|NV|123|
|BCC|15|
|AKIEC|8|
|BKL|22|
|DF|1|
|VASC|3|

此外，每一张图片都是600x450的尺寸三通道RGB图像。图像的label由一个groundtruth的csv文件提供。

比赛的难点
1.类别不均衡：图片数量最少的两类只有100多张图片，数量最多的一类多达6000张。
2.疾病区域大小不同：在某些图片中疾病的区域很小，而在某些图片中疾病占满了整张图片。

### imbalance
这个分类比赛最重要的难点就是类别不均衡，比赛官方采用Mean class accuracy来进行排名。这意味着当模型判断不准一个图片只是简单把它认为它属于数量最多的那一类，因为这样虽然会使模型在整个测试集上准确率很高，但是在较少数量类上的准确率会很低，进而导致Mean class accuracy(MCA)很低。

通常在分类任务中解决类别不均衡的一个方法就是修改loss function，pytorch可以很方便地修改loss function使其加上class weight。在我们的实验中发现加入class weight是一个非常有效的方法。

为了解决样本不均衡，我们尝试了其他很多种方法比如不使用class weight使用Focal Loss论文地址来作为模型的Loss function, 经过多个模型的测试发现相比class weight，Focal loss可以使整个模型在不同epoch时模型在测试集的准确率波动较小，但是峰值比不过class weight的结果。

其次我们还尝试了一些其他的idea，比如Multiscale，将不同尺寸的图片送入网络进行分类，将预测结果结合起来，这个会有一定的效果。

[GitHub上的一个解决方案](https://github.com/yuanqing811/ISIC2018)

MSK Dataset: (c) Anonymous; https://arxiv.org/abs/1710.05006; https://arxiv.org/abs/1902.03368

        [1] Noel Codella, Veronica Rotemberg, Philipp Tschandl, M. Emre Celebi, Stephen Dusza, David Gutman, Brian Helba, Aadi Kalloo, Konstantinos Liopyris, Michael Marchetti, Harald Kittler, Allan Halpern: "Skin Lesion Analysis Toward Melanoma Detection 2018: A Challenge Hosted by the International Skin Imaging Collaboration (ISIC)", 2018; https://arxiv.org/abs/1902.03368

        [2] Tschandl, P., Rosendahl, C. & Kittler, H. The HAM10000 dataset, a large collection of multi-source dermatoscopic images of common pigmented skin lesions. Sci. Data 5, 180161 doi:10.1038/sdata.2018.161 (2018).
