#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>
#include <sys/types.h>
//通过fork等系统调用创建子进程，演示父进程提前退出对子进程的影响
int main()
{
  pid_t pid = fork();

  if (0 == pid)
  {
    printf("child ppid is %d\n", getppid());
    sleep(10);
    printf("parent ppid is %d\n", getppid());
  }
  else
  {
    printf("parent id is %d\n", getpid());
    sleep(5);
    exit(0);
  }
}