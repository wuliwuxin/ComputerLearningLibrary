# 带饼状图的柱状图
import pandas as pd
import matplotlib.pyplot as plt
from mpl_toolkits.axes_grid1.inset_locator import inset_axes
import matplotlib as mpl

mpl.rcParams['font.family'] = 'times new roman'
# 设置字体
plt.rcParams['font.family'] = ['Times New Roman']

data = {
    'Labels': ['Label 1', 'Label 2', 'Label 3', 'Label 4',
                'Label 5', 'Label 6', 'Label 7', 'Label 8'],
    'Number of images': [790, 1122, 2240, 979, 529, 2748, 4797, 3195]
}

df = pd.DataFrame(data)

# 设置颜色
colors = ['#1f77b4', '#ff7f0e', '#2ca02c', '#d62728', '#9467bd', '#8c564b', '#e377c2', '#aec7e8']

# 绘制柱状图
fig, ax = plt.subplots(figsize=(10, 6))
bars = ax.bar(df['Labels'], df['Number of images'], color=colors[:len(df)])

ax.set_xlabel('Labels for Data', fontsize=14)
ax.set_ylabel('Number', fontsize=14)

# 旋转 x 轴标签
# plt.xticks(rotation=25)
# plt.subplots_adjust(bottom=0.183)

# 添加数值标签
for bar in bars:
    height = bar.get_height()
    ax.text(bar.get_x() + bar.get_width() / 2, height, height, ha='center', va='bottom')

# 创建新的 Axes 对象，并绘制饼状图
ax2 = inset_axes(ax, width="50%", height="50%", loc='upper left')
ax2.pie(df['Number of images'], radius=1, labeldistance=0.6, colors=colors[:len(df)])

# 显示图形
plt.show()
