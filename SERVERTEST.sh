#!/bin/bash
filePath="/run/eula.txt"
if [ ! -f "$filePath" ];then
touch $filePath
echo "eula=true" > $filePath
echo "文件创建完成"
else
echo "文件已经存在"
fi
