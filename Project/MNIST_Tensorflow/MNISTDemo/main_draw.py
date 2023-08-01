# -*- coding:utf-8 -*-
from tkinter import *
import tkinter.filedialog as td
import tkinter.messagebox as tb
import tkinter.ttk as ttk
from tkinter.scrolledtext import ScrolledText
import sys
import random
import time
from datetime import timedelta
from PIL import Image, ImageTk, ImageDraw
import input_data
from use_model import *
import os

import cv2 as cv
# TensorFlow will show warning message, close as follow
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

class Recon(object):
    def __init__(self):
        # ----- Initialize data -----------------------------------
        self.num = ['0.png','1.png','2.png','3.png','4.png','5.png','6.png','7.png','8.png','9.png']
        self.allconv1weight = ['conv1weight0.png','conv1weight1.png','conv1weight2.png','conv1weight3.png',
                               'conv1weight4.png','conv1weight5.png','conv1weight6.png','conv1weight7.png',
                               'conv1weight8.png','conv1weight9.png']
        self.allconv1out = ['conv1out0.png','conv1out1.png','conv1out2.png','conv1out3.png','conv1out4.png',
                            'conv1out5.png','conv1out6.png','conv1out7.png','conv1out8.png','conv1out9.png']
        self.allconv2weight = ['conv2weight0.png','conv2weight1.png','conv2weight2.png','conv2weight3.png',
                               'conv2weight4.png','conv2weight5.png','conv2weight6.png','conv2weight7.png',
                               'conv2weight8.png','conv2weight9.png']
        self.allconv2out = ['conv2out0.png','conv2out1.png','conv2out2.png','conv2out3.png','conv2out4.png',
                            'conv2out5.png','conv2out6.png','conv2out7.png','conv2out8.png','conv2out9.png']
        # ----- Initialize  the window ----------------------------
        self.root = Tk()
        self.netstr = ttk.LabelFrame(self.root, text='Net Structure')
        self.numberrec = ttk.LabelFrame(self.root, text='Recognizer')
        self.train = ttk.LabelFrame(self.root, text='Console')

        # ----- define the transferable variables-----------------
        self.imagepath = StringVar(self.root)
        self.inputreadom = StringVar(self.root)
        self.message = StringVar(self.root)
        self.number = StringVar(self.root)
        self.im_nparr = 0
        # ---- Initilaize the widgets -----------------------------
        # ------Menu--------------------
        self.menubar = Menu(self.root)
        self.root.config(menu=self.menubar)
        self.menubar.add_command(label='OpenFile', command=self.openfile)
        # self.menubar.add_command(label='Train', command=self.train_data)

        self.plott = Menu(self.menubar, tearoff=0)
        self.menubar.add_cascade(label='Plot', state='normal', menu=self.plott)
        self.plott.add_command(label='Input Layer', command=self.plot_inputdata)
        self.plott.add_command(label='Conv. 1 weight', command=self.plot_conv1)
        self.plott.add_command(label='Conv. 1 output', command=self.plot_conv1out)
        self.plott.add_command(label='Conv. 2 weight', command=self.plot_conv2)
        self.plott.add_command(label='Conv. 2 output', command=self.plot_conv2out)

        self.menubar.add_command(label='Quit',command=sys.exit)

        # -----------Net Structure LabelFrame--------------
        self.inputdata = Label(self.netstr,text="Input Layer (28*28)")
        self.inputcanv = Canvas(self.netstr,
                                width=30,height=30,bg='#efefef')

        self.conv1lab = Label(self.netstr,text="Conv. 1 weight (32 feature maps)")
        self.conv1canv = Canvas(self.netstr,
                                width=350,height=224,bg='#efefef')

        self.conv1outlab = Label(self.netstr,text="Conv. 1 output")
        self.conv1outcan = Canvas(self.netstr,
                                  width=350,height=224,bg='#efefef')

        self.conv2lab = Label(self.netstr,text="Conv. 2 weight (64 feature maps)")
        self.conv2canv = Canvas(self.netstr,
                                width=338,height=238,bg='#efefef')

        self.conv2outlab = Label(self.netstr,text="Conv. 2 output")
        self.conv2outcan = Canvas(self.netstr,
                                  width=338,height=238,bg='#efefef')
        # ------------Recognize LabelFrame----------------
        self.pathlab = Label(self.numberrec,text="the image path:")
        self.pathentry = Entry(self.numberrec,state="readonly",width=35,textvariable=self.imagepath)
        self.recbutton = Button(self.numberrec,width=8,height=1,text="Recognize",
                                state='disable',command=self.recobut)
        self.piccan = Canvas(self.numberrec,
                             width=168,height=168,bg='#ffffff')#"#bg='#f2f2f2')
        self.drawbutton = Button(self.numberrec,text='Draw',command=self.drawnumber)
        self.reshbutton = Button(self.numberrec,text='Reshape',state='disable',command=self.reshapepicc)
        self.graybutton = Button(self.numberrec,text='Graying',state='disable',command=self.gray)
        self.normalizebutton = Button(self.numberrec,text='Normalize',state='disable',command=self.normalize)
        self.resultlab = Label(self.numberrec,width=10,text="The result is:")
        self.resultnum = Entry(self.numberrec,width=9,state="readonly",textvariable=self.number)
        # ------------- console LabelFrame---------------------------
        self.trainmsg = ScrolledText(self.train,font=("Microsoft YaHei",10),width=38,height=15,padx=5,pady=5)
        self.printt("欢迎来到手写数字识别 1.0")
        self.printt("----------------------------------------------")
        # ------- visualize the widgets ----------------------------
        self.root['menu']=self.menubar

        self.netstr.grid(row=0,column=0,rowspan=2,padx=5,pady=5)
        self.numberrec.grid(row=0,column=6,padx=5,pady=5)
        self.train.grid(row=1,column=6,padx=5,pady=5)

        self.inputdata.grid(row=0,column=0)
        self.inputcanv.grid(row=1,column=0,padx=5,pady=5)
        self.conv1lab.grid(row=2,column=0,padx=5)
        self.conv1canv.grid(row=3,column=0,padx=5,pady=5)
        self.conv1outlab.grid(row=2,column=1,padx=5)
        self.conv1outcan.grid(row=3,column=1,padx=5,pady=5)
        self.conv2lab.grid(row=4,column=0,padx=5)
        self.conv2canv.grid(row=5,column=0,padx=5,pady=5)
        self.conv2outlab.grid(row=4,column=1,padx=5)
        self.conv2outcan.grid(row=5,column=1,padx=5,pady=5)

        self.pathlab.grid(row=0,column=0)
        self.pathentry.grid(row=1,column=0,padx=5,pady=5)
        self.recbutton.grid(row=1,column=1,padx=5,pady=5)
        self.piccan.grid(row=2,column=0,rowspan=3)
        #self.piccan.bind("<B1-Motion>", self.paint)
        self.drawbutton.grid(row=2,column=1)
        self.reshbutton.grid(row=3,column=1)
        self.graybutton.grid(row=4,column=1)
        self.normalizebutton.grid(row=5,column=1,padx=5)
        self.resultlab.grid(row=6,column=0)
        self.resultnum.grid(row=6,column=1,padx=5,pady=5)

        self.trainmsg.grid(column=0,row=0,padx=5,pady=5)

    # -----  Function to print in the 'Console'------------------------------
    def printt(self,msg):
        self.message.set(msg+"\n")
        self.trainmsg.insert(INSERT,self.message.get())
        self.trainmsg.see(END)

    # ------ Menu function --------------------------------------
    def openfile(self):
        try:
            fname = td.askopenfilename(title="open", filetypes=[('Image', '*.png'), ('Image', '*.bmp'), ('All Files', '*')])
            self.imagepath.set(fname)
            self.showimage()
            del(self.im_nparr)
            self.reshbutton['state'] = 'normal'
        except AttributeError:
            tb.showwarning('警告', "你没有导入任何图像！")
        except IOError:
            tb.showwarning('警告', "你应该导入一个图像！")
    # ------- function to show image ------------------------------
    def showimage(self):
        self.__img = Image.open(self.imagepath.get())
        self.__photo = ImageTk.PhotoImage(self.__img)
        self.piccan.create_image((84,84), image=self.__photo)

