# -*- coding: utf-8 -*-
#import modules
import numpy as np
# import matplotlib.pyplot as plt
#from sklearn.metrics import confusion_matrix
#from tensorflow.examples.tutorials.mnist import input_data
import tensorflow as tf
import time
from datetime import timedelta
import math
from PIL import Image

'''
def picprocess(pic):
    im = Image.open(pic)
    if im.size!=(28,28):
        im = im.resize((28,28),Image.ANTIALIAS)
    if im.mode=='RGB':
        im = im.convert('L')
    if im.mode!='RGBA':
        im = im.convert("RGBA")
    im_arr = list(im.getdata())
    im_nparr = [[]]
    for x in im_arr:
        x = (1 - x[0]/255, 1 - x[1]/255, 1 - x[2]/255)
        im_nparr = np.append(im_nparr, [[(x[0] + x[1] + x[2])/3]], axis=1)   # im_nparr为矩阵
    return im_nparr,im  # im为图像文件
'''
def picprocess(pic):
    im = Image.open(pic)
    if im.size!=(28,28):
        im = im.resize((28,28),Image.ANTIALIAS)
    if im.mode!='L':
        im = im.convert('L')
    im_arr = list(im.getdata())
    im_nparr = []
    for x in im_arr:
        x=1-x/255
        im_nparr.append(x)
    im_nparr = np.array([im_nparr])
    return im_nparr,im

# 整形
def reshapepic(pic):
    im = Image.open(pic)
    if im.size!=(28,28):
        im = im.resize((28,28)) #,Image.ANTIALIAS)
    return im
# graying
def graypic(pic):
    if pic.mode != 'L':
        pic = pic.convert('L')
    return pic
# normalization
def normalizepic(pic):
    im_arr = list(pic.getdata())
    im_nparr = []
    for x in im_arr:
        x = 1-x/255
        im_nparr.append(x)
    im_nparr = np.array([im_nparr])
    return im_nparr, pic


def array_para_numero_resultado(res):
    for i in range(len(res)):
        if res[i] == 1:
            return int(i)

def conv2d(x,W):
    return tf.nn.conv2d(x,W,strides=[1,1,1,1],padding='SAME')

def max_pool_2x2(inputx):
    return tf.nn.max_pool(inputx,ksize=[1,2,2,1],strides=[1,2,2,1],padding='SAME')

def rec(im_nparr):

    x = tf.placeholder(tf.float32, shape=[None, 784], name='x')
    x_image = tf.reshape(x, [-1,28,28,1])
    
    y_true = tf.placeholder(tf.float32,shape=[None,10],name='y_true')
    y_true_cls = tf.argmax(y_true,dimension=1)
    # Conv 1
    layer_conv1 = {"weights":tf.Variable(np.load("./paras/savew_hl1.npy")),
                   "biases":tf.Variable(np.load("./paras/saveb_hl1.npy"))}
    h_conv1 = tf.nn.relu(conv2d(x_image,layer_conv1["weights"])+layer_conv1["biases"])
    h_pool1 = max_pool_2x2(h_conv1)
    # Conv 2  # 关键的难点，多个输入，和多个卷积核，怎么进行卷积运算
    # 初步想法：前n个输入分别和卷积核进行数值卷积，然后相加
    layer_conv2 = {"weights": tf.Variable(np.load("./paras/savew_hl2.npy")),
                   "biases": tf.Variable(np.load("./paras/saveb_hl2.npy"))}
    #print("h_pool1:",h_pool1.shape)

    h_conv2 = tf.nn.relu(conv2d(h_pool1, layer_conv2["weights"])+layer_conv2["biases"])
    #print("h_conv2: ", h_conv2.shape)
    h_pool2 = max_pool_2x2(h_conv2)
    # Full-connected layer 1
    fc1_layer = {"weights": tf.Variable(np.load("./paras/savew_hl3.npy")),
                "biases": tf.Variable(np.load("./paras/saveb_hl3.npy"))}
    h_pool2_flat = tf.reshape(h_pool2, [-1, 7*7*64])
    h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, fc1_layer["weights"])+fc1_layer["biases"])
    # Droupout Layer
    keep_prob = tf.placeholder(tf.float32)
    h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)
    # Full-connected layer 2
    fc2_layer = {"weights": tf.Variable(np.load("./paras/savew_op.npy")),
                 "biases": tf.Variable(np.load("./paras/saveb_op.npy"))}
    
    # Predicted class
    y_pred = tf.nn.softmax(tf.matmul(h_fc1_drop,fc2_layer["weights"])+fc2_layer["biases"])  # The output is like [0 0 1 0 0 0 0 0 0 0]
    y_pred_cls = tf.argmax(y_pred,dimension=1)  # Show the real predict number like '2'
    
    # Performance Measures
    correct_prediction = tf.equal(y_pred_cls,y_true_cls)
    accuracy = tf.reduce_mean(tf.cast(correct_prediction,tf.float32))
    
    with tf.Session() as sess:
        init = tf.global_variables_initializer()
        sess.run(init)
        # feed_dict={x:im_nparr}
        # plot_layer(sess, h_pool1, feed_dict)
        resultado = []
        for i in range(10):
            tmp = [[0,0,0,0,0,0,0,0,0,0]]
            tmp[0][i] = 1
            resultado = np.append(resultado, accuracy.eval({x:im_nparr, y_true:tmp, keep_prob:1.0}))
            # resultado -> [resultado,accuracy]
    
        return (array_para_numero_resultado(resultado))

