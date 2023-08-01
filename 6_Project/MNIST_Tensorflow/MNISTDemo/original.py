# -*-coding:utf=8-*-
# 本程序为Tensorflow基于卷积神经网络的手写数字识别的基本程序

from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
import os
import numpy as np
import matplotlib.pyplot as plt
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

mnist = input_data.read_data_sets("./data/", one_hot=True)

## 显示CNN的输入层
#print(mnist.train.next_batch(1)[0].shape)
#tt = mnist.train.next_batch(1)[0]

#print(tt)
# im = mnist.train.next_batch(1)[0]
# im = np.array(im)
# im = im.reshape(28, 28)
# fig = plt.figure()
# plt.imshow(im,cmap='binary')
# plt.show()


def weight_variable(shape):
    initial = tf.truncated_normal(shape,stddev=0.1)
    return tf.Variable(initial)

def bias_variable(shape):
    initial = tf.constant(0.1,shape=shape)
    return tf.Variable(initial)

def conv2d(x,W):
    return tf.nn.conv2d(x,W,strides=[1,1,1,1],padding='SAME')
def max_pooling_2x2(x):
    return tf.nn.max_pool(x,ksize=[1,2,2,1],strides=[1,2,2,1],padding='SAME')

x = tf.placeholder(tf.float32,[None,784])
y_ = tf.placeholder(tf.float32,[None,10])
x_image = tf.reshape(x,[-1,28,28,1])

W_conv1 = weight_variable([5,5,1,32])
print("W_conv1 shape:", W_conv1.shape)
b_conv1 = bias_variable([32])
# 第一个卷积层
h_conv1 = tf.nn.relu(conv2d(x_image,W_conv1)+b_conv1)
print("h_conv1 shape: ", h_conv1.shape)

# 第一个池化层
h_pool1 = max_pooling_2x2(h_conv1)
print("h_pool1 shape: ", h_pool1.shape)

# 第二个卷积层
W_conv2 = weight_variable([5,5,32,64])
b_conv2 = bias_variable([64])
h_conv2 = tf.nn.relu(conv2d(h_pool1,W_conv2)+b_conv2)
print("h_conv2 shape: ", h_conv2.shape)

# 第二个池化层
h_pool2 = max_pooling_2x2(h_conv2)
print("h_pool2 shape: ", h_pool2.shape)

# 第一个全连接层，包括dropout
W_fc1 = weight_variable([7*7*64,1024])
b_fc1 = bias_variable([1024])
h_pool2_flat = tf.reshape(h_pool2,[-1,7*7*64])  # 需要整形
h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat,W_fc1)+b_fc1)
print("h_fc1 shape: ", h_fc1.shape)


keep_prob = tf.placeholder(tf.float32)
h_fc1_drop = tf.nn.dropout(h_fc1,keep_prob)

# 第二个全连接层，包括dropout
W_fc2 = weight_variable([1024,10])
b_fc2 = bias_variable([10])
y_conv = tf.nn.softmax(tf.matmul(h_fc1_drop,W_fc2)+b_fc2)


cross_entropy = tf.reduce_mean(-tf.reduce_sum(y_*tf.log(y_conv),reduction_indices=[1]))
train_step = tf.train.AdamOptimizer(1e-4).minimize(cross_entropy)

correct_prediction = tf.equal(tf.argmax(y_conv,1),tf.argmax(y_,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction,tf.float32))

sess = tf.InteractiveSession()
tf.global_variables_initializer().run()

loss = []

for i in range(10000):
    batch = mnist.train.next_batch(50)
    if i%100 == 0:
        train_accuracy = accuracy.eval(feed_dict={x:batch[0],y_:batch[1],keep_prob:1.0})
        print("step %d, training accuracy %g"%(i,train_accuracy))
        #print(sess.run(tf.argmax(y_conv,1),feed_dict={x:batch[0],keep_prob:1.0}))
        loss1 = sess.run(cross_entropy,feed_dict={x:batch[0],y_:batch[1],keep_prob:0.5})
        loss.append(loss1)
        #print(sess.run(cross_entropy,feed_dict={x:batch[0],y_:batch[1],keep_prob:0.5}))

    train_step.run(feed_dict={x:batch[0],y_:batch[1],keep_prob:0.5})

# print(sess.run(h_conv1,feed_dict={x:mnist.train.next_batch(1)[0]}))
# print(loss)
print("test accuracy %g"%accuracy.eval(feed_dict={x:mnist.test.images,y_:mnist.test.labels,keep_prob:1.0}))
