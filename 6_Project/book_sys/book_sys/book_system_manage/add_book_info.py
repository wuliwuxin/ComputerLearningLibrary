from tkinter import *
import sqlite3
import tkinter.messagebox as messagebox
import datetime

def insert_add():
	conn = sqlite3.connect("D:/BookManagerSys.db")
	curs = conn.cursor()
	while 1:
		val = (number.get(), name.get(), press_time.get(), price.get(), inventory_num.get())
		if len(number.get()) == 0 or len(name.get()) == 0 or len(press_time.get()) == 0 or len(price.get()) == 0 or len(inventory_num.get()) == 0:
			messagebox.showwarning('图书添加失败', '请确认为图书信息！')
			break
		else:
			query = 'insert into book values (?,?,?,?,?)'

			if check_number(number.get()) == False or check_context_num(number.get()) == False:  # 检查书号是否错误
				messagebox.showerror("图书添加失败", "请重新输入书号(6位数字)：")
				break
			elif check_date(press_time.get()) == False:# 检查时间是否错误
				messagebox.showerror("图书添加失败", "请重新时间（2018-12-11）：")
				break
			elif check_context_num(price.get()) == False:  # 检查价格是否错误
				messagebox.showerror("图书添加失败", "请重新价格(数字)")
				break
			elif check_context_num(inventory_num.get()) == False:  # 检查库存是否错误
				messagebox.showerror("图书添加失败", "请重新库存(数字)")
				break
			else:
				try:
					curs.execute(query, val)
					conn.commit()
					conn.close()
					messagebox.showinfo('图书添加成功','谢谢您的使用！')
					break
				except sqlite3.IntegrityError:
					messagebox.showerror("图书添加失败", "该图书已存在")
					break
#检查书号错误
def check_number(book_number):
	try:
		book_number = int(book_number)
		if len(str(book_number)) != 6:
			return False
	except:
		return True
#检查日期错误
def check_date(date):
	try:
		datetime.datetime.strptime(date, '%Y-%m-%d')
		return True
	except:
		return False
#  判断输入内容是不是数字
def check_context_num(price):
	if price.isdigit():
		return True
	else:
		return False

#添加书目信息
def add_book_info():
	win = Toplevel()
	win.geometry('550x216')
	win.title('图书库存管理系统')
	group = LabelFrame(win,text="添加图书信息",font=('华文新魏','16'))
	global number, name, press_time, price, inventory_num
	number = StringVar()
	name = StringVar()
	press_time = StringVar()
	price = StringVar()
	inventory_num = StringVar()
	txt6 = StringVar()
	txt6.set("请输入图书信息")
	lab1 = Label(group, text="书号：", font=('华文新魏','16'))
	lab2 = Label(group, text="书名：", font=('华文新魏','16'))
	lab3 = Label(group, text="出版时间：", font=('华文新魏','16'))
	lab4 = Label(group, text="价格：", font=('华文新魏','16'))
	lab5 = Label(group, text="库存：", font=('华文新魏','16'))
	lab6 = Label(group,textvariable=txt6, relief='ridge', width=18, font=('华文新魏','16'))
	entry1 = Entry(group,textvariable=number, width=16, font=('宋体','16'))
	entry2 = Entry(group,textvariable=name, width=16, font=('宋体','16'))
	entry3 = Entry(group,textvariable=press_time, width=16, font=('宋体','16'))
	entry4 = Entry(group,textvariable=price, width=16, font=('宋体','16'))
	entry5 = Entry(group,textvariable=inventory_num, width=16, font=('宋体','16'))
	button = Button(group, text='提交', command=insert_add, font=('宋体','16'))
	#布局设置
	group.grid(row=0, column=0,padx=5,pady=5)
	lab1.grid(row=1, column=0)
	lab2.grid(row=2, column=0)
	lab3.grid(row=3, column=0)
	lab4.grid(row=4, column=0)
	lab5.grid(row=5, column=0)
	lab6.grid(row=6, column=0,columnspan=2)
	entry1.grid(row=1, column=1)
	entry2.grid(row=2, column=1)
	entry3.grid(row=3, column=1)
	entry4.grid(row=4, column=1)
	entry5.grid(row=5, column=1)
	button.grid(row=6, column=2)
	photo = PhotoImage(file="cat.gif")
	imgLabel = Label(win, image=photo)
	imgLabel.grid(row=0, column=4)
	mainloop()
