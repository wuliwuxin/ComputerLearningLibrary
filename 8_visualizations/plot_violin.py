import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import matplotlib as mpl

mpl.rcParams['font.family'] = 'Times New Roman'
# 定义数据
# data = {'Model 1': [72.66, 75.77, 78.80, 82.66, 81.74],
#         'Model 2': [82.08, 83.43, 80.69, 84.58, 82.51],
#         'Model 3': [64.82, 69.24, 67.85, 60.91, 56.30],
#         'Model 4': [50.65, 56.46, 56.87, 55.33, 51.75],
#         'Model 5': [80.43, 83.14, 81.27, 82.90, 82.47],
#       }

# 从数据创建一个DataFrame
# df = pd.DataFrame(data)

# 从 Excel 或 CSV 文件中读取数据
df = pd.read_csv('data/violin_data.csv')     # For CSV file
# df = pd.read_excel('violin_data.xlsx')  # For Excel file

# 为小提琴图创建一个图形
fig, ax2 = plt.subplots(figsize=(10, 6))

# 为每个模型定义自定义颜色
palette = {
    df.columns[0]: "blue",
    df.columns[1]: "green",
    df.columns[2]: "orange",
    df.columns[3]: "purple",
    df.columns[4]: "red"
}

# 创建小提琴图
sns.violinplot(data=df, palette=palette, ax=ax2)

# 设置x轴和y轴的字体大小
plt.xticks(fontsize=16)
plt.yticks(fontsize=16)
# 设置 x 轴和 y 轴的标签
ax2.set_xlabel('Network Type', fontsize=18)
ax2.set_ylabel('AUC (%)', fontsize=18)

# Set the x-axis tick labels to be slanted at 45 degrees
# ax2.set_xticklabels(ax2.get_xticklabels(), rotation=12)

# 显示
plt.show()
