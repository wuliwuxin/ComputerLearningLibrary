import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd

# 数据
data = {
        'Model 1': [78.66, 75.77, 78.80, 82.66, 81.74],
        'Model 2': [82.08, 83.43, 86.90, 84.58, 82.51],
        'Model 3': [74.82, 79.24, 77.85, 79.10, 76.13],
}

# 从数据创建一个DataFrame
df = pd.DataFrame(data)

# 从 Excel 或 CSV 文件中读取数据
# df = pd.read_csv('box_data.csv')     # For CSV file
# df = pd.read_excel('box_data.xlsx')  # For Excel file

# 创建图形和轴
fig, ax = plt.subplots(figsize=(10, 6))
# 自定义颜色
colors = ['lightblue', 'lightgreen', 'pink']
# 创建箱线图
sns.boxplot(data=df, ax=ax, palette=colors)
# 设置x轴和y轴的字体大小
plt.xticks(fontsize=16)
plt.yticks(fontsize=16)
# 设置标题和轴标签
ax.set_title('AUC Boxplot for Different Network Types')
ax.set_xlabel('Network Type', fontsize=18)
ax.set_ylabel('AUC', fontsize=18)
# 横坐标旋转
# ax.set_xticklabels(ax.get_xticklabels(), rotation=12)
# 显示
plt.show()
