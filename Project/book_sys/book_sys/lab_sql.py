# -*- coding:utf-8 -*-
import sqlite3
# 创建数据库
conn = sqlite3.connect("D:/BookManagerSys.db")
cursor = conn.cursor()
print("连接成功")

# 为避免重复建表，建表时sqlite自动判断
# 创建表格书号,书名,出版时间,价格,库存

sqlstr1 = "create table if not exists book(number varchar(8) primary key, name varchar(25), press_time date, price varchar(25),inventory_num varchar(25))"
cursor.execute(sqlstr1)
print("图书表创建成功！")

# 创建用户表：用户名，密码，邮箱，密码错误次数（达到三次时锁定）
sqlstr2 = "create table if not exists loginuser(username varchar(30) primary key, password varchar(16), email varchar(25),loginerror int)"
conn.execute(sqlstr2)
print("用户表创建成功")

#  添加数据记录
sql_cmd = 'select * from  book'
cursor.execute(sql_cmd)
res = cursor.fetchall()
cur = conn.cursor()
if len(res) > 0:
	print("信息已存在！")
else:
	sqlstr1 = "insert into book(number, name, press_time, price, inventory_num) values(042099,'Photoshop入门到创意', '2016-04-01',45.0,5) "
	cur.execute(sqlstr1)
	sqlstr2 = "insert into book(number, name, press_time, price, inventory_num) values(480354,'SSM轻量级框架应用实战', '2018-05-01',66.8,4) "
	cur.execute(sqlstr2)
	sqlstr3 = "insert into book(number, name, press_time, price, inventory_num) values(978751,'HTML5+CSS3前端技术', '2016-04-01',52.0,7) "
	cur.execute(sqlstr3)
	conn.commit()
	print("记录添加成功")
conn.close()