# knn进行识别
def kNN_rec(im_arr):
    mnist = input_data.read_data_sets("./data/", one_hot=True)
    # 数据全部取出, 普通pc计算需要半小时左右，如果嫌太慢，可以少取一些数据。
    train_x, train_y = mnist.train.next_batch(60000)
    #test_x, test_y = mnist.test.next_batch(10000)

    train_x_p = tf.placeholder(tf.float32, [None, 784])
    test_x_p = tf.placeholder(tf.float32, [784])

    # L1距离计算：dist = sum(|X1-X2|)
    #dist_l1 = tf.reduce_sum(tf.abs(train_x_p + tf.negative(test_x_p)), reduction_indices=1)

    # L2距离计算：dist = sqrt(sum(|X1-X2|^2))
    dist_l2 = tf.sqrt(tf.reduce_sum(tf.square(tf.abs(train_x_p + tf.negative(test_x_p))), reduction_indices=1))

    # 获得最小距离的索引
    prediction = tf.arg_min(dist_l2, 0)
    init_op = tf.initialize_all_variables()

    with tf.Session() as sess:
        sess.run(init_op)
        nn_index = sess.run(prediction, feed_dict={train_x_p: train_x, test_x_p: im_arr[0]})
        knn_predict = np.argmax(train_y[nn_index])
    return knn_predict

def softmax_rec(im_arr):
    x = tf.placeholder(tf.float32, [None, 784])
    Wb = {"weights": tf.Variable(np.load("./paras/softmax_W.npy")),
                                 "biases": tf.Variable(np.load("./paras/softmax_b.npy"))}
    y_pred = tf.nn.softmax(tf.matmul(x, Wb["weights"]) + Wb["biases"])
    y_pred_cls = tf.argmax(y_pred,dimension=1)
    # correct_prediction = tf.equal(y_pred_cls,y_true_cls)
    # accuracy = tf.reduce_mean(tf.cast(correct_prediction,tf.float32))

    with tf.Session() as sess:
        init = tf.global_variables_initializer()
        sess.run(init)
        softmax_predict = sess.run(y_pred_cls, feed_dict={x: im_arr})
        # resultado = []
        # for i in range(10):
        #     tmp = [[0,0,0,0,0,0,0,0,0,0]]
        #     tmp[0][i] = 1
        #     resultado = np.append(resultado, accuracy.eval({x:im_nparr, y_true:tmp, keep_prob:1.0}))
        #     # resultado -> [resultado,accuracy]

    return softmax_predict[0]