# ------- function to train data ---------------------------
#     def train_data(self):
#         self.printt("Train is running...")
#         self.data = input_data.read_data_sets("./data", one_hot=True)
#         self.printt("Successfully finished extracting the MNIST dataset!!!")
#         self.printt("Size of:\n--Training-set:\t\t:%d\n--Testing-set:\t\t:%d\n--Validation-set:\t\t:%d\n"
#                          %((len(self.data.train.labels),len(self.data.test.labels),len(self.data.validation.labels))))
#         self.menubar.entryconfig(3,state='normal')
        
# ------- function to show weights ---------------------------
    def plot_inputdata(self):
        self.all_img = ['0.png','1.png','2.png','3.png','4.png','5.png','6.png','7.png',
                        '8.png','9.png']
        self.choice = random.choice(self.all_img)
        self.inputreadom.set(self.choice)
        self.inputimg = Image.open(r"./plotimages/"+self.choice)
        self.inputphoto = ImageTk.PhotoImage(self.inputimg)
        self.inputcanv.create_image((15, 15), image=self.inputphoto)

    def plot_conv1(self):
        #self.conv1img = Image.open(r"I:\GraduationDesign\Project\paper\layerpic\conv1wei_7.png")
        try:
            self.conv1img = Image.open(r"./plotimages/"+self.allconv1weight[self.num.index(self.inputreadom.get())])
            self.conv1photo = ImageTk.PhotoImage(self.conv1img)
            self.conv1canv.create_image((175, 112), image=self.conv1photo)
        except:
            tb.showwarning('警告', '你应该先画出输入层！')
    def plot_conv1out(self):
        try:
            self.conv1outimg = Image.open(r"./plotimages/"+self.allconv1out[self.num.index(self.inputreadom.get())])
            self.conv1outphoto = ImageTk.PhotoImage(self.conv1outimg)
            self.conv1outcan.create_image((175, 112), image=self.conv1outphoto)
        except:
            tb.showwarning('警告', '你应该先画出输入层！')
    def plot_conv2(self):
        try:
            self.conv2img = Image.open(r"./plotimages/"+self.allconv2weight[self.num.index(self.inputreadom.get())])
            self.conv2photo = ImageTk.PhotoImage(self.conv2img)
            self.conv2canv.create_image((169, 119), image=self.conv2photo)
        except:
            tb.showwarning('警告', '你应该先画出输入层！')

    def plot_conv2out(self):
        try:
            self.conv2outimg = Image.open(r"./plotimages/"+self.allconv2out[self.num.index(self.inputreadom.get())])
            self.conv2outphoto = ImageTk.PhotoImage(self.conv2outimg)
            self.conv2outcan.create_image((169,119),image=self.conv2outphoto)
        except:
            tb.showwarning('警告', '你应该先画出输入层！')
    # ------------ Recognizer button----------------------------
    def drawnumber(self):
        self.drawing = False  # 鼠标按下为真
        self.mode = False  # 如果为真，画矩形，按m切换为曲线
        self.ix, self.iy = -1, -1
        def draw_circle(event, x, y, flags, param):
            ##global ix, iy, drawing, mode
            if event == cv.EVENT_LBUTTONDOWN:
                self.drawing = True
                self.ix, self.iy = x, y
            elif event == cv.EVENT_MOUSEMOVE:
                if self.drawing == True:
                    if self.mode == True:
                        cv.rectangle(self.img, (self.ix, self.iy), (x, y), (0, 255, 0), -1)
                    else:
                        cv.circle(self.img, (x, y), 5, (0, 0, 255), -1)
            elif event == cv.EVENT_LBUTTONUP:
                self.drawing = False
                if self.mode == True:
                    cv.rectangle(self.img, (self.ix, self.iy), (x, y), (0, 255, 0), -1)
                else:
                    cv.circle(self.img, (x, y), 5, (0, 0, 255), -1)
        self.img = 255 * np.ones((128, 128, 3), np.uint8)
        cv.namedWindow('image')
        cv.setMouseCallback('image', draw_circle)
        while (1):
            cv.imshow('image', self.img)
            k = cv.waitKey(1) & 0xFF
            if k == ord('m'):
                self.mode = not self.mode
            elif k == 27:
                break
        cv.destroyAllWindows()
        self.img = Image.fromarray(cv.cvtColor(self.img,cv.COLOR_BGR2RGB))
        if self.img.size != (28, 28):
            self.img = self.img.resize((28, 28))  # ,Image.ANTIALIAS)
        if self.img.mode != 'L':
            self.img = self.img.convert('L')
        im_arr = list(self.img.getdata())
        im_nparr = []
        for x in im_arr:
            x = 1 - x / 255
            im_nparr.append(x)
        self.im_nparr = np.array([im_nparr])
        self.tkimg = ImageTk.PhotoImage(self.img)
        self.piccan.create_image((84, 84), image=self.tkimg)
        self.recbutton['state'] = 'normal'

    def reshapepicc(self):
        self.piccan.delete("all")
        pic = self.imagepath.get()
        self.repic = reshapepic(pic)
        self.repicimg = ImageTk.PhotoImage(self.repic)
        self.piccan.create_image((84, 84), image=self.repicimg)
        self.graybutton['state'] = 'normal'
    def gray(self):
        self.piccan.delete("all")
        pic = self.repic
        self.graypp = graypic(pic)
        self.grayimg = ImageTk.PhotoImage(self.graypp)
        self.piccan.create_image((84, 84), image=self.grayimg)
        self.normalizebutton['state'] = 'normal'
    def normalize(self):
        pic = self.graypp
        self.im_nparr,self.finalpic = normalizepic(pic)
        self.recbutton['state']='normal'
    def recobut(self):
        try:
            self.start_time = time.time()
            self.recresult = str(rec(self.im_nparr))
            self.end_time = time.time()
            self.number.set("        "+self.recresult)
            self.printt("识别结果为:"+self.recresult)
            self.time_dif = self.end_time-self.start_time
            self.printt("使用时间:"+str(timedelta(seconds=int(round(self.time_dif)))))
        except:
            tb.showwarning('警告',"你应该先对图像进行预处理！")
    # -----paint function to paint the number-----------------
    '''
    def paint(self, event):
        x1, y1 = (event.x - 8), (event.y - 8)
        x2, y2 = (event.x + 8), (event.y + 8)
        self.piccan.create_arc(x1, y1, x2, y2, fill="black")
    '''
# ------- function to mainkoop ---------------------------------
    def runloop(self):
        self.root.title("手写识别系统")
        self.root.geometry('1100x660+150+30')
        self.root.resizable(False, False)
        self.root.iconbitmap("./bitmap/pic.ico")
        self.root.mainloop()

"""
当.py文件被直接运行时，if __name__ == '__main__'之下的代码块将被运行；
当.py文件以模块形式被导入时，if __name__ == '__main__'之下的代码块不被运行。
"""
if __name__ == "__main__":
    window = Recon()
    window.runloop()
