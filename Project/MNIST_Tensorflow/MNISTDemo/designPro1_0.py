#import modules
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import confusion_matrix
import tensorflow as tf
import time
from datetime import timedelta
import math
from tensorflow.examples.tutorials.mnist import input_data

# Configuration of Neural Network
# Convolutional layer 1
filter_size1 = 5   # Convolution filters are 5x5 pixels.
num_filters1 = 32  # There are 16 of these filters.

# Convolutional layer 2.
filter_size2 = 5   # Convolution filters are 5x5 pixels.
num_filters2 = 64  # There are 36 of these filters.

# Fully-connected layer.
fc_size = 1024  # Number of neurous in fully-connected layer.

#import data
data = input_data.read_data_sets("./data", one_hot=True)  # one_hot means [0 0 1 0 0 0 0 0 0 0] stands for 2

#print(list(data.test.images[0]))

print("Size of:")
print("--Training-set:\t\t{}".format(len(data.train.labels)))
print("--Testing-set:\t\t{}".format(len(data.test.labels)))
print("--Validation-set:\t\t{}".format(len(data.validation.labels)))

data.test.cls = np.argmax(data.test.labels,axis=1)   # show the real test labels:  [7 2 1 ..., 4 5 6], 10000values
# print data.test.cls

# Input data Dimensions
# MNIST images are 28pixels in each dimensiion
img_size = 28

# Images are stored in one-dimensional arrays of this length.
img_size_flat = img_size * img_size

# Tuple with height and width of images used to reshape arrays.
img_shape = (img_size,img_size)

# Number of color channels for the images : 1 channel for gray-scale
num_channels = 1
#Number of classes,one class for each of 10 digits.
num_classes = 10
#------------------------------------------------------------

# Helper-function for plotting images

def plot_images(images, cls_true, cls_pred=None):
    assert len(images)==len(cls_true)==9
    #assert len(images) == len(cls_true) == 9
    # Create figure with 3*3 sub-plots.
    fig,axes = plt.subplots(3,3)
    fig.subplots_adjust(hspace=0.3,wspace=0.3)
    
    for i,ax in enumerate(axes.flat):
        #PLot image
        ax.imshow(images[i].reshape(img_shape),cmap='binary')
        
        # show true and predicted classes.
        if cls_pred is None:
            xlabel = "True:{0}".format(cls_true[i])
        else:
            xlabel = "True: {0}, Pred: {1}".format(cls_true[i],cls_pred[i])
        
        # Show the classes as the label on the x-axis.
        ax.set_xlabel(xlabel)
        
        # Remove ticks from the plot
        ax.set_xticks([])
        ax.set_yticks([])
        
    # Ensure the plot is shown correctly with multiple plots
    # in a single Notebook cell
    plt.show()

# Test the Plot function
'''
# Plot a few images to see if data is correct
images = data.test.images[0:9]

# Get the true classes for those images
cls_true = data.test.cls[0:9]

# Plot the images and labels using our helper-function above.
plot_images(images = images,cls_true=cls_true)
'''
# Helper-function for creating new variables
def new_weights(shape):
    return tf.Variable(tf.truncated_normal(shape,stddev=0.05))
def new_biases(length):
    return tf.Variable(tf.constant(0.05,shape=[length]))

# Helper-function for creating a new Convolutional layer
def new_conv_layer(input,  num_input_channels,  filter_size, num_filters,use_pooling=True):  
    '''
    input - The previus layer
    num_input_channels - Num. channels in prev. layer
    fiter_size - Width and height of each filter
    num_filters - Number of filters
    use_pooling=True - Use 2*2 max-pooling
'''
    #Shape of the filter-weights for the convulotion
    #This format is determined by the Tensorflow API
    shape = [filter_size,filter_size,num_input_channels,num_filters]
    
    #Create new weights aka. filters with the given shape.
    weights = new_weights(shape=shape)
    
    # Create new biases, one for wach filter.
    biases = new_biases(length=num_filters)
    
    # Create the Tensorflow operation for convolution.
    # Note the strides are set to 1 in all dimensions.
    # The first and last stride must always be 1,
    # because the frist is for the image-number and
    # the last is for the input-channel
    # But e.g. strides=[1,2,2,1] would mean that the filter
    # is moved 2 pixels across the x- and y-axis of the image.
    # The padding is set to 'SAME' which means the input image
    # is padded with zeroes so the size of the output is the same.
    layer = tf.nn.conv2d(input=input,filter=weights,strides=[1,1,1,1],padding='SAME')

    # Add the bias to the results of  the convolution.
    # A bias-value is added to each filter-channel.
    layer +=biases
    
    #Use pooling to down-sample the image resolution?
    if use_pooling:
        # This is 2x2 max-pooling, which means that we
        # consider 2x2 windows and select the largest calue
        # in each window. Then we move 2 pixels to the next window.
        pool_layer = tf.nn.max_pool(value=layer,ksize=[1,2,2,1],strides=[1,2,2,1],padding='SAME')
        # Rectified Linear Unit (ReLU).
        # It calculate max(x,0) for each input pixel x.
        # This adds some non-linearity to the formula and allows us
        # to learn more complicated functions.
        layer = tf.nn.relu(pool_layer)
        
        # Note that ReLU is normally executed before the pooling,
        # but since relu(max_pool(x))== max_pool(rulu(x)) we can 
        # save 75% of the relu-operations by max-pooling frist.
        
        # We retrun both the resulting layer and the dilter-weights
        # because we will plot the weights later.
        return pool_layer, layer, weights
        
