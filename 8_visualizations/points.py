import matplotlib.pyplot as plt
import numpy as np

# 模拟数据
np.random.seed(0)
num_points = 200  # 数据点数量
cost = np.random.uniform(100, 800, num_points)  # 成本数据，随机生成
revenue = cost + np.random.uniform(50, 300, num_points)  # 收入数据，随机生成
profit = (revenue - cost) / cost  # 利润百分比

# 根据利润百分比进行颜色映射
colors = np.where(profit < 0.2, 'red',
                  np.where(profit < 0.4, 'orange',
                           np.where(profit < 0.6, 'yellow',
                                    np.where(profit < 0.8, 'lightgreen', 'green'))))

# 创建散点图
plt.figure(figsize=(14, 8))
scatter = plt.scatter(cost, revenue, c=colors, alpha=0.6)

# 添加趋势线
z = np.polyfit(cost, revenue, 1)  # 计算最佳拟合线
p = np.poly1d(z)  # 创建多项式对象
plt.plot(cost, p(cost), "k--", linewidth=1)  # 绘制趋势线

# 添加标题和标签
plt.title('Revenue vs. Cost by Customer')  # 标题
plt.xlabel('Cost')  # x轴标签
plt.ylabel('Revenue')  # y轴标签

# 为利润百分比添加图例
for percent, color in zip(['0% - 20%', '20% - 40%', '40% - 60%', '60% - 80%', '80% - 100%'], 
                          ['red', 'orange', 'yellow', 'lightgreen', 'green']):
    plt.scatter([], [], c=color, alpha=0.6, label=percent)  # 使用空散点创建图例项

plt.legend(title="Percentage of Profit", loc='upper left', bbox_to_anchor=(1, 1))  # 设置图例位置

# 展示图表
plt.tight_layout()  # 调整布局
plt.show()  # 显示图表
