#include <stdio.h>     //标准输入输出库，提供printf函数
#include <stdlib.h>    //标准库，提供exit通用工具函数
#include <unistd.h>    //Unix便准库，提供fork，getpid等系统调用
#include <string.h>    //字符串处理函数库
#include <assert.h>    //断言库，提供assert宏
#include <sys/types.h> //系统数据类型定义，提供pid_t等类型

int main()
{
  pid_t pid = fork(); // fork是linux的操作系统命令，是一个系统调用，用于创建新进程
  // 调用一次fork会返回两次，在父进程中返回紫禁城的PID，在子进程中返回0，如果出错返回-1

  if (0 == pid)
  {
    printf("child id is %d\n", getpid());
    printf("parent id is %d\n", getppid());
  }
  else
  {
    while (1)
    {
    } // 使得子进程的父进程ID始终有效
  }
}