def plot_layer(layer, im_arr):
    x = tf.placeholder(tf.float32, shape=[None, 784], name='x')
    x_image = tf.reshape(x, [-1,28,28,1])

    y_true = tf.placeholder(tf.float32,shape=[None,10],name='y_true')
    y_true_cls = tf.argmax(y_true,dimension=1)
    # Conv 1
    layer_conv1 = {"weights":tf.Variable(np.load("./paras/savew_hl1.npy")),
                   "biases":tf.Variable(np.load("./paras/saveb_hl1.npy"))}
    h_conv1 = tf.nn.relu(conv2d(x_image,layer_conv1["weights"])+layer_conv1["biases"])
    h_pool1 = max_pool_2x2(h_conv1)
    # Conv 2  # 关键的难点，多个输入，和多个卷积核，怎么进行卷积运算
    # 初步想法：前n个输入分别和卷积核进行数值卷积，然后相加
    layer_conv2 = {"weights": tf.Variable(np.load("./paras/savew_hl2.npy")),
                   "biases": tf.Variable(np.load("./paras/saveb_hl2.npy"))}
    #print("h_pool1:",h_pool1.shape)

    h_conv2 = tf.nn.relu(conv2d(h_pool1, layer_conv2["weights"])+layer_conv2["biases"])
    #print("h_conv2: ", h_conv2.shape)
    h_pool2 = max_pool_2x2(h_conv2)
    if layer == 'conv1':
        layer = h_conv1
    elif layer == 'pool1':
        layer = h_pool1
    elif layer=='conv2':
        layer = h_conv2
    elif layer == 'pool2':
        layer = h_pool2
    else:
        print("wrong layer input!")
    with tf.Session() as sess:
        init = tf.global_variables_initializer()
        sess.run(init)
        values = sess.run(layer, feed_dict={x: im_arr})
        num_filters = values.shape[3]
        num_grids = int(num_filters/8)
        fig, axes = plt.subplots(num_grids, 8)

        for i,ax in enumerate(axes.flat):
            if i<num_filters:
                img = values[0,:,:,i]
                # ax.imshow(img, interpolation='nearest',cmap='binary')
                ax.imshow(img)#, interpolation='nearest',cmap='semantic')
            ax.set_xticks([])
            ax.set_yticks([])
        im = fig2img(fig)
        return im
        # im.show()
        # print("ok")
        # plt.show()

def fig2data(fig):
    """
    @brief Convert a Matplotlib figure to a 4D numpy array with RGBA channels and return it
    @param fig a matplotlib figure
    @return a numpy 3D array of RGBA values
    """
    # draw the renderer
    fig.canvas.draw()

    # Get the RGBA buffer from the figure
    w, h = fig.canvas.get_width_height()
    buf = np.fromstring(fig.canvas.tostring_argb(), dtype=np.uint8)
    buf.shape = (w, h, 4)

    # canvas.tostring_argb give pixmap in ARGB mode. Roll the ALPHA channel to have it in RGBA mode
    buf = np.roll(buf, 3, axis=2)
    return buf

def fig2img(fig):
    """
    @brief Convert a Matplotlib figure to a PIL Image in RGBA format and return it
    @param fig a matplotlib figure
    @return a Python Imaging Library ( PIL ) image
    """
    # put the figure pixmap into a numpy array
    buf = fig2data(fig)
    w, h, d = buf.shape
    return Image.frombytes("RGBA", (w, h), buf.tostring())

if __name__=="__main__":
    #im_nparr,img = picprocess("./testimgs/13.bmp")

    im = reshapepic("./testimgs/17.bmp")
    # im.show()
    img = graypic(im)
    im_nparr, pic = normalizepic(img)
    plot_layer('pool1', im_nparr)

    print(type(im_nparr))
    #print(img)
    # img.show()
    start_time = time.time()
    # print(kNN_rec(im_nparr))  # ok
    # print(softmax_rec(im_nparr))
    print(rec(im_nparr))
    end_time = time.time()
    time_dif = end_time-start_time
    print("Time usage:"+str(timedelta(seconds=int(round(time_dif)))))
    '''
    im_arr,img = picprocess("./testimgs/13.bmp")
    print(im_arr)
    print(len(im_arr))
    img.show()
    '''