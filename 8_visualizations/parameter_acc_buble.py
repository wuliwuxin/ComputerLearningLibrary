import matplotlib.pyplot as plt
import numpy as np

# 假定的模型性能和参数数量数据
models_data = {
    'ResNet-50': (75.6, 25.2),
    'ResNet-101': (76, 45),
    'ResNet-152': (77, 60),
    'SENet-50': (76.5, 28),
    'SENet-101': (77.5, 49),
    'CBAM-ResNet50': (77, 28),
    'CBAM-ResNet101': (78, 49),
    'ENet50(Ours)': (78.5, 29),
}

# 确定气泡大小的比例因子
size_factor = 1000
# 获取最小参数数量以用作参考点
min_params = min(params for _, params in models_data.values())
# 计算每个模型的气泡大小，保持与参数数量的比例关系
bubble_sizes = [(params / min_params) * size_factor for _, params in models_data.values()]

# 创建一个散点图
plt.figure(figsize=(10, 8))

# 创建颜色映射
# color_map = plt.cm.viridis(np.linspace(0, 1, len(models_data)))
color_map = ['b', 'g', 'c', 'm', 'k', 'orange', 'gray', 'r']

# 绘制每个模型的散点和文本
for (model, (accuracy, params)), size, color in zip(models_data.items(), bubble_sizes, color_map):
    plt.scatter(params, accuracy, s=size, color=color, alpha=0.5, label=model)
    plt.text(params, accuracy - 0.25, model, fontsize=9, fontweight='bold', color=color, ha='center', va='bottom')

# 添加标题和轴标签
plt.title('Top-1 Accuracy vs. Parameters of Networks', fontsize=20)

plt.xticks(fontsize=16)
plt.yticks(fontsize=16)

plt.xlabel('Parameters of Networks (Millions)', fontsize=18)
plt.ylabel('Top-1 Accuracy (%)', fontsize=18)

# 设置x、y轴取值范围
plt.xlim(20, 65)
plt.ylim(75, 79)


# 显示网格
# plt.grid(True)

# 显示图表
plt.tight_layout()
plt.show()