# Helper-function for flattening a layer
# A convolutional layer produces an output tensor with 4 dimensions. We will add fully-connected 
# layer after the convolution layers,so we need to reduce the 4-dim tensor to 2-dim which can be 
# used as input tothe fully_connected layer
def flatten_layer(layer):
    '''
    4-dim changes to 2-dim
    '''
    # Get the shape of the input layer.
    layer_shape = layer.get_shape()
    
    # The shape of the input layer is assumed to be :
    # layer_shape = [num_images,img_height,img_width,num,channels]
    
    # The number of features is : img_height*img_width* num_channels
    # We can use a function from Tensorflow to calculate this.
    num_features = layer_shape[1:4].num_elements()
    
    # Reshape the layer to [num_images,num_features].
    # Note that we just set the size of the second dimension
    # to num_features and size of the frist dimension to -1
    # which means the size in that dimension is calculaed 
    # so the total size of the tensor is unchanged from the reshaping.
    layer_flat = tf.reshape(layer,[-1,num_features])
    
    # The shape of the flattened layer is now :
    # [num_images, img_height*img_width*num_channels]
    # return both the flattened layer and the number of features.
    return layer_flat,num_features

# Helper-function for creating a new Fully-Connected layer
# It is assumed that the input is 2-dim tensor of shape[num_images,num_inputs].
# The output is a 2-dim tensor of shape [num_images,num_outputs].
def new_fc_layer(input,num_inputs,num_outputs,use_relu=True):
    '''
    Creating fully-connected layer
    input - The previous layer
    num_inputs - Num. inputs from prev. layer
    num_outputs - Num. outputs.
    use_relu - use Rectified Linear Unit(ReLU)?
    '''
    # Create new weights and biases.
    weights = new_weights(shape=[num_inputs,num_outputs])
    biases = new_biases(length=num_outputs)
    
    # Calculate the layer as the matrix ultiplication of
    # the input and weights, and then add the biase-values.
    layer = tf.matmul(input,weights)+biases
    
    # Use ReLU?
    if use_relu:
        layer = tf.nn.relu(layer)
    return layer

# Placeholder variables
# The data-type set to float32 and the shape is set to [None,img_size_flat],
# where None is means that the tensor may hold an arbitrary number of 
# images with each image being a vector of length img_size_flat.
x = tf.placeholder(tf.float32,shape=[None,img_size_flat],name='x')
# The convolution layers expect x to be encoded as a 4-dim tensor so we have to 
# reshape it so its shape is instead [num_images,img_height,img_width,num_channels]
x_image = tf.reshape(x,[-1,img_size,img_size,num_channels])

y_true = tf.placeholder(tf.float32,shape=[None,10],name='y_true')
# y_true is a vector like [0 0 1 0 0 0 0 0 0 0]
# Shoe the index of the 1
y_true_cls = tf.argmax(y_true,dimension=1)

# Comvolution layer 1
pool_layer1, layer_conv1, weights_conv1 = new_conv_layer(input=x_image,num_input_channels=num_channels,
                                           filter_size=filter_size1,num_filters=num_filters1,use_pooling=True)
# Test the shape of layer_conv1
# print layer_conv1

# Convolutional Layer 2
pool_layer2, layer_conv2, weights_conv2 = new_conv_layer(input=layer_conv1,num_input_channels=num_filters1,
                                           filter_size=filter_size2,num_filters=num_filters2,use_pooling=True)
# Check the shape of conv_layer2
#print layer_conv2

# Flatten Layer 
layer_flat, num_features = flatten_layer(layer_conv2)
# Check the flatten layer output
#print layer_flat
#print num_features

# Fully-conneted Layer1
layer_fc1 = new_fc_layer(input=layer_flat,num_inputs=num_features,
                         num_outputs=fc_size,use_relu=True)
# Check the output of fully-connected layer 1
#print layer_fc1

#Add the dropout layer to avoid over-fitting
keep_prob = tf.placeholder(tf.float32)
layer_fc1_drop = tf.nn.dropout(layer_fc1,keep_prob)

