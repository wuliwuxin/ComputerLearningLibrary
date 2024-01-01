"""
几种数据的折线图
"""
import matplotlib.pyplot as plt
import numpy as np

# 假设 x 轴有 8 个epcohs
epochs = np.arange(1, 9)

# 数据
micro_f1_values = {
    'w/o ATM, w/o APP': np.array([0.52, 0.54, 0.56, 0.58, 0.60, 0.61, 0.62, 0.63]),
    'w/ ATM, w/o APP': np.array([0.53, 0.55, 0.57, 0.59, 0.61, 0.62, 0.63, 0.64]),
    'w/o ATM, w/ APP': np.array([0.54, 0.56, 0.58, 0.59, 0.61, 0.64, 0.63, 0.65]),
    'w/ ATM, w/ APP': np.array([0.55, 0.57, 0.58, 0.56, 0.62, 0.63, 0.64, 0.66]),
}

# 设置不同的标记和颜色
markers = ['o', '^', '*', 's'] 
colors = ['black', 'blue', 'orange', 'red'] 

# 使用不同的标记和颜色绘制数据
plt.figure(figsize=(10, 6))
for (label, values), marker, color in zip(micro_f1_values.items(), markers, colors):
    plt.plot(epochs, values, marker=marker, color=color, linestyle='-', label=label)

# 添加标题和标签
plt.title('Micro-F1 Score over Epochs')
plt.xlabel('Train (num of epochs)')
plt.ylabel('Micro-F1')

# 添加网格和图例
plt.grid(True)
plt.legend()

# 显示
plt.show()

