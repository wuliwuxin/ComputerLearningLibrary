# 双轴柱状和折线
import matplotlib.pyplot as plt
import numpy as np

# 假定数据
regions = ['Africa', 'Asia', 'Central', 'Eurasia', 'Europe', 'Middle East', 'North America']
natural_gas = [5000, 20000, 3000, 8000, 25000, 7000, 2000]  # 从天然气产生的单位能量
coal = [90000, 40000, 20000, 30000, 100000, 50000, 120000]  # 从煤产生的单位能量

fig, ax1 = plt.subplots(figsize=(10, 6))

# 创建柱状图
ax1.bar(regions, natural_gas, color='teal', label='From Natural Gas')

# 设置Y轴标签
ax1.set_ylabel('From Natural Gas', color='black')
# 设置Y轴的颜色和刻度颜色
ax1.tick_params(axis='y', labelcolor='black')

# 创建第二个Y轴
ax2 = ax1.twinx()
# 创建折线图
ax2.plot(regions, coal, color='black', label='From Coal', marker='o')
# 设置第二个Y轴的标签
ax2.set_ylabel('From Coal', color='black')
# 设置第二个Y轴的颜色和刻度颜色
ax2.tick_params(axis='y', labelcolor='black')

# 设置图表标题
ax1.set_title('Visualization 2')

# 显示图例
# ax1.legend(loc='upper left')
# ax2.legend(loc='upper right')

# 显示图表
plt.tight_layout()
plt.show()