# Fully-connected Layer 2
# Add another fully-connected layer that outputs vectors with the length of 10

layer_fc2 = new_fc_layer(input=layer_fc1_drop,num_inputs=fc_size,
                         num_outputs=num_classes,use_relu=False)
# Check the shape of output
# print layer_fc2

# Predicted class
y_pred = tf.nn.softmax(layer_fc2)  # The output is like [0 0 1 0 0 0 0 0 0 0]
y_pred_cls = tf.argmax(y_pred,dimension=1)  # Show the real predict number like '2'

# cost function to be optimized
cross_entropy = tf.nn.softmax_cross_entropy_with_logits(logits=layer_fc2,labels=y_true)
cost = tf.reduce_mean(cross_entropy)
# Optimizer method
# AdamOptimizer is an advanced form of Gradient Descent
optimizer = tf.train.AdamOptimizer(learning_rate=1e-4).minimize(cost)

# Performance Measures
correct_prediction = tf.equal(y_pred_cls,y_true_cls)
accuracy = tf.reduce_mean(tf.cast(correct_prediction,tf.float32))


# Create Tensorflow session
sess = tf.Session()
# Initialize all the variables
init = tf.global_variables_initializer()
sess.run(init)

train_batch_size = 64

# The process is printed every 100 iterations
total_iterations =0 
def optimize(num_iterations):
    # Ensure we update the global variable rather than a local copy
    global total_iterations
    costall = []
    # Start-time used for printing time-usage below
    start_time = time.time()
    
    for i in range(total_iterations,total_iterations+num_iterations):
        x_batch,y_true_batch = data.train.next_batch(train_batch_size)
        feed_dict_train_op = {x:x_batch,y_true:y_true_batch,keep_prob:0.5}
        costall.append(sess.run(cross_entropy,feed_dict=feed_dict_train_op))
        feed_dict_train = {x:x_batch,y_true:y_true_batch,keep_prob:1.0}
        sess.run(optimizer,feed_dict=feed_dict_train_op)

        # Print status every 100 iterations.
        if i%100==0:
            acc = sess.run(accuracy,feed_dict=feed_dict_train)
            msg = "Optimization Iteration:{0:>6}, Training Accuracy: {1:>6.1%}"
            print(msg.format(i+1,acc))
    total_iterations += num_iterations
    
    # Ending time
    end_time = time.time()

    # Difference between start and end_times.
    time_dif = end_time-start_time
    times = list(range(10000))
    plt.plot(times,costall)
    plt.xlabel('Iterations')
    plt.ylabel('cost')
    plt.show()
    # Print the time-usage
    print("Time usage:"+str(timedelta(seconds=int(round(time_dif)))))
    
# Helper-function to plot example errors
def plot_example_errors(cls_pred,correct):
    incorrect = (correct==False)
    images = data.test.images[incorrect]
    cls_pred = cls_pred[incorrect]
    
    # Get the true classes for those images.
    cls_true = data.test.cls[incorrect]
    
    # Plot the first 9 images.
    plot_images(images=images[0:9],cls_true=cls_true[0:9],cls_pred=cls_pred[0:9])

# Helper-function to plot confusion matrix
def plot_confusion_matrix(cls_pred):
    # This is called from print _test_accuracy() below.
    
    # cls_pred is an array of the predicted class-number for 
    # all images in the test-set.
    
    # Get the true classifications for the test-set
    cls_true = data.test.cls
    
    # Get the confusion matrix using sklearn.
    cm = confusion_matrix(y_true=cls_true,y_pred=cls_pred)
    
    # Print the confusion matrix as text
    print(cm)
    
    # Plot the confusion matrix as an image.
    plt.matshow(cm)

    # Make various adjustments to the plot
    plt.colorbar()
    tick_marks = np.arange(num_classes)
    plt.xticks(tick_marks,range(num_classes))
    plt.yticks(tick_marks,range(num_classes))
    plt.xlabel('Predicted')
    plt.ylabel('True')
    
    # Ensure the plot is shown correctly with multiple plots 
    # in a single Notebook cell
    plt.show()
    
