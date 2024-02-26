# -*- coding: utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt
import os
import matplotlib as mpl
mpl.rcParams['font.family'] = 'times new roman'  # 设置字体为Times New Roman

root_path = 'ROC_AUC/'  # 根路径，存储ROC和AUC数据的文件夹
num_class = 7  # 类别数量

model_name = ['Model 1', 'Model 2', 'Model 3', 'Model 4', 'Model 5']  # 模型名称列表
# 为每个模型定义自定义颜色
colors = ['b', 'g', 'brown', 'gray', 'r']

# 初始化绘图
plt.figure(figsize=(8, 6))

# 遍历每个模型
for i, model in enumerate(model_name):
    model_path = os.path.join(root_path, model_name[i])
    # 加载fprs（假阳性率）, tprs（真阳性率）, 和roc_auc（ROC曲线下面积）
    fprs = np.load(model_path + '/fprs.npy', allow_pickle=True)
    roc_auc = np.load(model_path + '/roc_auc.npy', allow_pickle=True)
    tprs = np.load(model_path + '/tprs.npy', allow_pickle=True)
    all_fpr = np.unique(np.concatenate([fprs[i] for i in range(num_class)]))
    roc_auc = np.mean(roc_auc)
    # 插值所有ROC曲线在这些点
    mean_tpr = np.zeros_like(all_fpr)
    for j in range(num_class):
        mean_tpr += np.interp(all_fpr, fprs[j], tprs[j])
    # 最后平均它并计算AUC
    mean_tpr /= num_class
    # 遍历当前模型的每个曲线
    plt.plot(all_fpr, mean_tpr, label='{}, AUC = {:.2f}%'.format(model, roc_auc*100), color=colors[i % len(colors)])

# 添加图例、标题和轴标签
# plt.legend()
# 设置legend的字号大小
plt.legend(prop = {'size':14})
plt.xlabel('False Positive Rate', fontsize=16)  # x轴标签
plt.ylabel('True Positive Rate', fontsize=16)  # y轴标签

# 设置坐标轴刻度的字体大小
plt.tick_params(axis='both', which='major', labelsize=14)  # 主刻度
plt.tick_params(axis='both', which='minor', labelsize=10)  # 次刻度

# plt.xlabel('1-特异度', fontsize=16)
# plt.ylabel('召回率', fontsize=16)
plt.title('AUC values for different models', fontsize=20)  # 标题
plt.plot([0, 1], [0, 1], 'k--', lw=1)  # 绘制对角虚线

# 显示绘图
plt.show()
# plt.savefig('chest14_ROC.tif', dpi=300)  # 可以保存绘图
