# -*- coding: utf-8 -*-
# @Time    : 2022/7/7 12:02
# @Author  : wu xin
# @FileName: copyfile.py
# @IDE: PyCharm
import shutil
import os


def copy(txt_pth, save_path):
    with open(txt_pth, 'r', encoding='utf-8') as fh:
        savepath = save_path
        lines = fh.readlines()
        for line in lines:
            img_name = line.replace("\n", "")
            srcpath = './' + img_name

            print(srcpath)
            shutil.copy(srcpath, savepath)


if __name__ == '__main__':
    # 创建文件夹
    label = ["train", "val", "test"]

    for i in label:
        os.mkdir(i)

    train_txt_path = './img_path_train.txt'
    train_save_path = './train'
    copy(train_txt_path, train_save_path)
    print("copy trainset successfully!")

    val_txt_path = './img_path_val.txt'
    val_save_path = './val'
    copy(val_txt_path, val_save_path)
    print("copy valset successfully!")

    test_txt_path = './img_path_test.txt'
    test_save_path = './test'
    copy(test_txt_path, test_save_path)
    print("copy testset successfully!")