# Helper-function for showing the performance
# Function for printing the classification accuracy on the test-set
# Split the test-set into smller batches of this size
test_batch_size = 256
def print_test_accuracy(show_example_errors=False,show_confusion_matrix=False):
    # Number of images in the test-set.
    num_test = len(data.test.images)
    
    # Allocate an array for thepredicted classes which
    # will be calculated in the batches and filles into thos array
    cls_pred = np.zeros(shape=num_test,dtype=np.int)
    
    # Now claculate the predicted classes for the batches.
    # We  will just iterate through all the batches.
    # There might be a more clever and Pythonic way of doing this.
    
    # The starting index for the next batch is denoted i.
    i = 0
    
    while i<num_test:
        # The ending index for the next batch is denoted j.
        j = min(i+test_batch_size,num_test)
        
        # Get the images from the test-set between index i and j
        images = data.test.images[i:j, :]
        
        # Get the associated labels
        labels = data.test.labels[i:j, :]
        
        # Create a feed-dict with these images and labels.
        feed_dict={x:images,y_true:labels,keep_prob:1.0}
        # Calculate the predicted class using Tensorflow.
        cls_pred[i:j] = sess.run(y_pred_cls,feed_dict=feed_dict)
        
        # Set the start-index for the next batch to the 
        # end-index of the current batch
        i = j
        
    # Convenience variable for the true class-numbers of the test-set.
    cls_true = data.test.cls
    
    # Create a boolean array whether each image is correctly classified.
    correct = (cls_true==cls_pred)
    
    # Calculate the number of correctly classified images.
    # When  summing a boolean array, False means () and True means 1.
    correct_sum = correct.sum()
    
    # Classification accuracy is the number of correctly classified
    # images divided by the total number of images in the test-set.
    acc = float(correct_sum) / num_test
    
    # Print the accuracy
    msg = "Accuracy on Test-Set: {0:.1%} ({1}/{2})"
    print(msg.format(acc,correct_sum,num_test))
    
    # Plot some examples of mis-classifications, if desired
    if show_example_errors:
        print("Example errors:")
        plot_example_errors(cls_pred=cls_pred,correct=correct)
    
    # Plot the confusion matrix, if desired.
    if show_confusion_matrix:
        print("Confusion Matrix:")
        plot_confusion_matrix(cls_pred=cls_pred)
        
# Show the performance before any optimization 
# print_test_accuracy()
# the result:
# Accuracy on Test-Set: 9.8% (982.0/10000)

# Performance after 1optimizer iteration 
#optimize(num_iterations=1)
#print_test_accuracy()

# Performance after 100 optimization iterations
# optimize(num_iterations=100)
# print_test_accuracy(show_example_errors=True,show_confusion_matrix=True)

# Performance after 10000 optimization iterations
# optimize(num_iterations=10000)
#print_test_accuracy(show_example_errors=True,show_confusion_matrix=True)

# Visualization of weights and layers
# We will now visualize the weights of th convolutional filters
# and the resulting output images.
def plot_conv_weights(weights, input_channel=0):
    w = sess.run(weights)

    w_min = np.min(w)
    w_max = np.max(w)

    # Number of filters used in the conv. layer.
    num_filters = w.shape[3]
    num_grids =int(num_filters/8)
    # Create figure with a grid of sub-plots.
    fig,axes = plt.subplots(num_grids, 8)

    # Plot all the filter-weights
    for i,ax in enumerate(axes.flat):
        # Only plot the valid filter-weights.
        if i<num_filters:
            img = w[:, :, input_channel,i]

            # PLot image.
            ax.imshow(img, vmin=w_min,vmax=w_max,
                      interpolation='nearest',cmap='gray')#cmap='seismic')

        # Remove ticks from the plot
        ax.set_xticks([])
        ax.set_yticks([])
    plt.show()

# Helper function for plotting the output of a convolutional layer
def plot_layer(layer, image):
    feed_dict = {x: [image]}
    values = sess.run(layer, feed_dict=feed_dict)
    num_filters = values.shape[3]
    num_grids =int(num_filters/8)
    fig,axes = plt.subplots(num_grids, 8)

    for i,ax in enumerate(axes.flat):
        if i<num_filters:
            img = values[0,:,:,i]
            # Plot images
            ax.imshow(img, interpolation='nearest',cmap='binary')

        # Remove ticks from the plot
        ax.set_xticks([])
        ax.set_yticks([])
    plt.show()

# Input Images
def plot_image(image):
    plt.imshow(image.reshape(img_shape),
               interpolation='nearest',
               cmap='binary')
    plt.show()

if __name__ == "__main__":
    image2 = data.test.images[12]
    plot_image(image2)
    #print(image2)
    #plot_image(image2)
    #image2 = r"plotimages\0.png"
    plot_conv_weights(weights=weights_conv1)

    #plot_conv_layer(layer=layer_fc2,image=image1)
    #plot_conv_layer(layer=layer_conv1,image=image2)
    plot_conv_weights(weights=weights_conv2)
    #plot_conv_weights(weights=weights_conv2,input_channel=0)
    plot_layer(pool_layer1, image2)
    '''
    plot_conv_weights(weights=weights_conv2,input_channel=1)
    plot_conv_layer(layer=layer_conv2, image=image1)
    plot_conv_layer(layer=layer_conv2, image=image2)
    '''
