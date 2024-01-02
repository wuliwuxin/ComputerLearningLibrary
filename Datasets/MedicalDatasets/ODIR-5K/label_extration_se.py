# -*- coding: utf-8 -*-
# @Time    : 2022/7/7 11:56
# @Author  : wu xin
# @FileName: label_extration_se.py
# @IDE: PyCharm
# 提取csv文件的图片文件名和label信息保存到txt文件
import pandas as pd


def csv_to_txt(csv_path, txt_path):
    data = pd.read_csv(csv_path, encoding='utf-8')
    with open(txt_path, 'w', encoding='utf-8') as f:
        for line in data.values:
            file_name = str(line[18])  # 获取图像文件名，文件名在csv文件的第18列（从0开始）
            if str(line[16]) == "['N']":  # 获取文件label信息，csv文件共8个label
                labels = 0
            elif str(line[16]) == "['D']":
                labels = 1
            elif str(line[16]) == "['G']":
                labels = -1
            elif str(line[16]) == "['C']":
                labels = -1
            elif str(line[16]) == "['A']":
                labels = -1
            elif str(line[16]) == "['H']":
                labels = -1
            elif str(line[16]) == "['M']":
                labels = -1
            elif str(line[16]) == "['O']":
                labels = -1

            if labels >= 0:  # 将属于其他label的图片过滤掉。
                labels = "%s" % labels
                f.write(file_name + ' ' + labels + '\n')


if __name__ == '__main__':
    csv_to_txt('./full_df.csv', './label.txt')
