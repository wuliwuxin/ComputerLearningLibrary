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

# TensorFlow will show warning message, close as follow
import tkinter.messagebox as messagebox
from tkinter import *
import sqlite3

from main_win import Recon

class LoginPage:
	"""登录界面"""

	def __init__(self, master):
		self.root = master
		self.root.geometry('400x200+400+200')
		self.root.title('手写数字识别')
		self.conn = sqlite3.connect("C:/Users/wu/PycharmProjects/MNISTDemo/UserSys.db")
		self.username = StringVar()
		self.password = StringVar()
		self.page = Frame(self.root)
		self.creatapage()


	def creatapage(self):
		"""界面布局"""
		Label(self.page).grid(row=0)
		Label(self.page, text='用户名:').grid(row=1, stick=W, pady=10)
		Entry(self.page, textvariable=self.username).grid(row=1, column=1, stick=E)
		Label(self.page, text='密码:').grid(row=2, stick=W, pady=10)
		Entry(self.page, textvariable=self.password, show='*').grid(row=2, stick=E, column=1)
		Button(self.page, text='登录', command=self.login).grid(row=3, stick=W, pady=10)
		Button(self.page, text='注册账号', command=self.register).grid(row=3, stick=E, column=1)
		self.page.pack()

	def login(self):
		"""登录功能"""
		curs = self.conn.cursor()
		query = "select username, password, loginerror from loginuser where username='%s'" % self.username.get()
		curs.execute(query)  # 返回一个迭代器
		c = curs.fetchall()  # 接收全部信息
		if len(c) == 0:
			messagebox.showerror('登录失败', '账户不存在')
		else:
			us, pw, lerror = c[0]
			if lerror >= 3:
				messagebox.showwarning('登录失败', '账户已被锁定')
			elif us == self.username.get() and pw == self.password.get():
				self.conn.close()
				messagebox.showinfo('登录成功', '欢迎：%s' % us)
				self.page.destroy()
				os.system("main_win.py")
			else:
				messagebox.showwarning('登录失败', '密码错误')

	def register(self):
		"""注册功能跳转"""
		self.conn.close()
		self.page.destroy()
		RegisterPage(self.root)


class RegisterPage:
	"""注册界面"""

	def __init__(self, master=None):
		self.root = master
		self.root.title('账号注册')
		self.root.geometry('400x250+400+200')
		self.conn = sqlite3.connect("C:/Users/wu/PycharmProjects/MNISTDemo/UserSys.db")
		self.username = StringVar()
		self.password0 = StringVar()  # 第一次输入密码
		self.password1 = StringVar()  # 第二次输入密码
		self.email = StringVar()
		self.page = Frame(self.root)
		self.createpage()

	def createpage(self):
		"""界面布局"""
		Label(self.page).grid(row=0)
		Label(self.page, text="账号:").grid(row=1, stick=W, pady=10)
		Entry(self.page, textvariable=self.username).grid(row=1, column=1, stick=E)
		Label(self.page, text="密码:").grid(row=2, stick=W, pady=10)
		Entry(self.page, textvariable=self.password0, show='*').grid(row=2, column=1, stick=E)
		Label(self.page, text="再次输入:").grid(row=3, stick=W, pady=10)
		Entry(self.page, textvariable=self.password1, show='*').grid(row=3, column=1, stick=E)
		Label(self.page, text="Email*:").grid(row=4, stick=W, pady=10)
		Entry(self.page, textvariable=self.email).grid(row=4, column=1, stick=E)
		Button(self.page, text="返回", command=self.repage).grid(row=5, stick=W, pady=10)
		Button(self.page, text="注册", command=self.register).grid(row=5, column=1, stick=E)
		self.page.pack()

	def repage(self):
		"""返回登录界面"""
		self.page.destroy()
		self.conn.close()
		LoginPage(self.root)

	def register(self):
		"""注册"""
		if self.password0.get() != self.password1.get():
			messagebox.showwarning('错误', '密码核对错误')
		elif len(self.username.get()) == 0 or len(self.password0.get()) == 0 or len(self.email.get()) == 0:
			messagebox.showerror("错误", "不能为空")
		else:
			curs = self.conn.cursor()
			query = 'insert into loginuser values (?,?,?,?)'
			val = [self.username.get(), self.password0.get(), self.email.get(), 0]
			try:
				curs.execute(query, val)
				self.conn.commit()
				self.conn.close()
				messagebox.showinfo("成功", "注册成功，按确定返回登录界面")
				self.page.destroy()
				LoginPage(self.root)
			except sqlite3.IntegrityError:
				messagebox.showerror("注册失败", "该账户已存在")


if __name__ == '__main__':
	root = Tk()
	LoginPage(root)
	root.mainloop()