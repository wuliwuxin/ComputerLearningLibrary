# 柱状图
import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.axes_grid1.inset_locator import inset_axes
import matplotlib as mpl

mpl.rcParams['font.family'] = 'times new roman'
# 设置字体
plt.rcParams['font.family'] = ['Times New Roman']
data = {
    'Labels': ['Label 1', 'Label 2', 'Label 3', 'Label 4','Label 5'],
    'Number of images': [790, 1122, 2240, 979, 529]
}

df = pd.DataFrame(data)

# 设置颜色
colors = ['#1f77b4', '#ff7f0e', '#2ca02c', '#d62728', '#9467bd']

# 绘制柱状图
fig, ax = plt.subplots(figsize=(10, 6))
bars = ax.bar(df['Labels'], df['Number of images'], color=colors[:len(df)])

# 设置x轴和y轴的字体大小
plt.xticks(fontsize=16)
plt.yticks(fontsize=16)
# 设置 x 轴和 y 轴的标签
ax.set_xlabel('Labels for Data', fontsize=18)
ax.set_ylabel('Number', fontsize=18)

# 旋转 x 轴标签
# plt.xticks(rotation=25)
# plt.subplots_adjust(bottom=0.183)

# 添加数值标签
for bar in bars:
    height = bar.get_height()
    ax.text(bar.get_x() + bar.get_width() / 2, height, height, ha='center', va='bottom', fontsize=14)


# 显示图形
plt.show()
