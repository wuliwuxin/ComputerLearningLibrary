"""
建立两个模型，线性模型和卷积模型
"""
import tensorflow as tf


# Softmax Regression Model
#定义线性模型:Y=W*x+b
def regression(x):
    W = tf.Variable(tf.zeros([784, 10]), name="W") # 定义一个784*10的全0的图像，名字为W
    b = tf.Variable(tf.zeros([10]), name="b")#维度为10
    y = tf.nn.softmax(tf.matmul(x, W) + b)#y——tesnorflow的激励函数为softmax()，简单的向量乘法
    return y, [W, b]

# Multilayer Convolutional Network
def convolutional(x, keep_prob):
    def conv2d(x, W):#2*2的卷积
        return tf.nn.conv2d(x, W, strides=[1, 1, 1, 1], padding='SAME')

    def max_pool_2x2(x):#2×2的池化层
        return tf.nn.max_pool(x, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

    def weight_variable(shape):#定义权重变量
        initial = tf.truncated_normal(shape, stddev=0.1)
        return tf.Variable(initial)

    def bias_variable(shape):
        initial = tf.constant(0.1, shape=shape)#定义一个常量
        return tf.Variable(initial)

    # 定义卷积层
    # First Convolutional Layer
    x_image = tf.reshape(x, [-1, 28, 28, 1])
    W_conv1 = weight_variable([5, 5, 1, 32])
    b_conv1 = bias_variable([32])
    h_conv1 = tf.nn.relu(conv2d(x_image, W_conv1) + b_conv1)
    h_pool1 = max_pool_2x2(h_conv1)
    # Second Convolutional Layer
    W_conv2 = weight_variable([5, 5, 32, 64])
    b_conv2 = bias_variable([64])
    h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2) + b_conv2)
    h_pool2 = max_pool_2x2(h_conv2)
    # Densely Connected Layer
    # fc：full connection全连接
    W_fc1 = weight_variable([7 * 7 * 64, 1024])#全连接的权重
    b_fc1 = bias_variable([1024])#全连接的bias边
    h_pool2_flat = tf.reshape(h_pool2, [-1, 7 * 7 * 64])
    h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, W_fc1) + b_fc1)
    # Dropout
    h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)
    # Readout Layer
    W_fc2 = weight_variable([1024, 10])
    b_fc2 = bias_variable([10])
    y = tf.nn.softmax(tf.matmul(h_fc1_drop, W_fc2) + b_fc2)
    return y, [W_conv1, b_conv1, W_conv2, b_conv2, W_fc1, b_fc1, W_fc2, b_fc2]
