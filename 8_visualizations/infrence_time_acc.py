import matplotlib.pyplot as plt

# 假定的模型性能和推理时间数据
models = {
    'ResNet-50': (78.20, 3.9),
    'ResNet-101': (78.6, 4.0),
    'ResNeSt-50': (79.0, 4.0),
    'NeXt-50': (79.2, 3.7),
    'SNet-101': (79.4, 4.1),
    'LambdaResNet-50': (78.6, 3.8),
    'Ours-50': (79.5, 3.7),
    'Ours-101': (80.0, 4.0)
}

# 创建一个散点图
plt.figure(figsize=(12, 8))

# 定义颜色和形状
colors = ['green', 'blue', 'cyan', 'magenta', 'black', 'olive', 'red', 'red']

# 生成颜色和形状
color_map = {model: colors[i % len(colors)] for i, model in enumerate(models.keys())}
shape_map = {model: 'o' if 'Ours' in model else '^' for model in models.keys()}

# 绘制模型的散点
for model, (acc, time) in models.items():
    color = color_map[model]
    plt.scatter(time, acc, label=model, s=100, color=color, marker=shape_map[model])
    # 设置文本标签的颜色和粗细
    # plt.text(time, acc, model, fontsize=10, fontweight='bold', color=color, ha='center', va='bottom')
    #   设置文本标签的颜色、粗细，并将其放置在散点下方
    plt.text(time, acc - 0.08, model, fontsize=12, fontweight='bold', color=color, ha='center')

# Ours-Ours-101
plt.plot([models['Ours-50'][1], models['Ours-101'][1]],
         [models['Ours-50'][0], models['Ours-101'][0]],
         color='red', linestyle='-', linewidth=2, marker='o', markersize=10)

# 添加标题和轴标签
plt.title('Top-1 Accuracy vs. Inference Time', fontsize=20)
# 设置x轴和y轴的字体大小
plt.xticks(fontsize=16)
plt.yticks(fontsize=16)
plt.xlabel('Inference Time (ms)', fontsize=18)
plt.ylabel('Top-1 Accuracy (%)', fontsize=18)

# 显示图例
# plt.legend()

# 显示图表
plt.show()
