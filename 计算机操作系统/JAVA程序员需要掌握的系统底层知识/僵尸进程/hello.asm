;hello.asm  
;write(int fd, const void *buffer, size_t nbytes)  解释了write系统调用的参数含义：文件描述符、缓冲区指针、字节数
;fd 文件描述符 file descriptor - linux下一切皆文件
section data;;声明数据段，有些语法用.data
    msg db "Hello", 0xA;定义字节数据msg，内容为 "Hello" 加上 ASCII 码 0xA（换行符）
    len equ $ - msg;计算msg的长度（$表示当前地址）
section .text;声明代码段
global _start;将_start符号声明为全局可见
_start:;定义入口标签
​
    mov edx, len;将消息长度存入edx寄存器（第三个参数）
    mov ecx, msg;将消息地址存入ecx寄存器（第二个参数）
    mov ebx, 1 ;文件描述符1 std_out
    mov eax, 4 ;write函数系统调用号 4
    int 0x80;触发软中断，执行系统调用
​
    mov ebx, 0;将退出状态码0存入ebx寄存器
    mov eax, 1 ;exit函数系统调用号
    int 0x80;触发软中断，终止程序