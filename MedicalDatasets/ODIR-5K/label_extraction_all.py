# -*- coding: utf-8 -*-
# @Time    : 2022/7/7 11:23
# @Author  : wu xin
# @FileName: csv_to_txt.py
# @IDE: PyCharm
# 提取csv文件的图片文件名和label信息保存到txt文件
import pandas as pd


def csv_to_txt(csv_path, txt_path):
    data = pd.read_csv(csv_path, encoding='utf-8')
    with open(txt_path, 'w', encoding='utf-8') as f:
        for line in data.values:
            file_name = str(line[18])  # 获取图像文件名，文件名在csv文件的第18列（从0开始）
            if str(line[16]) == "['N']":  # 获取文件label信息，共8个label,都转化为0~7
                labels = 0
            elif str(line[16]) == "['D']":
                labels = 1
            elif str(line[16]) == "['G']":
                labels = 2
            elif str(line[16]) == "['C']":
                labels = 3
            elif str(line[16]) == "['A']":
                labels = 4
            elif str(line[16]) == "['H']":
                labels = 5
            elif str(line[16]) == "['M']":
                labels = 6
            elif str(line[16]) == "['O']":
                labels = 7
            labels = "%s" % labels
            f.write(file_name + ' ' + labels + '\n')


if __name__ == '__main__':
    csv_to_txt('./full_df.csv', './label.txt')
