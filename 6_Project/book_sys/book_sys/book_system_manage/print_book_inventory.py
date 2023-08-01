from tkinter import *
import sqlite3
from tkinter import ttk

##显示库存信息
def print_book_inventory():
    win = Toplevel()
    win.geometry('505x216')
    win.title('图书库存管理系统')
    columns = ("书号","书名","库存")

    treeview = ttk.Treeview(win, height=18, show="headings", columns=columns)

    treeview.column("书号", width=100, anchor='center')  # 表示列,不显示
    treeview.column("书名", width=300, anchor='center')
    treeview.column("库存", width=100, anchor='center')

    treeview.heading("书号", text="书号")  # 显示表头
    treeview.heading("书名", text="书名")
    treeview.heading("库存", text="出版时间")
    treeview.pack(side=LEFT, fill=BOTH)

    conn = sqlite3.connect("D:/BookManagerSys.db")
    print("数据库连接成功！")

    sqlstr = "select * from  book"
    inv = conn.execute(sqlstr)
    i = 0
    for row in inv:
        treeview.insert('', i, values=(row[0], "《" + row[1]+"》", row[4]))
        i += 1
    conn.close()