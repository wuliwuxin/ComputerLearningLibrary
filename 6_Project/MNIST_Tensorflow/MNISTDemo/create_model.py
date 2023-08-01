# import modules
import numpy as np
import matplotlib.pyplot as plt
# from sklearn.metrics import confusion_matrix
import tensorflow as tf
import time
from datetime import timedelta
import math
from tensorflow.examples.tutorials.mnist import input_data
import os
os.environ["CUDA_VISIBLE_DEVICES"] = "2"


def new_weights(shape):
    return tf.Variable(tf.truncated_normal(shape, stddev=0.05))

def new_biases(length):
    return tf.Variable(tf.constant(0.1, shape=length))

#构建CNN网络结构
"""
卷积步长为1，如要改成步长为2则strides=[1,2,2,1]，
只有中间两个是有效的（对于二维图来说），
使用‘SAME’的padding方法（即输出与输入保持相同尺寸，
边界处少一两个像素则自动补上）；
池化层的设置也类似，池化尺寸为2X2。
"""
#自定义卷积函数（后面卷积是就不用写太多）
def conv2d(x, W):
    return tf.nn.conv2d(x, W, strides=[1, 1, 1, 1], padding='SAME')

#自定义池化函数
def max_pool_2x2(inputx):
    return tf.nn.max_pool(inputx, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1], padding='SAME')

#读取数据
data = input_data.read_data_sets("./data", one_hot=True)  # one_hot means [0 0 1 0 0 0 0 0 0 0] stands for 2

print("Size of:")
print("--Training-set:\t\t{}".format(len(data.train.labels)))
print("--Testing-set:\t\t{}".format(len(data.test.labels)))
print("--Validation-set:\t\t{}".format(len(data.validation.labels)))
data.test.cls = np.argmax(data.test.labels,axis=1)   # show the real test labels:  [7 2 1 ..., 4 5 6], 10000values

#设置占位符，尺寸为样本输入和输出的尺寸
"""
设置输入输出的占位符，占位符是向一个会话中喂数据的入口，
因为TensorFlow的使用中，通过构建计算图来设计网络，
而网络的运行计算则在会话中启动，这个过程我们无法直接介入，
需要通过placeholder来对一个会话进行数据输入。
占位符设置好之后，将x变形成为28x28是矩阵形式（tf.reshape()函数）。

"""
x = tf.placeholder("float",shape=[None,784],name='x')
x_image = tf.reshape(x,[-1,28,28,1])

y_true = tf.placeholder("float",shape=[None,10],name='y_true')
y_true_cls = tf.argmax(y_true,dimension=1)

# Conv 1
layer_conv1 = {"weights":new_weights([5,5,1,32]),
               "biases":new_biases([32])}
h_conv1 = tf.nn.relu(conv2d(x_image,layer_conv1["weights"])+layer_conv1["biases"])
h_pool1 = max_pool_2x2(h_conv1)
# Conv 2
layer_conv2 = {"weights":new_weights([5,5,32,64]),
               "biases":new_biases([64])}
h_conv2 = tf.nn.relu(conv2d(h_pool1,layer_conv2["weights"])+layer_conv2["biases"])
h_pool2 = max_pool_2x2(h_conv2)
# Full-connected layer 1
fc1_layer = {"weights":new_weights([7*7*64,1024]),
            "biases":new_biases([1024])}
h_pool2_flat = tf.reshape(h_pool2,[-1,7*7*64])
h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat,fc1_layer["weights"])+fc1_layer["biases"])
# Droupout Layer
keep_prob = tf.placeholder("float")
h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)
# Full-connected layer 2
fc2_layer = {"weights": new_weights([1024, 10]),
             "biases": new_weights([10])}

# Predicted class
y_pred = tf.nn.softmax(tf.matmul(h_fc1_drop, fc2_layer["weights"])+fc2_layer["biases"])  # The output is like [0 0 1 0 0 0 0 0 0 0]
y_pred_cls = tf.argmax(y_pred,dimension=1)  # Show the real predict number like '2'

# cost function to be optimized
cross_entropy = -tf.reduce_mean(y_true*tf.log(y_pred))
tf.summary.scalar('loss', cross_entropy)

# Optimizer method
# AdamOptimizer is an advanced form of Gradient Descent
# AdamOptimize is based on gradient decent
# It is stochastic gradient decent
#配置Adam优化器，学习效率为1e-4
optimizer = tf.train.AdamOptimizer(learning_rate=1e-4).minimize(cross_entropy)

# Performance Measures
correct_prediction = tf.equal(y_pred_cls, y_true_cls)
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
tf.summary.scalar("training accuary", accuracy)  # 在tensorboa中显示训练准确率

merged = tf.summary.merge_all()

with tf.Session() as sess:
    # with tf.device("/gpu:0"):
    writer = tf.summary.FileWriter("./logs/", sess.graph)
    init = tf.global_variables_initializer()
    sess.run(init)
    train_batch_size = 50

    def optimize(num_iterations):
        total_iterations = 0

        start_time = time.time()

        for i in range(total_iterations, total_iterations+num_iterations):

            x_batch, y_true_batch = data.train.next_batch(train_batch_size)

            feed_dict_train_op = {x: x_batch, y_true: y_true_batch, keep_prob: 0.5}
            feed_dict_train = {x: x_batch, y_true: y_true_batch, keep_prob: 1.0}
            sess.run(optimizer, feed_dict=feed_dict_train_op)
            # writer.add_summary()


            # Print status every 100 iterations.
            #if i % 100 == 0:
                # Calculate the accuracy on the training-set.
            acc = sess.run(accuracy, feed_dict=feed_dict_train)
            rs = sess.run(merged, feed_dict=feed_dict_train_op)
            writer.add_summary(rs, i)

                # Message for printing.
            msg = "Optimization Iteration:{0:>6}, Training Accuracy: {1:>6.1%}"

            # Print it.
            print(msg.format(i+1, acc))
                # writer.add_summary(acc, i+1)  # tensorboard显示准确率
        # Update the total number of iterations performed
        total_iterations += num_iterations

        # Ending time
        end_time = time.time()

        # Difference between start and end_times.
        time_dif = end_time-start_time

        # Print the time-usage
        print("Time usage:"+str(timedelta(seconds=int(round(time_dif)))))

    test_batch_size = 256

    def print_test_accuracy():
        # Number of images in the test-set.
        num_test = len(data.test.images)

        cls_pred = np.zeros(shape=num_test, dtype=np.int)

        i = 0

        while i < num_test:
            # The ending index for the next batch is denoted j.
            j = min(i+test_batch_size, num_test)

            # Get the images from the test-set between index i and j
            images = data.test.images[i:j, :]

            # Get the associated labels
            labels = data.test.labels[i:j, :]

            # Create a feed-dict with these images and labels.
            feed_dict = {x: images, y_true: labels, keep_prob: 1.0}
            # Calculate the predicted class using Tensorflow.
            cls_pred[i:j] = sess.run(y_pred_cls, feed_dict=feed_dict)

            # Set the start-index for the next batch to the
            # end-index of the current batch
            i = j
        cls_true = data.test.cls
        correct = (cls_true == cls_pred)
        correct_sum = correct.sum()
        acc = float(correct_sum) / num_test


        # Print the accuracy
        msg = "Accuracy on Test-Set: {0:.1%} ({1}/{2})"
        print(msg.format(acc, correct_sum, num_test))

    # Performance after 10000 optimization iterations
    optimize(num_iterations=10000)
    print_test_accuracy()


