from tkinter import *
import tkinter.filedialog as td
import tkinter.messagebox as tb
import tkinter.ttk as ttk
from tkinter.scrolledtext import ScrolledText
import sys

from PIL import Image, ImageTk, ImageDraw

from use_model import *
import os

# TensorFlow will show warning message, close as follow
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'


class Recon:
    def __init__(self):
        # ----- Initialize data -----------------------------------
        self.num = ['0.png', '1.png', '2.png', '3.png', '4.png', '5.png', '6.png', '7.png', '8.png', '9.png']
        self.allconv1weight = ['conv1weight0.png', 'conv1weight1.png', 'conv1weight2.png', 'conv1weight3.png',
                               'conv1weight4.png', 'conv1weight5.png', 'conv1weight6.png', 'conv1weight7.png',
                               'conv1weight8.png', 'conv1weight9.png']
        self.allconv1out = ['conv1out0.png', 'conv1out1.png', 'conv1out2.png', 'conv1out3.png', 'conv1out4.png',
                            'conv1out5.png', 'conv1out6.png', 'conv1out7.png', 'conv1out8.png', 'conv1out9.png']
        self.allconv2weight = ['conv2weight0.png', 'conv2weight1.png', 'conv2weight2.png', 'conv2weight3.png',
                               'conv2weight4.png', 'conv2weight5.png', 'conv2weight6.png', 'conv2weight7.png',
                               'conv2weight8.png', 'conv2weight9.png']
        self.allconv2out = ['conv2out0.png', 'conv2out1.png', 'conv2out2.png', 'conv2out3.png', 'conv2out4.png',
                            'conv2out5.png', 'conv2out6.png', 'conv2out7.png', 'conv2out8.png', 'conv2out9.png']
        # ----- Initialize  the window ----------------------------
        self.root = Tk()
        self.netstr = ttk.LabelFrame(self.root, text='网络结构')
        self.numberrec = ttk.LabelFrame(self.root, text='识别器')
        self.train = ttk.LabelFrame(self.root, text='控制台')

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
        self.menubar.add_command(label='打开文件', command=self.openfile)
        # self.menubar.add_command(label='Train',command=self.train_data)
        #

        self.menubar.add_command(label='退出', command=sys.exit)

        # -----------Net Structure LabelFrame--------------
        self.inputdata = Label(self.netstr, text="Input Layer (28*28)")
        self.inputcanv = Canvas(self.netstr,
                                width=30, height=30, bg='#efefef')

        self.conv1lab = Label(self.netstr, text="Conv. 1 weight (32 feature maps)")
        self.conv1canv = Canvas(self.netstr,
                                width=350, height=224, bg='#efefef')

        self.conv1outlab = Label(self.netstr, text="Conv. 1 output")
        self.conv1outcan = Canvas(self.netstr,
                                  width=350, height=224, bg='#efefef')

        self.conv2lab = Label(self.netstr, text="Conv. 2 weight (64 feature maps)")
        self.conv2canv = Canvas(self.netstr,
                                width=338, height=238, bg='#efefef')

        self.conv2outlab = Label(self.netstr, text="Conv. 2 output")
        self.conv2outcan = Canvas(self.netstr,
                                  width=338, height=238, bg='#efefef')
        # ------------Recognize LabelFrame----------------
        self.pathlab = Label(self.numberrec, text="图片的路径:")
        self.pathentry = Entry(self.numberrec, state="readonly", width=35, textvariable=self.imagepath)
        self.recbutton = Button(self.numberrec, width=8, height=1, text="辨别",
                                state='disable', command=self.recobut)
        self.piccan = Canvas(self.numberrec,
                             width=168, height=168, bg='#f2f2f2')
        self.reshbutton = Button(self.numberrec, text='整形', state='disable', command=self.reshapepicc)
        self.graybutton = Button(self.numberrec, text='灰度化', state='disable', command=self.gray)
        self.normalizebutton = Button(self.numberrec, text='归一化', state='disable', command=self.normalize)
        self.resultlab = Label(self.numberrec, width=10, text="结果是:")
        self.resultnum = Entry(self.numberrec, width=9, state="readonly", textvariable=self.number)
        # ------------- console LabelFrame---------------------------
        self.trainmsg = ScrolledText(self.train, font=("Microsoft YaHei", 10), width=38, height=15, padx=5, pady=5)
        self.printt("欢迎来到手写数字识别系统  1.0")
        self.printt("----------------------------------------------")
        # ------- visualize the widgets ----------------------------
        self.root['menu'] = self.menubar

        self.netstr.grid(row=0, column=0, rowspan=2, padx=5, pady=5)
        self.numberrec.grid(row=0, column=6, padx=5, pady=5)
        self.train.grid(row=1, column=6, padx=5, pady=5)



        self.pathlab.grid(row=0, column=0)
        self.pathentry.grid(row=1, column=0, padx=5, pady=5)
        self.recbutton.grid(row=1, column=1, padx=5, pady=5)
        # self.piccan.grid(row=2,column=0,columnspan=2)
        self.piccan.grid(row=2, column=0, rowspan=3)
        self.reshbutton.grid(row=2, column=1)
        self.graybutton.grid(row=3, column=1)
        self.normalizebutton.grid(row=4, column=1, padx=5)
        self.resultlab.grid(row=5, column=0)
        self.resultnum.grid(row=5, column=1, padx=5, pady=5)

        self.trainmsg.grid(column=0, row=0, padx=5, pady=5)

    # -----  Function to print in the 'Console'------------------------------
    def printt(self, msg):
        self.message.set(msg + "\n")
        self.trainmsg.insert(INSERT, self.message.get())
        self.trainmsg.see(END)

    # ------ Menu function --------------------------------------
    def openfile(self):
        try:
            fname = td.askopenfilename(title="open",
                                       filetypes=[('Image', '*.png'), ('Image', '*.bmp'), ('All Files', '*')])
            self.imagepath.set(fname)
            self.showimage()
            del (self.im_nparr)
            self.reshbutton['state'] = 'normal'
        except AttributeError:
            tb.showwarning('警告', "你没有导入任何图像!")
        except IOError:
            tb.showwarning('警告', "你应该导入一个图像!")

    # ------- function to show image ------------------------------
    def showimage(self):
        self.__img = Image.open(self.imagepath.get())
        # print(self.__img)
        self.__photo = ImageTk.PhotoImage(self.__img)
        self.piccan.create_image((84, 84), image=self.__photo)
        # self.recbutton['state']='normal'

    # ------- function to train data ---------------------------


    # ------------ Recognizer button----------------------------
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
        self.im_nparr, self.finalpic = normalizepic(pic)
        self.recbutton['state'] = 'normal'

    def recobut(self):
        # try:
        # self.im,self.imgs = picprocess(self.imagepath.get())
        # self.imgs.show()
        # print(self.imgs.size)
        try:
            self.start_time = time.time()
            self.recresult = str(rec(self.im_nparr))
            self.end_time = time.time()
            self.number.set("        " + self.recresult)
            self.printt("识别的结果是:" + self.recresult)
            self.time_dif = self.end_time - self.start_time
            self.printt("用时:" + str(timedelta(seconds=int(round(self.time_dif)))))
        except:
            tb.showwarning('警告', "你应该先对图像进行预处理!")

    # ------- function to mainkoop ---------------------------------
    def runloop(self):
        self.root.title("手写数字识别系统")
        # geometry('窗口大小'+‘窗口初始位置’)
        self.root.geometry('500x605+150+30')
        self.root.resizable(False, False)
        self.root.iconbitmap("./bitmap/pic.ico")
        self.root.mainloop()


if __name__ == "__main__":
    root = Recon()
    root.runloop()
