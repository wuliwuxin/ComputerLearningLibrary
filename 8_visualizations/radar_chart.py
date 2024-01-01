#!/usr/bin/python3
# -*- coding: utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt
import matplotlib as mpl
mpl.rcParams['font.family'] = 'Times New Roman'
# mpl.rcParams['font.family'] = 'SimHei'  # 显示中文字

# 模型名称列表
model_names = ['Model 1', 'Model 2', 'Model 3', 'Model 4', 'Model 5']
# 标签列表
class_labels = ['Label 1', 'Label 2', 'Label 3', 'Label 4',
                'Label 5', 'Label 6', 'Label 7', 'Label 8']
# AUC 数据
auc_values = np.array([
    [79.31, 75.26, 82.51, 91.96, 76.15, 73.10, 89.94, 88.19],
    [80.01, 82.97, 89.33, 97.37, 80.69, 79.35, 93.09, 92.72],
    [77.42, 74.16, 85.96, 96.95, 79.63, 81.14, 93.20, 91.57],
    [83.02, 85.95, 89.96, 97.29, 77.26, 78.58, 92.37, 91.55],
    [87.92, 89.68, 92.02, 99.18, 85.18, 81.41, 93.88, 98.09]
])
# 计算均值
mean_auc_values = np.mean(auc_values, axis=1)

# 设置画布大小
plt.figure(figsize=(8, 8))

# 计算每个类别的角度范围
angles = np.linspace(0, 2 * np.pi, len(class_labels), endpoint=False).tolist()
angles += angles[:1]  # 闭合环形

# 定义颜色和标记符号
# colors = ['b', 'g', 'c', 'm', 'y', 'k', 'orange', 'purple', 'brown', 'gray', 'r']
# markers = ['o', 's', 'D', '^', 'v', 'p', 'P', '*', 'h', 'H', 'x']
colors = ['b', 'g', 'c', 'gray', 'r']
markers = ['o', 's', 'D', 'H', 'x']

# 画每个 model 的 AUC 值
for i, model in enumerate(model_names):
    values = auc_values[i, :].tolist()
    values += [values[0]]
    plt.polar(angles, values, color=colors[i % len(colors)], marker=markers[i % len(markers)], label=model)
# 设置刻度标签
plt.xticks(angles[:-1], class_labels)

# 调整标签的倾斜角度和位置
for label, angle in zip(plt.gca().xaxis.get_ticklabels(), angles[:-1]):
    # label.set_rotation(np.degrees(angle) - 90)
    label.set_y(-0.01)
    label.set_fontweight('bold')  # 加粗标签字体

# 设置标题
plt.title('AUC values for different models')

# 设置图例
legend_labels = ['{}, AUC = {:.2f}%'.format(name, auc) for name, auc in zip(model_names, mean_auc_values)]
num_columns = 3  # 每行显示的图例数量
num_rows = int(np.ceil(len(legend_labels) / num_columns))  # 计算行数

# 计算图例放置位置
legend_x = 0.5
legend_y = -0.1

# 绘制图例
plt.legend(legend_labels, loc='center', bbox_to_anchor=(legend_x, legend_y), ncol=num_columns,
           fontsize=10)

# 显示图形
plt.show()
