from tkinter import *
from tkinter import ttk
import sqlite3

#添加书目信息
def print_book_list():
    win = Toplevel()
    win.geometry('800x216')
    win.title('图书库存管理系统')

    columns = ("书号", "书名","出版时间","价格")

    treeview = ttk.Treeview(win, height=18, show="headings", columns=columns)

    treeview.column("书号", width=100, anchor='center')  # 表示列,不显示
    treeview.column("书名", width=300, anchor='center')
    treeview.column("出版时间", width=100, anchor='center')
    treeview.column("价格", width=300, anchor='center')

    treeview.heading("书号", text="书号")  # 显示表头
    treeview.heading("书名", text="书名")
    treeview.heading("出版时间", text="出版时间")  # 显示表头
    treeview.heading("价格", text="价格")
    treeview.pack(side=LEFT, fill=BOTH)

    conn = sqlite3.connect("D:/BookManagerSys.db")
    print("数据库连接成功！")

    sqlstr = "select * from  book"
    s = conn.execute(sqlstr)
    i = 0
    for row in s:
        treeview.insert('', i, values=(row[0], "《" + row[1]+"》", row[2], row[3]))
        i += 1
    conn.close()
